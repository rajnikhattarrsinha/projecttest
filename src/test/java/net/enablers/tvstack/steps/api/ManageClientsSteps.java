package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.ClientResponseModel;
import net.enablers.tvstack.model.api.admin.ClientsLeadRequestModel;
import net.enablers.tvstack.services.ClientsService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageClientsSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageClientsSteps.class);

    private String oktaAccessToken;
    private MongoDbConnection dbConnection = new MongoDbConnection();
    private Response clientsResponse, leadClientsResponse, deleteLeadClientsResponse;
    private ClientResponseModel[] clientResponseModel;
    private MongoCollection<Document> clientsCollection;
    private String clientId;


    @When("^User requests for Gets the clients$")
    public void userRequestsForGetsTheClients() throws Throwable {

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        clientsResponse = ClientsService.getClients(oktaAccessToken);
        Assert.assertTrue(clientsResponse.getStatusCode() == 200);
    }

    @Then("^clients are compared into database$")
    public void clientsAreComparedIntoDatabase() throws Throwable {
        clientResponseModel = gson().fromJson(clientsResponse.body().prettyPrint(), ClientResponseModel[].class);

        dbConnection.connect();

        clientsCollection = dbConnection.queryClients();

        Bson bson = Filters.eq("countryId", "GB");
        List<Document> clientsList = clientsCollection.find(bson).into(new ArrayList<>());

        Assert.assertTrue(clientResponseModel.length == clientsList.size());

        dbConnection.disconnect();
    }

    @And("^User requests for Creates a lead for the clients")
    public void userRequestsForCreatesALeadForTheClients(List<ClientsLeadRequestModel> clientsLeadRequestModels) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        clientResponseModel = gson().fromJson(clientsResponse.body().prettyPrint(), ClientResponseModel[].class);
        clientId = clientResponseModel[0].getId();

        leadClientsResponse = ClientsService.createLeadForClient(oktaAccessToken, clientId, clientsLeadRequestModels);
        Assert.assertTrue(leadClientsResponse.getStatusCode() == 201);

    }

    @Then("^lead \"([^\"]*)\" is created for \"([^\"]*)\" client into database$")
    public void leadIsCreatedForClientIntoDatabase(String clientLead, String country) throws Throwable {
        dbConnection.connect();

        clientsCollection = dbConnection.queryClients();
        Document clientName = clientsCollection.find(eq("countryId", country)).first();

        List<Document> leads = (List<Document>) clientName.get("leads");
        for (Document lead : leads) {
            if (lead.get("userId").equals(clientLead)) {
                System.out.println("UserId : " + lead.get("userId"));
                Assert.assertTrue(lead.get("userId").equals(clientLead));
            }
        }
        dbConnection.disconnect();

    }

    @When("^User requests for Deletes a lead client \"([^\"]*)\" for \"([^\"]*)\" country$")
    public void userRequestsForDeletesALeadClientForCountry(String userId, String country) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        deleteLeadClientsResponse = ClientsService.deleteLeadForClient(oktaAccessToken, clientId, userId);
        Assert.assertTrue(deleteLeadClientsResponse.getStatusCode() == 204);

    }

    @Then("^lead client \"([^\"]*)\" is deleted for \"([^\"]*)\" country into database$")
    public void leadClientIsDeletedForCountryIntoDatabase(String userId, String country) throws Throwable {
        dbConnection.connect();

        clientsCollection = dbConnection.queryClients();
        Document countryName = clientsCollection.find(eq("countryId", country)).first();

        List<Document> leads = (List<Document>) countryName.get("leads");
        Assert.assertTrue(leads.size() == 0);

        dbConnection.disconnect();

    }
}
