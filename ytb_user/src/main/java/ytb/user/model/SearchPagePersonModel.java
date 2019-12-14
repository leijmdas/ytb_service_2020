package ytb.user.model;

import java.util.Date;

/**
 * @Author hj
 * @Description //人才信息页面数据实体
 * @Date 2018/10/24
 **/
public class SearchPagePersonModel {

    //用户Id
    private int userId;
    //头像
    private String userHead;
    //昵称
    private String nickName;
    //公司名称
    private String companyName;
    //信用等级
    private String creditGrade;
    //工作地点
    private String userAddress;
    //学历
    private String educationName;
    //专业能力标签
    private String tagName;
    //接单范围
    private String projectName;
    //入驻时间
    private Date registeredTime;
    //浏览次数
    private int viewNum;
    //关注人数
    private int concerNum;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getConcerNum() {
        return concerNum;
    }

    public void setConcerNum(int concerNum) {
        this.concerNum = concerNum;
    }
    @Override
    public String toString() {
        return "SearchPagePersonModel{" +
                "userId=" + userId +
                ", userHead='" + userHead + '\'' +
                ", nickName='" + nickName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", creditGrade='" + creditGrade + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", educationName='" + educationName + '\'' +
                ", tagName='" + tagName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", registeredTime=" + registeredTime +
                ", viewNum=" + viewNum +
                ", concerNum=" + concerNum +
                '}';
    }
}
