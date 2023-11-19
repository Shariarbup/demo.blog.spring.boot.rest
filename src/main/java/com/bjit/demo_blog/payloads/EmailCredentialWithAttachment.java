package com.bjit.demo_blog.payloads;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailCredentialWithAttachment {
    private String subject;
    private String toEmail;
    private String body;
    private String attachmentPath;
}
