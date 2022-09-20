package com.bjit.demo_blog.serviceImpl;

import com.bjit.demo_blog.entity.Comment;
import com.bjit.demo_blog.entity.Post;
import com.bjit.demo_blog.exceptions.ResourceNotFoundException;
import com.bjit.demo_blog.payloads.CommentDto;
import com.bjit.demo_blog.repositories.CommentRespository;
import com.bjit.demo_blog.repositories.PostRepository;
import com.bjit.demo_blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRespository commentRespository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","post id",id));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saveComment = this.commentRespository.save(comment);
        return this.modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = this.commentRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", id));
        this.commentRespository.delete(comment);
    }
}
