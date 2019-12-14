package ytb.project.view.service.impl;

import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectEventViewModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.service.impl.flow.FlowFloderView;
import ytb.project.service.impl.talk.IPhaseAndEvent;
import ytb.project.view.dao.ITalkTaskView;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ytb.project.common.ProjectConst.TalkPhase;

public class ProjectTaskViewTalk extends FlowFloderView implements ITalkTaskView {


    public ProjectTaskViewResult getProjectTaskView(UserProjectContext context, int userId, int phase, String type) {

        if ("PA".equals(type)) {
            return getPaProjectTask(context, userId, phase);
        } else if ("PB".equals(type)) {
            return getPbProjectTask(context, userId, phase);
        } else if ("PM".equals(type)) {
            return getPmProjectTask(context, userId, phase);
        }

        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
    }


    Map<String, Object> buildMapPhaseStatus(ProjectTalkModel ptm) {
        Map<String,Object> mapStatus = new HashMap<>();
        mapStatus.put("phase", ptm.getPhase());
        mapStatus.put("phaseStatus", ptm.getPhaseStatus());
        return mapStatus;
    }


    public ProjectTaskViewResult getPaProjectTask(UserProjectContext context, int userId, int phase) {

        ProjectTaskViewResult result = new ProjectTaskViewResult();

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();


        //查询甲方文档
        List<ViewProjectTemplateUserModel> templateUserModels =
                selectViewProjectTemplateUserModel(pm.getFolderId(), ptm.getUserId2(), context.getUserId());
        //乙方文件夹
        //获取乙方的文档状态
        List<ViewProjectTemplateUserModel> templateUserModelsPb = selectViewProjectTemplateUserModel(ptm.getFolderId()
                ,userId,context.getUserId() );
        templateUserModels.addAll(templateUserModelsPb);//文档List
      /*  //控制事件

        for (ViewProjectTemplateUserModel templateUserModel : templateUserModels) {
            if (templateUserModel.getStatus() == 1) {
                color = 1;
                break;
            }
        }*/
        //判断是否当前阶段,非当前阶段按钮置灰
        if(phase < ptm.getPhase()){
            for(ViewProjectTemplateUserModel vm : templateUserModels){
                vm.setDisplayStatus(1);
            }
        }else{
            for(ViewProjectTemplateUserModel vm : templateUserModels){
                if(vm.getAuthor() != pm.getUserId1()){

                     if(vm.isViewShare()){//分享了点亮
                        vm.setDisplayStatus(0);
                    }else{//未分享置灰

                         vm.setDisplayStatus(1);
                     }
                     if(vm.getStatus() == 6){
                         vm.setStatus(ProjectConst.TASK_STATUS_Submitted);
                     }
                    vm.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);

                }
            }
        }



        //控制递交按钮
        Map<String,Object> mapPhaseStatus = buildMapPhaseStatus(ptm);

        IPhaseAndEvent phaseAndEvent = ProjectTalkModel.getPhaseAndEvent();
        //阶段事件
        int color = 1;
        List<ViewProjectEventModel> auditEvents = getEventModels(context, phase, userId);
        for(ViewProjectEventModel projectEventModel : auditEvents){
            List<ViewProjectTemplateUserModel> templateList = projectEventModel.getTemplateList();
            if(templateList !=null){
                for(ViewProjectTemplateUserModel templateUserModel :templateList){
                    if(templateUserModel.getStatus() >= ProjectConst.TASK_STATUS_Passing){
                        color = 0;
                    }
                    if(templateUserModel.getStatus() == 5){
                        templateUserModel.setStatus(ProjectConst.TASK_STATUS_NotStart);
                    }
                    if(templateUserModel.getStatus() == 6){
                        templateUserModel.setStatus(ProjectConst.TASK_STATUS_Passing);
                    }
                }
            }
        }


        if (ptm.getChangeStatus() == ProjectConstState.CHNAGE_TYPE_TALK_TERM) { //洽谈终止

            List<ProjectEventViewModel> eventViewModels = phaseAndEvent.selectProjectEventViewModelByUserId(
                    pm.getProjectId(), ProjectConstState.CHNAGE_TYPE_TALK_TERM, userId, 0);
            result.setTemplateList(templateUserModels);
            result.setMapPhaseStatus(mapPhaseStatus);
            result.setAuditEventList(auditEvents);
            result.setTalkEndEvent(eventViewModels.get(0));
            return result;
        } else
        if (ptm.getPhase() >= TalkPhase) {//洽谈阶段事件
            //洽谈支付
            if (ptm.getPhase() >= ProjectConst.TalkPhase &&
                    (ptm.getPhaseStatus() == ProjectConst.TalkPhase_STATUS_PAY  || ptm.getPhaseStatus() == 0)) {
                BigDecimal totalFee = getInst().getFlowPay().getProjectCostFee().getProjectCostTotalFee(context
                         );
                mapPhaseStatus.put("totalFee", totalFee);

                if ( ptm.getPhase() >= context.getProjectModel().getPhaseStart() ) {
                    List<ProjectEventViewModel> payModels = phaseAndEvent.selectPayEventViewModel(userId, ptm.getTalkId(), ProjectConst.TalkPhase);
                    result.setPayEventList(payModels);

                }
            }
            mapPhaseStatus.put("color", color);//判断乙方递交按钮点亮（1隐藏 0显示）
            result.setMapPhaseStatus(mapPhaseStatus);
            result.setTemplateList(templateUserModels);
            result.setMapPhaseStatus(mapPhaseStatus);
            result.setAuditEventList(auditEvents);
            ViewTaskModel defindeTask = new ViewTaskService().selectViewTaskModel(pm.getProjectId(), TalkPhase, userId);
            result.setDefineTask(defindeTask);

        }

