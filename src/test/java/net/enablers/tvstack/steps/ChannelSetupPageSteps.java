package net.enablers.tvstack.steps;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.pages.ChannelSetupPage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ChannelSetupPageSteps {
	
	NewPlanSetupPage newPlanSetupPage;
    AudienceSetupPage audienceSetupPage;
    ChannelSetupPage channelSetupPage;
    AppliEyes appliEyes;

    @Steps
    TvstackLandingPageSteps landingPageSteps;

    @Steps
    NewPlanSetupPageSteps planSetupPageSteps;
    
    @Steps
    AudienceSetupPageSteps audienceSetupPageSteps;


    //****************** RAJNI CODE START HERE*****************************//
    //*******************************************************************///

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

//    @Then("^I select Closest buying Audience as '(.*)' corresponding to Channel '(.*)'")
//    public void iSelectClosestbuyingAudienceOfChannel(String buyingAudience,String channelName) throws Throwable
//    {
//        channelSetupPage.selectClosestbuyingAudienceoption(channelName,buyingAudience);
//        appliEyes.capture("Closest buying Audience option '"+buyingAudience+"' successfully selected for Channel '"+channelName+"'.");
//    }

    @Then("^I select Closest buying Audience as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectClosestbuyingAudienceOfChannel(String buyingAudience, String channelNo) throws Throwable
    {
        channelSetupPage.selectClosestbuyingAudienceoption(channelNo,buyingAudience);
        appliEyes.capture("Closest buying Audience option '"+buyingAudience+"' successfully selected for Channel '"+channelNo+"'.");
    }


    @Then("^I select Second Length/Format as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectSecondLengthFormatOfChannel(String secondlengthFormatoption,String channelNo) throws Throwable
    {
        channelSetupPage.selectSecondLengthFormatoption(channelNo,secondlengthFormatoption);
        appliEyes.capture("Second Length format option '"+secondlengthFormatoption+"' successfully selected for Channel '"+channelNo+"'.");
    }

    @Then("^I should see '(.*)' populated in CPM textbox corresponding to Channel '(.*)'")
    public void iShouldSeePopulatedCPMtextboxValueOfChannel(String userCPMInput,String channelNo) throws Throwable
    {
        channelSetupPage.getCPMValueOfChannelAndVerifyWithUserCPMvalue(channelNo,userCPMInput);
        appliEyes.capture("Expected Channel '"+channelNo+"' CPM  value '"+userCPMInput+"' is displayed.");
    }



    @Then("^I should see GRPs Calibrated at textbox populated as '(.*)'")
    public void iShouldSeeGRPsCelebratedatTextboxValueOfChannel(String userGRPs) throws Throwable
    {
        channelSetupPage.getGRPsValueAndverifyWithUserGRPs(userGRPs);
        appliEyes.capture("Expected value of GRPs Calibrated at '"+userGRPs+"' is displayed.");
    }

    @Then("^I should see Reach populated as '(.*)'")
    public void iShouldSeeReachpopulatedTextValueAndVerifyWithUserReachValue(String Reach) throws Throwable
    {
        channelSetupPage.getReachvalueAndVerifyWithUserReachValue(Reach);
        appliEyes.capture("Expected value of Reach '"+Reach+"' is displayed.");
    }

    @Then("^I click on ADVANCED button")
    public void iClickOnADVANCEDbutton() throws Throwable
    {
        channelSetupPage.iClickonADVANCEDbutton();
        appliEyes.capture("'Advance' link clicked successfully");
    }

    @Then("^I should see Maximum Reach populated as '(.*)'")
    public void iShouldSeeMaximumReachPopulatedTextValueAndVerifyUserMaximumReach(String MaximumReach) throws Throwable
    {
        channelSetupPage.getMaximumReachValueAndVerifyWithUserMaximumReachValue(MaximumReach);
        appliEyes.capture("Expected value of 'Maximum Reach' "+MaximumReach+" is displayed.");
    }

    @Then("^I should see Precision populated as '(.*)'")
    public void iShouldSeePrecisionPopulatedValueAndVerifyWithUserPrecisionValue(String Precision) throws Throwable
    {
        channelSetupPage.getPrecisionValueAndComapreUserInputValue(Precision);
        appliEyes.capture("Expected value of 'Precision' "+Precision+" is displayed.");
    }

    @Then("^I click Cancel button$")
    public void iClickCancelButton() throws Throwable
    {
        channelSetupPage.iclickCancelbutton();
        appliEyes.capture("'Cancel' button clicked successfully");
    }

    @Then("^It should close Calibrate screen$")
    public void itShouldCloseCalibrateScreen() throws Throwable
    {
        channelSetupPage.verifyCalibrateScreenIsnotDisplayed();
        appliEyes.capture("Calibrate screen closed successfylly after clicking Cancel button");
    }

    //########################################################################################################
    //# Scenario ID : 2
    //# Test Case Calibrate Add Channel to the Plan
    //#------------------------------------------------------------------------------------------------------
    //# Description: This test case adding channel and verify next scenario page.
    //#------------------------------------------------------------------------------------------------------
    //# Pre-conditions: NA
    //# Post-conditions: NA
    //# Limitations: NA
    //#------------------------------------------------------------------------------------------------------
    //# Owner:  Rajni
    //# Created on: 30-July-2018
    //#------------------------------------------------------------------------------------------------------
    //# Reviewer:
    //# Review Date:
    //#------------------------------------------------------------------------------------------------------
    //# History Notes:
    //########################################################################################################

   @Given("^I should see Scenarios page with Create new scenario button$")
    public void iShouldSeeScenariosPageWithCreateNewScenarioButton() throws Throwable
   {
        channelSetupPage.verifyScenariosPageWithNewScenariosButton();
       appliEyes.capture("'Scenarios' page is displayed with 'Create scenario' button.");
    }




    //########################################################################################################
    //# Scenario ID : 3 & 4
    //# Test Case CPM is uneditable for Channels youtube(Auction),facebook and Instagram
    //#------------------------------------------------------------------------------------------------------
    //# Description: This test case verify CPM field is uneditable for channels youtube(Auction),facebook and Instagram
    //#------------------------------------------------------------------------------------------------------
    //# Pre-conditions: NA
    //# Post-conditions: NA
    //# Limitations: NA
    //#------------------------------------------------------------------------------------------------------
    //# Owner:  Rajni
    //# Created on: 31-July-2018
    //#------------------------------------------------------------------------------------------------------
    //# Reviewer:
    //# Review Date:
    //#------------------------------------------------------------------------------------------------------
    //# History Notes:
    //########################################################################################################

    @Given("^I select Age as '(.*)' corresponding to Channel Youtube")
    public void iSelectAgeForChannelYoutube(String ageforYouTube) throws Throwable
    {
        channelSetupPage.selectAgeforYoutubeChannel(ageforYouTube);
        appliEyes.capture("Age '"+ageforYouTube+"' is successfully selected for Channel 'YouTube'");
    }

    @Given("^I select Gender as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectGenderCorrespondingToChannel(String Gender,String Channel ) throws Throwable
    {
        channelSetupPage.selectGender(Gender, Channel);
        appliEyes.capture("Gender '"+Gender+"' is successfully selected for Channel '"+Channel+"'.");
    }

    @Then("^I should not be able to edit the CPM textbox corresponding to Channel '(.*)'")
    public void iShouldSeeNonEditableCPMtextboxCorrespondingToChannel(String channelNo) throws Throwable
    {
        channelSetupPage.verifyNoneditableCPMtextbox(channelNo);
        appliEyes.capture("CPM textbox is non editable for Channel '"+channelNo+".");
    }

    @Then("^I select Min Age as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectMinAgeCorrespondingToChannel(String MinAge,String channelName) throws Throwable
    {
        channelSetupPage.selectMinAge(MinAge, channelName);
        appliEyes.capture("Min Age '"+MinAge+"' is successfully selected for Channel '"+channelName+"'.");
    }

    @Then("^I select Max Age as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectMaxAgeCorrespondingToChannel(String MaxAge,String channelName) throws Throwable
    {
        channelSetupPage.selectMaxAge(MaxAge, channelName);
        appliEyes.capture("Max Age '"+MaxAge+"' is successfully selected for Channel '"+channelName+"'.");
    }

    @Then("^I should be able to enter value '(.*)' in CPM textbox corresponding to Channel '(.*)'$")
    public void iShouldBeAbleToEnterValueInCPMTextboxCorrespondingToChannel(String  CPM,String channelNo) throws Throwable
    {
        channelSetupPage.setCPMvalue(channelNo,CPM);
        appliEyes.capture("In Channel '"+channelNo+"' CPM  value '"+CPM+"' is entered.");
    }
    @Then("^I click on '(.*)' button$")
    public void iClickonanyButton(String buttonText) throws Throwable
    {
        channelSetupPage.iClickonButton(buttonText);
        appliEyes.capture("Button '"+buttonText+"' clicked successfully.");
    }


    //****************** RAJNI CODE END HERE*****************************//
    //*******************************************************************///


    @Then("^I should be taken to audience channel page$")
    public void iShouldBeTakenToAudienceChannelPage() {
    	newPlanSetupPage.verifyPageTitle("Channel");
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
    	newPlanSetupPage.iClickNextButton(buttonType);
    }

    @Then("^I can see the '(.*)' section$")
    public void iCanSeeTheSelected_Channel(String section) throws Exception {
    	newPlanSetupPage.verifyPageTitle(section);
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
