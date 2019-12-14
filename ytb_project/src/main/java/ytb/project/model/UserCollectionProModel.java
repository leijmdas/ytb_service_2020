package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

/**
 * 收藏项目
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2018/12/20 13:24
 */
public class UserCollectionProModel  extends Ytb_ModelSkipNull {

    //表主键
    private Integer collectionId = 0;

    //用户ID
    private Integer userId = 0;

    //公司ID
    private Integer companyId = 0;

    //项目ID
    private Integer projectId = 0;

    //时间
    private Date createTime = new Date();


    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
