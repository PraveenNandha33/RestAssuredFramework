Feature:  Validating place features

  @AddPlace @Map
  Scenario Outline: Validate that the new place is added using AddPlaceAPI
    Given Prepare Add Place Payload with "<name>" "<address>" "<PhoneNumber>" details
    When User makes "POST" request with "AddPlaceAPI"
    Then The status code should be 200
    And Validate that "status" in response body is "OK"
    And Validate that "scope" in response body is "APP"
    And Verify if the place added has the name "<name>"
    Examples:
      | name    | address  | PhoneNumber   |
      | Praveen | Pollachi | 1234567819    |
      | Nanda  | SPPPP    | 1234653222    |

  @DeletePlace @Map
  Scenario Outline: Validate that delete place API is working
    Given Prepare Add Place Payload with "<name>" "<address>" "<PhoneNumber>" details
    When User makes "POST" request with "AddPlaceAPI"
    Then The status code should be 200
    Given Prepare Delete Place payload
    When User makes "POST" request with "DeletePlaceAPI"
    Then The status code should be 200
    And Validate that "status" in response body is "OK"
        Examples:
      | name    | address  | PhoneNumber   |
      | Praveen | Pollachi | 1234567819    |


