package ytb.log.notify.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ytb.log.notify.rest.impl.TaskLog;
import ytb.log.notify.rest.impl.TaskLogTimer;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;


@RestController
@RequestMapping("/rest")
@SpringBootApplication
@Scope("prototype")
public class RestTaskLog extends RestHandler {
	
	//任务通知接口
	@RequestMapping(value = "/taskLog_task", produces = "application/json;charset=UTF-8")

	@ResponseBody
	public String taskLog(@RequestBody String data) {

		try {
			parseRequest(data);
			resp = process();
		} catch (YtbError e) {
			e.printStackTrace();
			resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
		} catch (Exception e) {
			e.printStackTrace();
			YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
			resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
		}
		return resp.toJSONStringPretty();

	}
	
	protected MsgResponse process() throws Exception {

		if(req.cmdtype.equals("tasklog")){
			 return new TaskLog().process(req,this);
		} else if(req.cmdtype.equals("tasklogTimer")){
			return new TaskLogTimer().process(req,this);
		}
	 	throw new YtbError(YtbError.CODE_INVALID_REST);
	}

}
