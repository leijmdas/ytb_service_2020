package ytb.manager.template.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.template.rest.impl.RestProcessDictRestRefModel;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*rest  base class*/
@RestController
@RequestMapping("/rest/service")
//@Scope("prototype")
public class RestServiceDictRestRefModel implements IRestProcess {

	@PostMapping(value = "DictRestRefModel")
	public String rest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
		return new MsgHandler().parseRequest(this, data, request, response);

	}

	public MsgResponse process(MsgHandler msgHandler, HttpServletRequest request, HttpServletResponse response) {

		if (msgHandler.req.cmdtype.equals("DictRestRefModel")) {
			return new RestProcessDictRestRefModel().process(msgHandler.req, msgHandler);
		} else if (msgHandler.req.cmdtype.equals("DictRestRefModel")) {
			return new RestProcessDictRestRefModel().process(msgHandler.req, msgHandler);
		}
		throw new YtbError(YtbError.CODE_INVALID_REST);

	}

}
