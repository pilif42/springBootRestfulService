package com.example.springboot.controller;

import com.example.springboot.error.OurException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class GrabJsonAsIsController {

    @ResponseBody
    @RequestMapping(value = "/takejsonin", method = POST, consumes = "application/json")
    public ResponseEntity<?> takeJsonIn(@RequestHeader(value = "X-An-Id", required = false) String userId, HttpServletRequest request) {
        log.debug("takeJsonIn with userId = {}", userId);

        try {
            String jsonBody = IOUtils.toString(request.getInputStream());
            log.debug("jsonBody received is {}", jsonBody);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.error("Failed to parse incoming request: " + e.getMessage(), e);
            throw new OurException(OurException.Fault.ERROR_PARSING_RECEIPT_REQUEST);
        }
    }
}
