package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.userroles.UserRoleCreateModel;
import net.enablers.tvstack.model.api.admin.userroles.UserRolesResponseModel;

public class UserRoleService extends ApiHelper {
    public static Response getListOfUserRoles(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles");
    }

    public static Response getListOfUserRolesWithGlobalRoleLevel(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles?roleLevel=global");
    }

    public static Response getListOfUserRolesWithMarketRoleLevel(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles?roleLevel=market");
    }

    public static Response getListOfUserRolesWithUSTargetId(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles?targetId=US");
    }

    public static Response getListOfUserRolesWithUserId(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles?userId=global.admin1@dentsuaegis.com");
    }

    public static Response getListOfUserRolesWithApplicationId(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles?applicationId=tv-stack");
    }

    public static Response getListOfUserRolesWithUniqueIdentifier(String oktaAccessToken, String uniqueId) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles/" + uniqueId);
    }

    public static Response getListOfUserRolesWithUserIdAndRoleLevel(String oktaAccessToken, String userId, String market) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/user-roles?userId=" + userId + "&roleLevel=" + market);
    }

    public static Response createRoleToUser(String oktaAccessToken, UserRoleCreateModel userRolesCreateModel) {
        return getRoleManagerConfig(oktaAccessToken).body(gson().toJson(userRolesCreateModel)).post("/api/user-roles");
    }

    public static Response deleteRoleFromUser(String oktaAccessToken, String id) {
        return getRoleManagerConfig(oktaAccessToken).delete("/api/user-roles/" + id);
    }
}
