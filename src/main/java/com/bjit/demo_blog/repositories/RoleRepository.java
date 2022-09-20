package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
