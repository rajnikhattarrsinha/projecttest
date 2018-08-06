package net.enablers.tvstack.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import net.enablers.tvstack.steps.api.PLMarketsSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import static com.jayway.restassured.RestAssured.given;

public class ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ApiHelper.class);
    static String pointLogicAuthenticateUrl = "https://plrbac.pointlogic.com/v1.0/AccessRights/Authenticate";
    // static String pointLogicApiUrl = "http://tvstack-api-dev.azurewebsites.net/api";
    static String pointLogicApiUrl = "http://tvstack-api-stg.azurewebsites.net/api";
    static String tvstackApiUrl = "http://ec2-52-56-105-109.eu-west-2.compute.amazonaws.com:35000";
    static String tvstackAdminApiUrl = "http://ec2-18-130-118-42.eu-west-2.compute.amazonaws.com:35000";
    //    static String roleManagerApiUrl = "http://ec2-18-130-37-40.eu-west-2.compute.amazonaws.com:3010";
    static String roleManagerApiUrl = "http://ec2-18-130-69-200.eu-west-2.compute.amazonaws.com:3010";

    public static String getTvstackApiUrl() {
        return tvstackApiUrl;
    }

    public static void setTvstackApiUrl(String tvstackApiUrl) {
        ApiHelper.tvstackApiUrl = tvstackApiUrl;
    }

    public static String getTvstackAdminApiUrl() {
        return tvstackAdminApiUrl;
    }

    public static void setTvstackAdminApiUrl(String tvstackAdminApiUrl) {
        ApiHelper.tvstackAdminApiUrl = tvstackAdminApiUrl;
    }

    public static Gson gson;

    protected static RequestSpecification getRequestSpecificationForPointLogicAuth() {
        RestAssured.baseURI = URI.create(pointLogicAuthenticateUrl).toString();
        RestAssured.useRelaxedHTTPSValidation();
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("username", "sysjigsawsa@dentsuaegis.com")
                .formParam("password", "XzMhU56Drkj@5aYY");
    }


    protected static RequestSpecification getMarketsFromPointLogic(String token) {
        RestAssured.baseURI = URI.create(pointLogicApiUrl).toString();
        RestAssured.useRelaxedHTTPSValidation();
        return given()
                .header("Accept", "application/json")
                .header("x-configurationKey", "AegisTVStack")
                .header("Authorization", "Bearer " + token);
    }

    protected static RequestSpecification getTvstackConfig(String token) {
        RestAssured.baseURI = URI.create(tvstackApiUrl).toString();
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Authorization", "Bearer " + token);
    }

    protected static RequestSpecification getTvstackAdminConfig(String adminToken) {
        RestAssured.baseURI = URI.create(tvstackAdminApiUrl).toString();
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Authorization", "Bearer " + adminToken);
    }

    protected static RequestSpecification getRoleManagerConfig(String adminToken) {
        RestAssured.baseURI = URI.create(roleManagerApiUrl).toString();
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .header("Authorization", "Bearer " + adminToken);
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }
}
