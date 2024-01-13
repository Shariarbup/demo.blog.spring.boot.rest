package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.criteria_entity.Employee;
import com.bjit.demo_blog.entity.criteria_entity.dto.EmployeeStatisticsInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
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
        System.out.println("Average salary Of Employees: "+ result);
    }

    public void employeesSumOfSalaryCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.sum(root.get("salary")));
        Double result = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println("Sum Of salary of Employees: "+ result);
    }

    public void  countDistinctEmployeeCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.countDistinct(root));
        Long result = entityManager.createQuery(criteriaQuery).getSingleResult();
        System.out.println("Number of Distinct Employees: "+ result);

    }

    public void writeaggregateFunctionToDTO() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeStatisticsInfo> query = criteriaBuilder.createQuery(EmployeeStatisticsInfo.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        Expression<Long> totalNumberOfEmployees = criteriaBuilder.count(employeeRoot);
        Expression<Long> totalNumberOfDistinctEmployees = criteriaBuilder.countDistinct(employeeRoot);
        Expression<Double> maxSalaryOftheEmployee = criteriaBuilder.max(employeeRoot.get("salary"));
        Expression<Double> averageSalaryOftheEmployee = criteriaBuilder.avg(employeeRoot.get("salary"));
        Expression<Number> sumOfSalaryOfEmployee = criteriaBuilder.sum(employeeRoot.get("salary"));
        query.select(criteriaBuilder.construct(EmployeeStatisticsInfo.class, totalNumberOfEmployees, totalNumberOfDistinctEmployees, maxSalaryOftheEmployee, averageSalaryOftheEmployee, sumOfSalaryOfEmployee));
        EmployeeStatisticsInfo result = entityManager.createQuery(query).getSingleResult();
        System.out.println("EmployeeStatistics info"+ result.toString());
    }

}
