package com.bjit.demo_blog.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SearchRequest {
    private Long id;
    private String name;
    private String email;
}
