Feature: Validating customer requests
  Scenario: Get request for an existing Customer ID
    When I make the GET call to the customer controller with id 2
    Then the response status should be 200
    And the response should contain the field "id" with an integer value of 2
    And the response should contain the field "version" with an integer value of 1
    And the response should contain the field "firstName" with value "John"
    And the response should contain the field "lastName" with value "Doe"
    And the response should contain the field "createdBy" with value "9d13f7e0-0c6d-7c4b-6327-ffde84545db0"
    And the response should contain the field "modifiedBy" with value "9d13f7e0-0c6d-7c4b-6327-ffde84545db0"
    And the response should contain the field "created" with a long value
    And the response should contain the field "modified" with a long value

