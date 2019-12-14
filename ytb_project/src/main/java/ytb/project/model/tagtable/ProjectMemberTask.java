package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

public class ProjectMemberTask extends Ytb_Model {


    //岗位任务主键
    private int mDutyTaskId;
    //成员岗位id
    private int memberDutyId;
    //任务名称
    private String taskName;
    //任务说明
    private String taskRemark;
    //文档夹ID
    private int folderId;
    //来自计划书或者新加
    private int createMode;
    //创建时间
    private Date createTime;
    //结束时间
    private Date finishTime;
    //关联项目
    private int projectId;
    //项目状态
    private int taskStatus;

    //采购单Id
    private int templateId;


    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getmDutyTaskId() {
        return mDutyTaskId;
    }

    public void setmDutyTaskId(int mDutyTaskId) {
        this.mDutyTaskId = mDutyTaskId;
    }

    public int getMemberDutyId() {
        return memberDutyId;
    }

    public void setMemberDutyId(int memberDutyId) {
        this.memberDutyId = memberDutyId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getCreateMode() {
        return createMode;
    }

    public void setCreateMode(int createMode) {
        this.createMode = createMode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

}
