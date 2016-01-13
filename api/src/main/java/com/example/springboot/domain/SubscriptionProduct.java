package com.example.springboot.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "SUBSCRIPTION")
@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionProduct extends Product {
    @Column(name = "START_DATE")
    @NotNull
    private Long startDate;

    @Column(name = "END_DATE")
    @NotNull
    private Long endDate;
}
