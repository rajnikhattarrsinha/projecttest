package net.enablers.tvstack.pages;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PlannerAdminPage extends PageObject {

	@FindBy(xpath = "//button[span/span/strong[contains(text(), 'Client')]]")
    WebElementFacade signInAsClientButton;
	
	@FindBy(xpath = "//button[span/span/strong[contains(text(), 'Global')]]")
    WebElementFacade signInAsGlobalButton;
	
	@FindBy(xpath = "//button[span/span/strong[contains(text(), 'Market')]]")
    WebElementFacade signInAsMarketButton;
	
	@FindBy(xpath = "//p[@class = 'Polaris-Heading']")
    WebElementFacade viewLeadsAndPlanners;
	
	@FindBy(xpath = "//div[@class='Polaris-FormLayout__Item']//input")
    WebElementFacade clientDropdown;
	
	@FindBy(xpath = "//div[@class='Polaris-Card__Section']")
    List <WebElementFacade> leadsAndPlannersSections;
	
	@FindBy(xpath = "//div[not(h2)]/button/span/span")
    WebElementFacade addPlannerButton;
	
	AdminHomePage adminHomePage = new AdminHomePage();
	
	public void signInAsClientLead() {
		signInAsClientButton.waitUntilClickable().then().click();		
	}
	
	public void signInAsGlobalLead() {
		signInAsGlobalButton.waitUntilClickable().then().click();		
	}
	
	public void signInAsMarketLead() {
		signInAsMarketButton.waitUntilClickable().then().click();		
	}

	public void selectClient(String client) {
		clientDropdown.waitUntilEnabled().clear();
		waitABit(500);
		clientDropdown.waitUntilEnabled().typeAndTab(client);
        waitABit(500);
	}
	
	public void verifyLeadsAndPlannersSections() {
		for (int i = 0; i < leadsAndPlannersSections.size(); i++) {
			leadsAndPlannersSections.get(i).shouldBeCurrentlyVisible();
		}
		assertThat(leadsAndPlannersSections.size()==2);
	}

	public void removeTestPlannerBeforeRunning(String user) {
        if (find(adminHomePage.getRemoveAccessButton(user)).withTimeoutOf(10, TimeUnit.SECONDS).isCurrentlyVisible()) {
            find(adminHomePage.getRemoveAccessButton(user)).waitUntilVisible().click();
            adminHomePage.deleteUserModalButton.waitUntilVisible().click();
        }
	}

	public void addPlanner(String user) {		
		addPlannerButton.waitUntilClickable().click();
    	adminHomePage.userDropdown.waitUntilEnabled().typeAndEnter(user);
        waitABit(2000);
        adminHomePage.userDropdown.sendKeys(Keys.ENTER);
        waitABit(500);
        adminHomePage.addUserModalButton.waitUntilClickable().then().click();
	}

	public void checkPlannerWasAdded(String user) {
		adminHomePage.closeAddUserModalButton.waitUntilClickable().then().click();
		addPlannerButton.waitUntilVisible();

        assertThat(find(adminHomePage.getRemoveAccessButton(user)).isDisplayed());
	}

	public void removePlanner(String user) {
		checkPlannerWasAdded(user);
        find(adminHomePage.getRemoveAccessButton(user)).waitUntilVisible().click();
        adminHomePage.deleteUserModalButton.waitUntilVisible().click();

        assertThat(!find(adminHomePage.getRemoveAccessButton(user)).isCurrentlyVisible());
	}
}

