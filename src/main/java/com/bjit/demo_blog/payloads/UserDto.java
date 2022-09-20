package com.bjit.demo_blog.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    @NotEmpty
    @Size(min=1, message = "Username must be min of 4 characters")
    private String name;
    @NotEmpty
    @Size(min=1, max = 10, message = "Password must be min of 1 chars and max of 10 characters")
//    @Pattern()
    private String password;
    @Email(message = "Your email address is not valid")
    private String email;
    @NotEmpty
    private String about;
    private Set<RoleDto> roles = new HashSet<>();
}
