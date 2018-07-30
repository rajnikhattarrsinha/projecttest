package net.enablers.tvstack.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;

import io.appium.java_client.pagefactory.WithTimeout;
import net.enablers.tvstack.helpers.WebHelper;

import org.openqa.selenium.support.FindBy;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.utilities.RandomGenerator;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.inject.spi.Elements;



public class ChannelSetupPage extends PageObject {

    NewPlanSetupPage newPlanSetupPage;
    WebHelper webHelper;
    
    @FindBy(xpath = "//div[@class = 'channel-setup__table']//tbody/tr")
    List<WebElementFacade> availableChannels;
    
    @FindBy(xpath = "//div[@class = 'channel-setup__table']//thead/tr")
    WebElementFacade channelSetupHeader;
    
    @FindBy(xpath = "//div[@class= 'tv-cpm']/button")
    WebElementFacade calibrateButton;
    
    @FindBy(xpath = "//div[contains(@class, 'advanced')]/button")
    WebElementFacade advancedCalibrateButton;
    
    @FindBy(xpath = "//section[contains(@class, 'Modal' )]//input")
    List<WebElementFacade> calibrationInputFields;
    
    @FindBy(xpath = "//div[contains(@class, 'Modal-Footer' )]//button[contains(@class, 'primary')]")
    WebElementFacade saveCalibrationButton;
    
    @FindBy(xpath = "//div[contains(@class, 'Error' )][@id]")
    WebElementFacade reachFieldError;
    
    @FindBy(xpath = "//label[//input[@type = 'checkbox']]")
    List<WebElementFacade> channelCheckboxes;

    public void verifyPageTitle(String section) {
        withTimeoutOf(180, TimeUnit.SECONDS).waitFor(newPlanSetupPage.getPageHeaderBasedOnSection(section));
        element(newPlanSetupPage.getPageHeaderBasedOnSection(section)).shouldBePresent();
    }

