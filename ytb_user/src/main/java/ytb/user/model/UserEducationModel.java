package ytb.user.model;

import java.util.Date;

/**
 * 个人教育表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/4 17:35
 */
public class UserEducationModel {
    //表主键
    private int educationId = 0;

    //用户ID
    private int userId = 0;

    //学历名称
    private String educationName ="";

    //学校名称
    private String schoolName = "";

    //入学开始日期
    private Date startDate = new Date();

    //入学结束日期
    private  Date endDate = new Date();

    //导师姓名
    private String tutor = "";

    //学生干部任职
    private String studentCadres = "";

    //学历级别
    private int level = 0;

    //专业名称
    private String majorName ="";

    //班主任姓名
    private String headMaster = "";

    //生成时间
    private Date createTime = new Date();

    //修改时间
    private Date update = new Date();

    //是否有效
    private boolean isValid = false;

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean valid) {
        isValid = valid;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getStudentCadres() {
        return studentCadres;
    }

    public void setStudentCadres(String studentCadres) {
        this.studentCadres = studentCadres;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getHeadMaster() {
        return headMaster;
    }

    public void setHeadMaster(String headMaster) {
        this.headMaster = headMaster;
    }
}
