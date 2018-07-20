package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.roles.RoleDefinitionsResponseModel;
import net.enablers.tvstack.model.api.admin.userroles.UserRoleCreateModel;
import net.enablers.tvstack.model.api.admin.userroles.UserRolesResponseModel;
import net.enablers.tvstack.services.RoleService;
import net.enablers.tvstack.services.UserRoleService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.enablers.tvstack.model.api.admin.userroles.Data;


public class UserRolesSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(UserRolesSteps.class);

    private String oktaAccessToken;
    private Response userRolesResponse, userRolesWithGlobalRoleLevelResponse, userRolesWithMarketRoleLevelResponse, userRolesWithUSTargetIdResponse, userRolesWithUserIdResponse, userRolesWithApplicationIdResponse, userRolesWithUniqueIdentifierResponse, userRolesWithUserIdAndRoleLevelResponse, userRolesCreateResponse, userRoleDeleteResponse, roleDefinitionResponse;
    private UserRolesResponseModel userRolesResponseModel, userRolesCreateModel;
    String uniqueId;
    private RoleDefinitionsResponseModel roleDefinitionsResponseModel;

    @When("^User requests to Get all user roles$")
    public void userRequestsToGetAllUserRoles() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesResponse = UserRoleService.getListOfUserRoles(oktaAccessToken);

        Assert.assertTrue(userRolesResponse.getStatusCode() == 200);
    }

    @Then("^user roles are retrieved into response$")
    public void userRolesAreRetrievedIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);
    }

    @When("^User requests to Get all user roles matching with role level is global$")
    public void userRequestsToGetAllUserRolesMatchingWithRoleLevelIsGlobal() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesWithGlobalRoleLevelResponse = UserRoleService.getListOfUserRolesWithGlobalRoleLevel(oktaAccessToken);

        Assert.assertTrue(userRolesWithGlobalRoleLevelResponse.getStatusCode() == 200);
    }

    @Then("^user roles are retrieved matching with role level is global into response$")
    public void userRolesAreRetrievedMatchingWithRoleLevelIsGlobalIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesWithGlobalRoleLevelResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);

        for (int count = 0; count < userRolesData.length; count++) {
            Assert.assertTrue(userRolesData[count].getRoleLevel().equalsIgnoreCase("global"));
        }

    }

    @When("^User requests to Get all user roles matching with role level is market$")
    public void userRequestsToGetAllUserRolesMatchingWithRoleLevelIsMarket() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesWithMarketRoleLevelResponse = UserRoleService.getListOfUserRolesWithMarketRoleLevel(oktaAccessToken);

        Assert.assertTrue(userRolesWithMarketRoleLevelResponse.getStatusCode() == 200);
    }

    @Then("^user roles are retrieved matching with role level is market into response$")
    public void userRolesAreRetrievedMatchingWithRoleLevelIsMarketIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesWithMarketRoleLevelResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);

        for (int count = 0; count < userRolesData.length; count++) {
            Assert.assertTrue(userRolesData[count].getRoleLevel().equalsIgnoreCase("market"));
        }
    }

    @When("^User requests to Get all user roles matching with target Id is US$")
    public void userRequestsToGetAllUserRolesMatchingWithTargetIdIsUS() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesWithUSTargetIdResponse = UserRoleService.getListOfUserRolesWithUSTargetId(oktaAccessToken);

        Assert.assertTrue(userRolesWithUSTargetIdResponse.getStatusCode() == 200);
    }

    @Then("^user roles are retrieved matching with target Id is US into response$")
    public void userRolesAreRetrievedMatchingWithTargetIdIsUSIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesWithUSTargetIdResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);

        for (int count = 0; count < userRolesData.length; count++) {
            Assert.assertTrue(userRolesData[count].getTargetId().equalsIgnoreCase("US"));
        }
    }

    @When("^User requests to Get all user roles matching with user Id is Global Admin$")
    public void userRequestsToGetAllUserRolesMatchingWithUserIdIsGlobalAdmin() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesWithUserIdResponse = UserRoleService.getListOfUserRolesWithUserId(oktaAccessToken);

        Assert.assertTrue(userRolesWithUserIdResponse.getStatusCode() == 200);

    }

    @Then("^user roles are retrieved matching with user Id is Global Admin into response$")
    public void userRolesAreRetrievedMatchingWithUserIdIsGlobalAdminIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesWithUserIdResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);

        for (int count = 0; count < userRolesData.length; count++) {
            Assert.assertTrue(userRolesData[count].getUserId().equalsIgnoreCase("global.admin1@dentsuaegis.com"));
        }
    }

    @When("^User requests to Get all user roles matching with application Id is tv-stack$")
    public void userRequestsToGetAllUserRolesMatchingWithApplicationIdIsTvStack() throws Throwable {

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesWithApplicationIdResponse = UserRoleService.getListOfUserRolesWithApplicationId(oktaAccessToken);

        Assert.assertTrue(userRolesWithApplicationIdResponse.getStatusCode() == 200);
    }

    @Then("^user roles are retrieved matching with application Id is tv-stack into response$")
    public void userRolesAreRetrievedMatchingWithApplicationIdIsTvStackIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesWithApplicationIdResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);

        for (int count = 0; count < userRolesData.length; count++) {
            Assert.assertTrue(userRolesData[count].getApplicationId().equalsIgnoreCase("tv-stack"));
        }
    }

    @When("^User requests to Get all user roles matching with unique identifier$")
    public void userRequestsToGetAllUserRolesMatchingWithUniqueIdentifier() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        userRolesResponse = UserRoleService.getListOfUserRoles(oktaAccessToken);

        Assert.assertTrue(userRolesResponse.getStatusCode() == 200);


        userRolesResponseModel = gson().fromJson(userRolesResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Data[] userRolesData = userRolesResponseModel.getData();
        uniqueId = userRolesData[0].getId();

        userRolesWithUniqueIdentifierResponse = UserRoleService.getListOfUserRolesWithUniqueIdentifier(oktaAccessToken, uniqueId);

        Assert.assertTrue(userRolesWithUniqueIdentifierResponse.getStatusCode() == 200);
    }

    @Then("^user roles are retrieved matching with unique identifier into response$")
    public void userRolesAreRetrievedMatchingWithUniqueIdentifierIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesWithUniqueIdentifierResponse.body().prettyPrint(), UserRolesResponseModel.class);

        Assert.assertTrue(userRolesResponseModel.getTimestamp() != null);
        Assert.assertTrue(userRolesResponseModel.getReturnCode() != null);

        Data[] userRolesData = userRolesResponseModel.getData();
        Assert.assertTrue(userRolesData.length != 0);

        Assert.assertEquals(uniqueId, userRolesData[0].getId());

        Assert.assertTrue(userRolesData[0].getUserId() != null);
        Assert.assertTrue(userRolesData[0].getRoleId() != null);
        Assert.assertTrue(userRolesData[0].getApplicationId().equalsIgnoreCase("tv-stack"));
    }

    @When("^User requests to Create and Deletes role to the user$")
    public void userRequestsToDeletesUserRolesMatchingWithUniqueIdentifier() throws Throwable {
        String roleId = null;
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitions(oktaAccessToken);
        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);

        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        net.enablers.tvstack.model.api.admin.roles.Data[] rolesData = roleDefinitionsResponseModel.getData();
        for (int count = 0; count <= rolesData.length; count++) {
            if (rolesData[count].getLevel().equalsIgnoreCase("client") && rolesData[count].getType().equalsIgnoreCase("admin")) {
                roleId = rolesData[count].getId();
                break;
            }
        }

        if (roleId != null) {
            UserRoleCreateModel userRoleCreateModel = new UserRoleCreateModel();
            userRoleCreateModel.setUserId("Subhani.Shaik@dentsuaegis.com");
            userRoleCreateModel.setRoleId(roleId);
            userRoleCreateModel.setTargetId("XX");
            userRoleCreateModel.setApplicationId("tv-stack");
            userRolesCreateResponse = UserRoleService.createRoleToUser(oktaAccessToken, userRoleCreateModel);
            Assert.assertTrue(userRolesCreateResponse.getStatusCode() == 200);
            log.info("Role Created to User");

            userRolesResponseModel = gson().fromJson(userRolesCreateResponse.body().prettyPrint(), UserRolesResponseModel.class);
            Data userRoleIdData[] = userRolesResponseModel.getData();
            String userRoleId = userRoleIdData[0].getId();

            userRoleDeleteResponse = UserRoleService.deleteRoleFromUser(oktaAccessToken, userRoleId);
            Assert.assertTrue(userRoleDeleteResponse.getStatusCode() == 200);
            log.info("Role Deleted to User");

        } else {
            System.out.println("Role not found");
        }
    }

    @Then("^User role is Deleted to the user into response$")
    public void userRoleIsDeletedMatchingWithUniqueIdentifierIntoResponse() throws Throwable {

    }
}
