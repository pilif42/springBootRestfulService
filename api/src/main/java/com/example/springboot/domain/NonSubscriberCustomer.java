package com.example.springboot.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NONSUBSCRIBER")
@Data
@EqualsAndHashCode(callSuper = true)
public class NonSubscriberCustomer extends Customer {

    @Column(name = "VOUCHER_CODE")
    private String voucherCode;
}
