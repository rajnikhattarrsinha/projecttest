Feature: API - Retrieve Roles information

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - Roles - Returns all roles definitions including type and level(roles are application and target unaware)
    When User requests to Get all roles definitions
    Then Roles definitions including type and level are retrieved into response

  Scenario: API - Roles - Returns the role definition matching the given Global Admin id
    When User requests to Get all roles definitions
    And User requests to Get roles definitions for Global Admin
    Then Global Admin Role definition including type and level are retrieved into response