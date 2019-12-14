package ytb.bangbang.model;

/**
 * @author Lxz 2019/2/21 17:21
 */
public class BBGroupInfo {

    private int id;
    private String groupname;
    private String avatar;

    public BBGroupInfo() {
    }

    public BBGroupInfo parseToBBGroupInfo(Group_InfoModel groupInfoModel) {
        this.id = groupInfoModel.getGroup_id();
        this.groupname = groupInfoModel.getGroupName();
        this.avatar = groupInfoModel.getGroupIcon();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
