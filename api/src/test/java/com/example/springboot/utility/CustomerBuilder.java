package com.example.springboot.utility;

import com.example.springboot.domain.Customer;
import com.example.springboot.domain.NonSubscriberCustomer;

public class CustomerBuilder {

    private Integer id;
    private String firstName;
    private String lastName;

    private CustomerBuilder(){}

    public CustomerBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Customer buildNonSubscriberCustomer() {
        NonSubscriberCustomer customer = new NonSubscriberCustomer();
        customer.setId(this.id);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        customer.setVoucherCode("INIT2016");
        customer.setCreatedBy("theCreator");
        customer.setCreated(100L);
        customer.setModifiedBy("theCreator");
        customer.setModified(100L);
        return customer;
    }

    public static CustomerBuilder customer() {
        return new CustomerBuilder();
    }
}
