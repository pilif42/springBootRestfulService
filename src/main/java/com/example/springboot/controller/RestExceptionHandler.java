package com.example.springboot.controller;

import com.example.springboot.error.OurException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(OurException.class)
    public OurException handleError(OurException ex) {
        return ex;
    }

}
