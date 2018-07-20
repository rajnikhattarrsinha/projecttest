package net.enablers.tvstack.model.api.admin;

public class FormatRequestModel {
    String channelId;
    String  buyingAudienceId;
    String  name;
    Integer seconds;
    Integer cpm;
    Integer grp;
    Integer reach;
    Integer maxGrp;
    Integer maxReach;
    Integer frequencyCap;
    Integer precision;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getBuyingAudienceId() {
        return buyingAudienceId;
    }

    public void setBuyingAudienceId(String buyingAudienceId) {
        this.buyingAudienceId = buyingAudienceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getCpm() {
        return cpm;
    }

    public void setCpm(Integer cpm) {
        this.cpm = cpm;
    }

    public Integer getGrp() {
        return grp;
    }

    public void setGrp(Integer grp) {
        this.grp = grp;
    }

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

    public Integer getMaxGrp() {
        return maxGrp;
    }

    public void setMaxGrp(Integer maxGrp) {
        this.maxGrp = maxGrp;
    }

    public Integer getMaxReach() {
        return maxReach;
    }

    public void setMaxReach(Integer maxReach) {
        this.maxReach = maxReach;
    }

    public Integer getFrequencyCap() {
        return frequencyCap;
    }

    public void setFrequencyCap(Integer frequencyCap) {
        this.frequencyCap = frequencyCap;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }
}
