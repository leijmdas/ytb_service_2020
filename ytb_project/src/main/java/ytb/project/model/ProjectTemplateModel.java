package ytb.project.model;

import ytb.manager.template.model.TemplateModel;

import java.util.Date;

public class ProjectTemplateModel extends TemplateModel {

    //文档主键
    private Integer templateId = 0;

    //原文档id
    private Integer templateIdV = 0;

    //文档序号
    private String docSeq = "";

    //文档名称
    //private String title ;
    //文档类型
    //private int docType;

    //文档版本
    private String docVer = "";

    //文档新内容
    private int documentId;

    //文档状态
    private int status;

    //文件夹
    private int folderId;

    //文档作者
    private int author;

    //协作人或审核人
    private Integer auditor = 0;

    //更新时间
    private Date updateTime;

    //共享次数
    private Integer shareNum =0;

    //协助次数
    private Integer assistNum = 0;

    private Integer documentType = 0;
    private Integer docSize = 0;

    private  ProjectDocumentModel documentModel;
    public ProjectDocumentModel getDocumentModel() {
        return documentModel;
    }

    public void setDocumentModel(ProjectDocumentModel documentModel) {
        this.documentModel = documentModel;
    }


    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(String docSeq) {
        this.docSeq = docSeq;
    }


    public String getDocVer() {
        return docVer;
    }

    public void setDocVer(String docVer) {
        this.docVer = docVer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateIdV() {
        return templateIdV;
    }

    public void setTemplateIdV(Integer templateIdV) {
        this.templateIdV = templateIdV;
    }

    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getAssistNum() {
        return assistNum;
    }

    public void setAssistNum(Integer assistNum) {
        this.assistNum = assistNum;
    }


    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public Integer getDocSize() {
        return docSize;
    }

    public void setDocSize(Integer docSize) {
        this.docSize = docSize;
    }
}
