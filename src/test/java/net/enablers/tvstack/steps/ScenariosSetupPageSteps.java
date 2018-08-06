package net.enablers.tvstack.steps;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.pages.ChannelSetupPage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.pages.ScenariosSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ScenariosSetupPageSteps {
	
	NewPlanSetupPage newPlanSetupPage;
    AudienceSetupPage audienceSetupPage;
    ChannelSetupPage channelSetupPage;
    ScenariosSetupPage scenariosSetupPage;
    AppliEyes appliEyes;

    @Steps
    TvstackLandingPageSteps landingPageSteps;

    @Steps
    NewPlanSetupPageSteps planSetupPageSteps;
    
    @Steps
    AudienceSetupPageSteps audienceSetupPageSteps;
    
    @Then("^I should be taken to scenarios page$")
    public void iShouldBeTakenToScenariosPage() {
    	newPlanSetupPage.verifyPageTitle("Scenarios");
        appliEyes.capture("Landed on audience channel page");
    }
    
    @Given("^I'm on scenarios page$")
    public void iMOnScenariosPage() throws Throwable {
        landingPageSteps.userHasChosenToCreateNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        audienceSetupPageSteps.iShouldBeTakenToAudienceSetupPage();
        audienceSetupPageSteps.iClickOnCreateNewAudience();
        audienceSetupPageSteps.iCreateNewAudienceAndSave();
        channelSetupPage.iClickChannelsButton();
        newPlanSetupPage.iClickNextButton("Next: Scenarios");
        this.iShouldBeTakenToScenariosPage();
    }
    
    @Then("^I should see the scenario setup table contains$")
    public void iShouldSeeTheScenarioSetupTableContainsAllOptions(List<String> options) throws Exception {
    	scenariosSetupPage.verifyScenarioTableHeader(options);
    }
    
    @When("^I add scenarios: (\\d+)$")
    public void iAddScenarios(int noOfScenarios) throws Exception {
    	List<String> scenarios = new ArrayList<String>();
    	for (int i = 0; i < noOfScenarios; i++) {
    		scenariosSetupPage.clickCreateNewScenarioButton();
    		scenarios.add(scenariosSetupPage.getScenarioName());
    		scenariosSetupPage.closeAddNewScenarioPage();
    		scenariosSetupPage.checkNewlyCreatedScenario(scenarios.get(i));
		}
    	Serenity.setSessionVariable("scenarios").to(scenarios);
    }
    
    @When("^I navigate to add a new scenario page$")
    public void iNavigateToAddANewScenarioPage() throws Exception {
    	scenariosSetupPage.clickCreateNewScenarioButton();
    }

    @Then("^the slider, piechart and compare table should be visible$")
    public void theSliderPiechartAndCompareTableAreVsible() throws Exception {
    	scenariosSetupPage.checkScenarioElements();
    }
}
