package ytb.bangbang.model;

import java.util.Date;

/**
 * @Author hj
 * @Description //群组公告
 * @Date 2018/10/10
 **/
public class GroupNoticeModel {
    //群组公告id
    private int noticeId;
    //群组id
    private int groupId;
    //群公告标题
    private String title;
    //群组公告消息
    private String noticeMsg;
    //群组公告创建时间
    private Date createTime;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNoticeMsg() {
        return noticeMsg;
    }

    public void setNoticeMsg(String noticeMsg) {
        this.noticeMsg = noticeMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
