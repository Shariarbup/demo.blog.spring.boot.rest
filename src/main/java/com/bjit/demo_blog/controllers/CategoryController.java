package com.bjit.demo_blog.controllers;

import com.bjit.demo_blog.payloads.CategoryDto;
import com.bjit.demo_blog.services.CategoryService;
import com.bjit.demo_blog.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }
    //update
    @PutMapping ("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long id){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping ("/categories/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        this.categoryService.delteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfullt", true), HttpStatus.OK);
    }
    //get
    @GetMapping ("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        CategoryDto getCategory = this.categoryService.getCategory(id);
        return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.OK);
    }
    //get All
    @GetMapping ("/categories")
    public ResponseEntity<List<CategoryDto>> getCategory(){
        List<CategoryDto> categoryDtos = this.categoryService.getCategories();
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
    }

}
