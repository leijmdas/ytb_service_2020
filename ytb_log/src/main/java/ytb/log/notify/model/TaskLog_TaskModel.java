package ytb.log.notify.model;

import java.util.Date;

/**
 * Package: ytb.project.model
 * Author: lzz
 * Date: Created in 2018/8/23 16:29
 */
public class TaskLog_TaskModel {

    //模板ID
    private int taskId ;
    //发放方
    private int userId ;
    //接收方
    private int toUserId;
    //工作流实例
    private  String activitiInstId;
    //任务策略类型
    private int templateType;
    //任务模板ID
    private int templateId;
    //任务输入参数json
    private String taskParamIn;
    //任务输出参数json
    private String taskParamOut;
    //受理结果
    private String taskRemark;
    //task受理时间
    private Date acceptTime;
    //完成时间
    private Date finishTime;
    //任务受理状态
    private int taskStat;
    //实际处理人
    private int createBy;


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getActivitiInstId() {
        return activitiInstId;
    }

    public void setActivitiInstId(String activitiInstId) {
        this.activitiInstId = activitiInstId;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTaskParamIn() {
        return taskParamIn;
    }

    public void setTaskParamIn(String taskParamIn) {
        this.taskParamIn = taskParamIn;
    }

    public String getTaskParamOut() {
        return taskParamOut;
    }

    public void setTaskParamOut(String taskParamOut) {
        this.taskParamOut = taskParamOut;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getTaskStat() {
        return taskStat;
    }

    public void setTaskStat(int taskStat) {
        this.taskStat = taskStat;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
}
