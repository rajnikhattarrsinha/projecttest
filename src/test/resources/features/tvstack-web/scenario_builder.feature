@component:Client_tests
Feature: Scenario builder
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage scenarios

  Scenario: Scenarios setup page verification
    Given I'm on scenarios page
    And I should see the scenario setup table contains
      | 'Scenario name' | 'Reach' | 'GRPs' | 'Last Modified' | 'Modified By' |
    And I should see 'Back' button and it should be enabled
    And I should see 'Next: Scenario Comparison' button and it should be disabled

  Scenario: Scenarios setup page - Create single scenarios
    Given I'm on scenarios page
    When I add scenarios: 1
    Then I should see 'Next: Scenario Comparison' button and it should be disabled

  Scenario: Scenarios setup page - Create multiple scenarios
    Given I'm on scenarios page
    When I add scenarios: 2
    Then I should see 'Next: Scenario Comparison' button and it should be enabled

  Scenario: Scenarios setup page - Add scenarios to a plan
    Given I'm on scenarios page
    When I add scenarios: 2
    And I click 'Next: Scenario Comparison' button
    Then I can see the 'Scenario Comparison' section

  Scenario: Scenarios setup page - Check create scenarios page elements
    Given I'm on scenarios page
    When I navigate to add a new scenario page
    Then the slider, piechart and compare table should be visible