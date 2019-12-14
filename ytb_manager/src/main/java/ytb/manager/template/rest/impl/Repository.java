package ytb.manager.template.rest.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.context.ManagerSrvContext;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.manager.tagtable.service.tagtemplate.TemplateDocumentRefService;
import ytb.manager.template.model.*;
import ytb.manager.template.service.ReqItemService;
import ytb.manager.template.service.TemplateRepositoryService;
import ytb.manager.template.service.TemplateService;
import ytb.manager.template.service.WorkJobTypeService;
import ytb.manager.template.service.impl.ReqItemServiceImpl;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: LZZ
 * Date: Created in 2018/8/14 16:21
 */

public class Repository {

    private ReqItemService reqItemService = new ReqItemServiceImpl();


    public MsgResponse process(MsgRequest req, RestHandler handler) throws IOException {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        TemplateRepositoryService templateRepositoryService = ManagerSrvContext.getInst().getTemplateRepositoryService();
        WorkJobTypeService workJobTypeService = ManagerSrvContext.getInst().getWorkJobTypeService();
        TemplateDocumentService templateDocumentService = ManagerSrvContext.getInst().getTemplateDocumentService();
        TemplateService templateService = ManagerSrvContext.getInst().getTemplateService();

        LoginSso logSso = handler.getUserContext().getLoginSso();

        if (req.cmd.equals("setUpRefAll")) {
            Integer repositoryId = req.msgBody.getInteger("repositoryId");
            Integer projectId = req.msgBody.getInteger("projectId");
            try {
                new TemplateDocumentRefService().setUpRefAll(repositoryId, projectId, logSso.getUserId());
            } catch (UnsupportedEncodingException e) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
            }
            msgBody = "{}";
        } else if (req.cmd.equals("spFindAllTemplateByRepository")) {

            Integer repositoryId = req.msgBody.getInteger("repositoryId");
            Integer projectId = req.msgBody.getInteger("projectId");
            if (repositoryId == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "repositoryId is null");
            }
            if (projectId == null) {
                projectId = 0;
            }
            List<Dict_TemplateModel> templateModelList = new TemplateDocumentRefService().spFindAllTemplateByRepository(repositoryId,
                    projectId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(templateModelList) + "}";
        } else //查询模板仓库相关数据
            if (req.cmd.equals("getRepositoryData")) {
                msgBody = templateRepositoryService.getRepositoryData();
            }
            //添加模板仓库
            else if (req.cmd.equals("addRepository")) {
                Template_Repository tr = JSON.toJavaObject(req.msgBody, Template_Repository.class);
                tr.setUuid(UUID.randomUUID().toString());
                tr.setCreateBy(logSso.getUserId().intValue());
                templateRepositoryService.addTemplateRepository(tr);
            }
            //修改模板仓库
            else if (req.cmd.equals("modifyRepository")) {
                Template_Repository tr = JSON.toJavaObject(req.msgBody, Template_Repository.class);
                judgeStateById(tr.getRepositoryId());
                templateRepositoryService.modifyTemplateRepository(tr);
            }
            //修改模板仓库状态
            else if (req.cmd.equals("modifyState")) {
                Integer repositoryId = req.msgBody.getInteger("repositoryId");
                Integer state = req.msgBody.getInteger("state");
                if (repositoryId == null || state == null) {
                    throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "repositoryId or state is null");
                }
                judgeStateById(repositoryId);
                templateRepositoryService.modifyStateById(repositoryId, state);
            }
            //删除模板仓库
            else if (req.cmd.equals("removeRepository")) {
                Integer repositoryId = req.msgBody.getInteger("repositoryId");
                if (repositoryId == null) {
                    throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "repositoryId not exist");
                }
                judgeStateById(repositoryId);
                templateRepositoryService.delTemplateRepository(repositoryId);
            }
            //获取文档模板列表
            else if (req.cmd.equals("getDocTemplateList")) {
                Integer repositoryId = req.msgBody.getInteger("repositoryId");
                JSONArray docTypeJsonArr = req.msgBody.getJSONArray("docTypeArr");
                if (repositoryId == null) {
                    throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "repositoryId is null");
                }
                Integer[] docTypeArr = docTypeJsonArr.toArray(new Integer[docTypeJsonArr.size()]);
                List<Dict_TemplateModel> docTemplateList = templateService.getDocTemplateList(repositoryId, 0, docTypeArr);
                JSONObject r = new JSONObject();
                r.put("list", docTemplateList);
                msgBody = r.toJSONString();
            }
            //添加文档模板
            else if (req.cmd.equals("addDocTemplate")) {
                Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
                templateModel.setWorkJobId(0);
                templateService.addTemplate(templateModel);
            }
            //修改文档模板
            else if (req.cmd.equals("modifyDoctemplate")) {
                Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
                judgeStateById(templateModel.getRepositoryId());
                templateService.modifyTemplate(templateModel);
            }
            //删除文档模板
            else if (req.cmd.equals("removeDoctemplate")) {
                Integer templateId = req.msgBody.getInteger("templateId");
                if (templateId == null) {
                    throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "templateId is null");
                }
                templateService.delTemplate(templateId);
            }
            //修改文档模板状态
            else if (req.cmd.equals("modifyDocTemplateState")) {
                Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
                templateRepositoryService.modifyTemplateState(templateModel);
            }
            //转换文档模板Excel
            else if (req.cmd.equals("parseDocTemplate")) {
                int templateId = req.msgBody.getInteger("templateId");
                boolean res = templateDocumentService.parseExcel(templateId);
                if (!res) {
                    retcode = 3;
                    retmsg = "转换模板异常";
                }
            }
            //获取文档模板详细数据
            else if (req.cmd.equals("getDocTemplateDetails")) {
                int templateId = req.msgBody.getIntValue("templateId");
                int workJobId = req.msgBody.getIntValue("workJobId");
                int repositoryId = req.msgBody.getIntValue("repositoryId");
                Dict_TemplateModel templateDetails = templateRepositoryService.getDocTemplateDetails(templateId, workJobId, repositoryId);
                msgBody = JSON.toJSONString(templateDetails);
            }


            //获取需求因子列表
            else if (req.cmd.equals("getReqItemList")) {
                Integer templateId = req.msgBody.getInteger("templateId");
                List<Dict_Req_Item> reqItems = reqItemService.queryListByTemplateId(templateId);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", reqItems);
                msgBody = jsonObject.toString();
            }
            //修改需求因子
            else if (req.cmd.equals("modifyReqItem")) {
                Dict_Req_Item reqItem = JSON.toJavaObject(req.msgBody, Dict_Req_Item.class);
                reqItemService.modifyReqItem(reqItem);
            }
            //获取岗位列表 根据岗位分类ID
            else if (req.cmd.equals("getWorkJobListByWorkJobTypeId")) {
                Integer workJobTypeId = req.msgBody.getInteger("workJobTypeId");
                if (workJobTypeId == null) {
                    throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "workJobTypeId not exist");
                }
                List<Dict_WorkJobModel> workJobList = workJobTypeService.getWorkJobListByWorkJobTypeId(workJobTypeId);
                JSONObject r = new JSONObject();
                r.put("list", workJobList);
                msgBody = r.toString();
            }
            //获取需求岗位真值约束详细列表
            else if (req.cmd.equals("getWorkJobCheckDetailsList")) {
                int templateId = req.msgBody.getInteger("templateId");
                List<Dict_WorkJob_Check> list = workJobTypeService.getWorkJobCheckDetailsListBy(templateId);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("list", list);
                msgBody = jsonObject.toString();
            }
            //添加需求岗位真值约束
            else if (req.cmd.equals("addWorkJobCheck")) {
                Dict_WorkJob_Check workJobCheck = JSON.toJavaObject(req.msgBody, Dict_WorkJob_Check.class);
                workJobTypeService.addConstList(workJobCheck);
            }
            //修改需求岗位真值约束
            else if (req.cmd.equals("modifyWorkJobCheck")) {
                Dict_WorkJob_Check workJobCheck = JSON.toJavaObject(req.msgBody, Dict_WorkJob_Check.class);
                workJobTypeService.modifyWorkJobCheck(workJobCheck);
            }
            //删除需求岗位真值约束
            else if (req.cmd.equals("removeWorkJobCheck")) {
                Integer checkId = req.msgBody.getInteger("checkId");
                workJobTypeService.removeWorkJobCheckBy(checkId);
            } else {
                retcode = 1;
                retmsg = "没找到匹配的cmd";
            }
        return handler.buildMsg(retcode, retmsg, msgBody);

    }

    //模板仓库状态判断
    public void judgeStateById(int repositoryId){
        TemplateRepositoryService templateRepositoryService = ManagerSrvContext.getInst().getTemplateRepositoryService();
        Template_Repository tr = templateRepositoryService.getTemplateRepository(repositoryId);
        Integer oldStae= tr.getState();//项目分类状态 0--草稿 1--发布 2--停止发布
        if(oldStae.equals(1))
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG,"发布状态下不能修改模板仓库信息");
    }


}
