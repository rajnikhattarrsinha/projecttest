Feature: API - Create, Update, Delete and Duplicate Plan

  Background:
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client

  Scenario: API - Plan - Create a plan
    When User requests for Create new plan
      | name         | clientId | budget | startDate    | endDate        |
      | TvStack_Test | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB

  Scenario Outline: API - Plan - Create a plan with invalid values
    When User requests for Create new plan with below values
      | name   | clientId   | budget   | startDate   | endDate   |
      | <name> | <clientId> | <budget> | <startDate> | <endDate> |
    Then plan request is throwing error '<errorMessage>'
    Examples:
      | name         | clientId | budget | startDate    | endDate        | errorMessage           |
      |              | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE | Empty project name.    |
      | TvStack_Test | client   |        | CURRENT_DATE | NEXTMONTH_DATE | Empty budget.          |
      | TvStack_Test | client   | 10000  |              | NEXTMONTH_DATE | Not a valid date input |
      | TvStack_Test | client   | 10000  | CURRENT_DATE |                | Not a valid date input |

  Scenario: API - Plan - Update a plan details
    When User requests for Create new plan
      | name            | clientId | budget | startDate    | endDate        |
      | TvStack_NewPlan | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update plan
      | id         | name               | budget | startDate    | endDate        |
      | project id | TvStack_UpdatePlan | 20000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then plan is updated into DB

  Scenario: API - Plan - Delete a plan
    When User requests for Create new plan
      | name         | clientId | budget | startDate    | endDate        |
      | TvStack_Test | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Delete plan
    Then plan is deleted from DB

  Scenario: API - Plan - Duplicates a plan
    When User requests for Create new plan
      | name               | clientId | budget | startDate    | endDate        |
      | TvStack_CreatePlan | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Duplicates a plan
      | name                  |
      | TvStack_DuplicatePlan |
    Then Duplicates plan is created into DB

  Scenario: API - Plan - Definitive flag a plan
    When User requests for Create new plan
      | name               | clientId | budget | startDate    | endDate        |
      | TvStack_Definitive | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Definitive a plan
    Then plan is Definitive into DB

