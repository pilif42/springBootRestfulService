package com.example.springboot.service;

import com.example.springboot.domain.Customer;
import com.example.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(String customerId) {
        return customerRepository.getCustomer(customerId);
    }

    @Override
    public boolean createCustomer(Customer aCustomer) {
        return customerRepository.createCustomer(aCustomer);
    }
}
