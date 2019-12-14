package ytb.bangbang.model;

/**
 * 好友分组表
 * tanchao
 * 2019/3/6
 */

public class FriendsGroup {

    private Integer friendsGroupId;//分组Id
    private String  groupname;//分组名称
    private  Integer userId;//用户Id

    public Integer getFriendsGroupId() {
        return friendsGroupId;
    }

    public void setFriendsGroupId(Integer friendsGroupId) {
        this.friendsGroupId = friendsGroupId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
