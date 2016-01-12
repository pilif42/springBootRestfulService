package com.example.springboot.repository;

import com.example.springboot.domain.SubscriptionProduct;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SubscriptionProductRepository extends ProductBaseRepository<SubscriptionProduct> {
}
