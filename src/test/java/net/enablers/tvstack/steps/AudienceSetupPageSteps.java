package net.enablers.tvstack.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AudienceSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class AudienceSetupPageSteps {
	
    AudienceSetupPage audienceSetupPage;
    AppliEyes appliEyes;

    @Steps
    TvstackLandingPageSteps landingPageSteps;

    @Steps
    NewPlanSetupPageSteps planSetupPageSteps;

    @Then("^I should be taken to Audience Home page$")
    public void iShouldBeTakenToAudienceHomePage() throws Exception {
    	audienceSetupPage.verifyHomePageTitle();
        appliEyes.capture("Landed on audience home page");
    }
    
    @Then("^I should be taken to Audience Setup page$")
    public void iShouldBeTakenToAudienceSetupPage() {
        audienceSetupPage.verifyPageTitle();
        appliEyes.capture("Landed on audience setup page");
    }

    @Given("^I'm on audience setup page$")
    public void iMOnAudienceSetupPage() throws Throwable {
        landingPageSteps.userHasChosenToCreateNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        this.iShouldBeTakenToAudienceSetupPage();
    }

    @Then("^I should see option to create new audience$")
    public void iShouldSeeOptionToCreateNewAudience() throws Throwable {
        audienceSetupPage.checkForNewAudienceButton();
    }

    @And("^I should see existing audiences$")
    public void iShouldSeeExistingAudiences() throws Throwable {
        audienceSetupPage.verifyExistingAudienceSectionPresent();
    }

    @And("^I should see all the options against each existing audience$")
    public void iShouldSeeAllTheOptionsAgainstEachExistingAudience() throws Throwable {
        audienceSetupPage.checkExistingAudienceFields();
    }

    @When("^I click on create new audience$")
    public void iClickOnCreateNewAudience() throws Throwable {
        audienceSetupPage.createNewAudience();
    }

    @Then("^I should be presented with planning audience pop up over screen$")
    public void iShouldBePresentedWithPlanningAudiencePopUpOverScreen() throws Throwable {
        audienceSetupPage.checkAudienceBuilderLayout();
    }

    @When("^I create a new query group based on gender$")
    public void iCreateANewQueryGroupBasedOnGender() throws Throwable {
        audienceSetupPage.createAQueryOnGender();
    }

    @And("^save the audience$")
    public void saveTheAudience() throws Throwable {
        audienceSetupPage.saveTheAudience();
    }

    @Then("^the audience is saved successfully$")
    public void theAudienceIsSavedSuccessfully() throws Throwable {
        audienceSetupPage.audienceIsSavedSuccessfully();
    }

    @When("^I click on delete audience$")
    public void iClickOnDeleteAudience() throws Throwable {
        audienceSetupPage.clickOnDeleteAudience();
    }

    @Then("^the audience is deleted successfully$")
    public void theAudienceIsDeletedSuccessfully() throws Throwable {
        audienceSetupPage.checkAudienceDeletedSuccessfully();
    }

    @When("^I create new audience and save$")
    public void iCreateNewAudienceAndSave() throws Throwable {
        audienceSetupPage.createAQueryOnGender();
        audienceSetupPage.saveTheAudience();
    }

    //------------------------------------------------------------------
    //Scenario: Copy existing audience


    @Given("^I create a new audience$")
    public void iCreateANewAudience() throws Throwable {
        landingPageSteps.userHasChosenToCreateNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        this.iShouldBeTakenToAudienceSetupPage();
        audienceSetupPage.createNewAudience();
        audienceSetupPage.checkAudienceBuilderLayout();
        audienceSetupPage.createAQueryOnGender();
        audienceSetupPage.saveTheAudience();
        audienceSetupPage.audienceIsSavedSuccessfully();
           }

    @Then("^I clone the Audience$")
    public void iClickOnCopyAudienceButton() throws Throwable {
        audienceSetupPage.clickOnCopyAudience();
        audienceSetupPage.ClonedAudience();
        audienceSetupPage.clickSaveCopiedAudience();


    }

    @And("^I verify that the clone audience is present on the list$")
    public void iVerifyThatTheCloneAudienceIsPresentOnTheList() throws Throwable {
    audienceSetupPage.verifyClonedAudienceIsPresent();
    }


    //Scenario: Edit Audience

    @Then("^I click on Edit Audience$")
    public void iClickOnEditAudience() throws Throwable {
        audienceSetupPage.clickEditAudienceBtn();

    }
    //Scenario: Edit Audience
    @Then("^I click on Edit Audience and edit the Audience name$")
    public void iClickOnEditAudienceAndEditTheAudienceName() throws Throwable {
        audienceSetupPage.clickEditAudienceBtn();
        audienceSetupPage.clickNewGroup();
        audienceSetupPage.AddNewQuery();
        audienceSetupPage.editAudienceName();
        audienceSetupPage.saveTheAudience();
    }

    @And("^I verify that the edited audience is present on the list$")
    public void iVerifyThatTheEditedAudienceIsPresentOnTheList() throws Throwable {
        audienceSetupPage.verifyEditedAudienceIsPresent();
    }


    //Scenario: Preview Audience

    @And ("^I verify the Preview details$")
    public void iVerifyTheDetails() {
        audienceSetupPage.verifyPreviewText();
    }
}
