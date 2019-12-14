package ytb.manager.tagtable.model.tagtemplate;

import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;

public class Document_RefModel extends Ytb_Model {
    // 模板关联标识
    int refId;

    //comment '模板仓库标识',
    int repositoryId;
    //'项目标识'
    int projectId;


    int talkId;
    int phase;


    long userId;
    //'文档标识'
    int documentId;
    Short docType;
    Timestamp docTimeSync;//datetime default '0000-00-00 00:00:00' not null
    //comment '同步时间',
    //comment '引用文档标识',
    int pDocumentId;
    Short pDocType;//smallint(6) default '0'                not null
    //comment '引用文档类型',
    Timestamp pDocTime;//datetime default '0000-00-00 00:00:00' not null

    //comment '引用文档时间'
    public Document_RefModel() {

    }

    public Document_RefModel(TemplateDocumentHeader header) {
        this.userId = header.getUserId();
        this.projectId = header.getProjectId();
        this.repositoryId = header.getRepositoryId();
        this.talkId = header.getTalkId();
        this.phase=header.getPhase();

        this.docType = (short) header.getDocType();
        this.documentId = header.getDocumentId();
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }
    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDocTimeSync() {
        return docTimeSync;
    }

    public void setDocTimeSync(Timestamp docTimeSync) {
        this.docTimeSync = docTimeSync;
    }


    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public Short getDocType() {
        return docType;
    }

    public void setDocType(Short docType) {
        this.docType = docType;
    }

    public int getpDocumentId() {
        return pDocumentId;
    }

    public void setpDocumentId(int pDocumentId) {
        this.pDocumentId = pDocumentId;
    }

    public Short getpDocType() {
        return pDocType;
    }

    public void setpDocType(Short pDocType) {
        this.pDocType = pDocType;
    }

    public Timestamp getpDocTime() {
        return pDocTime;
    }

    public void setpDocTime(Timestamp pDocTime) {
        this.pDocTime = pDocTime;
    }

}
