package ytb.project.rest.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.DAOService;
import ytb.project.service.IReleaseView;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.project.rest.impl
 * Author: ZCS
 * Date: Created in 2018/10/25 13:48
 */
public class CompanyProjectManager extends DAOService {


    public MsgResponse process(MsgRequest req, MsgHandler handler) {
        IReleaseView releaseView = getInst().getReleaseView();

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        UserProjectContext context= UserProjectContext.pre(handler);
        Integer loginUserId = context.getUserId();//当前登录人
        Integer loginCompanyId = context.getCompanyId();

        //公司员工项目
        if (handler.req.cmd.equals("getProjectListByCompUser")) {
            Map<String,Object> map = new HashMap<>();
            map.put("userId",loginUserId);
            map.put("companyId",loginCompanyId);
            map.put("page",req.msgBody.getInteger("currPage"));
            map.put("limit",req.msgBody.getInteger("pageSize"));
            Query query = new Query(map);

            List<Map<String,Object>> list = releaseView.getProjectDAOService().getProjectListByCompUser(query);

            PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";

        }

        //查询用户已完成项目
        else if(handler.req.cmd.equals("getUserFishedProList")){
            Integer userId = req.msgBody.getInteger("userId");

            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("page",req.msgBody.getInteger("currPage"));
            map.put("limit",req.msgBody.getInteger("pageSize"));


            Query query = new Query(map);
            List<Map<String,Object>> list = releaseView.getProjectDAOService().selectFinishProject(query);
            int count = releaseView.getProjectDAOService().selectFinishProjectCount(userId);
            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());

            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }

        //获取公司项目
        else if(handler.req.cmd.equals("getProjectListByComP")){

            String status = req.msgBody.getString("status");
            Map<String,Object> map = new HashMap<>();
            map.put("status",status);
            map.put("companyId1",loginCompanyId);
            List<Map<String,Object>> list = releaseView.getProjectListOfCompany(map);

            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";

        }
        else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
