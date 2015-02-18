package com.example.springboot.repository;

import com.example.springboot.domain.Customer;

public interface CustomerRepository {

    Customer getCustomer(String customerId);
    boolean createCustomer(Customer aCustomer);

}
