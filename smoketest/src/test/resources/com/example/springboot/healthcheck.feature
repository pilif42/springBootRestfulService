Feature: Validating healthchecks

#############################################################################
# Section using the default healthcheck at http://localhost:8089/healthcheck
#############################################################################
  Scenario: Get request to the default healthcheck with NO authentication
    When I make the GET call to the default healthcheck
    Then the response status should be 200
    And the response should contain the field "timestamp" with a long value
    And the response should contain the field "host" with value "localhost:8089"
    And the response should contain the field "message" with value "OK"
    And the response should contain the field "sha" with a String value
