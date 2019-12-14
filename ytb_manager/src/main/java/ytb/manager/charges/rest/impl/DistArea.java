package ytb.manager.charges.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.model.DictArea;

import ytb.manager.charges.rest.server.DistAreaServer;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class DistArea {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, RestHandler handler) {


        if (req.cmd.equals("getDistArea")) {

            DictArea data = JSONObject.parseObject(req.msgBody.toString(), DictArea.class);

            DistAreaServer distAreaServer = new DistAreaServer();
            List<DictArea> list = distAreaServer.getDistArea(data, handler);

            msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";


            return handler.buildMsg(retcode, retmsg, msgBody);


        } else if (req.cmd.equals("deleteDistArea")) {

            DictArea data = JSONObject.parseObject(req.msgBody.toString(), DictArea.class);
            DistAreaServer distAreaServer = new DistAreaServer();
            Boolean sta = distAreaServer.deleteDistArea(data, handler);
            if (sta == false) {
                retcode = 1;
                retmsg = "失败!区级以下有数据或数据错误!";
            }


            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("updateDistArea")) {

            DictArea data = JSONObject.parseObject(req.msgBody.toString(), DictArea.class);
            DistAreaServer distAreaServer = new DistAreaServer();
            Boolean sta = distAreaServer.updateDistArea(data, handler);


            if (sta == false) {
                retcode = 1;
                retmsg = "失败";
            }

            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("insertDistArea")) {

            DictArea data = JSONObject.parseObject(req.msgBody.toString(), DictArea.class);
            DistAreaServer distAreaServer = new DistAreaServer();
            Boolean sta = distAreaServer.insertDistArea(data, handler);

            if (sta == false) {
                retcode = 1;
                retmsg = "失败";
            }

            return handler.buildMsg(retcode, retmsg, msgBody);

        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

    }
}




