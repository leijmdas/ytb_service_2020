package ytb.project.service.impl.pay.payevent;

import ytb.common.utils.YtbUtils;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.IProjectTradeDetailModelService;
import ytb.project.daoservice.ProjectTradeDetailModelServiceImpl;
import ytb.project.daoservice.ProjectTradeModelServiceImpl;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTradeModel;
import ytb.project.service.impl.pay.PayResult;
import ytb.project.service.impl.pay.projectpay.ProjectPay;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class PayTradeService  extends ProjectTradeModelServiceImpl {
    public IProjectTradeDetailModelService tradeDetailModelService = new ProjectTradeDetailModelServiceImpl();
    protected  Byte serviceType;
    protected ProjectPay projectPay ;
    public   PayTradeService(ProjectPay projectPay){

        this.projectPay=projectPay;
        this.serviceType=projectPay.getServiceType();
    }

    public Integer getTradeIdByTalk(int talkId, int userId) {
        ProjectTradeModel m = getInst().getProjectTradeModelService().selectOne(TradeConst.SERVICE_TYPE_project, talkId, userId);

        return m.getTradeId();
    }

    public ProjectTradeModel selectOneByProjectTalk(  int projectId, int talkId ) {
        return  getInst().getProjectTradeModelService().selectOneByProjectTalk(serviceType ,projectId,talkId);

    }


    public List<ProjectTradeModel> selectListByProject(Byte serviceType, int projectId) {
        return getInst().getProjectTradeModelService().selectListByProject(serviceType, projectId);
    }


    public int addProjectTradePA(UserProjectContext context, Integer tradeId) {
        return addProjectTradePA(context, tradeId, 0);
    }

    public ProjectTradeModel buildProjectTradeModel(UserProjectContext context, Integer tradeId, Integer pid) {

        ProjectTradeModel tradeModel = new ProjectTradeModel(context, tradeId, pid);
        tradeModel.setServiceType(serviceType);
        return tradeModel;
    }


    //预留支付记录
    public  int saveCostModel(int tradeId, PayResult p) throws UnsupportedEncodingException {
        ProjectTradeModel tradeModel=new ProjectTradeModel();
        tradeModel.setTradeId(tradeId);
        tradeModel.setMemo(p.toString().getBytes("UTF-8"));
        tradeDetailModelService.update(tradeModel);
        return tradeId;
    }

    int saveB2FTradeId(int tradeId, List<Integer> trades) throws UnsupportedEncodingException {
        return saveF2FTradeId(tradeId,trades);
    }

    //阶段或者确认支付记录
    int saveF2FTradeId(int tradeId, List<Integer> trades) throws UnsupportedEncodingException {
        ProjectTradeModel model=new ProjectTradeModel();
        model.setTradeId(tradeId);
        model.setMemo(YtbUtils.toJSONStringPretty(trades).getBytes("UTF-8"));
        tradeDetailModelService.update(model);
        return  tradeId;
    }

    int saveF2F(int tradeId, List<AccountTradeProject> trades) throws UnsupportedEncodingException {
        ProjectTradeModel model=new ProjectTradeModel();
        model.setTradeId(tradeId);
        model.setMemo(YtbUtils.toJSONStringPretty(trades).getBytes("UTF-8"));
        tradeDetailModelService.update(model);
        return  tradeId;
    }
    //阶段或者确认支付记录
    int saveB2F(int tradeId, List<AccountTradeProject> trades) throws UnsupportedEncodingException {
        return saveF2F(tradeId,trades);
    }

    //List<AccountTrade>
    public void addProjectTradeCloseB2F(UserProjectContext context, List<AccountTrade> accountTrades) throws UnsupportedEncodingException {
    }

    //甲方冻结款转至乙方冻结款
    public void addProjectTradeF2F(UserProjectContext context, int preTradeId, ProjectBalanceProjectAgent pbAgent) throws UnsupportedEncodingException {
        //非协助templateId传0
        addProjectTradeF2F(context, 0, preTradeId, pbAgent);
    }

    //甲方冻结款转至乙方冻结款 templateId>0协助支付
    public void addProjectTradeF2F(UserProjectContext context, int templateId, int preTradeId,
                                   ProjectBalanceProjectAgent pbAgent) throws UnsupportedEncodingException {
        //甲方
        int paTradeid = pbAgent.getAccountTrade().getTradeId();
        ProjectTradeModel paModel = buildProjectTradeModel(context, paTradeid, preTradeId);
        paModel.setUserId(context.getUserId());
        paModel.setCompanyId(context.getCompanyId());
        paModel.setTemplateId(templateId);
        addProjectTrade(paModel);
        List<Integer> trades=new ArrayList<>();
        //乙方
        for (AccountTradeProject accountTrade : pbAgent.getToAccountTrades()) {
            trades.add(accountTrade.getTradeId());

        }
        saveF2F(paModel.getTradeId(),pbAgent.getToAccountTrades());
    }

    //乙方冻结款转至乙方余额
    public void addProjectTradeF2B(UserProjectContext context, int preTradeId,
                                   List<AccountTradeProject> accountTrades) throws UnsupportedEncodingException {
        addProjectTradeF2B(context,0,preTradeId,accountTrades);
    }

    //乙方冻结款转至乙方余额
    public void addProjectTradeF2B(UserProjectContext context, int templateId,int preTradeId,
                                   List<ytb.account.wallet.model.AccountTradeProject> accountTrades) throws UnsupportedEncodingException {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        List<Integer> trades = new ArrayList<>();
        ytb.account.wallet.model.AccountTradeProject accountTradePb = null;
        //乙方
        for (ytb.account.wallet.model.AccountTradeProject accountTrade : accountTrades) {
            //乙方负责人
            if (accountTrade.getUserId() == ptm.getUserId2()) {
                accountTradePb = accountTrade;
            }
            trades.add(accountTrade.getTradeId());
        }

        if (accountTradePb == null) {
            accountTradePb = accountTrades.get(0);
        }

        ProjectTradeModel paModel = buildProjectTradeModel(context, accountTradePb.getTradeId(), preTradeId);
        paModel.setUserId( ptm.getUserId2());
        paModel.setCompanyId(ptm.getCompanyId2());
        paModel.setTemplateId(templateId);
        addProjectTrade(paModel);
        saveB2F(paModel.getTradeId(), accountTrades);

    }

    /*
     * pid父
     * */
    public int addProjectTradePA(UserProjectContext context, Integer tradeId, Integer pid) {
        if(tradeId==0){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"支付失败");
        }

        return addProjectTrade(buildProjectTradeModel(context,tradeId,pid));
    }


    //添加交易记录
    public int addProjectTrade(ProjectTradeModel tradeModel) {
        if (tradeModel.getCompanyId() == null) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "getCompanyId is null!");
        }
        //System.out.println(tradeModel);
        return getInst().getProjectTradeModelService().insert(tradeModel);

    }

    public ProjectTradeModel selectOne(Byte serviceType, int talkId, int userId) {
        return getInst().getProjectTradeModelService().selectOne(serviceType, talkId, userId);
    }

    public ProjectTradeModel selectOneByTemplate(int talkId, int templateId) {
        return getInst().getProjectTradeModelService().selectOneByTemplate(talkId, templateId);
    }


}
