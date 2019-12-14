package ytb.project.service.project.change;

import ytb.common.ytblog.YtbLog;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.daoservice.ProjectDAOService;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.PBFolder;
import ytb.project.model.projectFolderView.projectTemplate.ProjectChange.CopyTemplateModelChange;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;

import ytb.project.model.tagtable.ProjectChngPModel;
import ytb.project.service.project.change.impl.ChangeService;
import ytb.project.view.model.ProjectChangeResult;
import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

public class ChangeFlow extends ChangeService implements IChangeService {

    //取消变更
    public void cancelChange(UserProjectContext context) {
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更结项！");
        }
        ProjectModel pm = context.getProjectModel();
        if (pm.getNewProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更项目或者不在变更状态中,无法取消！");

        }

    }

    //项目结项中
    @Override
    public void closeProject(UserProjectContext context) {
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更结项！");
        }
        ProjectModel pm = context.getProjectModel();
        if (pm.getNewProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更项目或者不在变更结项状态中！");

        }
    }

    ////结项费用=②+（③-④负数取0：多退少不补）+⑤+⑥
    //    //② 已支付工时费=  原工作计划阶段支付工时费合计x.xx
    //    //⑤ 支付当前阶段工时费=  当前p阶段的工时费x.xx
    //    //⑥ 支付补偿金（②+⑤）*毛利率= x.xx
    //    //③ 采购和加工预算=  （p-3）*总预算/3（p≥4/5，p＜4，则为0）
    //    //④ 已采购和加工发票总额=   x.xx
    // phase pay
    public ProjectChngPModel selectFeeClose(UserProjectContext oldContext) {
        ProjectModel pm = oldContext.getProjectModel();
        ProjectTalkModel newPtm = getNewProjectTalk(pm.getNewProjectId());
        UserProjectContext newContext = new UserProjectContext(oldContext, newPtm.getTalkId());

        if (oldContext.getProjectModel().isChangeBig()) {
            ProjectChngPModel chngPModel = selectOneByDocumentId(newContext);
            return chngPModel;
        }
        return new ProjectChngPModel(oldContext,newContext);
    }

    //<0支付，>0退款
    public BigDecimal selectFeeDiff(UserProjectContext newContext) {

        return selectOneByDocumentId(newContext).getFeeDiff();
    }

    //申请项目变更
    public ApplyResult paApplyChange(UserProjectContext context) {
        if (context.isChangeStop()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "终止项目不能变更！");
        }

        if (!context.isPa()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "只有甲方才可以提出变更！");
        }

        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更！");
        }

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (pm.getNewProjectId() > 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目正在变更中,不能再次发起变更！");
        }


        ApplyResult applyResult = new ApplyResult(context);

        UserInfoModel infoModel = context.getInstUser().getUserCenterService().getUserInfoById(ptm.getUserId2());
        applyResult.setNickName(infoModel.getNickName());
        if (ptm.getCompanyId2() > 0) {
            CompanyInfoModel model = context.getInstUser().getCompanyCenterService().getCompanyInfoById(ptm.getCompanyId2());
            applyResult.setCompanyName(model.getCompanyName());
        }
        applyResult.setReApply(needReApply(context));

        return applyResult;

    }


    public ApplyResult reApplyChange(UserProjectContext context) {
        //needReApply(context)
        //changeType改成littleChange
        return paApplyChange(context);
    }

    public ApplyResult newApplyChange(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        if (pm.isChangeBig()) {
            // phase 比较
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "大变更只能一次");
        }
        if (pm.isChangeStop()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "终止项目不能再变更！");
        }
        return paApplyChange(context);
    }

      /* ProjectModel pm = context.getProjectModel();
            if (pm.getNewProjectId() > 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更项目或者不在草稿状态中！");
        }*/
    public ProjectChangeResult paAgreeChange(UserProjectContext context,String displayItems, String changeItems) throws
            UnsupportedEncodingException {
        if (!context.isPa()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "只有甲方才可以确认变更！");
        }
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更结项！");
        }

        ProjectChangeResult result =needReApply(context) ? pbAgreeChangeReApply(context) :
                pbAgreeChangeFirst(context, changeItems);
        //判断有没 有存在！
        //UserProjectContext oldContext=new UserProjectContext(context,talkId);
        //添加一条变更记录oldProjectId,
        ProjectChangeModel changeModel = new ProjectChangeModel(context);
        changeModel.setNewProjectId(result.getNewProjectId());
        changeModel.setItems(displayItems);
        changeModel.setChangeType(result.getChangeType());
        changeModel.setPhaseStatus(ProjectConstState.PRO_CHANGESTATUS_PB_MAKE);
        changeModel.setEventType(ProjectConstState.PRO_CHANGESTATUS_PB_MAKE);
        addChange(changeModel);

        return  result;

    }

    //changeType 701小变更 702大变更
    ProjectChangeResult pbAgreeChangeReApply(UserProjectContext context){

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //set project Status = ProjectConstState.CHNAGE_TYPE_SMALL
        reCopyTemplates(context);

        ProjectChangeResult changeResult = new ProjectChangeResult();
        changeResult.setOldTalkId(ptm.getTalkId());
        changeResult.setNewProjectId(pm.getNewProjectId());
        ProjectTalkModel newPtm = getNewProjectTalk(pm.getNewProjectId());
        changeResult.setNewTalkId(newPtm.getTalkId());

        return changeResult;

    }

    public ProjectChangeResult pbAgreeChangeFirst(UserProjectContext context, String changeItems) throws
            UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        int changeType = computeChangeType(changeItems);//判断大小变更

        Integer newProjectId = createNewProject(pm);

        String fodlerName = pm.getProjectName() + "2";
        //copy Folder
        int newFolderId = copyFolderByProject(context, fodlerName, newProjectId);//copy文件夹
        int newTalkId = createNewTalk(context, newProjectId, newFolderId);

        //创建新洽谈
        UserProjectContext newContext = new UserProjectContext(context);
        newContext.buildModel(newTalkId);
        ProjectTalkModel newPtm = newContext.getProjectTalkModel();

        int newGroupId = createNewWorkGroup(newContext);//创建工作组
        //copyTemplate
        ProjectContext newPc = new ProjectContext(newContext);
        copyTemplateByProject(context, newFolderId, newPc);
        int newWorkplanId = newPc.getWorkplanId();
        pm.getProjectTalkService().updateNewProjectTalk(newTalkId, newGroupId, newWorkplanId);

        List<ProjectTemplateModel> newModelList = newPtm.selectTalkTemplates();
        //添加阶段文档控制(添加甲乙方)
        new ProjectTemplateUserControl().addProjectTemplateUserChngBig(newContext, newModelList);
        // 添加阶段文档控制(添加组员)
        new ProjectTemplateUserControl().addProjectTemplateUserChngBigToPM(context, newModelList);

        //拷贝需求告知书
        new CopyTemplateModelChange().copyTemplate(context ,newContext,newPtm.getFolderId(),changeType);

        new ProjectDAOService().modifyFolder(newProjectId, newFolderId);
        //status
        getInst().getReleaseView().getProjectDAOService().updateProjectInfoInChangeStage(pm
                .getProjectId(), newProjectId, ptm.getTalkId(),changeType);

        ProjectChangeResult changeResult = new ProjectChangeResult();
        changeResult.setNewTalkId(newTalkId);
        changeResult.setNewProjectId(newProjectId);
        changeResult.setOldTalkId(ptm.getTalkId());
        changeResult.setChangeType(changeType);
        changeResult.setPhase(ptm.getPhase());

        computeChange(newContext,context );
        context.refresh();
        //System.out.println(context.getProjectModel());

        YtbLog.logDebug("changeType",changeType);

        return changeResult;
    }

    @Override
    public void gotoPay(UserProjectContext context) {
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更支付！");
        }
        ProjectModel pm = context.getProjectModel();
        if (pm.getNewProjectId() <= 0) {

            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更项目或者不在变更支付状态中！");

        }

    }

    @Override
    public void removeChange(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        if (pm.getNewProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更取消状态中,不能删除变更！");
        }

    }

    @Override
    public void reCopyTemplates(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        if (pm.getNewProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更取消状态中，不能重新拷贝模板！");
        }
        //recopy pb Template req and workplan
    }

    //乙方提交
    public void pbSubmitChange(UserProjectContext oldContext,UserProjectContext newContext) throws
            IOException {

        if (!oldContext.isPb()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "只有乙方才可以提交变更审核！");
        }
        if (!oldContext.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更结项！");
        }

        ProjectModel pm = oldContext.getProjectModel();

        if (pm.getNewProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更项目或者不在编制状态中,无法提交！");

        }
        ProjectTalkModel newPtm = newContext.getProjectTalkModel();

        // 检查有费用数据,人员日薪都不为0
        newPtm.checkExistCost( newContext );

        List<ProjectTemplateModel> templateModels =  getInst().getProjectFileService().getTemplateListByFolder(newPtm.getFolderId());
        for (ProjectTemplateModel newTemplate : templateModels) {
            getInst().getProjectFileService().submitTemplate(newTemplate, 200, pm.getUserId1());
            //修改文档控制表的状态（document_user_status）
            modifyTemplateStatus(pm.getUserId1(), newPtm.getUserId2(), newTemplate.getTemplateId(), 0);

        }

        newPtm.addTalkEvent(newContext, "乙方提交变更文档", 5, newPtm.getUserId2(), pm.getUserId1());

        new ProjectTemplateUserControl().addProjectChangeTemplateUserForPM(newContext);//添加组员文档状态控制

        //修改project_change表 phase_status
        new ChangeDaoServiceImpl().updateProjectChange(newContext.getProjectModel().getProjectId
                (),ProjectConstState.PRO_CHANGESTATUS_PA_AUDIT,null);
    }

    // 甲方变更审核 pm old
    public void paAuditChange(UserProjectContext context, int newTalkId, int status) {
        if (context.isPb()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "只有乙方才可以提交变更审核！");
        }
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中不能变更结项！");
        }
        ProjectModel pm = context.getProjectModel();

        if (pm.getNewProjectId() <= 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, " 不是变更项目或者不在编制状态中,无法取消！");
        }

        ProjectTalkModel ptm = context.getProjectTalkModel();
        UserProjectContext newContext = new UserProjectContext(context, newTalkId);
        ProjectTalkModel newPtm = newContext.getProjectTalkModel();

        PBFolder pbFolder = context.getViewProjectFolderModel().getpBFolder();
        pbFolder.paAuditTalkFolderChange(newContext, status);//修改文档状态控制


        List<ProjectTemplateModel> list = getInst().getProjectFileService().getTemplateListByFolder(newPtm.getFolderId());
        if (status == ProjectConst.FOLDER_STATUS_PASS_PB) {//审核通过
            for (ProjectTemplateModel templateModel : list) {
                getInst().getProjectFileService().submitTemplate(templateModel,
                        ProjectConst.P_TEMPLATE_STAT_PASS_PM, pm.getUserId1());
            }
            ptm.addTalkEvent(newContext, "甲方审核变更文档通过", ProjectConst.TASK_STATUS_Passing_PA, pm
                    .getUserId1(), ptm.getUserId2());//, ProjectConst

           int phaseStatus =  (pm.getChangeType() == ProjectConstState.CHNAGE_TYPE_BIG) ?
                   ProjectConstState.PRO_CHANGESTATUS_CLOSE:ProjectConstState.PRO_CHANGESTATUS_PAYMENT;

            new ChangeDaoServiceImpl().updateProjectChange(newContext.getProjectModel().getProjectId(),phaseStatus,null);

        } else {//审核不通过

            pm.getProjectTalkService().modifyTalkPhaseAndStatus(ptm.getPhase(),
                    701, null,ptm.getTalkId());
            for (ProjectTemplateModel templateModel : list) {
                getInst().getProjectFileService().submitTemplate(templateModel,
                        ProjectConst.P_TEMPLATE_STAT_NOPASS_PM, pm.getUserId1());
            }
            ptm.addTalkEvent(newContext, "甲方审核变更文档不通过", ProjectConst.TASK_STATUS_NotPassing_PA, pm
                    .getUserId1(), ptm.getUserId2());
            new ChangeDaoServiceImpl().updateProjectChange(newContext.getProjectModel().getProjectId
                    (),ProjectConstState.PRO_CHANGESTATUS_PB_MAKE,ProjectConst
                    .FOLDER_STATUS_NOTPASS_PB);
        }

    }


    public int computeChangeReturnType(UserProjectContext newContext, UserProjectContext oldContext){

        super.computeChange(newContext, oldContext);
        return oldContext.getProjectModel().getChangeType();
    }




}
