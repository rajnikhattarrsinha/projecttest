package net.enablers.tvstack.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.inject.spi.Elements;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ChannelsSetupPage extends PageObject 
{

	//----- Property for listbox -> ' Youtube Age
		
	 @FindBy(xpath = "//td[text()='Youtube']/parent::tr//select")
	 WebElementFacade listboxYoutubeAge;
	 
	//----- Property for text -> headerSection
		
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
				 element(listboxbuyingAudience(ChannelName)).selectByVisibleText(SecondLengthformat);
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
		 return By.xpath("//td[text()='TV']/parent::tr/child::td[4]//select");
	 }
	 
	 public By buttonName(String ButtonText)
	 {
		 return By.xpath("//button//span[contains(text(),'"+ButtonText+"')]");
	 }
	 
	 public void clickonNextScenariosbutton()
	 {
		 element(buttonName("Next: Scenarios")).click();
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
			 if(!element(checkboxChannels(ChannelName)).isSelected())
			 {
				 //@ channel checkbox selection.
				 element(checkboxChannels(ChannelName)).click();
			 }
			 
		 }
		 
	 }
	 
	 public void selectAgeforYoutubeChannel(String Age)
	 {
		 listboxYoutubeAge.selectByVisibleText(Age);
	 }
	 
	 public void selectGender(String Gender,String Channel)
	 {
		 element(listboxGender(Channel)).selectByVisibleText(Gender);
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
	 
	 public void verifyMessageonHeader()
	 {
		 textvalidationMessage.withTimeoutOf(20, TimeUnit.SECONDS).shouldBeVisible();
	 }
	 
	 public void uncheckAllChannelcheckbox()
	 {
		 List<WebElementFacade> matchingWebElements = findAll(By.xpath("//input[contains(@id,'Checkbox')]"));
		 int Count = matchingWebElements.size();
		 for(int i=0;i<Count;i++)
		 {
			 if(matchingWebElements.get(i).isSelected())
			 {
				 matchingWebElements.get(i).click();
			 }
		 }
				 
		 
	 }
}
