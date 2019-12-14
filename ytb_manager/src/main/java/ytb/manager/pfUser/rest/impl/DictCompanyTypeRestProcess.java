package ytb.manager.pfUser.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.pfUser.model.Dict_CompanyType;
import ytb.manager.pfUser.service.impl.DictCompanyTypeService;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: ljm
 * Date: Created in 2018/11/11
 */
public class DictCompanyTypeRestProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody;

    public MsgResponse process(MsgHandler handler) {

        if (handler.req.cmd.equals("getDictCompanyTypeTree")) {
            List<Dict_CompanyType> lst = new DictCompanyTypeService().selectTree();
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(lst));

        } else if (handler.req.cmd.equals("getDictCompanyTypeListByID")) {
            Integer companType = handler.req.getMsgBody().getInteger("companyType");
            List<Dict_CompanyType> lst = new DictCompanyTypeService().getDictCompanyTypeListByID( companType );
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(lst));

            return handler.resp;
        } else if (handler.req.cmd.equals("deleteDictCompanyType")) {
            Integer companType = handler.req.getMsgBody().getInteger("companyType");
            new DictCompanyTypeService().deleteDictCompanyType(companType);

        } else if (handler.req.cmd.equals("updateDictCompanyType")) {
            Dict_CompanyType dct = JSON.toJavaObject(handler.req.getMsgBody(), Dict_CompanyType.class);
            new DictCompanyTypeService().updateDictCompanyType(dct);

        } else if (handler.req.cmd.equals("insertDictCompanyType")) {
            Dict_CompanyType dct = JSON.toJavaObject(handler.req.getMsgBody(), Dict_CompanyType.class);
            new DictCompanyTypeService().insertDictCompanyType(dct);
        }
        else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }
        handler.resp.setRetcode(0);
        handler.resp.setRetmsg("成功");
        return handler.resp;


    }


}
