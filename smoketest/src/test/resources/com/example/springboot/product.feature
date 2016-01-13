Feature: Validating product requests
  Scenario: Get request for a non existing Product ID
    When I make the GET call to the product controller with id 0
    Then the response status should be 404
    And the response should contain the field "error.code" with value "UA-GP-102"
    And the response should contain the field "error.message" with value "Product details not found"
    And the response should contain the field "error.timestamp" with a long value

