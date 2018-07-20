package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.ChannelInputs;
import net.enablers.tvstack.model.api.OptimizeScenarioRequestModel;
import net.enablers.tvstack.model.api.ScenarioCompareRequestModel;
import net.enablers.tvstack.model.api.ScenarioRequestModel;
import net.enablers.tvstack.utilities.RandomGenerator;

import java.util.List;

public class ScenarioService extends ApiHelper {

    private static String scenarioName, updateScenarioName;

    public static String getScenarioName() {
        return scenarioName;
    }

    public static void setScenarioName(String scenarioName) {
        ScenarioService.scenarioName = scenarioName;
    }

    public static String getUpdateScenarioName() {
        return updateScenarioName;
    }

    public static void setUpdateScenarioName(String updateScenarioName) {
        ScenarioService.updateScenarioName = updateScenarioName;
    }

    public static Response createScenario(String oktaToken, List<ScenarioRequestModel> scenarioRequestModelList, Object projectId, String audienceId) {
        Response createScenarioResponse = null;

        for (ScenarioRequestModel scenarioRequestModel : scenarioRequestModelList) {
            scenarioName = scenarioRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            scenarioRequestModel.setName(scenarioName);
            scenarioRequestModel.setAudienceId(audienceId);
            createScenarioResponse = getTvstackConfig(oktaToken).body(gson().toJson(scenarioRequestModel)).post("/api/scenarios/project/" + projectId);
        }
        return createScenarioResponse;
    }

    public static Response deleteScenario(String oktaToken, String scenarioId) {
        return getTvstackConfig(oktaToken).delete("/api/scenarios/" + scenarioId);
    }

    public static Response updateScenario(String oktaToken, List<ScenarioRequestModel> scenarioRequestModelList, String audienceId, String scenarioId) {
        Response updateScenarioResponse = null;

        for (ScenarioRequestModel scenarioRequestModel : scenarioRequestModelList) {
            updateScenarioName = scenarioRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            scenarioRequestModel.setName(updateScenarioName);
            scenarioRequestModel.setAudienceId(audienceId);
            updateScenarioResponse = getTvstackConfig(oktaToken).body(gson().toJson(scenarioRequestModel)).put("/api/scenarios/" + scenarioId);
        }
        return updateScenarioResponse;
    }

    public static Response optimizeScenario(String oktaToken, List<ChannelInputs> channelInputsModelList, String scenarioId) {

        ChannelInputs channelInputs = new ChannelInputs();
        channelInputs.setId(channelInputsModelList.get(0).getId());
        channelInputs.setBudget(channelInputsModelList.get(0).getBudget());

        ChannelInputs[] channelInputs1 = {channelInputs};

        OptimizeScenarioRequestModel optimizeScenarioRequestModel = new OptimizeScenarioRequestModel();
        optimizeScenarioRequestModel.setUseAttentiveReach(true);
        optimizeScenarioRequestModel.setChannelInputs(channelInputs1);
        return getTvstackConfig(oktaToken).body(gson().toJson(optimizeScenarioRequestModel)).post("/api/scenarios/" + scenarioId + "/optimize");
    }

    public static Response compareScenario(String oktaToken, Object projectId, String audienceId, List<String> scenariosList) {

        String[] scenarios = scenariosList.toArray(new String[scenariosList.size()]);

        ScenarioCompareRequestModel scenarioCompareRequestModel = new ScenarioCompareRequestModel();
        scenarioCompareRequestModel.setProjectId(projectId);
        scenarioCompareRequestModel.setAudienceId(audienceId);
        scenarioCompareRequestModel.setScenarios(scenarios);

        return getTvstackConfig(oktaToken).body(gson().toJson(scenarioCompareRequestModel)).post("/api/scenarios/compare");
    }
}
