@component:web
Feature: Manage Channels
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage plans

  Scenario: Channels setup page
    Given I'm on Channels setup page
    Then I should see multiple channels options
    Then I will select Channel 'TV' checkbox
    And  I will select any Closest buying Audience option 'userInput1' corresponding to Channels 'userInput2'
    And  I will select any Second Length/Format option 'userInput' corresponding to Channels 'userInput2'
    Then I will select Channel 'Video on Demand' checkbox
    And  I will select any Closest buying Audience option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Second Length/Format option 'userInput' corresponding to Channels 'userInput2'
    Then I will select Channel 'Online+Video' checkbox
    And  I will select any Closest buying Audience option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Second Length/Format option 'userInput' corresponding to Channels 'userInput2'
    Then I will select Channel 'Youtube' checkbox
    And  I will select any Age option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Gender option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Second Length/Format option 'userInput' corresponding to Channels 'userInput2'
    Then I will select Channel 'Facebook' checkbox
    And  I will select any Min Age option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Max Age option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Gender option 'userInput' corresponding to Channels 'userInput2'
    Then I will select Channel 'Instagram' checkbox
    And  I will select any Min Age option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Max Age option 'userInput' corresponding to Channels 'userInput2'
    And  I will select any Gender option 'userInput' corresponding to Channels 'userInput2'
    Then  I will click Next: Scenarios button
    Then i should see Scenarios page with Create new scenario button
    
 Scenario: To test validation message without selecting any Channels
    Given I'm on Channels setup page
    Then I will uncheck all channels options
    Then I will click Next: Scenarios button
    And I should see message 'Please select at least 1 channel buying audience and format to proceed'