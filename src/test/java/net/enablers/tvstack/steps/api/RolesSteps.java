package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.roles.Data;
import net.enablers.tvstack.model.api.admin.roles.RoleDefinitionsResponseModel;
import net.enablers.tvstack.services.RoleService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RolesSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(RolesSteps.class);

    private String oktaAccessToken;
    private Response roleDefinitionResponse, globalAdminRoleDefinitionResponse;
    private RoleDefinitionsResponseModel roleDefinitionsResponseModel;

    @When("^User requests to Get all roles definitions$")
    public void userRequestsToGetAllRolesDefinitions() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitions(oktaAccessToken);

        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);
    }

    @Then("^Roles definitions including type and level are retrieved into response$")
    public void rolesDefinitionsIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);

        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Global Admin", rolesData[0].getName());
        Assert.assertEquals("admin", rolesData[0].getType());
        Assert.assertEquals("global", rolesData[0].getLevel());

        Assert.assertTrue(rolesData[1].getId() != null);
        Assert.assertEquals("Market Admin", rolesData[1].getName());
        Assert.assertEquals("admin", rolesData[1].getType());
        Assert.assertEquals("market", rolesData[1].getLevel());
        Assert.assertTrue(rolesData[1].getParent() != null);

        Assert.assertTrue(rolesData[2].getId() != null);
        Assert.assertEquals("Client Admin", rolesData[2].getName());
        Assert.assertEquals("admin", rolesData[2].getType());
        Assert.assertEquals("client", rolesData[2].getLevel());
        Assert.assertTrue(rolesData[2].getParent() != null);


        Assert.assertTrue(rolesData[3].getId() != null);
        Assert.assertEquals("User", rolesData[3].getName());
        Assert.assertEquals("user", rolesData[3].getType());
        Assert.assertEquals("client", rolesData[3].getLevel());
        Assert.assertTrue(rolesData[3].getParent() != null);
    }

    @When("^User requests to Get roles definitions for Global Admin$")
    public void userRequestsToGetRolesDefinitionsForGlobalAdmin() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        String globalAdminId = rolesData[0].getId();

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        globalAdminRoleDefinitionResponse = RoleService.getRoleDefinitionForGlobalAdmin(oktaAccessToken, globalAdminId);

        Assert.assertTrue(globalAdminRoleDefinitionResponse.getStatusCode() == 200);

    }

    @Then("^Global Admin Role definition including type and level are retrieved into response$")
    public void globalAdminRoleDefinitionIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {

        roleDefinitionsResponseModel = gson().fromJson(globalAdminRoleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Global Admin", rolesData[0].getName());
        Assert.assertEquals("admin", rolesData[0].getType());
        Assert.assertEquals("global", rolesData[0].getLevel());
    }

}
