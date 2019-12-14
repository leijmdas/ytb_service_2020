package ytb.project.model;

import ytb.common.context.model.Ytb_Model;


/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/4/15 16:42
 */
public class TabPhaseModel extends Ytb_Model {
    private Integer phase;

    private Integer phaseStatus;

    private Integer changeType = 0;

    private Integer changeStatus = 0;

    private String phaseText;

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Integer getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(Integer phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }
    public String getPhaseText() {
        return phaseText;
    }

    public TabPhaseModel  setPhaseText(String phaseText) {
        this.phaseText = phaseText;
        return this;
    }
}
