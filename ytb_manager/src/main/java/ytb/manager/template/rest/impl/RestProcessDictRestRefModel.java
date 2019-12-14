package ytb.manager.template.rest.impl;//package ytb.common.testcase.Rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.template.daoservice.DictRestRefModelServiceImpl;
import ytb.manager.template.model.DictRestRefModel;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package:
 * Description：
 * Author: ZCS
 * Date: Created in 2018/8/14 16:21
 * Company: 公司
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 */
public class RestProcessDictRestRefModel {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, MsgHandler handler) {

        if (req.cmd.equals("selectList")) {
            int id = req.msgBody.getInteger("id");
            List<DictRestRefModel> list = new DictRestRefModelServiceImpl().selectList();
            msgBody = "{'list':" + JSONObject.toJSONString(list) + "}";
        } else if (req.cmd.equals("getRefId")) {
            String refPath = req.msgBody.getString("refPath");
            int getRefId = new DictRestRefModelServiceImpl().getRefId(refPath);
            msgBody = "{'list':[" + getRefId + "]}";

        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    String getPrjTypeList(int id) {

        return msgBody;

    }

}
