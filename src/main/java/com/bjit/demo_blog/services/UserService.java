package com.bjit.demo_blog.services;

import com.bjit.demo_blog.payloads.UserDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
    void saveUserFromImportExcel(MultipartFile file);

    String exportUserListPdf(String reportFormat) throws FileNotFoundException, JRException;
}
