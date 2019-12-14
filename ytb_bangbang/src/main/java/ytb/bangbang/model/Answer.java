package ytb.bangbang.model;

import java.util.Date;

/**
 * 申请好友回复表
 * tanchao
 * 2019/3/12
 */
public class Answer {
    private int answerId;
    private int inviteId;//邀请Id
    private int formUserId;
    private int toUserId;
    private String content;//回复内容
    private Date answerTime;//创建时间
    private int isRead;//1:未读,2:已读
    private int type;//来源类型 1：好友申请；2：群申请

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getInviteId() {
        return inviteId;
    }

    public void setInviteId(int inviteId) {
        this.inviteId = inviteId;
    }

    public int getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(int formUserId) {
        this.formUserId = formUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
