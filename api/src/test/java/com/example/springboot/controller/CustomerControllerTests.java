package com.example.springboot.controller;

import com.example.springboot.domain.Customer;
import com.example.springboot.error.OurException;
import com.example.springboot.service.CustomerService;
import com.example.springboot.utility.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static com.example.springboot.utility.CustomerBuilder.customer;
import static com.example.springboot.utility.JsonObjectMapperUtils.jsonMapper;
import static com.example.springboot.utility.MockMvcControllerAdviceHelper.mockAdviceFor;
import static com.example.springboot.utility.MvcHelper.getJson;
import static com.example.springboot.utility.MvcHelper.postJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTests {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController customerController;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders
            .standaloneSetup(customerController)
            .setHandlerExceptionResolvers(mockAdviceFor(RestExceptionHandler.class))
            .build();
  }

  @Test
  public void findCustomer() throws Exception {
    String testFirstName = "Ken";
    String testLastName = "Smith";
    Customer ourMockCustomer = customer().id(TestConstants.CUSTOMER_ID_1).firstName(testFirstName).lastName(testLastName).buildCustomer();
    when(customerService.findById(TestConstants.CUSTOMER_ID_1)).thenReturn(ourMockCustomer);
    List<Customer> ourMockCustomersList = new LinkedList<>();
    ourMockCustomersList.add(ourMockCustomer);
    when(customerService.findByLastName(testLastName)).thenReturn(ourMockCustomersList);

    ResultActions actions = mockMvc.perform(getJson("/customer").param("id", TestConstants.CUSTOMER_ID_1.toString()).param("lastName", testLastName));

    actions.andExpect(status().isOk());
    actions.andExpect(handler().handlerType(CustomerController.class));
    actions.andExpect(handler().methodName("findCustomer"));
    actions.andExpect(jsonPath("$.id", is(TestConstants.CUSTOMER_ID_1)));
    actions.andExpect(jsonPath("$.firstName", is(testFirstName)));
    actions.andExpect(jsonPath("$.lastName", is(testLastName)));
  }

  @Test
  public void storeCustomer() throws Exception {
    Customer customer = customer().id(TestConstants.CUSTOMER_ID_1).firstName("Test").lastName("User").buildCustomer();

    when(customerService.save(customer)).thenReturn(1);

    ResultActions actions = mockMvc.perform(
            postJson("/customer", jsonMapper.writeValueAsString(customer))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));

    actions.andExpect(status().is2xxSuccessful());
  }

  @Test
  public void storeCustomerExceptionThrownWhilePersisting() throws Exception {
    Customer customer = customer().id(TestConstants.CUSTOMER_ID_1).firstName("Test").lastName("User").buildCustomer();

    when(customerService.save(customer)).thenThrow(new OurException(OurException.Fault.GENERIC_SERVER_SIDE_ERROR));

    String jsonRequest = jsonMapper.writeValueAsString(customer);
    ResultActions actions = mockMvc.perform(
            postJson("/customer", jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));

    actions.andExpect(status().is5xxServerError());
    actions.andExpect(jsonPath("$.error.code", is("OS-016")));
    actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
    actions.andExpect(jsonPath("$.error.message", is("Request could not be completed because there is a problem with the service")));
  }

}
