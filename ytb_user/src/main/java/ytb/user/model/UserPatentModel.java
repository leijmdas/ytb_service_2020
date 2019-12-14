package ytb.user.model;

import java.util.Date;

/**
 * 个人专利表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/5 14:07
 */
public class UserPatentModel {
    /**
     * 主键ID
     */
    private Integer patentId = 0;
    /**
     * 用户ID（关联User_info表）
     */
    private Integer userId =0;
    /**
     * 专利名称
     */
    private String patentName ="";
    /**
     * 专利号
     */
    private String patentNumber ="";
    /**
     * 第一署名人名
     */
    private String firstName ="";
    /**
     * 第几署名人
     */
    private String myNameOrder ="";
    /**
     * 证书照片
     */
    private String pictureUrl ="";
    /**
     * 专利标识(1发明的专利、2实用的新型专利、3授权的外观专利、4商标专利、5软件著作专利)
     */
    private Integer patentType =0;
    /**
     * 信息添加时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 设置：主键ID
     */
    public void setPatentId(Integer patentId) {
        this.patentId = patentId;
    }
    /**
     * 获取：主键ID
     */
    public Integer getPatentId() {
        return patentId;
    }
    /**
     * 设置：用户ID（关联User_info表）
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户ID（关联User_info表）
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：专利名称
     */
    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }
    /**
     * 获取：专利名称
     */
    public String getPatentName() {
        return patentName;
    }
    /**
     * 设置：专利号
     */
    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }
    /**
     * 获取：专利号
     */
    public String getPatentNumber() {
        return patentNumber;
    }
    /**
     * 设置：第一署名人名
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * 获取：第一署名人名
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * 设置：第几署名人
     */
    public void setMyNameOrder(String myNameOrder) {
        this.myNameOrder = myNameOrder;
    }
    /**
     * 获取：第几署名人
     */
    public String getMyNameOrder() {
        return myNameOrder;
    }
    /**
     * 设置：证书照片
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    /**
     * 获取：证书照片
     */
    public String getPictureUrl() {
        return pictureUrl;
    }
    /**
     * 设置：专利标识(1发明的专利、2实用的新型专利、3授权的外观专利、4商标专利、5软件著作专利)
     */
    public void setPatentType(Integer patentType) {
        this.patentType = patentType;
    }
    /**
     * 获取：专利标识(1发明的专利、2实用的新型专利、3授权的外观专利、4商标专利、5软件著作专利)
     */
    public Integer getPatentType() {
        return patentType;
    }
    /**
     * 设置：信息添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：信息添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

}