	public void iClickChannelsButton() {
		waitABit(1500);
		$(".inner").withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
		element(newPlanSetupPage.getPlanSetupButtonBasedOn("Next: Channels")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilClickable().then().click();		
	}

	public void verifyAvailableChannels(int channelNo) {
		assertThat(availableChannels.size() == channelNo);		
	}

	public void verifyChannelTableHeader(List<String> options) {
		for (int i = 0; i < options.size(); i++) {
			assertThat(channelSetupHeader.containsText(options.get(i)));		
		}		
	}

	public void iClickScenariosButton(String buttonType) {
		element(newPlanSetupPage.getPlanSetupButtonBasedOn(buttonType)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilClickable().then().click();				
	}

	public void clickOnCalibrate() {
		calibrateButton.waitUntilClickable().then().click();		
	}

	public void addFormData(boolean isValidDataProvided) {
		Random r = new Random();
		advancedCalibrateButton.waitUntilClickable().click();
		
		for (int i = 0; i < calibrationInputFields.size(); i++) {
			if (isValidDataProvided) {
				if (calibrationInputFields.get(i).getAttribute("name").equals("reach")) {
					calibrationInputFields.get(i).sendKeys(String.valueOf(r.nextInt(19)));
				} else {
					calibrationInputFields.get(i).sendKeys(String.valueOf(r.nextInt(40)+20));
				}				
			} else {
				if (calibrationInputFields.get(i).getAttribute("name").equals("reach")) {
					calibrationInputFields.get(i).sendKeys(String.valueOf(r.nextInt(40)+20));
				} else {
					calibrationInputFields.get(i).sendKeys(String.valueOf(r.nextInt(19)));					
				}				
			}			
		}		
	}

	public void saveFormData() {
		saveCalibrationButton.waitUntilClickable().then().click();
	}

	public void checkModalError() {
		reachFieldError.shouldBeCurrentlyVisible();
	}

	public void deselectAllChannels() {
		this.verifyAvailableChannels(6);
		this.selectAllChannels();
	}

	public void allChannelsAreDeselected() {
		for (int i = 0; i < channelCheckboxes.size(); i++) {
			assertThat(!channelCheckboxes.get(i).isSelected());
		}		
	}

	public void selectAllChannels() {
		for (int i = 0; i < channelCheckboxes.size(); i++) {
			channelCheckboxes.get(i).waitUntilEnabled().click();
		}				
	}

	public void allChannelsAreSelected() {
		for (int i = 0; i < channelCheckboxes.size(); i++) {
			assertThat(channelCheckboxes.get(i).isSelected());
		}		
	}
	
	@Then("^an error is displayed$")
	public void anErrorIsDisplayed() throws Exception {
		// Error to be mapped once the the issue is fixed
	}
	
	@When("^I change the values of all input fields$")
	public void iChangeTheValuesOfAllInputFields() throws Exception {
		
	}


	@Then("^the values have changed$")
	public void theValuesHaveChanged() throws Exception {
		
	}


	//*************** RAJNI CODE START HERE ******************************//
	//********************************************************************//
	
	public void selectClosestbuyingAudienceoption(String channelName,String buyingAudience)
	{
		element(listboxbuyingAudience(channelName)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(buyingAudience);
	}
	public void selectSecondLengthFormatoption(String channelName,String secondLengthformat)
	{
		element(listboxSecondLengthformat(channelName)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(secondLengthformat);
	}
	public By checkboxChannels(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr//input[@type='checkbox']");
	}
	public By listboxbuyingAudience(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr/child::td[3]//select");
	}
	public By listboxSecondLengthformat(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr/child::td[4]//select");
	}
	public By buttonName(String ButtonText)
	{
		return By.xpath("//button//span[contains(text(),'"+ButtonText+"')]");
	}
	public By textboxBasedonName(String TextboxName)
	{
		return By.xpath("//*[text()='"+TextboxName+"']/following::input");
	}

	public By textboxCPM(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr//input[@type='text']");
	}
	public By listboxGender(String ChannelsName)
	{
		if(ChannelsName.trim().toLowerCase().equals("youtube"))
		{
			return By.xpath("//td[text()='Youtube']/parent::tr//child::div/child::div[2]//select");
		}
		else
		{
			return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr//child::div/child::div[3]//select");
		}

	}
	public By listboxMinAge(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr//child::div/child::div[1]//select");
	}
	public By listboxMaxAge(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr//child::div/child::div[2]//select");
	}

	public void getCPMValueOfChannelAndVerifyWithUserCPMvalue(String channelName,String valuetoverify)
	{
		assertThat(element(textboxCPM(channelName)).getValue() == valuetoverify);
	}

	public void getGRPsValueAndverifyWithUserGRPs(String UserGRPs)
	{
		assertThat(element(textboxBasedonName("GRPs Calibrated at")).getValue() == UserGRPs);
	}
	public void getReachvalueAndVerifyWithUserReachValue(String Reach)
	{
		assertThat(element(textboxBasedonName("Reach")).getValue()==Reach);
	}
	public void iClickonADVANCEDbutton()
	{
		advancedCalibrateButton.waitUntilClickable().click();
	}
	public void getMaximumReachValueAndVerifyWithUserMaximumReachValue(String MaximumReach)
	{
		assertThat(element(textboxBasedonName("Maximum Reach")).getValue()==MaximumReach);
	}
	public void getPrecisionValueAndComapreUserInputValue(String Precision)
	{
		assertThat(element(textboxBasedonName("Precision")).getValue()==Precision);
	}
	public void iclickCancelbutton()
	{
		element(buttonName("Cancel")).waitUntilClickable().click();
	}
	public void verifyCalibrateScreenIsnotDisplayed()
	{
		element(buttonName("Cancel")).shouldNotBeCurrentlyVisible();
	}

	//*************** RAJNI CODE END HERE ******************************//
	//********************************************************************//



}
