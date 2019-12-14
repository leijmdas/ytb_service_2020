package ytb.bangbang.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
public class GroupUserAndInfoPojo implements Serializable {
    /**
     * 群用户ID
     */
    private Integer groupUserId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 群组ID
     */
    private Integer groupId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户在群组的身份 1：群主2：管理员3：普通成员
     */
    private Integer groupUserType;

    private List<GroupInfo> groupInfos;

    public List<GroupInfo> getGroupInfos() {
        return groupInfos;
    }

    public void setGroupInfos(List<GroupInfo> groupInfos) {
        this.groupInfos = groupInfos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(Integer groupUserId) {
        this.groupUserId = groupUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGroupUserType() {
        return groupUserType;
    }

    public void setGroupUserType(Integer groupUserType) {
        this.groupUserType = groupUserType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupUserId=").append(groupUserId);
        sb.append(", userId=").append(userId);
        sb.append(", groupId=").append(groupId);
        sb.append(", createTime=").append(createTime);
        sb.append(", groupUserType=").append(groupUserType);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}