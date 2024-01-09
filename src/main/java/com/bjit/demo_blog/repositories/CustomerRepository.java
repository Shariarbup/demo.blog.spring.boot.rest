package com.bjit.demo_blog.repositories;

import jakarta.persistence.Id;
import net.sf.jasperreports.components.iconlabel.IconLabelDirectionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Long, Id> {
}
