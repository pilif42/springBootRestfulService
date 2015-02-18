package com.example.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCBaseRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

}
