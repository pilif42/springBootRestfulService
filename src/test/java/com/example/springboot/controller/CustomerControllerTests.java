package com.example.springboot.controller;

import com.example.springboot.domain.Customer;
import com.example.springboot.service.CustomerService;
import com.example.springboot.utilities.CustomerBuilder;
import com.example.springboot.utilities.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.example.springboot.utilities.CustomerBuilder.customer;
import static com.example.springboot.utilities.MockMvcControllerAdviceHelper.mockAdviceFor;
import static com.example.springboot.utilities.MvcHelper.getJson;

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
    public void defaultCustomerId() throws Exception {
        when(customerService.getCustomer(TestConstants.EXISTING_CUSTOMER)).thenReturn(customer().id("1").firstName("Ken").lastName("Smith").build());

        ResultActions actions = mockMvc.perform(getJson("/customer"));

        // TODO validate results
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.first_name", is("Ken")))
//                .andExpect(jsonPath("$.last_name", is("Smith")))
//                );
    }


}
