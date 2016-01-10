package com.example.springboot.utility;

import com.example.springboot.domain.Customer;

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

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        return customer;
    }

    public static CustomerBuilder customer() {
        return new CustomerBuilder();
    }
}
