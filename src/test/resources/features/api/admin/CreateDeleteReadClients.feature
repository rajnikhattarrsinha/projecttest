Feature: API - Create, Delete and Read Clients

  Background:
    Given Market Admin user log into Admin to get OktaToken

  Scenario: API - Clients - Gets the clients by Global Admin
    When User requests for Gets the clients
    Then clients are compared into database

  Scenario: API - Clients - Creates and Delete a lead for the client by Global Admin
    When User requests for Gets the clients
    Then clients are compared into database
    When User requests for Creates a lead for the clients
      | userId                        |
      | Subhani.Shaik@dentsuaegis.com |
    Then lead "Subhani.Shaik@dentsuaegis.com" is created for "GB" client into database
    When User requests for Deletes a lead client "Subhani.Shaik@dentsuaegis.com" for "GB" country
    Then lead client "Subhani.Shaik@dentsuaegis.com" is deleted for "GB" country into database