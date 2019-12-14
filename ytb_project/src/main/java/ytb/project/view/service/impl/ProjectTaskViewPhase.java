package ytb.project.view.service.impl;


import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.talk.IPhaseAndEvent;
import ytb.project.view.dao.ITalkTaskView;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;
import ytb.project.view.model.ProjectTaskViewResult;
import ytb.manager.template.model.Dict_ProjectTypeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectTaskViewPhase extends ProjectTaskViewTalk implements ITalkTaskView {


    public ProjectTaskViewResult getPaProjectTask (UserProjectContext context, int userId, int phase) {

        ProjectTaskViewResult result = new ProjectTaskViewResult();

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();
        IPhaseAndEvent phaseAndEvent = ProjectTalkModel.getPhaseAndEvent();


        int color = 0;
        int auditDisplayPaTalk = 1;//0显示  1灰色
        //乙方阶段总文件夹
        ProjectFolderModel phaseFolder = getPhaseFolderByParent(ptm.getFolderId(), phase, pm.getProjectId());

        List<ViewProjectTemplateUserModel> templateList = new ArrayList<>();
        templateList = getFolderTreeTemplates(phaseFolder, templateList, pm.getUserId1(), context.getUserId());


        if(phase < ptm.getPhase()){
            for(ViewProjectTemplateUserModel vm : templateList){
                vm.setDisplayStatus(1);
            }
        }

        //获取乙方事件
        List<ViewProjectEventModel> auditEventModels = getEventModels(context, phase, pm.getUserId1());
        ViewTaskModel defineTask = new ViewTaskService().selectViewTaskModel(pm.getProjectId(), phase, userId);

        color = IProjectFileDAOService.getProjectTemplateUser().paCheckModifyStatus(phaseFolder.getFolderId(),
                color, pm.getUserId1(), context.getUserId());

        if (phaseFolder.getFolderStatus() == ProjectConst.FOLDER_STATUS_SUBMIT_PB && ptm.getPhase() == phase && color == 0) {
            auditDisplayPaTalk = 0;  //审核按钮
        } else if (phaseFolder.getFolderStatus() == ProjectConst.FOLDER_STATUS_PASS_PM) {
            auditDisplayPaTalk = 2;
        }
        Map<String, Object> mapPhaseStatus = buildMapPhaseStatus(ptm);

        if (!dict_ptm.isPurchaseProcessing()) {

            if (ptm.getPhase() == phase && ptm.getPhaseStatus() == ProjectConst.TalkPhase_STATUS_PAY) {
                BigDecimal totalFee = getInst().getFlowPay().getProjectCostFee().getProjectCostTotalFee(context);
                mapPhaseStatus.put("totalFee", totalFee);
            }
            //查看上一阶段的费用
            if (ptm.getPhase() > phase) {

                List<ProjectEventViewModel> payEventList = phaseAndEvent.selectPayEventViewModel(pm
                        .getUserId1(),  ptm.getTalkId(), phase);
                result.setPayEventList(payEventList);
            }
        } else {
            mapPhaseStatus.put("isPurchase", 1);
        }

        result.setMapPhaseStatus(mapPhaseStatus);
        result.setTemplateList(templateList);
        result.setAuditEventList(auditEventModels);
        result.setAuditDisplayPaTalk(auditDisplayPaTalk);
        result.setDefineTask(defineTask);

        return result;

    }

    List<ViewProjectTemplateUserModel> takeoutPbTemplates(UserProjectContext context, List<ViewProjectTemplateUserModel> templateList) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ViewProjectTemplateUserModel> pbTemplates = new ArrayList<>();
        for (ViewProjectTemplateUserModel model : templateList) {
            if (model.getAuthor() == ptm.getUserId2()) {
                pbTemplates.add(model);
            }
        }
        return pbTemplates;
    }

    List<ViewProjectTemplateUserModel> takeoutPmTemplates(UserProjectContext context,int pmUserId,
                                                          List<ViewProjectTemplateUserModel> templateList) {

        List<ViewProjectTemplateUserModel> pmTemplates = new ArrayList<>();
        for (ViewProjectTemplateUserModel model : templateList) {
            if (model.getAuthor() == pmUserId) {
                pmTemplates.add(model);
            }
        }
        return pmTemplates;
    }
    List<ViewProjectTemplateUserModel> takeoutPmTemplates(UserProjectContext context,
                                                          List<ViewProjectTemplateUserModel> templateList) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ViewProjectTemplateUserModel> pmTemplates = new ArrayList<>();
        for (ViewProjectTemplateUserModel model : templateList) {
            if (model.getAuthor() != ptm.getUserId2()) {
                pmTemplates.add(model);
            }
        }
        return pmTemplates;
    }

    //乙方任务进行中
    public ProjectTaskViewResult getPbProjectTask(UserProjectContext context, int userId, int phase) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();
        IPhaseAndEvent phaseAndEvent = ProjectTalkModel.getPhaseAndEvent();

        ProjectTaskViewResult result = new ProjectTaskViewResult();
        //当前阶段的总文件夹
        ProjectFolderModel phaseFolder = getPhaseFolderByParent(ptm.getFolderId(), phase, pm.getProjectId());
        //PB文档
        List<ViewProjectTemplateUserModel> templateList = new ArrayList<>();
        templateList = getFolderTreeTemplates(phaseFolder, templateList, userId, context.getUserId());

        List<ViewProjectTemplateUserModel> pbTemplates = takeoutPbTemplates(context,templateList);

         // int color = 0; color = checkProjectTemplateUserStatus(templateList, color);

        if(phase < ptm.getPhase()){
            for(ViewProjectTemplateUserModel vm : templateList){
                vm.setDisplayStatus(1);
            }
        }

        Map<String, Object> mapPhaseStatus = buildMapPhaseStatus(ptm);
        int subMitStatus = 0;//0灰色 1显示  2不显示
        //判断是否是加工采购
        if (dict_ptm.NotPurchaseProcessing()) {//正常项目 非加工采购项目
            for (WorkGroupMemberModel workGroupMember : ptm.selectWorkGroupMember()) {
                if (workGroupMember.getUserId() != pm.getUserId1()  && workGroupMember.getUserId() != ptm.getUserId2()) {
                    List<ProjectFolderModel> memberFolder = getProjectPhaseFolders(ptm.getProjectId(), workGroupMember.getUserId(),
                            phase );
                    for (ProjectFolderModel projectFolder : memberFolder) {
                        if (projectFolder.getFolderStatus() != ProjectConst.FOLDER_STATUS_PASS_PM
                                || projectFolder.getAuditor() != userId /*|| color == 1*/) {
                            subMitStatus = 1;//灰色
                        }
                    }
                }
            }
        } else if (dict_ptm.isPurchase()) {//采购项目
            mapPhaseStatus.put("isPurchase", 1);
            ProjectMemberTask memberTask = ProjectFlowService.getTalkService().getPhaseTaskMFlow(pm.getProjectId());
            result.setPhaseTask(memberTask);
        } else if (dict_ptm.isProcessing()) {//加工项目
            mapPhaseStatus.put("isPurchase", 2);
            for (ViewProjectTemplateUserModel userModel : templateList) {
                if (userModel.getOwerTemplate() == 1 && (userModel.getStatus() == ProjectConst.TASK_STATUS_Passing
                        || userModel.getStatus() == ProjectConst.TASK_STATUS_Submitted)) {//已递交、通过 页面上递交按钮消失
                    subMitStatus = 2;
                } else if (userModel.getStatus() == ProjectConst.TASK_STATUS_NotStart) {//状态为未开始，递交按钮为灰色
                    subMitStatus = 1;
                } else if (userModel.getStatus() == ProjectConst.TASK_STATUS_NotSubmitted
                        && userModel.getActiveStatus() == ProjectConst.TASK_STATUS_Writing) {   //未递交，去修改点亮递交按钮
                    subMitStatus = 0;
                }
            }
            mapPhaseStatus.put("subMitStatus", subMitStatus);
        }

        if ((phaseFolder.getFolderStatus() == ProjectConst.FOLDER_STATUS_SUBMIT_PM
                || phaseFolder.getFolderStatus() == ProjectConst.FOLDER_STATUS_PASS_PM)
                && ptm.getPhase() != phase) {
            subMitStatus = 2;//负责人提交按钮消失
        }
        //PB事件
        List<ViewProjectEventModel> auditEventList = getEventModels(context, phase, pm.getUserId1());

        //PB自定义任务视图
        ViewTaskModel viewTaskModel = new ViewTaskService().selectViewTaskModel(pm.getProjectId(), phase, userId);

        Map mapSubmit = new HashMap();
        mapSubmit.put("subMitStatus", subMitStatus);
        mapSubmit.put("endTime", ptm.getPhaseEndTime(context));
        if (ptm.getPhase() > phase) {
            List<ProjectEventViewModel> eventViewModels = phaseAndEvent.selectPayEventViewModel(pm.getUserId1(),
                    ptm.getTalkId(), phase);
            result.setPayEventList(eventViewModels);
        }


        result.setMapPhaseStatus(mapPhaseStatus);
        result.setMapSubmit(mapSubmit);
        result.setAuditEventList(auditEventList);
        result.setTemplateList(pbTemplates);
        result.setDefineTask(viewTaskModel);

        Map<Integer, ProjectTaskViewResult> taskViewResultMap = buildPmTaskViewResultMap(context, phase);
        result.setPmTaskViewResults(taskViewResultMap.values());


        for(Integer pmUserId:taskViewResultMap.keySet()){
            List<ViewProjectTemplateUserModel> pmModels = takeoutPmTemplates(context,pmUserId,templateList);
            taskViewResultMap.get(pmUserId).setTemplateList(pmModels);

        }

        //组长审核组员文档 组员事件
        //List<ProjectTaskViewResult> pmEventResult = pbSelectPmTaskViewResult(context, phase);
        //result.setPmTaskViewResults(pmEventResult);
        return result;

    }

    //组员阶段视图
    public ProjectTaskViewResult getPmProjectTask(UserProjectContext context, int userId, int phase) {
        ProjectTaskViewResult result = new ProjectTaskViewResult();

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //PM事件
        List<ViewProjectEventModel> eventModels = getEventModels(context, phase, userId);
        //获取所有文档
        List<ProjectFolderModel> pmFolders = getProjectPhaseFolders(ptm.getProjectId(), userId, phase);
        //PM文档
        List<ViewProjectTemplateUserModel> templateList = getPmFoldersTemplates(pmFolders, userId, context.getUserId());
        if(phase < ptm.getPhase()){
            for(ViewProjectTemplateUserModel vm : templateList){
                vm.setDisplayStatus(1);
            }
        }
        int color = 0;
        color = checkProjectTemplateUserStatus(templateList, color);

        int subMitStatus = 0;
        for (ProjectFolderModel projectFolder : pmFolders) {
            if (projectFolder.getFolderStatus() == ProjectConst.FOLDER_STATUS_WRITE_PM
                    || projectFolder.getFolderStatus() == ProjectConst.FOLDER_STATUS_NOTPASS_PM  ) {
                int state = color == 0 ? 0 : 1;//0:点亮;1:灰色
                if(subMitStatus < state){
                    subMitStatus = state;
                }
            } else {
                if(subMitStatus<2) {
                    subMitStatus = 2;//不显示递交按钮
                }
            }
        }
        Map mapSubmit = new HashMap();
        mapSubmit.put("subMitStatus", subMitStatus);
        mapSubmit.put("endTime", ptm.getPhaseEndTime (context));

        //PM自定义任务视图
        ViewTaskModel viewTaskModel = new ViewTaskService().selectViewTaskModel(pm.getProjectId(),phase, userId);

        //Map<String,Object> mapStatus ;
        result.setMapSubmit(mapSubmit);

        result.setMapPhaseStatus(buildMapPhaseStatus(ptm));
        result.setTemplateList(templateList);
        result.setAuditEventList(eventModels);
        result.setDefineTask(viewTaskModel);

        return result;
    }

    //            if (memberModel.getUserId() == pm.getUserId1() || memberModel.getUserId() != ptm.getUserId2()) {
