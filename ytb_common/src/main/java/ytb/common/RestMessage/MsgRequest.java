package ytb.common.RestMessage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class MsgRequest {
    public String token;
    public long reqtime;
    public long seqno = 1l;
    public String cmdtype;
    public String cmd;
    public String apiKey;
    public String sign;

    Boolean testFlag;

    public String buildCmdInfo() {
        return cmdtype + "::" + cmd;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Boolean testFlag) {
        this.testFlag = testFlag;
    }


    public JSONObject msgBody = JSONObject.parseObject("{}");

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getReqtime() {
        return reqtime;
    }

    public void setReqtime(long reqtime) {
        this.reqtime = reqtime;
    }

    public long getSeqno() {
        return seqno;
    }

    public void setSeqno(long seqno) {
        this.seqno = seqno;
    }

    public String getCmdtype() {
        return cmdtype;
    }

    public void setCmdtype(String cmdtype) {
        this.cmdtype = cmdtype;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public JSONObject getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(JSONObject msgBody) {
        this.msgBody = msgBody;
    }


    void parseRequest(String reqBody) {

        JSONObject json = JSONObject.parseObject(reqBody);
        token = json.getString("token");
        reqtime = json.getLong("reqtime");
        seqno = json.getLong("seqno");
        cmdtype = json.getString("cmdtype");
        cmd = json.getString("cmd");
        msgBody = json.getJSONObject("msgBody");
        if (msgBody == null) {
            msgBody = JSONObject.parseObject("{}");
        }
    }

    public static MsgRequest parse(String reqBody) {
        JSONObject reqMsg = JSON.parseObject(reqBody);
        MsgRequest r = reqMsg.toJavaObject(MsgRequest.class);
        if (r.msgBody == null) {
            r.msgBody = JSONObject.parseObject("{}");
        }
        Integer pageNo = reqMsg.getInteger("pageNo");
        Integer limit = reqMsg.getInteger("limit");
        if (pageNo != null) {
            r.msgBody.put("pageNo", pageNo);
        }
        if (limit != null) {
            r.msgBody.put("limit", limit);
        }
        return r;
    }

    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat);

    }

    public String toJSONString() {
        return JSONObject.toJSONString(this, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);

    }

    public String toJSONStringPretty() {
        return JSONObject.toJSONString(this, SerializerFeature.PrettyFormat, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);

    }
}
