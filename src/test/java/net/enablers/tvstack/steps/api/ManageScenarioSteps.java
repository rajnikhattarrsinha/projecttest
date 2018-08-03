package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.*;
import net.enablers.tvstack.model.api.scenariocompare.ScenarioCompareResponseModel;
import net.enablers.tvstack.model.api.scenariocompare.Scenarios;
import net.enablers.tvstack.services.ScenarioService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageScenarioSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageScenarioSteps.class);
    private Response createScenarioResponse, deleteScenariosResponse, updateScenarioResponse, optimizeScenarioResponse, compareScenarioResponse;
    private Object projectId = null;
    private static String audienceId;
    private ScenarioResponseModel scenarioResponseModel, updateScenarioResponseModel;
    private ScenarioCompareResponseModel scenarioCompareResponseModel;
    private MongoDbConnection dbConnection = new MongoDbConnection();
    private MongoCollection<Document> scenarioCollection;
    private Long scenariosCountBeforeDelete, scenariosCountAfterDelete;
    private String scenarioId;
    private String oktaAccessToken;
    private Double expBudget;
    List<String> scenarios = new ArrayList<String>();

    @When("^User requests for Creates a new scenario$")
    public void userRequestsForCreatesANewScenario(List<ScenarioRequestModel> scenarioRequestModelList) throws Throwable {
        projectId = ManageProjectSteps.getProjectId();
        audienceId = ManageAudienceSteps.getAudienceId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createScenarioResponse = ScenarioService.createScenario(oktaAccessToken, scenarioRequestModelList, projectId, audienceId);
        Assert.assertTrue(createScenarioResponse.getStatusCode() == 201);
    }

    @Then("^scenario is created to the plan into DB$")
    public void scenarioIsCreatedToThePlanIntoDB() throws Throwable {

        scenarioResponseModel = gson().fromJson(createScenarioResponse.body().prettyPrint(), ScenarioResponseModel.class);

        dbConnection.connect();
        scenarioCollection = dbConnection.queryScenarios();

        String scenarioName = (String) scenarioCollection.find(eq("name", ScenarioService.getScenarioName())).first().get("name");
        Assert.assertEquals(ScenarioService.getScenarioName(), scenarioName);


        projectId = ManageProjectSteps.getProjectId();
        String scenarioProjectId = (String) scenarioCollection.find(eq("name", ScenarioService.getScenarioName())).first().get("projectId");
        Assert.assertEquals(projectId, scenarioProjectId);

        scenarioId = (String) scenarioCollection.find(eq("name", ScenarioService.getScenarioName())).first().get("_id");

        dbConnection.disconnect();

        scenarios.add(scenarioId);

        System.out.println(scenarios);
    }

    @When("^User requests for Delete a existing scenario$")
    public void userRequestsForDeleteAExistingScenario() throws Throwable {

        dbConnection.connect();
        scenariosCountBeforeDelete = dbConnection.queryScenarios().count();
        dbConnection.disconnect();
        log.info("Scenarios Count Before delete : " + scenariosCountBeforeDelete);

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        deleteScenariosResponse = ScenarioService.deleteScenario(oktaAccessToken, scenarioId);
        log.info("Delete Audiences Status Code : " + deleteScenariosResponse.getStatusCode());
        Assert.assertTrue(deleteScenariosResponse.getStatusCode() == 200);
    }

    @Then("^scenario is deleted to the plan into DB$")
    public void scenarioIsDeletedToThePlanIntoDB() throws Throwable {

        dbConnection.connect();
        scenariosCountAfterDelete = dbConnection.queryScenarios().count();
        dbConnection.disconnect();

        Assert.assertTrue(scenariosCountBeforeDelete - 1 == scenariosCountAfterDelete);
        log.info("Audience Count After delete : " + scenariosCountAfterDelete);

    }

    @When("^User requests for Updates a existing scenario$")
    public void userRequestsForUpdatesAExistingScenario(List<ScenarioRequestModel> scenarioRequestModelList) throws Throwable {
        audienceId = ManageAudienceSteps.getAudienceId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        updateScenarioResponse = ScenarioService.updateScenario(oktaAccessToken, scenarioRequestModelList, audienceId, scenarioId);
        Assert.assertTrue(updateScenarioResponse.getStatusCode() == 200);
    }

    @Then("^scenario is updated to the plan into DB$")
    public void scenarioIsUpdatedToThePlanIntoDB() throws Throwable {
        updateScenarioResponseModel = gson().fromJson(updateScenarioResponse.body().prettyPrint(), ScenarioResponseModel.class);

        dbConnection.connect();
        scenarioCollection = dbConnection.queryScenarios();

        String updateScenarioName = (String) scenarioCollection.find(eq("name", ScenarioService.getUpdateScenarioName())).first().get("name");
        Assert.assertEquals(ScenarioService.getUpdateScenarioName(), updateScenarioName);


        projectId = ManageProjectSteps.getProjectId();
        String scenarioProjectId = (String) scenarioCollection.find(eq("name", ScenarioService.getUpdateScenarioName())).first().get("projectId");
        Assert.assertEquals(projectId, scenarioProjectId);

        dbConnection.disconnect();
    }

    @When("^User requests for Optimize a existing scenario$")
    public void userRequestsForOptimizeAExistingScenario(List<ChannelInputs> channelInputsModelList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        optimizeScenarioResponse = ScenarioService.optimizeScenario(oktaAccessToken, channelInputsModelList, scenarioId);
        Assert.assertTrue(optimizeScenarioResponse.getStatusCode() == 200);

        expBudget = channelInputsModelList.get(0).getBudget();
    }

    @Then("^Scenario is Optimized to the plan$")
    public void scenarioIsOptimizedToThePlanIntoDB() throws Throwable {
        scenarioResponseModel = gson().fromJson(optimizeScenarioResponse.body().prettyPrint(), ScenarioResponseModel.class);
        double totalBudget = 0;
        double expTotalBudget = 10000;
        Channels[] channels = scenarioResponseModel.getChannels();
        for (int count = 0; count < channels.length; count++) {
            if (channels[count].getId().equals("TV") && channels[count].getBudget().equals(expBudget)) {
                Assert.assertTrue(true);
                break;
            }
        }

        log.info("TV channels budget is equal to 5000");

        for (int count = 0; count < channels.length; count++) {
            totalBudget = totalBudget + channels[count].getBudget();
        }

        Assert.assertTrue(expTotalBudget == totalBudget);

        log.info("All the channels budget is equal to total budget");
    }

    @When("^User requests for Compare scenarios for an audience$")
    public void userRequestsForCompareScenariosForAnAudience() throws Throwable {
        projectId = ManageProjectSteps.getProjectId();
        audienceId = ManageAudienceSteps.getAudienceId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        compareScenarioResponse = ScenarioService.compareScenario(oktaAccessToken, projectId, audienceId, scenarios);
        Assert.assertTrue(compareScenarioResponse.getStatusCode() == 200);

    }

    @Then("^Scenarios are compared into response$")
    public void scenariosAreComparedIntoResponse() throws Throwable {
        scenarioCompareResponseModel = gson().fromJson(compareScenarioResponse.body().prettyPrint(), ScenarioCompareResponseModel.class);

        Assert.assertTrue(scenarioCompareResponseModel.getPlanningAudienceId().equals(audienceId));
        Assert.assertTrue(scenarioCompareResponseModel.getProjectId().equals(projectId));

        Scenarios[] scenariosList = scenarioCompareResponseModel.getScenarios();
        for (int count = 0; count < scenariosList.length; count++) {
            Assert.assertTrue(scenarios.contains(scenariosList[count].getScenarioId()));
            Assert.assertTrue(scenariosList[count].getComparisonData().length > 0);
        }

    }
}
