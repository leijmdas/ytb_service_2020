package ytb.project.service.impl.pay.payfee;

import org.apache.ibatis.session.SqlSession;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.tagtable.BuyPriceListAllMapper;
import ytb.project.dao.tagtable.CostMapper;
import ytb.project.dao.tagtable.ProcessPriceListAllMapper;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.BuyPriceListAllModel;
import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.ProcessListPriceAllModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.service.impl.pay.projectpay.payfreeze.PayFreezeAccount;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.common.context.service.impl.YtbContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 费用计算基础类,项目费用
public final class ProjectCostFee extends PayFreezeAccount implements IProjectCostFee {


    List<CostModel> paPayBigChange_getCostModel(ProjectTalkModel ptm, int memberUserid) {

        try (SqlSession session = getSession()) {
            CostMapper costMapper = session.getMapper(CostMapper.class);
            return costMapper.getProjectFeeByTalkId(ptm.getProjectId(), memberUserid, ptm.getWorkplanId());

        }
    }


    //获取阶段总费用-tax-service
    public CostModel getPMCostModelSum(List<CostModel> costModelList) {
        CostModel costModelRet = new CostModel();
        costModelRet.setCostSum(newBigDecimal(0));

        for (CostModel costModel : costModelList) {
            costModelRet.setCostSum(costModelRet.getCostSum().add(costModel.getCostSum()));
        }
        return costModelRet;
    }


    public PayResult buildB2fPaPay(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayResult payResult = new PayResult(context);
        CostModel costModel = getProjectCostTotal(context);
        payResult.setDocumentId(ptm.getWorkplanId());

        payResult.setTotalBalance(costModel.getCostSum());
        payResult.setBalance(costModel.getCostSum());
        payResult.setServiceFee(BigDecimal.ZERO);

        return payResult;

    }

