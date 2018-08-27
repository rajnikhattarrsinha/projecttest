@component:Client_tests
Feature: Channel selection
  As a tvstack user
  I can land on welcome TV stack page
  So that I can manage channels

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


# Rajni's code starts here.....
#Scenario 1:Calibrate TV Channel-Prepopulating configured values

  Scenario: Calibrate TV Channel-Prepopulating configured values
    Given I'm on audience channels page
    And   I select Closest buying Audience as 'TVbuyingAudience' corresponding to Channel '1'
    And   I select Second Length/Format as 'TVSecondLength' corresponding to Channel '1'
    Then  I should see 'defaultTVCPM' populated in CPM textbox corresponding to Channel '1'
    And   I press calibrate button
    Then  I should see GRPs Celebrated at textbox populated as 'GRPsCelebrated'
    Then  I should see Reach populated as 'Reachpopulated'
    And   I click on ADVANCED button
    Then  I should see Maximum Reach populated as 'MaximumReachpopulated'
    Then  I should see Precision populated as 'Precision'
    And   I click Cancel button
    Then  It should close Calibrate screen

    #Scenario 2:Add Channel to the Plan with Values selected for TV,Video on Demand and Online Videos

  Scenario: Add Channel to the Plan with Values selected for TV,Video on Demand and Online Videos
    Given I'm on audience channels page
    And   I select Closest buying Audience as 'TVDONOTDELETE' corresponding to Channel '1'
    And   I select Second Length/Format as 'TVDONOTDELETE' corresponding to Channel '1'
    Then  I should see '20.00' populated in CPM textbox corresponding to Channel '1'
    And   I select Closest buying Audience as 'VideoDONOTDELETE' corresponding to Channel '2'
    And   I select Second Length/Format as 'VideoFormatDONOTDELETE' corresponding to Channel '2'
    Then  I should see '20.00' populated in CPM textbox corresponding to Channel '2'
    And   I select Closest buying Audience as 'OnlineVideoDONOTDELETE' corresponding to Channel '3'
    And   I select Second Length/Format as 'onlinevideoDONOTDELETE' corresponding to Channel '3'
    Then  I should see '20.00' populated in CPM textbox corresponding to Channel '3'
    And   I click 'Next: Scenarios' button
    #Then  I should see Scenarios page with Create new scenario button
    #Then I can see the 'Scenarios' section

#Scenario 3: Verify CPM is uneditable for Channels youtube(Auction),facebook and Instagram

  Scenario: CPM is uneditable for Channels youtube(Auction),facebook and Instagram
    Given I'm on audience channels page
    And    I select Age as '18-34' corresponding to Channel Youtube
    And   I select Gender as 'Female' corresponding to Channel 'Youtube'
    And   I select Second Length/Format as 'Auction' corresponding to Channel '4'
    Then  I should see '11.69' populated in CPM textbox corresponding to Channel '4'
    Then  I should not be able to edit the CPM textbox corresponding to Channel '4'
    And   I select Min Age as '20' corresponding to Channel 'Facebook'
    And   I select Max Age as '55' corresponding to Channel 'Facebook'
    And   I select Gender as 'Male' corresponding to Channel 'Facebook'
    Then  I should see '11.69' populated in CPM textbox corresponding to Channel '5'
    Then  I should not be able to edit the CPM textbox corresponding to Channel '5'
    And   I select Min Age as '18' corresponding to Channel 'Instagram'
    And   I select Max Age as '30' corresponding to Channel 'Instagram'
    And   I select Gender as 'Male' corresponding to Channel 'Instagram'
    Then  I should see '11.69' populated in CPM textbox corresponding to Channel '6'
    Then  I should not be able to edit the CPM textbox corresponding to Channel '6'

   #Scenario 4: Verify CPM is editable for Channel youtube(Reservation,GooglePreferred)

  Scenario: CPM is editable for Channel youtube(Reservation,GooglePreferred)
    Given I'm on audience channels page
    And  I select Age as '18-34' corresponding to Channel Youtube
    And  I select Gender as 'Female' corresponding to Channel 'Youtube'
    And  I select Second Length/Format as 'Reservation' corresponding to Channel '4'
    And  I should see '11.69' populated in CPM textbox corresponding to Channel '4'
    Then I should be able to enter value '12' in CPM textbox corresponding to Channel '4'
    And  I select Second Length/Format as 'GooglePreferred' corresponding to Channel '4'
    And  I should see '11.69' populated in CPM textbox corresponding to Channel '4'
    Then I should be able to enter value '14' in CPM textbox corresponding to Channel '4'


    #Scenario 5: Overwriting CPM for Channels TV,VideoOnDemand and Online+Video and Saving for Plan
  Scenario: Overwriting CPM for Channels TV,VideoOnDemand and Online+Video and Saving for Plan
    Given I'm on audience channels page
    And  I select Closest buying Audience as 'TVDONOTDELETE' corresponding to Channel '1'
    And  I select Second Length/Format as 'TVDONOTDELETE' corresponding to Channel '1'
    Then I should see '20.00' populated in CPM textbox corresponding to Channel '1'
    Then I should be able to enter value '22.00' in CPM textbox corresponding to Channel '1'
    And  I select Closest buying Audience as 'VideoDONOTDELETE' corresponding to Channel '2'
    And  I select Second Length/Format as 'VideoFormatDONOTDELETE' corresponding to Channel '2'
    And  I should see '20.00' populated in CPM textbox corresponding to Channel '2'
    Then I should be able to enter value '22.00' in CPM textbox corresponding to Channel '2'
    And  I select Closest buying Audience as 'OnlineVideoDONOTDELETE' corresponding to Channel '3'
    And  I select Second Length/Format as 'onlinevideoDONOTDELETE' corresponding to Channel '3'
    And  I should see '20.00' populated in CPM textbox corresponding to Channel '3'
    Then I should be able to enter value '22.00' in CPM textbox corresponding to Channel '3'
    Then I click on 'Next: Scenarios' button
    Then I can see the 'Scenarios' section
    Then I click on 'Create scenario' button
    Then I click on 'update' button
    Then I click on 'Close' button
    Then I should see the newly created scenario '1' on Scenarios page
    Then I click on 'Create scenario' button
    Then I click on 'update' button
    Then I click on 'Close' button
    Then I should see the newly created scenario '2' on Scenarios page
    Then I click on 'Next: Scenario Comparison' button
    Then I select newly created scenario A from scenario A listbox
    Then I select newly created scenario B  from scenario B listbox
    Then I select created plan from Planning Audience listbox
    Then I click on 'Compare' button
    And  I click plan name edit link from top of the page
    Then I should see 'Edit plan' page
    And  I click on 'Update' button
    And  I click on 'Next: Channels' button
    Then I should see '22.00' populated in CPM textbox corresponding to Channel '1'
    Then I should see '22.00' populated in CPM textbox corresponding to Channel '2'
    Then I should see '22.00' populated in CPM textbox corresponding to Channel '3'



    # Rajni's code ends here.....