package ytb.bangbang.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 
 */
public class GroupInfo implements Serializable {
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

    private List<GroupNoticeModel> groupNoticeModel;

    private static final long serialVersionUID = 1L;

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

    public List<GroupNoticeModel> getGroupNoticeModel() {
        return groupNoticeModel;
    }

    public void setGroupNoticeModel(List<GroupNoticeModel> groupNoticeModel) {
        this.groupNoticeModel = groupNoticeModel;
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
        sb.append(", groupNoticeModel=").append(groupNoticeModel);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}