package com.bjit.demo_blog.services;

import com.bjit.demo_blog.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long id);
    void deleteComment(Long id);
}
