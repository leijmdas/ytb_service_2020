package ytb.common;

import com.alibaba.fastjson.JSONObject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.view.model.ProjectChangeResult;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

public class FlowChange extends Flow {
    String tokenPa, tokenPb, tokenPm;
    int projectId, talkId;

    @Override
    public int getProjectId() {
        return projectId;
    }

    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }


    public ProjectChangeResult paApplyChange(MsgRequest req) {
        req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "paApplyChange";
        req.msgBody.fluentPut("changeType", ProjectConstState.CHNAGE_TYPE_SMALL);
        req.msgBody.fluentPut("projectId", projectId);
        req.msgBody.fluentPut("talkId", talkId);

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);

        checkEQ(0, resp.getRetcode());


        req.cmdtype = "projectFlow";
        req.cmd = "paAgreeChange";
        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        JSONObject result = resp.getMsgBody().getJSONObject("list");
        ProjectChangeResult changeResult=JSONObject.parseObject(YtbUtils.toJSONString(result),ProjectChangeResult.class);

        return changeResult;
    }

    public MsgResponse computeChange(MsgRequest req, ProjectChangeResult result) {
        req.token=tokenPb;
        computeChange(req, result, ProjectConst.Template_TYPE_req);
        return computeChange(req, result, ProjectConst.Template_TYPE_workplan);

    }

    public MsgResponse computeChange(MsgRequest req, ProjectChangeResult result, int docType) {
        req.cmdtype = "projectFlow";
        req.cmd = "computeChange";

        req.msgBody = JSONObject.parseObject("{\"docType\":100,\"oldTalkId\":5334,\"talkId\":5353}");
        req.msgBody.fluentPut("docType", docType);
        req.msgBody.fluentPut("talkId", result.getNewTalkId());
        //req.msgBody.fluentPut("newTalkId", result.getNewTalkId());
        req.msgBody.fluentPut("oldTalkId", result.getOldTalkId());


        String ret  = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp  = MsgResponse.parseResponse(ret );
        checkEQ(0, resp .getRetcode());
        System.out.println(resp);
        return resp;
    }

    public void pbSubmitPaAudit(MsgRequest req,ProjectChangeResult result ) {
        req.token = tokenPb;
        req.cmdtype = "projectFlow";
        req.cmd = "pbSubmitChange";
        req.msgBody.fluentPut("talkId", talkId);
        req.msgBody.fluentPut("newTalkId", result.getNewTalkId());

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

        req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "paAuditChange";
        req.msgBody.fluentPut("talkId", talkId);
        req.msgBody.fluentPut("newTalkId", result.getNewTalkId());
        req.msgBody.fluentPut("status", 0);

        ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());

        System.out.println(resp);
    }

    public void payChange(MsgRequest req,ProjectChangeResult result){
        req.token = tokenPa;
        req.cmdtype = "projectFlow";
        req.cmd = "paPayChange";
        //req.cmd = "paPayChangeClose";
        req.msgBody.fluentPut("talkId", result.getNewTalkId());
        req.msgBody.fluentPut("password", "");

        String ret = httpclient.post(url_projectCenter, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp.toJSONStringPretty());
    }

    public String getTokenPa() {
        return tokenPa;
    }

    public void setTokenPa(String tokenPa) {
        this.tokenPa = tokenPa;
    }

    public String getTokenPb() {
        return tokenPb;
    }

    public void setTokenPb(String tokenPb) {
        this.tokenPb = tokenPb;
    }

    public String getTokenPm() {
        return tokenPm;
    }

    public void setTokenPm(String tokenPm) {
        this.tokenPm = tokenPm;
    }


    public void setHttpclient(HttpClientNode httpclient) {
        this.httpclient = httpclient;
    }

}
