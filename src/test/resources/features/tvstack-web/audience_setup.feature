@component:Client_tests
Feature: Audience setup
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

    @issue DPTV-715
    Scenario: Copy Existing Audience
      Given I create a new audience
      When I clone the Audience
      And I verify that the clone audience is present on the list

@skip
      @issue DPTV-715
      Scenario: Edit Audience
        Given I'm on audience setup page
        When I click on create new audience
        Then I should be presented with planning audience pop up over screen
        When I create a new query group based on gender
        And save the audience
        Then the audience is saved successfully
        Then I click on Edit Audience
        Then I saved the details



