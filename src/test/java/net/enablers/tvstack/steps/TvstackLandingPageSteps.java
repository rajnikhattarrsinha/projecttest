package net.enablers.tvstack.steps;

import com.typesafe.config.Config;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AdminHomePage;
import net.enablers.tvstack.pages.HomePage;
import net.enablers.tvstack.pages.NewPlanSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;
import net.enablers.tvstack.utilities.ConfigLoader;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class TvstackLandingPageSteps extends ScenarioSteps {

    AdminHomePage adminHomePage;
    NewPlanSetupPage newPlanSetupPage;
    HomePage homePage;
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

    public void selectDefaultMarketAndClient(){
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
}
