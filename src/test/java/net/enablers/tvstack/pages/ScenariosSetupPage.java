package net.enablers.tvstack.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ScenariosSetupPage extends PageObject {
	
	@FindBy(xpath = "//button/span/span[text() = 'Create scenario']")
    WebElementFacade createNewScenario;
	
	@FindBy(xpath = "//div[@class = 'Polaris-Card__Section']//thead/tr")
    WebElementFacade scenarioSetupTableHeader;
	
	@FindBy(xpath = "//input")
    WebElementFacade scenarioNameField;
	
	@FindBy(xpath = "//*[@class='scenario-builder__form-container']/button")
    WebElementFacade submitScenarioButton;
	
	@FindBy(xpath = "//*[@class = 'ant-slider-rail']")
    WebElementFacade slider;
	
	@FindBy(css = ".rd3-piechart-pie")
    WebElementFacade piechart;
	
	@FindBy(xpath = "//table[@class = 'compare-table']")
    WebElementFacade compareTable;
	
	@FindBy(css = ".loader-modal")
    WebElementFacade loaderModal;
	
	@FindBy(xpath = "//button/span/span[text()= 'Close']")
    WebElementFacade closeScenarioButton;
	
	@FindBy(xpath = "//button/span/span[text() = 'Optimise']")
    WebElementFacade optimiseButton;
	
	@FindBy(xpath = "//div[@role= 'slider']")
    WebElementFacade sliderTooltip;
	
	@FindBy(xpath = "//div[p[text() = 'Total Budget']]/h2")
    WebElementFacade totalBudget;
	
	@FindBy(xpath = "//table[@class = 'compare-table']/tbody/tr/td/div/div/div[contains(@id,'Suffix')]//..//input")
    List<WebElementFacade> allPercentageInputFields;
	
	@FindBy(xpath = "//table[@class = 'compare-table']/tbody/tr/td/div/div/div[contains(@id,'Prefix')]//..//input")
    List<WebElementFacade> allBudgetInputFields;
	
	public By getSliderByPercentage(int percentage){
		return By.xpath("//span[@class = 'ant-slider-dot'][contains(@style,'"+percentage+"')]");
	}
	
	public By getLockCheckbox(String channel){
		return By.xpath("//table[@class = 'compare-table']/tbody/tr/td[text() = '"+channel+"']//..//td/label//input");
	}
	
	public By selectLock(String channel){
		return By.xpath("//table[@class = 'compare-table']/tbody/tr/td[text() = '"+channel+"']//..//td/label/span/span");
	}
	
	public By getBudgetInputField(String channel){
		return By.xpath("//table[@class = 'compare-table']/tbody/tr/td[text() = '"+channel+"']//../td/div/div/div[contains(@id,'Prefix')]//../input");
	}
	
	public By getBudgetPercentageInputField(String channel){
		return By.xpath("//table[@class = 'compare-table']/tbody/tr/td[text() = '"+channel+"']//../td/div/div/div[contains(@id,'Suffix')]//../input");
	}
	
	public By getScenarioSpecificButton(String scenarioName, String buttonType){
		return By.xpath("//table//tr[td[text() = '"+scenarioName+"']]//button[span/span/i[contains(@class, '"+buttonType+"')]]");
	}
	
	public By getScenarioDeleteButton(String scenarioName){
		return By.xpath("//table//tr[td[text() = '"+scenarioName+"']]//button[span/span/span]");
	}
	
	public By getScenarioName(String scenarioName){
		return By.xpath("//table/tbody/tr/td[text() = '"+scenarioName+"']");
	}
	
	public void verifyScenarioTableHeader(List<String> options) {
		for (int i = 0; i < options.size(); i++) {
			assertThat(scenarioSetupTableHeader.containsText(options.get(i)));		
		}		
	}
	
	public String getScenarioName(){
		String scenarioName = scenarioNameField.getValue();
		
		return scenarioName;
	}

	public void submitScenario() {
		submitScenarioButton.waitUntilEnabled().then().click();
	}

	public void checkScenarioElements() {
		slider.shouldBeVisible();
		piechart.shouldBeVisible();
		compareTable.shouldBeVisible();
	}

	public void clickCreateNewScenarioButton() {
		createNewScenario.withTimeoutOf(50, TimeUnit.SECONDS).then().click();
		loaderModal.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
	}

	public void closeAddNewScenarioPage() {
		withAction().moveToElement(closeScenarioButton).build().perform();
	    closeScenarioButton.waitUntilEnabled().click();
		closeScenarioButton.withTimeoutOf(20, TimeUnit.SECONDS).waitUntilNotVisible();
		createNewScenario.withTimeoutOf(50, TimeUnit.SECONDS).waitUntilEnabled();
	}

	public void checkNewlyCreatedScenario(String scenarioName) {
		//implicit wait should be chanced once we have a loading cover
		waitABit(7000);
		element(this.getScenarioName(scenarioName)).withTimeoutOf(20, TimeUnit.SECONDS).isCurrentlyVisible();
	}

	public void updatePercentageOfBudget(String channel, int percentage) {
		element(getBudgetPercentageInputField(channel)).waitUntilClickable().clear();
		element(getBudgetPercentageInputField(channel)).waitUntilClickable().sendKeys(String.valueOf(percentage));
	}

	public void pressOptimizeButton() {
		optimiseButton.waitUntilEnabled().then().click();
		waitABit(500);
		loaderModal.withTimeoutOf(60, TimeUnit.SECONDS).waitUntilNotVisible();
	}

	public void lock(String channel) {
		element(selectLock(channel)).waitUntilClickable().click();
		assertThat(element(getLockCheckbox(channel)).isSelected());
	}

	public void checkAllChannelsBudget() {	
		int expectedBudget = 0;
		String budget = totalBudget.getText().replaceAll("£", "").replaceAll(",", "");
		for (WebElementFacade web : allBudgetInputFields) {
			expectedBudget = expectedBudget + Integer.valueOf(web.getValue().replaceAll(",", ""));
		}		
		assertThat(expectedBudget == Integer.valueOf(budget));
	}

	public void checkAllChannelsBudgetPercentage() {
		int expectedBudget = 0;
		for (WebElementFacade web : allPercentageInputFields) {
			expectedBudget = expectedBudget + Integer.valueOf(web.getValue().replaceAll("\\.0*$", ""));
		}		
		assertThat(expectedBudget == 100);
	}

	public void compareChannelBudgetDistribution(int percentage) {
		int max = 100;
		String olvValue = sliderTooltip.getAttribute("aria-valuenow");
		int tvValue = max - Integer.valueOf(olvValue);
		assertThat(percentage == tvValue);
	}

	public void clickOnSliderBasedOnSelectedPercentage(int sliderPercentage) {
		element(this.getSliderByPercentage(sliderPercentage)).waitUntilClickable().then().click();
	}

	public void checkBudgetPercentageUpdate(String channel, int percentage) {
		int tvBudgetPercentage = Integer.valueOf(element(this.getBudgetPercentageInputField(channel)).waitUntilVisible().then().getValue().replaceAll("\\.0*$", ""));		
		assertThat(tvBudgetPercentage == percentage);	
	}

	public By textbuyingAudience(String channelNo)
	{
		return By.xpath("//table[@class='compare-table']//tbody//tr[" + channelNo + "]//td[5]");
	}
	public void verifybuyingAudienceTextonScenariopage(String buyingAudience,String channelNo)
	{
		assertThat(element(textbuyingAudience(channelNo)).getText().equals(buyingAudience));
	}
}
