package ytb.manager.pfUser.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.manager.pfUser.service.impl.PlatformUserManagerServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.pfUser.rest.impl
 * Author: ZCS
 * Date: Created in 2018/12/5 11:09
 * Company: 公司
 */
public class PlatformUserManager {
    public MsgResponse process(MsgHandler handler) {
        //获取平台用
        if (handler.req.cmd.equals("getPlatformUser")) {

            Map<String,Object> params = new HashMap<>();
            params.put("nickName",handler.req.getMsgBody().getString("nickName"));
            params.put("page",handler.req.getMsgBody().getInteger("currPage"));
            params.put("limit",handler.req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(params);

            List<Map<String,Object>>  list = new PlatformUserManagerServiceImpl().getPlatformUserList(query);
            String nickName = handler.req.getMsgBody().getString("nickName");
            int count = new PlatformUserManagerServiceImpl().getPlatformUserTotal(nickName);

            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());

            handler.resp.getMsgBody().put("list", JSONObject.toJSON(pageUtil));
        }

        //获取公司用户
        else if(handler.req.cmd.equals("getPlatformCompany")){
            Map<String,Object> params = new HashMap<>();
            params.put("nickName",handler.req.getMsgBody().getString("nickName"));
            params.put("page",handler.req.getMsgBody().getInteger("currPage"));
            params.put("limit",handler.req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(params);

            List<Map<String,Object>>  list = new PlatformUserManagerServiceImpl().getPlatformCompanyList(query);
            String nickName = handler.req.getMsgBody().getString("nickName");
            int count = new PlatformUserManagerServiceImpl().getPlatformCompanyTotal(nickName);

            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());

            handler.resp.getMsgBody().put("list", JSONObject.toJSON(pageUtil));
        }
        else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }
        handler.resp.setRetcode(0);
        handler.resp.setRetmsg("成功");
        return handler.resp;


    }
}
