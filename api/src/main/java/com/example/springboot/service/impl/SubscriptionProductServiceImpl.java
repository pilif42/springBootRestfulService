package com.example.springboot.service.impl;

import com.example.springboot.domain.SubscriptionProduct;
import com.example.springboot.repository.SubscriptionProductRepository;
import com.example.springboot.service.SubscriptionProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionProductServiceImpl implements SubscriptionProductService {

  @Autowired
  private SubscriptionProductRepository subscriptionProductRepository;

  @Override
  public int save(SubscriptionProduct aSubscription) {
    //return subscriptionProductRepository.save(aSubscription);
    return 0;
  }
}
