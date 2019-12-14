package ytb.manager.metadata.service.server;


import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.manager.metadata.model.*;
import ytb.manager.metadata.service.impl.DictDataTypeServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

public class DictDataTypeServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse getDictDataTypeList(MsgRequest req, RestHandler handler) {
        DictDataTypeServiceImpl dictDataTypeService = new DictDataTypeServiceImpl();
        DictDatatypeExample dictDatatypeExample = new DictDatatypeExample();
        dictDatatypeExample.setOrderByClause("type_id,data_id");
        Integer typeId = req.msgBody.getInteger("typeId");
        if (typeId != null) {
            dictDatatypeExample.createCriteria().andTypeIdEqualTo(typeId);
        }
        List<DictDatatype> lst = dictDataTypeService.selectByExample(dictDatatypeExample);
        msgBody = "{\"list\":" + YtbUtils.toJSONString(lst) + "}";
        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public MsgResponse getDictDataTypeById(MsgRequest req, RestHandler handler) {
        DictDataTypeServiceImpl dictDataTypeService = new DictDataTypeServiceImpl();
        DictDatatypeExample dictDatatypeExample = new DictDatatypeExample();
        Integer dataInnerId = req.msgBody.getInteger("dataInnerId");
        if (dataInnerId != null) {
            dictDatatypeExample.createCriteria().andDataInnerIdEqualTo(dataInnerId);
            DictDatatype dictDatatype = dictDataTypeService.selectByPrimaryKey(dataInnerId);
            msgBody = "{\"list\":[" + JSONObject.toJSONString(dictDatatype) + "]}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }
        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
    }

    public MsgResponse addDictDataType(MsgRequest req, RestHandler handler) {
        DictDataTypeServiceImpl dictDataTypeService = new DictDataTypeServiceImpl();
        DictDatatype dictDatatype = JSONObject.parseObject(JSONObject.toJSONString(req.getMsgBody()), DictDatatype.class);
        int id = dictDataTypeService.insert(dictDatatype);
        msgBody = "{\"id\":" + id + " }";
        return handler.buildMsg(retcode, retmsg, msgBody);

    }

    public MsgResponse updateDictDataTypeById(MsgRequest req, RestHandler handler) {
        DictDataTypeServiceImpl dictDataTypeService = new DictDataTypeServiceImpl();
        DictDatatype dictDatatype = JSONObject.parseObject(JSONObject.toJSONString(req.getMsgBody()), DictDatatype.class);
        int id = dictDataTypeService.updateByPrimaryKey(dictDatatype);
        msgBody = "{\"id\":" + id + " }";
        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    public MsgResponse deleteDictDataTypeById(MsgRequest req, RestHandler handler) {
        DictDataTypeServiceImpl dictDataTypeService = new DictDataTypeServiceImpl();
        //DictDatatypeExample dictDatatypeExample = new DictDatatypeExample();
        Integer dataInnerId = req.msgBody.getInteger("dataInnerId");
        if (dataInnerId != null) {
            //dictDatatypeExample.createCriteria().andDataInnerIdEqualTo(dataInnerId);
            int dd = dictDataTypeService.deleteByPrimaryKey(dataInnerId);
            msgBody = "{\"dataInnerId\":" + dd + "}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }

        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
    }

    public MsgResponse selectDatatype(MsgRequest req, RestHandler handler) {
        DictDataTypeServiceImpl dictDataTypeService = new DictDataTypeServiceImpl();
        DictDatatype dictDatatype = JSONObject.parseObject(JSONObject.toJSONString(req.getMsgBody()), DictDatatype.class);
        List<DictDatatype> lst = dictDataTypeService.selectDatatype( );
        msgBody = "{\"list\":" +JSONObject.toJSONString(lst) + " }";
        return handler.buildMsg(retcode, retmsg, msgBody);

    }



}
