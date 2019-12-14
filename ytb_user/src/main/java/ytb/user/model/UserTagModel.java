package ytb.user.model;

import java.util.Date;

/**
 *用户专业标签表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/4 13:06
 */
public class UserTagModel {
    //表主键
    private int userTagId = 0;

    //用户ID
    private int userId = 0;

    //标签ID
    private int tagId = 0;

    //标签类型 1：兴趣爱好  2：专业能力
    private int tagType = 0;

    //生成时间
    private Date createTime = new Date();

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    //修改时间
    private Date updateTime = new Date();

    private String tagName ="";

    public int getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(int userTagId) {
        this.userTagId = userTagId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
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
