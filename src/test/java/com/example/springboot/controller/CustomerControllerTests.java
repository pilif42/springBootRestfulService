package com.example.springboot.controller;

import com.example.springboot.service.CustomerService;
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
        String testID = "1";
        String testFirstName = "Ken";
        String testLastName = "Smith";
        when(customerService.findById(TestConstants.DEFAULT_CUSTOMER_ID)).thenReturn(customer().id(testID).firstName(testFirstName).lastName(testLastName).build());

        ResultActions actions = mockMvc.perform(getJson("/customer"));

        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.id", is(testID)));
        actions.andExpect(jsonPath("$.firstName", is(testFirstName)));
        actions.andExpect(jsonPath("$.lastName", is(testLastName)));
    }

}
