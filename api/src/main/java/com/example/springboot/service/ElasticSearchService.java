package com.example.springboot.service;

import org.springframework.stereotype.Service;

@Service
public interface ElasticSearchService {
    void storeSomeJsonDocument(String userId, String jsonDocument);
}
