Feature: Validating healthchecks

#############################################################################
# Section using the default healthcheck at http://localhost:8089/healthcheck
#############################################################################
  Scenario: Get request to the default healthcheck
    When I make the GET call to the default healthcheck
    Then the response status should be 200
    And the response should contain the field "timestamp" with value "AS-AI-100"
    And the response should contain the field "host" with value "localhost:8089"
    And the response should contain the field "message" with value "Ok"
    And the response should contain the field "sha" with value "1acdc4b881af30eb0150638c73be9ef5440d1a53"
