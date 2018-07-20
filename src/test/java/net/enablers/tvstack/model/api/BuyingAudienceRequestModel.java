package net.enablers.tvstack.model.api;

public class BuyingAudienceRequestModel {
    private String formattedFilter;
    private String name;
    private String filterMap;
    private String filter;
    private String channelId;

    public String getFormattedFilter() {
        return formattedFilter;
    }

    public void setFormattedFilter(String formattedFilter) {
        this.formattedFilter = formattedFilter;
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

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
