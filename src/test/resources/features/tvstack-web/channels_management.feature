@component:web
Feature: Manage Channels
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage plans
   
  Scenario: Channels setup page
    Given I'm on Channels setup page
    Then I should see multiple channels options
    Then I will select Channel 'TV' checkbox
    And  I will select any Closest buying Audience option 'Hello World' corresponding to Channels 'TV'
    And  I will select any Second Length/Format option 'Test3' corresponding to Channels 'TV'
    Then I will select Channel 'Video on Demand' checkbox
    And  I will select any Closest buying Audience option 'test 2' corresponding to Channels 'Video on Demand'
    And  I will select any Second Length/Format option 'format 1' corresponding to Channels 'Video on Demand'
    Then I will select Channel 'Online+Video' checkbox
    And  I will select any Closest buying Audience option 'Edited8X4vi' corresponding to Channels 'Online+Video'
    And  I will select any Second Length/Format option 'asd' corresponding to Channels 'Online+Video'
    Then I will select Channel 'Youtube' checkbox
    And  I will select any Age option '18-34' corresponding to Channels 'Youtube'
    And  I will select any Gender option 'Female' corresponding to Channels 'Youtube'
    And  I will select any Second Length/Format option 'Reservation' corresponding to Channels 'Youtube'
    Then I will select Channel 'Facebook' checkbox
    And  I will select any Min Age option '20' corresponding to Channels 'Facebook'
    And  I will select any Max Age option '55' corresponding to Channels 'Facebook'
    And  I will select any Gender option 'Male' corresponding to Channels 'Facebook'
    Then I will select Channel 'Instagram' checkbox
    And  I will select any Min Age option '18' corresponding to Channels 'Instagram'
    And  I will select any Max Age option '30' corresponding to Channels 'Instagram'
    And  I will select any Gender option 'Male' corresponding to Channels 'Instagram'
    Then  I will click Next: Scenarios button
    Then i should see Scenarios page with Create new scenario button
    
 Scenario: To test validation message without selecting any Channels
    Given I'm on Channels setup page
    Then I will uncheck all channels options
    Then I will click Next: Scenarios button
    And I should see message 'Please select at least 1 channel buying audience and format to proceed'