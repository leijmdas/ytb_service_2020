package ytb.project.rest.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.project.model.*;
import ytb.project.service.search.*;
import ytb.project.service.search.impl.*;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //购买商商品
 * @Date 2018/11/21
 **/
public class PurchaseGoods {

    public MsgResponse process(MsgRequest req, MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        if (handler.req.cmd.equals("goodsType")) {
            String parentId = req.msgBody.getString("parentId");
            PurchaseGoodsService service = new PurchaseGoodsServiceImpl();
            List<SearchProjectModel> projectModelList=service.getGoodsType(parentId);
            msgBody="{\"list\":" + JSONObject.toJSONString(projectModelList, SerializerFeature.WriteMapNullValue) + "}";
        }else if(handler.req.cmd.equals("purchaseGoodsList")){
            PurchaseGoodsService service = new PurchaseGoodsServiceImpl();

            Map<String,Object> map = new HashMap<>();
            map.put("week",req.msgBody.getString("week"));
            map.put("month",req.msgBody.getString("month"));
            map.put("year",req.msgBody.getString("year"));
            map.put("purchaseName",req.msgBody.getString("goodsName"));
            map.put("purchaseFactory",req.msgBody.getString("factoryName"));
            map.put("projectTypeId",req.msgBody.getString("typeId"));
            map.put("creditGrade",req.msgBody.getString("userGread"));
            map.put("page",req.getMsgBody().getInteger("currPage"));
            map.put("limit",req.getMsgBody().getInteger("pageSize"));
            Query query = new Query(map);

            List<Map<String,Object>> pageList =service.getPurchaseGoodsPageInfo(query);
            int count =  service.getPurchaseGoodsCount(query);
            PageUtils pageUtil = new PageUtils(pageList, count, query.getLimit(), query.getPage());
            msgBody = "{\"list\":" + JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue) + "}";        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }
}
