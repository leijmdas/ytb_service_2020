package ytb.project.model;


import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

public class MeetingNoticeModel  extends Ytb_ModelSkipNull {

    //会议通知id
    private int meetingNoticeId;

    //项目id
    private int projectId;

    //阶段id
    private int phase;

    //备注
    private String remark;

    //会议开始时间
    private Date startTime;

    //会议结束时间
    private Date stopTime;

    //议题
    private String issue;

    //发起人
    private int sponsor;

    //参会人
    private String participant;

    //创建时间
    private Date createTime;

    //附件id
    private int documentId;


    public int getMeetingNoticeId() {
        return meetingNoticeId;
    }

    public void setMeetingNoticeId(int meetingNoticeId) {
        this.meetingNoticeId = meetingNoticeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getSponsor() {
        return sponsor;
    }

    public void setSponsor(int sponsor) {
        this.sponsor = sponsor;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
}
