package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.payloads.UserDto;
import com.bjit.demo_blog.services.UserService;
import com.bjit.demo_blog.utils.ApiResponse;
import com.bjit.demo_blog.utils.ExcelHelper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    //    @GetMapping("/users")
    //GET - get user
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllusers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //GET - get user
    @GetMapping(value = "/users/withPagination", produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllusersWithPagination(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
                                                                   ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(userService.getAllUsersWithPagination(pageable));
    }


    @GetMapping(value = "/users/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }


    @PostMapping(value = "/users/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> upload(@RequestPart("file")MultipartFile file) {
        if(ExcelHelper.checkExcelFormat(file)) {
            this.userService.saveUserFromImportExcel(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is save successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
    }

}
