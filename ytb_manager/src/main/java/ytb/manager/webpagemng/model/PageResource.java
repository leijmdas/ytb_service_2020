package ytb.manager.webpagemng.model;

import java.util.Date;

/**
 * @author lxz 2018/12/15 16:39
 */
public class PageResource {

    private int resId;

    private byte resType;

    private String title;

    private String content;

    private int imgDocId;

    private int sortNo;

    private Date createTime;

    private int createBy;

    public PageResource() {
    }

    @Override
    public String toString() {
        return "PageResource{" +
                "resId=" + resId +
                ", resType=" + resType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgDocId='" + imgDocId + '\'' +
                ", sortNo=" + sortNo +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                '}';
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public byte getResType() {
        return resType;
    }

    public void setResType(byte resType) {
        this.resType = resType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgDocId() {
        return imgDocId;
    }

    public void setImgDocId(int imgDocId) {
        this.imgDocId = imgDocId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
}
