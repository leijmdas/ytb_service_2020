package ytb.project.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.project.service.IndexManagerService;
import ytb.project.service.impl.IndexManagerServiceImpl;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.manager.webpagemng.model.PageResource;
import ytb.manager.webpagemng.service.PageResourceService;
import ytb.manager.webpagemng.service.impl.PageResourceServiceImpl;
import ytb.manager.webpagemng.utils.PageData;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexPage {

    public MsgResponse process(MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String retMsgBody = "{}";

        MsgRequest req = handler.req;
        JSONObject reqMsgBody = req.msgBody;

        IndexManagerService indexManagerService = new IndexManagerServiceImpl();
        UserCenterService userCenterService = new UserCenterServiceImpl();
        PageResourceService pageResourceService = new PageResourceServiceImpl();

        switch (req.cmd) {
            case "index": {
                //最新项目
                List<Map<String, Object>> newProjectList = indexManagerService.selectNewProjects();

                //优质人才
                List<Map<String, Object>> talentList = userCenterService.selectGoodPeople();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("newProjectList", newProjectList);
                jsonObject.put("talentList", talentList);

                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "sliderBox"://轮播图
            case "findProject"://找项目
            case "sendDemand"://发需求
            case "projectWork"://项目协作介绍
            case "successExample"://成功案例
            {
                Map<String, Integer> map = new HashMap<String, Integer>() {
                    {
                        put("sliderBox", 3);
                        put("findProject", 4);
                        put("sendDemand", 5);
                        put("projectWork", 1);
                        put("successExample", 2);
                    }
                };
                int pageNo = reqMsgBody.getInteger("pageNo");
                int limit = reqMsgBody.getInteger("limit");
                PageResource pageResource = new PageResource();
                pageResource.setResType((byte) map.get(req.cmd).intValue());
                PageData<PageResource> pageData = pageResourceService.pageQuery(pageResource, pageNo, limit);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(pageData);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", jsonArray);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            default: {
                retcode = 10;
                retmsg = "fail";
            }
        }
        return handler.buildMsg(retcode, retmsg, retMsgBody);
    }
}
