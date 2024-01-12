package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.criteria_entity.Employee;
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
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        Long result = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println("Total Number Of Employee: "+ result);
    }

    public void employeesMaxSalaryCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.max(root.get("salary")));
        Double result = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println("Max salary Of Employee: "+ result);
    }

    public void employeesAverageSalaryCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.avg(root.get("salary")));
        Double result = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println("Max average Of Employee: "+ result);
    }
}
