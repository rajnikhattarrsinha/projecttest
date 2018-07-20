package net.enablers.tvstack.model.api.scenariocompare;

public class Scenarios {

    private String cpm;
    private String scenarioId;
    private String reach;
    private String budget;
    private String costsPerReachPoint;
    private String name;
    private String grps;
    private String frequency;
    private ComparisonData[] comparisonData;

    public String getCpm() {
        return cpm;
    }

    public void setCpm(String cpm) {
        this.cpm = cpm;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCostsPerReachPoint() {
        return costsPerReachPoint;
    }

    public void setCostsPerReachPoint(String costsPerReachPoint) {
        this.costsPerReachPoint = costsPerReachPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrps() {
        return grps;
    }

    public void setGrps(String grps) {
        this.grps = grps;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public ComparisonData[] getComparisonData() {
        return comparisonData;
    }

    public void setComparisonData(ComparisonData[] comparisonData) {
        this.comparisonData = comparisonData;
    }
}
