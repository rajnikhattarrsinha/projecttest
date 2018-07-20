package net.enablers.tvstack.model.api.scenariocompare;

public class ScenarioCompareResponseModel {
    private String projectId;
    private String planningAudienceId;
    private Scenarios[] scenarios;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPlanningAudienceId() {
        return planningAudienceId;
    }

    public void setPlanningAudienceId(String planningAudienceId) {
        this.planningAudienceId = planningAudienceId;
    }

    public Scenarios[] getScenarios() {
        return scenarios;
    }

    public void setScenarios(Scenarios[] scenarios) {
        this.scenarios = scenarios;
    }
}
