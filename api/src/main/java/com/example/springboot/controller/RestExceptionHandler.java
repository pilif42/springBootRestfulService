package com.example.springboot.controller;

import com.example.springboot.error.InvalidRequestException;
import com.example.springboot.error.OurException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(OurException.class)
    public ResponseEntity<?> handleError(OurException ourException) {
        return new ResponseEntity<>(
            ourException,
            ourException.getFault().getHttpStatus()
        );
    }

    @ResponseBody
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleMessageNotReadable(InvalidRequestException ex, Locale locale) {
        StringBuilder logMsg = new StringBuilder(ex.getSourceMessage());

        StringBuilder responseMsg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getErrors().getFieldErrors();
        for (Iterator<FieldError> errorsIte = fieldErrors.listIterator(); errorsIte.hasNext(); ) {
            FieldError fieldError = errorsIte.next();
            responseMsg.append(fieldError.getDefaultMessage());
            if (errorsIte.hasNext()) {
                responseMsg.append(",");
            }
        }

        log.error("logMsg is '{}' - responseMsg is '{}'", logMsg.toString(), responseMsg.toString());
        OurException ourException = new OurException(OurException.Fault.BAD_REQUEST);
        return new ResponseEntity<>(
            ourException,
            ourException.getFault().getHttpStatus()
        );
    }
}
