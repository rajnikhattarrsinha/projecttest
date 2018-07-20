package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import com.mongodb.client.MongoCollection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.CountryResponseModel;
import net.enablers.tvstack.model.api.admin.CountryLeadRequestModel;
import net.enablers.tvstack.services.CountryService;
import net.enablers.tvstack.utilities.MongoDbConnection;
import org.bson.Document;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManageCountriesSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ManageCountriesSteps.class);
    private String oktaAccessToken;
    private MongoDbConnection dbConnection = new MongoDbConnection();

    private Response countriesResponse, createLeadCountryResponse, deleteLeadCountryResponse;
    private CountryResponseModel[] countryResponseModel;
    private MongoCollection<Document> countriesCollection;

    @When("^User requests for Gets the countries$")
    public void userRequestsForGetsTheCountries() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        countriesResponse = CountryService.getCountries(oktaAccessToken);
        Assert.assertTrue(countriesResponse.getStatusCode() == 200);
    }

    @Then("^countries are compared into database$")
    public void countriesAreComparedIntoDatabase() throws Throwable {
        countryResponseModel = gson().fromJson(countriesResponse.body().prettyPrint(), CountryResponseModel[].class);

        dbConnection.connect();
        countriesCollection = dbConnection.queryCountries();

        Assert.assertTrue(countryResponseModel.length == (int) countriesCollection.count());

        dbConnection.disconnect();
    }

    @When("^User requests for Creates a lead for \"([^\"]*)\" country$")
    public void userRequestsForCreatesALeadForCountry(String country, List<CountryLeadRequestModel> leadRequestModelList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createLeadCountryResponse = CountryService.createLeadCountry(oktaAccessToken, country, leadRequestModelList);

        Assert.assertTrue(createLeadCountryResponse.getStatusCode() == 201);
    }

    @Then("^lead \"([^\"]*)\" is created for \"([^\"]*)\" country into database$")
    public void leadIsCreatedForCountryIntoDatabase(String userId, String country) throws Throwable {
        dbConnection.connect();

        countriesCollection = dbConnection.queryCountries();
        Document countryName = countriesCollection.find(eq("_id", country)).first();

        List<Document> leads = (List<Document>) countryName.get("leads");
        for (Document lead : leads) {
            if (lead.get("userId").equals(userId)) {
                System.out.println("UserId : " + lead.get("userId"));
                Assert.assertTrue(lead.get("userId").equals(userId));
            }
        }
        dbConnection.disconnect();
    }


    @Then("^lead \"([^\"]*)\" is deleted for \"([^\"]*)\" country into database$")
    public void leadIsDeletedForCountryIntoDatabase(String userId, String country) throws Throwable {
        dbConnection.connect();

        countriesCollection = dbConnection.queryCountries();
        Document countryName = countriesCollection.find(eq("_id", country)).first();

        List<Document> leads = (List<Document>) countryName.get("leads");
        Assert.assertTrue(leads.size() == 0);

        dbConnection.disconnect();
    }

    @When("^User requests for Deletes a lead \"([^\"]*)\" for \"([^\"]*)\" country$")
    public void userRequestsForDeletesALeadForCountry(String lead, String country) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        deleteLeadCountryResponse = CountryService.deleteLeadCountry(oktaAccessToken, lead, country);

        Assert.assertTrue(deleteLeadCountryResponse.getStatusCode() == 204);
    }
}
