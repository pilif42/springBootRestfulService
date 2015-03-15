package com.example.springboot.repository;

import com.example.springboot.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCCustomerRepository extends JDBCBaseRepository implements CustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(JDBCCustomerRepository.class);

    @Override
    public Customer getCustomer(String customerId) {
        logger.debug("entering getCustomer...");
        String sqlQuery = "SELECT CUSTOMER.ID AS \"id\"" +
                ",CUSTOMER.FIRSTNAME AS \"firstName\"" +
                ",CUSTOMER.LASTNAME AS \"lastName\"" +
                " FROM teststore.CUSTOMER" +
                " WHERE CUSTOMER.ID = ?";

        Customer result = null;
        try {
            result = jdbcTemplate.queryForObject(sqlQuery, new Object[]{customerId}, new CustomerMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("customer not found");
        }
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
