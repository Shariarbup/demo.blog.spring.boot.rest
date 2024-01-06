package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.payloads.UserDTOShorter;
import com.bjit.demo_blog.services.UserService;
import com.bjit.demo_blog.utils.SearchRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name="Bearer Authentication")
public class CriteriaQueryController {

    @Autowired
    private UserService userService;
    @GetMapping("/users/advancedQeuery")
    public ResponseEntity<List<User>> getUserByAdvancedQuery(
            @RequestParam(value = "searchRequest") SearchRequest searchRequest
    ){
        return ResponseEntity.ok(userService.findAllByAdvancedQuery(searchRequest));
    }

    @GetMapping(value = "/users/criteria/select", produces = "application/json")
    public ResponseEntity<List<User>> getUserCriteriaSelect(
    ){
        return ResponseEntity.ok(userService.findUserWhereUserIdisOneCriteriaSelect());
    }

    @GetMapping(value = "/users/criteria/selectUsernameList", produces = "application/json")
    public ResponseEntity<List<String>> getUserNameListOnlyCriteriaSelect(
    ){
        return ResponseEntity.ok(userService.findUserNameListCriteriaSelect());
    }

    @GetMapping(value = "/users/criteria/selectMultipleUserColumnList", produces = "application/json")
    public ResponseEntity<List<User>> getMultipleUserColumnListCriteriaSelect(
    ){
        return ResponseEntity.ok(userService.findMultipleUserColumnListCriteriaSelect());
    }

    @GetMapping(value = "/users/criteria/selectMultipleUserDtoShorterColumnList", produces = "application/json")
    public ResponseEntity<List<UserDTOShorter>> getMultipleUserDtoShorterColumnListCriteriaSelect(
    ){
        return ResponseEntity.ok(userService.findMultipleUserDtoShorterColumnListCriteriaSelect());
    }

    @GetMapping(value = "/users/criteria/selectUserTuples", produces = "application/json")
    public ResponseEntity<List<Tuple>> getMultipleUserDtoShorterTupleListCriteriaSelect(
    ){
        return ResponseEntity.ok(userService.getUserTuple());
    }

}
