package ytb.user.model;

/**
 * @Author hj
 * @Description //分页查询的参数
 * @Date 2018/10/29
 **/
public class SearchPersonParamModel {

    //用户Id
    private String userId;
    //用户昵称
    private String nickName;
    //专业能力标签Id
    private String tagId;
    //接单范围Id
    private String typeId;
    //工作地址
    private String userAddress;
    //学历
    private String level;
    //信用等级
    private String creditGrade;
    //页数
    private String pageSize;
    //页码
    private String pageNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "SearchPersonParamModel{" +
                "userId='" + userId + '\'' +
                "nickName='" + nickName + '\'' +
                ", tagId='" + tagId + '\'' +
                ", typeId='" + typeId + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", level='" + level + '\'' +
                ", creditGrade='" + creditGrade + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                '}';
    }
}
