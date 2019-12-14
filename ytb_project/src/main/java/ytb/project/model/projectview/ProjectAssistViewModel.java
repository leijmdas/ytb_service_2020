package ytb.project.model.projectview;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Date;

/**
 * Package: ytb.project.model.projectview
 * Author: ZCS
 * Date: Created in 2019/2/26 13:18
 */
public class ProjectAssistViewModel  extends Ytb_ModelSkipNull {

    //用户ID
    private int userId;

    //文件夹ID
    private int folderId;

    //项目ID
    private int projectId;

    //文档ID
    private int templateId;

    //
    private int templateIdIni;

    //docID
    private int documentId;

    private int phaseNo;

    private int phase;

    private int phaseStatus;

    private String projectName;

    private String groupName;

    private Date enterTime;

    private int talkId;

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getAssistType() {
        return assistType;
    }

    public void setAssistType(int assistType) {
        this.assistType = assistType;
    }

    private int assistType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
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

    public int getTemplateIdIni() {
        return templateIdIni;
    }

    public void setTemplateIdIni(int templateIdIni) {
        this.templateIdIni = templateIdIni;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getPhaseNo() {
        return phaseNo;
    }

    public void setPhaseNo(int phaseNo) {
        this.phaseNo = phaseNo;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }
}
