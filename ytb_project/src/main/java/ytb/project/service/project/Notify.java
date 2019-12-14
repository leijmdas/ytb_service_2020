package ytb.project.service.project;

import com.alibaba.fastjson.JSONObject;
import ytb.log.notify.service.impl.TaskNotifyServiceImpl;
import ytb.project.context.UserProjectContext;

//(1)通知评审工作流:
//①发需求评审
//②洽谈通知
//③文档审核
//④文档协助通知
//⑤项目支付短信通知
//⑥发送会议通知
//⑦发送物流单号通知
public class Notify extends TaskNotifyServiceImpl {

    //发需求评审
    public int sendTalkNotify(int userId, int objectType, String taskParamIn) {
        //申请
        return certificationUser(userId, objectType, taskParamIn);
    }



    public int sendNotifyPa(UserProjectContext toContext, int userId, int serviceType, String type, JSONObject param) {

        return sendMsg(userId, toContext.getUserId(), param, type,
                toContext.getLoginSso().getLoginSsoJson().getLogin_mobile(), serviceType);

    }
    //洽谈通知
    public int sendNotify(int userId, int toUserId, int serviceType, String type, String phone, JSONObject param) {

        return sendMsg(userId, toUserId, param, type, phone, serviceType);

    }

}
