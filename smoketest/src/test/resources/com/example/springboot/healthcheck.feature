Feature: Validating healthchecks

#############################################################################
# Section using the default healthcheck at http://localhost:8089/healthcheck
#############################################################################
  Scenario: Get request to the default healthcheck with NO authentication
    When I make the GET call to the default healthcheck
    Then the response status should be 401
    And the response should contain the field "timestamp" with a long value
    And the response should contain the field "error" with value "Unauthorized"
    And the response should contain the field "message" with value "Full authentication is required to access this resource"
    And the response should contain the field "path" with value "/healthcheck"

  Scenario: Get request to the default healthcheck with authentication
    Given valid basic authentication credentials are provided
    When I make the GET call to the default healthcheck
    Then the response status should be 200
    And the response should contain the field "timestamp" with a long value
    And the response should contain the field "host" with value "localhost:8089"
    And the response should contain the field "message" with value "OK"
    And the response should contain the field "sha" with a String value
