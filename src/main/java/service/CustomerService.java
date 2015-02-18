package service;

import domain.Customer;

public interface CustomerService {

    Customer getCustomer(String customerId);
    boolean createCustomer(Customer aCustomer);

}
