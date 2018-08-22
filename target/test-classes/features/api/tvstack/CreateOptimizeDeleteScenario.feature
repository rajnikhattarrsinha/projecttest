Feature: API - Create, Optimize and Delete scenario

  Background:
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name         | clientId | budget | startDate    | endDate      |
      | TvStack_Plan | client   | 10000  | CURRENT_DATE | CURRENT_DATE |
    Then New plan is created into DB
    When User requests for Creates a new audience
      | name         | filter               | formattedFilter                | filterMap              |
      | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then audience is created to the plan into DB

  Scenario: API - Scenario - Creates a new scenario
    When User requests for Creates a new scenario
      | name                | audienceId |
      | TvStack_NewScenario | audience   |
    Then scenario is created to the plan into DB

  Scenario: API - Scenario - Optimize a existing scenario
    When User requests for Creates a new scenario
      | name                     | audienceId |
      | TvStack_OptimizeScenario | audience   |
    Then scenario is created to the plan into DB
    When User requests for Optimize a existing scenario
      | id | budget |
      | TV | 5000   |
   Then Scenario is Optimized to the plan

  Scenario: API - Scenario - Delete a existing scenario
    When User requests for Creates a new scenario
      | name                   | audienceId |
      | TvStack_CreateScenario | audience   |
    Then scenario is created to the plan into DB
    When User requests for Delete a existing scenario
    Then scenario is deleted to the plan into DB

  Scenario: API - Scenario - Update an existing scenario
    When User requests for Creates a new scenario
      | name                | audienceId |
      | TvStack_NewScenario | audience   |
    Then scenario is created to the plan into DB
    When User requests for Updates a existing scenario
      | name                   | audienceId |
      | TvStack_UpdateScenario | audience   |
    Then scenario is updated to the plan into DB

  Scenario: API - Scenario - Compare scenarios for an audience
    When User requests for Creates a new scenario
      | name                | audienceId |
      | TvStack_NewScenario | audience   |
    Then scenario is created to the plan into DB
    When User requests for Creates a new scenario
      | name                | audienceId |
      | TvStack_CompareScenario | audience   |
    Then scenario is created to the plan into DB
    When User requests for Compare scenarios for an audience
    Then Scenarios are compared into response
