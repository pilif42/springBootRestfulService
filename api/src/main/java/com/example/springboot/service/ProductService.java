package com.example.springboot.service;

import com.example.springboot.domain.NonSubscriptionProduct;
import com.example.springboot.domain.Product;
import com.example.springboot.domain.SubscriptionProduct;

public interface ProductService {
  Product findById(int prodId);

  int saveSubscriptionProduct(SubscriptionProduct aSubscriptionProduct);

  int saveNonSubscriptionProduct(NonSubscriptionProduct aNonSubscriptionProduct);
}
