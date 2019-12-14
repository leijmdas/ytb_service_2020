package ytb.activiti.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ytb.common.RestMessage.MsgRequest;

import java.util.UUID;


public class ServiceNotify implements JavaDelegate {

        private Expression template_id;

        private Expression template_param;

        private Expression user_id;

        MsgRequest req = new MsgRequest();

        public void execute(DelegateExecution delegateExecution) {
            ProcessEngines.getDefaultProcessEngine();

            int template_Id = (Integer) template_id.getValue(delegateExecution);
            int userId = (Integer) user_id.getValue(delegateExecution);

            String template_Param =(String) template_param.getValue(delegateExecution);

            RestTemplate restTemplate=new RestTemplate();
            String url="http://192.168.0.134:80/rest/taskLog_task";
            req.token = UUID.randomUUID().toString();
            req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
            req.seqno = System.currentTimeMillis();
            req.cmdtype = "tasklog";
            req.cmd = "addTaskNotify";
            req.msgBody = JSONObject.parseObject("{\"template_Param\":"+ template_Param +"}");
            String data=new Gson().toJson(req).toString();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, data, String.class);

        }


}
