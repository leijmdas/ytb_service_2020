package ytb.project.view.model;

import ytb.project.model.ProjectEventViewModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.project.view.model
 * Author: ZCS
 * Date: Created in 2019/4/10 10:22
 * Company: 公司
 */
public class ProjectChangeTaskViewResult extends Ytb_ModelSkipNull {
    //乙方负责人审核组员模板与事件

    public List<ProjectTaskViewResult> getPmTaskViewResults() {
        return pmTaskViewResults;
    }

    public void setPmTaskViewResults(List<ProjectTaskViewResult> pmTaskViewResults) {
        this.pmTaskViewResults = pmTaskViewResults;
    }

    List<ProjectTaskViewResult> pmTaskViewResults;

    //模板任务 TemplateList
    private List<ViewProjectTemplateUserModel> templateList;

    //支付事件pa,pb有效
    private List<ProjectEventViewModel> payEventList;//payEndList->
    //甲方审核乙方
    private Integer auditDisplayPaTalk; //display->auditDisplayPaTalk

    //阶段状态phase and status
    private Map mapPhaseStatus; //mapStatus->mapPhaseStatus

    //甲方乙方审核事件
    private List<ViewProjectEventModel> auditEventList;//eventList->auditEventList

    private List<ViewProjectTemplateUserModel> knitTemplateList;

    public List<ViewProjectTemplateUserModel> getKnitTemplateList() {
        return knitTemplateList;
    }

    public void setKnitTemplateList(List<ViewProjectTemplateUserModel> knitTemplateList) {
        this.knitTemplateList = knitTemplateList;
    }

    public List<ViewProjectEventModel> getAuditEventList() {
        return auditEventList;
    }

    public void setAuditEventList(List<ViewProjectEventModel> auditEventList) {
        this.auditEventList = auditEventList;
    }

    public List<ViewProjectTemplateUserModel> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<ViewProjectTemplateUserModel> templateList) {
        this.templateList = templateList;
    }

    public List<ProjectEventViewModel> getPayEventList() {
        return payEventList;
    }

    public void setPayEventList(List<ProjectEventViewModel> payEventList) {
        this.payEventList = payEventList;
    }

    public Integer getAuditDisplayPaTalk() {
        return auditDisplayPaTalk;
    }

    public void setAuditDisplayPaTalk(Integer auditDisplayPaTalk) {
        this.auditDisplayPaTalk = auditDisplayPaTalk;
    }

    public Map getMapPhaseStatus() {
        return mapPhaseStatus;
    }

    public void setMapPhaseStatus(Map mapPhaseStatus) {
        this.mapPhaseStatus = mapPhaseStatus;
    }
}
