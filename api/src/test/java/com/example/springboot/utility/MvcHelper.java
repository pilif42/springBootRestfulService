package com.example.springboot.utility;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class MvcHelper {

    public static MockHttpServletRequestBuilder getJson(String url) {
        return get(url).accept(MediaType.APPLICATION_JSON);
    }
}
