package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.CountryLeadRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CountryService extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);

    public static Response getCountries(String oktaAccessToken) {
        return getTvstackAdminConfig(oktaAccessToken).when().get("/api/countries");
    }

    public static Response createLeadCountry(String oktaAccessToken, String country, List<CountryLeadRequestModel> leadRequestModelList) {
        Response createLeadCountryResponse = null;

        for (CountryLeadRequestModel leadRequestModel : leadRequestModelList) {
            createLeadCountryResponse = getTvstackAdminConfig(oktaAccessToken).body(gson().toJson(leadRequestModel)).post("/api/countries/" + country + "/leads");
        }
        return createLeadCountryResponse;
    }


    public static Response deleteLeadCountry(String oktaAccessToken, String lead, String country) {
        return getTvstackAdminConfig(oktaAccessToken).delete("/api/countries/" + country + "/leads/" + lead);
    }
}
