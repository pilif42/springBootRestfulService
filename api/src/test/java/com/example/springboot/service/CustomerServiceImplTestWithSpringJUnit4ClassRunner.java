package com.example.springboot.service;

import com.example.springboot.config.TestConfig;
import com.example.springboot.domain.Customer;
import com.example.springboot.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class, loader=AnnotationConfigContextLoader.class)
public class CustomerServiceImplTestWithSpringJUnit4ClassRunner {

  @Autowired
  private CustomerService customerService;

  @Mock
  CustomerRepository customerRepository;

  @Test
  public void testFindByIdExistingCustomer() {
    Customer theOneCustomer = new Customer();
    when(customerRepository.findById(1)).thenReturn(theOneCustomer);
    assertNotNull(customerService.findById(1));
  }
}
