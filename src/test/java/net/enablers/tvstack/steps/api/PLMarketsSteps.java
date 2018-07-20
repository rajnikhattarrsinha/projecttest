package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.PlAuthenticateResponseModel;
import net.enablers.tvstack.model.api.PlMarketResponseModel;
import net.enablers.tvstack.services.PointLogicService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PLMarketsSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(PLMarketsSteps.class);
    MongoDbConnection dbConnection = new MongoDbConnection();
    MongoCollection<Document> marketsCollection;


    private Response authenticateResponse, marketsResponse;
    private PlAuthenticateResponseModel authenticateResponseModel;
    private PlMarketResponseModel[] plMarketResponseModel;
    private String accessToken;
    private String oktaAccessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    @Given("^User Authenticate to point logic to get AccessToken$")
    public void userAuthenticateToPointLogicToGetAccessToken() throws Throwable {
        authenticateResponse = PointLogicService.authenticate();
        Assert.assertTrue(authenticateResponse.getStatusCode() == 200);
        authenticateResponseModel = gson().fromJson(authenticateResponse.body().prettyPrint(), PlAuthenticateResponseModel.class);
        setAccessToken(authenticateResponseModel.getAccessToken());
        System.out.println("\nAccess Token : " + authenticateResponseModel.getAccessToken());

    }

    @When("^User request for markets$")
    public void userRequestForMarkets() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        marketsResponse = PointLogicService.getMarkets(oktaAccessToken);
//        marketsResponse = PointLogicService.getMarkets(getAccessToken());
        Assert.assertTrue(marketsResponse.getStatusCode() == 200);
        plMarketResponseModel = gson().fromJson(marketsResponse.body().prettyPrint(), PlMarketResponseModel[].class);
    }

    @Then("^markets are retrieved into response$")
    public void marketsAreRetrievedIntoResponse() throws Throwable {
        System.out.println(plMarketResponseModel.length);
        System.out.println(plMarketResponseModel[0].getMarketId());
    }

    @And("^User query for Markets from database$")
    public void userQueryForMarketsFromDatabase() throws Throwable {
        dbConnection.connect();
        marketsCollection = dbConnection.queryMarkets();

    }

    @Then("^markets are stored into database$")
    public void marketsAreStoredIntoDatabase() throws Throwable {
        for (int marketModel = 0; marketModel < plMarketResponseModel.length; marketModel++) {
            List<Document> marketsFromDb = (List<Document>) marketsCollection.find().into(new ArrayList<Document>());
            for (int number = 0; number < marketsFromDb.size(); number++) {
                if (plMarketResponseModel[marketModel].getMarketId().equals(marketsFromDb.get(number).getString("_id"))) {
                    Assert.assertTrue(plMarketResponseModel[marketModel].getMarketId().equals(marketsFromDb.get(number).getString("_id")));
                }
            }
        }
    }
}
