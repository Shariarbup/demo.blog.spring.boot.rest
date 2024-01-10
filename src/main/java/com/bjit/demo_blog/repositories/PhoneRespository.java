package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.criteria_entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRespository extends JpaRepository<Phone, Long> {
}
