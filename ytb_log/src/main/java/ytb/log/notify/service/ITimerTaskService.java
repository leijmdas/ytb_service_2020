package ytb.log.notify.service;

import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.common.RestMessage.MsgRequest;

public interface ITimerTaskService {
    void execute(MsgRequest req,TaskLog_TimerModel task) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
