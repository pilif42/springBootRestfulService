package com.example.springboot.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NONSUBSCRIPTION")
@Data
@EqualsAndHashCode(callSuper = true)
public class NonSubscriptionProduct extends Product {

    @Column(name = "MAP_CODE")
    private String mapCode;

}
