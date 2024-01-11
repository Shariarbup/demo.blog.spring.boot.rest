package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.criteria_entity.Order;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Id> {
}
