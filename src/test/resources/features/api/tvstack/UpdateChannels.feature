Feature: API - Update existing channels

  Scenario: API - Channels - Update existing channels for TV
    Given Market Admin user "client.admin1@dentsuaegis.com" log into Admin to get OktaToken
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | TV        | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Creates a new format
      | channelId | buyingAudienceId | name      | seconds | cpm | grp | reach | maxGrp | maxReach | frequencyCap | precision |
      | TV        | buyingAudienceId | TV_Format | 5       | 4   | 1   | 1     | 4      | 3        | 2            | 3         |
    Then New format is created into DB
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name            | clientId | budget | startDate    | endDate        |
      | TV_Channel_Test | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "TV" to plan
    Then channels "TV" are updated to the plan

  Scenario: API - Channels - Update existing channels for OnlineVideo
    Given Market Admin user "client.admin1@dentsuaegis.com" log into Admin to get OktaToken
    When User requests for Create buying audience
      | channelId   | name         | filter               | formattedFilter                | filterMap              |
      | OnlineVideo | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Creates a new format
      | channelId   | buyingAudienceId | name               | seconds | cpm | grp | reach | maxGrp | maxReach | frequencyCap | precision |
      | OnlineVideo | buyingAudienceId | OnlineVideo_Format | 5       | 4   | 1   | 1     | 4      | 3        | 2            | 3         |
    Then New format is created into DB
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name                     | clientId | budget | startDate    | endDate        |
      | OnlineVideo_Channel_Test | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "OnlineVideo" to plan
    Then channels "OnlineVideo" are updated to the plan

  Scenario: API - Channels - Update existing channels for VOD
    Given Market Admin user "client.admin1@dentsuaegis.com" log into Admin to get OktaToken
    When User requests for Create buying audience
      | channelId | name         | filter               | formattedFilter                | filterMap              |
      | VOD       | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then New buying audience is created into DB
    When User requests for Creates a new format
      | channelId | buyingAudienceId | name       | seconds | cpm | grp | reach | maxGrp | maxReach | frequencyCap | precision |
      | VOD       | buyingAudienceId | VOD_Format | 5       | 4   | 1   | 1     | 4      | 3        | 2            | 3         |
    Then New format is created into DB
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name               | clientId | budget | startDate    | endDate        |
      | VOD_Channel_Test ﻿ | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "VOD" to plan
    Then channels "VOD" are updated to the plan

  Scenario: API - Channels - Update existing channels for Youtube
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name                   | clientId | budget | startDate    | endDate        |
      | Youtube_Channel_Test ﻿ | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "Youtube" to plan
    Then channels "Youtube" are updated to the plan

  Scenario: API - Channels - Update existing channels for Facebook
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name                    | clientId | budget | startDate    | endDate        |
      | Facebook_Channel_Test ﻿ | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "Facebook" to plan
    Then channels "Facebook" are updated to the plan

  Scenario: API - Channels - Update existing channels for Instagram
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name                      | clientId | budget | startDate    | endDate        |
      | ﻿Instagram_Channel_Test ﻿ | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB
    When User requests for Update existing channel "Instagram" to plan
    Then channels "Instagram" are updated to the plan