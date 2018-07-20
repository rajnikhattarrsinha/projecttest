package net.enablers.tvstack.steps;

import com.typesafe.config.Config;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AdminHomePage;
import net.enablers.tvstack.pages.PlannerAdminPage;
import net.enablers.tvstack.utilities.ConfigLoader;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class AdminSteps {
	
    AdminHomePage adminHomePage;
    PlannerAdminPage plannerAdminPage;
    private static Config conf = ConfigLoader.load();
    
    @Steps
    AudienceSetupPageSteps planSetupPageSteps;
    
    @Steps
    PlannerAdminSteps plannerAdminSteps;

    @When("^I login as '(.*)' user with '(.*)'$")
    public void iLoginAsTypeAdmin(String role, String username) throws Throwable {
        //Logout to make sure every scenario is clean
        adminHomePage.openUrl(conf.getString("admin_url") + "/logout");
        adminHomePage.openUrl(conf.getString("admin_url"));
        Serenity.setSessionVariable("role").to(role);
        adminHomePage.loginAs(username);
        if (Serenity.sessionVariableCalled("role").equals("client lead")) {
        	plannerAdminPage.signInAsClientLead();
		} else if (Serenity.sessionVariableCalled("role").equals("global admin")) {
			plannerAdminPage.signInAsGlobalLead();
		} 
    }

    @Then("^I should be able to login successfully and should see stuff as per my role assignment$")
    public void iShouldBeAbleToLoginSuccessfullyAndShouldSeeStuffAsPerMyRoleAssignment() throws Throwable {
        adminHomePage.checkIfLoginSuccessful(Serenity.sessionVariableCalled("role"));
    }

    @And("^I should be able to logout successfully$")
    public void iShouldBeAbleToLogoutSuccessfully() throws Throwable {
        adminHomePage.logOut();
    }

    @Then("^I should see any existing market leads$")
    public void iShouldSeeAnyExistingMarketLeads() throws Throwable {
        adminHomePage.checkForExistingMarketLeads();
    }

    @And("^I should see option to logout$")
    public void iShouldSeeOptionToLogout() throws Throwable {
        adminHomePage.logOutButtonIsPresent();
    }

    @Then("^I should see option to add a market lead user$")
    public void iShouldSeeOptionToAddMarketLeadUser() throws Exception {
        adminHomePage.addMarketLeadUserButtonIsPresent();
    }

    @When("^I add '(.*)' having '(.*)' as a market$")
    public void iAddUser(String user, String market) throws Exception { 
    	adminHomePage.removeTestUserBeforeRunning(user);
        adminHomePage.addLeadUser(user, market);
        Serenity.setSessionVariable("user").to(user);
        Serenity.setSessionVariable("market").to(market);
    }

    @When("^I add the same user again$")
    public void iAddTheSameUserAgain() throws Exception {
        adminHomePage.addLeadUser(Serenity.sessionVariableCalled("user"), Serenity.sessionVariableCalled("market"));
    }

    @Then("^the lead user is properly displayed in the market leads table$")
    public void userWasAddedSuccessfully() throws Exception {
        adminHomePage.checkUserWasAdded(Serenity.sessionVariableCalled("user"));
    }

    @Then("^a message is displayed saying the user already exists$")
    public void messageSayingUserAlreadyExists() throws Exception {
        adminHomePage.checkErrorBanner();
    }

    @Then("^a message is displayed saying the user was successfully added$")
    public void messageSayingUserWasCreated() throws Exception {
        adminHomePage.checkSuccessBanner();
    }

    @Then("^finally I should be able to remove the user successfully$")
    public void removePreviouslyAddedUserSuccessfully() throws Exception {
        adminHomePage.removeLeadUser(Serenity.sessionVariableCalled("user"));
    }

    @When("^a user who has no roles assigned logs in to admin site$")
    public void aUserWhoHasNoRolesAssignedLogsInToAdminSite() throws Throwable {
        iLoginAsTypeAdmin("unauthorised", "tvstack.user1@dentsuaegis.com");
    }

    @Then("^user should be presented with permission denied message$")
    public void userShouldBePresentedWithPermissionDeniedMessage() throws Throwable {
        adminHomePage.shouldThrowPermissionDeniedMessage();
    }

    @Then("^I should see view categories 'Television', 'General', 'Broadcasters'$")
    public void iShouldSeeViewCategoriesTelevisionGeneralBroadcasters() throws Throwable {
        adminHomePage.checkIfLoginSuccessful(Serenity.sessionVariableCalled("role"));
    }
}
