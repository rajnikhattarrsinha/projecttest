package net.enablers.tvstack.model.api.updatechannel;

public class Channels {
    private String id;
    private String formatId;
    private String buyingAudienceId;
    private Boolean selected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getBuyingAudienceId() {
        return buyingAudienceId;
    }

    public void setBuyingAudienceId(String buyingAudienceId) {
        this.buyingAudienceId = buyingAudienceId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
