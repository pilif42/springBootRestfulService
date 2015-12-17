Feature: Validating greeting requests

#############################################################################
# Section querying http://localhost:8089/greeting?name=Joe
#############################################################################
  Scenario: Get request to a given name
    When I make the GET call to the greeting controller with name "Joe"
    Then the response status should be 200
    And the response should contain the field "id" with an integer value
    And the response should contain the field "content" with value "Hello, Joe!"
