package com.bjit.demo_blog.serviceImpl;

import com.bjit.demo_blog.entity.criteria_entity.Customer;
import com.bjit.demo_blog.entity.criteria_entity.Order;
import com.bjit.demo_blog.services.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> getCustomerLeftJoinOrder() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        Join<Customer, Order> orderJoin = customerRoot.join("orders", JoinType.LEFT);
        query.select(customerRoot).distinct(true);
        TypedQuery<Customer> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Customer> getCustomerRightJoinOrder() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        Join<Customer, Order> orderJoin = customerRoot.join("orders", JoinType.RIGHT);
        query.select(customerRoot).distinct(true);
        TypedQuery<Customer> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Customer> getCustomerInnerJoinOrder() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        Join<Customer, Order> orderJoin = customerRoot.join("orders", JoinType.INNER);
        query.select(customerRoot).distinct(true);
        TypedQuery<Customer> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<String> getCustomerNameListFromOrderTable() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<Order> orderRoot = query.from(Order.class);
        Join<Order, Customer> customerJoin = orderRoot.join("customers", JoinType.INNER);
        query.select(customerJoin.get("firstName"));
        List<String> customerNames = entityManager.createQuery(query).getResultList();
        return customerNames;
    }
}
