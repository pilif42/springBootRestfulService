package com.example.springboot.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SUBSCRIPTION")
@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionProduct extends Product {
    @Column(name = "START_DATE")
    private Long startDate;

    @Column(name = "END_DATE")
    private Long endDate;
}
