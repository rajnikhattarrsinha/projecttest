package net.enablers.tvstack.model.api.updatechannel;

public class CurveCalibration {
    long grp;
    double reach;
    double maxGrp;
    double maxReach;

    public long getGrp() {
        return grp;
    }

    public void setGrp(long grp) {
        this.grp = grp;
    }

    public double getReach() {
        return reach;
    }

    public void setReach(double reach) {
        this.reach = reach;
    }

    public double getMaxGrp() {
        return maxGrp;
    }

    public void setMaxGrp(double maxGrp) {
        this.maxGrp = maxGrp;
    }

    public double getMaxReach() {
        return maxReach;
    }

    public void setMaxReach(double maxReach) {
        this.maxReach = maxReach;
    }
}
