package ytb.project.service.project.change.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.CostSumRateModelServiceImpl;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.CostSumRateModel;
import ytb.project.model.tagtable.ProjectChngPModel;
import ytb.project.service.impl.pay.payfee.IProjectCostFee;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.project.service.project.change.ChangeFlow;

import java.math.BigDecimal;

/**
 * Package: ytb.project.service.project.change
 * Author: ZCS
 * Date: Created in 2019/4/2 17:27
 */
public final class BigChange extends ChangeFlow {

    //1 原项目名称： 项目名称
    //2.新项目名称:  项目名称
    //3.项目进度：   第p阶段
    //3.费用结算（人民币元）：
    //① 原计划总费用 =    原工作计划总费用x.xx

    //结项费用=②+（③-④负数取0：多退少不补）+⑤+⑥
    //② 已支付工时费=  原工作计划阶段支付工时费合计x.xx
    //⑤ 支付当前阶段工时费=  当前p阶段的工时费x.xx
    //⑥ 支付补偿金（②+⑤）*毛利率= x.xx
    //③ 采购和加工预算=  （p-3）*总预算/3（p≥4/5，p＜4，则为0）
    //④ 已采购和加工发票总额=   x.xx
    //⑥ 支付补偿金（②+⑤）*毛利率= x.xx

    //⑦ 现计划总费用=  现工作计划总费用x.xx
    //（负值表示支付额；正值表示退款额）
    //⑧ 费用差额=①-②-（③-④负数取0：多退少不补）-⑤-⑥-⑦=x.xx
    //⑨ 毛利率 = x.xx
    //查询变更通知书数据
    //已经支付可能要查询account_trade_project
    public int computeChange(UserProjectContext newContext, UserProjectContext oldContext) {
        ProjectModel oldPm = oldContext.getProjectModel();
        ProjectTalkModel oldPtm = oldContext.getProjectTalkModel();

        ProjectChngPModel chngPModel = new ProjectChngPModel(oldContext,newContext);

        IProjectCostFee projectCostFee = new ProjectCostFee();
        //① 原计划总费用
        CostModel costModelTotal = projectCostFee.getProjectCostTotal(oldContext);
        chngPModel.setFeePre(costModelTotal.getCostSum());
        //⑦ 现计划总费用
        chngPModel.setFeeNow(projectCostFee.getProjectCostTotal(newContext).getCostSum());

        //② 已支付工时费=  原工作计划阶段支付工时费合计x.xx
        BigDecimal sumHistry = projectCostFee.sumHistryPhaseCost(oldContext, costModelTotal);
        chngPModel.setFeePayed(sumHistry);
        //⑤ 支付当前阶段工时费=  当前p阶段的工时费x.xx
        chngPModel.setFeePhase(costModelTotal.getCostPhase(oldPtm.getPhase()));

        //⑨ 毛利率
        BigDecimal rateGp = getRateGp(oldContext, newContext);
        chngPModel.setRateGp(rateGp);
        //⑥ 支付补偿金（②+⑤）*毛利率= x.xx
        chngPModel.setFeeMakeup(chngPModel.getFeePayed().add(chngPModel.getFeePhase()).multiply(rateGp));

        //③ 采购和加工预算=  （p-3）*总预算/3（p≥4/5，p＜4，则为0）
        int p = oldPtm.getPhase() - oldPm.getPhaseStart();
        p = p <= 3 ? 0 : p - 3;
        chngPModel.setFeePp(chngPModel.getFeePre().multiply(BigDecimal.valueOf(p)).divide(BigDecimal.valueOf(3)));
        //④ 已采购和加工发票总额 = x.xx
        chngPModel.setFeeInvoice(BigDecimal.ZERO);
        BigDecimal pp = chngPModel.getFeePp().subtract(chngPModel.getFeeInvoice());
        pp = pp.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : pp;
        //（负值表示支付额；正值表示退款额）
        //⑧ 费用差额=①-②-（③-④负数取0：多退少不补）-⑤-⑥-⑦=x.xx
        //减已支付费
        chngPModel.setFeeDiff(chngPModel.getFeePre().subtract(chngPModel.getFeePayed()));
        //退采购加工费
        chngPModel.setFeeDiff(chngPModel.getFeeDiff().subtract(pp));
        //减现阶段支付费
        chngPModel.setFeeDiff(chngPModel.getFeeDiff().subtract(chngPModel.getFeePhase()));
        //减补偿金
        chngPModel.setFeeDiff(chngPModel.getFeeDiff().subtract(chngPModel.getFeeMakeup()));
        //减现计划总费用
        chngPModel.setFeeDiff(chngPModel.getFeeDiff().subtract(chngPModel.getFeeNow()));
        return insertProjectChngPModel(chngPModel);

    }

    BigDecimal getRateGp(UserProjectContext oldContext, UserProjectContext newContext) {

        CostSumRateModel rateModel = getRateGp(newContext);
        if (rateModel == null) {
            rateModel = getRateGp(oldContext);
        }
        return new BigDecimal(rateModel.getRateValue()).setScale(0, BigDecimal.ROUND_HALF_UP);

    }

    CostSumRateModel getRateGp(UserProjectContext context) {
        ProjectModel newPm = context.getProjectModel();
        ProjectTalkModel newPtm = context.getProjectTalkModel();

        CostSumRateModelServiceImpl sumRateModelService = new CostSumRateModelServiceImpl();
        CostSumRateModel rateModel = new CostSumRateModel();
        rateModel.setProjectId(newPm.getProjectId());
        rateModel.setDocumentId(newPtm.getWorkplanId());
        rateModel = sumRateModelService.selectOne(rateModel);
        return rateModel;
    }

}
