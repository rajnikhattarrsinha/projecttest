package net.enablers.tvstack.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.PlannerAdminPage;
import net.serenitybdd.core.Serenity;

public class PlannerAdminSteps {
	
    PlannerAdminPage plannerAdminPage;
    
    @When("^I select '(.*)' for a client$")
    public void iSelectAClient(String client) throws Exception {
    	plannerAdminPage.selectClient(client);
    }

    @Then("^I see the client leads and planners$")
    public void iSeeTheClientLeadsAndPlanners() throws Exception {
    	plannerAdminPage.verifyLeadsAndPlannersSections();
    }
    
    @When("^I add '(.*)' as a planner$")
    public void iAddUserAsPlanner(String user) throws Exception {
    	plannerAdminPage.removeTestPlannerBeforeRunning(user);
    	plannerAdminPage.addPlanner(user);
        Serenity.setSessionVariable("user").to(user);
    }


    @Then("^the planner is properly displayed in the client planners table$")
    public void thePlannerIsProperlyDisplayed() throws Exception {
    	plannerAdminPage.checkPlannerWasAdded(Serenity.sessionVariableCalled("user"));
    }

    @When("^I add the same planner again$")
    public void iAddTheSamePlannerAgain() throws Exception {
    	plannerAdminPage.addPlanner(Serenity.sessionVariableCalled("user"));
    }

    @Then("^finally I should be able to remove the planner successfully$")
    public void removeThePlannerSuccessfully() throws Exception {
    	plannerAdminPage.removePlanner(Serenity.sessionVariableCalled("user"));
    }
}
