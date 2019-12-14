package ytb.activiti.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import ytb.common.basic.activititemplate.model.Manager_Template_ProcModel;
import ytb.activiti.service.ActivitiManagerService;
import ytb.activiti.service.impl.ActivitiManagerServiceImpl;
import ytb.common.utils.YtbUtils;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class activitiManager {


    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler) throws UnsupportedEncodingException {


        ActivitiManagerService activitiManagerService = new ActivitiManagerServiceImpl();
          //查询所有历史任务
        if(req.cmd.equals("getHistoryTaskList")){

          List<HistoricTaskInstance> list =  activitiManagerService.getHistoryTaskList();

        } //通过任务名查询历史任务
        else if(req.cmd.equals("getHistoryTaskListByName")){

            String taskName = req.msgBody.getString("taskName");
            activitiManagerService.getHistoryTaskListByName(taskName);

        } //查询工作流实例
        else if(req.cmd.equals("getActivitiInstance")){

          List<ProcessInstance> list = activitiManagerService.getActivitiInstance();
            JSONArray json = new JSONArray();
            for(ProcessInstance pi:list){
                json.add(pi);
            }
            msgBody="{\"list\":"+json.toJSONString()+"}";
        } //个人查询工作流任务
        else if(req.cmd.equals("getActivitiTask")){
            String assignee = req.msgBody.getString("assignee");
            List<Task> tasks = activitiManagerService.getActivitiTask(assignee);

        } //角色查询工作流任务
        else if(req.cmd.equals("getActivitiTaskByGroup")){

            String role = req.msgBody.getString("role");
            activitiManagerService.getActivitiTaskByGroup(role);

        }//通过用户名查询历史任务
        else if(req.cmd.equals("getHistoryTaskListByAssignee")){
            String assignee = req.msgBody.getString("assignee");
            activitiManagerService.getHistoryTaskListByAssignee(assignee);

        }//查询所有工作流模板
        else if (req.cmd.equals("getActivitiTemplateList")) {

            List<Manager_Template_ProcModel> list = activitiManagerService.getActivitiTemplateList();

            msgBody = "{\"list\":" + YtbUtils.toJSONStringPretty(list) + "}";

        }//根据id查询工作流模板
        else if (req.cmd.equals("getActivitiTemplateById")) {
            Integer proc_id = req.msgBody.getInteger("proc_id");
            if (proc_id == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
            Manager_Template_ProcModel mtp = activitiManagerService.getActivitiTemplate(proc_id);
            JSONArray json1 = new JSONArray();
            JSONObject json = (JSONObject) JSONObject.toJSON(mtp);
            if(mtp.getProc_content()!=null) {
                String s = new String(mtp.getProc_content(), "UTF-8");
                json.fluentPut("proc_content", s);
            }else{
                json.fluentPut("proc_content", "");
            }
            json1.add(json);
            msgBody = "{\"list\":" + MsgHandler.toJSONStringPrettyWriteMapNullValue(json1) + "}";

        } else if (req.cmd.equals("addActivitiTemplate")) {

            Manager_Template_ProcModel mtp = req.msgBody.toJavaObject(req.msgBody,Manager_Template_ProcModel.class);
            if(mtp==null) {
                retcode = 1;
                retmsg = "msgbody中参数有问题,或对象转换问题";
            }else {
                activitiManagerService.addActivitiTemplate(mtp);
            }
        }else if(req.cmd.equals("modifyActivitiStatus")){

            Manager_Template_ProcModel mtp = req.msgBody.toJavaObject(req.msgBody,Manager_Template_ProcModel.class);
            if(mtp==null) {
                retcode = 1;
                retmsg = "msgbody中参数有问题,或对象转换问题";
            }else {
               int status = activitiManagerService.modifyActivitiStatus(mtp);
               if(status ==1){
                   retcode = 1;
                   retmsg = "发布失败";
               }else if(status ==2){
                   retcode = 1;
                   retmsg = "流程模板key输入错误";
               }
            }
        }else if(req.cmd.equals("modifyActivitiTemplate")){

            Manager_Template_ProcModel mtp = req.msgBody.toJavaObject(req.msgBody,Manager_Template_ProcModel.class);

                if(mtp==null) {
                    retcode = 1;
                    retmsg = "msgbody中参数有问题,或对象转换问题";
                }else{
                    activitiManagerService.modifyActivitiTemplate(mtp);
                }

        }else if(req.cmd.equals("removeActivitiTemplate")){

            String assignee = req.msgBody.getString("proc_id");
            if(assignee==""||assignee==null){
                retcode = 1;
                retmsg = "msgbody中参数有问题";
            }else{
               int proc_id = Integer.parseInt(assignee);
               activitiManagerService.removeActivitiTemplate(proc_id);
            }

        }else if(req.cmd.equals("modifyActivitiTemplateContent")){
            String proc_content =req.msgBody.getString("proc_content");
            String proc_id = req.msgBody.getString("proc_id");
            byte[] bytes = proc_content.getBytes();
            Manager_Template_ProcModel mtp =new Manager_Template_ProcModel();
            mtp.setProc_content(bytes);
            mtp.setProc_id(Integer.parseInt(proc_id));
            activitiManagerService.modifyActivitiTemplateContent(mtp);
            //后台中止工作流实例
        }
        else if(req.cmd.equals("stopActiviti")){
            String processInstanceId = req.msgBody.getString("processInstanceId");
            activitiManagerService.stopActiviti(processInstanceId);

        }else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
