package net.enablers.tvstack.steps;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.pages.ChannelSetupPage;
import net.enablers.tvstack.pages.ComparisonSetupPage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.pages.ScenariosSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.thucydides.core.annotations.Steps;

public class ComparisonSetupPageSteps {
	
	NewPlanSetupPage newPlanSetupPage;
    AudienceSetupPage audienceSetupPage;
    ChannelSetupPage channelSetupPage;
    ScenariosSetupPage scenariosSetupPage;
    ComparisonSetupPage comparisonSetupPage;
    AppliEyes appliEyes;

    @Steps
    TvstackLandingPageSteps landingPageSteps;

    @Steps
    NewPlanSetupPageSteps planSetupPageSteps;
    
    @Steps
    AudienceSetupPageSteps audienceSetupPageSteps;
    
    @Steps
    ScenariosSetupPageSteps scenariosSetupPageSteps;
    
    @Then("^I should be taken to comparison page$")
    public void iShouldBeTakenToComparisonPage() {
    	newPlanSetupPage.verifyPageTitle("Scenario Comparison");
        appliEyes.capture("Landed on Comparison channel page");
    }
    
    @Given("^I'm on comparison page$")
    public void iMOnScenariosPage() throws Throwable {
        landingPageSteps.userHasChosenToCreateNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        audienceSetupPageSteps.iShouldBeTakenToAudienceSetupPage();
        audienceSetupPageSteps.iClickOnCreateNewAudience();
        audienceSetupPageSteps.iCreateNewAudienceAndSave();
        channelSetupPage.iClickChannelsButton();
        newPlanSetupPage.iClickNextButton("Next: Scenarios");
        scenariosSetupPageSteps.iAddScenarios(2);
        newPlanSetupPage.iClickNextButton("Next: Scenario Comparison");
        this.iShouldBeTakenToComparisonPage();
    }
    
    @Then("^I should be taken to '(.*)' page$")
    public void iShouldBeTakenTo(String section) throws Exception {
    	newPlanSetupPage.verifyPageTitle(section);
    }
    
    @When("^I select previously created scenarios$")
    public void iSelectPreviouslyCreatedScenarios() throws Exception {
    	comparisonSetupPage.selectScenarios();
    }


    @Then("^I should see that the comparison table contains$")
    public void iShouldSeeThatTheComparisonTableContains(List<String> options) throws Exception {
    	comparisonSetupPage.verifyCompareTableHeader(options);
    }

    @Then("^the GRPs and Reach charts should be visible$")
    public void theGRPsAndReachGraphsShouldBeVisible() throws Exception {
    	comparisonSetupPage.chartsShouldBePresent();
    }

    @Then("^'(.*)' button is displayed$")
    public void buttonIsDisplayed(String button) throws Exception {
    	comparisonSetupPage.checkButtonIsDisplayed(button);
    }
}
