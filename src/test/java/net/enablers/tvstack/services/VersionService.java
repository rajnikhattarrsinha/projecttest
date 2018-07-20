package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;

import static com.jayway.restassured.RestAssured.given;

public class VersionService extends ApiHelper {

    public static Response getClientVersion() {
        return given().when().get(ApiHelper.getTvstackApiUrl() + "/version");
    }

    public static Response getAdminVersion() {
        return given().when().get(ApiHelper.getTvstackAdminApiUrl() + "/version");
    }
}
