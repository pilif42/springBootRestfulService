package com.example.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER", schema = "testschema")
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class Customer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_customerid_seq_gen")
    @SequenceGenerator(name="my_customerid_seq_gen", sequenceName="testschema.CUSTOMERID_SEQ_GEN")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;

    public Customer() {
    }

    public Customer(Integer id, String firstName, String lastName) {
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
