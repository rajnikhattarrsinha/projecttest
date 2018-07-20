Feature: API - Compare Makets with Point logic

  Scenario: API - Compare Markets with Point logic
    Given Market Admin user log into Admin to get OktaToken
    When User request for markets
    And User query for Markets from database
    Then markets are stored into database
