package com.bjit.demo_blog.repositories;

import com.bjit.demo_blog.entity.criteria_entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
