@component:web
Feature: Create and edit plans
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage plans

  @issue:DPTV-175 
  Scenario: Click on create new plan
    Given I login with 'Tvstack.user2@dentsuaegis.com'
    And I select 'United Kingdom' market
    And I should be presented with option to select a client
    And I select the client 'Monsoon Accessorise'
    And I should see the option to create a new plan
    When I click on create new plan
    Then I should be taken to Create new plan page
    Then I should be on 'Plan setup' tab
    And I should have option to provide 'Plan name', 'Total video budget', 'Campaign length'
    And I should see 'Create Plan' button and it should be disabled
    And I should see 'Cancel' button and it should be enabled
    When I add plan details and click Next button
    Then I should be taken to Audience Setup page
    And the plan is saved successfully

  Scenario: Delete a plan
    Given I login with 'Tvstack.user2@dentsuaegis.com'
    And I select 'United Kingdom' market
    And I should be presented with option to select a client
    And I select the client 'Monsoon Accessorise'
    And I should see the option to create a new plan
    When I create a new plan
    Then the plan is saved successfully
    And the plan is deleted
    Then the deleted plan is verified

  @issue:DPTV-175
  Scenario: The plan name must be unique for the client and market
    Given a user plans to use an existing plan name for a new plan
    When user enters the exiting plan name
    Then error should be displayed
