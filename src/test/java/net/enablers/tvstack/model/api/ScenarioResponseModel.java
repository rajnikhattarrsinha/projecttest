package net.enablers.tvstack.model.api;

import com.google.gson.annotations.SerializedName;

public class ScenarioResponseModel {

    private Channels[] channels;
    private String projectId;
    private String olvFirstPercent;
    private String updateDate;
    private String tvFirstPercent;
    private String totalBudget;
    private String planningAudience;

    @SerializedName("_id")
    private String _id;
    private String name;
    private String tvOlvSplit;
    private String createDate;
    private String totalReach;
    private String totalGrps;

    public Channels[] getChannels() {
        return channels;
    }

    public void setChannels(Channels[] channels) {
        this.channels = channels;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOlvFirstPercent() {
        return olvFirstPercent;
    }

    public void setOlvFirstPercent(String olvFirstPercent) {
        this.olvFirstPercent = olvFirstPercent;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTvFirstPercent() {
        return tvFirstPercent;
    }

    public void setTvFirstPercent(String tvFirstPercent) {
        this.tvFirstPercent = tvFirstPercent;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getPlanningAudience() {
        return planningAudience;
    }

    public void setPlanningAudience(String planningAudience) {
        this.planningAudience = planningAudience;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTvOlvSplit() {
        return tvOlvSplit;
    }

    public void setTvOlvSplit(String tvOlvSplit) {
        this.tvOlvSplit = tvOlvSplit;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTotalReach() {
        return totalReach;
    }

    public void setTotalReach(String totalReach) {
        this.totalReach = totalReach;
    }

    public String getTotalGrps() {
        return totalGrps;
    }

    public void setTotalGrps(String totalGrps) {
        this.totalGrps = totalGrps;
    }
}