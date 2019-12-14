package ytb.bangbang.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName GroupInfoFriend
 * @Description TODO
 * @Author qsy
 * @Date 2019/4/17 10:57
 **/
public class GroupInfoFriend implements Serializable {

    private static final long serialVersionUID = 6297574823509763369L;

    /**
     * 群组表ID
     */
    private Integer groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 群组图标
     */
    private String groupIcon;

    /**
     * 群组类型 1：工作组2：兴趣组
     */
    private Integer groupType;

    /**
     * 好友
     */
    private List<UserFriends> userFriendsModel;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public List<UserFriends> getUserFriendsModel() {
        return userFriendsModel;
    }

    public void setUserFriendsModel(List<UserFriends> userFriendsModel) {
        this.userFriendsModel = userFriendsModel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", createTime=").append(createTime);
        sb.append(", groupIcon=").append(groupIcon);
        sb.append(", groupType=").append(groupType);
        sb.append(", userFriendsModel=").append(userFriendsModel);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}
