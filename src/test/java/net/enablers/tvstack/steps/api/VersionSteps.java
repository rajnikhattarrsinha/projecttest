package net.enablers.tvstack.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.services.VersionService;
import org.junit.Assert;

public class VersionSteps extends ApiHelper {

    Response clientVersionResponse, adminVersionResponse;
    VersionModel versionModel;

    @Given("^TvStack Client application is deployed$")
    public void tvstackClientApplicationIsDeployed() throws Throwable {

    }

    @Given("^TvStack Admin is deployed$")
    public void tvstackAdminIsDeployed() throws Throwable {

    }

    @When("^User request for get TvStack Client Version$")
    public void userRequestForGetTvStackClientVersion() throws Throwable {
        clientVersionResponse = VersionService.getClientVersion();
        Assert.assertTrue(clientVersionResponse.getStatusCode() == 200);
    }

    @And("^Client version number is returned into response$")
    public void clientVersionNumberIsReturnedIntoResponse() throws Throwable {
        versionModel = gson().fromJson(clientVersionResponse.body().prettyPrint(), VersionModel.class);
        Assert.assertTrue(!(versionModel.getVersion().isEmpty()));
    }

    @When("^User request for get TvStack Admin Version$")
    public void userRequestForGetTvStackAdminVersion() throws Throwable {
        adminVersionResponse = VersionService.getAdminVersion();
        Assert.assertTrue(adminVersionResponse.getStatusCode() == 200);
    }

    @And("^Admin version number is returned into response$")
    public void adminVersionNumberIsReturnedIntoResponse() throws Throwable {
        versionModel = gson().fromJson(adminVersionResponse.body().prettyPrint(), VersionModel.class);
        Assert.assertTrue(!(versionModel.getVersion().isEmpty()));
    }


    class VersionModel {
        String version;

        public String getVersion() {
            return version;
        }
    }
}
