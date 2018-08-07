package net.enablers.tvstack.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import cucumber.api.java.en.Then;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

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
    
    @FindBy(xpath = "//div[contains(@class, 'statusCritical')]")
    WebElementFacade errorBanner;

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
		assertThat(errorBanner.waitUntilVisible().then().isCurrentlyVisible());
	}




	//*************** RAJNI CODE START HERE ******************************//
	//********************************************************************//

	//*--------- Web Element Proprty decelation section

	@FindBy(xpath = "//td[text()='Youtube']/..//select")
	WebElementFacade listboxYoutubeAge;

	@FindBy(xpath = "//h1")
	WebElementFacade headerTag;

	@FindBy(xpath = "//a[contains(@href,'edit') and contains(@href,'plan')]")
	WebElementFacade linkPlan;

	public void selectClosestbuyingAudienceoption(String channelName,String buyingAudience)
	{
		element(listboxbuyingAudience(channelName)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(buyingAudience);
	}
	public void selectSecondLengthFormatoption(String channelName,String secondLengthformat)
	{
		waitABit(2000);
		element(listboxSecondLengthformat(channelName)).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(secondLengthformat);
	}
	public By checkboxChannels(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/..//input[@type='checkbox']");
	}
	public By listboxbuyingAudience(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/../td[3]//select");
	}
	public By listboxSecondLengthformat(String ChannelsName)
	{
		return By.xpath("//td[text()='"+ChannelsName+"']/../td[4]//select");
	}
	public By listboxonScenarioComparison(String listboxName)
	{
		return By.xpath("//label[text()='"+listboxName+"']/parent::div/parent::div/parent::div//select");
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
		return By.xpath("//td[text()='"+ChannelsName+"']/..//input[@type='text']");
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
	public By linkBreadCrumb(String breadcrumbName)
	{
		return By.xpath("//ol//li//a[text()='"+breadcrumbName+"']");
	}

	public void getCPMValueOfChannelAndVerifyWithUserCPMvalue(String channelName,String valuetoverify)
	{
		assertThat(element(textboxCPM(channelName)).getValue() == valuetoverify);
	}


	public void setCPMvalue(String ChannelName,String NewCPMValue)
	{
		element(textboxCPM(ChannelName)).typeAndTab(NewCPMValue);

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

	public void verifyScenariosPageWithNewScenariosButton()
	{
		element(buttonName("Create new scenario")).withTimeoutOf(180, TimeUnit.SECONDS).waitUntilClickable();
	}

	public void selectAgeforYoutubeChannel(String Age)
	{
		listboxYoutubeAge.waitUntilEnabled().selectByVisibleText(Age);
	}

	public void selectGender(String Gender,String Channel)
	{
		element(listboxGender(Channel)).waitUntilEnabled().selectByVisibleText(Gender);
	}

	public void selectMinAge(String MinAge,String Channel)
	{
		element(listboxMinAge(Channel)).selectByVisibleText(MinAge);
	}
	public void selectMaxAge(String MaxAge,String Channel)
	{
		element(listboxMaxAge(Channel)).selectByVisibleText(MaxAge);
	}

	public void verifyNoneditableCPMtextbox(String channelName)
	{
		assertThat(element(textboxCPM(channelName)).getAttribute("readonly") !=null);
	}

	public void enterCPMValueCorrespondingToChannel(String channelName,String cpmValue)
	{
		element(textboxCPM(channelName)).waitUntilClickable().clear();
		element(textboxCPM(channelName)).sendKeys(cpmValue);
	}
	public void selectScenarioAValue(String ScenarioAvalue)
	{
		element(listboxonScenarioComparison("Scenario A")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(ScenarioAvalue);
	}
	public void selectScenarioBValue(String ScenarioBvalue)
	{
		element(listboxonScenarioComparison("Scenario B")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(ScenarioBvalue);
	}
	public void selectPlanningAudienceValue(String PlanningAudience)
	{
		element(listboxonScenarioComparison("Planning Audience")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilEnabled().selectByVisibleText(PlanningAudience);
	}

	public void clickonBreadCrumbLink(String breadcrumbLinkName)
	{
		element(linkBreadCrumb("Channels")).waitUntilClickable().click();
	}
	public void verifyPageHeader(String PageHeader)
	{
		headerTag.shouldBeVisible();
		assertThat(headerTag.getText().toLowerCase().contains(PageHeader.toLowerCase()));

	}

	public void iClickonButton(String buttonName)
	{

		try
		{
			$(".inner").withTimeoutOf(120, TimeUnit.SECONDS).waitUntilNotVisible();
		}
		catch(Exception e) {}
		element(buttonName(buttonName)).withTimeoutOf(10, TimeUnit.SECONDS).waitUntilClickable().then().click();
		try
		{
			$(".inner").withTimeoutOf(120, TimeUnit.SECONDS).waitUntilNotVisible();
		}

		catch(Exception e) {}

	}

	public void verifyNewlyCreatedScenario(String ScenarioNumber)
	{
		String ScenarioName = Serenity.sessionVariableCalled("new_audience_name")+"-scenario"+ScenarioNumber;
		$("//td[text()='"+ScenarioName+"']").withTimeoutOf(30, TimeUnit.SECONDS).waitUntilVisible();
	}

	public void clickonPlanNamefromHeaderSection()
	{
		linkPlan.waitUntilClickable().click();
	}

	//*************** RAJNI CODE END HERE ******************************//
	//********************************************************************//


}
