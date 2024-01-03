package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.services.ExcelService;
import com.bjit.demo_blog.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    @Autowired
    private UserService userService;

    @GetMapping("/excel/users")
    public ResponseEntity<Resource> download() throws IOException {
        String fileName = "users.xlsx";
        ByteArrayInputStream actualData = excelService.getUserExcel();
        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename - " + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @GetMapping("/jasperReport/users/{format}")
    public ResponseEntity<String> generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        String message = userService.exportUserListPdf(format);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/jasperReport/nutrition/{format}")
    public ResponseEntity<String> generateReportForNutrition(@PathVariable String format) throws JRException, FileNotFoundException {
        String path = "E:\\";
        File file = ResourceUtils.getFile("classpath:nutrition.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> params = new HashMap<>();
        params.put("createdBy", "Al Shariar");
        params.put("firstName", "Al");
        params.put("lastName", "Shariar");
        params.put("dob", "07-11-1997");
        params.put("age", "26");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "user-list.html");
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "user-list.pdf");
        }
        return ResponseEntity.ok("Report generated successfully to this path : " + path);
    }

    @PostMapping(value = "/import-excel/users/async", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<String> imporrtUsersExcelAsync(@RequestParam(value = "files")MultipartFile[] files) throws Exception {
        for (MultipartFile file: files) {
            excelService.saveUserUsingImportExcelAsync(file);
        }
        return ResponseEntity.ok("Import successfull");
    }

    @GetMapping(value = "/users/byAsyncThread", produces = "application/json")
    public ResponseEntity getUsersByAsyncThread() {
        CompletableFuture<List<User>> user1 = excelService.findAllUsers();
        CompletableFuture<List<User>> user2 = excelService.findAllUsers();
        CompletableFuture<List<User>> user3 = excelService.findAllUsers();
        CompletableFuture.allOf(user1, user2, user3);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
