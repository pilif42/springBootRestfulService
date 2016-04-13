package com.example.springboot.service;

import com.example.springboot.domain.Customer;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example of a unit test where we mock objects.
 *
 * Note that the class name ends up in Test. This is important for the class to be picked up automatically by Maven or
 * Gradle as a unit test to be run.
 *
 * In Maven, you should add the following to your pom:
 * <plugin>
 *   <groupId>org.apache.maven.plugins</groupId>
 *   <artifactId>maven-surefire-plugin</artifactId>
 *   <version>${maventest.version}</version>
 *   </plugin>
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
  @Mock
  CustomerRepository customerRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  @Before
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindById() {
    Customer aCustomer = new Customer();
    when(customerRepository.findById(1)).thenReturn(aCustomer);

    Customer result = customerService.findById(1);

    verify(customerRepository).findById(1);
    Assert.assertEquals(aCustomer, result);
  }
}
