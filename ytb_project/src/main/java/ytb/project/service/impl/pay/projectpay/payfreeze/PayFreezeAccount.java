package ytb.project.service.impl.pay.projectpay.payfreeze;


//项目冻结款历史查询
//pa pb分开
//冻结帐户

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.IVProjectTradeAccountModelService;
import ytb.project.dao.tagtable.CostMapper;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.daoservice.CostModelServiceImpl;
import ytb.project.daoservice.VProjectTradeAccountModelServiceImpl;
import ytb.project.model.*;
import ytb.project.model.tagtable.CostModel;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.*;

////计算人员阶段比例
//    Map<Integer, Double> computePhaseCostRate(UserProjectContext context, int phase);
//   计算人员阶段比例
//   Map<Integer, Double> computeSumCostRate(UserProjectContext context);

//计算已经发生的支付，解冻
public class PayFreezeAccount extends VProjectTradeAccountModelServiceImpl {
    protected CostModelServiceImpl costModelService = new CostModelServiceImpl();

    protected BigDecimal newBigDecimal(float v) {
        return new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    protected BigDecimal newBigDecimal(double v) {
        return new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    protected BigDecimal newBigDecimal(int v) {
        return new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    protected BigDecimal newBigDecimal(long v) {
        return new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    public List<CostModel> getProjectCostByTalk(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        return costModelService.getProjectCostByTalk(ptm.getTalkId(), ptm.getWorkplanId());
    }

    public Integer getTradeIdByTalk(int talkId, int userId) {
        ProjectTradeModel m = getInst().getProjectTradeModelService().selectOne(TradeConst.SERVICE_TYPE_project, talkId, userId);

        return m.getTradeId();
    }


    //通过洽谈id查询费用
    public List<CostModel> getProjectCostByTalk(int talkId, int documentId) {

        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            CostMapper costMapper = session.getMapper(CostMapper.class);
            return costMapper.getProjectCostByTalk(talkId, documentId);
        }
    }

    //	select * from cost where project_id = #{projectId} and user_id = #{userId} and document_id = #{documentId}
    public List<CostModel> getProjectFeeByTalk(int projectId, int userId, int documentId) {

        try(SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true)){
            CostMapper costMapper = session.getMapper(CostMapper.class);
            return costMapper.getProjectFeeByTalkId(projectId,userId,documentId);
        }
    }

    //计算人员阶段比例
    public Map<Integer, Double> computePhaseCostRate(UserProjectContext context, int phase) {

        List<CostModel> costModels = getProjectCostByTalk(context);

        return computeSumCostRate(costModels, phase);
    }

    //计算人员总费用比例
    public Map<Integer, Double> computeSumCostRate(UserProjectContext context) {

        List<CostModel> costModels = getProjectCostByTalk(context);

        return computeSumCostRate(costModels, 0);
    }

    //phaseOrSum=0 sum, else phase
    protected  Map<Integer, Double> computeSumCostRate(List<CostModel> costModels, int phaseOrSum) {
        Map<Integer, Double> userRateMap = new LinkedHashMap<>();
        Double sum = 0d;
        for (CostModel costModel : costModels) {
            BigDecimal value = phaseOrSum == 0 ? costModel.getCostSum() : costModel.getCostPhase(phaseOrSum);
            Double fvalue = value.doubleValue();
            sum += fvalue;

            if (userRateMap.containsKey(costModel.getUserId())) {
                fvalue += userRateMap.get(costModel.getUserId());
            }
            userRateMap.put(costModel.getUserId(), fvalue);
        }

        for (Integer key : userRateMap.keySet()) {
            BigDecimal v = BigDecimal.valueOf(userRateMap.get(key) / sum);
            userRateMap.put(key, v.doubleValue());
        }

        return userRateMap;
    }

    //计算加一方 或者成员的项目余下冻结款 ， 用以退款 ，或者收款
    //used by stop   flow.
    protected  BigDecimal sumFrozen(List<VProjectTradeAccountModel> accountModels) {

        BigDecimal ret = BigDecimal.valueOf(0);
        for (VProjectTradeAccountModel model : accountModels) {
           ret = sumFrozen(ret,model);
        }

        return ret;
    }
    //used by stop and normal flow.

    protected BigDecimal sumFrozen(BigDecimal sum, VProjectTradeAccountModel accountModel) {

        if (accountModel.checkB2F()) {
            sum = sumB2fFrozen(sum, accountModel);

        } else if (accountModel.checkF2F()) {
            sum = sumF2fFrozen(sum, accountModel);
        }
        return sum;
    }

    // balance sum total when payout, sum balance when income

    //frozen sum total
    BigDecimal sumB2fFrozen(BigDecimal ret, VProjectTradeAccountModel accountModel ) {
        if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_INCOME)) {
            ret = ret.subtract(accountModel.getTotalBalance());

        } else if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_INCOME_REFUND)) {
            ret = ret.add(accountModel.getTotalBalance());

        } else if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_PAYOUT)) {
            ret = ret.add(accountModel.getTotalBalance());

        } else if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_PAYOUT_REFUND)) {
            ret = ret.subtract(accountModel.getTotalBalance());

        }

        return ret;
    }

    BigDecimal sumF2fFrozen(BigDecimal ret, VProjectTradeAccountModel accountModel ) {
        if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_INCOME)) {
            ret = ret.add(accountModel.getTotalBalance());

        } else if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_INCOME_REFUND)) {
            ret = ret.subtract(accountModel.getTotalBalance());

        } else if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_PAYOUT)) {
            ret = ret.subtract(accountModel.getTotalBalance());

        } else if (accountModel.getTradeType().equals(TradeConst.TRADE_TYPE_PAYOUT_REFUND)) {
            ret = ret.add(accountModel.getTotalBalance());

        }

        return ret;
    }


    //甲方 终止变更：甲方多余的冻结款要退回
    public Map<Integer, CostModel> sumPaUnfreeze(UserProjectContext context) {
        return sumUnfreeze(context,false);
    }
    //计算解冻金额
    //tradeType-subType-serviceType
    //30--102 project
    //31--102 project
    //30--102 project_cnge / stop : repay
    //31--102                     :  penalty
    //乙方 终止变更完成，冻结款要解款或退回: 重大变更解冻原项目，新项目，分开来解的
    public Map<Integer, CostModel> sumPbUnfreeze(UserProjectContext context) {
        return sumUnfreeze(context ,true);
    }

    public Map<Integer, CostModel> sumUnfreeze(UserProjectContext context,boolean isPb) {

        //只查询一个项目的trade
        List<VProjectTradeAccountModel> accountModels = computeUnfreezeTalk(context,isPb);
        ProjectModel pm = context.getProjectModel();
        //查询变更支付的冻结款
        if (pm.isChange()) {
            List<VProjectTradeAccountModel> changeModels = computeUnfreezeChange(context,isPb);
            accountModels.addAll(changeModels);
        }
        //查询多个项目的trade,如果是大变更要解冻原项目
        if (accountModels.size() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "冻结记录");
        }
        return sumPbUnfreeze(context,isPb , accountModels);
    }

    //当前项目
    List<VProjectTradeAccountModel> computeUnfreezeTalk(UserProjectContext context,boolean isPb) {

        ProjectModel pm = context.getProjectModel();
        //IVProjectTradeAccountModelService service = new VProjectTradeAccountModelServiceImpl();
        return isPb ? selectListPb(pm.getProjectId(), pm.getUserId1()) :
                      selectListPa(pm.getProjectId(), pm.getUserId1());
    }

    //变更新项目
    List<VProjectTradeAccountModel> computeUnfreezeChange(UserProjectContext context, boolean isPb) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        IVProjectTradeAccountModelService service = new VProjectTradeAccountModelServiceImpl();
        List<VProjectTradeAccountModel> models = new ArrayList<>();
        List<ProjectChangeModel> changeModels = new ChangeDaoServiceImpl().getChangeModels(ptm.getTalkId());
        for (ProjectChangeModel changeModel : changeModels) {

            List<VProjectTradeAccountModel> changeAccountModels = isPb ?
                    service.selectListPb(changeModel.getNewProjectId(), pm.getUserId1()) :
                    service.selectListPa(changeModel.getNewProjectId(), pm.getUserId1());
            models.addAll(changeAccountModels);
        }

        return models;
    }

    //isCompanyUser 不包括甲方 used
    Map<Integer, CostModel> sumPbUnfreeze(UserProjectContext context,boolean isPb, List<VProjectTradeAccountModel> models  ) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        Map<Integer, CostModel> modelMap = new HashMap<>();
        if (isPb) {
            //公司用户支付给乙方负责人
            if (context.pbIsCompanyUser()) {
                modelMap.put(ptm.getUserId2(), new CostModel());
                modelMap.get(ptm.getUserId2()).setCostSum(newBigDecimal(0));
            } else {
                for (VProjectTradeAccountModel model : models) {
                    if (!modelMap.containsKey(model.getUserId())) {
                        modelMap.put(model.getUserId(), new CostModel());
                        modelMap.get(model.getUserId()).setCostSum(newBigDecimal(0));
                    }
                }
            }
        } else {
            modelMap.put(pm.getUserId1(), new CostModel());
            modelMap.get(pm.getUserId1()).setCostSum(newBigDecimal(0));

        }

        for (VProjectTradeAccountModel model : models) {
            CostModel costModel = context.pbIsCompanyUser() ? modelMap.get(ptm.getUserId2()) : modelMap.get(model.getUserId());
            if (!isPb) {
                costModel = modelMap.get(pm.getUserId1());
            }
            //收入冻结款
//            if (model.getTradeType().equals(TradeConst.TRADE_TYPE_INCOME)) {
//                costModel.setCostSum(costModel.getCostSum().add(model.getBalance()));
//            } else if (model.getTradeType().equals(TradeConst.TRADE_TYPE_INCOME_REFUND)) {
//                costModel.setCostSum(costModel.getCostSum().subtract(model.getBalance()));
//            }
            BigDecimal costSum = sumFrozen(costModel.getCostSum(), model);
            costModel.setCostSum(costSum);

        }

        return modelMap;
    }

}
