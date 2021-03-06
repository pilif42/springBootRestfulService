package com.example.springboot.repository;

import com.example.springboot.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

  Customer findById(Integer customerId);

  List<Customer> findByLastName(String customerLastName);

  Customer save(Customer aCustomer);
}
