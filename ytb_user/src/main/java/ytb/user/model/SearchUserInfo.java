package ytb.user.model;

/**
 * @Author hj
 * @Description //搜索页面需要的用户基本信息
 * @Date 2018/11/19
 **/
public class SearchUserInfo {


    //用户Id
    private String userId;
    //用户昵称
    private  String nickName;
    //用户头像
    private  String userHead;
    //性别
    private  String sex;
    //居住地址
    private  String userAddress;
    //年龄
    private String age;
    //信用等级
    private String creditGrade;
    //学历名称
    private String educationName;
    //学校
    private String schoolName;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    //专业
    private String majorName;

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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }
    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "SearchUserInfo{" +
                "userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userHead='" + userHead + '\'' +
                ", sex='" + sex + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", age='" + age + '\'' +
                ", creditGrade='" + creditGrade + '\'' +
                ", educationName='" + educationName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
