package com.example.springboot.service.impl;

import com.example.springboot.domain.Product;
import com.example.springboot.repository.ProductRepository;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(int prodId) {
        return productRepository.findById(prodId);
    }
}
