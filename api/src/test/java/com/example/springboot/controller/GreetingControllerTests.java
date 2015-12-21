package com.example.springboot.controller;

import com.example.springboot.utility.TestConstants;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.io.InputStream;

import static com.example.springboot.utility.MatchesPattern.isADate;

import static com.example.springboot.utility.MockMvcControllerAdviceHelper.mockAdviceFor;
import static com.example.springboot.utility.MvcHelper.getJson;
import static com.example.springboot.utility.MvcHelper.postJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertNotNull;
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
        actions.andExpect(handler().handlerType(GreetingController.class));
        actions.andExpect(handler().methodName("getAGreeting"));
        actions.andExpect(jsonPath("$.id", isA(Integer.class)));
        actions.andExpect(jsonPath("$.content", is(String.format(template, TestConstants.CUSTOMER_NAME))));
    }

    @Test
    public void validateInvalidGplayReceipt() throws Exception {
        ResultActions actions = mockMvc.perform(postJson("/greeting", getInvalidGplayReceipt()));

        actions.andExpect(handler().handlerType(GreetingController.class));
        actions.andExpect(handler().methodName("validateGoogleReceipts"));
        actions.andExpect(status().is5xxServerError());
        actions.andExpect(jsonPath("$.error.code", is("AS-GO-100")));
        actions.andExpect(jsonPath("$.error.timestamp", isA(Long.class)));
        actions.andExpect(jsonPath("$.error.message", is("Failed to parse receipt request")));
    }

    @Test
    public void validateValidGplayReceipt() throws Exception {
        ResultActions actions = mockMvc.perform(postJson("/greeting", getValidGplayReceipt()));

        actions.andExpect(handler().handlerType(GreetingController.class));
        actions.andExpect(handler().methodName("validateGoogleReceipts"));
        actions.andExpect(status().is2xxSuccessful());
        actions.andExpect(jsonPath("$.product_id", is("infinite_gas")));
        actions.andExpect(jsonPath("$.start_date", isADate()));
    }

    private String getValidGplayReceipt() throws IOException {
        InputStream is = getClass().getResourceAsStream("/gplayReceipts/live_valid_sub_receipt.json");
        assertNotNull(is);
        return IOUtils.toString(is);
    }

    private String getInvalidGplayReceipt() throws IOException {
        InputStream is = getClass().getResourceAsStream("/gplayReceipts/live_invalid_receipt.json");
        assertNotNull(is);
        return IOUtils.toString(is);
    }
}
