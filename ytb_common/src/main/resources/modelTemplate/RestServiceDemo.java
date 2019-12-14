//package ytb.common.testcase.Rest;


import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

/*rest  base class*/
@RestController
@RequestMapping("/rest/service")
@Scope("prototype")
public class RestServiceDemo {

	@RequestMapping(value = "demoModel")
	public String rest(@RequestBody String data) {
		MsgHandler msgHandler = new MsgHandler();
		try {
			msgHandler.parseRequest(data);
			process(msgHandler);
		} catch (YtbError e) {
			e.printStackTrace();
			msgHandler.buildMsg(e.getRetcode(), e.getMsg(), "{}");
		} catch (Exception e) {
			e.printStackTrace();
			YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
			msgHandler.buildMsg(ye.getRetcode(), e.getMessage(), "{}");
		}
		//msgHandler.resp.msgBody.put("hash", this.hashCode());
		return msgHandler.resp.toJSONString();
	}

	protected MsgResponse process(MsgHandler msgHandler) {

		if (msgHandler.req.cmdtype.equals("demoModel")) {
			return new  RestProcess().process(msgHandler.req, msgHandler);
		} else if (msgHandler.req.cmdtype.equals("demoModel")) {
			return new RestProcess().process(msgHandler.req, msgHandler);
		}
		throw new YtbError(YtbError.CODE_INVALID_REST);

	}

}
