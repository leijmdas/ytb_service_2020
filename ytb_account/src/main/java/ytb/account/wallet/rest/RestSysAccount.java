package ytb.account.wallet.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ytb.account.cashpay.wechat.service.WeCharNotifyService;
import ytb.account.wallet.rest.type.*;
import ytb.common.context.rest.IRestProcess;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.DefaultUserContext;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台元数据字典模块的Rest类
 * Package: ytb.manager.metadata.rest
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestSysAccount extends RestHandler implements IRestProcess {

    @PostMapping(value = "wallet", produces = {"Application/json;charset=UTF-8"})
    public String sysAcRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler(new DefaultUserContext()).parseRequest(this, data, request, response);

//        try {
//            parseRequest(data);
//            resp = process(request, response);
//        } catch (YtbError e) {
//            e.printStackTrace();
//            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
//        } catch (Exception e) {
//            e.printStackTrace();
//            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
//            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
//        }
//        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
    }


    @PostMapping(value = "weChatNotify")
    public String weChatNotify(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {


        WeCharNotifyService weCharNotifyService = new WeCharNotifyService();

        return weCharNotifyService.weChatNotify(data);


    }

    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        MsgRequest req = handler.req;
        if (req.cmdtype.equals("userOut")) {

            return new SysAccountUserOut().process(req, handler, request, response);

        } else if (req.cmdtype.equals("userInner")) {

            return new SysAccountUserInner().process(req, handler, request, response);

        } else if (req.cmdtype.equals("userDetail")) {

            return new SysAccountUserDetail().process(req, handler, request, response);

        } else if (req.cmdtype.equals("trade")) {

            return new SysAccountTrade().process(req, handler, request, response);

        } else if (req.cmdtype.equals("pfOut")) {

            return new SysAccountPfOut().process(req, handler, request, response);

        } else if (req.cmdtype.equals("pfInner")) {

            return new SysAccountPfInner().process(req, handler, request, response);

        } else if (req.cmdtype.equals("pfDetail")) {

            return new SysAccountPfDetail().process(req, handler, request, response);

        } else if (req.cmdtype.equals("transaction")) {

            return new SysAccountUserRecharge().process(req, handler, request, response);

        } else if (req.cmdtype.equals("pfTransaction")) {

            return new SysAccountPfRecharge().process(req, handler, request, response);

        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }

//    public static void main(String[] args) {
//
//        MsgRequest req = new MsgRequest();
//
//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date());
//        req.seqno = System.currentTimeMillis();
//
//
//        req.cmdtype = "metadata";
//
//        req.cmd = "getDictList";
//        req.msgBody = JSONObject.parseObject("{\"metaDataId\":0}");
//
//
//        req.cmd = "makeTableByDictId";
//        req.msgBody = JSONObject.parseObject("{\"metaDataId\":1}");
//        //Sys_DictDataTypeModel templateModel=  req.msgBody.toJavaObject(req.msgBody,Sys_DictDataTypeModel.class);
//
//        req.cmd = "getDictList";
//        req.msgBody = JSONObject.parseObject("{\"metaDataId\":1}");
//        //Sys_DictDataTypeModel templateModel=  req.msgBody.toJavaObject(req.msgBody,Sys_DictDataTypeModel.class);
//
//        System.out.println(new Gson().toJson(req));
///*
//        System.out.println("返回" + new RestSysMetaDataManager().rest(new Gson().toJson(req)));
//
//
//        System.out.println(new RestSysMetaDataManager().rest(new Gson().toJson(req)));
//
//        System.out.println("返回的是：" + new RestSysMetaDataManager().rest(new Gson().toJson(req)));*/
//
//    }
}
