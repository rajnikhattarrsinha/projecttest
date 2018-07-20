Feature: API - Create, Update and Delete buying audience

  Background:
    Given Market Admin user log into Admin to get OktaToken

  Scenario: API - BuyingAudiences - Creates a new buying audience
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | TV        | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB

  Scenario: API - BuyingAudiences - Updates an existing buying audience
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | VOD       | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Updates an existing buying audience
      | name                      | filter                                                            | formattedFilter                                                                                                                                     | filterMap                           |
      | Watching_Listening_Cinema | ((field("1062") == 8 && field("12463") == 2 && (field("14108")))) | ((Watch TV [Every day] AND Listen to the radio (on a radio) [Once a month] AND (O5 Favourite type of film to watch at cinema [Romantic Comedies]))) | Watching_Listening_Cinema_filterMap |
    Then buying audience is updated into DB

  Scenario: API - BuyingAudiences - Deletes an existing buying audience
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | TV        | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Deletes an existing buying audience
    Then buying audience is Deleted into DB

  Scenario: API - BuyingAudiences - Duplicates an existing buying audience
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | VOD       | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Duplicates a buying audience
      | name                                 |
      | Demographics_DuplicateBuyingAudience |
    Then Duplicates buying audience is created into DB