package com.example.springboot.service;

import com.example.springboot.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer findById(int customerId);
    List<Customer> findByLastName(String customerLastName);
    int save(Customer aCustomer);
}
