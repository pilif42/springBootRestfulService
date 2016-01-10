package com.example.springboot.service;

import com.example.springboot.domain.Customer;
import com.example.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findById(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public List<Customer> findByLastName(String customerLastName) {
        return customerRepository.findByLastName(customerLastName);
    }

    @Override
    public int save(Customer aCustomer) {
        Customer savedCustomer = customerRepository.save(aCustomer);
        return savedCustomer.getId();
    }
}
