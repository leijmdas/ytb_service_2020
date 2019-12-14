package ytb.bangbang.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class UserFriends implements Serializable {
    /**
     * 用户好友表ID
     */
    private Integer userRltnId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 好友ID
     */
    private Integer friendId;

    /**
     * 备注名
     */
    private String remarks;

    /**
     * 好友分组Id
     */
    private Integer friendsTypeId;

    private static final long serialVersionUID = 1L;

    private  String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserRltnId() {
        return userRltnId;
    }

    public void setUserRltnId(Integer userRltnId) {
        this.userRltnId = userRltnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getFriendsTypeId() {
        return friendsTypeId;
    }

    public void setFriendsTypeId(Integer friendsTypeId) {
        this.friendsTypeId = friendsTypeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userRltnId=").append(userRltnId);
        sb.append(", userId=").append(userId);
        sb.append(", friendId=").append(friendId);
        sb.append(", remarks=").append(remarks);
        sb.append(", friendsTypeId=").append(friendsTypeId);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}