package ytb.project.model.projectview;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

/**
 * Package: ytb.project.model.projectview
 * Author: ZCS
 * Date: Created in 2019/2/14 15:21
 */
public class ProjectViewModel extends Ytb_ModelSkipNull {
    //项目ID
    private Integer projectId;

    //项目名称
    private String projectName;

    //状态
    private Integer status;

    //浏览数
    private Integer viewNumber;

    //收藏数
    private Integer favoriteNumber;

    //创建时间
    private Date enterTime;

    //工作组名
    private String groupName;

    //需求说明书ID
    private Integer reqTemplateId;

    //工作组计划书ID
    private Integer workplanTemplateId;

    //用户ID
    private Integer userId;

    private Integer changeStatus;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getReqTemplateId() {
        return reqTemplateId;
    }

    public void setReqTemplateId(Integer reqTemplateId) {
        this.reqTemplateId = reqTemplateId;
    }

    public Integer getWorkplanTemplateId() {
        return workplanTemplateId;
    }

    public void setWorkplanTemplateId(Integer workplanTemplateId) {
        this.workplanTemplateId = workplanTemplateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }
}
