@component:Client_tests
Feature: Scenario comparison
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage comparison

  Scenario: Comparison setup page - Edit Audiences
    Given I'm on comparison page
    When I click 'Edit Audiences' button
    Then I should be taken to 'Audiences' page

  Scenario: Comparison setup page - Edit Channels
    Given I'm on comparison page
    When I click 'Edit Channels' button
    Then I should be taken to 'Channels' page

  Scenario: Comparison setup page - Edit Scenarios
    Given I'm on comparison page
    When I click 'Edit Scenarios' button
    Then I should be taken to 'Scenarios' page

  Scenario: Comparison setup page - Compare scenarios
    Given I'm on comparison page
    When I select previously created scenarios
    And I click 'Compare' button
    Then I should see that the comparison table contains
      | 'Scenario' | 'Budget' | 'Reach' | 'Freq.' | 'GRPs' | 'CPM' | '2+ Reach' | '4+ Reach' | '5+ Reach' | 'Cost Per Reach Point' |
    And the GRPs and Reach charts should be visible
    And 'Download' button is displayed
    And 'Measure' button is displayed
