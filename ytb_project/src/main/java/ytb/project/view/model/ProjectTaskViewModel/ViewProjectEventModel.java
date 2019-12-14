package ytb.project.view.model.ProjectTaskViewModel;

import ytb.project.model.ProjectEventViewModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;

public class ViewProjectEventModel extends Ytb_ModelSkipNull {

    List<ViewProjectTemplateUserModel> templateList;
    ProjectEventViewModel submitEvent;
    ProjectEventViewModel auditEvent;

    public ProjectEventViewModel getSubmitEvent() {
        return submitEvent;
    }

    public void setSubmitEvent( ProjectEventViewModel  submitEvent) {
        this.submitEvent = submitEvent;
    }

    public List<ViewProjectTemplateUserModel> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<ViewProjectTemplateUserModel> templateList) {
        this.templateList = templateList;
    }

    public ProjectEventViewModel getAuditEvent() {
        return auditEvent;
    }

    public void setAuditEvent(ProjectEventViewModel auditEvent) {
        this.auditEvent = auditEvent;
    }

}
