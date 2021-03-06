package com.example.springboot.utility;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MvcHelper {

  public static MockHttpServletRequestBuilder getJson(String url) {
    return get(url).accept(MediaType.APPLICATION_JSON);
  }

  public static MockHttpServletRequestBuilder postJson(String url, String content) {
    return post(url).content(content)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
  }

}
