package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;
import java.util.Date;

public class WorkGroupModel extends Ytb_Model {


    //表主建
    private int groupId;
    //工作组名
    private String groupName;
    //项目主键
    private int projectId;
    //工作计划书文档ID
    private int documentId;
    //洽谈主键
    private int talkId;
    //图片类型
    private boolean isActive;
    //工作组创建者
    private int createBy;
    //创建时间
    private Date createTime;
    //工作组类型
    private int type;
    //帮帮工作组id
    private int bbGroupId;


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getBbGroupId() {
        return bbGroupId;
    }

    public void setBbGroupId(int bbGroupId) {
        this.bbGroupId = bbGroupId;
    }
}
