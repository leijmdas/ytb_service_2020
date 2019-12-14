package ytb.bangbang.model;

import java.util.List;

/**
 * 帮帮好友分组表
 * tanchao
 * 2019/3/6
 */

public class UserFriendsGroup {
    private  Integer id;//好友分组Id
    private  Integer userId;//用户Id
    private  String groupname;//好友分组名
    private List<BBUserInfo> list;//分组下的好友

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<BBUserInfo> getList() {
        return list;
    }

    public void setList(List<BBUserInfo> list) {
        this.list = list;
    }
}
