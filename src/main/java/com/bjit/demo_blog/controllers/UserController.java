package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.payloads.UserDto;
import com.bjit.demo_blog.services.UserService;
import com.bjit.demo_blog.utils.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name="Bearer Authentication")
public class UserController {
    @Autowired
    private UserService userService;

    //POST -  create user
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //PUT - update user
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,@Valid @RequestBody UserDto userDto){
        UserDto updateUserDto = userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updateUserDto);
    }
    //Admin
    //DELETE - delete user
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
    }

    //GET - get user
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllusers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

//    @GetMapping("/users")
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
