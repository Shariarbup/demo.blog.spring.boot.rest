package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.payloads.EmailCredentialWithAttachment;
import com.bjit.demo_blog.payloads.EmailCredentials;
import com.bjit.demo_blog.payloads.UserDto;
import com.bjit.demo_blog.services.EmailSenderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name="Bearer Authentication")
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailCredentials emailCredential){
        emailSenderService.sendSimpleEmail(emailCredential.getToEmail(), emailCredential.getBody(), emailCredential.getBody());
        return new ResponseEntity<>("Email Successfully Send to "+emailCredential.getToEmail(), HttpStatus.OK);
    }

    @PostMapping("/send-email/attachment")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody EmailCredentialWithAttachment emailCredential) throws MessagingException {
        try {
            emailSenderService.sendEmailWithAttachment(emailCredential.getToEmail(), emailCredential.getBody(), emailCredential.getBody(), emailCredential.getAttachmentPath());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Email Successfully Send to "+emailCredential.getToEmail(), HttpStatus.OK);
    }
}
