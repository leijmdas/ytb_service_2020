package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;

public class T_Stop_ActionModel extends Ytb_Model {

    Integer actionId;
    Integer templateId;

    Boolean stop;
    Byte cPhase;
    String stopAction;
    BigDecimal stopQ;
    int penaltyRate;

    public int getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(int penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Byte getcPhase() {
        return cPhase;
    }

    public void setcPhase(Byte cPhase) {
        this.cPhase = cPhase;
    }

    public String getStopAction() {
        return stopAction;
    }

    public void setStopAction(String stopAction) {
        this.stopAction = stopAction;
    }

    public BigDecimal getStopQ() {
        return stopQ;
    }

    public void setStopQ(BigDecimal stopQ) {
        this.stopQ = stopQ;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

}