        return result;
    }

    //乙方任务洽谈
    public ProjectTaskViewResult getPbProjectTask(UserProjectContext context, int userId, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectTaskViewResult result = new ProjectTaskViewResult();

        Map<String,Object>  mapStatus = buildMapPhaseStatus(ptm);

        int color = 0;
        List<ViewProjectTemplateUserModel> statusList = selectViewProjectTemplateUserModel(pm
                .getFolderId(), userId,context.getUserId());
        List<ViewProjectTemplateUserModel> list = selectViewProjectTemplateUserModel
                (ptm.getFolderId(), userId,context.getUserId());
        for (ViewProjectTemplateUserModel m : list) {
            statusList.add(m);
            if (m.getActiveStatus() != ProjectConst.ACTIVE_STATUS_ToModify) {
                color = 1;
            }
        }
        mapStatus.put("color", color);//判断乙方递交按钮点亮
        if (ptm.getPhase() >= ProjectConst.TalkPhase) {
            //乙方事件
            IPhaseAndEvent phaseAndEvent = ProjectTalkModel.getPhaseAndEvent();
            List<ViewProjectEventModel> eventLst = getEventModels(context, phase, userId);

            //自定义任务数据
            ViewTaskModel viewTaskModel = new ViewTaskService().selectViewTaskModel(pm.getProjectId(), ProjectConst.TalkPhase, userId);

            if (ptm.getPhase() >= ProjectConst.TalkPhase) {
                if (ptm.getChangeStatus() == ProjectConstState.CHNAGE_TYPE_TALK_TERM) {
                    List<ProjectEventViewModel> eventViewModels = phaseAndEvent.selectProjectEventViewModelByUserId(
                            pm.getProjectId(), ProjectConstState.CHNAGE_TYPE_TALK_TERM, userId, 0);
                    result.setTemplateList(statusList);
                    result.setMapPhaseStatus(mapStatus);
                    result.setAuditEventList(eventLst);
                    result.setTalkEndEvent(eventViewModels.get(0));
                    return result;
                }

                BigDecimal totalFee = getInst().getFlowPay().getProjectCostFee().getProjectCostTotalFee(context);
                mapStatus.put("totalFee", totalFee);

                if (ptm.getPhase() >= pm.getPhaseStart()) {
                    //JSONArray changeJson = new JSONArray();
                    List<ProjectEventViewModel> payEventViewModels =
                            phaseAndEvent.selectPayEventViewModel(userId, ptm.getTalkId(), ProjectConst.TalkPhase);

                    result.setTemplateList(statusList);
                    result.setMapPhaseStatus(mapStatus);
                    result.setAuditEventList(eventLst);
                    result.setPayEventList(payEventViewModels);
                    result.setDefineTask(viewTaskModel);
                    return result;
                }
            }
            result.setTemplateList(statusList);
            result.setMapPhaseStatus(mapStatus);
            result.setAuditEventList(eventLst);
            result.setDefineTask(viewTaskModel);
            return result;
        }

        result.setMapPhaseStatus(mapStatus);
        return result;
    }

    //组员任务界面
    public ProjectTaskViewResult getPmProjectTask(UserProjectContext context, int userId, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectTaskViewResult result = new ProjectTaskViewResult();
        Map<String, Object> mapStatus = buildMapPhaseStatus(ptm);

        List<ViewProjectTemplateUserModel> statusList = selectViewProjectTemplateUserModel(ptm
                .getFolderId(), userId, context.getUserId());
        if (ptm.getPhase() >= TalkPhase) {
            List<ViewProjectEventModel> eventLst = getEventModels(context, phase, userId);
            result.setAuditEventList(eventLst);
            ViewTaskModel viewTaskModel = new ViewTaskService().selectViewTaskModel(pm.getProjectId(), TalkPhase, userId);
            result.setDefineTask(viewTaskModel);
        }

        result.setMapPhaseStatus(mapStatus);
        result.setTemplateList(statusList);
        return result;
    }

}
