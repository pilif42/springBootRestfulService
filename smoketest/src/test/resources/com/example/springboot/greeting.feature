Feature: Validating greeting requests

#############################################################################
# Section querying http://localhost:8089/greeting?name=Joe
#############################################################################
  Scenario: Get request to a given name with NO authentication
    When I make the GET call to the greeting controller with name "Joe"
    Then the response status should be 200
    And the response should contain the field "id" with an integer value
    And the response should contain the field "content" with value "Hello, Joe!"

#############################################################################
# Section validating an invalid GPlay receipt
#############################################################################
  Scenario: Validate a invalid GPlay receipt with NO authentication
    Given an invalid JSON Google receipt
    When I post the receipt to the Greeting endpoint
    Then the response status should be 401
    And the response should contain the field "error" with value "Unauthorized"
    And the response should contain the field "message" with value "Full authentication is required to access this resource"
    And the response should contain the field "path" with value "/greeting"

  Scenario: Validate a invalid GPlay receipt with authentication
    Given an invalid JSON Google receipt
    Given valid basic authentication credentials are provided
    When I post the receipt to the Greeting endpoint
    Then the response status should be 500
    And the response should contain the field "error.code" with value "AS-GO-100"
    And the response should contain the field "error.message" with value "Failed to parse receipt request"

#############################################################################
# Section validating a valid GPlay receipt
#############################################################################
  Scenario: Validate a valid GPlay receipt with NO authentication
    Given a valid JSON Google receipt
    When I post the receipt to the Greeting endpoint
    Then the response status should be 401
    And the response should contain the field "error" with value "Unauthorized"
    And the response should contain the field "message" with value "Full authentication is required to access this resource"
    And the response should contain the field "path" with value "/greeting"

  Scenario: Validate a valid GPlay receipt with authentication
    Given a valid JSON Google receipt
    Given valid basic authentication credentials are provided
    When I post the receipt to the Greeting endpoint
    Then the response status should be 200
    And the response should contain the field "product_id" with value "infinite_gas"
    And the response should contain the field "start_date" with value matching the regex "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}"