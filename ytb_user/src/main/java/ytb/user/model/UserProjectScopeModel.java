package ytb.user.model;

import java.util.Date;

/**
 * 用户接单范围
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/11/6 17:07
 */
public class UserProjectScopeModel {
    //表主键
    private Integer scodeId =0;

    //用户Id
    private Integer userId = 0;

    //公司ID
    private Integer companyId = 0;

    //项目类别ID
    private Integer typeId = 0;

    //是否公开
    private Boolean isPrivate = true;

    //生成时间
    private Date createTime = new Date();


    public Integer getScodeId() {
        return scodeId;
    }

    public void setScodeId(Integer scodeId) {
        this.scodeId = scodeId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
