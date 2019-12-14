package ytb.project.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.webpagemng.model.CurServiceHotSearch;
import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.manager.webpagemng.model.CusServiceQuestionType;
import ytb.manager.webpagemng.service.CusHotDoService;
import ytb.manager.webpagemng.service.CusServiceQuestionService;
import ytb.manager.webpagemng.service.CusServiceQuestionTypeService;
import ytb.manager.webpagemng.service.impl.CusServiceQuestionServiceImpl;
import ytb.manager.webpagemng.service.impl.CusServiceQuestionTypeServiceImpl;
import ytb.manager.webpagemng.utils.PageData;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * @author lxz 2018/12/25 13:43
 */
public class CusServiceCenter {

    public MsgResponse process(MsgHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String retMsgBody = "{}";

        MsgRequest req = handler.req;
        JSONObject reqMsgBody = req.msgBody;

        CusServiceQuestionTypeService cusServiceQuestionTypeService = new CusServiceQuestionTypeServiceImpl();
        CusServiceQuestionService cusServiceQuestionService = new CusServiceQuestionServiceImpl();
        CusHotDoService cusHotDoService = new CusHotDoService();

        switch (req.cmd) {
            case "selQueTypeListTree": {//查询问题分类树
                CusServiceQuestionType questionType = JSON.toJavaObject(reqMsgBody, CusServiceQuestionType.class);
                JSONArray rootNodes = cusServiceQuestionTypeService.selectList(questionType);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", rootNodes);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "hotKeyWordSelect": //热词搜索
            case "questionPageData": {//分页查询问题列表
                CusServiceQuestion question = reqMsgBody.getObject("question", CusServiceQuestion.class);
                int pageNo = reqMsgBody.getIntValue("pageNo");
                int limit = reqMsgBody.getIntValue("limit");
                PageData<CusServiceQuestion> pageData;
                if (req.cmd.equals("questionPageData")) {
                    pageData = cusServiceQuestionService.pageQuery(question, pageNo, limit);
                } else {
                    pageData = cusServiceQuestionService.pageQueryHotSearch(question, pageNo, limit);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", pageData);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "selQuestion": {//查询单个问题
                Integer qid = reqMsgBody.getInteger("qid");
                if (qid == null) {
                    throw new YtbError(YtbError.REQ_PARAMETER_NULL);
                }
                CusServiceQuestion cusServiceQuestion = new CusServiceQuestion();
                cusServiceQuestion.setQid(qid);
                CusServiceQuestion question = cusServiceQuestionService.selectOne(cusServiceQuestion);

                JSONArray jsonArray = new JSONArray();
                jsonArray.add(question);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", jsonArray);
                retMsgBody = jsonObject.toJSONString();
                break;
            }
            case "hotKeyWordList": {//查询热词列表
                Integer dataTotal = req.msgBody.getInteger("dataTotal");
                if (dataTotal == null) {
                    throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "msgBody parameter dataTotal no exist");
                }
                List<CurServiceHotSearch> curServiceHotSearches = cusHotDoService.selectByHot("", dataTotal);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", curServiceHotSearches);
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
