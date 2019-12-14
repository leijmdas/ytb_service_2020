package ytb.bangbang.model;

import java.util.List;

/**
 * @Author hj
 * @Description //申请加群
 * @Date 2018/9/17
 **/
public class Group_Apply_InfoModel {
    private int id;
    //申请者id
    private int userId=0;
    //管理者id
    private int toUserId=0;
    private String userName;
    private String toUserName;
    //群组id
    private int groupId=0;

    private Integer type;//1:主动申请加群  2:被动被拉人群
    //是否同意
    private int isAgree=1;
    //创建时间
    private String createTime=null;

    private GroupInfo groupInfo;

    private List<Answer> answers;//回复

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
