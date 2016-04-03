package com.example.springboot.config;

import com.example.springboot.service.CustomerService;
import com.example.springboot.service.impl.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
//  @Bean
//  public CustomerRepository customerRepository() {
//    CustomerRepository customerRepository = new CustomerRepository();
//    return customerRepository;
//  }

  @Bean
  public CustomerService customerService() {
    CustomerService customerService = new CustomerServiceImpl();
    return customerService;
  }
}
