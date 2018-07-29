package net.enablers.tvstack.steps;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.WebHelper;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.pages.ChannelSetupPage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.serenitybdd.screenplay.questions.ValueOf;
import net.thucydides.core.annotations.Steps;

public class ChannelSetupPageSteps {
	
    AudienceSetupPage audienceSetupPage;
    ChannelSetupPage channelSetupPage;
    AppliEyes appliEyes;
    WebHelper webHelper;

    @Steps
    TvstackLandingPageSteps landingPageSteps;


    @Steps
    NewPlanSetupPageSteps planSetupPageSteps;
    
    @Steps
    AudienceSetupPageSteps audienceSetupPageSteps;
    
    //****************** RAJNI CODE START HERE*****************************//
    //*******************************************************************///

    @Given("^I'm on Channels setup page$")
    public void i_m_on_Channels_setup_page() throws Throwable
    {
        landingPageSteps.userHasChosenToCreateNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        webHelper.WaitForPageLoad(60);
        audienceSetupPage.iWillCreateNewAudienceandSavetheAudience();
        channelSetupPage.clickOnNextChannelsbuttonandverifyChannelPage();
        appliEyes.capture("User is landed on Channels setup page.");
    }

    @Then("^I should see multiple channels options$")
    public void i_should_see_multiple_channels_options() throws Throwable
    {
        channelSetupPage.verifyChannelsPage();
        appliEyes.capture("Channels page is displayed multiple channels option.");
    }

    @Then("^I will select Channel '(.*)' checkbox$")
    public void i_will_select_Channel_checkbox(String Channelname) throws Throwable
    {
        channelSetupPage.checkChannelscheckbox(Channelname);
        appliEyes.capture("Channel '"+Channelname+"' checkbox checked successfully.");
    }

    @Then("^I will select any Closest buying Audience option '(.*)' corresponding to Channels '(.*)'$")
    public void i_will_select_any_Closest_buying_Audience_option_userInput_corresponding_to_Channels_userInput(String buyingAudience, String ChannelName) throws Throwable
    {
        channelSetupPage.selectClosestbuyingAudienceoption(ChannelName,buyingAudience);
        appliEyes.capture("Closest buying Audience option '"+buyingAudience+"' successfully selected for Channel '"+ChannelName+"'.");
    }

    @Then("^I will select any Second Length/Format option '(.*)' corresponding to Channels '(.*)'$")
    public void i_will_select_any_Second_Length_Format_option_userInput_corresponding_to_Channels_userInput(String secondlengthFormatoption,String ChannelName) throws Throwable
    {
        channelSetupPage.selectSecondLengthFormatoption(ChannelName,secondlengthFormatoption);
        appliEyes.capture("Second Length format option '"+secondlengthFormatoption+"' successfully selected for Channel '"+ChannelName+"'.");
    }

    @Then("^I will select any Age option '(.*)' corresponding to Channels '(.*)'$")
    public void i_will_select_any_Age_option_userInput_corresponding_to_Channels_userInput(String Age,String Channel) throws Throwable
    {
        channelSetupPage.selectAgeforYoutubeChannel(Age);
        appliEyes.capture("Age '"+Age+"' is successfully selected for Channel '"+Channel+"'.");
    }

    @Then("^I will select any Gender option '(.*)' corresponding to Channels '(.*)'$")
    public void i_will_select_any_Gender_option_userInput_corresponding_to_Channels_userInput(String Gender,String Channel) throws Throwable
    {
        channelSetupPage.selectGender(Gender, Channel);
        appliEyes.capture("Gender '"+Gender+"' is successfully selected for Channel '"+Channel+"'.");
    }


    @Then("^I will click Next: Scenarios button$")
    public void i_will_click_Next_Scenarios_button() throws Throwable
    {
        channelSetupPage.clickonNextScenariosbutton();
        appliEyes.capture("Button 'Next Scenario' clicked successfully.");
    }

    @Then("^i should see Scenarios page with Create new scenario button$")
    public void i_should_see_Scenarios_page_with_Create_new_scenario_button() throws Throwable
    {
        channelSetupPage.verifyScenariosPage();
        appliEyes.capture("'Scenarios' page open successfully.");
    }

    @Then("^I will uncheck all channels options$")
    public void i_will_uncheck_all_channels_options() throws Throwable
    {
        channelSetupPage.uncheckAllChannelcheckbox();
        appliEyes.capture("All Channel checkbox uncheck successfully.");
    }

