package ytb.user.model;


import java.util.Date;

/**
 *个人简历表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/5 16:31
 */
public class UserWorkHistroyModel {
    //表主键
    private int workId = 0;

    //用户ID
    private int userId ;

    //开始日期
    private Date startDate = new Date();

    //结束日期
    private Date endDate = new Date();

    //公司名称
    private String companyName ="";

    //所属部门
    private String dpt ="";

    //担任职位
    private String duty ="";

    //上级姓名
    private String superName ="";

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

}
