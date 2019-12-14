package ytb.log.smslog.rest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ytb.log.smslog.rest.impl.SmsLog;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

/*rest  base class*/
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestSmsLog extends RestHandler {

	//任务通知接口
	@RequestMapping(value = "smsLog_sms", produces = {"Application/json;charset=UTF-8"})
	@ResponseBody
	public String rest(@RequestBody String data) {

		System.out.println(data);
		parseRequest(data);
		resp=process();
		return new Gson().toJson(resp);
		//return  super.rest(data);
	}
	
	protected MsgResponse process() {

		 if(req.cmdtype.equals("smslog")){

			return new SmsLog().process(req,this);
		}
	 	return buildMsg(1, "ok", "{\"msg\":\"cmdtype参数有问题\"}");
	}
//
	public static void main(String[] args) {
		MsgRequest req = new MsgRequest();
		req.token = "100af477ba724d7aa84f69cd94cb2889";
		req.reqtime = System.currentTimeMillis();// DateFormat(new Date());
		req.seqno = System.currentTimeMillis();
		req.cmdtype = "smslog";
		req.cmd = "checkSmsCode";
		req.msgBody = JSONObject.parseObject("{\"phone\":18270054570,\"smsCode\":\"342304\"}");
		System.out.println(new Gson().toJson(req));
		System.out.println(new RestSmsLog().rest(new Gson().toJson(req)));

	}
	
}
