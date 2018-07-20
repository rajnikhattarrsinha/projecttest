package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.*;
import net.enablers.tvstack.model.api.duplicateaudience.DuplicateAudienceRequestModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AudiencesService extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(AudiencesService.class);
    private static String audiencesName, updatedAudiencesName, audienceDuplicateName;
    static AudienceRequestModel audienceRequestModel = new AudienceRequestModel();


    public static String getAudiencesName() {
        return audiencesName;
    }

    public static void setAudiencesName(String audiencesName) {
        AudiencesService.audiencesName = audiencesName;
    }

    public static String getUpdatedAudiencesName() {
        return updatedAudiencesName;
    }

    public static void setUpdatedAudiencesName(String updatedAudiencesName) {
        AudiencesService.updatedAudiencesName = updatedAudiencesName;
    }

    public static String getAudienceDuplicateName() {
        return audienceDuplicateName;
    }

    public static void setAudienceDuplicateName(String audienceDuplicateName) {
        AudiencesService.audienceDuplicateName = audienceDuplicateName;
    }

    public static Response createAudiences(String oktaToken, List<Audience> audienceList, Object projectId, String filterMap) {
        Response createAudiencesResponse = null;

        for (Audience audience : audienceList) {
            audiencesName = audience.getName() + RandomGenerator.randomAlphanumeric(3);
            audience.setName(audiencesName);
            audience.setFilter(audience.getFilter().toString());
            audience.setFormattedFilter(audience.getFormattedFilter().toString());
            audience.setFilterMap(filterMap);
            audienceRequestModel.setProjectId(projectId);
            audienceRequestModel.setAudience(audience);
            createAudiencesResponse = getTvstackConfig(oktaToken).body(gson().toJson(audienceRequestModel)).post("/api/audiences");
        }
        return createAudiencesResponse;
    }

    public static Response updateAudiences(String oktaToken, List<Audience> audienceList, Object projectId, String audienceId, String filterMap) {
        Response updateAudiencesResponse = null;

        for (Audience audience : audienceList) {
            updatedAudiencesName = audience.getName() + RandomGenerator.randomAlphanumeric(3);
            audience.setName(updatedAudiencesName);
            audience.setFilter(audience.getFilter().toString());
            audience.setFormattedFilter(audience.getFormattedFilter().toString());
            audience.setFilterMap(filterMap);
            audienceRequestModel.setProjectId(projectId);
            audienceRequestModel.setAudience(audience);
            updateAudiencesResponse = getTvstackConfig(oktaToken).body(gson().toJson(audienceRequestModel)).put("/api/audiences/" + audienceId);
        }
        return updateAudiencesResponse;
    }

    public static Response deleteAudiences(String oktaToken, String audienceId) {
        return getTvstackConfig(oktaToken).delete("/api/audiences/" + audienceId);
    }

    public static Response duplicateAudience(String oktaToken, List<net.enablers.tvstack.model.api.duplicateaudience.Audience> audiences, Object projectId, String audienceId) {
        Response duplicateAudienceResponse = null;

        DuplicateAudienceRequestModel duplicateAudienceRequestModel = new DuplicateAudienceRequestModel();

        for (net.enablers.tvstack.model.api.duplicateaudience.Audience audienceModel : audiences) {
            audienceDuplicateName = audienceModel.getName() + RandomGenerator.randomAlphanumeric(3);
            audienceModel.setName(audienceDuplicateName);

            duplicateAudienceRequestModel.setProjectId(projectId);
            duplicateAudienceRequestModel.setAudience(audienceModel);

            duplicateAudienceResponse = getTvstackConfig(oktaToken).body(gson().toJson(duplicateAudienceRequestModel)).post("/api/audiences/" + audienceId + "/duplicate");
        }
        return duplicateAudienceResponse;
    }

    public static Response getQuestionsForSurvey(String oktaToken, Object marketId) {
        return getTvstackConfig(oktaToken).get("/api/surveys/" + marketId);
    }

    public static Response evaluateFilterForAudience(String oktaToken, List<EvaluateFilterRequestModel> evaluateFilterRequestModels, Object marketId) {
        Response evaluateFilterResponse = null;
        for (EvaluateFilterRequestModel evaluateFilterRequestModel : evaluateFilterRequestModels) {
            evaluateFilterRequestModel.setFilter(evaluateFilterRequestModel.getFilter().toString());
            evaluateFilterResponse = getTvstackConfig(oktaToken).body(gson().toJson(evaluateFilterRequestModel)).post("/api/surveys/" + marketId + "/evaluate");
        }
        return evaluateFilterResponse;
    }

    public static Response selectAudiences(String oktaToken, Object projectId, String[] audiences) {

        SelectAudienceRequestModel selectAudienceRequestModel = new SelectAudienceRequestModel();
        selectAudienceRequestModel.setAudiences(audiences);

        return getTvstackConfig(oktaToken).body(gson().toJson(selectAudienceRequestModel)).put("/api/projects/" + projectId + "/audiences");
    }
}
