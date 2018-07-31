package net.enablers.tvstack.pages;
import static org.assertj.core.api.Assertions.assertThat;
import net.enablers.tvstack.model.web.PlanModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.openqa.selenium.By;

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

    @FindBy(xpath = "//input[@id = 'endDate']")
    WebElementFacade endDate;

    @FindBy(xpath = "//*[contains(@class,'header__logo') and not(contains(@class, 'non-clickable'))]/img")
    WebElementFacade tvstackLogo;

    @FindBy(xpath = "//*[@class='Polaris-Labelled__Error']")
    WebElementFacade errorBanner;

    @FindBy(xpath = "//button[span='Create Plan']")
    WebElementFacade createPlanButton;

    public By getPageHeaderBasedOnSection(String section) {
    	return By.xpath("//h1[contains(.,'"+section+"')]");
    }
    
    public By getPlanSetupButtonBasedOn(String text) {
    	return By.xpath("//button[contains(span,'" + text + "')]");
    }
    
    public void verifyPageTitle() {
    	element(this.getPageHeaderBasedOnSection("Create new plan")).shouldBeVisible();
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
        element(this.getPlanSetupButtonBasedOn(arg0)).shouldBeEnabled();
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

    /*
    public void submitNewPlan() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int startDate = Integer.parseInt(sdf.format(date));
        int endDate = Integer.parseInt(sdf.format(date)) + 10;

        plan.setMarket("United Kingdom");
        plan.setClient("JD Sports");
        plan.setPlanName("AutoPlan" + RandomGenerator.randomAlphanumeric(3));
        Serenity.setSessionVariable("planName").to(plan.getPlanName());
        plan.setBudget(100000);
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        planName.type(plan.getPlanName());
        videoBudget.type(plan.getBudget().toString());

        dateField.waitUntilClickable().then().click();
        $("//td[text()='"+plan.getStartDate()+"' and contains(@aria-label,'Choose')]").waitUntilClickable().click();
        $("//td[text()='"+plan.getEndDate()+"' and contains(@aria-label,'Choose')]").waitUntilClickable().click();
        $("//button[span='Create Plan']").waitUntilEnabled().and().waitUntilClickable().click();
    }
    */

    public void submitNewPlan()
    {
        Date today = new Date();
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        int startDate = Integer.parseInt(formattedDate.format(c.getTime()));
        c.add(Calendar.DATE, 1);
        int endDate = Integer.parseInt(formattedDate.format(c.getTime()));

        plan.setMarket("United Kingdom");
        plan.setClient("JD Sports");
        plan.setPlanName("AutoPlan" + RandomGenerator.randomAlphanumeric(3));
        Serenity.setSessionVariable("planName").to(plan.getPlanName());
        plan.setBudget(100000);
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        planName.type(plan.getPlanName());
        videoBudget.type(plan.getBudget().toString());

        dateField.waitUntilClickable().then().click();
        $("//td[text()='"+plan.getStartDate()+"' and contains(@aria-label,'Choose')]").waitUntilClickable().click();
        $("//td[text()='"+plan.getEndDate()+"' and contains(@aria-label,'Choose')]").waitUntilClickable().click();
        $("//button[span='Create Plan']").waitUntilEnabled().and().waitUntilClickable().click();
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
}
