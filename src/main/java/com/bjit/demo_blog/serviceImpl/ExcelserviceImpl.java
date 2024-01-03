package com.bjit.demo_blog.serviceImpl;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.payloads.UserDto;
import com.bjit.demo_blog.repositories.UserRepository;
import com.bjit.demo_blog.services.ExcelService;
import com.bjit.demo_blog.utils.ExcelHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ExcelserviceImpl implements ExcelService {

    Logger logger = LoggerFactory.getLogger(ExcelserviceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ByteArrayInputStream getUserExcel() throws IOException {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        ByteArrayInputStream byteArrayInputStream = ExcelHelper.dataToExcel(userDtos);
        return byteArrayInputStream;
    }

    @Async
    @Override
    public String saveUserUsingImportExcelAsync(MultipartFile multipartFile) throws Exception {
        long start = System.currentTimeMillis();
        List<User> userList = ExcelHelper.parseCSVFileForImportUser(multipartFile);
        logger.info("Saving list of users of size {}", userList.size(), "" + Thread.currentThread().getName());
        userRepository.saveAll(userList);
        long end = System.currentTimeMillis();
        logger.info("Total time: {}", end-start);
        return "User import successfull";
    }

    @Async
    @Override
    public CompletableFuture<List<User>> findAllUsers(){
        logger.info("get list of user by "+Thread.currentThread().getName());
        List<User> users=userRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }


    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
