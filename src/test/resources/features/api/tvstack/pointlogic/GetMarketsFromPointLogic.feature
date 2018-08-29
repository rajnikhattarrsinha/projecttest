Feature: API -  Get markets from point logic

  Scenario: API - Pointlogic - Get markets from point logic
    Given Market Admin user log into Admin to get OktaToken
    When User request for markets
    Then markets are retrieved into response
