package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.Post;
import com.bjit.demo_blog.payloads.PostDto;
import com.bjit.demo_blog.utils.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);
    //update
    PostDto updatePost(PostDto postDto, Long id);
    //delete
    void deletePost(Long id);
    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    //get single post
    PostDto getPostById(Long id);
    //get All post by category
    List<PostDto> getPostBycategory(Long CategoryId);
    //get All post by user
    List<PostDto> getAllPostByUser(Long userId);
    //search post
    List<PostDto> searchPosts(String keyword);
}
