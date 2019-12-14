package ytb.project.view.model.ProjectTaskViewModel;

import ytb.project.common.ProjectConst;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

public class ViewProjectTemplateUserModel extends Ytb_ModelSkipNull {

    private int folderId;

    private String title;
    private int templateId;
    private int documentId;

    //协助、分享发起人,原作者
    private int author;
    //审核人
    private int auditor;
    //文档类型
    private int docType;
    //按扭颜色 //显示状态  //0-点亮1--灰色2--隐藏
    private int displayStatus;
    //按扭的文字 //动作 1去查阅 2去编制 3去修改 4去审核绿色 5上传文件 6重新上传
    private int activeStatus;

    //模板状态（0发布中 1未开始 2已查阅 3编制中 4未递交 5已递交 6通过 7未通过）status
    private Integer status;
    private String updateTime;
    private int shareNum;
    private int assistNum;

    //协助、分享接收人
    private int shareReceive;

    //是否有分享
    private boolean viewShare;

    //是否是自己文档 (0:不是)
    private int owerTemplate;

    public int checkColor(int color) {
        if (getActiveStatus() != ProjectConst.ACTIVE_STATUS_ToModify) {
            color = 1;
        }
        return color;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public boolean isTemplateReq() {
        return docType >= Dict_TemplateModel.Template_TYPE_req_START
                && docType <= Dict_TemplateModel.Template_TYPE_req_END;
    }

    public boolean isTemplateWorkplan() {
        return docType >= Dict_TemplateModel.Template_TYPE_workplan_START
                && docType <= Dict_TemplateModel.Template_TYPE_workplan_END;
    }

    public int getOwerTemplate() {
        return owerTemplate;
    }

    public void setOwerTemplate(int owerTemplate) {
        this.owerTemplate = owerTemplate;
    }

    public int getShareReceive() {
        return shareReceive;
    }

    public void setShareReceive(int shareReceive) {
        this.shareReceive = shareReceive;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public boolean isViewShare() {
        return viewShare;
    }

    public void setViewShare(boolean viewShare) {
        this.viewShare = viewShare;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getAuditor() {
        return auditor;
    }

    public void setAuditor(int auditor) {
        this.auditor = auditor;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(int displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public int getAssistNum() {
        return assistNum;
    }

    public void setAssistNum(int assistNum) {
        this.assistNum = assistNum;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

}





