package net.enablers.tvstack.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.enablers.tvstack.pages.ChannelsSetupPage;
import net.enablers.tvstack.utilities.AppliEyes;

public class ChannelsSetupPageSteps 
{
	ChannelsSetupPage channelsSetupPage;
	 AppliEyes appliEyes;
	 
	@Given("^I'm on Channels setup page$")
	public void i_m_on_Channels_setup_page() throws Throwable 
	{
	  
	}

	@Then("^I should see multiple channels options$")
	public void i_should_see_multiple_channels_options() throws Throwable 
	{
		channelsSetupPage.verifyChannelsPage();
		appliEyes.capture("Channels page is displayed multiple channels option.");
	}

	@Then("^I will select Channel '(.*)' checkbox$")
	public void i_will_select_Channel_TV_checkbox(String Channelname) throws Throwable 
	{
		channelsSetupPage.checkChannelscheckbox(Channelname);
		appliEyes.capture("Channel '"+Channelname+"' checkbox checked successfully.");
	}

	@Then("^I will select any Closest buying Audience option '(.*)' corresponding to Channels '(.*)'$")
	public void i_will_select_any_Closest_buying_Audience_option_userInput_corresponding_to_Channels_userInput(String buyingAudience, String ChannelName) throws Throwable 
	{
		channelsSetupPage.selectClosestbuyingAudienceoption(ChannelName,buyingAudience);
		appliEyes.capture("Closest buying Audience option '"+buyingAudience+"' successfully selected for Channel '"+ChannelName+"'.");
	}

	@Then("^I will select any Second Length/Format option '(.*)' corresponding to Channels '(.*)'$")
	public void i_will_select_any_Second_Length_Format_option_userInput_corresponding_to_Channels_userInput(String secondlengthFormatoption,String ChannelName) throws Throwable 
	{
		channelsSetupPage.selectClosestbuyingAudienceoption(ChannelName,secondlengthFormatoption);
		appliEyes.capture("Second Length format option '"+secondlengthFormatoption+"' successfully selected for Channel '"+ChannelName+"'.");
	}

	@Then("^I will select any Age option '(.*)' corresponding to Channels '(.*)'$")
	public void i_will_select_any_Age_option_userInput_corresponding_to_Channels_userInput(String Age,String Channel) throws Throwable 
	{
		channelsSetupPage.selectAgeforYoutubeChannel(Age);
		appliEyes.capture("Age '"+Age+"' is successfully selected for Channel '"+Channel+"'.");
	}

	@Then("^I will select any Gender option '(.*)' corresponding to Channels '(.*)'$")
	public void i_will_select_any_Gender_option_userInput_corresponding_to_Channels_userInput(String Gender,String Channel) throws Throwable 
	{
		channelsSetupPage.selectGender(Gender, Channel);
	}

	@Then("^I will select any Min Age option 'userInput' corresponding to Channels 'userInput(\\d+)'$")
	public void i_will_select_any_Min_Age_option_userInput_corresponding_to_Channels_userInput(int arg1) throws Throwable 
	{

	}

	@Then("^I will select any Max Age option 'userInput' corresponding to Channels 'userInput(\\d+)'$")
	public void i_will_select_any_Max_Age_option_userInput_corresponding_to_Channels_userInput(int arg1) throws Throwable 
	{

	}

	@Then("^I will click Next: Scenarios button$")
	public void i_will_click_Next_Scenarios_button() throws Throwable 
	{
		channelsSetupPage.clickonNextScenariosbutton();
		appliEyes.capture("Button 'Next Scenario' clicked successfully.");
	}

	@Then("^i should see Scenarios page with Create new scenario button$")
	public void i_should_see_Scenarios_page_with_Create_new_scenario_button() throws Throwable 
	{
		channelsSetupPage.verifyScenariosPage();
		appliEyes.capture("'Scenarios' page open successfully.");
	}
	
	@Then("^I will uncheck all channels options$")
	public void i_will_uncheck_all_channels_options() throws Throwable 
	{
		channelsSetupPage.uncheckAllChannelcheckbox();
		appliEyes.capture("All Channel checkbox uncheck successfully.");
	}

	@Then("^I should see message 'Please select at least (\\d+) channel buying audience and format to proceed'$")
	public void i_should_see_message_Please_select_at_least_channel_buying_audience_and_format_to_proceed(int arg1) throws Throwable 
	{
		channelsSetupPage.verifyMessageonHeader();
		appliEyes.capture("'Please select at least 1 channel buying audience and format to proceed' message displayed.");
	}
}
