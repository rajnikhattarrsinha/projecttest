package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;

public class RoleService extends ApiHelper {

    public static Response getRolesDefinitions(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles");
    }

    public static Response getRoleDefinitionForGlobalAdmin(String oktaAccessToken, String globalAdminId) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles/" + globalAdminId);
    }
}
