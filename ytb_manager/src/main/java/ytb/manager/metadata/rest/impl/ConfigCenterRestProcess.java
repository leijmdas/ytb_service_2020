package ytb.manager.metadata.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import ytb.common.basic.config.model.Dict_ConfigModel;
import ytb.common.basic.config.model.Dict_ErrorCode;
import ytb.common.basic.config.service.ConfigDAOService;

import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class ConfigCenterRestProcess {

    ConfigDAOService configManagerService = new ConfigDAOService();

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        //handler.buildMsg(0, "success", "{}");
        if (req.cmd.equals("getDictConfig")) {
            List<Dict_ConfigModel> lst = configManagerService.getDictConfig();
            String msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
            return handler.buildMsg(0,"success",  msgBody);
        } else
        if (req.cmd.equals("selectDictConfig")) {
            List<Dict_ConfigModel> lst = configManagerService.selectDictConfig();
            String msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
            return handler.buildMsg(0,"success",  msgBody);
        }
        else if (req.cmd.equals("getDictErrorCode")) {
            List<Dict_ErrorCode> lst = configManagerService.getDictErrorCode();
            String msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
            return handler.buildMsg(0,"success",  msgBody);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);


    }


}
