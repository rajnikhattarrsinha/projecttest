package net.enablers.tvstack.model.api.updatechannel;

public class UpdateChannelResponseModel {

    private Boolean selected;
    private int seconds;
    private String channelId;
    private Double cpm;
    private CurveCalibration curveCalibration;
    private Options options;

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

    public Double getCpm() {
        return cpm;
    }

    public void setCpm(Double cpm) {
        this.cpm = cpm;
    }

    public CurveCalibration getCurveCalibration() {
        return curveCalibration;
    }

    public void setCurveCalibration(CurveCalibration curveCalibration) {
        this.curveCalibration = curveCalibration;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
