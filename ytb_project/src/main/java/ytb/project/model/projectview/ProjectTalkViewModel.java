package ytb.project.model.projectview;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

/**
 * Package: ytb.project.model.projectview
 * Author: ZCS
 * Date: Created in 2019/2/14 11:15
 */
public class ProjectTalkViewModel extends Ytb_Model {
    //项目ID
    private int projectId;

    //洽谈ID
    private int talkId;

    //项目阶段
    private int phase;

    //状态
    private int phaseStatus;
    //项目阶段状态
    private int changeStatus;

    //项目变更类型
    private int changeType;

    //项目变更ID
    private int newProjectId;

    //老项目ID
    private int oldTalkId;

    //阶段数
    private int phaseNo;

    //工作组ID
    private int groupId;

    //浏览数
    private int viewNumber;

    //类型
    private String type;

    //完成时间
    private Date finishTime;

    //工作组名
    private String groupName;

    //收藏数
    private int favoriteNumber;

    //生成时间
    private Date enterTime;

    //项目名称
    private String projectName;

    //项目状态
    private int status;

    private int bbGroupId;


    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public int getOldTalkId() {
        return oldTalkId;
    }

    public void setOldTalkId(int oldTalkId) {
        this.oldTalkId = oldTalkId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public int getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(int newProjectId) {
        this.newProjectId = newProjectId;
    }

    public int getBbGroupId() {
        return bbGroupId;
    }

    public void setBbGroupId(int bbGroupId) {
        this.bbGroupId = bbGroupId;
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

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhaseNo() {
        return phaseNo;
    }

    public void setPhaseNo(int phaseNo) {
        this.phaseNo = phaseNo;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public int getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(int changeStatus) {
        this.changeStatus = changeStatus;
    }

}
