package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExcelService {
    ByteArrayInputStream getUserExcel() throws IOException;

    String saveUserUsingImportExcelAsync(MultipartFile multipartFile) throws Exception;

    CompletableFuture<List<User>> findAllUsers();
}
