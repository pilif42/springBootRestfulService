package com.example.springboot.controller;

import com.example.springboot.utility.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.springboot.utility.MockMvcControllerAdviceHelper.mockAdviceFor;
import static com.example.springboot.utility.MvcHelper.getJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GreetingControllerTests {

    private static final String template = "Hello, %s!";

    @InjectMocks
    private GreetingController greetingController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(greetingController)
            .setHandlerExceptionResolvers(mockAdviceFor(RestExceptionHandler.class))
            .build();
    }

    @Test
    public void getAGreeting() throws Exception {
        ResultActions actions = mockMvc.perform(getJson("/greeting").param("name", TestConstants.CUSTOMER_NAME));

        actions.andExpect(status().isOk());
        actions.andExpect(handler().methodName("getAGreeting"));
        actions.andExpect(handler().handlerType(GreetingController.class));
        actions.andExpect(jsonPath("$.id", isA(Integer.class)));
        actions.andExpect(jsonPath("$.content", is(String.format(template, TestConstants.CUSTOMER_NAME))));
    }
}
