package ytb.project.service.project.stop.impl;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.CostModelServiceImpl;
import ytb.project.daoservice.DAOService;
import ytb.project.daoservice.ProjectStopPModelServiceImpl;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.ProjectStopPModel;
import ytb.project.service.impl.pay.payfee.IProjectCostFee;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.T_Stop_ActionModel;

import java.math.BigDecimal;

import static java.lang.Math.abs;

public abstract class ComputeStopBase extends DAOService implements IStopCause, IComputeStop {
    IProjectCostFee projectCostFee = new ProjectCostFee();
    CostModelServiceImpl costModelService = new CostModelServiceImpl();
    protected void buildCostCommon(UserProjectContext context,StopType stopType){
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ComputeStopResult stopResult = stopType.getComputeResult();
        CostModel costModelTotal = projectCostFee.getProjectCostTotal(context);
        BigDecimal phaseCost = projectCostFee.getPhaseCost(costModelTotal,ptm.getPhase());
        //总费用
        stopResult.setCostTotal(costModelTotal.getCostSum());
        //已付费用
        stopResult.setPayedPhaseCost(projectCostFee.sumHistryPhaseCost(context,costModelTotal));
        //未付费用
        stopResult.setUnpayPhaseCost(projectCostFee.sumUnpayPhaseCost(context,costModelTotal));
        //当期费用
        stopResult.setPhaseNowCost(phaseCost);
        //set q
        stopResult.setQ(computeQ(context,stopType));

    }

    T_Stop_ActionModel findT_Stop_Action(UserProjectContext context, StopType stopType) {
        Dict_TemplateModel templateModel = getStopTemplateDict(context, stopType);
        return UserProjectContext.getInst().getProjectCacheManager().findT_Stop_Action(
                templateModel.getTemplateId(), stopType.getPhaseNow());
    }

    public boolean canStop(UserProjectContext context, StopType stopType) {

        return findT_Stop_Action(context,stopType).getStop();

    }

    @Override
    public Dict_TemplateModel getStopTemplateDict(UserProjectContext context, StopType stopType) {
        return getInst().getProjectCacheManager().findStopTemplateModel(stopType);
    }

    //原因和阶段
    //变更原项目阶段钱还冻结 litterchange
    //1.项目名称：  项目名称
    //2.项目进度：  第p阶段
    //3.费用结算（人民币元）：
    //① 原计划总费用 =  原工作计划总费用x.xx
    //② 现计划总费用 =  现工作计划总费用x.xx
    //③ 费用差额(①-②) =  x.xx
    public int computeStopPTable(UserProjectContext context, StopType stopType) {


        ProjectStopPModel stopPModel = new ProjectStopPModel( context );
        return new ProjectStopPModelServiceImpl().insert(stopPModel);

    }

    //原因和阶段
    @Override
    public int compute(UserProjectContext context, StopType stopType) {
        if(stopType.getSubType().equals(StopType.SUBTYPE_PA_CANCEL)){
            paCancel(context,stopType);
            return  0;
        }
        if(stopType.getSubType().equals(StopType.SUBTYPE_PB_OFFGRADE)){
            paOffgrade(context,stopType);
            return  0;
        }
        if(stopType.getSubType().equals(StopType.SUBTYPE_DELAY_500)){
            paDelay500(context,stopType);
            return  0;
        }
        if(stopType.getSubType().equals(StopType.SUBTYPE_PB_Unsatisfy)){
            pbUnsatisfy(context,stopType);
            return  0;
        }

        stopType.getComputeResult().setPenalty(computePenalty(context, stopType));
        stopType.getComputeResult().setQ(computeQ(context, stopType));
        stopType.getComputeResult().setDelayRate(computeDelayRate(context));
        stopType.getComputeResult().setQ(BigDecimal.valueOf(0.5));
        stopType.getComputeResult().setPayFee(BigDecimal.valueOf(10));
        return 0;
    }

    @Override
    public long computePenalty(UserProjectContext context, StopType stopType) {
        int prate = findT_Stop_Action(context,stopType).getPenaltyRate();
        return prate*1000/100;
    }

    //ptm.selectStopTemplate();
    @Override
    public BigDecimal computeQ(UserProjectContext context, StopType stopType) {
        return findT_Stop_Action(context,stopType).getStopQ();

    }

    //原因和阶段
    public long computeDelayRate(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        //获取 结束时间延期率(t)定义：t=(实际完成工期D-计划工期D) / 计划工期(D) (扣除甲方审核时间)
        long planDays = YtbUtils.dateDiffDays(ptm.getPlanEndTime(context), ptm.getPlanStartTime());
        long realDays = YtbUtils.dateDiffDays(ptm.getProjectEndTime(), ptm.getProjectStartTime());
        if(realDays==0){
            return 0;
        }
        return abs((realDays - planDays) * 100 / realDays);
    }

}
