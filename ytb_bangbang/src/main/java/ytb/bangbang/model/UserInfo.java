package ytb.bangbang.model;

public class UserInfo {
    private Integer userId;
    private String nickName;
    private String userHead;
    private String status;
    private String sign;
    private Integer friendsTypeId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getFriendsTypeId() {
        return friendsTypeId;
    }

    public void setFriendsTypeId(Integer friendsTypeId) {
        this.friendsTypeId = friendsTypeId;
    }
}
