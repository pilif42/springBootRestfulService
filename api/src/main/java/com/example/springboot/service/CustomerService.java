package com.example.springboot.service;

import com.example.springboot.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer findById(String customerId);
    List<Customer> findByLastName(String customerLastName);
    String save(Customer aCustomer);
}
