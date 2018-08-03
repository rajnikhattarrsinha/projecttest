package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
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


    @Then("^channels \"([^\"]*)\" are updated to the plan$")
    public void channelsAreUpdatedToThePlan(String channel) throws Throwable {
        updateChannelResponseModels = gson().fromJson(updateChannelResponse.body().prettyPrint(), UpdateChannelResponseModel[].class);

        for (UpdateChannelResponseModel updateChannelResponseModel : updateChannelResponseModels) {
            if (updateChannelResponseModel.getChannelId().equals("TV") || updateChannelResponseModel.getChannelId().equals("OnlineVideo") || updateChannelResponseModel.getChannelId().equals("VOD")) {
                Assert.assertTrue(updateChannelResponseModel.getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getSelected().equals(true));
                Assert.assertTrue(updateChannelResponseModel.getSeconds() == 5);
                Assert.assertTrue(updateChannelResponseModel.getCpm() == 4);
                Assert.assertTrue(updateChannelResponseModel.getCurveCalibration().getGrp() == 1);
                Assert.assertTrue(updateChannelResponseModel.getCurveCalibration().getMaxGrp() == 4);
                Assert.assertTrue(updateChannelResponseModel.getCurveCalibration().getReach() == 1);
                Assert.assertTrue(updateChannelResponseModel.getCurveCalibration().getMaxReach() == 3);
            }
            if (updateChannelResponseModel.getChannelId().equals("Youtube")) {
                Assert.assertTrue(updateChannelResponseModel.getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getSelected().equals(true));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getGender().equals("Male"));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getAge().equals("25-34"));
            }
            if (updateChannelResponseModel.getChannelId().equals("Facebook")) {
                Assert.assertTrue(updateChannelResponseModel.getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getSelected().equals(true));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getGender().equals("Female"));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getAgeMin().equals(18));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getAgeMax().equals(65));
            }
            if (updateChannelResponseModel.getChannelId().equals("Instagram")) {
                Assert.assertTrue(updateChannelResponseModel.getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getSelected().equals(true));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getChannelId().equals(channel));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getGender().equals("Both"));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getAgeMin().equals(18));
                Assert.assertTrue(updateChannelResponseModel.getOptions().getAgeMax().equals(33));
            }
        }
    }
}
