package ytb.project.service.project.change.impl;

import ytb.project.common.ProjectYtbError;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.DAOService;
import ytb.project.daoservice.ProjectChngPModelServiceImpl;
import ytb.project.model.ProjectModel;
import ytb.project.model.tagtable.ProjectChngPModel;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.common.context.model.YtbError;

public abstract class ComputeChangeType extends DAOService implements IComputeChangeType {
    public void checkBigChange_valid(UserProjectContext oldContext) {
        ProjectModel oldPm = oldContext.getProjectModel();
        if (oldPm.isChangeBig() && oldContext.isLastStage()) {
            throw new ProjectYtbError(ProjectYtbError.CODE_PROJECT_LASTSTAGE_BIGCHANGE);
        }
    }

    public int computeChangeType(String changeItems) {
        int changeType = ProjectConstState.CHNAGE_TYPE_NONE;

        if (changeItems.contains("1")
                || changeItems.contains("2")) {
            changeType = ProjectConstState.CHNAGE_TYPE_SMALL;
        }
        if (changeItems.contains("3")
                || changeItems.contains("4")
                || changeItems.contains("5")) {
            changeType = ProjectConstState.CHNAGE_TYPE_BIG;
        }

        if(changeType == ProjectConstState.CHNAGE_TYPE_NONE){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"请至少选择一项变更项!");
        }

        return changeType;
    }



    public int computeChange(UserProjectContext newContext, UserProjectContext oldContext) {
        if(!oldContext.isChange()){
            return ProjectConstState.CHNAGE_TYPE_NONE;
        }
        if (oldContext.isChangeBig()) {
            return new BigChange().computeChange(newContext, oldContext);
        }
        return computeChangeLittle(oldContext, newContext);
    }


    //查询变更通知书数据
    protected ProjectChngPModel selectOneByDocumentId(UserProjectContext newContext) {

        ProjectChngPModelServiceImpl service = new ProjectChngPModelServiceImpl();
        ProjectChngPModel projectChngPModel = new ProjectChngPModel(newContext,newContext);
        return service.selectOneByDocumentId(projectChngPModel);
    }

    protected int insertProjectChngPModel(ProjectChngPModel projectChngPModel) {

        ProjectChngPModelServiceImpl service = new ProjectChngPModelServiceImpl();
        service.delete(projectChngPModel.getProjectId(),projectChngPModel.getDocumentId());

        return service.insert(projectChngPModel);

    }

    //变更原项目阶段钱还冻结 litterchange
    //1.项目名称：  项目名称
    //2.项目进度：  第p阶段
    //3.费用结算（人民币元）：
    //① 原计划总费用 =  原工作计划总费用x.xx
    //② 现计划总费用 =  现工作计划总费用x.xx
    //③ 费用差额(①-②) =  x.xx
    protected int computeChangeLittle(UserProjectContext oldContext, UserProjectContext newContext) {


        ProjectChngPModel projectChngPModel = new ProjectChngPModel(oldContext,newContext);

        ProjectCostFee projectCostFee = new ProjectCostFee();
        //原计划总费用
        projectChngPModel.setFeePre(projectCostFee.getProjectCostTotal(oldContext).getCostSum());
        //现计划总费用
        projectChngPModel.setFeeNow(projectCostFee.getProjectCostTotal(newContext).getCostSum());
        //费用差额
        projectChngPModel.setFeeDiff(projectChngPModel.getFeePre().subtract(projectChngPModel.getFeeNow()));

        return insertProjectChngPModel(projectChngPModel);

    }


}
