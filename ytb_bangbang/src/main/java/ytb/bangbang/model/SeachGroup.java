package ytb.bangbang.model;


import java.util.Date;

public class SeachGroup {
    private Integer id;//id
    private String groupName;//群名称
    private String groupAddress;//群地址
    private Integer groupCounts;//群成员
    private Date createTime;//注册时间

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

    public String getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
    }

    public Integer getGroupCounts() {
        return groupCounts;
    }

    public void setGroupCounts(Integer groupCounts) {
        this.groupCounts = groupCounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
