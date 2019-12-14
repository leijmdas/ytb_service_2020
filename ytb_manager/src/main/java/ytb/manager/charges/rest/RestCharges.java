package ytb.manager.charges.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.charges.rest.impl.DistArea;
import ytb.manager.charges.rest.impl.DistAreaSalary;

import ytb.manager.charges.rest.impl.ServiceFeeRest;
import ytb.manager.charges.rest.impl.ServiceThirdPartyRest;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestCharges extends RestHandler {

    @PostMapping(value = "charges", produces = "application/json;charset=UTF-8")
    public String chargeRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        try {
            parseRequest(data);
            resp = process(request, response);
        } catch (YtbError e) {
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);

    }


    protected MsgResponse process(HttpServletRequest request, HttpServletResponse response) {

        if (req.cmdtype.equals("DistArea")) {
            return new DistArea().process(req, this);
        }else
        if (req.cmdtype.equals("DistAreaSalary")) {
            return new DistAreaSalary().process(req, this);
        }else
        if (req.cmdtype.equals("serviceFee")) {
            return new ServiceFeeRest().process(req, this);
        }else
        if (req.cmdtype.equals("thirdParty")) {
            return new ServiceThirdPartyRest().process(req, this);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }

}
