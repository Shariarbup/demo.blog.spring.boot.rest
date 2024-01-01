package com.bjit.demo_blog.utils;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.payloads.UserDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    Logger logger = LoggerFactory.getLogger(ExcelHelper.class);
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


    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }
    
    //excel to list of user
   public static List<User> convertExcelToListOfUser(InputStream inputStream) {
        List<User> list = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("data");// excel sheet er name jeita thakbe...ekhane oita boshaite hobe
            int rowNUmber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while(iterator.hasNext()) {
                Row row = iterator.next();
                if(rowNUmber == 0) {
                    rowNUmber++;
                    continue;
                }
                User user = new User();
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                while(cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            user.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            user.setAbout((String) cell.getStringCellValue());
                            break;
                        case 2:
                            user.setEmail((String) cell.getStringCellValue());
                            break;
                        case 3:
                            user.setName((String) cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<User> parseCSVFile(final MultipartFile file) throws Exception {
        final List<User> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[1]);
                    user.setEmail(data[2]);
                    user.setPassword(data[3]);
                    user.setAbout(data[4]);
                    users.add(user);
                }
                return users;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
