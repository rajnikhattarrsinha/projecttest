package net.enablers.tvstack.model.api;

import com.google.gson.annotations.SerializedName;

public class AudienceResponseModel {

    private String formattedFilter;
    private String[] projects;

    @SerializedName("_id")
    private String id;
    private String name;
    private String filterMap;
    private String createDate;
    private String surveyId;
    private String updateDate;
    private String filter;
    private String size;

    public String getFormattedFilter() {
        return formattedFilter;
    }

    public void setFormattedFilter(String formattedFilter) {
        this.formattedFilter = formattedFilter;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(String filterMap) {
        this.filterMap = filterMap;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
