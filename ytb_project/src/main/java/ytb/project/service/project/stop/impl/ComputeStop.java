package ytb.project.service.project.stop.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.DAOService;
import ytb.project.daoservice.ProjectStopPModelServiceImpl;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.ProjectStopPModel;
import ytb.project.service.project.stop.IStopService;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;


public abstract class ComputeStop extends DAOService implements IStopService {

    ProjectStopPModelServiceImpl projectStopPModelServiceImpl=new ProjectStopPModelServiceImpl();

    ComputeStopBase computeStopBase ;

    ComputeStopBase findComputeStopBase(UserProjectContext context) {
        if (computeStopBase != null) {
            return computeStopBase;
        }
        ProjectModel pm = context.getProjectModel();
        if (pm.getPhaseNo() == 3) {
            computeStopBase = new ComputeStop3Phase();

        }else {
            computeStopBase = new ComputeStop6Phase();
        }
        return computeStopBase;
    }

    public boolean checkCanStop(UserProjectContext context, StopType stopType) {
        return findComputeStopBase(context).canStop(context, stopType);
    }

    //终止计算的入口
    public int computeStopPTable(UserProjectContext context, StopType stopType) {

        //checkCanStop(context,stopType);

        //compute
        compute(context,stopType);

        ProjectTalkModel ptm = context.getProjectTalkModel();
        int stopTemplateDocumentId = ptm.selectStopTemplate().getDocumentId();
        projectStopPModelServiceImpl.delete(stopTemplateDocumentId);
        return addProjectStopPModel(context, stopType, stopTemplateDocumentId);
    }

    @Override
    public void  compute(UserProjectContext context, StopType stopType) {
        if(!checkCanStop(context,stopType)){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"甲方最后一阶段不可以提出终止：取消项目！");
        }
        findComputeStopBase(context).compute(context, stopType);

    }


    //变更原项目阶段钱还冻结 litterchange
    //1.项目名称：  项目名称
    //2.项目进度：  第p阶段
    //3.费用结算（人民币元）：
    //① 原计划总费用 =  原工作计划总费用x.xx
    //② 现计划总费用 =  现工作计划总费用x.xx
    //③ 费用差额(①-②) =  x.xx
    public int addProjectStopPModel(UserProjectContext context, StopType stopType,int stopTemplateDocumentId) {

        ProjectStopPModel stopPModel = new ProjectStopPModel(context);

        stopPModel.setWhy(stopType.getCause());
        stopPModel.setQ(stopType.getComputeResult().getQ());

        ComputeStopResult stopResult=stopType.getComputeResult();
        //⑦ 甲方支付金额=⑤+⑥-（③-④负数取0：多退少不补）=  x.xx
        if(stopType.getComputeResult().getAction().equals(ComputeStopResult.Stop_Pay_Action_PB_REFUND)){
            stopPModel.setFee(stopResult.getPayFee());
        }else{
            stopPModel.setFee(stopResult.getPayFee().multiply(BigDecimal.valueOf(-1)));
        }
        //⑥ 支付违约金=    {①-（②+⑤）}*a（p=1/2/3/4/5时，a分别=0.05/0.1/0.2/0.5/1）
        stopPModel.setCFeePenalty(BigDecimal.valueOf(stopResult.getPenalty()));
        //① 原计划总工时费 = 原工作计划总工时费x.xx
        stopPModel.setCFeePlan(stopResult.getCostTotal());
        //② 已支付工时费=   原工作计划阶段支付工时费合计x.xx
        stopPModel.setCFeePayed(stopResult.payedPhaseCost.toString());

            //③ 采购和加工预算= (p-3) *总预算/3（p≥4/5，p＜4，则为0）
            //④ 已采购和加工发票总额= x.xx


            //支付或者退不会同时有效
            // 乙方退回非造型或界面的工时费
        if(stopResult.getAction().equals(ComputeStopResult.Stop_Pay_Action_PB_REFUND)) {
            stopPModel.setFeeBack20(stopResult.getPhaseNowCost());
        }else{
            //⑤ 支付当前阶段工时费=  当前p阶段的工时费x.xx
            stopPModel.setCFeePhase(stopResult.getPhaseNowCost());
            stopPModel.setFeePay10(stopResult.getPhaseNowCost());
        }

        return projectStopPModelServiceImpl.insert(stopPModel);

    }


    @Override
    public Dict_TemplateModel getStopTemplate(UserProjectContext context, StopType stopType) {
        stopType.checkParam(context, findComputeStopBase(context));
        return findComputeStopBase(context).getStopTemplateDict(context,stopType);
    }



}
