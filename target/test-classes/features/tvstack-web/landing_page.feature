@component:Client_tests
Feature: Plan setup - Landing page
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage plans

  @issue:DPTV-173
  Scenario: View landing page features
    Given I login with 'tvstack.user1@dentsuaegis.com'
    Then I should see the option to select the market
    When I select 'United Kingdom' market
    Then I should be presented with option to select a client
    When I select the client 'John Lewis'
    Then I should see the option to create a new plan
    Then I should be presented with existing plans section
    Then I should see Plan name, Owner, Date Created fields under existing plans
    Then I should see options to 'copy', 'edit', 'delete' and 'launch' under any existing plans

  @issue:DPTV-179 @issue:DPTV-576
  Scenario: Click on editing existing plan
    Given there is an existing plan
    When I click on edit button on existing plan
    Then I should be taken to Edit plan page
    And I should see 'Update' button and it should be enabled
    When I edit plan details and click on update
    And the plan is saved successfully

  @issue:DPTV-356
  Scenario: Set definitive and Remove definitive from home page plan list
    Given A new plan is there on landing page
    Then I marked the plan as definitive
    And I checked the marked plan is definitive
    Then I marked the plan as not definitive
    And I checked marked plan is not definitive

  @issue:DPTV-356
     Scenario: Set definitive from Edit page
    Given A new plan is there on landing page
    When I click on edit button for a plan
    And I mark the plan as definitive on edit page
    Then I checked the marked plan is definitive

  @issue:DPTV-607
  Scenario: Sort plans on landing page
    Given A Plan is there on landing page with marked as definitive
    Then I should see the definitive plan on top of the list
    When I change the sorting to newest first
    Then I should see the plan which is newly created on the top of list
    When I change the sorting to oldest first
    Then I should not be able to see the newest plan first

