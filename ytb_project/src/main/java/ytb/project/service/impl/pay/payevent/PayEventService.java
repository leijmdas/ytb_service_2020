package ytb.project.service.impl.pay.payevent;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectEventModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;

import java.math.BigDecimal;
import java.util.List;

public final class PayEventService {

    public ProjectEventModel addEvent(UserProjectContext context, PayEventModel payEvent) {
        return context.getProjectTalkModel().addEvent(context, payEvent);
    }

    public ProjectEventModel addPayEvent(UserProjectContext context, PayEventModel payEvent) {
        return context.getProjectTalkModel().addPayEvent(context, payEvent);
    }

    // change open 启动项目
    public void addPayEventPaChangeOpen(UserProjectContext context, BigDecimal payFee, boolean isSuccess) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());

        payEvent.setEvenType(isSuccess?ProjectConst.TASK_STATUS_Passing:ProjectConst.TASK_STATUS_NO_RIGHT);
        payEvent.setPayFee(payFee);
        payEvent.setPa(pm.getUserId1());
        payEvent.setPb(ptm.getUserId2());

        payEvent.setRemark(isSuccess ? "变更甲方增加预付款!" : "变更甲方增加预付款失败!");

        addPayEvent(context, payEvent);
        payEvent.setPb(pm.getUserId1());
        payEvent.setPa(ptm.getUserId2());
        addPayEvent(context, payEvent);

    }

    // change open 启动项目
    public void addPayEventPbChangeRefund(UserProjectContext context, BigDecimal payFee, boolean isSuccess) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());

        payEvent.setEvenType(isSuccess?ProjectConst.TASK_STATUS_Passing:ProjectConst.TASK_STATUS_NO_RIGHT);
        payEvent.setPayFee(payFee);
        payEvent.setPa(ptm.getUserId2());
        payEvent.setPb(pm.getUserId1());

        payEvent.setRemark(isSuccess ? "变更乙方退还冻结款!" : "变更乙方退还冻结款失败!");

        addPayEvent(context, payEvent);
        payEvent.setPa(pm.getUserId1());
        payEvent.setPb(ptm.getUserId2());
        addPayEvent(context, payEvent);

    }



    // payEventService.addPayEvent(newContext, "甲方预付款失败", ProjectConst.TASK_STATUS_NO_RIGHT,
    // pm.getUserId1(), ptm.getUserId2(), payResult.getTotalBalance());
    // open 启动项目
    public void addPayEventPaOpen(UserProjectContext context, BigDecimal payFee, boolean isSuccess) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());

        payEvent.setEvenType(isSuccess?ProjectConst.TASK_STATUS_Passing:ProjectConst.TASK_STATUS_NO_RIGHT);
        payEvent.setPayFee(payFee);
        payEvent.setPa(pm.getUserId1());
        payEvent.setPb(ptm.getUserId2());

        payEvent.setRemark(isSuccess ? "甲方预付款成功!" : "甲方预付款失败!");

        addPayEvent(context, payEvent);
        payEvent.setPb(pm.getUserId1());
        payEvent.setPa(ptm.getUserId2());
        addPayEvent(context, payEvent);

    }


    //addPayEvent(context, "甲方支付", 6, pm.getUserId1(),
    //    ptm.getUserId2(), pbAgent.getAccountTrade().getBalance());
    //phase甲方阶段付
    public void addPayEventPaPhase(UserProjectContext context, BigDecimal payFee) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayEventModel payEvent = new PayEventModel();

        payEvent.setChangeType(pm.getChangeType());
        payEvent.setEvenType(ProjectConst.TASK_STATUS_Passing);
        payEvent.setPayFee(payFee);
        payEvent.setPa(pm.getUserId1());
        payEvent.setPb(ptm.getUserId2());
        payEvent.setRemark("甲方阶段支付");//甲方支付 甲方阶段支付
        addPayEvent(context, payEvent);
        //payEvent.setPb(pm.getUserId1());
        //payEvent.setPa(ptm.getUserId2());
        //addPayEvent(context, payEvent);
    }

    public void addPayEventPaPmPhase(UserProjectContext context, ProjectBalanceProjectAgent pbAgent) {
        addPayEventPaPhase(context,pbAgent.getAccountTrade().getBalance());
        addPayEventPmPhase(context,pbAgent.getToAccountTrades());

    }

    protected void addPayEventPmPhase(UserProjectContext context,List<AccountTradeProject> trades) {

        for (AccountTradeProject trade : trades) {

            addPayEventPmPhase(context, trade.getUserId(), trade.getBalance());
        }
    }

    //addPayEvent(context, "乙方收到预付款:" + trade.getBalance() + "元", ProjectConst.TASK_STATUS_Passing,
    //trade.getUserId(), pm.getUserId1(), trade.getBalance());
    protected void addPayEventPmPhase(UserProjectContext context,int pmUserId, BigDecimal payFee) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());
        payEvent.setEvenType(ProjectConst.TASK_STATUS_Passing);
        payEvent.setPayFee(payFee);
        payEvent.setPa(pmUserId);
        payEvent.setPb(pm.getUserId1());
        payEvent.setRemark("乙方收到预付款");//甲方支付 甲方阶段支付
        addPayEvent(context, payEvent);
    }

    //乙方退款
    public void addPayEventPbPhase(UserProjectContext context, BigDecimal payFee) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());
        payEvent.setEvenType(ProjectConst.TASK_STATUS_Passing);
        payEvent.setPayFee(payFee);
        payEvent.setPa(ptm.getUserId2());
        payEvent.setPb(pm.getUserId1());
        payEvent.setRemark("乙方阶段退款");
        addPayEvent(context, payEvent);
        payEvent.setPa(pm.getUserId1());
        payEvent.setPb(ptm.getUserId2());
        addPayEvent(context, payEvent);
    }

    //close
    public void addPayEventPaFinish(UserProjectContext context, BigDecimal payFee) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());
        payEvent.setEvenType(ProjectConst.TASK_STATUS_Passing);
        payEvent.setPayFee(payFee);
        payEvent.setPa(pm.getUserId1());
        payEvent.setPb(ptm.getUserId2());
        payEvent.setRemark("甲方冻结款解冻");
        addPayEvent(context, payEvent);
    }

    //项目结束付
    public void addPayEventPbFinish(UserProjectContext context, BigDecimal payFee) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(pm.getChangeType());
        payEvent.setEvenType(ProjectConst.TASK_STATUS_Passing);
        payEvent.setPayFee(payFee);
        payEvent.setPa(ptm.getUserId2());
        payEvent.setPb(pm.getUserId1());
        payEvent.setRemark("乙方冻结款解冻");
        addPayEvent(context, payEvent);
    }

    public void addPayEventPmFinish(UserProjectContext context,List<AccountTradeProject> trades) {
        for (AccountTradeProject trade : trades) {
            //addPayEvent(context, "乙方解冻款", 6,
            //        tradeProject.getUserId(), 0, tradeProject.getBalance());
            addPayEventPmFinish(context,trade.getUserId(),trade.getBalance());
        }
    }
    //成员收到解冻款
    /* context:项目上下文
     *  pmuserId":成员 ID
     * */
    protected void addPayEventPmFinish(UserProjectContext context,int pmUserId, BigDecimal payFee) {

        PayEventModel payEvent = new PayEventModel();
        payEvent.setChangeType(context.getProjectModel().getChangeType());
        payEvent.setEvenType(ProjectConst.TASK_STATUS_Passing);
        payEvent.setPayFee(payFee);
        payEvent.setPa(pmUserId);
        payEvent.setPb(0);
        payEvent.setRemark("乙方解冻款");
        addPayEvent(context, payEvent);
    }

}