    public PayResult buildB2fPaPay(UserProjectContext context, BigDecimal fee) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayResult payResult = new PayResult(context);
        payResult.setDocumentId(ptm.getWorkplanId());
        payResult.setBalance(fee);
        payResult.setTotalBalance(fee);
        payResult.setServiceFee(BigDecimal.ZERO);
        return payResult;

    }

    public static TradeInfo buildTradeInfo(UserProjectContext context) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        TradeInfo tradeInfo = new TradeInfo();

        //乙方参数
        tradeInfo.setProjectId(pm.getProjectId());
        tradeInfo.setTalkId(ptm == null ? 0 : ptm.getTalkId());
        tradeInfo.setPhase(ptm == null ? 0 : ptm.getPhase());

        tradeInfo.setUserId(context.getUserId());
        tradeInfo.setCompanyId(context.getCompanyId());
        return tradeInfo;
    }

    //最后一次结项 PB解冻
    public TradeInfo buildF2BPbPay(UserProjectContext context, int toUserId, int toCompanyId, CostModel costModel) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        int tradeIdPre = UserProjectContext.getInst().getFlowPay().getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());

        TradeInfo tradeInfo = buildTradeInfo(context);
        tradeInfo.setTradeIdPre(tradeIdPre);
        tradeInfo.setUserId(toUserId);
        tradeInfo.setCompanyId(toCompanyId);

        tradeInfo.setTotalBalance(costModel.getCostSum());
        tradeInfo.computeAllFee(context.selectProjectRateTaxModel(), TradeInfo.BALANCE_FLAG_ADD).computeBalance();

        return tradeInfo;
    }
    //终止PA解冻
    public TradeInfo buildB2FPaPay(UserProjectContext context, int toUserId, int toCompanyId, CostModel costModel) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        int tradeIdPre = UserProjectContext.getInst().getFlowPay().getTradeIdByTalk(ptm.getTalkId(), pm.getUserId1());

        TradeInfo tradeInfo = buildTradeInfo(context);
        tradeInfo.setTradeIdPre(tradeIdPre);
        tradeInfo.setUserId(toUserId);
        tradeInfo.setCompanyId(toCompanyId);

        tradeInfo.setTotalBalance(costModel.getCostSum());
        tradeInfo.computeServiceFee(context.selectProjectRateTaxModel(), TradeInfo.BALANCE_FLAG_ADD).computeBalance();

        return tradeInfo;
    }
    public PayResult buildF2fPaPayTotalPp(UserProjectContext context) {
        return buildF2fPaPayTotal(context);

    }

    public PayResult buildF2fPaPayTotal(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        //以乙方名义付款
        BigDecimal totalBalance = newBigDecimal(0);
        for (WorkGroupMemberModel memberModel : ptm.selectWorkGroupMember()) {
            if (memberModel.getUserId() != pm.getUserId1()) {
                List<CostModel> costModel = getProjectFeeByTalk(context, memberModel.getUserId());
                BigDecimal balance = sumPhaseCost(ptm.getPhase(), costModel);
                totalBalance = totalBalance.add(balance);
            }
        }
        PayResult payResult = new PayResult(context);
        payResult.setDocumentId(ptm.getWorkplanId());
        payResult.setTotalBalance(totalBalance);
        payResult.setBalance(new BigDecimal(totalBalance.doubleValue()));
        payResult.setServiceFee(BigDecimal.ZERO);
        payResult.setTax(BigDecimal.ZERO);
        payResult.setFee(BigDecimal.ZERO);

        return payResult;

    }

    public PayResult buildF2fPaPayMember(UserProjectContext context, WorkGroupMemberModel memberModel) {

        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<CostModel> costModel = getProjectFeeByTalk(context, memberModel.getUserId());
        BigDecimal totalBalance = sumPhaseCost(ptm.getPhase(), costModel);

        PayResult payResult = new PayResult(context);
        payResult.setUserId(memberModel.getUserId());
        payResult.setCompanyId(memberModel.getCompanyId());
        payResult.setTotalBalance(totalBalance);
        payResult.setBalance(totalBalance.add(BigDecimal.ZERO));

        return payResult;

    }

    //阶段
    public List<CostModel> getProjectFeeByTalk(UserProjectContext context, int userId) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();

        if (dict_ptm.isPurchaseProcessing()) { //采购加工
            CostModel costModel = getProjectCostTotal(context);
            List<CostModel> costModels = new ArrayList<>();
            costModels.add(costModel);
            return costModels;

        }
        return costModelService.getProjectFeeByTalk(pm.getProjectId(), userId, ptm.getWorkplanId());


    }

    public BigDecimal getProjectCostTotalFee(UserProjectContext context) {
        return getProjectCostTotal(context).getCostSum();
    }

    //获取洽谈支付金额  加工采购total = phase
    public CostModel getProjectCostTotal(UserProjectContext context) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();

        CostModel sumCostModel = new CostModel();
        if (dict_ptm.isPurchase()) { //采购
            BuyPriceListAllModel buyPriceListAll = selectBuyPriceListAll(pm.getProjectId(), ptm.getWorkplanId());
            BigDecimal costSum = buyPriceListAll.getAllMoneyValue();
            sumCostModel.setCostPhase1(costSum);
            sumCostModel.setCostSum(BigDecimal.valueOf(costSum.floatValue()));
        } else if (dict_ptm.isProcessing()) {//加工
            ProcessListPriceAllModel processPriceListAll = selectProcessListPriceAll(pm.getProjectId(), ptm.getWorkplanId());
            BigDecimal costSum = processPriceListAll.getAllMoneyValue();
            sumCostModel.setCostPhase1(costSum);
            sumCostModel.setCostSum(BigDecimal.valueOf(costSum.floatValue()));

        } else {
            List<CostModel> costModels = costModelService.selectList(pm.getProjectId(), ptm.getWorkplanId());
            sumCostModel = sumCostModels(context, costModels);
        }

        return sumCostModel;
    }


    //获取当前阶段费用
    public BigDecimal sumHistryPhaseCost(int phase, CostModel costModel) {

        return costModel.getCostPhase(phase);

    }

    //获取当前阶段费用
    public BigDecimal sumPhaseCost(UserProjectContext context, int phase) {
        List<CostModel> costModels = getProjectCostByTalk(context);
        BigDecimal sum = newBigDecimal(0);
        for (CostModel costModel : costModels) {
            sum = sum.add(costModel.getCostPhase(phase));
        }
        return sum;
    }

    //获取当前阶段费用
    public BigDecimal sumPhaseCost(int phase, List<CostModel> costModels) {
        BigDecimal sum = newBigDecimal(0);
        for (CostModel costModel : costModels) {
            sum = sum.add(costModel.getCostPhase(phase));
        }
        return sum;
    }

    //汇总当前阶段后费用支付
    public BigDecimal sumUnpayPhaseCost(UserProjectContext context, CostModel costModel) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        BigDecimal sum = newBigDecimal(0);
        for (int phase = ptm.getPhase(); phase < pm.getPhaseEnd(); phase++) {
            sum.add(costModel.getCostPhase(phase));
        }
        return sum;

    }

    @Override
    public BigDecimal getPhaseCost(CostModel costModel, int phase) {
        return costModel.getCostPhase(phase);
    }

    //汇总历史阶段支付
    public BigDecimal sumHistryPhaseCost(UserProjectContext context, CostModel costModel) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        BigDecimal sum = newBigDecimal(0);
        for (int phase = pm.getPhaseStart(); phase < ptm.getPhase(); phase++) {
            sum.add(costModel.getCostPhase(phase));
        }
        return sum;

    }

    //获取阶段总费用
    public CostModel sumCostModels(UserProjectContext context, List<CostModel> costModels) {

        ProjectModel pm = context.getProjectModel();
        CostModel sumCostModel = new CostModel();
        sumCostModel.setCostSum(newBigDecimal(0));
        for (CostModel costModel : costModels) {
            sumCostModel.setCostSum(sumCostModel.getCostSum().add(costModel.getCostSum()));
        }

        for (int phase = pm.getPhaseStart(); phase < pm.getPhaseEnd(); phase++) {
            sumCostModel.setCostPhase(phase, sumPhaseCost(phase, costModels));
        }

        return sumCostModel;
    }


    //查询采购费用
    public BuyPriceListAllModel selectBuyPriceListAll(int projectId, int documentId) {

        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            BuyPriceListAllMapper buyPriceListAllMapper = session.getMapper(BuyPriceListAllMapper.class);
            return buyPriceListAllMapper.select(projectId, documentId);
        }
    }

    //查询加工费用
    public ProcessListPriceAllModel selectProcessListPriceAll(int projectId, int documentId) {

        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            ProcessPriceListAllMapper processPriceListAllMapper = session.getMapper(ProcessPriceListAllMapper.class);
            return processPriceListAllMapper.selectProcessListPriceAll(projectId, documentId);
        }
    }

}
