package com.example.springboot.repository.impl;

import com.example.springboot.repository.HealthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHealthDAO implements HealthDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void checkDB() {
        jdbcTemplate.queryForObject("select count(*) from teststore.CUSTOMER", Long.class);
    }
}