    @Then("^I should see message 'Please select at least (\\d+) channel buying audience and format to proceed'$")
    public void i_should_see_message_Please_select_at_least_channel_buying_audience_and_format_to_proceed(int arg1) throws Throwable
    {
        channelSetupPage.verifyMessageonHeader();
        appliEyes.capture("'Please select at least 1 channel buying audience and format to proceed' message displayed.");
    }


    @Then("^I will select any Min Age option '(.*)' corresponding to Channels '(.*)'$")
    public void i_will_select_any_Min_Age_option_corresponding_to_Channels(String MinAge,String Channel) throws Throwable
    {
        channelSetupPage.selectMinAge(MinAge, Channel);
        appliEyes.capture("MinAge '"+MinAge+"' is successfully selected for Channel '"+Channel+"'.");
    }

    @Then("^I will select any Max Age option '(.*)' corresponding to Channels '(.*)'$")
    public void i_will_select_any_Max_Age_option_corresponding_to_Channels(String MaxAge,String Channel) throws Throwable
    {
        channelSetupPage.selectMaxAge(MaxAge, Channel);
        appliEyes.capture("MaxAge '"+MaxAge+"' is successfully selected for Channel '"+Channel+"'.");

    }

//########################################################################################################
    //# Scenario ID : 1
    //# Test Case Calibrate TV Channel-Prepopulating configured values
    //#------------------------------------------------------------------------------------------------------
    //# Description: This test case check configured values of Channel TV
    //#------------------------------------------------------------------------------------------------------
    //# Pre-conditions: NA
    //# Post-conditions: NA
    //# Limitations: NA
    //#------------------------------------------------------------------------------------------------------
    //# Owner:  Rajni
    //# Created on: 26-July-2018
    //#------------------------------------------------------------------------------------------------------
    //# Reviewer:
    //# Review Date:
    //#------------------------------------------------------------------------------------------------------
    //# History Notes:
    //########################################################################################################

    @Then("^I should see multiple channels checkboxes selected by default$")
    public void i_should_see_multiple_channels_checkboxes_selected_by_default() throws Throwable
    {
        channelSetupPage.verifyChannelsPage();
        appliEyes.capture("Channels page is displayed multiple channels option.");
    }

    @Then("^I select Closest buying Audience as '(.*)' corresponding to Channel '(.*)'")
    public void i_select_Closest_buying_Audience_as_corresponding_to_Channel(String buyingAudience,String ChannelName ) throws Throwable
    {
        channelSetupPage.selectClosestbuyingAudienceoption(ChannelName,buyingAudience);
        appliEyes.capture("Closest buying Audience option '"+buyingAudience+"' successfully selected for Channel '"+ChannelName+"'.");
    }

    @Then("^I select Second Length/Format as '(.*)' corresponding to Channel '(.*)'")
    public void i_select_Second_Length_Format_corresponding_to_Channel(String secondlengthFormatoption,String ChannelName) throws Throwable
    {
        channelSetupPage.selectSecondLengthFormatoption(ChannelName,secondlengthFormatoption);
        appliEyes.capture("Second Length format option '"+secondlengthFormatoption+"' successfully selected for Channel '"+ChannelName+"'.");
    }

    @Then("^I should see '(.*)' populated in CPM textbox corresponding to Channel '(.*)'$")
    public void i_should_see_populated_in_CPM_textbox_corresponding_to_Channel(String  CPM,String Channel) throws Throwable
    {
        channelSetupPage.getCPMvalueandverify(Channel,CPM);
        appliEyes.capture("Expected Channel '"+Channel+"' CPM  value '"+CPM+"' is displayed.");
    }

    @Then("^I click Calibrate button$")
    public void i_click_Calibrate_button() throws Throwable
    {
        channelSetupPage.clickonCalibratebutton();
        appliEyes.capture("Button 'Calibrate' successfully clicked.");
    }

    @Then("^I should see GRPs Celebrated at textbox populated as '(.*)'$")
    public void i_should_see_GRPs_Celebrated_at_textbox_populated_as(String GRps) throws Throwable
    {
        channelSetupPage.getGRPsvalueandverifytouserinput(GRps);
        appliEyes.capture("Expected value of GRPs Calibrated at '"+GRps+"' is displayed.");
    }

    @Then("^I should see Reach populated as '(.*)'")
    public void i_should_see_Reach_populated_as(String Reach) throws Throwable
    {
        channelSetupPage.getReachvalueandComapreUserInputValue(Reach);
        appliEyes.capture("Expected value of Reach '"+Reach+"' is displayed.");
    }

