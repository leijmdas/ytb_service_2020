package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

public class ProjectTemplateUserModel extends Ytb_Model {

    public static int DISPLAY_STATUS_LIGHT = 0;
    public static int DISPLAY_STATUS_GRAY = 1;
    public static int DISPLAY_STATUS_HIDE = 2;

    //表主键
    private Integer pkId;

    //用户ID
    private Integer userId;

    //项目ID
    private Integer projectId;

    //洽谈标识
    private Integer talkId;

    //文档ID
    private Integer templateId;

    //显示状态0-点亮1--灰色2--隐藏
    private Integer displayStatus;

    //动作 1去查阅 2去编制 3去修改 4去审核绿色 5上传文件 6重新上传
    private Integer activeStatus;

    //模板状态（0发布中 1未开始 2已查阅 3编制中 4未递交 5已递交 6通过 7未通过）status
    private Integer status;

    //模板员作者
    private Integer author;

    //时间
    private Date createTime;

    //
    private Integer templateIdAssist = 0;

    //
    private String title;

    public int getAuditor() {
        return auditor;
    }

    public void setAuditor(int auditor) {
        this.auditor = auditor;
    }

    private int auditor;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }


    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    //public static int DISPLAY_STATUS_LIGHT = 0;
    public void setDisplayStatusLight( ) {
        this.displayStatus = DISPLAY_STATUS_LIGHT;
    }

    //public static int DISPLAY_STATUS_GRAY = 1;
    public void setDisplayStatusGray() {
        this.displayStatus = DISPLAY_STATUS_GRAY;
    }

    //public static int DISPLAY_STATUS_HIDE = 2;
    public void setDisplayStatusHide() {
        this.displayStatus = DISPLAY_STATUS_HIDE;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTemplateIdAssist() {
        return templateIdAssist;
    }

    public void setTemplateIdAssist(Integer templateIdAssist) {
        this.templateIdAssist = templateIdAssist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
