package ytb.project.view.model;

import com.alibaba.fastjson.JSONArray;
import ytb.project.model.ProjectEventViewModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;



public class ProjectTaskViewResult extends Ytb_ModelSkipNull {


    //乙方负责人审核组员模板与事件 不用
    private Collection<ProjectTaskViewResult> pmTaskViewResults;

    //阶段状态phase and status
    private Map mapPhaseStatus; //mapStatus->mapPhaseStatus
    //甲方审核乙方
    private Integer auditDisplayPaTalk; //display->auditDisplayPaTalk
    //乙方递交甲方状态
    private Map mapSubmit;//map->mapSubmit
    //甲方乙方审核事件
    private List<ViewProjectEventModel> auditEventList;//eventList->auditEventList
    //模板任务 TemplateList
    private List<ViewProjectTemplateUserModel> templateList;//list->templateList


    //洽谈终止事件
    private ProjectEventViewModel talkEndEvent;//talkEndList->talkEndEvent
    //支付事件pa,pb有效
    private List<ProjectEventViewModel> payEventList;//payEndList->payEventList
    //项目标准任务
    private ProjectMemberTask phaseTask;
    //自定义任务
    private ViewTaskModel defineTask;//task->defineTask

    //private List<UserShareModel> shareList;
    //项目变更事件
    private JSONArray projectChangeList;

    public Integer getAuditDisplayPaTalk() {
        return auditDisplayPaTalk;
    }

    public void setAuditDisplayPaTalk(Integer auditDisplayPaTalk) {
        this.auditDisplayPaTalk = auditDisplayPaTalk;
    }

    public Map getMapSubmit() {
        return mapSubmit;
    }

    public void setMapSubmit(Map mapSubmit) {
        this.mapSubmit = mapSubmit;
    }

    public Map getMapPhaseStatus() {
        return mapPhaseStatus;
    }

    public void setMapPhaseStatus(Map mapPhaseStatus) {
        this.mapPhaseStatus = mapPhaseStatus;
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

    public ProjectEventViewModel getTalkEndEvent() {
        return talkEndEvent;
    }

    public void setTalkEndEvent(ProjectEventViewModel talkEndEvent) {
        this.talkEndEvent = talkEndEvent;
    }

    public List<ProjectEventViewModel> getPayEventList() {
        return payEventList;
    }

    public void setPayEventList(List<ProjectEventViewModel> payEventList) {
        this.payEventList = payEventList;
    }

    public JSONArray getProjectChangeList() {
        return projectChangeList;
    }

    public void setProjectChangeList(JSONArray projectChangeList) {
        this.projectChangeList = projectChangeList;
    }

    public ViewTaskModel getDefineTask() {
        return defineTask;
    }

    public void setDefineTask(ViewTaskModel defineTask) {
        this.defineTask = defineTask;
    }

    public ProjectMemberTask getPhaseTask() {
        return phaseTask;
    }

    public void setPhaseTask(ProjectMemberTask phaseTask) {
        this.phaseTask = phaseTask;
    }

    public Collection<ProjectTaskViewResult> getPmTaskViewResults() {
        return pmTaskViewResults;
    }

    public void setPmTaskViewResults(Collection<ProjectTaskViewResult> pmTaskViewResults) {
        this.pmTaskViewResults = pmTaskViewResults;
    }


}
