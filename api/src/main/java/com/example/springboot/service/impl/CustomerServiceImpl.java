package com.example.springboot.service.impl;

import com.example.springboot.domain.Customer;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
        // TODO remove the hardcoding below
        aCustomer.setCreatedBy("7c13f7e0-0c6d-7c4b-6327-ffde84545db0");
        aCustomer.setModifiedBy("7c13f7e0-0c6d-7c4b-6327-ffde84545db0");
        aCustomer.setVersion(1);
        long currentTime = System.currentTimeMillis();
        aCustomer.setCreated(currentTime);
        aCustomer.setModified(currentTime);

        Customer savedCustomer = customerRepository.save(aCustomer);

        return savedCustomer.getId();
    }
}
