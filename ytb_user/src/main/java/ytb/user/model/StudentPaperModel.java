package ytb.user.model;

import java.util.Date;

/**
 * 学生论文
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/5 13:02
 */
public class StudentPaperModel {

    /**
     * 表主键
     */
    private Integer paperId = 0;
    /**
     * 序号
     */
    private String paperNo ="";
    /**
     * 用户ID关联User_info表
     */
    private Integer userId =0;
    /**
     * 论文通过时间
     */
    private Date passDate;
    /**
     * 论文名称
     */
    private String paperName="";
    /**
     * 论文封面图片
     */
    private String picture1="";

    /**
     * 论文目录图片
     */
    private String picture2="";
    /**
     * 1本科论文、2博士论文、3硕士论文
     */
    private Integer paperGrade=1;
    /**
     * 生成时间
     */
    private Date createTime;

    /**
     * 设置：表主键
     */
    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
    /**
     * 获取：表主键
     */
    public Integer getPaperId() {
        return paperId;
    }
    /**
     * 设置：序号
     */
    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo;
    }
    /**
     * 获取：序号
     */
    public String getPaperNo() {
        return paperNo;
    }
    /**
     * 设置：用户ID关联User_info表
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户ID关联User_info表
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：论文通过时间
     */
    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }
    /**
     * 获取：论文通过时间
     */
    public Date getPassDate() {
        return passDate;
    }
    /**
     * 设置：论文名称
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
    /**
     * 获取：论文名称
     */
    public String getPaperName() {
        return paperName;
    }
    /**
     * 设置：论文封面图片
     */
    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }
    /**
     * 获取：论文封面图片
     */
    public String getPicture1() {
        return picture1;
    }
    /**
     * 设置：论文目录图片
     */
    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }
    /**
     * 获取：论文目录图片
     */
    public String getPicture2() {
        return picture2;
    }
    /**
     * 设置：1本科论文、2博士论文、3硕士论文
     */
    public void setPaperGrade(Integer paperGrade) {
        this.paperGrade = paperGrade;
    }
    /**
     * 获取：1本科论文、2博士论文、3硕士论文
     */
    public Integer getPaperGrade() {
        return paperGrade;
    }
    /**
     * 设置：生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }



}
