package com.example.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER", schema = "testschema")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Customer {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_customerid_seq_gen")
  @SequenceGenerator(name = "my_customerid_seq_gen", sequenceName = "testschema.CUSTOMERID_SEQ_GEN")
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
