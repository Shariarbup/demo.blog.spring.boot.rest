package com.bjit.demo_blog.utils;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.payloads.UserDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static String[] HEADERS={
            "ID",
            "ABOUT",
            "EMAIL",
            "NAME"
    };

    public static String SHEET_NAME = "user_data";

    public static ByteArrayInputStream dataToExcel(List<UserDto> userDtos) throws IOException {
        //create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            //create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            //create row : Header row
            Row row = sheet.createRow(0);

            for(int i=0; i<HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            //Value row
            int rowIndex = 1;
            for(UserDto userDto: userDtos) {
                Row dataRow = sheet.createRow(rowIndex);
                dataRow.createCell(0).setCellValue(userDto.getId());
                dataRow.createCell(1).setCellValue(userDto.getAbout());
                dataRow.createCell(2).setCellValue(userDto.getEmail());
                dataRow.createCell(3).setCellValue(userDto.getName());
                rowIndex++;
            }
            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e ) {
          e.printStackTrace();
            System.out.println("Data import failed");
        } finally {
            workbook.close();
            out.close();
        }
        return null;
    }
}
