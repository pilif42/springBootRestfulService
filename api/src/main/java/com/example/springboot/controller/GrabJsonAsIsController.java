package com.example.springboot.controller;

import com.example.springboot.domain.UserPreferencesResponse;
import com.example.springboot.error.OurException;
import com.example.springboot.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class GrabJsonAsIsController {

  @Autowired
  private ElasticSearchService elasticSearchService;

  @ResponseBody
  @RequestMapping(value = "/takejsonin", method = POST, consumes = "application/json")
  public ResponseEntity<?> takeJsonIn(@RequestHeader(value = "X-An-Id", required = false) String userId, HttpServletRequest request) {
    log.debug("takeJsonIn with userId = {}", userId);

    try {
      String jsonBody = IOUtils.toString(request.getInputStream());
      log.debug("jsonBody received is {}", jsonBody);

      elasticSearchService.storeSomeJsonDocument(userId, jsonBody);

      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IOException e) {
      log.error("Failed to parse incoming request: " + e.getMessage(), e);
      throw new OurException(OurException.Fault.ERROR_PARSING_RECEIPT_REQUEST);
    }
  }

  @ResponseBody
  @RequestMapping(value = "/takejsonin", method = GET, produces = "application/json")
  public UserPreferencesResponse getUserPreferences(@RequestHeader(value = "X-An-Id", required = false) String userId) {
    log.debug("Retrieving user preferences with userId = {}", userId);
    return elasticSearchService.getPreferences(userId);
  }
}
