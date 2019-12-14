package ytb.project.service.project.stop.impl;

import ytb.common.context.model.YtbError;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.UserProjectCreditModelServiceImpl;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.TalkFolder;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateModelStop;
import ytb.project.model.tagtable.ProjectStopPModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.project.stop.IStopService;
import ytb.project.service.project.stop.StopApplyResult;
import ytb.manager.template.model.Dict_TemplateModel;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


//集成能力，计算，文件夹项目相关的
public class StopService extends ComputeStop implements IStopService {


    //费用差额 <0甲方支付，>0乙方退款 0-不支付（我处理，前面显示去支付处理）
    public BigDecimal selectPaPbPayFee(UserProjectContext context) {
        ProjectStopPModel stopPModel = selectProjectStopPModel(context);
        return stopPModel.getFee();
    }

    //创建终止文件夹 拷贝终止告知书 stopFolder->stopTemplate ID
    public void createStopFolderAndTemplate(UserProjectContext context,    StopType stopType,
                                            StopApplyResult applyResult) throws UnsupportedEncodingException {

        ProjectTalkModel ptm = context.getProjectTalkModel();

        //创建终止文件夹 判断已经存在不创建！
        ProjectFolderModel folderModel = ptm.fetchStopFolder();
        Integer stopFolderId = folderModel != null ? folderModel.getFolderId() : new TalkFolder().createFolderStop(context);

        if( !ptm.checkExistStopTemplate(stopFolderId) ) {
            //获取源终止告知书
            Dict_TemplateModel dictTemplateModel = getStopTemplate(context, stopType);
            //拷贝终止告知书
            ProjectTemplateModel projectTemplateModel = new CopyTemplateModelStop().copyTemplate(context, stopFolderId,
                    dictTemplateModel);
            //修改文件头
            ProjectContext pc = new ProjectContext(context);
            pc.setDocumentId(projectTemplateModel.getDocumentId());
            pc.modifyHeader(context);

        }

        ProjectTemplateModel stopTemplate =  ptm.selectStopTemplate(stopFolderId);
        applyResult.setStopFolderId(stopFolderId);
        applyResult.setStopTemplateId(stopTemplate.getTemplateId());
        applyResult.setStopDocumentId(stopTemplate.getDocumentId());
    }
    public boolean checkCanStop(UserProjectContext context, StopType stopType) {


        checkCanStop(context);

        if ( !super.checkCanStop(context, stopType)) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "甲方最后一阶段不可以提出终止：原因取消项目！");

        }

        stopType.checkParam(context, findComputeStopBase(context));

        return true;
    }

    public boolean checkCanStop( UserProjectContext context ) {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (!context.isPb() && context.isPm()) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "组员不能发起终止");
        }

        if (ptm == null) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中，不能终止！");
        }

        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行中，不能终止！");
        }
        if(  ptm.checkChangeBig() ){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"项目已经完成大变更，不能终止！");
        }
        if(  ptm.checkStop() ){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"项目已经终止，不能再次终止！");
        }
        if(  ptm.checkFinish() ){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"项目已经完成 ，不能再次终止！");
        }


        return true;
    }


    public List<Integer> payAfter(UserProjectContext context) {
        return addUserProjectCredit(context);
    }

    @Override
    public void noticePartyStopProject(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        //添加终止通知事件


        //暂停双方所有的任务

    }


    //支付后添加信用分记录
    List<Integer> addUserProjectCredit(UserProjectContext context) {
        List<Integer> creditIds = new ArrayList<>();

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        UserProjectCreditModelServiceImpl service = new UserProjectCreditModelServiceImpl();

        ProjectStopPModel stopPModel = selectProjectStopPModel(context);
        UserProjectCreditModel creditModel = new UserProjectCreditModel();
        for (WorkGroupMemberModel memberModel:ptm.selectWorkGroupMember()){
            if(memberModel.getUserId()==pm.getUserId1()){
                continue;
            }
            service.delete(memberModel.getUserId(),pm.getProjectId());
            creditModel.setUserId(memberModel.getUserId());
            creditModel.setName("");
            creditModel.setProjectId(pm.getProjectId());
            creditModel.setProjectName(pm.getProjectName());
            creditModel.setDelayQ(BigDecimal.ZERO);
            creditModel.setFinishQ(BigDecimal.ZERO);
            creditModel.setStopQ(stopPModel.getQ());
            int creditId = service.insert(creditModel);
            creditIds.add(creditId);
        }
        return creditIds;
    }


    public ProjectStopPModel selectProjectStopPModel(UserProjectContext context){
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectTemplateModel templateModel = ptm.selectStopTemplate();
        return  projectStopPModelServiceImpl.selectOne( templateModel.getDocumentId());

    }


}
