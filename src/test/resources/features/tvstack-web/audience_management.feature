@component:web
Feature: Manage audience
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage plans

  Scenario: Audience setup page
    Given I'm on audience setup page
    Then I should see option to create new audience
    And I should see existing audiences
    And I should see all the options against each existing audience
    And I should see 'Next: Channels' button and it should be enabled

  Scenario: Create new Audience and delete
    Given I'm on audience setup page
    When I click on create new audience
    Then I should be presented with planning audience pop up over screen
    When I create a new query group based on gender
    And save the audience
    Then the audience is saved successfully
    When I click on delete audience
    Then the audience is deleted successfully

