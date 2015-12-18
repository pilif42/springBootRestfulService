package com.example.springboot.controller;

import com.example.springboot.domain.Greeting;
import com.example.springboot.domain.ReceiptRequest;
import com.example.springboot.domain.SignedReceipt;
import com.example.springboot.error.OurException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    private final ObjectMapper mapper;

    public GreetingController() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(SignedReceipt.class, new SignedReceipt.SignedReceiptDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        this.mapper = mapper;
    }

    @RequestMapping(value="/greeting", method=RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Greeting getAGreeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.info("info: entering greeting...");
        log.debug("debug: entering greeting...");
        log.warn("warn: entering greeting...");
        log.error("error: entering greeting...");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value="/greeting", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateGoogleReceipts(@RequestBody ReceiptRequest receiptRequest) throws IOException {
        SignedReceipt signedReceipt = null;
        try {
            signedReceipt = mapper.readValue(receiptRequest.appstoreReceipt, SignedReceipt.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse incoming Google receipt: {}", e.getMessage());
            throw new OurException(OurException.Fault.ERROR_PARSING_RECEIPT_REQUEST);
        }

        // TODO Do sth with the receipt
        log.debug("receipt = {}", signedReceipt);

        return new ResponseEntity<>(HttpStatus.OK);
        // TODO return new ResponseEntity<>(
//        Order.builder()
//            .withEndDate(new Date(purchase.getExpiryTimeMillis()))
//            .withStartDate(new Date(purchase.getStartTimeMillis()))
//            .withOrderId(receipt.getReceipt().getOrderId())
//            .withProductId(receipt.getReceipt().getProductId())
//            .build(),
//            HttpStatus.OK
//        );
    }
}
