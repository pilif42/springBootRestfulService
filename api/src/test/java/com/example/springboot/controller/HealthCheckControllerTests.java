package com.example.springboot.controller;

import com.example.springboot.BuildInfo;
import com.example.springboot.repository.HealthDAO;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static com.example.springboot.utility.MockMvcControllerAdviceHelper.mockAdviceFor;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HealthCheckControllerTests {

    private static final BuildInfo BUILD_INFO = new BuildInfo("AAAAAAAAAA", "DATE", "GROUP", "NAME", "VERSION");

    @Mock
    private HealthDAO healthDAO;

    @InjectMocks
    private HealthCheckController healthCheckController = new HealthCheckController(BUILD_INFO);

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(healthCheckController)
            .setHandlerExceptionResolvers(mockAdviceFor(RestExceptionHandler.class))
            .build();
    }

    @Test
    public void test_healthcheck() throws Exception {
        Mockito.doNothing().when(healthDAO).checkDB();

        String myhost = "localhost:8089";
        Long start = System.currentTimeMillis();

        mockMvc.perform(MockMvcRequestBuilders.get("/healthcheck").accept(MediaType.APPLICATION_JSON).header("host", myhost))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.host", is(myhost)))
            .andExpect(jsonPath("$.message", is("OK")))
            .andExpect(jsonPath("$.sha", is("AAAAAAAAAA")))
            .andExpect(jsonPath("$.timestamp", is(new CustomMatcher<Long>("Timestamp matcher") {
                @Override
                public boolean matches(Object item) {
                    return item instanceof Long && ((Long)item) > start;
                }
            })))
        ;
    }
}
