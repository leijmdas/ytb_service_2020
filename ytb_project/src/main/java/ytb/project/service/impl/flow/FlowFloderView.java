package ytb.project.service.impl.flow;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.model.*;
import ytb.project.service.IFlowFolderView;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;

import java.util.*;

public class FlowFloderView extends FlowFolderAssist implements IFlowFolderView {

    public void checkTemplateList(UserProjectContext context, int templateId, int status, int talkId) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectTemplateUserModel pa = IProjectFileDAOService.getProjectTemplateUser().selectOne(pm.getUserId1(),
                templateId);
        ProjectTemplateUserModel pb = IProjectFileDAOService.getProjectTemplateUser().selectOne(ptm.getUserId2(),
                templateId);
        if (status == 3) {
            pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            pa.setStatus(ProjectConst.TASK_STATUS_Passing);
            pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            pb.setStatus(ProjectConst.TASK_STATUS_Passing);
        } else {
            pa.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            pa.setStatus(ProjectConst.TASK_STATUS_NotPassing);
            pb.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            pb.setStatus(ProjectConst.TASK_STATUS_NotPassing);
        }
        IProjectFileDAOService.getProjectTemplateUser().modify(pa);
        IProjectFileDAOService.getProjectTemplateUser().modify(pb);
    }

    public List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(  int folderId, int userId,
                                                                                   int author) {

        List<ViewProjectTemplateUserModel> models = IProjectFileDAOService.getProjectTemplateUser()
                .selectViewProjectTemplateUserModel(  folderId, userId, author );
        //加个判断，只有当点击查看任务才进这里
        for (ViewProjectTemplateUserModel model : models){
            if(author == model.getShareReceive()){
                model.setViewShare(true);
            }
        }
        return models;

    }

    public List<ViewProjectTemplateUserModel> getPmFoldersTemplates(List<ProjectFolderModel> folderModels,
                                                                    int userId, int author) {
        List<ViewProjectTemplateUserModel> result = new ArrayList<>();
        for(ProjectFolderModel folderModel: folderModels) {
            //获取该文件夹下的文档
            List<ViewProjectTemplateUserModel> models = IProjectFileDAOService.getProjectTemplateUser()
                    .selectViewProjectTemplateUserModel(folderModel.getFolderId(), userId, author);
            result.addAll(models);
        }
        return result;
    }

    public Map<String,Object> getProjectTab(UserProjectContext context) {
        Map<String, Object> tabMap = new HashMap<>();

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<TabPhaseModel> tabList =  getProjectTabResult(context);
        tabMap.put("tabList", tabList);
        tabMap.put("currPhase", ptm.getPhase());
        tabMap.put("changeStatus", ptm.getChangeStatus());
        tabMap.put("phaseStart", pm.getPhaseStart());
        tabMap.put("phaseNo", pm.getPhaseNo());
        tabMap.put("changeType",pm.getChangeType());
        return tabMap;
    }


    //乙方结项文档
    @Override
    public List<ViewProjectTemplateUserModel> getKnitTemplateList(UserProjectContext context,int
            userId, int phase) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        //PB文档
        List<ViewProjectTemplateUserModel> templateList = new ArrayList<>();

        //当前阶段的总文件夹
        ProjectFolderModel phaseFolder = getPhaseFolderByParent(ptm.getFolderId(), phase, pm.getProjectId());
        //PB文档
        templateList = getFolderTreeTemplates(phaseFolder, templateList, userId, context.getUserId());

        return templateList;
    }

    private List<TabPhaseModel> getProjectTabResult(UserProjectContext context) {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();

        List<TabPhaseModel> tabPhaseList = new ArrayList<>();
        TabPhaseModel phaseModel = new TabPhaseModel();
        phaseModel.setPhaseText("洽谈阶段");
        phaseModel.setPhase(ProjectConst.TalkPhase);
        phaseModel.setPhaseStatus(0);
        //洽谈终止
        if(ptm.getChangeStatus() == ProjectConstState.CHNAGE_TYPE_TALK_TERM){
            phaseModel.setChangeStatus(ProjectConstState.CHNAGE_TYPE_TALK_TERM);
        }
        tabPhaseList.add(phaseModel);
        if ( ptm.getPhase() == ProjectConst.TalkPhase ) {//洽谈阶段
            phaseModel.setPhaseStatus(ptm.getPhaseStatus());
            return tabPhaseList;
        }


        for (int i = 1; i <= ptm.getPhase() - pm.getPhaseStart() + 1; i++) {//正常流程
            TabPhaseModel tm = new TabPhaseModel();
            tm.setPhase(pm.getPhaseStart() + i - 1);
            tm.setPhaseStatus(ptm.getPhaseStatus());
            tm.setChangeStatus(ptm.getChangeStatus());
            tm = (pm.getPhaseStart() + i - 1) == pm.getPhaseEnd() ?
                    tm.setPhaseText("交付") : tm.setPhaseText("第" + i + "阶段");
            tabPhaseList.add(tm);
        }

        List<TabPhaseModel> changeModels = new ChangeDaoServiceImpl().getChangeModelList(pm.getProjectId());
        for (int i = 1; i <= changeModels.size(); i++) {//变更终止
            TabPhaseModel cPhaseModel = changeModels.get(i-1);
            cPhaseModel.setChangeStatus(cPhaseModel.getChangeType());
            if (cPhaseModel.getChangeType() == ProjectConstState.CHNAGE_TYPE_STOP) {
                cPhaseModel.setPhaseText("终止(" + i + ")");
            } else {
                cPhaseModel.setPhaseText("变更需求洽谈(" + i + ")");
            }
        }
        tabPhaseList.addAll(changeModels);

        Collections.sort(tabPhaseList, new Comparator<TabPhaseModel>() {
            @Override
            public int compare(TabPhaseModel a, TabPhaseModel b) {
                int flag = a.getPhase().compareTo(b.getPhase());//先根据phase升序排列
                if(flag == 0) {
                    flag = b.getChangeType() - a.getChangeType();//根据changType降序
                }
                return flag;
            }
        });
        return tabPhaseList;
    }
    //获取阶段事件
    public List<ViewProjectEventModel> getEventModels(UserProjectContext context, int phase, int userId) {

        return getEventModelsNoUse(context, phase, userId, ProjectEventModel.EVENT_SERVICE_START, ProjectEventModel.EVENT_SERVICE_END);

    }

    public List<ViewProjectEventModel> getAssistEventModels(UserProjectContext context, int phase, int userId) {

        return getEventModelsNoUse(context, phase, userId, ProjectEventModel.EVENT_SERVICE_ASSIST, ProjectEventModel.EVENT_SERVICE_ASSIST);
    }

   /* //获取协助的事件
    List<ViewProjectEventModel> getEventModels(UserProjectContext context, int phase, int userId, int serviceTypeStart, int serviceTypeEnd) {

        List<ViewProjectEventModel> viewProjectEventList = new ArrayList<>();
        ViewProjectEventModel eventModel = new ViewProjectEventModel();
        List<ProjectEventViewModel> viewModels = spBuildProjectEventViewModels(
                context, phase, userId, serviceTypeStart,serviceTypeEnd);

        for (int i = 0; i < viewModels.size(); i++) {
            if (viewModels.get(i).getEventType() == ProjectConst.TASK_STATUS_Submitted) {//已递交文档
                eventModel.setSubmitEvent(viewModels.get(i));
                if (i == viewModels.size() - 1) {
                    viewProjectEventList.add(eventModel);     //所有记录最后一条
                }
            } else {
                if (viewModels.get(i).getEventType() == ProjectConst.TASK_STATUS_Passing_PA
                        || viewModels.get(i).getEventType() == ProjectConst.TASK_STATUS_NotPassing_PA) {
                    eventModel.setAuditEvent(viewModels.get(i));
                    viewProjectEventList.add(eventModel);
                    //审核对最后一条
                    eventModel = new ViewProjectEventModel();
                }
            }
        }
        return viewProjectEventList;
    }*/


    List<ViewProjectEventModel> getEventModelsNoUse(UserProjectContext context, int phase,int userId,int serviceTypeStart, int serviceTypeEnd) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ViewProjectEventModel> totalModels = new ArrayList<>();
        List<ProjectEventViewModel> viewModels = spBuildProjectEventViewModels(
                context, phase, userId, serviceTypeStart,serviceTypeEnd);
        ViewProjectEventModel eventModel = new ViewProjectEventModel();

        for (int i = 0; i < viewModels.size(); i++) {
            if (viewModels.get(i).getEventType() == ProjectConst.TASK_STATUS_Submitted) {//已递交文档
                eventModel.setSubmitEvent(viewModels.get(i));
                if (i == viewModels.size() - 1) {
                  List<ViewProjectTemplateUserModel> templateUserModels =
                          selectViewProjectTemplateUserModel(
                                    ptm.getFolderId(), pm.getUserId1(),context.getUserId());//查询文档
                    eventModel.setTemplateList(templateUserModels);
                    totalModels.add(eventModel);     //所有记录最后一条
                }
            } else {
               if (viewModels.get(i).getEventType() == ProjectConst.TASK_STATUS_Passing_PA
                        || viewModels.get(i).getEventType() == ProjectConst.TASK_STATUS_NotPassing_PA) {
                    eventModel.setAuditEvent(viewModels.get(i));
                    totalModels.add(eventModel);
                    //审核对最后一条
                    eventModel = new ViewProjectEventModel();
                }
            }
        }
        return totalModels;
    }

    List<ProjectEventViewModel> spBuildProjectEventViewModels(UserProjectContext context, int phase, int userId,
                                                              int serviceStart, int serviceEnd) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //用户ID
        return YtbSql.spDb(
                ProjectEventViewModel.class,
                "ytb_project.fnPEvent_audit",
                phase == ProjectConst.TalkPhase ? ptm.getUserId2() : userId,
                phase,
                pm.getProjectId(),
                serviceStart,
                serviceEnd);
    }

    //检查文档是否都已修改
    protected int checkProjectTemplateUserStatus(List<ViewProjectTemplateUserModel> models, int color) {
        for (ViewProjectTemplateUserModel templateUserModel : models) {
            color=templateUserModel.checkColor(color);

        }
        return color;
    }


    //检查文档是否都已修改
    protected int checkProjectTemplateUserStatus(int folderId, int userId, int color) {

        List<ViewProjectTemplateUserModel> list = IProjectFileDAOService.getProjectTemplateUser().select(folderId, userId);
        for (ViewProjectTemplateUserModel vptu : list) {
            if (vptu.getActiveStatus() != ProjectConst.ACTIVE_STATUS_ToModify) {
                color = 1;
            }
        }
        return color;
    }


}
