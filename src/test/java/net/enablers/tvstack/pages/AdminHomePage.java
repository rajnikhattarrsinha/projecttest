package net.enablers.tvstack.pages;


import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import net.enablers.tvstack.model.api.OktaTokenStorage;
import net.enablers.tvstack.steps.AudienceSetupPageSteps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import java.util.concurrent.TimeUnit;

public class AdminHomePage extends PageObject {

    @FindBy(xpath = "//p[@class]")
    WebElementFacade administrationText;

    @FindBy(xpath = "//button")
    WebElement loginButton;

    @FindBy(xpath = "//header//a[@class = 'header__signout']")
    WebElement logoutButton;

    @FindBy(id = "okta-signin-username")
    WebElement oktaSigninUsername;

    @FindBy(id = "okta-signin-password")
    WebElement oktaSigninPassword;

    @FindBy(id = "okta-signin-submit")
    WebElement oktaSigninSubmit;

    @FindBy(xpath = "//h1[contains(.,'Market Leads')]")
    WebElementFacade globalAdminPageHeader;

    @FindBy(xpath = "//div[contains(@class,'MainContent')]/div[not(contains(@class,'TitleAndActions'))]/button")
    WebElementFacade addUserButton;

    @FindBy(xpath = "//*[@class='Polaris-Card']//table")
    WebElementFacade existingUsersSection;

    @FindBy(xpath = "//section//div[@class='Polaris-Card__Section'][div/div/h2[text() = 'Market']]//input[not(contains(@type, 'hidden'))]")
    WebElementFacade marketDropdown;

    @FindBy(xpath = "//section//div[@class='Polaris-Card__Section'][div/div/h2[text() = 'User']]//input[not(contains(@type, 'hidden'))]")
    WebElementFacade userDropdown;

    @FindBy(xpath = "//section//button//span[text() = 'Add user']")
    WebElementFacade addUserModalButton;

    @FindBy(xpath = "//button[contains(@class, 'Modal')]")
    WebElementFacade closeAddUserModalButton;

    @FindBy(xpath = "//button[span/span[text() = 'Next']]")
    WebElementFacade nextButton;

    @FindBy(xpath = "//div[contains(@class, 'statusCritical')]//p")
    WebElementFacade errorBanner;

    @FindBy(xpath = "//div[contains(@class, 'statusSuccess')]//p")
    WebElementFacade successBanner;

    @FindBy(xpath = "//div[contains(@class, 'Modal')]//button[contains(@class, 'primary')]")
    WebElementFacade deleteUserModalButton;

    @FindBy(xpath = "//button[@id='TV']")
    WebElementFacade tvChannel;

    @FindBy(xpath = "//button[@id='VOD']")
    WebElementFacade vodChannel;

    @FindBy(xpath = "//button[@id='OnlineVideo']")
    WebElementFacade ovChannel;

    @FindBy(xpath = "//button[@tabindex = 0]")
    WebElementFacade firstIndexChannel;

    AudienceSetupPageSteps audienceSetupPage;
    PlannerAdminPage plannerAdminPage;

    @FindBy(xpath = "//*[@class='Polaris-Card']")
    WebElementFacade bodyContainer;

    public By getRemoveAccessButton(String name) {
        return By.xpath("//tbody//tr//td[contains(text(),  '" + name + "')]/..//a");
    }

    public By getAudienceExpandButtons() {
        return By.xpath("//button[span/span[@class='Polaris-Button__Icon']]");
    }

    public By getBuyingAudienceByName(String audienceName) {
        return By.xpath("//button[span/span[text() = '" + audienceName + "']]");
    }

    //This method to get Okta Token for API calls
    public String signInAndGetOktaTokenStorage(String user) {
        administrationText.shouldBePresent();
        loginButton.click();
        waitABit(2000);
        oktaSigninUsername.sendKeys(user);
        oktaSigninPassword.sendKeys("Great123");
        oktaSigninSubmit.click();
        waitABit(2000);

        return (String) evaluateJavascript(String.format("return window.localStorage.getItem('%s');", "okta-token-storage"));
    }

