package ytb.project.model.projectview;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

/**
 *
 * 项目中心收到、发出的申请model
 * Package: ytb.project.model.projectview
 * Author: ZCS
 * Date: Created in 2019/2/18 10:22
 */
public class ProjectApplyViewModel extends Ytb_ModelSkipNull {

    //项目ID
    private Integer projectId;

    //项目发布时间
    private Date projectSendTime;

    //项目申请时间
    private Date projectApplyTime;

    //备注
    private String remark;

    //项目名称
    private String projectName;

    //浏览数
    private Integer viewNumber;

    //收藏数
    private Integer favoriteNumber;

    //申请状态
    private Integer eventType;

    //公司名称
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getProjectSendTime() {
        return projectSendTime;
    }

    public void setProjectSendTime(Date projectSendTime) {
        this.projectSendTime = projectSendTime;
    }

    public Date getProjectApplyTime() {
        return projectApplyTime;
    }

    public void setProjectApplyTime(Date projectApplyTime) {
        this.projectApplyTime = projectApplyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }
}
