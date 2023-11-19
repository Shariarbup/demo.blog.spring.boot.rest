package com.bjit.demo_blog.serviceImpl;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.payloads.UserDto;
import com.bjit.demo_blog.repositories.UserRepository;
import com.bjit.demo_blog.services.ExcelService;
import com.bjit.demo_blog.utils.ExcelHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelserviceImpl implements ExcelService {
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
