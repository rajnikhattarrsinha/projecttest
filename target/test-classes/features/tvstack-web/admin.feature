@component:web
Feature: Admin tests
  As a global, market, client admin
  I have access to admin features
  So can manager and administer users and buying audience

  @issue:DPTV-309 
  Scenario Outline: Login as global, market and local admins
    When I login as '<type>' user with '<username>'
    Then I should be able to login successfully and should see stuff as per my role assignment
    And I should be able to logout successfully

    Examples: 
      | type         | username                      |
      | global admin | global.admin1@dentsuaegis.com |
      | market lead  | market.admin1@dentsuaegis.com |
      | client lead  | client.admin1@dentsuaegis.com |

  @issue:DPTV-456 @issue:DPTV-457
  Scenario: Global admin to assign market admin
    Given I login as 'global admin' user with 'global.admin1@dentsuaegis.com'
    Then I should see any existing market leads
    And I should see option to logout
    Then I should see option to add a market lead user
    When I add 'admin2' having 'France' as a market
    Then a message is displayed saying the user was successfully added
    And the lead user is properly displayed in the market leads table
    When I add the same user again
    Then a message is displayed saying the user already exists
    And finally I should be able to remove the user successfully

  Scenario: Market admin options
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    Then I should see view categories 'Television', 'General', 'Broadcasters'
    And I should see option to rename channel
    And I should see option to create new buying audience

  Scenario: Market admin - create new buying audience
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    When I click on create new buying audience
    Then I should be presented with planning audience pop up over screen
    When I create new audience and save
    Then the buying audience is created successfully

  Scenario: Unauthorised user login
    When a user who has no roles assigned logs in to admin site
    Then user should be presented with permission denied message

  Scenario: Market admin - rename channel
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    Then I should see view categories 'Television', 'General', 'Broadcasters'
    Then I should see option to rename channel
    When I click on the rename channel button
    And edit the channel name
    Then the name of the channel is updated
    And I revert the channel name to the original value

  Scenario: Market admin - preview existing buying audience along with checking new format button inside
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    When I select an existing audience
    Then I check that the buying audience format is displayed
    And the add new format button is displayed

  @issue:DPTV-576
  Scenario: Market admin - create new format
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    When I click on create new buying audience
    And I create new audience and save
    When I create a new format
    Then I verify the format in the buying audience

  Scenario: Market admin - duplicate a buying audience
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    When I select an existing audience
    Then I click on duplicate button
    And the buying audience was duplicated

  Scenario: Market admin - edit a buying audience
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    When I select an existing audience
    Then I click on edit button
    And the buying audience was edited

  Scenario: Market admin - delete a buying audience
    Given I login as 'market lead' user with 'market.admin1@dentsuaegis.com'
    When I click on create new buying audience
    And I create new audience and save
    When I click on delete button
    Then the buying audience was deleted

  Scenario: Client admin - Verify client leads and planners
    Given I login as 'client lead' user with 'client.admin1@dentsuaegis.com'
    When I select 'adidas' for a client
    Then I see the client leads and planners

  Scenario: Client admin - Add a planner
    Given I login as 'client lead' user with 'client.admin1@dentsuaegis.com'
    When I select 'John Lewis' for a client
    And I add 'Tvstack.user1@dentsuaegis.com' as a planner
    Then a message is displayed saying the user was successfully added
    And the planner is properly displayed in the client planners table
    When I add the same planner again
    Then a message is displayed saying the user already exists
    And finally I should be able to remove the planner successfully
