package net.enablers.tvstack.steps;

import com.typesafe.config.Config;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.model.web.PlanModel;
import net.enablers.tvstack.pages.AdminHomePage;
import net.enablers.tvstack.pages.HomePage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.enablers.tvstack.utilities.ConfigLoader;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TvstackLandingPageSteps extends ScenarioSteps {

    AdminHomePage adminHomePage;
    NewPlanSetupPage newPlanSetupPage;
    HomePage homePage;
    @Steps
    NewPlanSetupPageSteps planSetupPageSteps;
    public AppliEyes appliEyes;
    private static Config conf = ConfigLoader.load();
    private String DEFAULT_MARKET = "United Kingdom";
    private String DEFAULT_CLIENT = "Monsoon Accessorise";


    @Given("^I login with '(.*)'$")
    public void iLoginWith(String username) {
        if (conf.getBoolean("appli_eyes")) {
            appliEyes.load(conf.getString("webdriver.base.url"));
        } else {
            homePage.openUrl(conf.getString("webdriver.base.url") + "logout");
            homePage.openUrl(conf.getString("webdriver.base.url"));
        }
        adminHomePage.loginAs(username);
        homePage.verifyPageHeaderIsCorrect();
        appliEyes.capture("Landed on landing page");
    }


    @Then("^I should see the option to select the market$")
    public void iShoudSeeTheOptionToSelectTheMarket() {
        homePage.verifyMarketSelectionIsPresent();
    }

    @When("^I select '(.*)' market$")
    public void iSelectTheMarket(String arg0) {
        homePage.selectMarket(arg0);
    }

    @Then("^I should be presented with option to select a client$")
    public void iShouldBePresentedWithOptionToSelectAClient() {
        homePage.verifyClientSelectionIsPresent();
    }

    @When("^I select the client '(.*)'$")
    public void iSelectTheClient(String arg0) {
        homePage.selectClient(arg0);
    }

    @Then("^I should be presented with existing plans section$")
    public void iShouldBePresentedWithExistingPlansSection() {
        homePage.verifyExistingPlansSectionPresent();
    }

    @Then("^I should see the option to create a new plan$")
    public void iShouldSeeTheOptionToCreateANewPlan() {
        homePage.verifyCreateNewPlanOptionPresent();
    }

    @Then("^I should see Plan name, Owner, Date Created fields under existing plans$")
    public void iShouldSeePlanNameOwnerDateCreatedFieldsUnderExistingPlans() {
        homePage.checkExistingPlansFields();
    }

    @Then("^I should see options to 'copy', 'edit', 'delete' and 'launch' under any existing plans$")
    public void iShouldSeeOptionsToCopyEditDeleteAndLaunchUnderEachPlan() {
        homePage.verifyManagePlansOptionsExist();
    }

    @When("^I click on create new plan$")
    public void iClickOnCreateNewPlan() {
        appliEyes.capture("Selected market and clients and now going to click new plan button");
        homePage.createNewPlan();
    }

    @Given("^there is an existing plan$")
    public void thereIsAnExistingPlan() {
        selectDefaultMarketAndClient();
        homePage.verifyAtleastOnePlanExist();
    }

    @When("^I click on edit button on existing plan$")
    public void iClickOnEditButtonOnExistingPlan() {
        homePage.editAnExistingPlan();
    }

    @Given("^a user plans to use an existing plan name for a new plan$")
    public void aUserPlansToUseAnExistingPlanNameForANewPlan() {
        selectDefaultMarketAndClient();
        Serenity.setSessionVariable("Existing plan name").to(homePage.storeFirstPlansName());
        this.iClickOnCreateNewPlan();
    }

    public void selectDefaultMarketAndClient() {
        this.iLoginWith("Tvstack.user2@dentsuaegis.com");
        Serenity.setSessionVariable("user").to("Tvstack.user2@dentsuaegis.com");
        this.iSelectTheMarket(DEFAULT_MARKET);
        this.iSelectTheClient(DEFAULT_CLIENT);
    }

    @Step
    @Given("^user has chosen to create new plan$")
    public void userHasChosenToCreateNewPlan() throws Throwable {
        selectDefaultMarketAndClient();
        iClickOnCreateNewPlan();
    }


    @Given("^A new plan is there on landing page$")
    public void createAPlan() throws Throwable {

        this.selectDefaultMarketAndClient();
        homePage.createNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        PlanModel plan = newPlanSetupPage.clickOnLogo();
        waitABit(5000);
       }

    @Then("^I marked the plan as definitive$")
    public void markPlanAsDefinitive() throws Throwable {
        homePage.clickOnDefinitiveButton(Serenity.sessionVariableCalled("planName"));
    }

    @And("^I checked the marked plan is definitive$")
    public void verifyDefinitiveButtonIsShown() throws Throwable {

        homePage.verifyDefinitiveButtonIsShown(Serenity.sessionVariableCalled("planName"));

    }

    @Then("^I marked the plan as not definitive$")

    public void markPlanAsNotDefinitive() throws Throwable {

        homePage.clickOnRmoveDefinitiveButton(Serenity.sessionVariableCalled("planName"));
        waitABit(5000);

    }

    @And("^I checked marked plan is not definitive$")

    public void seePlanIsMarkedAsNotDefinitive() throws Throwable {

        homePage.verifyDefinitiveBadgeIsRemoved(Serenity.sessionVariableCalled("planName"));
        waitABit(5000);
    }

    //Scenario: Set definitive from Edit page

    @When("^I click on edit button for a plan$")
    public void clickOnEditButton() throws Throwable {
        homePage.clickOnEditButton(Serenity.sessionVariableCalled("planName"));
    }

    @And("^I mark the plan as definitive on edit page$")
    public void selectTheCheckbox() throws Throwable {
        homePage.clickSetAsDefinitiveCheckBox();
        homePage.clickUpdateButton();
        PlanModel plan = newPlanSetupPage.clickOnLogo();
    }


   // Scenario: Sort plans on landing page


    @Given("^A Plan is there on landing page with marked as definitive$")
    public void aPlanIsThereOnLandingPageWithMarkedAsDefinitive() throws Throwable {
        this.selectDefaultMarketAndClient();
        homePage.createNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        newPlanSetupPage.clickOnLogo();
        waitABit(5000);
        homePage.clickOnDefinitiveButton(Serenity.sessionVariableCalled("planName"));
        waitABit(10000);
    }


    @Then("^I should see the definitive plan on top of the list$")
    public void iShouldSeeTheDefinitivePlanOnTopOfTheList() throws Throwable {

        homePage.verifyFirstPlanAfterSort(Serenity.sessionVariableCalled("planName"));
        waitABit(3000);

        homePage.clickOnRmoveDefinitiveButton(Serenity.sessionVariableCalled("planName"));
        waitABit(2000);
    }

    @When("^I change the sorting to newest first$")
    public void iChangeTheSortingToNewestFirst() throws Throwable {

        homePage.createNewPlan();
        planSetupPageSteps.iAddPlanDetailsAndClickNextButton();
        PlanModel plan = newPlanSetupPage.clickOnLogo();
        waitABit(5000);
        homePage.setSelectSortBox();
        homePage.setSelectSortOptionNewest();
    }

    @Then("^I should see the plan which is newly created on the top of list$")
    public void iShouldSeeThePlanWhichIsNewlyCreatedOnTheTopOfList() throws Throwable {
        homePage.verifyFirstPlanAfterSort(Serenity.sessionVariableCalled("planName"));
    }

    @When("^I change the sorting to oldest first$")
    public void iChangeTheSortingToOldestFirst() throws Throwable {
        homePage.setSelectSortBox();
        homePage.setSelectSortOptionOldest();
    }

    @Then("^I should not be able to see the newest plan first$")
    public void iShouldNotBeAbleToSeeTheNewestPlanFirst() throws Throwable {

        homePage.verifyFirstPlanAfterSortOldestFirst(Serenity.sessionVariableCalled("planName"));

    }
}
