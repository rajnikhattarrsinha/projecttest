package net.enablers.tvstack.steps;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.pages.ChannelSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ChannelSetupPageSteps {
	
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

    @Then("^I select Closest buying Audience as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectClosestbuyingAudienceOfChannel(String buyingAudience,String channelName) throws Throwable
    {
        channelSetupPage.selectClosestbuyingAudienceoption(channelName,buyingAudience);
        appliEyes.capture("Closest buying Audience option '"+buyingAudience+"' successfully selected for Channel '"+channelName+"'.");
    }

    @Then("^I select Second Length/Format as '(.*)' corresponding to Channel '(.*)'")
    public void iSelectSecondLengthFormatOfChannel(String secondlengthFormatoption,String channelName) throws Throwable
    {
        channelSetupPage.selectSecondLengthFormatoption(channelName,secondlengthFormatoption);
        appliEyes.capture("Second Length format option '"+secondlengthFormatoption+"' successfully selected for Channel '"+channelName+"'.");
    }

    @Then("^I should see '(.*)' populated in CPM textbox corresponding to Channel '(.*)'")
    public void iShouldSeePopulatedCPMtextboxValueOfChannel(String userCPMInput,String channelName) throws Throwable
    {
        channelSetupPage.getCPMValueOfChannelAndVerifyWithUserCPMvalue(channelName,userCPMInput);
        appliEyes.capture("Expected Channel '"+channelName+"' CPM  value '"+userCPMInput+"' is displayed.");
    }

    @Then("^I should see GRPs Celebrated at textbox populated as '(.*)'")
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
        appliEyes.capture("'Scenarios' page is displayed with 'Create new scenario' button.");
    }

    //########################################################################################################
    //# Scenario ID : 3
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

    @Given("^I select Age as '(.*)' corresponding to Channel 'Youtube'$")
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
    public void iShouldSeeNonEditableCPMtextboxCorrespondingToChannel(String channelName) throws Throwable
    {
        channelSetupPage.verifyNoneditableCPMtextbox(channelName);
        appliEyes.capture("CPM textbox is non editable for Channel '"+channelName+".");
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

    //########################################################################################################
    //# Scenario ID : 5
    //# Test Case Overwriting CPM for Channels TV,VideoOnDemand and Online+Video and Saving for Plan
    //#------------------------------------------------------------------------------------------------------
    //# Description: This test case verify user is able to override CPM value of channel
    //#------------------------------------------------------------------------------------------------------
    //# Pre-conditions: NA
    //# Post-conditions: NA
    //# Limitations: NA
    //#------------------------------------------------------------------------------------------------------
    //# Owner:  Rajni
    //# Created on: 01-Aug-2018
    //#------------------------------------------------------------------------------------------------------
    //# Reviewer:
    //# Review Date:
    //#------------------------------------------------------------------------------------------------------
    //# History Notes:
    //########################################################################################################

    @Then("^I should be able to enter value '(.*)' in CPM textbox corresponding to Channel '(.*)'")
    public void iWillEnterCPMValueCorrespondingToChannel(String cpmValue,String channelName) throws Throwable
    {
        channelSetupPage.enterCPMValueCorrespondingToChannel(channelName,cpmValue);
        appliEyes.capture("CPM value '"+cpmValue+"' enter successfully for Channel '"+channelName+".");
    }

    @Then("^I select newly created scenario A from scenario A listbox$")
    public void iWillSelectScenarioA() throws Throwable
    {
        String scenarioA = Serenity.sessionVariableCalled("new_audience_name")+"-scenario1";
        channelSetupPage.selectScenarioAValue(scenarioA);
        appliEyes.capture("'"+scenarioA+"' selected from Scenario A listbox");
    }

    @Then("^I select newly created scenario B  from scenario B listbox$")
    public void iWillSelectScenarioB() throws Throwable
    {
        String scenarioB = Serenity.sessionVariableCalled("new_audience_name")+"-scenario2";
        channelSetupPage.selectScenarioBValue(scenarioB);
        appliEyes.capture("'"+scenarioB+"' selected from Scenario B listbox");
    }

    @Then("^I select created plan from Planning Audience listbox$")
    public void iWillSelectPlanningAudience() throws Throwable
    {
        String PlanningAudience = Serenity.sessionVariableCalled("new_audience_name");
        channelSetupPage.selectPlanningAudienceValue(PlanningAudience);
        appliEyes.capture("'"+PlanningAudience+"' selected from Planning Audience listbox");// Write code here that turns the phrase above into concrete actions
    }

    @Then("^I click on '(.*)' from header breadcrumb$")
    public void iClickOnChannelLinkFromBreadcrumb(String breadcrumbChannel) throws Throwable
    {
        channelSetupPage.clickonBreadCrumbLink(breadcrumbChannel);
        appliEyes.capture("Link 'Edit' along with Plan name clicked successfully.");
    }

    @Then("^I navigate till (.*) set up page$")
    public void iNavigateChannelsSetUpPage(String PageHeader) throws Throwable
    {
        channelSetupPage.verifyPageHeader(PageHeader);
        appliEyes.capture("'"+PageHeader+"'page is displayed.");
    }

    @Then("^I click on '(.*)' button$")
    public void iWillClickonanyButton(String buttonText) throws Throwable
    {
        channelSetupPage.iWillClickonButton(buttonText);
        appliEyes.capture("Button '"+buttonText+"' clicked successfully.");
    }

    @Then("^I should see the newly created scenario '(.*)' on Scenarios page$")
    public void iWillVerifyNewCreatedScenarioOnScenarioPage(String scenarioNumber) throws Throwable
    {
        channelSetupPage.verifyNewlyCreatedScenario(scenarioNumber);
        appliEyes.capture("New Created Scenario id displayed on Scenario page");
    }


    //****************** RAJNI CODE END HERE*****************************//
    //*******************************************************************///
    
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
