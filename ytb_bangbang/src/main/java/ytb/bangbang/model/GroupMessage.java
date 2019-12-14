package ytb.bangbang.model;

import java.util.Date;

public class GroupMessage {
    private Integer messageId;//消息ID
    private Integer type;// 消息类型 1:好友验证 2:群系统消息
    private Integer receiveUserId;//用户Id
    private Integer inviteId;//邀请Id
    private Integer read;//1:未读 2:已读 3:过期
    private Date createTime;//创建时间
    private Group_Apply_InfoModel groupApplyInfoModel;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Group_Apply_InfoModel getGroupApplyInfoModel() {
        return groupApplyInfoModel;
    }

    public void setGroupApplyInfoModel(Group_Apply_InfoModel groupApplyInfoModel) {
        this.groupApplyInfoModel = groupApplyInfoModel;
    }
}
