package com.bjit.demo_blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long categoryId;
    @NotBlank
    @Size(min = 1, message = "Min size of category title is 4")
    private String categoryTitle;
    @NotBlank
    @Size(min = 1, message = "Min size of category description is 10")
    private String categoryDescription;
}
