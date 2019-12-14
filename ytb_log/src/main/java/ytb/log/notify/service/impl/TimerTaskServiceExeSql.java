package ytb.log.notify.service.impl;

import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.service.impl.YtbContext;

public class TimerTaskServiceExeSql implements ITimerTaskService {
    @Override
    public void execute(MsgRequest req,TaskLog_TimerModel task) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("start exe " + task.getExeObject());
        StringBuilder sql=new StringBuilder();
        sql.append(task.getExeObject());
        YtbContext.getYtb_context().getSqlSessionBuilder().updateSql(sql);
        System.out.println(sql);
        System.out.println("end exe " + task.getExeObject() + " ok!");

    }
}
