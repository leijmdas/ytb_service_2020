package ytb.project.model;

import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;

import java.util.Date;

/**
 *
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/4/11 16:06
 */
public class ProjectChangeModel {
    private int changeId;
    private int projectId;
    private  int talkId;
    private int newProjectId;
    private int changeType;
    private int phase;
    private int phaseStatus;
    private int eventType;
    private String items;
    private Date changeTime;
    private int PaPb;
    public ProjectChangeModel(){

    }

    public static ProjectChangeModel newStopProjectChangeModel(UserProjectContext context) {
        ProjectChangeModel changeModel = new ProjectChangeModel(context);
        changeModel.newProjectId = 0;
        changeModel.changeType = ProjectConstState.CHNAGE_TYPE_STOP;
        changeModel.phaseStatus = 0;
        changeModel.eventType = 0;
        changeModel.items = "";
        return changeModel;
    }

    public ProjectChangeModel(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        this.projectId = pm.getProjectId();
        this.talkId = ptm.getTalkId();
        this.phase = ptm.getPhase();
        this.eventType = 0;

        //this.newProjectId = newProjectId;
        //this.changeType = changeType;
        //this.phaseStatus = phaseStatus;
        //this.items = items;
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(int newProjectId) {
        this.newProjectId = newProjectId;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
    public int getPaPb() {
        return PaPb;
    }

    public void setPaPb(int paPb) {
        PaPb = paPb;
    }

}
