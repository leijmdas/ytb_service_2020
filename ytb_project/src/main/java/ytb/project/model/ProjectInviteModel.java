package ytb.project.model;

import ytb.project.common.ProjectConstState;
import ytb.common.context.model.Ytb_Model;

import java.util.Date;

/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/2/15 10:08
 */
public class ProjectInviteModel extends Ytb_Model {
    //表主键
    private Integer talkId = 0;

    //项目ID
    private Integer projectId = 0;

    //用户ID
    private Integer userId2 = 0;

    //公司ID
    private Integer companyId2 = 0;

    //阶段
    private Integer phase = 0;

    //1：邀请 2：申请
    private Integer phaseStatus = 0;

    //1邀请中 2已邀请 3拒绝
    private Integer eventType = 1;

    //生成时间
    private Date enterTime = new Date();

    //完成时间
    private Date finishTime = new Date();

    //备注
    private String remark = "";


    public boolean isInvite(){
        return  phaseStatus.equals(ProjectConstState.PROJECT_INVITE);
    }

    public boolean isApply(){
        return  phaseStatus.equals(ProjectConstState.PROJECT_APPLY);
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public Integer getCompanyId2() {
        return companyId2;
    }

    public void setCompanyId2(Integer companyId2) {
        this.companyId2 = companyId2;
    }

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

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public boolean isPb(int userId) {
        return userId == getUserId2();
    }


    public ProjectInviteModel(Integer projectId, Integer userId2, Integer companyId2, Integer phase, Integer phaseStatus, Integer eventType,String remark) {

        this.projectId = projectId;
        this.userId2 = userId2;
        this.companyId2 = companyId2;
        this.phase = phase;
        this.phaseStatus = phaseStatus;
        this.eventType = eventType;
        this.enterTime = new Date();
        this.finishTime = new Date();
        this.remark = remark;

    }
    public ProjectInviteModel() {

    }
}