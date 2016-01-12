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
@Table(name = "PRODUCT", schema = "testschema")
@JsonIgnoreProperties(ignoreUnknown=true)
@DiscriminatorColumn(name = "PRODUCT_TYPE")
@Data
public abstract class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_productid_seq_gen")
    @SequenceGenerator(name="my_productid_seq_gen", sequenceName="testschema.PRODUCTID_SEQ_GEN")
    private Integer id;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "CREATED")
    private Long created;
    @Column(name = "MODIFIED")
    private Long modified;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Override
    public String toString() {
        return String.format(
            "Customer[id='%s', name='%s', version='%s', createdBy='%s', created='%s', modifiedBy='%s', modified='%s']",
            id, name, version, createdBy, created, modifiedBy, modified);
    }
}
