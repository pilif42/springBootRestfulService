package com.example.springboot.controller;

import com.example.springboot.error.OurException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(OurException.class)
    public ResponseEntity<?> handleError(OurException ourException) {
        return new ResponseEntity<>(
            ourException,
            ourException.getFault().getHttpStatus()
        );
    }

}
