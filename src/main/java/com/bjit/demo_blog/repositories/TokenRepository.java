package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t inner join User u on t.user.id =  u.id where u.id = :userId " +
            "and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Long userId);
}
