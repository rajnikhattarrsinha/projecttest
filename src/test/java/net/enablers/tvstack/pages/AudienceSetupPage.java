package net.enablers.tvstack.pages;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.enablers.tvstack.model.api.ProjectRequestModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class AudienceSetupPage extends PageObject {

    ProjectRequestModel plan = new ProjectRequestModel();

    @FindBy(xpath = "//*[@class='audience-builder__dd-pane-header']/h2[contains(.,'Planning audience') or contains(.,'Buying audience')]")
    WebElementFacade audienceBuilderHeader;

    @FindBy(xpath = "//*[@role=\"tablist\"]")
    WebElementFacade tablist;

    @FindBy(xpath = "//table")
    WebElementFacade existingAudiencesSection;

    @FindBy(xpath = "//table/thead/tr")
    WebElementFacade existingAudienceHeader;

    @FindBy(xpath = "//button/span/span[text() = 'Create audience']")
    WebElementFacade newAudieceButton;

    @FindBy(xpath = "//input[contains(@class,'title-input')]")
    WebElementFacade newAudieceInputField;

    @FindBy(xpath = "//span[@class='audience-builder__query-preview-value']")
    WebElementFacade previewZone;

    @FindBy(xpath = "//*[@class='Polaris-TextContainer']")
    WebElementFacade previewDisplayUnderExistingAudiences;
    
    @FindBy(xpath = "//h1[contains(.,'TV Stack')]")
    WebElementFacade homePageHeader;
    
    @FindBy(xpath = "//tbody//button[span/span/span]")
    WebElementFacade deleteAudienceButton;
    
    @FindBy(xpath = "//button[text()='Delete']")
    WebElementFacade deleteAudienceModalButton;
    
    @FindBy(xpath = "//*[@class='modal-content']")
    WebElementFacade deleteAudienceModal;

    @FindBy(xpath= "//input[@placeholder='Enter new Audience name']")
    WebElementFacade RenameAudience;

    @FindBy(xpath= "//button[contains(text(),'Save')]")
            WebElement SaveBtn;

    @FindBy(xpath = "//tr//td[8]//button[1]")
    WebElement EditAudienceButton;

    @FindBy(xpath = "//div[@class='Polaris-ButtonGroup']//div[2]//button[1]")
    WebElement ContinueBtn;

    @FindBy(xpath = "//input[@placeholder='Audience name']")
    WebElement RenameAudienceName;

    @FindBy(xpath = "//td[contains(text(),'copied')]")
    WebElement ClonedAudience;


    NewPlanSetupPage newPlanSetupPage;
    
    public By getPageHeader(String section) {
    	return By.xpath("//h1[contains(.,'"+section+"')]");
    }
    
    public void verifyExistingAudienceSectionPresent() {
        existingAudiencesSection.shouldBePresent();
    }

    public void verifyPageTitle() {
        withTimeoutOf(180, TimeUnit.SECONDS).waitFor(newPlanSetupPage.getPageHeaderBasedOnSection("Audiences"));
        element(newPlanSetupPage.getPageHeaderBasedOnSection("Audiences")).shouldBePresent();
    }
    
    public void verifyHomePageTitle() {
        withTimeoutOf(180, TimeUnit.SECONDS).waitFor(homePageHeader);
        homePageHeader.shouldBePresent();
    }

    public void checkForNewAudienceButton() {
        newAudieceButton.shouldBeEnabled();
    }

    public void checkExistingAudienceFields() {
        existingAudienceHeader.shouldContainText("Audience Name");
        existingAudienceHeader.shouldContainText("Size");
        existingAudienceHeader.shouldContainText("Last Modified");
        existingAudienceHeader.shouldContainText("Modified By");
    }

    public void createNewAudience() {
        //Setting this variable so we can mimic the drag and drop on new audience page later on
        ((JavascriptExecutor) getDriver()).executeScript("window.APP_ENV = 'test';");
        newAudieceButton.waitUntilClickable().then().click();
        waitABit(1000);
    }

    public void checkAudienceBuilderLayout() {
        audienceBuilderHeader.shouldBePresent();
        $("//button[contains(span,\"Create new group\")]").shouldBePresent();
        $("//button[contains(span,\"Save\")]").shouldBePresent();
        find(By.linkText("Cancel")).shouldBePresent();
        find(By.linkText("Reset")).shouldBePresent();
        newAudieceInputField.shouldBePresent();
    }

    public void createAQueryOnGender() {
        WebElement demographicsFolder = $("//div[span='Demographics']");
        WebElement genderFolder = $("//div[span='Sex']");
        WebElement genderContainer = $("//div[span='Gender']");
        withTimeoutOf(200, TimeUnit.SECONDS).waitFor(demographicsFolder).then().click();
        waitFor(genderFolder).then().click();
        waitFor(genderContainer).then().click();
        WebElement femaleCandidate = $("//div[span='Female']");
        femaleCandidate.click();
        Serenity.setSessionVariable("female_query").to("((Gender [Female]))");
        previewZone.shouldContainText(Serenity.sessionVariableCalled("female_query"));
        Serenity.setSessionVariable("new_audience_name").to("Auto_audience" + RandomGenerator.randomAlphanumeric(3));
        newAudieceInputField.type(Serenity.sessionVariableCalled("new_audience_name"));
    }

    public void ClonedAudience(){
        Serenity.setSessionVariable("clone_audience_name").to("Clone_audience" + RandomGenerator.randomAlphanumeric(3));
        RenameAudience.type(Serenity.sessionVariableCalled("clone_audience_name"));


    }

    public void saveTheAudience() {
        $("//button[contains(span,'Save')]").withTimeoutOf(50, TimeUnit.SECONDS).waitUntilClickable().click();
        $("//button[span[@class = 'au-spinner undefined']]").withTimeoutOf(10, TimeUnit.SECONDS).waitUntilVisible();
        $("//button[span[@class = 'au-spinner undefined']]").withTimeoutOf(180, TimeUnit.SECONDS).waitUntilNotVisible();
    }
    public void verifyClonedAudienceIsPresent(){
        this.verifyPageTitle();
        withTimeoutOf(300, TimeUnit.SECONDS).waitForText(existingAudiencesSection, Serenity.sessionVariableCalled("clone_audience_name"));
        $("//button[span='Preview']").click();

    }

    public void audienceIsSavedSuccessfully() {
        this.verifyPageTitle();
        withTimeoutOf(300, TimeUnit.SECONDS).waitForText(existingAudiencesSection, Serenity.sessionVariableCalled("new_audience_name"));
        $("//button[span='Preview']").click();
        waitFor(previewDisplayUnderExistingAudiences).shouldContainText(Serenity.sessionVariableCalled("female_query"));
    }

    public void clickOnDeleteAudience() {
        deleteAudienceButton.waitUntilClickable().click();
        waitFor(deleteAudienceModal).shouldContainText("Do you want to delete planning audience?");
        deleteAudienceModalButton.waitUntilClickable().then().click();
    }

    public void clickOnCopyAudience() throws InterruptedException {
        $("//tr//td[7]//button[1]").click();
        String popup = getDriver().getWindowHandle();
        getDriver().switchTo().window(popup);

    }


    public void clickSaveCopiedAudience() throws InterruptedException{
        SaveBtn.click();

    }


    public void clickEditAudienceBtn() throws InterruptedException {
        EditAudienceButton.click();
        String popup = getDriver().getWindowHandle();
        System.out.println(popup);
        getDriver().switchTo().window(popup);
        ContinueBtn.click();
        RenameAudienceName.clear();
        RenameAudience.sendKeys("Renamed");


    }

    public void checkAudienceDeletedSuccessfully() {
        //the row containing the audience in the table should be deleted
        withTimeoutOf(300, TimeUnit.SECONDS).waitForAbsenceOf("//table/tbody");
    }
}
