package com.bjit.demo_blog.services;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ExcelService {
    ByteArrayInputStream getUserExcel() throws IOException;


}
