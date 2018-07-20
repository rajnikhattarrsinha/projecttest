package net.enablers.tvstack.model.api;

public class PlMarketResponseModel {
    private String marketId;
    private CampaignLenghts[] campaignLenghts;
    private String name;
    private MetaData metaData;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public CampaignLenghts[] getCampaignLenghts() {
        return campaignLenghts;
    }

    public void setCampaignLenghts(CampaignLenghts[] campaignLenghts) {
        this.campaignLenghts = campaignLenghts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
