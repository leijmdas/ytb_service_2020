package ytb.user.model;

import java.util.Date;

/**
 *个人奖励表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/5 15:35
 */
public class UserAwardModel {
    //表主键
    private int awardId =0;

    //用户ID
    private int userId = 0;

    //颁发时间
    private Date awardDate = new Date();

    //奖励名称
    private String awardName ="";

    //颁发机构名称
    private String organName = "";

    //获奖证书图片
    private String picUrl = "";

    //生成时间
    private Date createTime = new Date();

    //修改时间
    private Date updateTime = new Date();

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(Date awardDate) {
        this.awardDate = awardDate;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
