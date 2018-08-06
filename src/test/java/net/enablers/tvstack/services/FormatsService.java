package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.FormatRequestModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FormatsService extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);
    private static String formatName, updatedFormatName;

    public static String getFormatName() {
        return formatName;
    }

    public static void setFormatName(String formatName) {
        FormatsService.formatName = formatName;
    }

    public static String getUpdatedFormatName() {
        return updatedFormatName;
    }

    public static void setUpdatedFormatName(String updatedFormatName) {
        FormatsService.updatedFormatName = updatedFormatName;
    }

    public static Response getFormats(String oktaAccessToken) {
        return getTvstackAdminConfig(oktaAccessToken).when().get("/api/formats");
    }

    public static Response createFormat(String oktaToken, String buyingAudienceId, List<FormatRequestModel> formatRequestModelList) {
        Response createFormatResponse = null;

        for (FormatRequestModel formatRequestModel : formatRequestModelList) {
            formatName = formatRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            formatRequestModel.setName(formatName);
            formatRequestModel.setBuyingAudienceId(buyingAudienceId);
            createFormatResponse = getTvstackAdminConfig(oktaToken).body(gson().toJson(formatRequestModel)).post("/api/formats");
        }
        return createFormatResponse;
    }

    public static Response deleteFormat(String oktaAccessToken, String formatId) {
        return getTvstackAdminConfig(oktaAccessToken).delete("/api/formats/" + formatId);
    }


    public static Response updateFormat(String oktaToken, List<FormatRequestModel> formatRequestModelList, String formatId) {
        Response updateFormatResponse = null;

        for (FormatRequestModel formatRequestModel : formatRequestModelList) {
            updatedFormatName = formatRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            formatRequestModel.setName(updatedFormatName);
            updateFormatResponse = getTvstackAdminConfig(oktaToken).body(gson().toJson(formatRequestModel)).put("/api/formats/" + formatId);
        }
        return updateFormatResponse;
    }
}
