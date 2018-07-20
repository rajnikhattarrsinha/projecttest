package net.enablers.tvstack.model.web;

public class PlanModel {

    private String planName;
    private Object client;
    private Object market;
    private Integer budget;
    private int startDate;
    private int endDate;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    public Object getMarket() {
        return market;
    }

    public void setMarket(Object market) {
        this.market = market;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

	public void setStartDate(int startDate) {
		this.startDate = startDate;		
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public int getEndDate() {
		return endDate;
	}
	
	public int getStartDate() {
		return startDate;
	}
}
