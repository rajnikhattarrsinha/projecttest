package net.enablers.tvstack.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ComparisonSetupPage extends PageObject {
    
	NewPlanSetupPage newPlanSetupPage = new NewPlanSetupPage();
	
	@FindBy(xpath = "//div[@class = 'Polaris-Card__Section']//thead/tr")
    WebElementFacade comparisonSetupTableHeader;
	
	@FindBy(xpath = "//*[@class = 'scenario-comparison-chart']")
    List<WebElementFacade> compareCharts;
	
	@FindBy(xpath = "//a[contains(span,'Measure')]")
    WebElementFacade measureGoogleSurveyButton;
	
	public By getOptionByScenarioName(String scenarioName, int position) {
		return By.xpath("//div["+position+"]/div/div/select/option[text() = '"+ scenarioName +"']");
	}

	public void selectScenarios() {
		List<String> scenarioNames = Serenity.sessionVariableCalled("scenarios");
		
		for (int i = 0; i < scenarioNames.size(); i++) {
			waitABit(500);
			element(this.getOptionByScenarioName(scenarioNames.get(i), i+1)).waitUntilClickable().then().click();
		}
	}

	public void verifyCompareTableHeader(List<String> options) {
		waitABit(500);
		$(".inner").withTimeoutOf(60, TimeUnit.SECONDS).waitUntilNotVisible();
		for (int i = 0; i < options.size(); i++) {
			assertThat(comparisonSetupTableHeader.containsText(options.get(i)));		
		}		
	}

	public void chartsShouldBePresent() {
		assertThat(compareCharts.size() == 2);
		for (WebElementFacade web : compareCharts) {
			web.waitUntilPresent().shouldBePresent();
		}
	}

	public void checkButtonIsDisplayed(String button) {
		if (button.equals("Download")) {
			element(newPlanSetupPage.getPlanSetupButtonBasedOn(button)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilClickable().isCurrentlyEnabled();
		} else {
			measureGoogleSurveyButton.withTimeoutOf(20, TimeUnit.SECONDS).waitUntilClickable().isCurrentlyEnabled();
		}		
	}	
}
