Feature: Validating product requests
  Scenario: Get request for a non existing Product ID
    When I make the GET call to the product controller with id 0
    Then the response status should be 404
    And the response should contain the field "error.code" with value "UA-GP-102"
    And the response should contain the field "error.message" with value "Product details not found"
    And the response should contain the field "error.timestamp" with a long value

  Scenario: Get request for a NON subscription product
    When I make the GET call to the product controller with id 1
    Then the response status should be 200
    And the response should contain the field "id" with an integer value of 1
    And the response should contain the field "version" with an integer value of 1
    And the response should contain the field "created" with a long value
    And the response should contain the field "modified" with a long value
    And the response should contain the field "name" with value "NON_SUB_1"
    And the response should contain the field "createdBy" with value "7c13f7e0-0c6d-7c4b-6327-ffde84545db0"
    And the response should contain the field "modifiedBy" with value "7c13f7e0-0c6d-7c4b-6327-ffde84545db0"
    And the response should contain the field "mapCode" with value "MAP_1"

  Scenario: Get request for a subscription product
    When I make the GET call to the product controller with id 2
    Then the response status should be 200
    And the response should contain the field "id" with an integer value of 2
    And the response should contain the field "version" with an integer value of 1
    And the response should contain the field "created" with a long value
    And the response should contain the field "modified" with a long value
    And the response should contain the field "name" with value "SUB_1"
    And the response should contain the field "createdBy" with value "7c13f7e0-0c6d-7c4b-6327-ffde84545db0"
    And the response should contain the field "modifiedBy" with value "7c13f7e0-0c6d-7c4b-6327-ffde84545db0"
    And the response should contain the field "startDate" with a long value
    And the response should contain the field "endDate" with a long value

  Scenario: Store a new NON subscription product
    Given the JSON parameter "firstName" with value "Lionel"
    And the JSON parameter "lastName" with value "Messi"
    When I make the REST call to the POST customer endpoint
    Then the response status should be 201