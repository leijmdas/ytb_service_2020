package ytb.project.model.tagtable;


import ytb.common.context.model.Ytb_Model;

public class ProjectMemberDutyModel extends Ytb_Model {


    //表主建
    private int memberDutyId;
    //工作组成员id
    private int memberId;
    //洽谈id
    private int talkId;
    //工作岗位id
    private int workJobId;
    //顺序
    private int orderNo;


    public int getMemberDutyId() {
        return memberDutyId;
    }

    public void setMemberDutyId(int memberDutyId) {
        this.memberDutyId = memberDutyId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getWorkJobId() {
        return workJobId;
    }

    public void setWorkJobId(int workJobId) {
        this.workJobId = workJobId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