    @Then("^I click on ADVANCED button$")
    public void i_click_on_ADVANCED_button() throws Throwable
    {
        channelSetupPage.iClickonADVANCEDbutton();
        appliEyes.capture("'Advance' link clicked successfully");
    }

    @Then("^I should see Maximum Reach populated as '(.*)'")
    public void i_should_see_Maximum_Reach_populated_as(String MaximumReach) throws Throwable
    {
        channelSetupPage.getMaximumReachValueAndComapreUserInputValue(MaximumReach);
        appliEyes.capture("Expected value of 'Maximum Reach' "+MaximumReach+" is displayed.");
    }

    @Then("^I should see Precision populated as '(.*)'")
    public void i_should_see_Precision_populated_as(String Precision) throws Throwable
    {
        channelSetupPage.getPrecisionValueAndComapreUserInputValue(Precision);
        appliEyes.capture("Expected value of 'Precision' "+Precision+" is displayed.");
    }

    @Then("^I click Cancel button$")
    public void i_click_Cancel_button() throws Throwable
    {
        channelSetupPage.iclickCancelbutton();
        appliEyes.capture("'Cancel' button clicked successfully");
    }

    @Then("^It should close Calibrate screen$")
    public void it_should_close_Calibrate_screen() throws Throwable
    {
        channelSetupPage.verifyCalibrateScreenIsnotDisplayed();
        appliEyes.capture("Calibrate screen closed successfylly after clicking Cancel button");
    }

    //****************** RAJNI CODE END HERE*****************************//
    //*******************************************************************//

    //Rajni's code ends here......
    
    @Then("^I should be taken to audience channel page$")
    public void iShouldBeTakenToAudienceChannelPage() {
    	channelSetupPage.verifyPageTitle("Channel");
        appliEyes.capture("Landed on audience channel page");
    }

    @Given("^I'm on audience channels page$")
    public void iMOnAudienceChannelPage() throws Throwable {
        landingPageSteps.userHasChosenToCreateNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        audienceSetupPageSteps.iShouldBeTakenToAudienceSetupPage();
        audienceSetupPageSteps.iClickOnCreateNewAudience();
        audienceSetupPageSteps.iCreateNewAudienceAndSave();
        channelSetupPage.iClickChannelsButton();
        this.iShouldBeTakenToAudienceChannelPage();
    }
    
    @Then("^I should see (\\d+) channels displayed$")
    public void iShouldSeeTheChannels(int channelNo) throws Exception {
    	channelSetupPage.verifyAvailableChannels(channelNo);
    }
    
    @Then("^I should see the channel setup table contains$")
    public void iShouldSeeTheChannelSetupTableContainsAllOptions(List<String> options) throws Exception {
    	channelSetupPage.verifyChannelTableHeader(options);
    }
    
    @Given("^I click '(.*)' button$")
    public void iClickButton(String buttonType) throws Exception {
    	channelSetupPage.iClickScenariosButton(buttonType);
    }

    @Then("^I can see the '(.*)' section$")
    public void iCanSeeTheSelected_Channel(String section) throws Exception {
    	channelSetupPage.verifyPageTitle(section);
    }
    
    @When("^I press calibrate button$")
    public void iPressCalibrateButton() throws Exception {
    	channelSetupPage.clickOnCalibrate();
    }

    @Then("^I save a new valid calibration$")
    public void iSaveANewCalibration(List<Boolean> isValid) throws Exception {
    	boolean isValidDataProvided = isValid.get(0);
    	channelSetupPage.addFormData(isValidDataProvided);
    	channelSetupPage.saveFormData();
    }
    
    @Then("^an error is displayed in the form$")
    public void anErrorIsDisplayedInTheForm() throws Exception {
    	channelSetupPage.checkModalError();
    }
    
    @When("^I deselect all channels$")
    public void iDeselectAllChannels() throws Exception {
    	channelSetupPage.deselectAllChannels();
    }

    @Then("^all channels are deselected$")
    public void allChannelsAreDeselected() throws Exception {
    	channelSetupPage.allChannelsAreDeselected();
    }

    @When("^I select all channels$")
    public void iSelectAllChannels() throws Exception {
    	channelSetupPage.selectAllChannels();
    }

    @Then("^all channels are selected$")
    public void allChannelsAreSelected() throws Exception {
    	channelSetupPage.allChannelsAreSelected();
    }

}
