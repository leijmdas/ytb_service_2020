package ytb.user.model;

import java.util.Date;

/**
 * 个人刊物论文表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/3 16:32
 */
public class UserPaperModel {
    //表主键
    private int paperId = 0;

    //用户ID
    private int userId = 0;

    //刊物论文发表时间
    private Date publicationDate= new Date();

    //刊物论文名称
    private String name = "";

    //期刊号
    private String publicationNum = "";

    //第一书署名人
    private String firstName = "";

    //第几署名人
    private String thisName = "";

    //刊物证书图片
    private String picture ="";

    //生成时间
    private Date createTime = new Date();

    //修改时间
    private Date updateTime = new Date();

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicationNum() {
        return publicationNum;
    }

    public void setPublicationNum(String publicationNum) {
        this.publicationNum = publicationNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getThisName() {
        return thisName;
    }

    public void setThisName(String thisName) {
        this.thisName = thisName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
