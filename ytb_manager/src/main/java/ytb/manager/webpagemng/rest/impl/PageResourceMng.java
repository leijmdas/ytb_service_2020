package ytb.manager.webpagemng.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.manager.webpagemng.model.PageResource;
import ytb.manager.webpagemng.service.PageResourceService;
import ytb.manager.webpagemng.service.impl.PageResourceServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.Date;
import java.util.List;

/**
 * @author lxz 2018/12/15 17:36
 */
public class PageResourceMng {

    public MsgResponse process(MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String retMsgBody = "{}";

        MsgRequest req = handler.req;
        JSONObject reqMsgBody = req.msgBody;

        PageResourceService pageResourceService = new PageResourceServiceImpl();

        switch (req.cmd) {
            case "addPageResource": {
                PageResource pageResource = JSON.toJavaObject(reqMsgBody, PageResource.class);
                pageResource.setCreateTime(new Date());
                LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(req.token);
                LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
                pageResource.setCreateBy(loginSsoJson.getUserId());
                pageResourceService.add(pageResource);
                break;
            }
            case "removePageResource": {
                Integer resId = reqMsgBody.getInteger("resId");
                pageResourceService.removeByResId(resId);
                break;
            }
            case "updatePageResource": {
                PageResource pageResource = JSON.toJavaObject(reqMsgBody, PageResource.class);
                pageResourceService.modify(pageResource);
                break;
            }
            case "selectPageResourceList": {
                PageResource pageResource = JSON.toJavaObject(reqMsgBody, PageResource.class);
                List<PageResource> pageResourceList = pageResourceService.selectAll(pageResource);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", pageResourceList);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            default: {
                retcode = 1000;
                retmsg = "fail";
            }
        }
        return handler.buildMsg(retcode, retmsg, retMsgBody);
    }
}
