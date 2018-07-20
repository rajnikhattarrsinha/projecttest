package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.*;
import net.enablers.tvstack.model.api.duplicateaudience.DuplicateAudienceRequestModel;
import net.enablers.tvstack.services.AudiencesService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageAudienceSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageAudienceSteps.class);
    private MongoDbConnection dbConnection = new MongoDbConnection();
    private Response createAudiencesResponse, updateAudiencesResponse, deleteAudiencesResponse, duplicateAudienceResponse, questionsResponse, evaluateFilterResponse, selectAudienceResponse;
    private AudienceResponseModel audienceResponseModel;
    private QuestionsResponseModel questionsResponseModel;
    private EvaluateFilterResponseModel evaluateFilterResponseModel;
    private MongoCollection<Document> audiencesCollection, surveysCollection;
    private Object projectId;
    private Object marketId;
    private static String audienceId;
    private String demographics = "{\"id\":\"2a3603b5-4d56-4d1a-aa09-0f6ea6272f0a\",\"queryItem\":{\"id\":1524563600256,\"type\":\"&&\",\"children\":[{\"id\":1524563600255,\"type\":\"&&\",\"matches\":[{\"id\":1524563635367,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Age group recode 1\",\"choice\":\"55-64\",\"filter\":\"field(\"26\") == 6\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private String employmentMedia = "{\"id\":\"7c1bff47-2165-49fe-83ce-2dd420205132\",\"queryItem\":{\"id\":1524577042664,\"type\":\"&&\",\"children\":[{\"id\":1524577042662,\"type\":\"&&\",\"matches\":[{\"id\":1524577112783,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Annual HOUSEHOLD income\",\"choice\":\"$250,000 or more\",\"filter\":\"field(\"303\") == 63\",\"depth\":2},{\"id\":1524577132801,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Use the Internet (on any device)\",\"choice\":\"Every 2-3 months\",\"filter\":\"field(\"1044\") == 2\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private String watchingListeningCinema = "{\"id\":\"7c1bff47-2165-49fe-83ce-2dd420205132\",\"queryItem\":{\"id\":1524577042664,\"type\":\"&&\",\"children\":[{\"id\":1524577448545,\"type\":\"&&\",\"matches\":[{\"id\":1524577474261,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Watch TV\",\"choice\":\"Every day\",\"filter\":\"field(\"1062\") == 8\",\"depth\":2},{\"id\":1524577487625,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Listen to the radio (on a radio)\",\"choice\":\"Once a month\",\"filter\":\"field(\"12463\") == 2\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false},{\"id\":1524577496260,\"type\":\"&&\",\"matches\":[{\"id\":1524577524216,\"type\":\"LogicalCluster\",\"field\":\"\",\"group\":\"O5 Favourite type of film to watch at cinema\",\"choice\":\"Romantic Comedies\",\"filter\":\"field(\"14108\")\",\"depth\":3}],\"children\":[],\"depth\":3,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private String internetCommunicatingMobile = "{\"id\":\"7c1bff47-2165-49fe-83ce-2dd420205132\",\"queryItem\":{\"id\":1524577042664,\"type\":\"&&\",\"children\":[{\"id\":1524577636585,\"type\":\"&&\",\"matches\":[{\"id\":1524577648544,\"type\":\"LogicalCluster\",\"field\":\"\",\"group\":\"Use Internet by Mobile/Tablet\",\"choice\":\"Accesses internet on a mobile phone/smartphone\",\"filter\":\"field(\"1154\")\",\"depth\":2},{\"id\":1524577659885,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Facebook\",\"choice\":\"1-3 days a week\",\"filter\":\"field(\"5926\") == 4\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false},{\"id\":1524577667262,\"type\":\"&&\",\"matches\":[{\"id\":1524577681266,\"type\":\"LogicalCluster\",\"field\":\"\",\"group\":\"T2 Type of mobile phone owned\",\"choice\":\"Blackberry\",\"filter\":\"field(\"3417\")\",\"depth\":3}],\"children\":[],\"depth\":3,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private String attitudesShoppingConsumerWatching = "{\"id\":\"7c1bff47-2165-49fe-83ce-2dd420205132\",\"queryItem\":{\"id\":1524577042664,\"type\":\"&&\",\"children\":[{\"id\":1524577975890,\"type\":\"&&\",\"matches\":[{\"id\":1524577993167,\"type\":\"LogicalCluster\",\"field\":\"\",\"group\":\"Recoded - Any agree\",\"choice\":\"Advertising helps me know what is available\",\"filter\":\"field(\"564\")\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false},{\"id\":1524577993996,\"type\":\"&&\",\"matches\":[{\"id\":1524578012523,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Smartphone\",\"choice\":\"Yes\",\"filter\":\"field(\"3259\") == 1\",\"depth\":3},{\"id\":1524578025357,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Drank a fizzy drink\",\"choice\":\"Longer ago\",\"filter\":\"field(\"14278\") == 1\",\"depth\":3}],\"children\":[],\"depth\":3,\"negate\":false,\"countCoding\":false},{\"id\":1524578028692,\"type\":\"&&\",\"matches\":[{\"id\":1524578043129,\"type\":\"Nominal\",\"field\":\"\",\"group\":\"Watch video on demand/catch-up TV\",\"choice\":\"Every 2-3 months\",\"filter\":\"field(\"1063\") == 2\",\"depth\":4}],\"children\":[],\"depth\":4,\"negate\":false,\"countCoding\":false}],\"depth\":1},\"valid\":true}";
    private String employmentMediaWatching = "{\"id\":\"9cc42ae7-81d4-4900-9ac6-bc2d70590334\",\"queryItem\":{\"id\":1524652645725,\"type\":\"&&\",\"children\":[{\"id\":1524652645724,\"type\":\"&&\",\"matches\":[{\"id\":1524652809070,\"type\":\"LogicalCluster\",\"group\":\"Work status\",\"choice\":\"Work full time\",\"filter\":\"field(\"278\")\",\"depth\":2}],\"children\":[],\"depth\":2,\"negate\":false,\"countCoding\":false},{\"id\":1524652809924,\"type\":\"&&\",\"matches\":[{\"id\":1524652882046,\"type\":\"Nominal\",\"group\":\"Use the Internet (on any device)\",\"choice\":\"Every day\",\"filter\":\"field(\"1044\") == 8\",\"depth\":3}],\"children\":[],\"depth\":3,\"negate\":false,\"countCoding\":false},{\"id\":1524652918529,\"type\":\"&&\",\"matches\":[{\"id\":1524652921531,\"type\":\"Nominal\",\"group\":\"Base for TV questions\",\"choice\":\"Watch TV (BASE)\",\"filter\":\"field(\"1448\") == 1\",\"depth\":4}],\"children\":[],\"depth\":4,\"negate\":false,\"countCoding\":false}],\"depth\":1,\"typeSelected\":true},\"valid\":true}";
    private Long audienceCountBeforeDelete, audienceCountAfterDelete;
    private String oktaAccessToken;

    @When("^User requests for Creates a new audience$")
    public void userRequestsForCreatesANewAudience(List<Audience> audienceList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        projectId = ManageProjectSteps.getProjectId();

        if (audienceList.get(0).getName().equals("Demographics")) {
            createAudiencesResponse = AudiencesService.createAudiences(oktaAccessToken, audienceList, projectId, demographics);
            log.info("Create Audiences Status Code : " + createAudiencesResponse.getStatusCode());
            Assert.assertTrue(createAudiencesResponse.getStatusCode() == 201);

        } else if (audienceList.get(0).getName().equals("Employment_Media_Watching")) {
            createAudiencesResponse = AudiencesService.createAudiences(oktaAccessToken, audienceList, projectId, employmentMediaWatching);
            log.info("Create Audiences Status Code : " + createAudiencesResponse.getStatusCode());
            Assert.assertTrue(createAudiencesResponse.getStatusCode() == 201);

        } else if (audienceList.get(0).getName().equals("Employment_Media")) {
            createAudiencesResponse = AudiencesService.createAudiences(oktaAccessToken, audienceList, projectId, employmentMedia);


        } else if (audienceList.get(0).getName().equals("Internet_Communicating_Mobile")) {
            createAudiencesResponse = AudiencesService.createAudiences(oktaAccessToken, audienceList, projectId, internetCommunicatingMobile);
            log.info("Create Audiences Status Code : " + createAudiencesResponse.getStatusCode());
            Assert.assertTrue(createAudiencesResponse.getStatusCode() == 201);

        } else if (audienceList.get(0).getName().equals("Attitudes_Shopping_Consumer_Watching")) {
            createAudiencesResponse = AudiencesService.createAudiences(oktaAccessToken, audienceList, projectId, attitudesShoppingConsumerWatching);
            log.info("Create Audiences Status Code : " + createAudiencesResponse.getStatusCode());
            Assert.assertTrue(createAudiencesResponse.getStatusCode() == 201);
        }
    }

    @Then("^audience is created to the plan into DB$")
    public void audienceIsCreatedToThePlanIntoDB() throws Throwable {
        audienceResponseModel = gson().fromJson(createAudiencesResponse.body().prettyPrint(), AudienceResponseModel.class);
        audienceId = audienceResponseModel.getId();

        dbConnection.connect();
        audiencesCollection = dbConnection.queryAudiences();

        String audienceName = (String) audiencesCollection.find(eq("projectId", projectId)).first().get("name");
        Assert.assertEquals(AudiencesService.getAudiencesName(), audienceName);

        dbConnection.disconnect();
    }

    @When("^User requests for Updates an existing audience$")
    public void userRequestsForUpdatesAnExistingAudience(List<Audience> audienceList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        updateAudiencesResponse = AudiencesService.updateAudiences(oktaAccessToken, audienceList, projectId, audienceId, watchingListeningCinema);
        log.info("Update Audiences Status Code : " + updateAudiencesResponse.getStatusCode());
        Assert.assertTrue(updateAudiencesResponse.getStatusCode() == 200);
    }

    @Then("^audience is updated to the plan into DB$")
    public void audienceIsUpdatedToThePlanIntoDB() throws Throwable {
        audienceResponseModel = gson().fromJson(updateAudiencesResponse.body().prettyPrint(), AudienceResponseModel.class);

        dbConnection.connect();
        audiencesCollection = dbConnection.queryAudiences();

        String audienceName = (String) audiencesCollection.find(eq("projectId", projectId)).first().get("name");
        Assert.assertEquals(AudiencesService.getUpdatedAudiencesName(), audienceName);

        dbConnection.disconnect();
    }

    @When("^User requests for Delete an existing audience$")
    public void userRequestsForDeleteAnExistingAudience() throws Throwable {

        dbConnection.connect();
        audienceCountBeforeDelete = dbConnection.queryAudiences().count();
        dbConnection.disconnect();
        log.info("Audience Count Before delete : " + audienceCountBeforeDelete);

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        deleteAudiencesResponse = AudiencesService.deleteAudiences(oktaAccessToken, audienceId);
        log.info("Delete Audiences Status Code : " + deleteAudiencesResponse.getStatusCode());
        Assert.assertTrue(deleteAudiencesResponse.getStatusCode() == 200);
    }

    @Then("^audience is deleted to the plan into DB$")
    public void audienceIsDeletedToThePlanIntoDB() throws Throwable {

        dbConnection.connect();
        audienceCountAfterDelete = dbConnection.queryAudiences().count();
        dbConnection.disconnect();

        Assert.assertTrue(audienceCountBeforeDelete - 1 == audienceCountAfterDelete);
        log.info("Audience Count After delete : " + audienceCountAfterDelete);
    }

    @When("^User requests for Duplicates an existing audience$")
    public void userRequestsForDuplicatesAnExistingAudience(List<net.enablers.tvstack.model.api.duplicateaudience.Audience> audiences) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        duplicateAudienceResponse = AudiencesService.duplicateAudience(oktaAccessToken, audiences, projectId, audienceId);
        Assert.assertTrue(duplicateAudienceResponse.getStatusCode() == 201);
    }

    @Then("^Duplicates audience is created into DB$")
    public void duplicatesAudienceIsCreatedIntoDB() throws Throwable {

        dbConnection.connect();

        audiencesCollection = dbConnection.queryAudiences();
        String duplicateAudienceName = (String) audiencesCollection.find(eq("name", AudiencesService.getAudienceDuplicateName())).first().get("name");

        Assert.assertEquals(AudiencesService.getAudienceDuplicateName(), duplicateAudienceName);

        dbConnection.disconnect();

    }

    @Then("^audience service throws error$")
    public void audienceServiceThrowsError() throws Throwable {
        Assert.assertTrue(createAudiencesResponse.getStatusCode() == 400);
        Assert.assertTrue(createAudiencesResponse.getBody().prettyPrint().contains("The sample size is zero."));
    }

    @When("^Users requests questions for a survey")
    public void usersRequestsQuestionsForASurvey() throws Throwable {
        marketId = ManageProjectSteps.getMarketId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        questionsResponse = AudiencesService.getQuestionsForSurvey(oktaAccessToken, marketId);
        Assert.assertTrue(questionsResponse.getStatusCode() == 200);
    }

    @Then("^questions are stored into DB$")
    public void questionsAreStoredToTheMarketsIntoDB() throws Throwable {
        questionsResponseModel = gson().fromJson(questionsResponse.body().prettyPrint(), QuestionsResponseModel.class);

        dbConnection.connect();
        surveysCollection = dbConnection.querySurveys();
        String surveyId = (String) surveysCollection.find(eq("_id", questionsResponseModel.getId())).first().get("_id");
        dbConnection.disconnect();

        Assert.assertTrue(questionsResponseModel.getId().equals(surveyId));

    }

    @When("^Users requests for Evaluates the filter for a survey")
    public void usersRequestsForEvaluatesTheFilterForAAudience(List<EvaluateFilterRequestModel> evaluateFilterRequestModels) throws Throwable {
        marketId = ManageProjectSteps.getMarketId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        evaluateFilterResponse = AudiencesService.evaluateFilterForAudience(oktaAccessToken, evaluateFilterRequestModels, marketId);
        Assert.assertTrue(evaluateFilterResponse.getStatusCode() == 200);
    }

    @Then("^filter value '(\\d+)' is returned for a audience into response$")
    public void filterValueIsReturnedForAAudienceIntoResponse(int size) throws Throwable {
        evaluateFilterResponseModel = gson().fromJson(evaluateFilterResponse.body().prettyPrint(), EvaluateFilterResponseModel.class);
        Assert.assertTrue(evaluateFilterResponseModel.getSize() == size);
    }

    public static String getAudienceId() {
        return audienceId;
    }

    public static void setAudienceId(String audienceId) {
        ManageAudienceSteps.audienceId = audienceId;
    }

    @When("^User requests for Select audiences$")
    public void userRequestsForSelectAudiences() throws Throwable {
        projectId = ManageProjectSteps.getProjectId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        String[] audiences = {};

        selectAudienceResponse = AudiencesService.selectAudiences(oktaAccessToken, projectId, audiences);

        Assert.assertTrue(selectAudienceResponse.getStatusCode() == 204);
    }

    @Then("^Audiences not selected to the project into DB$")
    public void audiencesNotSelectedToTheProjectIntoDB() throws Throwable {
        dbConnection.connect();

        audiencesCollection = dbConnection.queryAudiences();
        Boolean isAudienceSelected = (Boolean) audiencesCollection.find(eq("projectId", ManageProjectSteps.getProjectId())).first().get("selected");

        Assert.assertFalse(isAudienceSelected);

        dbConnection.disconnect();
    }

    @Then("^Audiences is selected to the project into DB$")
    public void audiencesSelectedToTheProjectIntoDB() throws Throwable {
        dbConnection.connect();

        audiencesCollection = dbConnection.queryAudiences();
        Boolean isAudienceSelected = (Boolean) audiencesCollection.find(eq("projectId", ManageProjectSteps.getProjectId())).first().get("selected");

        Assert.assertTrue(isAudienceSelected);

        dbConnection.disconnect();
    }
}
