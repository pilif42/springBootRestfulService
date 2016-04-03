package com.example.springboot.controller;

import com.example.springboot.BuildInfo;
import com.example.springboot.repository.HealthDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/*
 * Provides a simple interface for internal clients to healthcheck the service.
 * This will hit the database.
 */
@Controller
@Slf4j
public class HealthCheckController {

  @Autowired
  private HealthDAO healthDAO;

  private BuildInfo buildInfo;

  @Autowired
  public HealthCheckController(BuildInfo buildInfo) {
    this.buildInfo = buildInfo;
  }

  @RequestMapping(value = "/healthcheck", method = GET, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> handleHealthcheck(@RequestHeader("host") String host) {
    try {
      log.debug("Entering handleHealthcheck...");
      healthDAO.checkDB();
      return new ResponseEntity<>(new HealthcheckResponse(host, "OK"), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new HealthcheckResponse(host, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public class HealthcheckResponse {
    public final long timestamp;
    public final String host;
    public final String message;
    public final String sha;

    public HealthcheckResponse(String host, String message) {
      this.host = host;
      this.message = message;
      this.timestamp = System.currentTimeMillis();
      this.sha = buildInfo.getSha();
    }
  }
}
