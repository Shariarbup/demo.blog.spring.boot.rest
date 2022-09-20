package com.bjit.demo_blog.repository;

import com.bjit.demo_blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void repoTest(){
        String className = userRepository.getClass().getName();
        String packName = userRepository.getClass().getPackageName();
        System.out.println("Class Name: "+className);
        System.out.println("Package Name: "+packName);
    }
}
