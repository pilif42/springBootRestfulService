package com.example.springboot.controller;

import com.example.springboot.config.SecurityConfiguration;
import com.example.springboot.domain.Greeting;
import com.example.springboot.domain.Order;
import com.example.springboot.domain.ReceiptRequest;
import com.example.springboot.domain.SignedReceipt;
import com.example.springboot.error.OurException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
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

  @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
  public Greeting getAGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    log.info("info: entering greeting...");
    log.debug("debug: entering greeting...");
    log.warn("warn: entering greeting...");
    log.error("error: entering greeting...");
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  @Secured(SecurityConfiguration.INBOUND_SECURED_ENDPOINTS_ROLE)
  @RequestMapping(value = "/greeting", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> validateGoogleReceipts(@RequestBody ReceiptRequest receiptRequest) throws IOException {
    SignedReceipt signedReceipt = null;
    try {
      signedReceipt = mapper.readValue(receiptRequest.appstoreReceipt, SignedReceipt.class);
    } catch (JsonProcessingException e) {
      log.error("Failed to parse incoming Google receipt: {}", e.getMessage());
      throw new OurException(OurException.Fault.ERROR_PARSING_RECEIPT_REQUEST);
    }

    // TODO Use a service to validate the receipt
    log.debug("receipt = {}", signedReceipt);

    return new ResponseEntity<>(
            Order.builder()
                    .withStartDate(new Date(System.currentTimeMillis()))
                    .withOrderId(signedReceipt.getPurchaseData().getOrderId())
                    .withProductId(signedReceipt.getPurchaseData().getProductId())
                    .build(),
            HttpStatus.OK
    );
  }
}
