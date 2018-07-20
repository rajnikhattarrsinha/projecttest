package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.*;
import net.enablers.tvstack.services.BuyingAudienceService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageBuyingAudienceSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageBuyingAudienceSteps.class);


    private MongoCollection<Document> buyingAudiencesCollection;
    private MongoDbConnection dbConnection = new MongoDbConnection();
    private Response createBuyingAudienceResponse, updateBuyingAudiencesResponse, deleteBuyingAudiencesResponse, duplicateBuyingAudienceResponse;
    private Object marketId = null;
    private BuyingAudienceResponseModel audienceResponseModel;
    private String demographics = "{\"id\":\"2a3603b5-4d56-4d1a-aa09-0f6ea6272f0a\",\"queryItem\":{\"id\":1524563600256,\"type\":\"&&\",\"children\":[{\"id\":1524563600255,\"type\":\"&&\",\"matches\":[{\"id\":1524563635367,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Age group recode 1\",\"choice\":\"55-64\",\"filter\":\"field(\"26\") == 6\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private String watchingListeningCinema = "{\"id\":\"7c1bff47-2165-49fe-83ce-2dd420205132\",\"queryItem\":{\"id\":1524577042664,\"type\":\"&&\",\"children\":[{\"id\":1524577448545,\"type\":\"&&\",\"matches\":[{\"id\":1524577474261,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Watch TV\",\"choice\":\"Every day\",\"filter\":\"field(\"1062\") == 8\",\"depth\":2},{\"id\":1524577487625,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Listen to the radio (on a radio)\",\"choice\":\"Once a month\",\"filter\":\"field(\"12463\") == 2\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false},{\"id\":1524577496260,\"type\":\"&&\",\"matches\":[{\"id\":1524577524216,\"type\":\"LogicalCluster\",\"field\":\"\",\"group\":\"O5 Favourite type of film to watch at cinema\",\"choice\":\"Romantic Comedies\",\"filter\":\"field(\"14108\")\",\"depth\":3}],\"children\":[],\"depth\":3,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private static String buyingAudienceId;
    private Long buyingAudienceCountBeforeDelete, buyingAudienceCountAfterDelete;
    private String oktaAccessToken;
    private PlanResponseModel duplicateBuyingAudienceResponseModel;
    private Object duplicateBuyingAudience;

    public static String getBuyingAudienceId() {
        return buyingAudienceId;
    }

    public static void setBuyingAudienceId(String buyingAudienceId) {
        ManageBuyingAudienceSteps.buyingAudienceId = buyingAudienceId;
    }

    @When("^User requests for Create buying audience$")
    public void userRequestsForCreateBuyingAudience(List<BuyingAudienceRequestModel> buyingAudienceRequestModelList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        marketId = ManageProjectSteps.getMarketId();
        if (buyingAudienceRequestModelList.get(0).getName().equals("Demographics")) {
            createBuyingAudienceResponse = BuyingAudienceService.createNewBuyingAudience(buyingAudienceRequestModelList, demographics, oktaAccessToken);
            log.info("Create Buying Audiences Status Code : " + createBuyingAudienceResponse.getStatusCode());
            Assert.assertTrue(createBuyingAudienceResponse.getStatusCode() == 201);
        }
    }

    @Then("^New buying audience is created into DB$")
    public void newBuyingAudienceIsCreatedIntoDB() throws Throwable {

        audienceResponseModel = gson().fromJson(createBuyingAudienceResponse.body().prettyPrint(), BuyingAudienceResponseModel.class);
        buyingAudienceId = audienceResponseModel.getId();

        dbConnection.connect();
        buyingAudiencesCollection = dbConnection.queryBuyingAudiences();

        String buyingAudiencesName = (String) buyingAudiencesCollection.find(eq("name", BuyingAudienceService.getBuyingAudiencesName())).first().get("name");
        Assert.assertEquals(BuyingAudienceService.getBuyingAudiencesName(), buyingAudiencesName);

        dbConnection.disconnect();
    }

    @When("^User requests for Updates an existing buying audience$")
    public void userRequestsForUpdatesAnExistingBuyingAudience(List<BuyingAudienceRequestModel> buyingAudienceRequestModelList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        updateBuyingAudiencesResponse = BuyingAudienceService.updateBuyingAudiences(buyingAudienceRequestModelList, buyingAudienceId, watchingListeningCinema, oktaAccessToken);

        log.info("Update Buying Audiences Status Code : " + updateBuyingAudiencesResponse.getStatusCode());
        Assert.assertTrue(updateBuyingAudiencesResponse.getStatusCode() == 204);
    }

    @Then("^buying audience is updated into DB$")
    public void buyingAudienceIsUpdatedIntoDB() throws Throwable {

        dbConnection.connect();
        buyingAudiencesCollection = dbConnection.queryBuyingAudiences();

        String updatedBuyingAudiencesName = (String) buyingAudiencesCollection.find(eq("name", BuyingAudienceService.getUpdatedBuyingAudiencesName())).first().get("name");

        Assert.assertEquals(BuyingAudienceService.getUpdatedBuyingAudiencesName(), updatedBuyingAudiencesName);

        dbConnection.disconnect();
    }

    @When("^User requests for Deletes an existing buying audience$")
    public void userRequestsForDeletesAnExistingBuyingAudience() throws Throwable {

        dbConnection.connect();
        buyingAudienceCountBeforeDelete = dbConnection.queryBuyingAudiences().count();
        dbConnection.disconnect();
        log.info("Audience Count Before delete : " + buyingAudienceCountBeforeDelete);

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        deleteBuyingAudiencesResponse = BuyingAudienceService.deleteBuyingAudiences(buyingAudienceId, oktaAccessToken);
        log.info("Delete Audiences Status Code : " + deleteBuyingAudiencesResponse.getStatusCode());
        Assert.assertTrue(deleteBuyingAudiencesResponse.getStatusCode() == 204);
    }

    @Then("^buying audience is Deleted into DB$")
    public void buyingAudienceIsDeletedIntoDB() throws Throwable {
        dbConnection.connect();
        buyingAudienceCountAfterDelete = dbConnection.queryBuyingAudiences().count();
        dbConnection.disconnect();

        Assert.assertTrue(buyingAudienceCountBeforeDelete - 1 == buyingAudienceCountAfterDelete);
        log.info("Audience Count After delete : " + buyingAudienceCountAfterDelete);
    }

    @When("^User requests for Duplicates a buying audience$")
    public void userRequestsForDuplicatesABuyingAudience(List<DuplicateRequestModel> duplicateBuyingAudienceRequestModels) throws Throwable {

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        duplicateBuyingAudienceResponse = BuyingAudienceService.duplicateBuyingAudience(duplicateBuyingAudienceRequestModels, buyingAudienceId, oktaAccessToken);

        Assert.assertTrue(duplicateBuyingAudienceResponse.getStatusCode() == 201);
    }

    @Then("^Duplicates buying audience is created into DB$")
    public void duplicatesBuyingAudienceIsCreatedIntoDB() throws Throwable {
        dbConnection.connect();

        buyingAudiencesCollection = dbConnection.queryBuyingAudiences();
        String duplicateBuyingAudiencesName = (String) buyingAudiencesCollection.find(eq("name", BuyingAudienceService.getBuyingAudienceDuplicateName())).first().get("name");

        Assert.assertEquals(duplicateBuyingAudiencesName, BuyingAudienceService.getBuyingAudienceDuplicateName());

        dbConnection.disconnect();
    }
}
