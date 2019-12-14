package ytb.bangbang.model;

/**
 * @Author hj
 * @Description //好友信息
 * @Date 2018/10/12
 **/
public class FriendsInfoModel {
    //好友Id
    private  int  friendId =  0 ;
    //备注
    private String remarks = "";
    //头像
    private String head= "";

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
