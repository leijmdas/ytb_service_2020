package ytb.project.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.account.cashpay.wechat.service.WeCharNotifyService;
import ytb.project.rest.impl.InvoiceRest;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

 */
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestSysInvoice extends RestHandler {

    @RequestMapping(value = "projectInvoice", produces = {"Application/json;charset=UTF-8"})
    public String restSysMetaData(@RequestBody String data, HttpServletRequest
            request, HttpServletResponse response) {

        try {
            parseRequest(data);
            resp = process(request, response);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
    }


    ///@RequestMapping(value = "weChatNotify")
    public String weChatNotify(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {


        WeCharNotifyService weCharNotifyService = new WeCharNotifyService();

        return weCharNotifyService.weChatNotify(data);


    }


    protected MsgResponse process(HttpServletRequest request, HttpServletResponse response) {

        if (req.cmdtype.equals("invoice")) {

            return new InvoiceRest().process(req, this, request, response);

        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }


}
