package com.example.springboot.service;

import com.example.springboot.domain.Customer;

public interface CustomerService {
    Customer findById(String customerId);
}
