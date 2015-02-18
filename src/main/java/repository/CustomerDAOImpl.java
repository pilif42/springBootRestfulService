package repository;

import domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustomerDAOImpl extends JdbcDaoSupport implements CustomerDAO {

    private static final Logger logger = LogManager.getLogger(CustomerDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer getCustomer(String customerId) {
        logger.debug("entering getCustomer...");
        String sqlQuery = "SELECT CUSTOMER.ID AS 'id'" +
                ",CUSTOMER.FIRSTNAME AS 'firstName'" +
                ",CUSTOMER.LASTNAME AS 'lastName'" +
                " FROM CUSTOMER" +
                " WHERE CUSTOMER.ID = ?";
        Customer result = jdbcTemplate.queryForObject(sqlQuery, new Object[]{customerId}, new CustomerMapper());
        return result;
    }

    @Override
    public boolean createCustomer(Customer aCustomer) {
        logger.debug("entering createCustomer...");
        return false;
    }

    private static class CustomerMapper implements ParameterizedRowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getString("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            return customer;
        }
    }
}
