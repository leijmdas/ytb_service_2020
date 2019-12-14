package ytb.project.service.impl.pay.payevent;

import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.service.project.Notify;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;

import java.util.List;

public final class PayNotifyService extends Notify {

    // 支付通知
    public void payNotify(UserProjectContext context, String msgType, ProjectBalanceProjectAgent pbAgent) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("params1", pm.getProjectName());
        jsonObject.fluentPut("params2", ptm.getPhase());//- 600
        jsonObject.fluentPut("params3", pbAgent.getAccountTrade().getBalance());
        sendNotifyPa(context, 0, 2, msgType, jsonObject);
        payNotifyPm(context, msgType, pbAgent.getToAccountTrades());
//        for (AccountTradeProject trade : pbAgent.getToAccountTrades()) {
//
//            JSONObject jsonObject1 = new JSONObject();
//            jsonObject1.fluentPut("params1", pm.getProjectName());
//            jsonObject1.fluentPut("params2", ptm.getPhase() - 600);
//            jsonObject1.fluentPut("params3", trade.getBalance());
//            sendMsg(trade.getUserId(), 0, ProjectConstState.PROJECT_PREPAY_PB_Notify, "",
//                    jsonObject1, 2);
////            addPayEvent(context, "乙方收到预付款:" + trade.getBalance() + "元", ProjectConst.TASK_STATUS_Passing,
////                    trade.getUserId(), pm.getUserId1(), trade.getBalance());
//            //   addPayEventPmPhase(context,trade.getUserId(),trade.getBalance());
//        }
//        //addPayEventPmPhase(context,pbAgent);
//        //addPayEventPaPhase(context,pbAgent.getAccountTrade().getBalance());
//        //addPayEventPmPhase(context,pbAgent.getToAccountTrades());

    }

    void payNotifyPm(UserProjectContext context, String msgType, List<AccountTradeProject> tradeProjects) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        for (AccountTradeProject trade : tradeProjects) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.fluentPut("params1", pm.getProjectName());
            jsonObject.fluentPut("params2", ptm.getPhase() - 600);
            jsonObject.fluentPut("params3", trade.getBalance());
            sendNotify(0, trade.getUserId(), 2,ProjectConstState.PROJECT_PREPAY_PB_Notify, "",jsonObject);
//            addPayEvent(context, "乙方收到预付款:" + trade.getBalance() + "元", ProjectConst.TASK_STATUS_Passing,
//                    trade.getUserId(), pm.getUserId1(), trade.getBalance());
            //   addPayEventPmPhase(context,trade.getUserId(),trade.getBalance());
        }
        //addPayEventPmPhase(context,pbAgent);
        //addPayEventPaPhase(context,pbAgent.getAccountTrade().getBalance());
        //addPayEventPmPhase(context,pbAgent.getToAccountTrades());

    }
}
