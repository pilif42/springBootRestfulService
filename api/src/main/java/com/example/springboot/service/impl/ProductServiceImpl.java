package com.example.springboot.service.impl;

import com.example.springboot.domain.NonSubscriptionProduct;
import com.example.springboot.domain.Product;
import com.example.springboot.domain.SubscriptionProduct;
import com.example.springboot.repository.NonSubscriptionProductRepository;
import com.example.springboot.repository.ProductRepository;
import com.example.springboot.repository.SubscriptionProductRepository;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubscriptionProductRepository subscriptionProductRepository;

    @Autowired
    private NonSubscriptionProductRepository nonSubscriptionProductRepository;

    @Override
    public Product findById(int prodId) {
        return productRepository.findById(prodId);
    }

    @Override
    public int saveSubscriptionProduct(SubscriptionProduct aSubscriptionProduct) {
        SubscriptionProduct subscriptionProduct = subscriptionProductRepository.save(aSubscriptionProduct);
        return subscriptionProduct.getId();
    }

    @Override
    public int saveNonSubscriptionProduct(NonSubscriptionProduct aNonSubscriptionProduct) {
        NonSubscriptionProduct nonSubscriptionProduct = nonSubscriptionProductRepository.save(aNonSubscriptionProduct);
        return nonSubscriptionProduct.getId();
    }

}
