package net.enablers.tvstack.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.pages.AudienceAdminPage;
import net.thucydides.core.annotations.Steps;

public class AudienceAdminSteps {
	
    AudienceAdminPage channelOperationsPage;
    
    @Steps
    AudienceSetupPageSteps planSetupPageSteps;

    @And("^I should see option to rename channel$")
    public void iShouldSeeOptionToRenameChannel() throws Throwable {
    	channelOperationsPage.renameOptionExists();
    }

    @And("^I should see option to create new buying audience$")
    public void iShouldSeeOptionToCreateNewBuyingAudience() throws Throwable {
    	channelOperationsPage.newAudienceOptionExists();
    }

    @When("^I click on create new buying audience$")
    public void iClickOnCreateNewBuyingAudience() throws Throwable {
    	channelOperationsPage.clickNewAudienceButton();
    }

    @Then("^the buying audience is created successfully$")
    public void theBuyingAudienceIsCreatedSuccessfully() throws Throwable {
    	channelOperationsPage.shouldContainSavedAudience();
    }
    
    @When("^I click on the rename channel button$")
    public void iClickTheRenameChannelButton() throws Throwable {
    	channelOperationsPage.clickRenameChannel();
    }
    
    @When("^edit the channel name$")
    public void editChannelName() throws Throwable {
    	channelOperationsPage.editChannelName();
    }
    
    @Then("^the name of the channel is updated$")
    public void checkChannelNameUpdate() throws Throwable {
    	channelOperationsPage.checkChannelNameUpdate();
    }
    
	@Then("^I revert the channel name to the original value$")
	public void iRevertChannelNameToOriginalValue() throws Throwable {
		channelOperationsPage.clickRenameChannel();
		channelOperationsPage.editChannelName();
		channelOperationsPage.checkChannelNameRevertion();
	}
	
	@When("^I select an existing audience$")
	public void iSelectExistingAudience() throws Throwable {
		if (channelOperationsPage.isElementVisible(channelOperationsPage.getAudienceExpandButtons())) {
			channelOperationsPage.selectExistingAudience();
		} else {
			channelOperationsPage.clickNewAudienceButton();
			planSetupPageSteps.iCreateNewAudienceAndSave();	
			channelOperationsPage.selectExistingAudience();
		}		
	}
	
	@Then("^I check that the buying audience format is displayed$")
	public void iCheckThatTheBuyingAudienceFormatIsDisplayed() throws Throwable {
		channelOperationsPage.checkBuyingAudienceFormat();
	}
	
	@Then("^the add new format button is displayed$")
	public void addNewFormatButtonIsDisplayed() throws Throwable {
		channelOperationsPage.checkAddNewFormatButton();
	}
	
	@When("^I create a new format$")
	public void iCreateNewFormat() throws Throwable {
		channelOperationsPage.selectExistingAudience();
		channelOperationsPage.addNewFormat();
	}
	
	@Then("^I verify the format in the buying audience$")
	public void iVerifyTheBuyingAudienceFormat() throws Throwable {
		channelOperationsPage.checkNewFormat();
	}
	
	@Then("^I click on duplicate button$")
	public void iClickDuplicateButton() throws Exception {
		channelOperationsPage.checkExistingFormatValues();
		channelOperationsPage.duplicateAudience();
	}

	@Then("^the buying audience was duplicated$")
	public void theBuyingAudienceWasDuplicated() throws Exception {
		channelOperationsPage.checkFormatValues();
	}
	
	@Then("^I click on edit button$")
	public void iClickEditButton() throws Exception {
		channelOperationsPage.checkExistingFormatValues();
		channelOperationsPage.editAudience();
	}

	@Then("^the buying audience was edited$")
	public void theBuyingAudienceWasEdited() throws Exception {
		channelOperationsPage.checkAudienceAfterEdit();
	}
	
	@When("^I click on delete button$")
	public void iClickOnDeleteButton() throws Throwable {
		channelOperationsPage.selectExistingAudience();
		channelOperationsPage.deleteAudience();
	}


	@Then("^the buying audience was deleted$")
	public void theBuyingAudienceWasDeleted() throws Exception {
		channelOperationsPage.checkAudienceWasDeleted();
	}
}
