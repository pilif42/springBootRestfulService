package com.example.springboot.controller;

import com.example.springboot.domain.Customer;
import com.example.springboot.error.OurException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.service.CustomerService;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/customer", method= RequestMethod.GET)
    public Customer findCustomer(@RequestParam(value="id") String id, @RequestParam(value="lastName") String lastName) throws OurException
    {
        // TODO Validate params
        log.debug("debug: entering customer with id = {} - lastName = {}", id, lastName);

        Customer result = null;
        if (id != null) {
            result = customerService.findById(id);
        }
        if (lastName != null) {
            // TODO we should return all not just the 1st one
            result = customerService.findByLastName(lastName).get(0);
        }

        if (result == null) {
            throw new OurException(OurException.Fault.CUSTOMER_NOT_FOUND);
        }
        return result;
    }

}
