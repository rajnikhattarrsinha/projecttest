package net.enablers.tvstack.model.api.updatechannel;

public class UpdateChannelRequestModel {

    private Channels[] channels;
    private String projectId;

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
}
