package com.example.springboot.controller;

import com.example.springboot.error.OurException;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(OurException.class)
    public OurException handleError(OurException ex) {
        return ex;
    }

}
