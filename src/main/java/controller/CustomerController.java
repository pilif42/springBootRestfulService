package controller;

import domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/customer", method= RequestMethod.GET)
    public Customer customer(@RequestParam(value="name", defaultValue="Joe") String name) {
        logger.debug("debug: entering customer...");
        return new Customer(counter.incrementAndGet(), name, "Zidane");
    }

}
