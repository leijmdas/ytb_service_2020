package ytb.manager.webpagemng.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.manager.webpagemng.model.CusServiceQuestionType;
import ytb.manager.webpagemng.service.CusServiceQuestionService;
import ytb.manager.webpagemng.service.CusServiceQuestionTypeService;
import ytb.manager.webpagemng.service.impl.CusServiceQuestionServiceImpl;
import ytb.manager.webpagemng.service.impl.CusServiceQuestionTypeServiceImpl;
import ytb.manager.webpagemng.utils.PageData;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.util.Date;

/**
 * @author lxz 2018/12/22 16:31
 */
public class CustomerServiceCenter {

    public MsgResponse process(MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String retMsgBody = "{}";

        MsgRequest req = handler.req;
        JSONObject reqMsgBody = req.msgBody;

        CusServiceQuestionTypeService cusServiceQuestionTypeService = new CusServiceQuestionTypeServiceImpl();
        CusServiceQuestionService cusServiceQuestionService = new CusServiceQuestionServiceImpl();

        switch (req.cmd) {
            case "addQueType": {
                CusServiceQuestionType questionType = JSON.toJavaObject(reqMsgBody, CusServiceQuestionType.class);
                questionType.setCreateTime(new Date());
                LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(req.token);
                LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson(), LoginSsoJson.class);
                questionType.setCreateBy(loginSsoJson.getUserId());
                CusServiceQuestionType cusServiceQuestionType = cusServiceQuestionTypeService.add(questionType);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(cusServiceQuestionType);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", jsonArray);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "removeQueType": {
                int id = reqMsgBody.getInteger("id");
                id = cusServiceQuestionTypeService.removeById(id);
                retMsgBody = "{\"list\":[{\"id\":" + id + "}]}";
                break;
            }
            case "updateQueType": {
                CusServiceQuestionType questionType = JSON.toJavaObject(reqMsgBody, CusServiceQuestionType.class);
                CusServiceQuestionType updatedQueType = cusServiceQuestionTypeService.modify(questionType);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(updatedQueType);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", jsonArray);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "selQueTypeListTree": {
                CusServiceQuestionType questionType = JSON.toJavaObject(reqMsgBody, CusServiceQuestionType.class);
                JSONArray rootNodes = cusServiceQuestionTypeService.selectList(questionType);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", rootNodes);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "addQuestion": {
                CusServiceQuestion question = JSON.toJavaObject(reqMsgBody, CusServiceQuestion.class);
                question.setCreateTime(new Date());
                LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(req.token);
                LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson(), LoginSsoJson.class);
                question.setCreateBy(loginSsoJson.getUserId());
                cusServiceQuestionService.add(question);
                break;
            }
            case "removeQuestion": {
                int qid = reqMsgBody.getInteger("qid");
                cusServiceQuestionService.removeByQid(qid);
                break;
            }
            case "updateQuestion": {
                CusServiceQuestion question = JSON.toJavaObject(reqMsgBody, CusServiceQuestion.class);
                cusServiceQuestionService.modify(question);
                break;
            }
            case "questionPageData": {
                CusServiceQuestion question = reqMsgBody.getObject("question", CusServiceQuestion.class);
                int pageNo = reqMsgBody.getIntValue("pageNo");
                int limit = reqMsgBody.getIntValue("limit");
                PageData<CusServiceQuestion> pageData = cusServiceQuestionService.pageQuery(question, pageNo, limit);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", pageData);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            default: {
                retcode = 1000;
                retmsg = "fail";
            }
        }
        return handler.buildMsg(retcode, retmsg, retMsgBody);
    }
}
