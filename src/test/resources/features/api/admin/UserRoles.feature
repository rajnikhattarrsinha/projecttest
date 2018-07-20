Feature: API - Retrieve user-roles information

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - User Roles - Returns the list of user roles
    When User requests to Get all user roles
    Then user roles are retrieved into response

  Scenario: API - User Roles - Returns the list of user roles matching with role level is global
    When User requests to Get all user roles matching with role level is global
    Then user roles are retrieved matching with role level is global into response

  Scenario: API - User Roles - Returns the list of user roles matching with role level is market
    When User requests to Get all user roles matching with role level is market
    Then user roles are retrieved matching with role level is market into response

  Scenario: API - User Roles - Returns the list of user roles matching with target Id is US
    When User requests to Get all user roles matching with target Id is US
    Then user roles are retrieved matching with target Id is US into response

  Scenario: API - User Roles - Returns the list of user roles matching with user Id is Global Admin
    When User requests to Get all user roles matching with user Id is Global Admin
    Then user roles are retrieved matching with user Id is Global Admin into response

  Scenario: API - User Roles - Returns the list of user roles matching with application Id is tv-stack
    When User requests to Get all user roles matching with application Id is tv-stack
    Then user roles are retrieved matching with application Id is tv-stack into response

  Scenario: API - User Roles - Returns the user role matching the correspondent unique identifier
    When User requests to Get all user roles matching with unique identifier
    Then user roles are retrieved matching with unique identifier into response

#  Scenario: API - User Roles - Create and Deletes the user role
#    When User requests to Create and Deletes role to the user
#    Then User role is Deleted to the user into response