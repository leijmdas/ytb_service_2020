package ytb.manager.charges.rest.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ytb.manager.charges.model.ServiceFee;
import ytb.manager.charges.pojo.Paging;
import ytb.manager.charges.rest.server.ServiceFeeServer;


import ytb.common.utils.pageutil.PageUtils;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.Date;
import java.util.List;


public class ServiceFeeRest {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        if (req.cmd.equals("getServiceFee")) {
            //

            ServiceFee data = JSONObject.parseObject(req.msgBody.toString(), ServiceFee.class);

            List<ServiceFee> list = new ServiceFeeServer().getServiceFee(data);

            msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";

            return handler.buildMsg(retcode, retmsg, msgBody);

        }
        if (req.cmd.equals("getServiceFeePage")) {
            //

            ServiceFee data = JSONObject.parseObject(req.msgBody.toString(), ServiceFee.class);
            Date date = new Date();

            Paging paging = null;

            if (
                    req.msgBody.getInteger("currPage") != null &
                            req.msgBody.getInteger("pageSize") != null &
                            req.msgBody.getString("toOrder") != null &
                            req.msgBody.getString("orderBy") != null
            ) {
                paging = new Paging();
                paging.setCurrPage(req.msgBody.getInteger("currPage"));
                paging.setPageSize(req.msgBody.getInteger("pageSize"));
                paging.setToOrder(req.msgBody.getString("toOrder"));
                paging.setOrderBy(req.msgBody.getString("orderBy"));
            }

            PageUtils list = new ServiceFeeServer().getServiceFeePage(data, paging);

            msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";

            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("insertServiceFee")) {
            List<ServiceFee> data = JSONArray.parseArray(req.msgBody.toString(), ServiceFee.class);


            ServiceFeeServer server = new ServiceFeeServer();

            Boolean sta = server.insertServiceFee(data);

            if (sta == true) {
                retcode = 0;
                retmsg = "成功";

            } else {
                retcode = 1;
                retmsg = "新增失败";
            }
            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("deleteServiceFee")) {
            ServiceFee data = JSONObject.parseObject(req.msgBody.toString(), ServiceFee.class);

            Boolean sta = new ServiceFeeServer().deleteServiceFee(data.getFeeId());
            if (sta == true) {
                retcode = 0;
                retmsg = "成功";

            } else {
                retcode = 1;
                retmsg = "删除失败";
            }
            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("updateServiceFee")) {
            ServiceFee data = JSONObject.parseObject(req.msgBody.toString(), ServiceFee.class);


            boolean sta = new ServiceFeeServer().updateServiceFee(data);

            if (sta == true) {
                retcode = 0;
                retmsg = "成功";

            } else {
                retcode = 1;
                retmsg = "修改失败";
            }


            return handler.buildMsg(retcode, retmsg, msgBody);


        } else {

            throw new YtbError(YtbError.CODE_INVALID_REST);

        }


    }


}
