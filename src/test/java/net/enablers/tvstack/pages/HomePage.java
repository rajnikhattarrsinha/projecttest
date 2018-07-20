package net.enablers.tvstack.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends PageObject {

    @FindBy(xpath = "//h1[contains(.,'TV Stack')]")
    WebElementFacade pageHeader;

    @FindBy(css = "#markets")
    WebElementFacade marketSelectionBox;

    @FindBy(xpath = "//div[@class='Select-menu-outer']")
    WebElementFacade marketSelectionDropDown;

    @FindBy(css = "#clients")
    WebElementFacade clientSelectionBox;

    @FindBy(xpath = "//*[@class='Polaris-Card']/*/ul")
    WebElementFacade existingPlansSection;

    @FindBy(xpath = "//button[span=\"Create new plan\"]")
    WebElementFacade createNewPlanButton;

    @FindBy(xpath = "//*[@class='Polaris-Card']//ul/li[1]//div[h3]")
    WebElementFacade existingPlansHeader;

    @FindBy(xpath = "//*[@class=\'Polaris-Card']/*/ul/li[1]")
    WebElementFacade firstExistingPlan;

    @FindBy(xpath = "//*[@class='Polaris-Card']/*/ul/li[1]/div/button")
    WebElementFacade firstExistingPlanOperations;

    @FindBy(xpath = "//*[@class='Polaris-Card']/*/ul/li[1]//button/span/span[text()='Edit']")
    WebElementFacade firstExistingPlanEditButton;

    @FindBy(xpath = "//*[@class=\'Polaris-Card']/*/ul/li[1]//h3")
    WebElementFacade firstExistingPlanName;


    @FindBy(xpath = "//p[@class]")
    WebElementFacade tvStackClientText;

    @FindBy(xpath = "//button")
    WebElement loginButton;

    @FindBy(id = "okta-signin-username")
    WebElement oktaSigninUsername;

    @FindBy(id = "okta-signin-password")
    WebElement oktaSigninPassword;

    @FindBy(id = "okta-signin-submit")
    WebElement oktaSigninSubmit;
    
    @FindBy(css = ".btn-primary")
    WebElementFacade deletePlanModalBtn;
    
    @FindBy(css = ".inner")
    WebElementFacade loadingCover;

    //@FindBy(xpath = "//header//span[@class = 'header__signout']")
    @FindBy(xpath = "//span[@class='Polaris-Button__Content']")
    WebElement logoutButton;


    public By getDeleteButtonForPlan(String plan) {
        return By.xpath("//li/div[contains(@class, 'Polaris-ResourceList-Item')]//h3/span[text() = '"+plan+"']//..//..//..//..//..//button/span/span[text() = 'Delete']");
    }
    
    public By getEditButtonForPlan(String user) {
        return By.xpath("//li/div[contains(@class, 'Polaris-ResourceList-Item')]//strong[text() = '"+user+"']//..//..//..//..//..//button/span/span[text() = 'Edit']");
    }
    
    public void verifyPageHeaderIsCorrect() {
        pageHeader.withTimeoutOf(20, TimeUnit.SECONDS).shouldBeVisible();
    }

    public void verifyMarketSelectionIsPresent() {
        marketSelectionBox.waitUntilVisible().shouldBeEnabled();
    }

    public void selectMarket(String arg0) {
//        commented out the selection part as the selection box locator is not available as dev are using react plugin. Typing the value instead temporarily
        marketSelectionBox.waitUntilEnabled().clear();
        marketSelectionBox.typeAndTab(arg0);
        waitABit(500);
        if (marketSelectionDropDown.isCurrentlyVisible()) {
            marketSelectionDropDown.waitUntilClickable().then().click();
        }
        pageHeader.waitUntilClickable().then().click();
    }

    public void verifyClientSelectionIsPresent() {
        clientSelectionBox.shouldBeVisible();
    }

    public void selectClient(String arg0) {
//        commented out the selection part as the selection box locator is not available as dev are using react plugin. Typing the value instead temporarily
//        clientSelectionBox.selectByVisibleText(arg0);
        clientSelectionBox.typeAndEnter(arg0);
    }

    public void verifyExistingPlansSectionPresent() {
        existingPlansSection.shouldBePresent();
    }

    public void verifyCreateNewPlanOptionPresent() {
        createNewPlanButton.shouldBePresent();
    }

    public void checkExistingPlansFields() {

        existingPlansHeader.isCurrentlyVisible();
    }

    public void verifyManagePlansOptionsExist() {
        if (findAll("//*[@class=\"Polaris-Card\"]/*/table/tbody").size() > 1) {
            assertThat(findAll(By.xpath("//*[@class=\"Polaris-Card\"]/*/table/tbody/tr[1]/td/button")).size()).isEqualTo(4);
        }
    }

    public void createNewPlan() {
        createNewPlanButton.waitUntilClickable().click();
    }

    public void verifyNewPlanIsShown(String plan) {
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(existingPlansSection);
        existingPlansSection.shouldContainText(plan);
    }
    
    public void verifyPlanWasDeleted(String plan) {
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(existingPlansSection);
        existingPlansSection.shouldNotContainText(plan);
    }
    
    public void deletePlan(String plan) {
    	withAction().moveToElement(element(this.getDeleteButtonForPlan(plan))).click().build().perform();
        element(this.getDeleteButtonForPlan(plan)).waitUntilEnabled().click();
        deletePlanModalBtn.waitUntilClickable().then().click();
        loadingCover.shouldBeCurrentlyVisible();
        loadingCover.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();       
    }
    
    public void verifyAtleastOnePlanExist() {
        assertThat(findAll("//*[@class='Polaris-Card']/*/ul//li").size() >= 1).isTrue();
    }

    public void editAnExistingPlan() {
    	withAction().moveToElement(element(this.getEditButtonForPlan(Serenity.sessionVariableCalled("user")))).build().perform();
    	element(this.getEditButtonForPlan(Serenity.sessionVariableCalled("user"))).waitUntilEnabled().click();
    }

    public String storeFirstPlansName() {
        return firstExistingPlanName.getText();
    }


    //This method to get Okta Token for API calls
    public String signIntoClientAndGetOktaTokenStorage(String planner) {

        tvStackClientText.shouldBePresent();
        loginButton.click();
        waitABit(2000);
        oktaSigninUsername.sendKeys(planner);
        oktaSigninPassword.sendKeys("Great123");
        oktaSigninSubmit.click();
        waitABit(5000);

        return (String) evaluateJavascript(String.format("return window.localStorage.getItem('%s');", "okta-token-storage"));
    }

    public void logOut() {
        logoutButton.click();
        waitFor(tvStackClientText);
    }
}
