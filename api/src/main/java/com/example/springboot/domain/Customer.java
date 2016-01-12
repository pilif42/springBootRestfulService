package com.example.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER", schema = "testschema")
@JsonIgnoreProperties(ignoreUnknown=true)
@DiscriminatorColumn(name = "CUSTOMER_TYPE")
@Data
public abstract class Customer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_customerid_seq_gen")
    @SequenceGenerator(name="my_customerid_seq_gen", sequenceName="testschema.CUSTOMERID_SEQ_GEN")
    private Integer id;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "CREATED")
    private Long created;
    @Column(name = "MODIFIED")
    private Long modified;

    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Override
    public String toString() {
        return String.format(
                "Customer[id='%s', firstName='%s', lastName='%s', version='%s', createdBy='%s', created='%s', modifiedBy='%s', modified='%s']",
                id, firstName, lastName, version, createdBy, created, modifiedBy, modified);
    }
}
