package ytb.user.model;

import java.util.Date;

/**
 * 公司专利表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/10 10:39
 */
public class CompanyPatentModel {
    //表主键
    private int patentId = 0;

    //公司ID
    private int companyId = 0;

    //专利名称
    private String patentName = "";

    //产品号
    private String patentNo = "";

    //专利类别
    private int patentType ;

    //专利图片
    private String image = "";

    //生成时间
    private Date createTime = new Date();

    //修改时间
    private Date updateTime = new Date();

    public int getPatentType() {
        return patentType;
    }

    public void setPatentType(int patentType) {
        this.patentType = patentType;
    }

    public int getPatentId() {
        return patentId;
    }

    public void setPatentId(int patentId) {
        this.patentId = patentId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
