Feature: API - Create, Update and Delete Audiences


  Background:
    Given Planner user log into Client to get OktaToken
    And User selects Market and Client
    When User requests for Create new plan
      | name         | clientId | budget | startDate    | endDate        |
      | TvStack_Plan | client   | 10000  | CURRENT_DATE | NEXTMONTH_DATE |
    Then New plan is created into DB

  Scenario: API - Audiences - Creates a new audience
    When User requests for Creates a new audience
      | name         | filter               | formattedFilter                | filterMap              |
      | Demographics | ((field("26") == 6)) | ((Age group recode 1 [55-64])) | Demographics_filterMap |
    Then audience is created to the plan into DB

#  Scenario: API - Audiences - Creates a new audience when Reach is 0
#    When User requests for Creates a new audience
#      | name             | filter                                       | formattedFilter                                                                                        | filterMap                  |
#      | Employment_Media | ((field("303") == 63 && field("1044") == 2)) | ((Annual HOUSEHOLD income [$250,000 or more] AND Use the Internet (on any device) [Every 2-3 months])) | Employment_Media_filterMap |
#    Then audience service throws error

  Scenario: API - Audiences - Updates an existing audience
    When User requests for Creates a new audience
      | name                      | filter                                                           | formattedFilter                                                                                                                   | filterMap                           |
      | Employment_Media_Watching | ((field("278") && (field("1044") == 8 && (field("1448") == 1)))) | ((Work status [Work full time] AND (Use the Internet (on any device) [Every day] AND (Base for TV questions [Watch TV (BASE)])))) | Employment_Media_Watching_filterMap |
    Then audience is created to the plan into DB
    When User requests for Updates an existing audience
      | name                      | filter                                                            | formattedFilter                                                                                                                                     | filterMap                           |
      | Watching_Listening_Cinema | ((field("1062") == 8 && field("12463") == 2 && (field("14108")))) | ((Watch TV [Every day] AND Listen to the radio (on a radio) [Once a month] AND (O5 Favourite type of film to watch at cinema [Romantic Comedies]))) | Watching_Listening_Cinema_filterMap |
    Then audience is updated to the plan into DB

  Scenario: API - Audiences - Deletes an existing audience
    When User requests for Creates a new audience
      | name                          | filter                                                     | formattedFilter                                                                                                                                                    | filterMap                               |
      | Internet_Communicating_Mobile | ((field("1154") && field("5926") == 4 && (field("3417")))) | ((Use Internet by Mobile/Tablet [Accesses internet on a mobile phone/smartphone] AND Facebook [1-3 days a week] AND (T2 Type of mobile phone owned [Blackberry]))) | Internet_Communicating_Mobile_filterMap |
    Then audience is created to the plan into DB
    When User requests for Delete an existing audience
    Then audience is deleted to the plan into DB

  Scenario: API - Audiences - Duplicates an existing audience
    When User requests for Creates a new audience
      | name                                 | filter                                                                                  | formattedFilter                                                                                                                                                                              | filterMap                                      |
      | Attitudes_Shopping_Consumer_Watching | ((field("564") && (field("3259") == 1 && field("14278") == 1 && (field("1063") == 2)))) | ((Recoded - Any agree [Advertising helps me know what is available] AND (Smartphone [Yes] AND Drank a fizzy drink [Longer ago] AND (Watch video on demand/catch-up TV [Every 2-3 months])))) | Attitudes_Shopping_Consumer_Watching_filterMap |
    Then audience is created to the plan into DB
    When User requests for Duplicates an existing audience
      | name                        |
      | Age_Group_DuplicateAudience |
    Then Duplicates audience is created into DB

  Scenario: API - Audiences - Select/Unselect audiences for a project
    When User requests for Creates a new audience
      | name                                 | filter                                                                                  | formattedFilter                                                                                                                                                                              | filterMap                                      |
      | Attitudes_Shopping_Consumer_Watching | ((field("564") && (field("3259") == 1 && field("14278") == 1 && (field("1063") == 2)))) | ((Recoded - Any agree [Advertising helps me know what is available] AND (Smartphone [Yes] AND Drank a fizzy drink [Longer ago] AND (Watch video on demand/catch-up TV [Every 2-3 months])))) | Attitudes_Shopping_Consumer_Watching_filterMap |
    Then audience is created to the plan into DB
    And Audiences is selected to the project into DB
    When User requests for Select audiences
    Then Audiences not selected to the project into DB

