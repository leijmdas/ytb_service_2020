package ytb.project.view.model;

import ytb.project.model.ProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;
import java.util.Map;

/**
 * 项目协助的任务视图
 * Package: ytb.project.view.model
 * Author: ZCS
 * Date: Created in 2019/3/4 16:48
 */
public class ProjectAssitsViewResult extends Ytb_ModelSkipNull {

    Map<String, Object> mapPhaseStatus;

    List<ProjectTemplateUserModel> templateList;

    List<ViewProjectEventModel> auditEventList;

    public List<ViewProjectEventModel> getAuditEventList() {
        return auditEventList;
    }

    public void setAuditEventList(List<ViewProjectEventModel> auditEventList) {
        this.auditEventList = auditEventList;
    }

    public List<ProjectTemplateUserModel> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<ProjectTemplateUserModel> templateList) {
        this.templateList = templateList;
    }

    public Map<String, Object> getMapPhaseStatus() {
        return mapPhaseStatus;
    }

    public void setMapPhaseStatus(Map<String, Object> mapPhaseStatus) {
        this.mapPhaseStatus = mapPhaseStatus;
    }
}
