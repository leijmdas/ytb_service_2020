package ytb.user.model;

import java.util.Date;

/**
 * 个人工作业绩
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/7 11:11
 */
public class UserWorkPrjModel {
    //表主键
    private int prjId = 0;

    //用户ID
    private int userId ;

    //开始日期
    private Date startDate = new Date();

    //结束日期
    private Date endDate = new Date();

    //工作公司名称
    private String companyName = "";

    //项目名称
    private String prjName ="";

    //项目模块描述
    private String describe ="";

    //项目负责人
    private String prjLeader = "";

    //信息添加时间
    private Date createTime = new Date();

    //修改时间
    private Date updateTime = new Date();

    //图片
    private String image = "";


    public int getPrjId() {
        return prjId;
    }

    public void setPrjId(int prjId) {
        this.prjId = prjId;
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

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrjLeader() {
        return prjLeader;
    }

    public void setPrjLeader(String prjLeader) {
        this.prjLeader = prjLeader;
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
