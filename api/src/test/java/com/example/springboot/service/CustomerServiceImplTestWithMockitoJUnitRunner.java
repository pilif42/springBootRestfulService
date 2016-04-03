package com.example.springboot.service;

import com.example.springboot.domain.Customer;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTestWithMockitoJUnitRunner {
  @Mock
  CustomerRepository customerRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  @Test
  public void testFindById() {
    Customer aCustomer = new Customer();
    when(customerRepository.findById(1)).thenReturn(aCustomer);

    Customer result = customerService.findById(1);

    verify(customerRepository).findById(1);
    Assert.assertEquals(aCustomer, result);
  }
}
