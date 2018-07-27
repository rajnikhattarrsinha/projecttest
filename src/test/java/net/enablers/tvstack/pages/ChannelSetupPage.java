package net.enablers.tvstack.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertThat;
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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.CoreMatchers;

public class ChannelSetupPage extends PageObject {

    NewPlanSetupPage newPlanSetupPage;
    
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
	
	@FindBy(xpath = "//td[text()='Youtube']/parent::tr//select")
	WebElementFacade listboxYoutubeAge;

	@FindBy(xpath = "//p[text()='Please select at least 1 channel buying audience and format to proceed']")
	WebElementFacade textvalidationMessage;

	public void selectClosestbuyingAudienceoption(String ChannelName,String buyingAudience)
	{
		//@ If user pass some channel name then only it checkbox.
		if(ChannelName.trim()!="")
		{
			//@ select buying Audience if input is not blank.
			if(buyingAudience.trim() !="")
			{
				element(listboxbuyingAudience(ChannelName)).selectByVisibleText(buyingAudience);
				WaitForPageLoad(20);
			}

		}
	}
	public void selectSecondLengthFormatoption(String ChannelName,String SecondLengthformat)
	{
		//@ If user pass some channel name then only it checkbox.
		if(ChannelName.trim()!="")
		{
			//@ select Second Length format if input is not blank.
			if(SecondLengthformat.trim() !="")
			{
				element(listboxSecondLengthformat(ChannelName)).selectByVisibleText(SecondLengthformat);
				WaitForPageLoad(20);
			}
		}
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
	public void clickonNextScenariosbutton()
	{
		element(buttonName("Next: Scenarios")).click();
		WaitForPageLoad(180);
	}
	public By textboxBasedonName(String TextboxName)
	{
		return By.xpath("//*[text()='"+TextboxName+"']/following::input");
	}
	public By textboxCPM(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/parent::tr//input[@type='text']");
	}
	public void verifyScenariosPage()
	{
		element(buttonName("Create new scenario")).withTimeoutOf(20, TimeUnit.SECONDS).shouldBeVisible();
	}
	public void verifyChannelsPage()
	{
		element(buttonName("Next: Scenarios")).withTimeoutOf(20, TimeUnit.SECONDS).shouldBeVisible();
	}
	public void checkChannelscheckbox(String ChannelName)
	{
		//@ If user pass some channel name then only it checkbox.
		if(ChannelName.trim()!="")
		{
			//@ check by default behaviour of checkbox if it is not checked then only it will click.
			JavascriptExecutor executor = (JavascriptExecutor)getDriver();
			if(!element(checkboxChannels(ChannelName)).isSelected())
			{
				//@ channel checkbox selection.
				element(checkboxChannels(ChannelName)).click();
				executor.executeScript("arguments[0].focus();", element(checkboxChannels(ChannelName)));
				executor.executeScript("arguments[0].click();", element(checkboxChannels(ChannelName)));
			}
		}

	}
	public void selectAgeforYoutubeChannel(String Age)
	{
		listboxYoutubeAge.selectByVisibleText(Age);
		WaitForPageLoad(20);
	}
	public void selectGender(String Gender,String Channel)
	{
		element(listboxGender(Channel)).selectByVisibleText(Gender);
		WaitForPageLoad(20);
	}
	public void selectMinAge(String MinAge,String Channel)
	{
		element(listboxMinAge(Channel)).selectByVisibleText(MinAge);
		WaitForPageLoad(20);
	}
	public void selectMaxAge(String MaxAge,String Channel)
	{
		element(listboxMaxAge(Channel)).selectByVisibleText(MaxAge);
		WaitForPageLoad(20);
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
	public void verifyMessageonHeader()
	{
		textvalidationMessage.withTimeoutOf(20, TimeUnit.SECONDS).shouldBeVisible();
	}
	public void clickOnNextChannelsbuttonandverifyChannelPage()
	{
		element(buttonName("Next: Channels")).click();
		WaitForPageLoad(20);
	}
	public void uncheckAllChannelcheckbox()
	{
		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
		List<WebElementFacade> matchingWebElements = findAll(By.xpath("//input[contains(@id,'Checkbox')]"));
		int Count = matchingWebElements.size();
		for(int i=0;i<Count;i++)
		{
			if(matchingWebElements.get(i).isSelected())
			{
				executor.executeScript("arguments[0].focus();", matchingWebElements.get(i));
				executor.executeScript("arguments[0].click();", matchingWebElements.get(i));
			}
		}
	}
	public void WaitForPageLoad(int timeoutinsecond)
	{
		//@ Wait for browser Ready state.
		waitABit(100);
		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
		try
		{
			for(int i =0;i<timeoutinsecond;i++)
			{
				String browserState = String.valueOf(executor.executeScript("return document.readyState"));
				if(browserState.equals("complete"))
				{
					break;
				}
				else
				{
					waitABit(1000);
				}
			}

		}
		catch (Exception e)
		{}
		//@ Wait for Loader
		try
		{
			for (int i = 0; i < timeoutinsecond; i++)
			{
				WebElement elementajax = getDriver().findElement(By.xpath("//*[@aria-label='Loading']"));
				if(elementajax.isDisplayed())
				{
					waitABit(1000);
				}
				else
				{
					break;
				}
			}
		}
		catch (Exception e)
		{ }

		//@ Wait for Spinner
		try
		{
			for (int i = 0; i < timeoutinsecond; i++)
			{
				WebElement elementajax = getDriver().findElement(By.xpath("//*[contains(@class,'Spinner')]"));
				if(elementajax.isDisplayed())
				{
					waitABit(1000);
				}
				else
				{
					break;
				}
			}
		}
		catch (Exception e)
		{ }

	}
	public void getCPMvalueandverify(String ChannelName,String valuetoverify)
	{
		assertThat(element(textboxCPM(ChannelName)).getValue(), CoreMatchers.equalTo(valuetoverify));
	}
	public void clickonCalibratebutton()
	{
		element(buttonName("Calibrate")).click();
		WaitForPageLoad(180);
	}
	public void getGRPsvalueandverifytouserinput(String UserGRPs)
	{
		assertThat(element(textboxBasedonName("GRPs Calibrated at")).getValue(), CoreMatchers.equalTo(UserGRPs));
	}
	public void getReachvalueandComapreUserInputValue(String Reach)
	{
		assertThat(element(textboxBasedonName("Reach")).getValue(), CoreMatchers.equalTo(Reach));
	}
	public void iClickonADVANCEDbutton()
	{
		advancedCalibrateButton.click();
		WaitForPageLoad(10);
	}
	public void getMaximumReachValueAndComapreUserInputValue(String MaximumReach)
	{
		assertThat(element(textboxBasedonName("Maximum Reach")).getValue(), CoreMatchers.equalTo(MaximumReach));
	}
	public void getPrecisionValueAndComapreUserInputValue(String Precision)
	{
		assertThat(element(textboxBasedonName("Precision")).getValue(), CoreMatchers.equalTo(Precision));
	}
	public void iclickCancelbutton()
	{
	     	element(buttonName("Cancel")).click();
		WaitForPageLoad(10);
	}
	public void verifyCalibrateScreenIsnotDisplayed()
	{
		element(buttonName("Cancel")).shouldNotBeCurrentlyVisible();
	}
	//*************** RAJNI CODE END HERE ******************************//
	//********************************************************************//



}
