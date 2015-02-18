package repository;

import domain.Customer;

public interface CustomerDAO {

    Customer getCustomer(String customerId);
    boolean createCustomer(Customer aCustomer);

}
