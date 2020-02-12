Feature: To retrieve the api version

  Scenario: retrieve the api version
    Given an api user
    When the client calls GET "/api/version"
    Then the client receives status code of 200
    And the response contains version "1.4"
