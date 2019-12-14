package ytb.user.rest.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.user.model.*;
import ytb.user.service.SearchPersonService;
import ytb.user.service.impl.SearchPersonServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //人才搜索
 * @Date 2018/10/29
 **/
public class SearchPerson {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {
        if (req.cmd.equals("searchTag")) {
            String parentId = req.msgBody.getString("parentId");
            //专业能力标签
            SearchPersonService service = new SearchPersonServiceImpl();
            List<Map<String,Object>> tagList = service.getPageTag(parentId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(tagList, SerializerFeature.WriteMapNullValue) + "}";

        } else if (req.cmd.equals("searchScope")) {
            //接单范围
            String parentId = req.msgBody.getString("parentId");
            SearchPersonService service = new SearchPersonServiceImpl();

            List<SearchProjectModel> scopeList = service.getPageScope(parentId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(scopeList, SerializerFeature.WriteMapNullValue) + "}";


        } else if (req.cmd.equals("searchPagePerson")) {
            //接单范围
            Map<String,Object> params = new HashMap<>();
            SearchPersonService service = new SearchPersonServiceImpl();

            params.put("nickName",req.msgBody.getString("nickName"));//昵称
            params.put("userGread",req.msgBody.getString("userGread"));//信用等级
            params.put("level",req.msgBody.getString("level"));//学历
            params.put("tagId",req.msgBody.getString("tagId"));//专业能力标签
            params.put("typeId",req.msgBody.getString("typeId"));//项目类别
            params.put("userAddress",req.msgBody.getString("userAddress"));//用户地址
            params.put("page",req.getMsgBody().getInteger("currPage"));
            params.put("limit",req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(params);
            List<SearchPagePersonModel> pageList = service.getPagePerson(query);
            int count =  service.getPagePersonCount(query);
            PageUtils pageUtil = new PageUtils(pageList, count, query.getLimit(), query.getPage());

            msgBody = "{\"list\":" + JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue) + "}";

        }

        else if(req.cmd.equals("getAreaList")){
            Integer parentId = req.msgBody.getInteger("parentId");
            SearchPersonService service = new SearchPersonServiceImpl();
            List<Map<String,Object>> list = service.getDictAreaList(parentId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue) + "}";

        }
        //公司搜索
        else if(req.cmd.equals("searchCompanyList")){
            Map<String,Object> params = new HashMap<>();
            SearchPersonService service = new SearchPersonServiceImpl();

            params.put("companyName",req.msgBody.getString("companyName"));//昵称
            params.put("tagId",req.msgBody.getString("tagId"));//专业能力标签
            params.put("typeId",req.msgBody.getString("typeId"));//项目类别
            params.put("userAddress",req.msgBody.getString("userAddress"));//用户地址
            params.put("page",req.getMsgBody().getInteger("currPage"));
            params.put("limit",req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(params);
            List<Map<String,Object>> pageList = service.getSearchCompany(query);
            int count =  service.getSearchCompanyCount(query);
            PageUtils pageUtil = new PageUtils(pageList, count, query.getLimit(), query.getPage());
            msgBody = "{\"list\":" + JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue) + "}";
        }

        else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
