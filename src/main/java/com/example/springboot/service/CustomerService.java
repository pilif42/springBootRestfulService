package com.example.springboot.service;

import com.example.springboot.domain.Customer;

public interface CustomerService {

    Customer getCustomer(String customerId);
    boolean createCustomer(Customer aCustomer);

}
