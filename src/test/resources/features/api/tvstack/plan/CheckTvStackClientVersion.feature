Feature: API - Check TvStack Client Version

  Scenario: API - Check TvStack Client Version
    Given TvStack Client application is deployed
    When User request for get TvStack Client Version
    And Client version number is returned into response
