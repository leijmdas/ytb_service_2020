package ytb.project.view.service.impl;

import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.model.*;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.impl.flow.FlowFloderView;
import ytb.project.service.impl.talk.ProjectEvent;
import ytb.project.service.project.change.ChangeFlow;
import ytb.project.view.model.ProjectChangeTaskViewResult;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.project.view.service.IChangeTaskView;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class ChangeTaskViewTalk extends FlowFloderView implements IChangeTaskView {

    /**
     * 变更项目视图
     * @param context
     * @param newTalkId
     * @param type
     * @param phase
     * @return
     */
    public ProjectChangeTaskViewResult getProjectChangeView(UserProjectContext context, int  newTalkId, String type,
                                                            int phase) {
        if ("PA".equals(type)) {
            return getPaProjectChangeTask(context, newTalkId,phase);
        } else if ("PB".equals(type)) {
            return getPbProjectChangeTask(context, newTalkId,phase);
        }
        else if ("PM".equals(type)) {
            return getPmProjectChangeTask(context, newTalkId,phase);
        }
        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
    }


    Map<String, Object> buildMapPhaseStatus(UserProjectContext context, ProjectTalkModel newPtm, ProjectChangeModel pcm ) {
        ProjectModel pm = context.getProjectModel();

        Map<String, Object> mapStatus = new HashMap<>();
        mapStatus.put("changeType", pm.getChangeType());
        mapStatus.put("oldTalkId", context.getTalkId());
        mapStatus.put("newTalkId", newPtm.getTalkId());
        mapStatus.put("phase", newPtm.getPhase());
        mapStatus.put("phaseStatus", pcm.getPhaseStatus());
        mapStatus.put("knitStatus", pcm.getEventType());//结项文档审核状态
        return mapStatus;
    }

    //甲方变更页面
    public ProjectChangeTaskViewResult getPaProjectChangeTask(UserProjectContext context, int
            newTalkId, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectChangeModel pcm = phase == ptm.getPhase() ?  context.getProjectChangeModel() :
                new ChangeDaoServiceImpl().getChangeByTalkAndPhase(context.getTalkId(),  phase);

        ProjectChangeTaskViewResult result = new ProjectChangeTaskViewResult();
        UserProjectContext newContext = new UserProjectContext(context, newTalkId);

        ProjectTalkModel newPtm = pm.getProjectTalkService().getProjectTalkById(newTalkId);

        Map mapStatus = buildMapPhaseStatus(context, newPtm, pcm);



        //控制甲方结项审核的按钮显示、隐藏
        int paKnitAuditStatus = 0;
        if(pcm.getChangeType() == 702){//大变更要显示结项
            //乙方阶段总文件夹
            ProjectFolderModel phaseFolder = getPhaseFolderByParent(ptm.getFolderId(), phase, pm.getProjectId());
            List<ViewProjectTemplateUserModel> templateList = new ArrayList<>();
            templateList = getFolderTreeTemplates(phaseFolder, templateList, pm.getUserId1(), context.getUserId());
            for(ViewProjectTemplateUserModel templateUserModel :templateList){
                if(templateUserModel.getStatus() == 6){
                    paKnitAuditStatus = 1;
                }else{
                    paKnitAuditStatus = 0;
                }
            }
            result.setKnitTemplateList(templateList);//甲方结项的List
        }

        ProjectFolderModel projectFolder = newPtm.fetchTalkFolderModel();
        List<ViewProjectTemplateUserModel> list = IProjectFileDAOService.getProjectTemplateUser().select(projectFolder.getFolderId(), pm.getUserId1());
        int auditDisplayPaTalk = getAuditDisplay(list);

        //PA事件
        List<ViewProjectEventModel> auditEventList = getEventModels(newContext, newPtm.getPhase(), pm.getUserId1());

        if (pcm.getPhaseStatus() == ProjectConstState.PRO_CHANGESTATUS_PAYMENT) {//准备支付
            BigDecimal fee = new ChangeFlow().selectFeeDiff(newContext);
            mapStatus.put("fee", fee);
        }

        if (pcm.getPhaseStatus() == ProjectConstState.PRO_CHANGESTATUS_CONFIRM_CHANGE/*50*/) {//变更结束
            List<ProjectEventViewModel> payList = new ProjectEvent().selectPayEventViewModel(pm
                    .getUserId1(), newPtm.getTalkId(), newPtm.getPhase());
            for (ProjectEventViewModel eventViewModel : payList) {

                if (eventViewModel.getEventUserId() == pm.getUserId1()) {
                    result.setPayEventList(payList);
                }
            }
        }

        mapStatus.put("auditDisplayPaTalk", auditDisplayPaTalk);//控制甲方变更文档审核按钮显示、隐藏
        mapStatus.put("paKnitAuditStatus",paKnitAuditStatus);//控制甲方结项文档审核显示、隐藏
        result.setAuditDisplayPaTalk(auditDisplayPaTalk);
        result.setMapPhaseStatus(mapStatus);
        result.setTemplateList(list);
        result.setAuditEventList(auditEventList);
        return result;
    }

    //乙方变更视图页面
    public ProjectChangeTaskViewResult getPbProjectChangeTask(UserProjectContext context, int
            newTalkId,int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectChangeTaskViewResult result = new ProjectChangeTaskViewResult();
        UserProjectContext newContext = new UserProjectContext(context, newTalkId);
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectChangeModel pcm = phase == ptm.getPhase() ?  context.getProjectChangeModel() :
                new ChangeDaoServiceImpl().getChangeByTalkAndPhase(context.getTalkId(),  phase);

        ProjectTalkModel newPtm = pm.getProjectTalkService().getProjectTalkById(newTalkId);

        int color = 0;//控制变更页面递交按钮点亮、灰色  0点亮 1灰色
        ProjectFolderModel projectFolder = newPtm.fetchTalkFolderModel();

        List<ViewProjectTemplateUserModel> templateUserModels =
                IProjectFileDAOService.getProjectTemplateUser().select(projectFolder.getFolderId(), newPtm.getUserId2());

        for (ViewProjectTemplateUserModel model : templateUserModels) {
            if (model.getActiveStatus() != ProjectConst.ACTIVE_STATUS_ToModify
                    && (model.isTemplateReq() || model.isTemplateWorkplan())) {
                color = 1;
            }
        }


        Map mapStatus = buildMapPhaseStatus(context, newPtm, pcm);
        mapStatus.put("color", color);//判断乙方递交按钮点亮

        //PB事件
        List<ViewProjectEventModel> auditEventList = getEventModels(newContext, newPtm.getPhase(), pm.getUserId1());
        if (pcm.getPhaseStatus() == ProjectConstState.PRO_CHANGESTATUS_PAYMENT) {
            List<ProjectEventViewModel> payList = new ProjectEvent().selectPayEventViewModel(pm
                    .getUserId1(), newPtm.getTalkId(), newPtm.getPhase());
            for (ProjectEventViewModel eventViewModel : payList) {
                if (eventViewModel.getEventUserId() == newPtm.getUserId2()) {
                    result.setPayEventList(payList);
                }
            }
        }

        //组长审核组员文档 组员事件
        List<ProjectTaskViewResult> pmEventResult = new ProjectTaskViewPhase()
                .pbSelectPmTaskViewResult(context, phase);
        result.setPmTaskViewResults(pmEventResult);

        result.setMapPhaseStatus(mapStatus);
        result.setTemplateList(templateUserModels);
        result.setAuditEventList(auditEventList);
        return result;
    }


    //乙方结项的视图
    public ProjectChangeTaskViewResult getPbKnitChangeTask(UserProjectContext context, int
            newTalkId, int usetId ,int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectChangeModel pcm = phase == ptm.getPhase() ?  context.getProjectChangeModel() :
                new ChangeDaoServiceImpl().getChangeByTalkAndPhase(context.getTalkId(),  phase);

        ProjectChangeTaskViewResult result = new ProjectChangeTaskViewResult();

        List<ViewProjectTemplateUserModel> list = getKnitTemplateList(context,usetId,phase);

        int auditDisplayPaTalk = getAuditDisplay(list);

        ProjectTalkModel newPtm = pm.getProjectTalkService().getProjectTalkById(newTalkId);

        Map mapStatus = buildMapPhaseStatus(context, newPtm, pcm);
        int subMitStatus = 0;//0灰色 1显示  2不显示
        List<WorkGroupMemberModel> list1 = ptm.selectWorkGroupMember();
        for (WorkGroupMemberModel workGroupMember : list1) {
            if (workGroupMember.getUserId() != pm.getUserId1()  && workGroupMember.getUserId() != ptm.getUserId2()) {
                List<ProjectFolderModel> memberFolder = getProjectPhaseFolders(ptm.getProjectId(), workGroupMember.getUserId(),
                        phase );
                for (ProjectFolderModel folderModel : memberFolder) {
                    if (folderModel.getFolderStatus() != ProjectConst.FOLDER_STATUS_PASS_PM
                            || folderModel.getAuditor() != context.getUserId()) {
                        subMitStatus = 1;//灰色
                    }
                }
            }
        }

        mapStatus.put("subMitStatus",subMitStatus);
        mapStatus.put("auditDisplayPaTalk",auditDisplayPaTalk);
        result.setMapPhaseStatus(mapStatus);
        result.setTemplateList(list);
        return result;
    }


    //组员结项视图
    public ProjectChangeTaskViewResult getPmKnitChangeTask(UserProjectContext context, int
            newTalkId,int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectChangeModel pcm = phase == ptm.getPhase() ? context.getProjectChangeModel() :
                new ChangeDaoServiceImpl().getChangeByTalkAndPhase(context.getTalkId(), phase);

        ProjectTalkModel newPtm = pm.getProjectTalkService().getProjectTalkById(newTalkId);

        ProjectChangeTaskViewResult result = new ProjectChangeTaskViewResult();

        //获取所有文档
        List<ProjectFolderModel> pmFolders = getProjectPhaseFolders(ptm.getProjectId(), context.getUserId(), phase);
        //PM文档
        List<ViewProjectTemplateUserModel> templateUserList = getPmFoldersTemplates(pmFolders,
                context.getUserId(), context.getUserId());
        Map mapStatus = buildMapPhaseStatus(context, newPtm, pcm);

        int auditDisplayPaTalk = 0 ;//不显示(控制变更文件的审核)
        for(ViewProjectTemplateUserModel templateUserModel : templateUserList){
            if(templateUserModel.getStatus() == ProjectConst.TASK_STATUS_NotSubmitted ){
                auditDisplayPaTalk = 1;//显示
            }else{
                auditDisplayPaTalk = 0;//bu显示
            }
        }

        mapStatus.put("auditDisplayPaTalk",auditDisplayPaTalk);
        result.setTemplateList(templateUserList);
        result.setMapPhaseStatus(mapStatus);
        return result;
    }



    //组员变更视图页面
    public ProjectChangeTaskViewResult getPmProjectChangeTask(UserProjectContext context, int newTalkId, int phase) {
        ProjectModel pm = context.getProjectModel();
        ProjectChangeTaskViewResult result = new ProjectChangeTaskViewResult();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectChangeModel pcm = phase == ptm.getPhase() ? context.getProjectChangeModel() :
                new ChangeDaoServiceImpl().getChangeByTalkAndPhase(context.getTalkId(), phase);
        ProjectTalkModel newPtm = pm.getProjectTalkService().getProjectTalkById(newTalkId);
        ProjectFolderModel projectFolder = newPtm.fetchTalkFolderModel();
        List<ViewProjectTemplateUserModel> templateUserModels =
                IProjectFileDAOService.getProjectTemplateUser().select(projectFolder.getFolderId
                        (), context.getUserId());

        //PM事件
        List<ViewProjectEventModel> eventList = getEventModels(context, phase, context.getUserId());


        Map mapStatus = buildMapPhaseStatus(context, newPtm, pcm);
        result.setMapPhaseStatus(mapStatus);
        result.setTemplateList(templateUserModels);
        result.setAuditEventList(eventList);
        return result;
    }

    public int getAuditDisplay(List<ViewProjectTemplateUserModel> list){
        int auditDisplayPaTalk = 0 ;//不显示
        for(ViewProjectTemplateUserModel templateUserModel : list){
            if(templateUserModel.getDocType() != ProjectConst.Template_TYPE_chng && templateUserModel.getStatus()
                    == ProjectConst.TASK_STATUS_Passing){
                auditDisplayPaTalk = 1;//显示
            }else if(templateUserModel.getDocType() != ProjectConst.Template_TYPE_chng &&
                    templateUserModel.getStatus()
                            == ProjectConst.TASK_STATUS_NotStart){
                auditDisplayPaTalk = 0;//bu显示
            }
        }

        return auditDisplayPaTalk;
    }
}