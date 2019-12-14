package ytb.manager.charges.rest.impl;


import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.service.impl.ThirdPartyServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;


public class ServiceThirdPartyRest {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        if (req.cmd.equals("modifyStatus")) {

            JSONObject account_third_key = handler.req.msgBody.getJSONObject("account_third_key");
            int key_id = account_third_key.getInteger("key_id");
            Boolean is_active = account_third_key.getBoolean("is_active");
            new ThirdPartyServiceImpl().modifyStatus(key_id,is_active);
            msgBody = "{ }";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);


    }


}
