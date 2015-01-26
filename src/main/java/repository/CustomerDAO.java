package repository;

import domain.Customer;

public interface CustomerDAO {

    Customer getCustomer(int customerId);
    boolean createCustomer(Customer aCustomer);

}
