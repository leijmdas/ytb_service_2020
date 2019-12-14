package ytb.user.model;

import java.util.Date;

/**
 *公司平台外合作项目表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/10 10:45
 */
public class CompanyProjectModel {
    //表主键
    private int projectId = 0;

    //公司Id
    private int companyId = 0;

    //合作开始时间
    private Date startTime = new Date();

    //合作结束时间
    private Date endTime = new Date();

    //合作的项目的名称
    private String  projectName = "";

    //合作的公司名称
    private String companyName = "";

    //图片
    private String image= "";


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
