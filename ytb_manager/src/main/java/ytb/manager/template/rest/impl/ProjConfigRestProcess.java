package ytb.manager.template.rest.impl;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import ytb.manager.template.model.Dict_Prj_Config;
import ytb.manager.template.service.impl.ProjConfigServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: leijianming
 * Date: Created in 2018/11/16 16:21
 */


public class ProjConfigRestProcess {



    public MsgResponse process(RestHandler handler, MultipartFile file) throws Exception {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";

        if (handler.req.cmd.equals("selectProjConfig")) {
            int projectType = handler.req.getMsgBody().getInteger("projectType");
            ProjConfigServiceImpl pcs = new ProjConfigServiceImpl();
            List<Dict_Prj_Config> lst = pcs.selectProjConfig(projectType);
            msgBody = "{\"list\":" + MsgHandler.toJSONStringPretty(lst) + "}";

        } else if (handler.req.cmd.equals("addProjConfig")) {
            Dict_Prj_Config c = JSONObject.parseObject(handler.req.getMsgBody().toJSONString(), Dict_Prj_Config.class);
            ProjConfigServiceImpl pcs = new ProjConfigServiceImpl();
            pcs.addProjConfig(c);
            msgBody = "{}";
        }
        else if (handler.req.cmd.equals("modifyProjConfig")) {
            Dict_Prj_Config c = JSONObject.parseObject(handler.req.getMsgBody().toJSONString(), Dict_Prj_Config.class);
            ProjConfigServiceImpl pcs = new ProjConfigServiceImpl();
            pcs.modifyProjConfig(c);
            msgBody = "{}";
        } else if (handler.req.cmd.equals("delProjConfig")) {
            int configId = handler.req.getMsgBody().getInteger("configId");
            ProjConfigServiceImpl pcs = new ProjConfigServiceImpl();
            pcs.delProjConfig(configId);
            msgBody = "{}";
        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
