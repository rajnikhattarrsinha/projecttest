Feature: API - Get Questions and Evaluates the filter for survey

  Background:
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name         | clientId | budget | startDate    | endDate      |
      | TvStack_Plan | client   | 10000  | CURRENT_DATE | CURRENT_DATE |
    Then New plan is created into DB

  Scenario: API - Survey - Gets the questions for a survey
    When Users requests questions for a survey
    Then questions are stored into DB

  Scenario: API - Survey - Evaluates the filter for a survey
    When Users requests for Evaluates the filter for a survey
      | filter               |
      | ((field("26") == 6)) |
    Then filter value '2991034' is returned for a audience into response

  Scenario: API - Survey - Evaluates the filter value for a survey when Reach is 0
    When Users requests for Evaluates the filter for a survey
      | filter                                       |
      | ((field("303") == 63 && field("1044") == 2)) |
    Then filter value '0' is returned for a audience into response