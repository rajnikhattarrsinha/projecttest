package net.enablers.tvstack.model.api;

public class ScenarioCompareRequestModel {
    private String audienceId;
    private Object projectId;
    private String[] scenarios;

    public String getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }

    public Object getProjectId() {
        return projectId;
    }

    public void setProjectId(Object projectId) {
        this.projectId = projectId;
    }

    public String[] getScenarios() {
        return scenarios;
    }

    public void setScenarios(String[] scenarios) {
        this.scenarios = scenarios;
    }
}
