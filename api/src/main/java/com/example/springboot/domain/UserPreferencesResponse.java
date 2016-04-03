package com.example.springboot.domain;

import lombok.Data;

import java.util.Map;

@Data
public class UserPreferencesResponse {
  private long version;
  private Map<String, Object> source;
}
