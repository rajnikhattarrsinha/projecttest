Feature: API - Retrieve user information

  Scenario: API - Users - Returns all users available in active directory (including the ones being unassigned to the application, up to a maximum of 100)
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get users available in active directory
    Then Users are retrieved into response

  Scenario: API - Users - Returns the query details from active directory
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get query for Users "Subhani" details
    Then Users details are retrieved into response

  Scenario: API - Users - Returns the query details when user is not in active directory
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get query for Users "abcdef123" details
    Then Users details are not retrieved into response

  Scenario: API - Users - Returns details about the user matching the given id, including related user roles
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get the User details for given id "Subhani.Shaik@dentsuaegis.com"
    Then Users matching details including user roles are returned into response

  Scenario: API - Users - Returns details about the Global admin logged in user, including related user roles
    Given Global Admin user log into Admin to get OktaToken
    When Users requests to Get details about the logged in user
    Then Users details about the Global admin logged in user are returned into response

  Scenario: API - Users - Returns details about the Market admin logged in user, including related user roles
    Given Market Admin user log into Admin to get OktaToken
    When Users requests to Get details about the logged in user
    Then Users details about the Market admin logged in user are returned into response

  Scenario: API - Users - Returns details about the Client admin logged in user, including related user roles
    Given Client Admin user log into Admin to get OktaToken
    When Users requests to Get details about the logged in user
    Then Users details about the Client admin logged in user are returned into response