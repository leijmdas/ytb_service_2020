package ytb.project.model;


import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

public class CustomTaskModel  extends Ytb_ModelSkipNull {

    public final  static int TASK_TYPE_Define=1;
    public final  static int TASK_TYPE_Puserchase=2;
    public final  static int TASK_TYPE_Processing=3;

    //自定义任务id
    private int customTaskId;

    //接收人
    private String receiver;

    //任务发起人
    private int userId;

    //项目id
    private int projectId;

    //洽谈ID
    private int talkId;

    //附件文档id
    private int templateId;

    //备注
    private String remark;

    //阶段id
    private int phase;

    //文件夹
    private int taskFolder;

    //任务类型：1其它自定义 2采购 3加工
    private int customType;

    //创建时间

    public int getCustomTaskId() {
        return customTaskId;
    }

    public void setCustomTaskId(int customTaskId) {
        this.customTaskId = customTaskId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getTaskFolder() {
        return taskFolder;
    }

    public void setTaskFolder(int taskFolder) {
        this.taskFolder = taskFolder;
    }

    public int getCustomType() {
        return customType;
    }

    public void setCustomType(int customType) {
        this.customType = customType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Date createTime;



}
