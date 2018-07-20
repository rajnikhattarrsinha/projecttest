Feature: API - Create, Delete and Read formats

  Background:
    Given Market Admin user log into Admin to get OktaToken

  Scenario: API - Formats - Gets all formats
    When User requests for Gets the formats
    Then formats are compared into database

  Scenario: API - Formats - Creates a new format
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | TV        | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Creates a new format
      | channelId | buyingAudienceId | name       | seconds | cpm | grp | reach | maxGrp | maxReach | frequencyCap | precision |
      | TV        | buyingAudienceId | New_Format | 5       | 4   | 1   | 4     | 1      | 5        | 2            | 3         |
    Then New format is created into DB

  Scenario: API - Formats - Deletes an existing format
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | TV        | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Creates a new format
      | channelId | buyingAudienceId | name       | seconds | cpm | grp | reach | maxGrp | maxReach | frequencyCap | precision |
      | TV        | buyingAudienceId | New_Format | 5       | 4   | 1   | 1     | 4      | 5        | 2            | 3         |
    Then New format is created into DB
    When User requests for Deletes an existing format
    Then Fotmat is deleted from DB