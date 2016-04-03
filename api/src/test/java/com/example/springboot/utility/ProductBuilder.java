package com.example.springboot.utility;

import com.example.springboot.domain.NonSubscriptionProduct;
import com.example.springboot.domain.SubscriptionProduct;

public class ProductBuilder {

  private Integer id;

  private ProductBuilder() {
  }

  public static ProductBuilder product() {
    return new ProductBuilder();
  }

  public ProductBuilder id(Integer id) {
    this.id = id;
    return this;
  }

  public NonSubscriptionProduct buildNonSubscriptionProduct() {
    NonSubscriptionProduct product = new NonSubscriptionProduct();
    product.setId(this.id);
    product.setName(TestConstants.NON_SUB_PRODUCT_NAME_1);
    product.setCreatedBy("theCreator");
    product.setCreated(100L);
    product.setModifiedBy("theCreator");
    product.setModified(100L);
    product.setMapCode(TestConstants.NON_SUB_PRODUCT_MAP_CODE_1);
    product.setVersion(1);
    return product;
  }

  public SubscriptionProduct buildSubscriptionProduct() {
    SubscriptionProduct product = new SubscriptionProduct();
    product.setId(this.id);
    product.setName(TestConstants.SUB_PRODUCT_NAME_1);
    product.setCreatedBy("theCreator");
    product.setCreated(100L);
    product.setModifiedBy("theCreator");
    product.setModified(100L);
    product.setStartDate(TestConstants.SUB_PRODUCT_START_DATE_1);
    product.setEndDate(TestConstants.SUB_PRODUCT_END_DATE_1);
    product.setVersion(1);
    return product;
  }
}

