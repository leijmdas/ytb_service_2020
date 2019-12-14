package ytb.manager.template.model;


import java.util.Date;


public class Dict_TemplateModel extends TemplateModel {
    private int templateId;
    private int templateIdV;
    private int repositoryId;
    private int workJobId;

    private String alias;
    private int docXls;
    private int documentId;
    private String uuid;
    private int optional;
    private Date createTime;
    private int createBy;
    private int user; //0 通用 1甲方,2乙方
    private int phase; //阶段数3, 6
    private int subType; //变更或者终止原因

    private String workJobName;
    private String repositoryNo;
    private String repositoryName;

    private Dict_document documentModel;

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getTemplateIdV() {
        return templateIdV;
    }

    public void setTemplateIdV(int templateIdV) {
        this.templateIdV = templateIdV;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public int getWorkJobId() {
        return workJobId;
    }

    public void setWorkJobId(int workJobId) {
        this.workJobId = workJobId;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }



    public int getDocXls() {
        return docXls;
    }

    public void setDocXls(int docXls) {
        this.docXls = docXls;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getOptional() {
        return optional;
    }

    public void setOptional(int optional) {
        this.optional = optional;
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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public String getWorkJobName() {
        return workJobName;
    }

    public void setWorkJobName(String workJobName) {
        this.workJobName = workJobName;
    }

    public String getRepositoryNo() {
        return repositoryNo;
    }

    public void setRepositoryNo(String repositoryNo) {
        this.repositoryNo = repositoryNo;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public Dict_document getDocumentModel() {
        return documentModel;
    }

    public void setDocumentModel(Dict_document documentModel) {
        this.documentModel = documentModel;
    }
}
