package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Project implements Serializable {
    /**
     * 项目标识
     */
    private Integer projectId;

    /**
     * 变更新项目标识
     */
    private Integer projectIdChange;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 甲方
     */
    private Integer userId1;

    /**
     * 甲方公司
     */
    private Integer companyId1;

    /**
     * 项目类别
     */
    private Integer projectTypeId;

    /**
     * 项目文件夹
     */
    private Integer folderId;

    /**
     * 发布状态(1草稿 2发布审核中 3审核未通过 4发布中 5发布停止 6重新发布)
     */
    private Byte status;

    /**
     * 阶段数
     */
    private Byte phaseNo;

    /**
     * 项目开始阶段
     */
    private Short phaseStart;

    /**
     * 浏览数
     */
    private Integer viewNumber;

    /**
     * 收藏数
     */
    private Integer favoriteNumber;

    /**
     * 是否公开
     */
    private Boolean isPublish;

    /**
     * 是否邀请
     */
    private Boolean isInvite;

    /**
     * 变更状态
     */
    private Byte changeStatus;

    /**
     * 变更类型
     */
    private Byte changeType;

    /**
     * 变更子状态
     */
    private Short changeSubStatus;

    /**
     * 创建时间
     */
    private Date enterTime;

    /**
     * 结束时间
     */
    private Date finishTime;

    /**
     * 洽谈支付次数
     */
    private Byte payTimes;

    private static final long serialVersionUID = 1L;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectIdChange() {
        return projectIdChange;
    }

    public void setProjectIdChange(Integer projectIdChange) {
        this.projectIdChange = projectIdChange;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPhaseNo() {
        return phaseNo;
    }

    public void setPhaseNo(Byte phaseNo) {
        this.phaseNo = phaseNo;
    }

    public Short getPhaseStart() {
        return phaseStart;
    }

    public void setPhaseStart(Short phaseStart) {
        this.phaseStart = phaseStart;
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

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    public Boolean getIsInvite() {
        return isInvite;
    }

    public void setIsInvite(Boolean isInvite) {
        this.isInvite = isInvite;
    }

    public Byte getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Byte changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Byte getChangeType() {
        return changeType;
    }

    public void setChangeType(Byte changeType) {
        this.changeType = changeType;
    }

    public Short getChangeSubStatus() {
        return changeSubStatus;
    }

    public void setChangeSubStatus(Short changeSubStatus) {
        this.changeSubStatus = changeSubStatus;
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

    public Byte getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(Byte payTimes) {
        this.payTimes = payTimes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectId=").append(projectId);
        sb.append(", projectIdChange=").append(projectIdChange);
        sb.append(", projectName=").append(projectName);
        sb.append(", userId1=").append(userId1);
        sb.append(", companyId1=").append(companyId1);
        sb.append(", projectTypeId=").append(projectTypeId);
        sb.append(", folderId=").append(folderId);
        sb.append(", status=").append(status);
        sb.append(", phaseNo=").append(phaseNo);
        sb.append(", phaseStart=").append(phaseStart);
        sb.append(", viewNumber=").append(viewNumber);
        sb.append(", favoriteNumber=").append(favoriteNumber);
        sb.append(", isPublish=").append(isPublish);
        sb.append(", isInvite=").append(isInvite);
        sb.append(", changeStatus=").append(changeStatus);
        sb.append(", changeType=").append(changeType);
        sb.append(", changeSubStatus=").append(changeSubStatus);
        sb.append(", enterTime=").append(enterTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", payTimes=").append(payTimes);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}