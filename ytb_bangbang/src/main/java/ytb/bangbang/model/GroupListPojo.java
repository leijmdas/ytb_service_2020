package ytb.bangbang.model;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/16
 */
public class GroupListPojo {

    private GroupUser groupUser;

    private List<GroupUser> groupUsers;


    public GroupUser getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(GroupUser groupUser) {
        this.groupUser = groupUser;
    }

    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }
}
