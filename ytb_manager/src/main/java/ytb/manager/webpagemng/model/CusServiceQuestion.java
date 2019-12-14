package ytb.manager.webpagemng.model;

import java.util.Date;

/**
 * @author lxz 2018/12/22 15:02
 */
public class CusServiceQuestion {

    private int qid;

    private String title;

    private String content;

    private Date createTime;

    private int createBy;

    private int mark;

    private int picDocId;

    private String btnName;

    private String forwardUrl;

    private int sortNo;

    private int typeId;

    private String typeName;

    public CusServiceQuestion() {
    }

    @Override
    public String toString() {
        return "CusServiceQuestion{" +
                "qid=" + qid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", mark=" + mark +
                ", picDocId=" + picDocId +
                ", btnName='" + btnName + '\'' +
                ", forwardUrl='" + forwardUrl + '\'' +
                ", sortNo=" + sortNo +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getPicDocId() {
        return picDocId;
    }

    public void setPicDocId(int picDocId) {
        this.picDocId = picDocId;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
}
