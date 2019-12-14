package ytb.manager.templateexcel.model.xls;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;

public class TemplateDocumentHeader extends Ytb_ModelSkipNull {
    public static TemplateDocumentHeader parse(String msgBody) {
        return JSONObject.parseObject(msgBody, TemplateDocumentHeader.class);
    }

    public static String[] headerParams={
            "userId","projectUserType","documentId",
            "projectId","talkId","phase",
            "groupId","docType"
    } ;
    public static boolean isHeaderParam(String paramName){
        for(String p:headerParams){
            if (p.equals(paramName)){
                return true;
            }
        }
        return false;
    }

    private String tagVersion;
    private String tagDocument;
    private List<String> tagDocumentRef;

    protected int projectTypeId;
    protected Integer repositoryId;

    protected Long userId;
    //PA PB PM　ＰＮ非成员
    protected String projectUserType = "PN";
    protected int documentId;
    protected int projectId;
    protected int talkId;
    protected int phase;
    protected int groupId;
    protected int docType;

    protected int templateId;

    protected Integer reqId;
    protected Integer workplanId;

    public String getProjectUserType() {
        return projectUserType;
    }

    public void setProjectUserType(String projectUserType) {
        this.projectUserType = projectUserType;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }


    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }


    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }


    public TemplateDocumentHeader() {
        defaultDocmentHeader();
    }

    public TemplateDocumentHeader(TemplateDocumentHeader h) {
        defaultDocmentHeader();
        setRepositoryId(h.getRepositoryId());
        setProjectId(h.getProjectId());
        setTalkId(h.getTalkId());
        setUserId(h.getUserId());
        setGroupId(h.getGroupId());
    }

    TemplateDocumentHeader defaultDocmentHeader() {

        setDocumentId(0);
        setDocType(0);
        setTagVersion("1.0");
        setTemplateId(0);
        setUserId(0l);
        setRepositoryId(0);
        setProjectId(0);
        setGroupId(0);
        setReqId(0);
        setWorkplanId(0);
        return this ;
    }

    public void setUpReqWorkplan_dict(List<Dict_TemplateModel> models) {

        for (Dict_TemplateModel model : models) {
            if (model.isTemplateReq()) {
                setReqId(model.getDocumentId());
            }
            if (model.isTemplateWorkplan()) {
                setWorkplanId(model.getDocumentId());
            }
        }

    }

    public boolean checkTemplateReqTrue() {
        return getDocType() >= Dict_TemplateModel.Template_TYPE_req_START
                && getDocType() <= Dict_TemplateModel.Template_TYPE_req_END;
    }

    public boolean checkTemplateWorkplanTrue() {
        return getDocType() >= Dict_TemplateModel.Template_TYPE_workplan_START
                && getDocType() <= Dict_TemplateModel.Template_TYPE_workplan_END;
    }
    //非加工采购工作计划书
    public boolean checkTemplate_TYPE_workplan_NonePpTrue() {
        return getDocType() == Dict_TemplateModel.Template_TYPE_workplan;
    }

    public boolean checkTemplate_TYPE_testReportTrue() {
        return getDocType() == Dict_TemplateModel.Template_TYPE_testReport ;
    }


    public JSONObject toJSONObject() {
        return JSONObject.parseObject(this.toString());
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRepositoryId() {

        return repositoryId == null ? 0 : repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getWorkplanId() {
        return workplanId;
    }

    public void setWorkplanId(Integer workplanId) {
        this.workplanId = workplanId;
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }


    public String getTagDocument() {
        return tagDocument;
    }

    public void setTagDocument(String tagDocument) {
        this.tagDocument = tagDocument;
    }

    public List<String> getTagDocumentRef() {
        return tagDocumentRef;
    }

    public void setTagDocumentRef(List<String> tagDocumentRef) {
        this.tagDocumentRef = tagDocumentRef;
    }

    public String getTagVersion() {
        return tagVersion;
    }

    public void setTagVersion(String tagVersion) {
        this.tagVersion = tagVersion;
    }
}
