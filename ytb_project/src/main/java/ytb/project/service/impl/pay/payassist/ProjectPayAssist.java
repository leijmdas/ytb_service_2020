package ytb.project.service.impl.pay.payassist;

import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.service.impl.pay.projectpay.ProjectPay;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.project.service.impl.pay.payfee.ProjectRateTaxModel;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//协助，洽谈类支付
public class ProjectPayAssist extends ProjectPay implements IProjectPayAssist {
    public final static int MaxAssistNumber = 5 ;
    public ProjectPayAssist(){
        serviceType = TradeConst.SERVICE_TYPE_assist;
    }

    public boolean isAssist(){
        return getServiceType().equals( TradeConst.SERVICE_TYPE_assist);
    }

    public boolean isGroupMember(UserAssistModel assistModel){
        if(isAssist()) {
            return assistModel.isGroupMember();
        }
        return false;
    }

    List<UserAssistModel> checkAssistMoney(UserProjectContext context,List<UserAssistModel> monies) {
        if (monies.size() == 0 || monies.size() > MaxAssistNumber) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "协助人数不正确！");
        }
        for (UserAssistModel assistModel : monies) {
            if (isGroupMember(assistModel)) {
                if (assistModel.getMoney() > 0) {
                    throw new YtbError(YtbError.CODE_DEFINE_ERROR, "组内协助不付费！");
                }
            } else {
                if (assistModel.getMoney() <= 0) {
                    throw new YtbError(YtbError.CODE_DEFINE_ERROR, "洽谈或组外协助须付费！");
                }
            }
        }
        if(isAssist()) {
            ProjectTalkModel ptm = context.getProjectTalkModel();
            for (UserAssistModel am : monies) {
                if (ptm.isPm(am.getUserId()) || ptm.isPb(am.getUserId())) {
                    throw new YtbError(YtbError.CODE_DEFINE_ERROR, "组内协助不需要支付");
                }
            }
        }
        List<UserAssistModel> result = new ArrayList<>();
        for (UserAssistModel assistModel : monies) {
            if (assistModel.getMoney() > 0) {
                result.add(assistModel);
            }
        }
        return result;
    }


    BigDecimal countMoney(List<UserAssistModel> monies) {
        float money = 0;
        for (UserAssistModel assistModel : monies) {
            if(!isGroupMember(assistModel)) {
                money += assistModel.getMoney();
            }
        }
        return BigDecimal.valueOf(money).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    //预留支付记录
    int saveAssistMoney(int tradeId, List<UserAssistModel> monies) throws UnsupportedEncodingException {
        ProjectTradeModel tradeModel=new ProjectTradeModel();
        tradeModel.setTradeId(tradeId);
        tradeModel.setMemo(YtbUtils.toJSONStringPretty(monies).getBytes("UTF-8"));
        getPayTradeService().tradeDetailModelService.update(tradeModel);
        return tradeId;
    }

    public int addProjectTradePre(UserProjectContext context, int templateId, int tradeId,
                                  List<UserAssistModel> monies) throws UnsupportedEncodingException {
        ProjectTradeModel tradeModel = getPayTradeService().buildProjectTradeModel(context, tradeId, 0);
        tradeModel.setTemplateId(templateId);
        getPayTradeService().addProjectTrade(tradeModel);
        return saveAssistMoney(tradeId, monies);
    }

    //洽谈金支付
    public int payAssistPre(UserProjectContext context, String payPassword,  List<UserAssistModel> monies) throws UnsupportedEncodingException {
        return payAssistPre(context, payPassword, 0, monies);
    }

    //模板协助支付预付
    public int payAssistPre(UserProjectContext context, String payPassword, int templateId,
                            List<UserAssistModel> inMonies) throws UnsupportedEncodingException {

        List<UserAssistModel>  monies = checkAssistMoney(context, inMonies);
        //new ProjectPay().checkAccountPassword(context,payPassword);

        //TransferInfoAssist
        TradeInfo info = ProjectCostFee.buildTradeInfo(context);
        info.setTradeIdPre(0);
        info.setUserId(context.getUserId());
        info.setCompanyId(context.getCompanyId());
        //info.setPassword(payPassword);

        BigDecimal sum=countMoney(monies).setScale(2, BigDecimal.ROUND_HALF_UP) ;
        info.setTotalBalance(sum);
        info.computeBalance();
        //payAssistNewOrder f2b
        int tradeId = transactionService.b2fPaPayProject(info, serviceType);
        info.setTradeIdPre(tradeId);
        addProjectTradePre(context, templateId, tradeId, inMonies);

        //预付通知
        return tradeId;

    }


    public List<UserAssistModel> queryAssistMoneies(UserProjectContext context, int templateId) throws UnsupportedEncodingException {

        ProjectTradeModel model=getPayTradeService().selectOneByTemplate(context.getProjectTalkModel().getTalkId(),templateId);
        return queryAssistMoneies(model.getTradeId());
    }

    public UserAssistModel queryAssistMoney(int tradeId, int pbUserId) throws UnsupportedEncodingException {
        List<UserAssistModel> models = queryAssistMoneies(tradeId);
        for (UserAssistModel model : models) {
            if (model.getUserId() == pbUserId) {
                return model;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
    }

    public List<UserAssistModel> queryAssistMoneies(int tradeId) throws UnsupportedEncodingException {
        List<UserAssistModel> assistMonies = new ArrayList<>();
        List<ProjectTradeModel> models = getPayTradeService().tradeDetailModelService.selectList(tradeId);
        if (models.size() > 0) {
            String info = new String(models.get(0).getMemo(), "UTF-8");
            assistMonies = JSONObject.parseArray(info, UserAssistModel.class);
            for (UserAssistModel model : assistMonies) {
                model.setTradeId(models.get(0).getTradeId());
            }
        }
        return assistMonies;

    }

    //协助支付确认
    public List<AccountTradeProject> payAssistConfrim(UserProjectContext context, int preTradeId,
                                                      List<UserAssistModel> monies) throws UnsupportedEncodingException {
        return payAssistConfrim(context, 0, preTradeId, monies);
    }

    //协助支付确认
    public List<AccountTradeProject> payAssistConfrim(UserProjectContext context, int templateId, int preTradeId,
                                     List<UserAssistModel> inMonies) throws UnsupportedEncodingException {
        List<UserAssistModel> monies = checkAssistMoney(context, inMonies);

        TradeInfo info = ProjectCostFee.buildTradeInfo(context);
        info.setServiceType(serviceType);
        info.setTradeIdPre( preTradeId );
        info.setUserId(context.getUserId());
        info.setCompanyId(context.getCompanyId());
        info.setPassword("");

        List<TradeInfo> infos = new ArrayList<>();
        info.setBalance(countMoney(monies).setScale(2, BigDecimal.ROUND_HALF_UP));
        for (UserAssistModel am : monies) {
            TradeInfo toInfo = new TradeInfo(info);
            toInfo.setUserId(am.getUserId());
            toInfo.setCompanyId(0);

            toInfo.setTotalBalance(newBigDecimal(am.getMoney()));
            toInfo.computeBalance();
            infos.add(toInfo);
        }
        //确认支付
        ProjectBalanceProjectAgent pbAgent = transactionService.f2fTransferPayPa2Pb(info, infos, serviceType);
        //记录支付轨迹
        getPayTradeService().addProjectTradeF2F(context, templateId, preTradeId, pbAgent);
        //支付通知
        payNotifyService.payNotify(context, ProjectConstState.PROJECT_PREPAY_ASSIST_Notify, pbAgent);
        payEventService.addPayEventPaPmPhase(context,pbAgent);
        ProjectRateTaxModel rateTaxModel = context.selectProjectRateTaxModel();
        // 协助支付解冻CLOSE b2f
        for (TradeInfo toInfo : infos) {
            toInfo.computeServiceFee(rateTaxModel,TradeInfo.BALANCE_FLAG_ADD).computeBalance();
        }

        List<AccountTradeProject> trades = transactionService.f2bProjectPbUnfreeze(infos, serviceType);
        getPayTradeService().addProjectTradeF2B(context, templateId, preTradeId, trades);
        return trades;
    }


    //协助支付取消
    public int payAssistCancel(UserProjectContext context, int preTradeId,
                               List<UserAssistModel> inMonies) throws UnsupportedEncodingException {
        List<UserAssistModel> monies = checkAssistMoney(context, inMonies);

        TradeInfo toInfo = ProjectCostFee.buildTradeInfo(context);
        toInfo.setServiceType(serviceType);

        toInfo.setTradeIdPre(preTradeId);
        toInfo.setUserId(context.getUserId());
        toInfo.setCompanyId(context.getCompanyId());
        toInfo.setPassword("");

        toInfo.setTotalBalance(countMoney(monies));
        toInfo.computeServiceFee(context.selectProjectRateTaxModel(),TradeInfo.BALANCE_FLAG_ADD).computeBalance();
        //确认支付取消
        int tradeId = transactionService.paPayoutAgent2Balance(toInfo);
        //记录支付轨迹
        ProjectTradeModel tradeModel = getPayTradeService().buildProjectTradeModel(context, tradeId, preTradeId);
        getPayTradeService().addProjectTrade(tradeModel);
        saveAssistMoney(tradeId, monies);
        //支付通知
        //payNotify(context, ProjectConstState.PROJECT_PREPAY_ASSIST_Notify, pbAgent);


        return tradeId;

    }


}
