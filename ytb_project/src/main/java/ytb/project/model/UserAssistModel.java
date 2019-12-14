package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/3/14 11:08
 */
public class UserAssistModel extends Ytb_Model {
    public static int FLAG_GORUP_MEMBER = 1;
    public static int FLAG_GORUP_MEMBER_NONE = 2;
    public static int UserAssistModel_GROUP_MEMBER = 1;
    public static int UserAssistModel_GROUP_MEMBER_NONE = 2;
    private int flag  ;//1：组员  2非组员
    //0 1 2

    //查询返回结果
    transient  int tradeId ;

    private int talkId = 0;
    private int userId = 0;
    private String userName = "";
    private int money = 0;

    public boolean isGroupMember(){
        return flag==FLAG_GORUP_MEMBER;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }
    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
