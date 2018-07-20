package net.enablers.tvstack.pages;

import java.util.concurrent.TimeUnit;

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

    @FindBy(xpath = "//h1[contains(.,'Audiences')]")
    WebElementFacade pageHeader;

    @FindBy(xpath = "//*[@class='audience-builder__dd-pane-header']/h2[contains(.,'Planning audience') or contains(.,'Buying audience')]")
    WebElementFacade audienceBuilderHeader;

    @FindBy(xpath = "//*[@role=\"tablist\"]")
    WebElementFacade tablist;

    @FindBy(xpath = "//table")
    WebElementFacade existingAudiencesSection;

    @FindBy(xpath = "//table/thead/tr")
    WebElementFacade existingAudienceHeader;

    @FindBy(xpath = "//button/span/span[text() = 'Create new audience']")
    WebElementFacade newAudieceButton;

    @FindBy(xpath = "//input[contains(@class,'title-input')]")
    WebElementFacade newAudieceInputField;

    @FindBy(xpath = "//span[@class='audience-builder__query-preview-value']")
    WebElementFacade previewZone;

    @FindBy(xpath = "//*[@class='Polaris-TextContainer']")
    WebElementFacade previewDisplayUnderExistingAudiences;
    
    @FindBy(xpath = "//h1[contains(.,'TV Stack')]")
    WebElementFacade homePageHeader;

    public void verifyExistingAudienceSectionPresent() {
        existingAudiencesSection.shouldBePresent();
    }

    public void verifyPageTitle() {
        withTimeoutOf(180, TimeUnit.SECONDS).waitFor(pageHeader);
        pageHeader.shouldBePresent();
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

    public void saveTheAudience() {
        $("//button[contains(span,'Save')]").withTimeoutOf(50, TimeUnit.SECONDS).waitUntilClickable().click();
        $("//button[contains(span,'Save')]").withTimeoutOf(100, TimeUnit.SECONDS).waitUntilNotVisible();
    }

    public void audienceIsSavedSuccessfully() {
        this.verifyPageTitle();
        withTimeoutOf(300, TimeUnit.SECONDS).waitForText(existingAudiencesSection, Serenity.sessionVariableCalled("new_audience_name"));
        $("//button[span='Preview']").click();
        waitFor(previewDisplayUnderExistingAudiences).shouldContainText(Serenity.sessionVariableCalled("female_query"));
    }

    public void clickOnDeleteAudience() {
        $("//button[span/span/span[not(contains(@class,'header'))]]").click();
        waitFor("//*[@class='modal-content']").shouldContainText("Do you want to delete planning audience?");
        $("//*[@class='modal-content']").findBy(By.xpath("//button[text()='Delete']")).click();
    }

    public void checkAudienceDeletedSuccessfully() {
        //the row containing the audience in the table should be deleted
        withTimeoutOf(300, TimeUnit.SECONDS).waitForAbsenceOf("//table/tbody");
    }
}
