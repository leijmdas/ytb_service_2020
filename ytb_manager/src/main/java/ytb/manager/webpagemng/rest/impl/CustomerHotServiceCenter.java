package ytb.manager.webpagemng.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ytb.manager.webpagemng.model.CurServiceHotSearch;
import ytb.manager.webpagemng.service.CusHotDoService;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.List;

/**
 * @author lxz 2018/12/22 16:31
 */
@Scope("prototype")
@Service
public class CustomerHotServiceCenter {

    public MsgResponse process(MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String retMsgBody = "{}";

        MsgRequest req = handler.req;
        JSONObject reqMsgBody = req.msgBody;

        CusHotDoService hotDoService = new CusHotDoService();

        switch (req.cmd) {
            case "addHotSearch": {
                CurServiceHotSearch questionType = JSON.toJavaObject(reqMsgBody, CurServiceHotSearch.class);
                hotDoService.add(questionType);
                break;
            }
            case "removeHotSearch": {
                CurServiceHotSearch questionType = JSON.toJavaObject(reqMsgBody, CurServiceHotSearch.class);
                Boolean sta = hotDoService.delete(questionType.getId());
                if (sta) {
                    retmsg = "成功";
                    retMsgBody = "{}";
                } else {
                    retcode = 1000;
                    retmsg = "fail";
                }
                break;
            }
            case "updateHotSearch": {
                CurServiceHotSearch questionType = JSON.toJavaObject(reqMsgBody, CurServiceHotSearch.class);
                boolean curServiceHotSearches = hotDoService.updateByPrimaryKeySelective(questionType);
                if (curServiceHotSearches) {
                    retmsg = "成功";
                    retMsgBody = "{}";
                } else {
                    retcode = 1000;
                    retmsg = "fail";
                }
                break;
            }
            case "selHot": {
                List<CurServiceHotSearch> curServiceHotSearches = hotDoService.selectAll();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", curServiceHotSearches);
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
