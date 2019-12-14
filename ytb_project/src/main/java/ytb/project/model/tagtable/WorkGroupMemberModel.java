package ytb.project.model.tagtable;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

public class WorkGroupMemberModel extends Ytb_Model {
    public static String TABLE_NAME = "ytb_project.work_group_member";

    public final static int IsAdmin_TYPE_PA = 0;
    public final static int IsAdmin_TYPE_PB = 1;
    public final static int IsAdmin_TYPE_PM = 2;

    //表主建
    private int memberId;
    //工作组名
    private int groupId;
    //项目主键
    private int projectId;
    //用户ID
    private int userId;

    //公司ID
    private int companyId;
    //是否是项目负责人
    private int IsAdmin;
    //创建时间
    private Date createTime;
    //顺序
    private int orderNo;
    //洽谈主键
    private int talkId;
    //工作计划书文档ID
    private int documentId;
    //
    private int isActive;

    //组长或者组员
    public boolean isPm() {
        return isActive>0 && getIsAdmin()>IsAdmin_TYPE_PA;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        IsAdmin = isAdmin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
