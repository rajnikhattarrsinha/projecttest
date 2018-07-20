package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.Audience;
import net.enablers.tvstack.model.api.BuyingAudienceRequestModel;
import net.enablers.tvstack.model.api.DuplicateRequestModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BuyingAudienceService extends ApiHelper {


    private static final Logger log = LoggerFactory.getLogger(BuyingAudienceService.class);
    private static String buyingAudiencesName, updatedBuyingAudiencesName, buyingAudienceDuplicateName;
    static BuyingAudienceRequestModel buyingAudienceRequestModel = new BuyingAudienceRequestModel();

    public static String getBuyingAudiencesName() {
        return buyingAudiencesName;
    }

    public static void setBuyingAudiencesName(String buyingAudiencesName) {
        BuyingAudienceService.buyingAudiencesName = buyingAudiencesName;
    }

    public static String getUpdatedBuyingAudiencesName() {
        return updatedBuyingAudiencesName;
    }

    public static void setUpdatedBuyingAudiencesName(String updatedBuyingAudiencesName) {
        BuyingAudienceService.updatedBuyingAudiencesName = updatedBuyingAudiencesName;
    }


    public static String getBuyingAudienceDuplicateName() {
        return buyingAudienceDuplicateName;
    }

    public static void setBuyingAudienceDuplicateName(String buyingAudienceDuplicateName) {
        BuyingAudienceService.buyingAudienceDuplicateName = buyingAudienceDuplicateName;
    }

    public static Response createNewBuyingAudience(List<BuyingAudienceRequestModel> buyingAudienceRequestModelList, String filterMap, String oktaToken) {
        Response buyingAudienceResponse = null;

        for (BuyingAudienceRequestModel buyingAudienceRequestModel : buyingAudienceRequestModelList) {
            buyingAudiencesName = buyingAudienceRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            buyingAudienceRequestModel.setName(buyingAudiencesName);
            buyingAudienceRequestModel.setFilter(buyingAudienceRequestModel.getFilter().toString());
            buyingAudienceRequestModel.setFormattedFilter(buyingAudienceRequestModel.getFormattedFilter().toString());
            buyingAudienceRequestModel.setFilterMap(filterMap);
            buyingAudienceResponse = getTvstackAdminConfig(oktaToken).body(gson().toJson(buyingAudienceRequestModel)).post("/api/buyingAudiences");
        }
        return buyingAudienceResponse;
    }


    public static Response updateBuyingAudiences(List<BuyingAudienceRequestModel> buyingAudienceRequestModelList, String buyingAudienceId, String filterMap, String oktaToken) {
        Response updateBuyingAudienceResponse = null;

        for (BuyingAudienceRequestModel buyingAudienceRequestModel : buyingAudienceRequestModelList) {
            updatedBuyingAudiencesName = buyingAudienceRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            buyingAudienceRequestModel.setName(updatedBuyingAudiencesName);
            buyingAudienceRequestModel.setFilter(buyingAudienceRequestModel.getFilter().toString());
            buyingAudienceRequestModel.setFormattedFilter(buyingAudienceRequestModel.getFormattedFilter().toString());
            buyingAudienceRequestModel.setFilterMap(filterMap);
            updateBuyingAudienceResponse = getTvstackAdminConfig(oktaToken).body(gson().toJson(buyingAudienceRequestModel)).put("/api/buyingAudiences/" + buyingAudienceId);
        }
        return updateBuyingAudienceResponse;
    }

    public static Response deleteBuyingAudiences(String buyingAudienceId, String oktaToken) {
        return getTvstackAdminConfig(oktaToken).delete("/api/buyingAudiences/" + buyingAudienceId);
    }

    public static Response duplicateBuyingAudience(List<DuplicateRequestModel> duplicateBuyingAudienceRequestModels, String buyingAudienceId, String oktaToken) {
        Response duplicateBuyingAudienceResponse = null;

        for (DuplicateRequestModel duplicateBuyingAudienceRequestModel : duplicateBuyingAudienceRequestModels) {
            buyingAudienceDuplicateName = duplicateBuyingAudienceRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            duplicateBuyingAudienceRequestModel.setName(buyingAudienceDuplicateName);

            duplicateBuyingAudienceResponse = getTvstackAdminConfig(oktaToken).body(gson().toJson(duplicateBuyingAudienceRequestModel)).post("/api/buyingAudiences/" + buyingAudienceId + "/duplicate");
        }
        return duplicateBuyingAudienceResponse;
    }
}
