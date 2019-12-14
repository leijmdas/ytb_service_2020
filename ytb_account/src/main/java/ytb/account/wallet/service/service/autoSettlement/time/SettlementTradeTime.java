package ytb.account.wallet.service.service.autoSettlement.time;


import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.account.wallet.service.service.autoSettlement.SettlementTrade;
import ytb.common.RestMessage.MsgRequest;

public class SettlementTradeTime implements ITimerTaskService {

    //定时任务
    @Override
    public void execute(MsgRequest req, TaskLog_TimerModel task) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        SettlementTrade settlementTrade = new SettlementTrade();
        settlementTrade.UserSettlementTrade();
    }
}
