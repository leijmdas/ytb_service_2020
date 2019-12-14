package ytb.log.notify.service.impl;

import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.common.RestMessage.MsgRequest;

public class TimerTaskServiceExeJAVA implements ITimerTaskService {
    @Override
    public void execute(MsgRequest req, TaskLog_TimerModel task) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("start exe " + task.getExeObject());
        ITimerTaskService tts = (ITimerTaskService) Class.forName(task.getExeObject()).newInstance();
        tts.execute(req, task);
        System.out.println("end exe " + task.getExeObject() + " ok!");
    }
}
