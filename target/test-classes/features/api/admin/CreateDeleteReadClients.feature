Feature: API - Create, Delete and Read Clients

  Scenario: API - Clients - Gets the clients
    Given Market Admin user log into Admin to get OktaToken
    When User requests for Gets the clients
    Then clients are compared into database

  Scenario: API - Clients - Creates and Delete a lead for the client
    Given Market Admin user log into Admin to get OktaToken
    When User requests for Gets the clients
    Then clients are compared into database
    When User requests for Creates a lead for the clients
      | userId                        |
      | Subhani.Shaik@dentsuaegis.com |
    Then lead "Subhani.Shaik@dentsuaegis.com" is created for "GB" client into database
    When User requests for Deletes a lead client "Subhani.Shaik@dentsuaegis.com" for "GB" country
    Then lead client "Subhani.Shaik@dentsuaegis.com" is deleted for "GB" country into database

  Scenario: API - Clients - Creates and Delete a planner for the client
    When Client Admin user log into Admin to get OktaToken
    And User requests for planner a lead for the clients
      | userId                        |
      | Subhani.Shaik@dentsuaegis.com |
    Then planner "Subhani.Shaik@dentsuaegis.com" is created into database
    When User requests for Deletes a planner to the  client "Subhani.Shaik@dentsuaegis.com"
    Then planner "Subhani.Shaik@dentsuaegis.com" is deleted into database

