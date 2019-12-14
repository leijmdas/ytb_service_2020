package ytb.bangbang.model;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/16
 */
public class FriendGroupInfo {

    List<UserFriends> userFriends;

    //    List<GroupListPojo> groups;
    public List<UserFriends> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(List<UserFriends> userFriends) {
        this.userFriends = userFriends;
    }
}
