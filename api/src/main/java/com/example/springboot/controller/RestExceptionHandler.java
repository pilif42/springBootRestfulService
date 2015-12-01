package com.example.springboot.controller;

import com.example.springboot.error.OurException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OurException.class)
    public OurException handleError(OurException ex) {
        return ex;
    }

}
