Feature: API - Check TvStack Admin Version

  Scenario: API - Check TvStack Admin Version
    Given TvStack Admin is deployed
    When User request for get TvStack Admin Version
    And Admin version number is returned into response
