package com.bjit.demo_blog.services;

import com.bjit.demo_blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
    public CategoryDto createCategory(CategoryDto categoryDto);
    //update
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    //delete
    public void delteCategory(Long categoryId);
    //get
    public CategoryDto getCategory(Long categoryId);
    //get All
    List<CategoryDto> getCategories();
}
