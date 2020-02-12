Feature: To retrieve the published articles

  Scenario: retrieve the api version
    Given an api user
    When the client calls GET "/api/articles"
    Then the client receives status code of 200
    And the response contains "[]"
