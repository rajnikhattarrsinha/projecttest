package net.enablers.tvstack.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.model.web.PlanModel;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.pages.HomePage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class NewPlanSetupPageSteps extends ScenarioSteps {

    NewPlanSetupPage newPlanSetupPage;
    AudienceSetupPage audienceSetupPage;
    HomePage homePage;
    AppliEyes appliEyes;

    @Then("^I should be taken to Create new plan page$")
    public void iShouldBeTakenToNewPlanSetupPage() {
        newPlanSetupPage.verifyPageTitle();
        appliEyes.capture("Landed on new plan setup page");
    }
    
    @Then("^I should be taken to Edit plan page$")
    public void iShouldBeTakenToEditPlanSetupPage() {
        newPlanSetupPage.verifyEditPageTitle();
        appliEyes.capture("Landed on edit plan setup page");
    }

    @Then("^I should be on 'Plan setup' tab$")
    public void iShouldBeOnPlanSetupTab() {
        newPlanSetupPage.verifyPlanSetupSelected();
    }

    @And("^I should have option to provide 'Plan name', 'Total video budget', 'Campaign length'$")
    public void iShouldHaveOptionToProvidePlanNameTotalVideoBudgetCampaignLength() {
        newPlanSetupPage.verifyFieldsPresent();
    }

    @And("^I should see '(.*)' button and it should be disabled$")
    public void iShouldSeeNextButtonAndItShouldBeDisabled(String arg0) {
        newPlanSetupPage.verifyButtonIsDisabled(arg0);
    }

    @And("^I should see '(.*)' button and it should be enabled$")
    public void iShouldSeeBackButtonAndItShouldBeEnabled(String arg0) {
        newPlanSetupPage.verifyButtonIsEnabled(arg0);
    }

    @And("^I should see tabs 'Audience Setup', 'Channel selection', 'Build scenario', 'Compare scenario'$")
    public void iShouldSeeTabsAudienceSetupChannelSelectionBuildScenarioCompareScenario() {
        newPlanSetupPage.verifyTabs();
    }

    @Step
    @When("^I add plan details and click Next button$")
    public void iAddPlanDetailsAndClickNextButton() {
        newPlanSetupPage.submitNewPlan();
    }

    @And("^the plan is saved successfully$")
    public void thePlanIsSavedSuccessfullySoFar() {
        PlanModel plan = newPlanSetupPage.clickOnLogo();
        homePage.verifyNewPlanIsShown(plan.getPlanName());
    }

    @When("^I edit plan details and click on update$")
    public void iEditPlanDetailsAndClickOnUpdate() {
        newPlanSetupPage.editExistingPlan();
    }

    @When("^user enters the exiting plan name$")
    public void userEntersTheExitingPlanName() {
        newPlanSetupPage.enterPlanName(Serenity.sessionVariableCalled("Existing plan name"));
    }

    @Then("^error should be displayed$")
    public void errorShouldBeDisplayed() {
        newPlanSetupPage.verifyError();
    }
    
    @When("^I create a new plan$")
    public void iCreateANewPlan() throws Exception {
        homePage.createNewPlan();
    	newPlanSetupPage.submitNewPlan();
    	this.thePlanIsSavedSuccessfullySoFar();
    }

    @Then("^the plan is deleted$")
    public void thePlanIsDeleted() throws Exception {
    	homePage.deletePlan(Serenity.sessionVariableCalled("planName"));
    }
    
    @Then("^the deleted plan is verified$")
    public void theDeletedPlanIsVerified() throws Exception {
    	homePage.verifyPlanWasDeleted(Serenity.sessionVariableCalled("planName"));
    }
}
