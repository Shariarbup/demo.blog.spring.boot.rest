package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.services.UserService;
import com.bjit.demo_blog.utils.SearchRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

}
