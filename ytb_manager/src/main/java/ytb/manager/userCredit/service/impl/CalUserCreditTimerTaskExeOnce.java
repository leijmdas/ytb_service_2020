package ytb.manager.userCredit.service.impl;

import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.common.RestMessage.MsgRequest;

public class CalUserCreditTimerTaskExeOnce implements ITimerTaskService {
    @Override
    public void execute(MsgRequest req, TaskLog_TimerModel task) {

        System.out.println("CalUserCreditTimerTaskExeOnce ...");
        Integer row = new CalUserCredit().calTotalUserQ();
        System.out.println("CalUserCreditTimerTaskExeOnce user number is " + row);

    }
}