//                continue;
//            }
    public Map<Integer, ProjectTaskViewResult> buildPmTaskViewResultMap(UserProjectContext context, int phase) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        Map<Integer, ProjectTaskViewResult> pmTaskViewResultMap = new HashMap<>();
        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMemberPM()) {

            ProjectTaskViewResult viewResult = pbSelectPmTaskViewResult(context, memberModel.getUserId(), phase);
            pmTaskViewResultMap.put(memberModel.getUserId(), viewResult);
        }
        return pmTaskViewResultMap;


    }


    public void splitPmTaskViewResult (UserProjectContext context, int phase,Map<Integer, ProjectTaskViewResult> map) {

        Map<Integer,ProjectTaskViewResult> taskViewResultMap=new HashMap<>();

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        for (WorkGroupMemberModel model : ptm.selectWorkGroupMemberPM()) {
            //找到组员
            if (model.getUserId() != pm.getUserId1()  && model.getUserId() != ptm.getUserId2()) {
              //results.add(pbSelectPmTaskViewResult(context, model, phase));
            }
        }

    }

    public List<ProjectTaskViewResult> pbSelectPmTaskViewResult(UserProjectContext context, int phase) {
        List<ProjectTaskViewResult> results = new ArrayList<>();

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        for (WorkGroupMemberModel model : ptm.selectWorkGroupMemberPM()) {
            //找到组员
            if (model.getUserId() != pm.getUserId1()  && model.getUserId() != ptm.getUserId2()) {
                results.add(pbSelectPmTaskViewResult(context, model.getUserId(), phase));
            }
        }
        return results;

    }
    ProjectTaskViewResult selectPmTaskViewResult(UserProjectContext context, WorkGroupMemberModel memberModel,
                                                   int phase) {
        ProjectTaskViewResult result = new ProjectTaskViewResult();

        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ProjectFolderModel> folderModels = getUserPhaseFolders(ptm.getTalkId(), memberModel.getUserId(), phase);
        for (ProjectFolderModel folderModel : folderModels) {
            //userId,subMitStatus
            Map mapSubmit = buildMapSubmitUser(memberModel.getUserId(), folderModels, context, phase);
            //setMapSubmit
            result.setMapSubmit(mapSubmit);
            //setTemplateList
            List<ViewProjectTemplateUserModel> templateList = selectViewProjectTemplateUserModel
                    (folderModel.getFolderId(), ptm.getUserId2(), context.getUserId());
            result.setTemplateList(templateList);
            //setAuditEventList
            List<ViewProjectEventModel> auditEventList = getEventModels(context, phase, memberModel.getUserId());
            result.setAuditEventList(auditEventList);
        }

        return result;
    }
    ProjectTaskViewResult pbSelectPmTaskViewResult(UserProjectContext context, int pmUserId,  int phase) {
        ProjectTaskViewResult result = new ProjectTaskViewResult();

        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ProjectFolderModel> folderModels = getUserPhaseFolders(ptm.getTalkId(), pmUserId, phase);
        for (ProjectFolderModel folderModel : folderModels) {
            //userId,subMitStatus
            Map mapSubmit = buildMapSubmitUser(pmUserId, folderModels, context, phase);
            //setMapSubmit
            result.setMapSubmit(mapSubmit);
            //setTemplateList
            List<ViewProjectTemplateUserModel> templateList = selectViewProjectTemplateUserModel
                    (folderModel.getFolderId(), pmUserId, context.getUserId());
            result.setTemplateList(templateList);
            //setAuditEventList
            List<ViewProjectEventModel> auditEventList = getEventModels(context, phase, pmUserId);
            result.setAuditEventList(auditEventList);
        }

        return result;
    }

    Map buildMapSubmitUser(int userIdPM, List<ProjectFolderModel> folderModels, UserProjectContext context, int phase) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        int color = 0;
        int subMitStatus = ProjectTemplateUserModel.DISPLAY_STATUS_LIGHT;//显示灰色
        for (ProjectFolderModel folderModel : folderModels) {
            color = IProjectFileDAOService.getProjectTemplateUser().paCheckModifyStatus(folderModel.getFolderId(),
                    color, ptm.getUserId2(), context.getUserId());
            //pb  ProjectConst.FOLDER_STATUS_WRITE_PM
            if (folderModel.getFolderStatus() == 2 && folderModel.getAuditor() == ptm.getUserId2()
                    && color == 0) {
                if (subMitStatus < ProjectTemplateUserModel.DISPLAY_STATUS_GRAY) {
                    subMitStatus = ProjectTemplateUserModel.DISPLAY_STATUS_GRAY;
                }
            }
            //pa
            else if (folderModel.getFolderStatus() == 3 || (folderModel.getFolderStatus() == 2
                    && folderModel.getAuditor() == pm.getUserId1())) {
                if (subMitStatus < ProjectTemplateUserModel.DISPLAY_STATUS_HIDE) {
                    subMitStatus = ProjectTemplateUserModel.DISPLAY_STATUS_HIDE;
                }
            }

        }
        Map mapSubmit = new HashMap();
        mapSubmit.put("userId", userIdPM);
        mapSubmit.put("subMitStatus", subMitStatus);
        return mapSubmit;
    }


}
