package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.DuplicateRequestModel;
import net.enablers.tvstack.model.api.ProjectErrorModel;
import net.enablers.tvstack.model.api.ProjectRequestModel;
import net.enablers.tvstack.model.api.ProjectUpdateRequestModel;
import net.enablers.tvstack.model.api.admin.ClientResponseModel;
import net.enablers.tvstack.services.ClientsService;
import net.enablers.tvstack.services.ProjectsService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class ManageProjectSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageProjectSteps.class);
    private MongoDbConnection dbConnection = new MongoDbConnection();
    private MongoCollection<Document> marketsCollection, clientsCollection, projectsCollection;
    private Response createPlanResponse, loginResponse, updatePlanResponse, deletePlanResponse, duplicatePlanResponse, clientsResponse, definitivePlanResponse;
    private Object clientId, countryId, duplicateProjectId;
    private PlanResponseModel createPlanResponseModel, duplicatePlanResponseModel;
    private Long projectsCountBeforeDelete, projectCountAfterDelete;
    private ProjectErrorModel errorModel;
    private String oktaAccessToken;
    private ClientResponseModel[] clientResponseModel;
    private String clientName;

    private static Object projectId, marketId;


    public static Object getProjectId() {
        return projectId;
    }

    public static void setProjectId(Object projectId) {
        ManageProjectSteps.projectId = projectId;
    }

    public static Object getMarketId() {
        return marketId;
    }

    public static void setMarketId(Object marketId) {
        ManageProjectSteps.marketId = marketId;
    }

    @Given("^User selects Market and Client$")
    public void userSelectsMarketAndClient() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        clientsResponse = ClientsService.getClients(oktaAccessToken);
        Assert.assertTrue(clientsResponse.getStatusCode() == 200);
        clientResponseModel = gson().fromJson(clientsResponse.body().prettyPrint(), ClientResponseModel[].class);

        for (int count = 0; count <= clientResponseModel.length; count++) {
            if (clientResponseModel[count].getCountryId().equals("AU")) {
                clientName = clientResponseModel[count].getName();
                clientId = clientResponseModel[count].getId();
                break;
            }
        }

        log.info("Client Name : " + clientName);
        log.info("Client Id : " + clientId);
        System.out.println(clientName);
        System.out.println(clientId);

    }

    @When("^User requests for Create new plan$")
    public void userRequestForCreateNewPlan(List<ProjectRequestModel> projectRequestModelList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

//        loginResponse = ProjectsService.logIntoPointLogicServer(oktaAccessToken);
//        Assert.assertTrue(loginResponse.getStatusCode() == 200);

        createPlanResponse = ProjectsService.createProject(oktaAccessToken, projectRequestModelList, clientId);
        createPlanResponseModel = gson().fromJson(createPlanResponse.body().prettyPrint(), PlanResponseModel.class);
        Assert.assertTrue(createPlanResponse.getStatusCode() == 201);

        projectId = createPlanResponseModel.getId();
        setProjectId(projectId);
    }

    @Then("^New plan is created into DB$")
    public void newPlanIsCreatedIntoDB() throws Throwable {
        dbConnection.connect();
        projectsCollection = dbConnection.queryProjects();
        String projectName = (String) projectsCollection.find(eq("name", ProjectsService.getProjectName())).first().get("name");
        Assert.assertEquals(ProjectsService.getProjectName(), projectName);

        marketId = projectsCollection.find(eq("name", ProjectsService.getProjectName())).first().get("marketId");

        System.out.println("Market Id : " + marketId);
        log.info("Market Id : " + marketId);

        dbConnection.disconnect();
    }

    @When("^User requests for Update plan$")
    public void userRequestsForUpdatePlan(List<ProjectUpdateRequestModel> updateRequestModels) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        updatePlanResponse = ProjectsService.updateProject(oktaAccessToken, updateRequestModels, projectId);
        Assert.assertTrue(updatePlanResponse.getStatusCode() == 200);
    }

    @Then("^plan is updated into DB$")
    public void planIsUpdatedIntoDB() throws Throwable {
        dbConnection.connect();

        projectsCollection = dbConnection.queryProjects();
        String projectName = (String) projectsCollection.find(eq("name", ProjectsService.getProjectUpdatedName())).first().get("name");
        Assert.assertEquals(ProjectsService.getProjectUpdatedName(), projectName);

        dbConnection.disconnect();
    }

    @When("^User requests for Delete plan$")
    public void userRequestsForDeletePlan() throws Throwable {
        dbConnection.connect();

        projectsCountBeforeDelete = dbConnection.queryProjects().count();

        dbConnection.disconnect();
        log.info("Project Count Before delete : " + projectsCountBeforeDelete);

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        deletePlanResponse = ProjectsService.deleteProject(oktaAccessToken, projectId);
        Assert.assertTrue(deletePlanResponse.getStatusCode() == 200);
    }

    @Then("^plan is deleted from DB$")
    public void planIsDeletedFromDB() throws Throwable {
        dbConnection.connect();

        projectCountAfterDelete = dbConnection.queryProjects().count();

        dbConnection.disconnect();

        Assert.assertTrue(projectsCountBeforeDelete - 1 == projectCountAfterDelete);
        log.info("Project Count After delete : " + projectCountAfterDelete);

    }

    @When("^User requests for Duplicates a plan$")
    public void userRequestsForDuplicatesAPlan(List<DuplicateRequestModel> duplicatePlanRequestModels) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        duplicatePlanResponse = ProjectsService.duplicteProject(oktaAccessToken, duplicatePlanRequestModels, projectId);
        Assert.assertTrue(createPlanResponse.getStatusCode() == 201);

        duplicatePlanResponseModel = gson().fromJson(duplicatePlanResponse.body().prettyPrint(), PlanResponseModel.class);
        duplicateProjectId = duplicatePlanResponseModel.getId();

        log.info("Duplicate project id  : " + duplicateProjectId);
    }

    @Then("^Duplicates plan is created into DB$")
    public void duplicatesPlanIsCreatedIntoDB() throws Throwable {
        dbConnection.connect();

        projectsCollection = dbConnection.queryProjects();
        String projectName = (String) projectsCollection.find(eq("_id", duplicateProjectId)).first().get("name");

        Assert.assertEquals(projectName, ProjectsService.getProjectDuplicateName());

        dbConnection.disconnect();
    }

    @When("^User requests for Create new plan with below values$")
    public void userRequestsForCreateNewPlanWithBelowValues(List<ProjectRequestModel> projectRequestModelList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        loginResponse = ProjectsService.logIntoPointLogicServer(oktaAccessToken);
        Assert.assertTrue(loginResponse.getStatusCode() == 200);

        createPlanResponse = ProjectsService.createProjectWithInvalidValues(oktaAccessToken, projectRequestModelList, clientId);
        Assert.assertTrue(createPlanResponse.getStatusCode() == 400);
    }

    @Then("^plan request is throwing error '(.*)'")
    public void planRequestIsThrowingErrorMessage(String errorMessage) throws Throwable {
        errorModel = gson().fromJson(createPlanResponse.body().prettyPrint(), ProjectErrorModel.class);
        Assert.assertTrue(errorModel.getError().equals(errorMessage));
    }

    @When("^User requests for Definitive a plan$")
    public void userRequestsForDefinitiveAPlan() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        definitivePlanResponse = ProjectsService.definitiveProject(oktaAccessToken, projectId);
        Assert.assertTrue(definitivePlanResponse.getStatusCode() == 204);
    }

    @Then("^plan is Definitive into DB$")
    public void planIsDefinitiveIntoDB() throws Throwable {

        dbConnection.connect();
        projectsCollection = dbConnection.queryProjects();
        Boolean isDefinitive = (Boolean) projectsCollection.find(eq("name", ProjectsService.getProjectName())).first().get("definitive");

        Assert.assertTrue(isDefinitive);

        dbConnection.disconnect();

    }


}

class PlanResponseModel {
    Object id;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}