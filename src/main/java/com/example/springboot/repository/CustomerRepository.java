package com.example.springboot.repository;

import com.example.springboot.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    Customer findById(String customerId);
}
