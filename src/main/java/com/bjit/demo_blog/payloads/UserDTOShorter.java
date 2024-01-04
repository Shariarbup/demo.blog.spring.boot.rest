package com.bjit.demo_blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTOShorter {
    private String name;

    private String email;

    private String about;
}
