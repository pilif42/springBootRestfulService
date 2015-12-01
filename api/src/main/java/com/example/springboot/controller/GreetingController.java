package com.example.springboot.controller;

import com.example.springboot.domain.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.info("info: entering greeting...");
        log.debug("debug: entering greeting...");
        log.warn("warn: entering greeting...");
        log.error("error: entering greeting...");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
