package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.FormatRequestModel;
import net.enablers.tvstack.model.api.admin.FormatResponseModel;
import net.enablers.tvstack.services.FormatsService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageFormatSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageFormatSteps.class);

    private String oktaAccessToken;
    private Response formatsResponse, createFormatResponse, deleteFormatResponse, updateFormatResponse;
    private MongoDbConnection dbConnection = new MongoDbConnection();
    private MongoCollection<Document> formatsCollection, createFormatsCollection;
    private String buyingAudienceId;
    private static String formatId;
    private long formatCountBeforeDelete, formatCountAfterDelete;

    public static String getFormatId() {
        return formatId;
    }

    public static void setFormatId(String formatId) {
        ManageFormatSteps.formatId = formatId;
    }

    @When("^User requests for Gets the formats$")
    public void userRequestsForGetsTheFormats() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        formatsResponse = FormatsService.getFormats(oktaAccessToken);
        Assert.assertTrue(formatsResponse.getStatusCode() == 200);
    }

    @Then("^formats are compared into database$")
    public void formatsAreComparedIntoDatabase() throws Throwable {
        FormatResponseModel[] formatResponseModel = gson().fromJson(formatsResponse.body().prettyPrint(), FormatResponseModel[].class);

        dbConnection.connect();
        formatsCollection = dbConnection.queryFormats();
        Bson bson = Filters.eq("countryId", "GB");
        List<Document> formats = formatsCollection.find(bson).into(new ArrayList<>());
        Assert.assertTrue(formatResponseModel.length == formats.size());

        dbConnection.disconnect();
    }


    @When("^User requests for Creates a new format$")
    public void userRequestsForCreatesANewFormat(List<FormatRequestModel> formatRequestModelList) throws Throwable {
        buyingAudienceId = ManageBuyingAudienceSteps.getBuyingAudienceId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createFormatResponse = FormatsService.createFormat(oktaAccessToken, buyingAudienceId, formatRequestModelList);

        Assert.assertTrue(createFormatResponse.getStatusCode() == 201);
    }

    @Then("^New format is created into DB$")
    public void newFormatIsCreatedIntoDB() throws Throwable {
        FormatResponseModel createFormatResponseModel = gson().fromJson(createFormatResponse.body().prettyPrint(), FormatResponseModel.class);
        formatId = createFormatResponseModel.getId();

        dbConnection.connect();

        createFormatsCollection = dbConnection.queryFormats();
        String formatName = (String) createFormatsCollection.find(eq("name", FormatsService.getFormatName())).first().get("name");

        Assert.assertEquals(FormatsService.getFormatName(), formatName);

        dbConnection.disconnect();
    }

    @When("^User requests for Deletes an existing format$")
    public void userRequestsForDeletesAnExistingFormat() throws Throwable {

        dbConnection.connect();
        formatCountBeforeDelete = dbConnection.queryFormats().count();
        log.info("Format Count Before delete : " + formatCountBeforeDelete);
        dbConnection.disconnect();

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        deleteFormatResponse = FormatsService.deleteFormat(oktaAccessToken, formatId);

        Assert.assertTrue(deleteFormatResponse.getStatusCode() == 204);

    }

    @Then("^Fotmat is deleted from DB$")
    public void fotmatIsDeletedFromDB() throws Throwable {
        dbConnection.connect();
        formatCountAfterDelete = dbConnection.queryFormats().count();
        dbConnection.disconnect();

        Assert.assertTrue(formatCountBeforeDelete - 1 == formatCountAfterDelete);
        log.info("Format Count After delete : " + formatCountAfterDelete);
    }

    @When("^User requests for Updates an existing format$")
    public void userRequestsForUpdatesAnExistingFormat(List<FormatRequestModel> formatRequestModelList) throws Throwable {
        buyingAudienceId = ManageBuyingAudienceSteps.getBuyingAudienceId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        updateFormatResponse = FormatsService.updateFormat(oktaAccessToken, formatRequestModelList, formatId);
        Assert.assertTrue(updateFormatResponse.getStatusCode() == 204);
    }

    @Then("^format is updated into DB$")
    public void formatIsUpdatedIntoDB() throws Throwable {

        dbConnection.connect();

        createFormatsCollection = dbConnection.queryFormats();
        String updatedFormatName = (String) createFormatsCollection.find(eq("name", FormatsService.getUpdatedFormatName())).first().get("name");

        Assert.assertEquals(FormatsService.getUpdatedFormatName(), updatedFormatName);

        dbConnection.disconnect();
    }
}
