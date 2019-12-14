package ytb.manager.charges.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaSalary;
import ytb.manager.charges.pojo.Paging;
import ytb.manager.charges.rest.server.DistAreaSalaryServer;

import ytb.common.utils.pageutil.PageUtils;
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
public class DistAreaSalary {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, RestHandler handler) {
        DistAreaSalaryServer server = new DistAreaSalaryServer();


        if (req.cmd.equals("getDistAreaSalary")) {
            DictAreaSalary data = JSONObject.parseObject(req.msgBody.toString(), DictAreaSalary.class);

            List<DictAreaSalary> list = server.getDistAreaSalary(data, handler);
            msgBody = "{\"list\":" + JSONObject.toJSON(list) + "}";
            return handler.buildMsg(retcode, retmsg, msgBody);

        }

        if (req.cmd.equals("getDistAreaSalaryPage")) {
            DictAreaSalary data = JSONObject.parseObject(req.msgBody.toString(), DictAreaSalary.class);
            Paging paging = structurePaging(req);
            PageUtils pageUtil = server.getDistAreaSalaryPage(data, paging);

            //     msgBody = "{\"list\":" + JSONArray.toJSONString(pageUtil) + "}";
            msgBody = "{\"list\":" + JSONObject.toJSON(pageUtil) + "}";
            return handler.buildMsg(retcode, retmsg, msgBody);


        }


        if (req.cmd.equals("getDistAreaSalaryByName")) {
            DictArea data = JSONObject.parseObject(req.msgBody.toString(), DictArea.class);

            Paging paging = structurePaging(req);

            PageUtils pageUtils = server.getDistAreaSalaryByName(data, paging);
            msgBody = "{\"list\":" + JSONObject.toJSON(pageUtils) + "}";

            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("deleteDistAreaSalary")) {
            DictAreaSalary data = JSONObject.parseObject(req.msgBody.toString(), DictAreaSalary.class);

            int sta = server.deleteAreaSalary(data.getSalaryId());

            if (sta <= 0) {
                retcode = 1;
                retmsg = "失败";

            }

            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("insertDistAreaSalary")) {
            DictAreaSalary data = JSONObject.parseObject(req.msgBody.toString(), DictAreaSalary.class);
            int sta = server.insertAreaSalary(data);
            if (sta <= 0) {
                retcode = 1;
                retmsg = "失败";

            }
            return handler.buildMsg(retcode, retmsg, msgBody);

        } else if (req.cmd.equals("updateDistAreaSalary")) {
            DictAreaSalary data = JSONObject.parseObject(req.msgBody.toString(), DictAreaSalary.class);
            int sta = server.updateDistAreaSalary(data);

            if (sta <= 0) {
                retcode = 1;
                retmsg = "失败";

            }
            return handler.buildMsg(retcode, retmsg, msgBody);

        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

    }


    Paging structurePaging(MsgRequest req) {
        Paging paging = new Paging();
        if (
                req.msgBody.getInteger("currPage") != null &
                        req.msgBody.getInteger("pageSize") != null &
                        req.msgBody.getString("toOrder") != null &
                        req.msgBody.getString("orderBy") != null
        ) {
            paging.setCurrPage(req.msgBody.getInteger("currPage"));
            paging.setPageSize(req.msgBody.getInteger("pageSize"));
            paging.setToOrder(req.msgBody.getString("toOrder"));
            paging.setOrderBy(req.msgBody.getString("orderBy"));
            return paging;
        } else {
            return null;
        }

    }


}
