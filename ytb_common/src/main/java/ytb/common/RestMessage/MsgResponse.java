package ytb.common.RestMessage;

import com.alibaba.fastjson.JSONObject;

public final class MsgResponse extends MsgRequest {
	transient public String token;
	transient public long reqtime;
	transient public String apiKey;
	transient public String sign;
	public long restime;
	int retcode;
	String retmsg;

	public long getRestime() {
		return restime;
	}

	public void setRestime(long restime) {
		this.restime = restime;
	}

	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public MsgResponse(MsgRequest req) {
		this.token = req.token;
		this.reqtime = req.reqtime;
		this.seqno = req.seqno;
		this.cmdtype = req.cmdtype;
		this.cmd = req.cmd;
		this.retcode = 0;
		this.retmsg = "成功";
	}

	public MsgResponse() {

	}

	public void success(JSONObject retBody) {
		buildMsg(0, "成功", retBody);
	}
	public void success(String retBody) {
		buildMsg(0, "成功", JSONObject.parseObject(retBody));
	}

	public void failure(int retcode, String retmsg) {
		buildMsg(retcode, retmsg, JSONObject.parseObject("{}"));
	}

	public void buildMsg(int retcode, String retmsg, String retBody) {
		buildMsg(retcode, retmsg, JSONObject.parseObject(retBody));
	}
	
	public void buildMsg(int retcode, String retmsg, JSONObject retBody) {
		
		this.retcode = retcode;
		this.retmsg = retmsg;
		this.msgBody = retBody;
		this.restime = System.currentTimeMillis();

	}

	public static MsgResponse parseResponse(String sresp){
		return JSONObject.parseObject(sresp,MsgResponse.class);
	}
}
