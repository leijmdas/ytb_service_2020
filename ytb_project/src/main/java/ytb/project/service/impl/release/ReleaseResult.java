package ytb.project.service.impl.release;

import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;

public class ReleaseResult extends Ytb_ModelSkipNull {
    ProjectModel projectModel;
    int reqTemplateId;
    int workplanTemplateId;
    List<Integer> lstTalkId;
    int newProjectId;

    public void modifyRelaseResult(List<ProjectTemplateModel> models) {
        for (ProjectTemplateModel model : models) {
            if (model.isTemplateReq()) {
                setReqTemplateId(model.getTemplateId());
            } else if (model.isTemplateWorkplan()) {
                setWorkplanTemplateId(model.getTemplateId());
            }
        }

    }

    public int getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(int newProjectId) {
        this.newProjectId = newProjectId;
    }


    public int getReqTemplateId() {
        return reqTemplateId;
    }

    public void setReqTemplateId(int reqTemplateId) {
        this.reqTemplateId = reqTemplateId;
    }

    public int getWorkplanTemplateId() {
        return workplanTemplateId;
    }

    public void setWorkplanTemplateId(int workplanTemplateId) {
        this.workplanTemplateId = workplanTemplateId;
    }


    public List<Integer> getLstTalkId() {
        return lstTalkId;
    }

    public void setLstTalkId(List<Integer> lstTalkId) {
        this.lstTalkId = lstTalkId;
    }

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }


}
