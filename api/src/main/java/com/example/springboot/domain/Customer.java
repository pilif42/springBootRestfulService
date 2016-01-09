package com.example.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER", schema = "testschema")
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class Customer {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id='%s', firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
