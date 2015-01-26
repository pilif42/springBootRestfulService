package repository;

import domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer getCustomer(int customerId) {
        return null;
    }

    @Override
    public boolean createCustomer(Customer aCustomer) {
        return false;
    }
}
