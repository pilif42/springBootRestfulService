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

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/customer", method= RequestMethod.GET)
    public Customer customer(@RequestParam(value="name", defaultValue="Joe") String name) {
        logger.debug("debug: entering customer...");

        // TODO Remove the hardcoding below
        Customer result = customerService.getCustomer("1");
        return result;
    }

}
