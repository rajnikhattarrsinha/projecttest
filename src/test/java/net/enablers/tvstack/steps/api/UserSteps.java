package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.Data;
import net.enablers.tvstack.model.api.admin.UserDetailsResponseModel;
import net.enablers.tvstack.model.api.admin.UserRoles;
import net.enablers.tvstack.model.api.admin.UsersResponseModel;
import net.enablers.tvstack.services.UserService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(UserSteps.class);

    private String oktaAccessToken;
    private Response getUsersResponse, getUserDetailResponse, userDetailResponse, loggedInUserDetailResponse;
    private UsersResponseModel usersResponseModel;
    private UserDetailsResponseModel userDetailsResponseModel;


    @When("^User requests to Get users available in active directory$")
    public void userRequestsToGetUsersAvailableInActiveDirectory() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        getUsersResponse = UserService.getActiveDirectoryUsers(oktaAccessToken);

        Assert.assertTrue(getUsersResponse.getStatusCode() == 200);
    }

    @Then("^Users are retrieved into response$")
    public void usersAreRetrievedIntoResponse() throws Throwable {
        usersResponseModel = gson().fromJson(getUsersResponse.body().prettyPrint(), UsersResponseModel.class);

        Assert.assertTrue(usersResponseModel != null);
    }

    @Then("^Users details are retrieved into response$")
    public void usersDetailsAreRetrievedIntoResponse() throws Throwable {
        usersResponseModel = gson().fromJson(getUserDetailResponse.body().prettyPrint(), UsersResponseModel.class);

        Assert.assertTrue(usersResponseModel.getTimestamp() != null);
        Assert.assertTrue(usersResponseModel.getReturnCode() != null);

        Data[] userData = usersResponseModel.getData();
        Assert.assertTrue(userData[0].getInternalId() != null);
        Assert.assertTrue(userData[0].getCreated() != null);
        Assert.assertTrue(userData[0].getLastLogin() != null);
        Assert.assertTrue(userData[0].getLastUpdated() != null);
        Assert.assertEquals("Subhani.Shaik@dentsuaegis.com", userData[0].getEmail());
        Assert.assertEquals("Subhani", userData[0].getFirstName());
        Assert.assertEquals("Shaik", userData[0].getLastName());
        Assert.assertEquals("Subhani.Shaik@dentsuaegis.com", userData[0].getLogin());
        Assert.assertTrue(userData[0].getStatus() != null);
        Assert.assertTrue(userData[0].getStatusChanged() != null);
    }

    @When("^User requests to Get query for Users \"([^\"]*)\" details$")
    public void userRequestsToGetQueryForUsersDetails(String queryString) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        getUserDetailResponse = UserService.getActiveDirectoryQueryForUsers(oktaAccessToken, queryString);

        Assert.assertTrue(getUserDetailResponse.getStatusCode() == 200);
    }

    @Then("^Users details are not retrieved into response$")
    public void usersDetailsAreNotRetrievedIntoResponse() throws Throwable {
        usersResponseModel = gson().fromJson(getUserDetailResponse.body().prettyPrint(), UsersResponseModel.class);

        Assert.assertTrue(usersResponseModel.getTimestamp() != null);
        Assert.assertTrue(usersResponseModel.getReturnCode() != null);

        Data[] userData = usersResponseModel.getData();

        Assert.assertTrue(userData.length == 0);
    }

    @When("^User requests to Get the User details for given id \"([^\"]*)\"$")
    public void userRequestsToGetTheUserDetailsForGivenId(String userId) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userDetailResponse = UserService.usersDetailsForGivenId(oktaAccessToken, userId);

        Assert.assertTrue(userDetailResponse.getStatusCode() == 200);
    }

    @Then("^Users matching details including user roles are returned into response$")
    public void usersMatchingDetailsIncludingUserRolesAreReturnedIntoResponse() throws Throwable {
        UserDetailsResponseModel userDetailsResponseModel = gson().fromJson(userDetailResponse.body().prettyPrint(), UserDetailsResponseModel.class);

        Assert.assertTrue(userDetailsResponseModel.getTimestamp() != null);
        Assert.assertTrue(userDetailsResponseModel.getReturnCode() != null);

        Data userData = userDetailsResponseModel.getData();
        Assert.assertTrue(userData.getInternalId() != null);
        Assert.assertTrue(userData.getCreated() != null);
        Assert.assertTrue(userData.getLastLogin() != null);
        Assert.assertTrue(userData.getLastUpdated() != null);
        Assert.assertEquals("Subhani.Shaik@dentsuaegis.com", userData.getEmail());
        Assert.assertEquals("Subhani", userData.getFirstName());
        Assert.assertEquals("Shaik", userData.getLastName());
        Assert.assertEquals("Subhani.Shaik@dentsuaegis.com", userData.getLogin());
        Assert.assertTrue(userData.getStatus() != null);
        Assert.assertTrue(userData.getStatusChanged() != null);

        UserRoles[] userRoles = userData.getUserRoles();
        Assert.assertTrue(userRoles[0].getId() != null);
        Assert.assertEquals("Subhani.Shaik@dentsuaegis.com", userRoles[0].getUserId());
        Assert.assertTrue(userRoles[0].getRoleId() != null);
        Assert.assertEquals("Subhani.Shaik@dentsuaegis.com", userRoles[0].getUserId());
        Assert.assertEquals("global", userRoles[0].getRoleLevel());
        Assert.assertEquals("admin", userRoles[0].getRoleType());
        Assert.assertEquals("tv-stack", userRoles[0].getApplicationId());
    }

    @Then("^Users details about the Global admin logged in user are returned into response$")
    public void usersDetailsAboutTheGlobalAdminLoggedInUserAreReturnedIntoResponse() throws Throwable {
        UserDetailsResponseModel userDetailsResponseModel = gson().fromJson(loggedInUserDetailResponse.body().prettyPrint(), UserDetailsResponseModel.class);

        Assert.assertTrue(userDetailsResponseModel.getTimestamp() != null);
        Assert.assertTrue(userDetailsResponseModel.getReturnCode() != null);

        Data userData = userDetailsResponseModel.getData();
        Assert.assertTrue(userData.getInternalId() != null);
        Assert.assertTrue(userData.getCreated() != null);
        Assert.assertTrue(userData.getLastLogin() != null);
        Assert.assertTrue(userData.getLastUpdated() != null);
        Assert.assertEquals("global.admin1@dentsuaegis.com", userData.getEmail());
        Assert.assertEquals("global", userData.getFirstName());
        Assert.assertEquals("admin1", userData.getLastName());
        Assert.assertEquals("global.admin1@dentsuaegis.com", userData.getLogin());
        Assert.assertTrue(userData.getStatus() != null);
        Assert.assertTrue(userData.getStatusChanged() != null);

        UserRoles[] userRoles = userData.getUserRoles();
        Assert.assertTrue(userRoles[0].getId() != null);
        Assert.assertEquals("global.admin1@dentsuaegis.com", userRoles[0].getUserId());
        Assert.assertTrue(userRoles[0].getRoleId() != null);
        Assert.assertEquals("global", userRoles[0].getRoleLevel());
        Assert.assertEquals("admin", userRoles[0].getRoleType());
        Assert.assertEquals("tv-stack", userRoles[0].getApplicationId());
    }

    @When("^Users requests to Get details about the logged in user$")
    public void usersRequestsToGetDetailsAboutTheLoggedInUser() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        loggedInUserDetailResponse = UserService.usersDetailsForLoggedInUserIncludingRoles(oktaAccessToken);

        Assert.assertTrue(loggedInUserDetailResponse.getStatusCode() == 200);
    }

    @Then("^Users details about the Market admin logged in user are returned into response$")
    public void usersDetailsAboutTheMarketAdminLoggedInUserAreReturnedIntoResponse() throws Throwable {
        UserDetailsResponseModel userDetailsResponseModel = gson().fromJson(loggedInUserDetailResponse.body().prettyPrint(), UserDetailsResponseModel.class);

        Assert.assertTrue(userDetailsResponseModel.getTimestamp() != null);
        Assert.assertTrue(userDetailsResponseModel.getReturnCode() != null);

        Data userData = userDetailsResponseModel.getData();
        Assert.assertTrue(userData.getInternalId() != null);
        Assert.assertTrue(userData.getCreated() != null);
        Assert.assertTrue(userData.getLastLogin() != null);
        Assert.assertTrue(userData.getLastUpdated() != null);
        Assert.assertEquals("Market.admin1@dentsuaegis.com", userData.getEmail());
        Assert.assertEquals("Market", userData.getFirstName());
        Assert.assertEquals("admin1", userData.getLastName());
        Assert.assertEquals("Market.admin1@dentsuaegis.com", userData.getLogin());
        Assert.assertTrue(userData.getStatus() != null);
        Assert.assertTrue(userData.getStatusChanged() != null);

        UserRoles[] userRoles = userData.getUserRoles();
        Assert.assertTrue(userRoles[0].getId() != null);
        Assert.assertEquals("Market.admin1@dentsuaegis.com", userRoles[0].getUserId());
        Assert.assertTrue(userRoles[0].getRoleId() != null);
        Assert.assertEquals("market", userRoles[0].getRoleLevel());
        Assert.assertEquals("admin", userRoles[0].getRoleType());
        Assert.assertEquals("tv-stack", userRoles[0].getApplicationId());
    }

    @Then("^Users details about the Client admin logged in user are returned into response$")
    public void usersDetailsAboutTheClientAdminLoggedInUserAreReturnedIntoResponse() throws Throwable {
        UserDetailsResponseModel userDetailsResponseModel = gson().fromJson(loggedInUserDetailResponse.body().prettyPrint(), UserDetailsResponseModel.class);

        Assert.assertTrue(userDetailsResponseModel.getTimestamp() != null);
        Assert.assertTrue(userDetailsResponseModel.getReturnCode() != null);

        Data userData = userDetailsResponseModel.getData();
        Assert.assertTrue(userData.getInternalId() != null);
        Assert.assertTrue(userData.getCreated() != null);
        Assert.assertTrue(userData.getLastLogin() != null);
        Assert.assertTrue(userData.getLastUpdated() != null);
        Assert.assertEquals("client.admin1@dentsuaegis.com", userData.getEmail());
        Assert.assertEquals("client", userData.getFirstName());
        Assert.assertEquals("admin1", userData.getLastName());
        Assert.assertEquals("client.admin1@dentsuaegis.com", userData.getLogin());
        Assert.assertTrue(userData.getStatus() != null);
        Assert.assertTrue(userData.getStatusChanged() != null);

        UserRoles[] userRoles = userData.getUserRoles();
        Assert.assertTrue(userRoles[0].getId() != null);
        Assert.assertEquals("client.admin1@dentsuaegis.com", userRoles[0].getUserId());
        Assert.assertTrue(userRoles[0].getRoleId() != null);
        Assert.assertEquals("client", userRoles[0].getRoleLevel());
        Assert.assertEquals("admin", userRoles[0].getRoleType());
        Assert.assertEquals("tv-stack", userRoles[0].getApplicationId());
    }
}
