package ytb.common.test.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.rest.IRestProcess;
import ytb.common.context.service.impl.DefaultUserContext;
import ytb.common.context.model.YtbError;
import ytb.common.test.demo.IStudent;
import ytb.common.test.demo.Student;
import ytb.common.test.rest.impl.PrjType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*rest  base class*/
@RestController
@RequestMapping("/rest/service")
@Scope("prototype")
public final class RestDemo implements IRestProcess {
    @Autowired
	Student student;

	@PostMapping(value = "demoHandler",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String restMsg(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler(new DefaultUserContext()).parseRequest(this, data, request, response);

    }

	@PostMapping(value = "demoModel")
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

		if (msgHandler.req.cmdtype.equals("demoModel1")) {
			return new PrjType().process(msgHandler.req, msgHandler);
		} else if (msgHandler.req.cmdtype.equals("demoModel2")) {
			return new PrjType().process(msgHandler.req, msgHandler);
		}else if (msgHandler.req.cmdtype.equals("student")) {
			return processNew(msgHandler.req, msgHandler);
		}
		throw new YtbError(YtbError.CODE_INVALID_REST);

	}
	String msgBody;
	public MsgResponse processNew(MsgRequest req, MsgHandler handler) {


		if (req.cmd.equals("select")) {
			//int id=req.msgBody.getInteger("id");
			msgBody = student.toString();
			System.out.print(msgBody);
		}
		return handler.buildMsg(0,  "ok", msgBody);

	}

	@Override
	public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
}
