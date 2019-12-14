package ytb.manager.webpagemng.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.webpagemng.rest.impl.CustomerHotServiceCenter;
import ytb.manager.webpagemng.rest.impl.CustomerServiceCenter;
import ytb.manager.webpagemng.rest.impl.PageResourceMng;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lxz 2018/12/15 17:27
 */
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestWebPageMng implements IRestProcess {

    @RequestMapping(value = "/webpagemng", produces = "application/json;charset=UTF-8")
    public String restWebPageMng(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }

    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (handler.req.cmdtype.equals("pageResourceMng")) {
            return new PageResourceMng().process(handler);
        } else if (handler.req.cmdtype.equals("customerHotServiceCenter")) {
            return new CustomerHotServiceCenter().process(handler);
        } else if (handler.req.cmdtype.equals("customerServiceCenter")) {
            return new CustomerServiceCenter().process(handler);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);
    }
}
