package ytb.bangbang.model;

import java.util.List;

public class GroupData {
    private Integer id;
    private String groupName;//群名称
    private List<GroupNoticeModel> notice;//群介绍
    private String groupAddress;//群地点
    private List<GroupUserData> list;//群主,管理员列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupNoticeModel> getNotice() {
        return notice;
    }

    public void setNotice(List<GroupNoticeModel> notice) {
        this.notice = notice;
    }

    public String getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
    }

    public List<GroupUserData> getList() {
        return list;
    }

    public void setList(List<GroupUserData> list) {
        this.list = list;
    }
}
