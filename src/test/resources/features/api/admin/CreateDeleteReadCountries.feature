Feature: API - Create, Delete and Read countries

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - Countries - Gets the countries
    When User requests for Gets the countries
    Then countries are compared into database

  Scenario: API - Countries - Creates and Delete a lead for the country
    When User requests for Creates a lead for "IE" country
      | userId                        |
      | Subhani.Shaik@dentsuaegis.com |
    Then lead "Subhani.Shaik@dentsuaegis.com" is created for "IE" country into database
    When User requests for Deletes a lead "Subhani.Shaik@dentsuaegis.com" for "IE" country
    Then lead "Subhani.Shaik@dentsuaegis.com" is deleted for "IE" country into database