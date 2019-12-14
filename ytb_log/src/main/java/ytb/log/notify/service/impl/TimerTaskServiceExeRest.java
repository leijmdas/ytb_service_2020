package ytb.log.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.log.notify.model.TaskLog_TimerModel;
import ytb.log.notify.service.ITimerTaskService;
import ytb.common.RestMessage.MsgRequest;

public class TimerTaskServiceExeRest implements ITimerTaskService {

    @Override
    public void execute(MsgRequest req, TaskLog_TimerModel task) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("start exe " + task.getExeObject());
        JSONObject newReq = JSONObject.parseObject(task.getParams());
        newReq.put("token", req.getToken());
        YtbUtils.postFor(task.getExeObject(), JSONObject.toJSONString(newReq));
        System.out.println(task.getExeObject());
        System.out.println("end exe " + task.getExeObject() + " ok!");

    }
}
