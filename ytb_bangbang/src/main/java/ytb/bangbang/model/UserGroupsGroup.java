package ytb.bangbang.model;

import java.util.List;

public class UserGroupsGroup {

    private Integer id;
    private String groupname;

    private List<Group_InfoModel> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<Group_InfoModel> getList() {
        return list;
    }

    public void setList(List<Group_InfoModel> list) {
        this.list = list;
    }
}
