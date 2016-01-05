package com.example.springboot.service;

import com.example.springboot.domain.UserPreferencesResponse;
import org.springframework.stereotype.Service;

@Service
public interface ElasticSearchService {
    void storeSomeJsonDocument(String userId, String jsonDocument);
    UserPreferencesResponse getPreferences(String userId);
}
