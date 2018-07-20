package net.enablers.tvstack.model.api;

import com.google.gson.annotations.SerializedName;

public class QuestionsResponseModel {

    @SerializedName("_id")
    private String id;
    private String marketId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
