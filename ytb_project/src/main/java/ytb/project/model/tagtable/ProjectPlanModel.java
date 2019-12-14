package ytb.project.model.tagtable;


import ytb.common.context.model.Ytb_Model;

import java.util.Date;

public class ProjectPlanModel extends Ytb_Model {


    private int planId;

    private int documentId;

    private Date startTime;

    private String phase;

    private Date phase0;
    private Date phase1;

    private Date phase2;

    private Date phase3;

    private Date phase4;

    private Date phase5;

    private Date phase6;

    public Date getPhase0() {
        return phase0;
    }

    public void setPhase0(Date phase0) {
        this.phase0 = phase0;
    }

    public Date getPhase7() {
        return phase7;
    }

    public void setPhase7(Date phase7) {
        this.phase7 = phase7;
    }

    private Date phase7;

    private Date endTime;



    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public Date getPhase1() {
        return phase1;
    }

    public void setPhase1(Date phase1) {
        this.phase1 = phase1;
    }

    public Date getPhase2() {
        return phase2;
    }

    public void setPhase2(Date phase2) {
        this.phase2 = phase2;
    }

    public Date getPhase3() {
        return phase3;
    }

    public void setPhase3(Date phase3) {
        this.phase3 = phase3;
    }

    public Date getPhase4() {
        return phase4;
    }

    public void setPhase4(Date phase4) {
        this.phase4 = phase4;
    }

    public Date getPhase5() {
        return phase5;
    }

    public void setPhase5(Date phase5) {
        this.phase5 = phase5;
    }

    public Date getPhase6() {
        return phase6;
    }

    public void setPhase6(Date phase6) {
        this.phase6 = phase6;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
