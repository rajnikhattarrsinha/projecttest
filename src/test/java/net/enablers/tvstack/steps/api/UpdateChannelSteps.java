package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.updatechannel.UpdateChannelResponseModel;
import net.enablers.tvstack.services.ChannelService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UpdateChannelSteps extends ApiHelper {
    private static final Logger log = LoggerFactory.getLogger(UpdateChannelSteps.class);

    private Object projectId = null;
    private String buyingAudienceId;
    private String formatId;
    private String oktaAccessToken;
    private Response updateChannelResponse;
    private UpdateChannelResponseModel[] updateChannelResponseModels;


    @When("^User requests for Update existing channel \"([^\"]*)\" to plan$")
    public void userRequestsForUpdateExistingChannelToPlan(String updateChannel) throws Throwable {
        projectId = ManageProjectSteps.getProjectId();
        buyingAudienceId = ManageBuyingAudienceSteps.getBuyingAudienceId();
        formatId = ManageFormatSteps.getFormatId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        Thread.sleep(10000);

        updateChannelResponse = ChannelService.updateChannelsToThePlan(oktaAccessToken, projectId, updateChannel, buyingAudienceId, formatId);
        Assert.assertTrue(updateChannelResponse.getStatusCode() == 200);
    }


    @Then("^channels are updated to the plan$")
    public void channelsAreUpdatedToThePlan() throws Throwable {
        updateChannelResponseModels = gson().fromJson(updateChannelResponse.body().prettyPrint(), UpdateChannelResponseModel[].class);

        for (int count = 0; count < updateChannelResponseModels.length; count++) {
            if (updateChannelResponseModels[count].getChannelId().equals("TV")) {
                Assert.assertTrue(updateChannelResponseModels[count].getChannelId().equals("TV"));
                Assert.assertTrue(updateChannelResponseModels[count].getSelected().equals(true));
                Assert.assertTrue(updateChannelResponseModels[count].getSeconds() == 5);
                Assert.assertTrue(updateChannelResponseModels[count].getCpm() == 4);
                Assert.assertTrue(updateChannelResponseModels[count].getCurveCalibration().getGrp() == 1);
                Assert.assertTrue(updateChannelResponseModels[count].getCurveCalibration().getMaxGrp() == 4);
                Assert.assertTrue(updateChannelResponseModels[count].getCurveCalibration().getReach() == 1);
                Assert.assertTrue(updateChannelResponseModels[count].getCurveCalibration().getMaxReach() == 3);
            }
        }
    }

}
