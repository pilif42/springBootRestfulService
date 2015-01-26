package service;

import domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(int customerId) {
        return null;
    }

    @Override
    public boolean createCustomer(Customer aCustomer) {
        return false;
    }
}
