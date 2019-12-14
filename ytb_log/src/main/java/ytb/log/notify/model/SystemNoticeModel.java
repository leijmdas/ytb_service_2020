package ytb.log.notify.model;

import java.util.Date;

/**
 * Package: ytb.log.notify.model
 * Author: ZCS
 * Date: Created in 2018/11/22 13:58
 */
public class SystemNoticeModel {

    //表主键
    private Integer id = 0;

    //通知内容
    private String messageText = "";

    //标题
    private String messageTitle = "";

    //类型
    private Integer messageType = 1;

    //创建时间
    private String createTime = "";

    //读取状态
    private Integer readStatus;

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
