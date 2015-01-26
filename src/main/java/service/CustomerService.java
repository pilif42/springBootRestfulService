package service;

import domain.Customer;

public interface CustomerService {

    Customer getCustomer(int customerId);
    boolean createCustomer(Customer aCustomer);

}
