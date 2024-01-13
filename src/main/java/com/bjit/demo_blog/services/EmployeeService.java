package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.criteria_entity.Department;
import com.bjit.demo_blog.entity.criteria_entity.Employee;
import com.bjit.demo_blog.entity.criteria_entity.dto.EmployeeStatisticsInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void writeAggregateFunctionToDTO() {
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

    private void joinTwoTableAndFetchBothTableData() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        Root<Department> departmentRoot = query.from(Department.class);

        query.multiselect(employeeRoot, departmentRoot);

        query.where(criteriaBuilder.equal(employeeRoot.get("department"), departmentRoot.get("id")));
        List<Object[]> resultList = entityManager.createQuery(query).getResultList();
        for(Object[] objects : resultList) {
            Employee employee = (Employee) objects[0];
            System.out.println("Employee Information"+ employee.toString());
            Department department = (Department) objects[1];
            System.out.println("Department Information"+ department.toString());
        }
    }

    public void criteriaQueryWithGroupByAndHaving() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.multiselect(criteriaBuilder.count(employeeRoot.get("name")), criteriaBuilder.sum(employeeRoot.get("salary")), employeeRoot.get("department"));
        query.groupBy(employeeRoot.get("department"));
        query.having(criteriaBuilder.greaterThan(criteriaBuilder.sum(employeeRoot.get("salary")), 2000));
        List<Object[]> resultList = entityManager.createQuery(query).getResultList();
        for(Object[] objects : resultList) {
            Department department = (Department) objects[2];
            System.out.println(department.getId()+"\t"+department.getName());
            long count = (Long) objects[0];
            System.out.println("Count : " + count);
            Double salary = (Double) objects[1];
            System.out.println("Salary: "+salary);
        }
    }

}
