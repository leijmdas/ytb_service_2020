package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

/**
 * Package: ytb.project.model
 * <p>
 * Description： TODO
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2019/1/12 16:47
 */
public class VProjectTalkConfirmModel  extends Ytb_ModelSkipNull {

    private Integer projectId;
    private Integer projectChangeId;
    private Integer userId1;//甲方ID
    private Integer companyId1;
    private Integer projectTypeId;
    private String projectName;
    private Integer folderId;
    private Integer talkId;
    private Integer status;
    private Integer workGroupNum;
    private Integer viewNumber;
    private Integer favoriteNumber;
    private Boolean isPublish;
    private Boolean isInvite;
    private Date createTime;
    private Date stopTime;
    private Date payDate;
    private Integer phaseNo;
    private Integer changeStatus;
    private Integer changeType;
    private Integer userId2;
    private Integer companyId2;
    private Integer phase;
    private Integer projectPhaseStatus;
    private Integer groupId;
    private Integer documentId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectChangeId() {
        return projectChangeId;
    }

    public void setProjectChangeId(Integer projectChangeId) {
        this.projectChangeId = projectChangeId;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getCompanyId1() {
        return companyId1;
    }

    public void setCompanyId1(Integer companyId1) {
        this.companyId1 = companyId1;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkGroupNum() {
        return workGroupNum;
    }

    public void setWorkGroupNum(Integer workGroupNum) {
        this.workGroupNum = workGroupNum;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

    public Boolean getInvite() {
        return isInvite;
    }

    public void setInvite(Boolean invite) {
        isInvite = invite;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getPhaseNo() {
        return phaseNo;
    }

    public void setPhaseNo(Integer phaseNo) {
        this.phaseNo = phaseNo;
    }

    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
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

    public Integer getProjectPhaseStatus() {
        return projectPhaseStatus;
    }

    public void setProjectPhaseStatus(Integer projectPhaseStatus) {
        this.projectPhaseStatus = projectPhaseStatus;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
}
