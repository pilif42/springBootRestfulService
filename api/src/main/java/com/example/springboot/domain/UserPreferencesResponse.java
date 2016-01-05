package com.example.springboot.domain;

import lombok.Data;

@Data
public class UserPreferencesResponse {
    private long version;
    private String source;
}
