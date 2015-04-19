package com.example.springboot.controller;

import com.example.springboot.repository.HealthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/*
 * Provides a simple interface for internal clients to healthcheck the service.
 * This will hit the database.
 */
@Controller
public class HealthCheckController {

    @Autowired
    private HealthDAO healthDAO;

    @RequestMapping(value = "/healthcheck", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleHealthcheck() {
        try {
            healthDAO.checkDB();
            return new ResponseEntity<>(HealthcheckResponse.OK(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new HealthcheckResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class HealthcheckResponse {
        public final String message;
        public final long timestamp;

        public HealthcheckResponse(String message) {
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }

        public static HealthcheckResponse OK() {
            return new HealthcheckResponse("OK");
        }
    }
}
