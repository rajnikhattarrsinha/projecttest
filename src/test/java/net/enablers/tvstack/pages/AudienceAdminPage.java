package net.enablers.tvstack.pages;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.enablers.tvstack.model.api.OktaTokenStorage;
import net.enablers.tvstack.utilities.RandomGenerator;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class AudienceAdminPage extends PageObject {

    @FindBy(xpath = "//button/span[span='New Audience']")
    WebElementFacade newAudienceButton;

    @FindBy(xpath = "//button/span[span='Add Audience']")
    WebElementFacade addAudienceButton;

    @FindBy(xpath = "//button/span[span='Rename Channel']")
    WebElementFacade renameButton;

    @FindBy(xpath = "//*[@class=\"Polaris-Card__Section\"]")
    WebElementFacade existingAudiencesSection;

    @FindBy(xpath = "//input[@name]")
    WebElementFacade editChannelInputField;

    @FindBy(xpath = "//button[span/span[text()= 'Save Channel']]")
    WebElementFacade saveChannelButton;
        
    @FindBy(xpath = "//button[span/span[@class='Polaris-Button__Icon']]")
    List<WebElement> audienceExpandButtons;
    
    @FindBy(css = "div .group__row")
    WebElementFacade formatSection;
    
    @FindBy(css = "div .group__action button")
    WebElementFacade newFormatButton;
    
    @FindBy(css = "input[name]:not([type = 'hidden'])")
    List<WebElementFacade> formatInputs;
    
    @FindBy(xpath = "//button[span/span[text() = 'Create Format']]")
    WebElementFacade createFormatButton;
    
    @FindBy(xpath = "//strong")
    WebElementFacade expandedContentFormatName;
    
    @FindBy(xpath = "//span[@class = 'figure__value']")
    List<WebElementFacade> expandedContentFormatInputs;
    
    @FindBy(xpath = "//div[contains(@class, 'Footer')]//button//span[text() = 'Duplicate']")
    WebElementFacade duplicateModalButton;
    
    @FindBy(xpath = "//div[contains(@class, 'Modal-Footer')]//button[contains(@class, 'primary')]")
    WebElementFacade deleteAudienceModalButton;
    
    @FindBy(css = "input[name]")
    WebElementFacade duplicatedAudienceInputName;
       
    OktaTokenStorage oktaTokenStorage;
    
    AudienceSetupPage audienceSetupPage;
    AdminHomePage adminHomePage;

	public By getAudienceExpandButtons() {
		return By.xpath("//button[span/span[@class='Polaris-Button__Icon']]");
	}
	
	public By getBuyingAudienceByName(String audienceName){
		return By.xpath("//button[span/span[text() = '"+audienceName+"']]");
	}
	
	public By getAudienceSpecificButton(String audienceName, String buttonType){
		return By.xpath("//div[@class = 'Polaris-Stack'][div/button[span/span[text() = '"+audienceName+"']]]//button[span/span[text() = '"+buttonType+"']]");
	}
	
    public void renameOptionExists() {
        renameButton.shouldBePresent();
    }

    public void newAudienceOptionExists() {
        if (newAudienceButton.isPresent()) {
            newAudienceButton.shouldBePresent();
        } else {
        	//this is when there are no existing buying audience present
            addAudienceButton.shouldBePresent();
        }
    }

    public void clickNewAudienceButton() {
        //Setting this variable so we can mimic the drag and drop on new audience page later on
        ((JavascriptExecutor) getDriver()).executeScript("window.APP_ENV = 'test';");
        waitABit(500);
        if (newAudienceButton.isPresent()) {
            newAudienceButton.click();
        } else {
            //this is when there are no existing buying audience present
            addAudienceButton.click();
        }
        waitABit(1000);
    }

    public void shouldContainSavedAudience() {
        withTimeoutOf(300, TimeUnit.SECONDS).waitForText(existingAudiencesSection, Serenity.sessionVariableCalled("new_audience_name"));
    }

    public void clickRenameChannel() {
        renameButton.waitUntilClickable().then().click();
    }

    public void editChannelName() {
        if (Serenity.sessionVariableCalled("updatedChannel") == null) {
            Serenity.setSessionVariable("updatedChannel").to("Auto_" + RandomGenerator.randomAlphanumeric(5));
            editChannelInputField.waitUntilEnabled().then().clear();
            editChannelInputField.sendKeys(Serenity.sessionVariableCalled("updatedChannel").toString());
            saveChannelButton.waitUntilClickable().then().click();
            saveChannelButton.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
        } else {
            editChannelInputField.waitUntilEnabled().then().clear();
            editChannelInputField.sendKeys(Serenity.sessionVariableCalled("originalChannel").toString());
            saveChannelButton.waitUntilClickable().then().click();
            saveChannelButton.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
        }
    }

    public void checkChannelNameUpdate() {
    	adminHomePage.firstIndexChannel.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilVisible().containsOnlyText(Serenity.sessionVariableCalled("updatedChannel"));
    }

    public void checkChannelNameRevertion() {
    	adminHomePage.firstIndexChannel.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilVisible().containsOnlyText(Serenity.sessionVariableCalled("originalChannel"));
    }

	public void selectExistingAudience() throws Throwable {
		WebElement expandButton;
		
		if (Serenity.sessionVariableCalled("new_audience_name") == null) {
			expandButton = audienceExpandButtons.get(new Random().nextInt(audienceExpandButtons.size()-1));
			Serenity.setSessionVariable("new_audience_name").to(expandButton.getText());
		} else {
			expandButton = element(getBuyingAudienceByName(Serenity.sessionVariableCalled("new_audience_name").toString()));
			
		}
		element(expandButton).withTimeoutOf(50, TimeUnit.SECONDS).waitUntilClickable().click();
	}

	public void checkBuyingAudienceFormat() {
		if (!formatSection.isCurrentlyVisible()) {
			newFormatButton.waitUntilVisible().click();
			addNewFormatInfo();
			createFormatButton.waitUntilVisible().click();
			createFormatButton.withTimeoutOf(50, TimeUnit.SECONDS).waitUntilNotVisible();
			formatSection.waitUntilVisible();
			assertThat(formatSection.getText().contains(Serenity.sessionVariableCalled("formatName").toString()));
		} else {
			assertThat(formatSection.isCurrentlyVisible());
		}		
	}
	
	public void addNewFormatInfo() {
		Serenity.setSessionVariable("formatName").to("Format_" + RandomGenerator.randomAlphanumeric(5));
		
		List<String> formData = new ArrayList<String>();
		
		for (int i = 0; i < formatInputs.size(); i++) {
			if (formatInputs.get(i).getAttribute("name").equals("name")) {
				formatInputs.get(i).sendKeys(Serenity.sessionVariableCalled("formatName").toString());
			} else {
				formatInputs.get(i).clear();
				formatInputs.get(i).sendKeys(RandomGenerator.randomInteger(2));
				formData.add(formatInputs.get(i).getValue());
			}
		}
		Serenity.setSessionVariable("formatData").to(formData);
	}

	public void checkAddNewFormatButton() {
		newFormatButton.shouldBeCurrentlyVisible();		
	}
	
	public void addNewFormat() {
		newFormatButton.waitUntilVisible().click();
		addNewFormatInfo();
		createFormatButton.waitUntilVisible().click();
		createFormatButton.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
		formatSection.waitUntilVisible();
	}

	public void checkNewFormat() throws Throwable {
		assertThat(expandedContentFormatName.getText().contains(Serenity.sessionVariableCalled("formatName").toString()));		
		for (int i = 0; i < expandedContentFormatInputs.size(); i++) {
			assertThat(Serenity.sessionVariableCalled("formatData").toString().contains(expandedContentFormatInputs.get(i).getText()));
		}		
	}
	
	public void checkExistingFormatValues() {
		List<String> formData = new ArrayList<String>();
		
		if (formatSection.isCurrentlyVisible()) {			
			for (int i = 0; i < expandedContentFormatInputs.size(); i++) {
				formData.add(expandedContentFormatInputs.get(i).getText());
			}	
			
			Serenity.setSessionVariable("beforeFormatData").to(formData);
		} else {
			Serenity.setSessionVariable("emptyList").to(formData.size());
		}		
	}
	
	public void duplicateAudience() {
		Serenity.setSessionVariable("duplicatedAudienceName").to("Duplicated_" + RandomGenerator.randomAlphanumeric(5));
		element(getAudienceSpecificButton(Serenity.sessionVariableCalled("new_audience_name").toString(), "Duplicate")).waitUntilClickable().then().click();
		duplicatedAudienceInputName.waitUntilClickable().then().sendKeys(Serenity.sessionVariableCalled("duplicatedAudienceName").toString());
		duplicateModalButton.waitUntilClickable().then().click();
		duplicateModalButton.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
	}

	public void checkFormatValues() {
		element(getBuyingAudienceByName(Serenity.sessionVariableCalled("duplicatedAudienceName").toString())).waitUntilClickable().then().click();
		if (Serenity.sessionVariableCalled("beforeFormatData") != null) {
			for (int i = 0; i < expandedContentFormatInputs.size(); i++) {
				assertThat(Serenity.sessionVariableCalled("beforeFormatData").toString().contains(expandedContentFormatInputs.get(i).getText()));
			}		

		} else {
			assertThat(expandedContentFormatInputs.size() == Integer.valueOf(Serenity.sessionVariableCalled("emptyList").toString()));	
		}
	}

	public void editAudience() {
		Serenity.setSessionVariable("editedAudienceName").to("Edited" + RandomGenerator.randomAlphanumeric(5));
		element(getAudienceSpecificButton(Serenity.sessionVariableCalled("new_audience_name").toString(), "Edit")).waitUntilClickable().then().click();
		audienceSetupPage.newAudieceInputField.clear();
		audienceSetupPage.newAudieceInputField.sendKeys(Serenity.sessionVariableCalled("editedAudienceName").toString());
		audienceSetupPage.saveTheAudience();
	}

	public void checkAudienceAfterEdit() {
		waitABit(5000);
		assertThat(element(getBuyingAudienceByName(Serenity.sessionVariableCalled("editedAudienceName").toString())).isPresent());
		assertThat(!element(getBuyingAudienceByName(Serenity.sessionVariableCalled("editedAudienceName").toString())).getText().equals(Serenity.sessionVariableCalled("new_audience_name").toString()));
	}

	public void deleteAudience() {
		element(getAudienceSpecificButton(Serenity.sessionVariableCalled("new_audience_name").toString(), "Delete")).waitUntilClickable().then().click();	
		deleteAudienceModalButton.waitUntilClickable().then().click();
		deleteAudienceModalButton.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilNotVisible();
	}

	public void checkAudienceWasDeleted() {
		waitABit(5000);
		element(getBuyingAudienceByName(Serenity.sessionVariableCalled("new_audience_name").toString())).shouldNotBePresent();		
	}
}

