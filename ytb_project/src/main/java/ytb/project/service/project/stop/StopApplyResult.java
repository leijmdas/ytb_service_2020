package ytb.project.service.project.stop;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.common.context.model.Ytb_ModelSkipNull;
import ytb.project.model.ProjectTalkModel;

public class StopApplyResult extends Ytb_ModelSkipNull {

    String projectName;

    int projectId;
    int talkId;
    int phase;
    //终止文件夹
    Integer stopFolderId;
    //终止通知书模板
    Integer stopTemplateId;
    //终止通知书内容
    int stopDocumentId;

    public StopApplyResult (UserProjectContext context){
            ProjectModel pm = context.getProjectModel();
            ProjectTalkModel ptm = context.getProjectTalkModel();
            setProjectName(pm.getProjectName());
            setProjectId(pm.getProjectId());
            setTalkId(ptm.getTalkId());
            setPhase(ptm.getPhase());
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getStopDocumentId() {
        return stopDocumentId;
    }

    public void setStopDocumentId(int stopDocumentId) {
        this.stopDocumentId = stopDocumentId;
    }

    public Integer getStopFolderId() {
        return stopFolderId;
    }

    public void setStopFolderId(Integer stopFolderId) {
        this.stopFolderId = stopFolderId;
    }

    public Integer getStopTemplateId() {
        return stopTemplateId;
    }

    public void setStopTemplateId(Integer stopTemplateId) {
        this.stopTemplateId = stopTemplateId;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

}
