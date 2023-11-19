package com.bjit.demo_blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class EmailCredentials {
    private String subject;
    private String toEmail;
    private String body;
}
