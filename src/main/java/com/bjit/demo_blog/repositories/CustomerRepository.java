package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.criteria_entity.Customer;
import jakarta.persistence.Id;
import net.sf.jasperreports.components.iconlabel.IconLabelDirectionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Id> {
}
