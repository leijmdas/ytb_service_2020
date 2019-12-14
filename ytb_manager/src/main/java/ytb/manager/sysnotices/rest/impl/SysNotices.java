package ytb.manager.sysnotices.rest.impl;

import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.log.notify.model.SystemNoticeModel;
import ytb.log.notify.service.TaskNotifyService;
import ytb.log.notify.service.impl.TaskNotifyServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.sysnotices.rest.impl
 * Author: ZCS
 * Date: Created in 2018/11/22 15:20
 */
public class SysNotices {


    public MsgResponse process(MsgHandler handler) {

        if (handler.req.cmd.equals("getSysNoticesList")) {

            Map<String,Object> params = new HashMap<>();
            params.put("type",handler.req.getMsgBody().getString("type"));//1系统通知  2系统公告
            params.put("title",handler.req.getMsgBody().getString("title"));
            params.put("page",handler.req.getMsgBody().getInteger("currPage"));
            params.put("limit",handler.req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(params);

            TaskNotifyService taskNotifyService =  new TaskNotifyServiceImpl();
            List<Map<String,Object>> lst = taskNotifyService.getMessageList(query);

            int count =  taskNotifyService.getMessageTotal(handler.req.getMsgBody().getString("title"),Integer.parseInt(handler.req.getMsgBody().getString("type")));

            PageUtils pageUtil = new PageUtils(lst, count, query.getLimit(), query.getPage());
            handler.resp.getMsgBody().put("list", pageUtil);
            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;
        } else if (handler.req.cmd.equals("addSysNotices")) {

            String messageText = handler.req.getMsgBody().getString("messageText");
            String messageTitle = handler.req.getMsgBody().getString("messageTitle");

            TaskNotifyService taskNotifyService =  new TaskNotifyServiceImpl();

            SystemNoticeModel systemNoticeModel = new SystemNoticeModel();
            systemNoticeModel.setMessageText(messageText);
            systemNoticeModel.setMessageTitle(messageTitle);

            taskNotifyService.addMessageInfo(systemNoticeModel);

            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;
        }

        else if(handler.req.cmd.equals("getSysNoticeById")){

            Integer id = handler.req.getMsgBody().getInteger("id");

            TaskNotifyService taskNotifyService =  new TaskNotifyServiceImpl();

            handler.resp.getMsgBody().put("list", taskNotifyService.getMessageById(id));
            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;

        }

        else if(handler.req.cmd.equals("deleteSysNotices")){
            Integer id = handler.req.getMsgBody().getInteger("id");
            TaskNotifyService taskNotifyService =  new TaskNotifyServiceImpl();

            taskNotifyService.deleteSysNotices(id);

            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;

        }

        else if(handler.req.cmd.equals("updateSysNotices")){
            String messageText = handler.req.getMsgBody().getString("messageText");
            String messageTitle = handler.req.getMsgBody().getString("messageTitle");
            Integer id = handler.req.getMsgBody().getInteger("id");
            TaskNotifyService taskNotifyService =  new TaskNotifyServiceImpl();

            SystemNoticeModel systemNoticeModel = new SystemNoticeModel();
            systemNoticeModel.setMessageText(messageText);
            systemNoticeModel.setMessageTitle(messageTitle);
            systemNoticeModel.setId(id);
            taskNotifyService.updateSysNotices(systemNoticeModel);

            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;

        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }

}
