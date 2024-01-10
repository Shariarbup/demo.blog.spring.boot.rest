package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.criteria_entity.Person;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Id> {
}
