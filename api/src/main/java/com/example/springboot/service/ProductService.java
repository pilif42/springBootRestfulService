package com.example.springboot.service;

import com.example.springboot.domain.Product;

public interface ProductService {
    Product findById(int prodId);
}
