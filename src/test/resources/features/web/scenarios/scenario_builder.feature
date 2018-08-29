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

  Scenario: Scenarios setup page - Optimize scenarios - Set percentage Budget to 100%
    Given I'm on scenarios page
    When I navigate to add a new scenario page
    And I optimize the 'TV' channel percentage budget to 100
    Then the slider displays 100% for TV
    And I verify that the budget is fully distributed within channels

  Scenario: Scenarios setup page - Optimize scenarios - Set Budget to maximum default value
    Given I'm on scenarios page
    When I navigate to add a new scenario page
    And I optimize the 'TV' channel budget to 1000000
    Then the slider displays 100% for TV
    And I verify that the budget is fully distributed within channels

  Scenario: Scenarios setup page - Optimize scenarios - Check automated channel budget distribution
    Given I'm on scenarios page
    When I navigate to add a new scenario page
    And I optimize the 'TV' channel budget to 300000
    Then the slider displays 30% for TV
    And I verify that the budget is fully distributed within channels

  Scenario: Scenarios setup page - Optimize scenarios using the Slider
    Given I'm on scenarios page
    When I navigate to add a new scenario page
    And I optimize using the 30% OLV slider bar
    Then the 'TV' budget percentage was changed to 70%
    And the slider displays 70% for TV
    And I verify that the budget is fully distributed within channels