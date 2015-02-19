package com.example.springboot.controller;

import com.example.springboot.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.service.CustomerService;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/customer", method= RequestMethod.GET)
    public Customer customer(@RequestParam(value="id", defaultValue="1") String id) {
        logger.debug("debug: entering customer with id = " + id);
        Customer result = customerService.getCustomer(id);
        return result;
    }

}
