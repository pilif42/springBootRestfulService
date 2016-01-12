package com.example.springboot.controller;

import com.example.springboot.domain.Product;
import com.example.springboot.error.OurException;
import com.example.springboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

//    @RequestMapping("/product/create/subscription")
//    @ResponseBody
//    public String createSubscriptionProduct(String email, String firstName) {
//        try {
//            Person person = new Person();
//            person.setEmail(email);
//            person.setFirstName(firstName);
//            personRepository.save(person);
//        }
//        catch (Exception ex) {
//            return "Error creating the person: " + ex.toString();
//        }
//        return "Person succesfully created!";
//    }
}
