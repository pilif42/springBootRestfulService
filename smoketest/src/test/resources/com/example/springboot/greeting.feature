Feature: Validating greeting requests

#############################################################################
# Section querying http://localhost:8089/greeting?name=Joe
#############################################################################
  Scenario: Get request to a given name
    When I make the GET call to the greeting controller with name "Joe"
    Then the response status should be 200
    And the response should contain the field "id" with an integer value
    And the response should contain the field "content" with value "Hello, Joe!"

#############################################################################
# Section validating an invalid GPlay receipt
#############################################################################
  Scenario: Validate a invalid GPlay receipt
    Given an invalid JSON Google receipt
    When I post the receipt to the Greeting endpoint
    Then the response status should be 500
    And the response should contain the field "error.code" with value "AS-GO-100"
    And the response should contain the field "error.message" with value "Failed to parse receipt request"

#############################################################################
# Section validating a valid GPlay receipt
#############################################################################
  @ignore
  Scenario: Validate a valid GPlay receipt
    Given a valid JSON Google receipt
    When I post the receipt to the Greeting endpoint
    Then the response status should be 200