Feature: API - Update existing channels

  Background:
    Given Market Admin user "client.admin1@dentsuaegis.com" log into Admin to get OktaToken
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | TV        | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Creates a new format
      | channelId | buyingAudienceId | name       | seconds | cpm | grp | reach | maxGrp | maxReach | frequencyCap | precision |
      | TV        | buyingAudienceId | New_Format | 5       | 4   | 1   | 1     | 4      | 3        | 2            | 3         |
    Then New format is created into DB

  Scenario: API - Channels - Update existing channels
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name         | clientId | budget | startDate    | endDate      |
      | Channel_Test | client   | 10000  | CURRENT_DATE | CURRENT_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "TV" to plan
    Then channels are updated to the plan