package ytb.bangbang.model;

/**
 * @Author hj
 * @Description //离线消息
 * @Date 2018/9/14
 **/
public class OfflineMsgModel {
    //用户id
    private int userId =0;
    //离线消息体
    private String msgBody="";

    private int type;//100 单聊信息; 200 群聊信息 ;300 通知信息(好友验证,系统消息)

    private int isRead;//1 未读  ；2 已读 ；3 过期
    //创建时间
    private String createTime=null;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getCreateTime() {
        return createTime;
    }
}
