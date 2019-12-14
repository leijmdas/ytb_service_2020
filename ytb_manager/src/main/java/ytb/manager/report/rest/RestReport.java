package ytb.manager.report.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.report.rest.impl.Report;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.report.rest
 * Author: ZCS
 * Date: Created in 2018/12/26 16:30
 */
@RestController
@RequestMapping("/rest/reportManager")
public class RestReport implements IRestProcess {
    @RequestMapping(value = "sysReport", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String sysReport(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }

    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        if (handler.req.cmdtype.equals("reportList")) {
            return new Report().process(handler);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);
    }



}
