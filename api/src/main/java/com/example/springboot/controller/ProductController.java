package com.example.springboot.controller;

import com.example.springboot.domain.NonSubscriptionProduct;
import com.example.springboot.domain.Product;
import com.example.springboot.domain.SubscriptionProduct;
import com.example.springboot.error.InvalidRequestException;
import com.example.springboot.error.OurException;
import com.example.springboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/product", method= RequestMethod.GET)
    public Product findProduct(@RequestParam(value="id") Integer id) throws OurException
    {
        // TODO Validate params

        log.debug("debug: entering find product with id = {}", id);

        Product result = null;
        if (id != null) {
            result = productService.findById(id);
        }

        if (result == null) {
            throw new OurException(OurException.Fault.PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/product/create/subscription", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createSubscriptionProduct(@RequestBody @Valid SubscriptionProduct subscriptionProduct, BindingResult bindingResult) {
        log.debug("Processing product creation for subscriptionProduct with name '{}'.", subscriptionProduct.getName());

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Binding errors for subscriptionProduct creation: ", bindingResult);
        }

        Integer productId = productService.saveSubscriptionProduct(subscriptionProduct);
        log.debug("Just created subscriptionProduct with id {}", productId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value="/product/create/nonsubscription", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createNonSubscriptionProduct(@RequestBody @Valid NonSubscriptionProduct nonSubscriptionProduct, BindingResult bindingResult) {
        log.debug("Processing product creation for nonSubscriptionProduct with name '{}'.", nonSubscriptionProduct.getName());

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Binding errors for nonSubscriptionProduct creation: ", bindingResult);
        }

        Integer productId = productService.saveNonSubscriptionProduct(nonSubscriptionProduct);
        log.debug("Just created subscriptionProduct with id {}", productId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
