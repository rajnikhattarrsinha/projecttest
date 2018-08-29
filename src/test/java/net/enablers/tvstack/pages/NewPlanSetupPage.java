package net.enablers.tvstack.pages;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Verify;
import org.jruby.RubyProcess;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.enablers.tvstack.model.web.PlanModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class NewPlanSetupPage extends PageObject {

    PlanModel plan = new PlanModel();

    @FindBy(xpath = "//*[@role=\"tablist\"]")
    WebElementFacade tablist;

    @FindBy(css = ".plan-setup__wrapper")
    WebElementFacade planSetupTab;

    @FindBy(xpath = "//div[label[text() = 'Plan name']]//..//..//input")
    WebElementFacade planName;

    @FindBy(xpath = "//div[label[text() = 'Total video budget']]//..//..//input")
    WebElementFacade videoBudget;

    @FindBy(xpath = "//input[@id = 'startDate']")
    WebElementFacade dateField;

    @FindBy(xpath = "//input[@id = 'startDate']")
    WebElementFacade dateField1;

    @FindBy(xpath = "//input[@id = 'endDate']")
    WebElementFacade endDate1;


    @FindBy(xpath = "//input[@id = 'endDate']")
    WebElementFacade endDate;

    @FindBy(xpath = "//*[contains(@class,'header__logo') and not(contains(@class, 'non-clickable'))]/img")
    WebElementFacade tvstackLogo;

    @FindBy(xpath = "//*[@class='Polaris-Labelled__Error']")
    WebElementFacade errorBanner;

    @FindBy(xpath = "//button[span='Create Plan']")
    WebElementFacade createPlanButton;

    @FindBy(xpath = "//td[@tabindex='0']")
    List<WebElementFacade> lastOfMonth;

    @FindBy(xpath = "//td[text() = '1'][not(contains(@class, 'blocked'))]")
    List<WebElementFacade> firstOfMonth;

    @FindBy(xpath = "//button[@class='DateRangePickerInput_calendarIcon DateRangePickerInput_calendarIcon_1']")
    WebElementFacade DatePicker;

    @FindBy(xpath = "//*[@class='Polaris-Banner__Content']/p/strong")
    WebElement CampaignLength;

    @FindBy(xpath = "//*[@class='Polaris-Banner__Content']/p")
    WebElement CampaignText;

    public By getPageHeaderBasedOnSection(String section) {
    	return By.xpath("//h1[contains(.,'"+section+"')]");
    }

    public By getPlanSetupButtonBasedOn(String text) {
    	return By.xpath("//button[contains(span,'" + text + "')]");
    }

    public void verifyPageTitle(String section) {
        withTimeoutOf(180, TimeUnit.SECONDS).waitFor(this.getPageHeaderBasedOnSection(section));
        element(this.getPageHeaderBasedOnSection(section)).shouldBeVisible();
    }

    public void verifyPlanSetupSelected() {
        assertThat(planSetupTab.isCurrentlyVisible());
    }

    public void verifyFieldsPresent() {
        planName.shouldBePresent();
        videoBudget.shouldBePresent();
    }

    public void verifyButtonIsEnabled(String arg0) {
        element(this.getPlanSetupButtonBasedOn(arg0)).shouldBePresent();
        element(this.getPlanSetupButtonBasedOn(arg0)).withTimeoutOf(20, TimeUnit.SECONDS).shouldBeEnabled();
    }

    public void verifyButtonIsDisabled(String arg0) {
    	element(this.getPlanSetupButtonBasedOn(arg0)).shouldBePresent();
    	element(this.getPlanSetupButtonBasedOn(arg0)).shouldNotBeEnabled();
    }

    public void verifyTabs() {
        $("//button[span='Audience Setup']").shouldBePresent();
        $("//button[span='Channel selection']").shouldBePresent();
        $("//button[span='Build scenario']").shouldBePresent();
        $("//button[span='Compare scenario']").shouldBePresent();
    }

    public void submitNewPlan() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int startDate = Integer.parseInt(sdf.format(date));
        int endDate = Integer.parseInt(sdf.format(date)) + 1;

        plan.setMarket("United Kingdom");
        plan.setClient("JD Sports");
        plan.setPlanName("AutoPlan" + RandomGenerator.randomAlphanumeric(3));
        Serenity.setSessionVariable("planName").to(plan.getPlanName());
        plan.setBudget(1000000);
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        planName.type(plan.getPlanName());
        videoBudget.type(plan.getBudget().toString());

        dateField.waitUntilClickable().then().click();
        $("//td[text() = '" + plan.getStartDate() + "'][@tabindex = '0']").waitUntilClickable().click();
        if (lastOfMonth.size()==1) {
			firstOfMonth.get(0).waitUntilClickable().click();
		} else {
			$("//td[text() = '" + plan.getEndDate() + "'][@tabindex = '0']").waitUntilClickable().click();
		}
        $("//button[span='Create Plan']").waitUntilEnabled().and().waitUntilClickable().click();
    }


    public void SevenDaysDatePicker(){

       LocalDate localDate = LocalDate.now();
       Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

       SimpleDateFormat sdf = new SimpleDateFormat("dd");
       int startDate = Integer.parseInt(sdf.format(date));
       int endDate = Integer.parseInt(sdf.format(date)) + 3;
       plan.setStartDate(startDate);
       plan.setEndDate(endDate);


       dateField.waitUntilClickable().then().click();
       $("//td[text() = '" + plan.getStartDate() + "'][@tabindex = '0']").waitUntilClickable().click();

    //   $("//td[text() = '" + plan.getEndDate() + "'][not(contains(@class, 'blocked'))]").waitUntilClickable().click();

    }

    public void selectMoreThan3WeekSlot()
    {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int startDate = Integer.parseInt(sdf.format(date));
        int endDate = Integer.parseInt(sdf.format(date)) + 31;
        plan.setStartDate(startDate);
        int res= endDate%30;
        plan.setEndDate(res);

        dateField.waitUntilClickable().then().click();
        $("//td[text() = '" + plan.getStartDate() + "'][@tabindex = '0']").waitUntilClickable().click();
       $("//td[text() = '" + plan.getEndDate() + "'][not(contains(@class, 'blocked'))]").waitUntilClickable().click();

    }

    public void verifyCampaignLength() {

        waitFor(CampaignLength).waitUntilVisible();
        String campaign = CampaignLength.getText();

        Assert.assertEquals(campaign , "7 days");
                }

    public void verifyCampaignLengthForMoreThan7Days(){
        waitFor(CampaignLength).waitUntilVisible();
        String campaign = CampaignLength.getText();
        Assert.assertEquals(campaign , "14 days");
    }

    public void verifyCampaignLengthForMoreThan3Week() {
        waitFor(CampaignLength).waitUntilVisible();
        String campaign = CampaignLength.getText();
        Assert.assertEquals(campaign, "4-8 weeks");
    }

    public void selectMoreThan7Days(){
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int startDate = Integer.parseInt(sdf.format(date));
        int endDate = Integer.parseInt(sdf.format(date)) + 11;
        plan.setStartDate(startDate);
        int res= endDate%30;
        plan.setEndDate(res);

        dateField.waitUntilClickable().then().click();
        $("//td[text() = '" + plan.getStartDate() + "'][@tabindex = '0']").waitUntilClickable().click();

            $("//td[text() = '" + plan.getEndDate() + "'][not(contains(@class, 'blocked'))]").waitUntilClickable().click();

    }

    public PlanModel clickOnLogo() {
        waitABit(20000);
        if (tvstackLogo.isPresent()) {
            tvstackLogo.click();
        }
        return plan;
    }

    public void editExistingPlan() {
        plan.setPlanName($("#TextField1").getValue() + "edited");
        plan.setBudget(53200);
        planName.clear();
        planName.type(plan.getPlanName());
        videoBudget.type(plan.getBudget().toString());
        $("//button[span='Update']").click();
        $("//button//span[text() = 'Save']").waitUntilClickable().click();
    }

    public void enterPlanName(String existingPlanName) {
        planName.type(existingPlanName);
    }

    public void verifyError() {
        errorBanner.isCurrentlyVisible();
        createPlanButton.shouldNotBeEnabled();
    }

	public void verifyEditPageTitle() {
		element(this.getPageHeaderBasedOnSection("Edit plan")).shouldBeVisible();
	}
	
	public void iClickNextButton(String buttonType) {
		element(this.getPlanSetupButtonBasedOn(buttonType)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilClickable().then().click();				
	}
}
