package ytb.common;

import com.alibaba.fastjson.JSONObject;
import com.jtest.testframe.ITestImpl;
import ytb.common.utils.YtbUtils;
import ytb.common.RestMessage.MsgResponse;

public class CheckResp extends ITestImpl {
    MsgResponse resp;

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }

    public int getWorkplan_id() {
        return workplan_id;
    }

    public void setWorkplan_id(int workplan_id) {
        this.workplan_id = workplan_id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    int req_id;//= c.getResp().msgBody.getInteger("reqDocumentId");
    int workplan_id;
    int projectId;

    public MsgResponse getResp() {
        return resp;
    }

    public void setResp(MsgResponse resp) {
        this.resp = resp;
    }

    //"msgBody":{
//        "result":{
//            "phase":200,
//                    "docType":0,
//                    "groupId":594,
//                    "params":{
//                      "taxRate":0.01,
//                        "feeRate":0.1
//                           },
//                  "templateId":0,
//                    "userId":123,
//                    "reqId":2120,
//                    "templateWorkplan":false,
//                    "repositoryId":0,
//                    "documentId":2121,
//                    "workplanId":2121,
//                    "talkId":766,
//                    "projectId":462,
//                    "templateReq":false
//        }
    public CheckResp parseProjectReqWorkplanId() {
        JSONObject result = resp.getMsgBody().getJSONObject("result");
        req_id = result.getInteger("reqId");
        workplan_id = result.getInteger("workplanId");

        projectId = result.getInteger("projectId");
        return this;

    }

    public CheckResp(String ret) {
        resp = MsgResponse.parseResponse(ret);
        System.out.println(resp);
    }

    public void checkRetcode(int retCode) {
        checkEQ(retCode, resp.getRetcode());
    }

    public void checkListSize(int size) {
        checkEQ(size, resp.msgBody.getJSONArray("list").size());
    }

    public String toString() {
        return YtbUtils.toJSONStringPretty(this);
    }
}
