//package ytb.common.testcase.Rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

public class RestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, MsgHandler handler) {


        if (req.cmd.equals("selectList")) {
            int id=req.msgBody.getInteger("id");
            List<ModelTemplate> list= new ModelServiceImpl().selectList(id);
            msgBody = "{'list':"+ JSONObject.toJSONString(list) +"}";

        }else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    String getPrjTypeList(int id) {

        return msgBody;

    }

}
