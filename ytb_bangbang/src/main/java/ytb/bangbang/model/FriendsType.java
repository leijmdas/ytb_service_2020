package ytb.bangbang.model;

/**
 * tanchao
 * 2019/3/13
 */

public class FriendsType {

    private  Integer friendsTypeId;
    private String groupName;//分组名
    private  Integer userId;

    public Integer getFriendsTypeId() {
        return friendsTypeId;
    }

    public void setFriendsTypeId(Integer friendsTypeId) {
        this.friendsTypeId = friendsTypeId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
