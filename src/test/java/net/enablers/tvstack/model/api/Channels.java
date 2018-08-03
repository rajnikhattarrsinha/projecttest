package net.enablers.tvstack.model.api;

public class Channels {
    private String id;
    private Double budget;
    private BuyingAudienceResults buyingAudienceResults;
    private PlanningAudienceResults planningAudienceResults;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BuyingAudienceResults getBuyingAudienceResults() {
        return buyingAudienceResults;
    }

    public void setBuyingAudienceResults(BuyingAudienceResults buyingAudienceResults) {
        this.buyingAudienceResults = buyingAudienceResults;
    }

    public PlanningAudienceResults getPlanningAudienceResults() {
        return planningAudienceResults;
    }

    public void setPlanningAudienceResults(PlanningAudienceResults planningAudienceResults) {
        this.planningAudienceResults = planningAudienceResults;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
