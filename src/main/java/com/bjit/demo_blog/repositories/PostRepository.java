package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.Category;
import com.bjit.demo_blog.entity.Post;
import com.bjit.demo_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post>  findAllByUser(User user);
    List<Post> findAllByCategory(Category category);

    List<Post> findByTitleContaining(String title);

//    @Query("select p from Post p where p.title like :key")
//    List<Post> searchByTitle(String keyword); // eikhane :key bolte bhujai %key%
}
