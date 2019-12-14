package ytb.log.notify.service.impl;

import ytb.common.basic.safecontext.service.SafeContext;
import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.common.RestMessage.MsgRequest;

public class TimerTaskServiceClearSesson implements ITimerTaskService {
    @Override
    public void execute(MsgRequest req, TaskLog_TimerModel task) {
        SafeContext.clearSessionTimeout();
    }
}
