package ytb.project.service.impl.pay.projectpay;

import ytb.project.context.ProjectContext;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.UserAssistModel;
import ytb.project.service.impl.pay.payevent.PayEventService;
import ytb.project.service.impl.pay.payevent.PayTradeService;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.project.service.impl.pay.payevent.PayNotifyService;
import ytb.project.service.impl.pay.projectpay.payfreeze.PayFreezeAccount;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.service.sq.business.user.ProjecTransactionService;
import ytb.account.wallet.service.sq.business.user.impl.ProjecTransactionServiceImpl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;


public class ProjectPay extends PayFreezeAccount {
    protected Byte serviceType = TradeConst.SERVICE_TYPE_project;

    protected ProjectCostFee projectCostFee = new ProjectCostFee();
    protected PayTradeService payTradeService ;
    protected PayEventService payEventService = new PayEventService();
    protected PayNotifyService payNotifyService = new PayNotifyService();

    protected static ProjecTransactionService transactionService = new ProjecTransactionServiceImpl();

    public static class CheckPayAccount {

        public final static Byte BALANCE_TYPE_BALANCE = 0;
        public final static Byte BALANCE_TYPE_PAYOUT = 1;
        public final static Byte BALANCE_TYPE_INCOME = 2;

        int userId;
        int companyId;
        String password = "";

        Byte balanceType = BALANCE_TYPE_BALANCE;
        BigDecimal payFee = BigDecimal.ZERO;

        // 检查支付密码，和检查余额足够接口
        public void checkAccountPassword() {
            //支付密码
            transactionService.checkAccountPassword(getUserId(), getCompanyId(), getPassword());
        }


        // 检查余额足够接口,指扣除时
        public boolean checkAccountEnouph() {

            AccountUserInner userInner = transactionService.getAccountInfo(userId, companyId);
            return userInner.getBalance().compareTo(payFee) >= 0;
        }


        public Byte getBalanceType() {
            return balanceType;
        }

        public void setBalanceType(Byte balanceType) {
            this.balanceType = balanceType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public BigDecimal getPayFee() {
            return payFee;
        }

        public void setPayFee(BigDecimal payFee) {
            this.payFee = payFee;
        }
    }

    public ProjectCostFee getProjectCostFee() {
        return projectCostFee;
    }

    // 检查支付密码，和检查余额足够接口
    public void checkAccountPassword(UserProjectContext context, String password) {
        CheckPayAccount payAccount=new CheckPayAccount();
        payAccount.setPassword(password);
        payAccount.setUserId(context.getUserId());
        payAccount.setCompanyId(context.getCompanyId());
        payAccount.checkAccountPassword();
    }

    public boolean checkAccountEnouph(UserProjectContext context, List<UserAssistModel> models) {
        int sum = 0;
        for(UserAssistModel model:models){
            sum += model.getMoney();
        }
        CheckPayAccount payAccount = new CheckPayAccount();
        payAccount.setPayFee( BigDecimal.valueOf(sum) );
        payAccount.setUserId(context.getUserId());
        payAccount.setCompanyId(context.getCompanyId());

        return payAccount.checkAccountEnouph();

    }

    // 检查余额足够接口,指扣除时
    public boolean checkAccountEnouph(UserProjectContext context,  BigDecimal payFee) {
        CheckPayAccount payAccount=new CheckPayAccount();
        payAccount.setPayFee(payFee);
        payAccount.setUserId(context.getUserId());
        payAccount.setCompanyId(context.getCompanyId());

        return payAccount.checkAccountEnouph();

    }

    public PayTradeService getPayTradeService() {
        if (payTradeService == null) {
            payTradeService = new PayTradeService(this);
        }
        return payTradeService;
    }

    public void setProjectCostFee(ProjectCostFee projectCostFee) {
        this.projectCostFee = projectCostFee;
    }


    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }


    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }


//    void payAssistBase(UserProjectContext context, TransferInfo info) {
//
//        int tradeId = new ProjecTransactionServiceImpl().b2bPayTransfer(info);
//        if (tradeId == 0) {
//            throw new YtbError(YtbError.PHASE_PAY_FAILD);
//        }
//        Notify projectNotify = new Notify();
//        JSONObject jsonObject = new JSONObject();
//        String noteType = ProjectConstState.PROJECT_Assist_Notice;
//        if(info.getServiceType().equals(TradeConst.SERVICE_TYPE_penalty)){
//            noteType = ProjectConstState.PROJECT_Penalty_Notice;
//        }
//        if(info.getServiceType().equals(TradeConst.SERVICE_TYPE_thank)){
//            noteType = ProjectConstState.PROJECT_Talk_Notice;
//        }
//
//        projectNotify.sendMsg(info.getToUserId(), info.getUserId(), noteType, "", jsonObject, 1);
//
//    }

    protected ProjectContext buildPhaseStartFolder(UserProjectContext newContext) throws UnsupportedEncodingException {
        ProjectContext pc = new ProjectContext(newContext);
        //确认支付进入首阶段copy return folderID
        Dict_ProjectTypeModel dict_ptm = newContext.getDict_ProjectTypeModel();
        if (dict_ptm.isPurchaseProcessing()) {
            int folderid = newContext.getViewProjectFolderModel().getpBFolder().copyFolderPB_phaseStartPp(newContext);
            pc.setFolderId(folderid);
        } else {
            newContext.getViewProjectFolderModel().getpBFolder().copyFolder_phaseStart
                    (newContext, pc, newContext.getProjectModel().getPhaseStart());

        }
        return pc;
    }

    //只有大变更才会加目录
//        if( oldContext.isChangeBig() ) {
//            //修改洽谈表支付时间和phase
//            pm.getProjectTalkService().modifyTalkPhasePayDate(newContext, newContext.getProjectModel().getPhaseStart(), 0);
//
//            //确认支付进入首阶段copy return folderID
//            if (dict_ptm.isPurchaseProcessing()) {
//                int folderid = newContext.getViewProjectFolderModel().getpBFolder().copyFolderPB_phaseStartPp(newContext);
//                payResult.setFolderId(folderid);
//            } else {
//                ProjectContext pc = new ProjectContext(newContext);
//                newContext.getViewProjectFolderModel().getpBFolder().copyFolder_phaseStart
//                        (newContext, pc, newContext.getProjectModel().getPhaseStart());
//                payResult.setFolderId(pc.getFolderId());
//            }
//            pm.getProjectTalkService().modifyTalkPhaseAndStatus(newContext, pm.getPhaseStart());
//
//        }
}
