Feature: Validating grabJsonAsIs requests
  Scenario: Get request with a valid X-An-Id
    Given A valid x-an-id
    When I make the GET call to the grabJsonAsIs controller
    Then the response status should be 200
    And the response should contain the field "source.initial_mapview" with value "gb"
    And the response should contain the field "source.anotherprop" with value "thevalue"
    And the response should contain the field "source.show_distance_in" with value "metric"

  Scenario: Post request with a valid X-An-Id
    Given the JSON parameter "initial_mapview" with value "gb"
    And the JSON parameter "anotherprop" with value "thevalue"
    And the JSON parameter "show_distance_in" with value "metric"
    And A valid x-an-id
    When I make the REST call to the POST grabJsonAsIs endpoint
    Then the response status should be 200
