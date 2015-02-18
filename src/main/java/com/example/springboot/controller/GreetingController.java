package com.example.springboot.controller;

import com.example.springboot.domain.Greeting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final Logger logger = LogManager.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("info: entering greeting...");
        logger.debug("debug: entering greeting...");
        logger.warn("warn: entering greeting...");
        logger.error("error: entering greeting...");
        logger.fatal("fatal: entering greeting...");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}