package com.example.springboot.repository;

import com.example.springboot.domain.Product;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NonSubscriptionProductRepository extends ProductBaseRepository<Product> {
}

