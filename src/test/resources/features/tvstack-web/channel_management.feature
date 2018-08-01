@component:web
Feature: Manage channels
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage channels
  
# Rajni's code starts here.....
#Scenario 5: Overwriting CPM for Channels TV,VideoOnDemand and Online+Video and Saving for Plan
Scenario: CPM is editable for Channel youtube(Reservation,GooglePreferred)
    Given I'm on audience channels page
    And  I select Closest buying Audience as 'TVDONOTDELETE' corresponding to Channel 'TV'
    And  I select Second Length/Format as 'TVDONOTDELETE' corresponding to Channel 'TV'
    Then I should see '20.00' populated in CPM textbox corresponding to Channel 'TV'
    Then I should be able to enter value '22.00' in CPM textbox corresponding to Channel 'TV'
    And  I select Closest buying Audience as 'VideoDONOTDELETE' corresponding to Channel 'Video on Demand'
    And  I select Second Length/Format as 'VideoFormatDONOTDELETE' corresponding to Channel 'Video on Demand'
    And  I should see '20.00' populated in CPM textbox corresponding to Channel 'Video on Demand'
    Then I should be able to enter value '22.00' in CPM textbox corresponding to Channel 'Video on Demand'
    And  I select Closest buying Audience as 'OnlineVideoDONOTDELETE' corresponding to Channel 'Online+Video'
    And  I select Second Length/Format as 'onlinevideoDONOTDELETE' corresponding to Channel 'Online+Video'
    And  I should see '20.00' populated in CPM textbox corresponding to Channel 'Online+Video'
    Then I should be able to enter value '22.00' in CPM textbox corresponding to Channel 'Online+Video'
    Then I will click on 'Next: Scenarios' button
    Then I can see the 'Scenarios' section
    Then I will click on 'Create new scenario' button
    Then I will click on 'update' button
    Then I will click on 'Close' button
    Then I can see the 'Scenarios' section
    Then I will click on 'Create new scenario' button
    Then I will click on 'update' button
    Then I will click on 'Close' button
    Then I will click on 'Next: Scenario Comparison' button
    Then I will select newly created scenario A from scenario A listbox
    Then I will select newly created scenario B  from scenario B listbox
    Then I will select created plan from Planning Audience listbox
    Then I will click on 'Compare' button
    Then I click on 'Channels' from header breadcrumb
    Then I navigate till 'Channels' set up page
    Then I should see '22.00' populated in CPM textbox corresponding to Channel 'TV'
    Then I should see '22.00' populated in CPM textbox corresponding to Channel 'Video on Demand'
    Then I should see '22.00' populated in CPM textbox corresponding to Channel 'Online+Video'

