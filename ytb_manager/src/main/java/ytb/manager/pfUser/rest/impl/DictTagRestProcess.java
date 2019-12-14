package ytb.manager.pfUser.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ytb.common.ytblog.YtbLog;
import ytb.manager.pfUser.model.Dict_Tag;
import ytb.manager.pfUser.service.impl.DictTagService;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: ljm
 * Date: Created in 2018/11/11
 */
public class DictTagRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody;

    public MsgResponse process(MsgHandler handler) {
        YtbLog.logDebug(handler.req.toJSONString());

        if (handler.req.cmd.equals("getDictTagTree")) {
            int tagType = handler.req.getMsgBody().getInteger("tagType");
            List<Dict_Tag> lst = new DictTagService().selectTree(tagType);
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(lst));

        } else if (handler.req.cmd.equals("getDictTagListByID")) {
            Integer tagType = handler.req.getMsgBody().getInteger("tagType");
            Integer tagId = handler.req.getMsgBody().getInteger("tagId");
            List<Dict_Tag> lst = new DictTagService().getDictTagListByID(tagType, tagId);
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(lst));

            return handler.resp;
        } else if (handler.req.cmd.equals("deleteDictTag")) {
            Integer tagId = handler.req.getMsgBody().getInteger("tagId");
            new DictTagService().deleteDictTag(tagId);

        } else if (handler.req.cmd.equals("updateDictTag")) {
            Dict_Tag dict_Tag = JSON.toJavaObject(handler.req.getMsgBody(), Dict_Tag.class);
            new DictTagService().updateDictTag(dict_Tag);

        } else if (handler.req.cmd.equals("insertDictTag")) {
            Dict_Tag dict_Tag = JSON.toJavaObject(handler.req.getMsgBody(), Dict_Tag.class);
            new DictTagService().insertDictTag(dict_Tag);
        }
        else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }
        handler.resp.setRetcode(0);
        handler.resp.setRetmsg("成功");
        return handler.resp;


    }


}