    public void loginAs(String username) {
        administrationText.waitUntilVisible().shouldBePresent();
        loginButton.click();
        waitABit(2000);
        oktaSigninUsername.sendKeys(username);
        oktaSigninPassword.sendKeys("Great123");
        oktaSigninSubmit.click();
        waitABit(2000);
    }

    public void checkIfLoginSuccessful(String role) {
        if (role.equalsIgnoreCase("global admin")) {
            globalAdminPageHeader.withTimeoutOf(10, TimeUnit.SECONDS).waitUntilVisible().shouldBePresent();
            addUserButton.waitUntilVisible().shouldBePresent();
        } else if (role.equalsIgnoreCase("market lead")) {
            Serenity.setSessionVariable("originalChannel").to(firstIndexChannel.waitUntilVisible().getText());
            tvChannel.waitUntilVisible().shouldBePresent();
            vodChannel.waitUntilVisible().shouldBePresent();
            ovChannel.waitUntilVisible().shouldBePresent();
        } else {
            plannerAdminPage.viewLeadsAndPlanners.waitUntilVisible().shouldBePresent();
        }
    }

    public void logOut() {
        logoutButton.click();
        waitFor(administrationText);
    }

    public void checkForExistingMarketLeads() {
        existingUsersSection.isPresent();
        existingUsersSection.shouldContainText("Name");
        existingUsersSection.shouldContainText("Market");
    }

    public void logOutButtonIsPresent() {
        find(By.linkText("Log out")).isPresent();
    }

    public void shouldThrowPermissionDeniedMessage() {
        withTimeoutOf(10, TimeUnit.SECONDS).waitForText(bodyContainer, "You do not have permission to access this area");
        find(By.linkText("Log out")).shouldBePresent();
    }

    public void addMarketLeadUserButtonIsPresent() {
        addUserButton.shouldBePresent();
    }

    public void addLeadUser(String user, String market) {
        new Actions(getDriver()).sendKeys(Keys.PAGE_UP).build().perform();
        waitABit(500);
        addUserButton.waitUntilClickable().click();
        marketDropdown.waitUntilEnabled().clear();
        marketDropdown.typeAndTab(market);
        userDropdown.waitUntilEnabled().typeAndEnter(user);
        waitABit(2000);
        userDropdown.sendKeys(Keys.ENTER);
        addUserModalButton.waitUntilClickable().then().click();
    }

    public void checkUserWasAdded(String user) throws Exception {
        addUserModalButton.waitUntilClickable().then().click();
        closeAddUserModalButton.waitUntilClickable().then().click();
        addUserButton.waitUntilVisible();

        while (nextButton.isCurrentlyEnabled()) {
        	if (find(getRemoveAccessButton(user)).isCurrentlyVisible()) {
        		assertThat(find(getRemoveAccessButton(user)).isDisplayed());
        		break;        		
			}     
        	nextButton.click();
        }       
    }

    public void checkErrorBanner() {
        assertThat(errorBanner.waitUntilVisible().isDisplayed());
        assertThat(errorBanner.waitUntilVisible().getText().contains("is already assigned to the"));
    }

    public void checkSuccessBanner() {
        assertThat(successBanner.waitUntilVisible().isDisplayed());
        assertThat(successBanner.waitUntilVisible().getText().contains("added successfully"));
    }

    public void removeLeadUser(String user) throws Exception {
        checkUserWasAdded(user);
        find(getRemoveAccessButton(user)).waitUntilVisible().click();
        deleteUserModalButton.waitUntilVisible().click();

        while (nextButton.isCurrentlyEnabled()) {
            assertThat(!find(getRemoveAccessButton(user)).isCurrentlyVisible());
            nextButton.click();
        }
        assertThat(!find(getRemoveAccessButton(user)).isCurrentlyVisible());
    }

    public void removeTestUserBeforeRunning(String user) {
        while (nextButton.isCurrentlyEnabled() || find(getRemoveAccessButton(user)).isCurrentlyVisible()) {
            if (find(getRemoveAccessButton(user)).isCurrentlyVisible()) {
                find(getRemoveAccessButton(user)).waitUntilVisible().click();
                deleteUserModalButton.waitUntilVisible().click();
                break;
            }
            nextButton.click();
        }
    }
}

