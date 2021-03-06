package com.example.springboot.controller;

import com.example.springboot.domain.Customer;
import com.example.springboot.error.InvalidRequestException;
import com.example.springboot.error.OurException;
import com.example.springboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @RequestMapping(value = "/customer", method = RequestMethod.GET)
  public Customer findCustomer(@RequestParam(value = "id") Integer id) throws OurException {
    // TODO Validate params

    log.debug("debug: entering find customer with id = {}", id);

    Customer result = null;
    if (id != null) {
      result = customerService.findById(id);
    }

    if (result == null) {
      throw new OurException(OurException.Fault.CUSTOMER_NOT_FOUND);
    }
    return result;
  }

  @ResponseBody
  @RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<?> createNonSubscriberCustomer(@RequestBody @Valid Customer customer, BindingResult bindingResult) {
    log.debug("Processing customer creation for customer with firstName '{}'.", customer.getFirstName());

    if (bindingResult.hasErrors()) {
      throw new InvalidRequestException("Binding errors for customer creation: ", bindingResult);
    }

    Integer customerId = customerService.save(customer);
    log.debug("Just created customer with id {}", customerId);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
