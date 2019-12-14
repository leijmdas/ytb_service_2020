package ytb.user.model;

import java.util.Date;

/**
 * 公司专业标签
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/10 10:54
 */
public class CompanyTagModel {
    //表主键
    private int companyTagId = 0;

    //公司ID
    private int companyId = 0;

    //标签ID
    private int tagId = 0;

    //生成时间
    private Date createTime = new Date();

    //修改时间
    private Date updateTime = new Date();

    public int getCompanyTagId() {
        return companyTagId;
    }

    public void setCompanyTagId(int companyTagId) {
        this.companyTagId = companyTagId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
