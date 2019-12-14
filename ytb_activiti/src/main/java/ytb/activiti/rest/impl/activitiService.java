package ytb.activiti.rest.impl;



import com.alibaba.fastjson.JSONObject;

import ytb.activiti.service.ActivitiService;
import ytb.activiti.service.impl.ActivitiServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;


public class activitiService {


    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";


    public MsgResponse process(MsgRequest req, RestHandler handler) {
        ActivitiService activitiService = new ActivitiServiceImpl();


        if(req.cmd.equals("startActiviti")){
            /*
           if(processId==null||processId.equals("")){
               retcode =1;
               retmsg = "启动工作流实例失败";
           }else{

               msgBody ="\"processId\":"+processId+"";
           }*/

        }//认领任务
        /*else if(req.cmd.equals("asSignMents")){

            String taskId = req.msgBody.getString("taskId");
            int userId = Integer.parseInt(req.msgBody.getString("userId"));
            activitiService.asSignMents(userId,taskId);

        }*/
        //完成任务
        else if(req.cmd.equals("completeTask")){
            String taskId = req.msgBody.getString("taskId");
            activitiService.completeTask(taskId);

        }else if(req.cmd.equals("sendNotify")){

            String processId ="";
            String proc_code = req.msgBody.getString("proc_code");
            JSONObject notify = req.msgBody.getJSONObject("notify");
            JSONObject task = req.msgBody.getJSONObject("task");
            if(notify!=null) {
                activitiService.sendNotify(proc_code,notify);
            }
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
