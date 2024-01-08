package com.bjit.demo_blog.services;

import com.bjit.demo_blog.entity.criteria_entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public List<Customer> getCustomerLeftJoinOrder();

    public List<Customer> getCustomerRightJoinOrder();
}
