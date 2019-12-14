package ytb.project.rest.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.project.model.*;
import ytb.project.service.search.SupplierCommodityService;
import ytb.project.service.search.impl.SupplierCommodityServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2018/11/20
 **/
public class SupplierCommodity {


    public MsgResponse process(MsgRequest req, MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        if (handler.req.cmd.equals("goodsType")) {
            String parentId = req.msgBody.getString("parentId");
            SupplierCommodityService service = new SupplierCommodityServiceImpl();
            List<SearchProjectModel> projectModelList = service.getGoodsType(parentId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(projectModelList, SerializerFeature.WriteMapNullValue) + "}";
        }

        else if (handler.req.cmd.equals("SearchGoodsList")) {
            SupplierCommodityService service = new SupplierCommodityServiceImpl();

            Map<String,Object> map = new HashMap<>();
            map.put("productName",req.msgBody.getString("goodsName"));
            map.put("productFactory",req.msgBody.getString("factoryName"));
            map.put("projectTypeId",req.msgBody.getString("typeId"));
            map.put("week",req.msgBody.getString("week"));
            map.put("month",req.msgBody.getString("month"));
            map.put("year",req.msgBody.getString("year"));
            map.put("creditGrade",req.msgBody.getString("userGread"));
            map.put("page",req.getMsgBody().getInteger("currPage"));
            map.put("limit",req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(map);

            List<Map<String,Object>> pageList = service.getSellGoodsPageInfo(query);

            int count =  service.getSellGoodsPageInfoCount(query);
            PageUtils pageUtil = new PageUtils(pageList, count, query.getLimit(), query.getPage());
            msgBody = "{\"list\":" + JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue) + "}";
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
