@component:web
Feature: Manage channels
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage channels
  
# Rajni's code starts here.....
#Scenario 1:
  Scenario: Calibrate TV Channel-Prepopulating configured values
    Given I'm on audience channels page
    And   I select Closest buying Audience as 'TVDONOTDELETE' corresponding to Channel 'TV'
    And   I select Second Length/Format as 'TVDONOTDELETE' corresponding to Channel 'TV'
    Then  I should see '12' populated in CPM textbox corresponding to Channel 'TV'
    And   I press calibrate button
    Then  I should see GRPs Celebrated at textbox populated as '12'
    Then  I should see Reach populated as '25'
    And   I click on ADVANCED button
    Then  I should see Maximum Reach populated as '56'
    Then  I should see Precision populated as '36'
    And   I click Cancel button
    Then  It should close Calibrate screen

    #Scenario 2:
  Scenario: Add Channel to the Plan
    Given I'm on audience channels page
    And   I select Closest buying Audience as 'TVDONOTDELETE' corresponding to Channel 'TV'
    And   I select Second Length/Format as 'TVDONOTDELETE' corresponding to Channel 'TV'
    Then  I should see '12' populated in CPM textbox corresponding to Channel 'TV'
    And   I select Closest buying Audience as 'VideoDONOTDELETE' corresponding to Channel 'Video on Demand'
    And   I select Second Length/Format as 'VideoFormatDONOTDELETE' corresponding to Channel 'Video on Demand'
    Then  I should see '3' populated in CPM textbox corresponding to Channel 'Video on Demand'
    And   I select Closest buying Audience as 'OnlineVideoDONOTDELETE' corresponding to Channel 'Online+Video'
    And   I select Second Length/Format as 'onlinevideoDONOTDELETE' corresponding to Channel 'Online+Video'
    Then  I should see '13' populated in CPM textbox corresponding to Channel 'Online+Video'
    And   I click 'Next: Scenarios' button
    Then  I should see Scenarios page with Create new scenario button

#Scenario 3: Verify CPM is uneditable for Channels youtube(Auction),facebook and Instagram
Scenario: CPM is uneditable for Channels youtube(Auction),facebook and Instagram
  Given I'm on audience channels page
  And   I select Age as '18-34' corresponding to Channel 'Youtube'
  And   I select Gender as 'Female' corresponding to Channel 'Youtube'
  And   I select Second Length/Format as 'Auction' corresponding to Channel 'Youtube'
  Then  I should see '11.69' populated in CPM textbox corresponding to Channel 'Youtube'
  Then  I should not be able to edit the CPM textbox corresponding to Channel 'Youtube'
  And   I select Min Age as '20' corresponding to Channel 'Facebook'
  And   I select Max Age as '55' corresponding to Channel 'Facebook'
  And   I select Gender as  'Male' corresponding to Channel 'Facebook'
  Then  I should see '11.69' populated in CPM textbox corresponding to Channel 'Facebook'
  Then  I should not be able to edit the CPM textbox corresponding to Channel 'Facebook'
  And   I select Min Age as '18' corresponding to Channel 'Instagram'
  And   I select Max Age as '30' corresponding to Channel 'Instagram'
  And   I select Gender as 'Male' corresponding to Channel 'Instagram'
  Then  I should see '11.69' populated in CPM textbox corresponding to Channel 'Instagram'
  Then  I should not be able to edit the CPM textbox corresponding to Channel 'Instagram'

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
    Then I can see the newly created scenario '1' on Scenarios page
    Then I will click on 'Create new scenario' button
    Then I will click on 'update' button
    Then I will click on 'Close' button
    Then I can see the newly created scenario '2' on Scenarios page
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

  Scenario: To test validation message without selecting any Channels
    Given I'm on Channels setup page
    Then I will uncheck all channels options
    Then I will click Next: Scenarios button
    And I should see message 'Please select at least 1 channel buying audience and format to proceed'

    # Rajni's code ends here.....
  Scenario: Channel setup page
    Given I'm on audience channels page
    Then I should see 6 channels displayed
    And I should see the channel setup table contains
      | 'Channel' | 'Closest buying Audience' | 'Second Lenghts/Format' | 'CPM' |
    And I should see 'Back' button and it should be enabled
    And I should see 'Next: Scenarios' button and it should be enabled

  Scenario: Add a channel to a plan
    Given I'm on audience channels page
    And I click 'Next: Scenarios' button
    Then I can see the 'Scenarios' section

  Scenario: Calibrate TV channel - having valid reach
    Given I'm on audience channels page
    When I press calibrate button
    And I save a new valid calibration
      | true |
    Then I should be taken to audience channel page

  Scenario: Calibrate TV channel - having invalid reach
    Given I'm on audience channels page
    When I press calibrate button
    And I save a new valid calibration
      | false |
    Then an error is displayed in the form

  @issue:DPTV-685 
  Scenario: Deselect all channels and check error
    Given I'm on audience channels page
    When I deselect all channels
    Then all channels are deselected
    And I click 'Next: Scenarios' button
    Then an error is displayed
    When I select all channels
    Then all channels are selected
    And I click 'Next: Scenarios' button
    Then I can see the 'Scenarios' section

  #Scenario: Overwrite values in channel setup table
    #Given I'm on audience channels page
    #When I change the values of all input fields
    #Then the values have changed
