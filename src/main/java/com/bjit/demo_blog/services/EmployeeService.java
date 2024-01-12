package com.bjit.demo_blog.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EntityManager entityManager;
    public void totalEmployeeCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<EmployeeService> root = criteriaQuery.from(EmployeeService.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        Long result = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println("Total Number Of Employee: "+ result);
    }
}
