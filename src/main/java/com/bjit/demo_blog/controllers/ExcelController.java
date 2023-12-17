package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.services.ExcelService;
import com.bjit.demo_blog.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name="Bearer Authentication")
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
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename - "+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @GetMapping("/jasperReport/users/{format}")
    public ResponseEntity<String> generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        String message = userService.exportUserListPdf(format);
        return ResponseEntity.ok(message);
    }

}
