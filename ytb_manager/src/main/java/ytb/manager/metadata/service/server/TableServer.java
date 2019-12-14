package ytb.manager.metadata.service.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.common.utils.YtbUtils;
import ytb.manager.metadata.model.SelectSql;
import ytb.manager.metadata.service.impl.MetadataDictServiceImpl;
import ytb.manager.metadata.service.impl.SysMetaDataServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;
import java.util.Map;

public class TableServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;
    void checkTableExists(SelectSql ss){
        String tbl=ss.getTable();
        String [] names=tbl.split("\\.");
        if(names.length>1) {
            if(!MetadataDictServiceImpl.checkTableExists(names[0],names[1])){
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR," 表不存在！");
            }
        }
    }
    public MsgResponse selectByTableByLimitOrder(MsgRequest req, RestHandler handler) {


        SysMetaDataServiceImpl sysMetaDataService = new SysMetaDataServiceImpl();
        SelectSql asd = JSONObject.parseObject(req.msgBody.toString(), SelectSql.class);
        checkTableExists(asd);

        List<Map<String, Object>> list = sysMetaDataService.selectByTableLimitOrderBy(asd);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        msgBody = "{\"list\":" + JSONObject.toJSONString(array, SerializerFeature.WriteMapNullValue) + "}";

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse selectByTableByLimit(MsgRequest req, RestHandler handler) {

        SelectSql asd = JSONObject.parseObject(req.msgBody.toString(), SelectSql.class);
        checkTableExists(asd);
        SysMetaDataServiceImpl sysMetaDataService = new SysMetaDataServiceImpl();
        List<Map<String, Object>> list = sysMetaDataService.selectByTableLimit(asd);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        msgBody = "{\"list\":" + array.toJSONString() + "}";
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse selectByTable(MsgRequest req, RestHandler handler) {

        SysMetaDataServiceImpl sysMetaDataService = new SysMetaDataServiceImpl();
        SelectSql selectSql = JSONObject.parseObject(req.msgBody.toString(), SelectSql.class);
        checkTableExists(selectSql);
        List<Map<String, Object>> list = sysMetaDataService.selectByTable(selectSql);
        msgBody = "{\"list\":" + YtbUtils.toJSONStringPretty(list) + "}";

        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public MsgResponse makeTableByDictId(MsgRequest req, RestHandler handler) {
        SysMetaDataServiceImpl sysMetaDataService = new SysMetaDataServiceImpl();
        int metadataId = req.msgBody.getInteger("metadataId");
        return sysMetaDataService.makeTableByDictId(metadataId, req, handler);
    }




}
