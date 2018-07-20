package net.enablers.tvstack.model.api.updatechannel;

public class UpdateChannelResponseModel {

    private Boolean selected;
    private int seconds;
    private String channelId;
    private long cpm;
    private CurveCalibration curveCalibration;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public long getCpm() {
        return cpm;
    }

    public void setCpm(long cpm) {
        this.cpm = cpm;
    }

    public CurveCalibration getCurveCalibration() {
        return curveCalibration;
    }

    public void setCurveCalibration(CurveCalibration curveCalibration) {
        this.curveCalibration = curveCalibration;
    }
}
