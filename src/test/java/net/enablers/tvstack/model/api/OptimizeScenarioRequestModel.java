package net.enablers.tvstack.model.api;

public class OptimizeScenarioRequestModel {

    private ChannelInputs[] channelInputs;
    private Boolean useAttentiveReach;

    public ChannelInputs[] getChannelInputs() {
        return channelInputs;
    }

    public void setChannelInputs(ChannelInputs[] channelInputs) {
        this.channelInputs = channelInputs;
    }

    public Boolean getUseAttentiveReach() {
        return useAttentiveReach;
    }

    public void setUseAttentiveReach(Boolean useAttentiveReach) {
        this.useAttentiveReach = useAttentiveReach;
    }
}
