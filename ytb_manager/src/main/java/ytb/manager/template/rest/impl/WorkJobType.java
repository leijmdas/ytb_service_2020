package ytb.manager.template.rest.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.tagtable.service.tagtemplate.DocumentRefService;
import ytb.manager.template.model.*;
import ytb.manager.template.service.TemplateService;
import ytb.manager.template.service.WorkJobTypeService;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: LZZ
 * Date: Created in 2018/8/14 16:21
 */

public class WorkJobType {


    public MsgResponse process(MsgRequest req, RestHandler handler) {
        WorkJobTypeService workJobTypeService = ManagerSrvContext.getInst().getWorkJobTypeService();
        TemplateService templateService = ManagerSrvContext.getInst().getTemplateService();

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        if (req.cmd.equals("spFindAllTemplateByWorkjobType")) {

            Integer workJobTypeId = req.msgBody.getInteger("workJobTypeId");
            Integer projectId = req.msgBody.getInteger("projectId");
            if (workJobTypeId == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "workJobTypeId is null");
            }
            if (projectId == null) {
                projectId = 0;
            }
            List<Dict_TemplateModel> lst = DocumentRefService.getDocumentRefService().spFindAllTemplateByWorkjobType(workJobTypeId, projectId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
        }

        //查询岗位分类列表
        else if (req.cmd.equals("getWorkJobTypeList")) {
            List<Dict_WorkJobTypeModel> workJobTypeList = workJobTypeService.getWorkJobTypeList();
            JSONObject r = new JSONObject();
            r.put("list", workJobTypeList);
            msgBody = r.toJSONString();
        }

        //修改岗位类别状态
        else if(req.cmd.equals("modifyState")){
            Integer workJobTypeId = req.msgBody.getInteger("workJobTypeId");
            Integer state = req.msgBody.getInteger("state");
            if(workJobTypeId==null||state==null){
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG,"WorkJobTypeId or state is null");
            }
//            Dict_WorkJobTypeModel dw=workJobTypeService.getWorkJobType(WorkJobTypeId);
//            Integer oldStae= dw.getState();//项目分类状态 0--草稿 1--发布 2--停止发布
//            if(oldStae.equals("1"))
//                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG,"发布状态下不能修改岗位类别信息");
            judgeStateById(workJobTypeId);
            workJobTypeService.modifyStateById(workJobTypeId,state);
        }

        //添加岗位分类
        else if (req.cmd.equals("addWorkJobType")) {
            Dict_WorkJobTypeModel workJobTypeModel = JSON.toJavaObject(req.msgBody, Dict_WorkJobTypeModel.class);
            workJobTypeService.addWorkJobType(workJobTypeModel);
        }
        //修改岗位分类
        else if (req.cmd.equals("modifyWorkJobType")) {
            Dict_WorkJobTypeModel workJobTypeModel = JSON.toJavaObject(req.msgBody, Dict_WorkJobTypeModel.class);
            judgeStateById(workJobTypeModel.getWorkJobTypeId());
            workJobTypeService.modifyWorkJobType(workJobTypeModel);
        }
        //删除岗位分类
        else if (req.cmd.equals("removeWorkJobType")) {
            Integer workJobTypeId = req.msgBody.getInteger("workJobTypeId");
            if (workJobTypeId == null) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "workJobTypeId is null");
            }
            judgeStateById(workJobTypeId);
            workJobTypeService.delWorkJobType(workJobTypeId);
        }
        //查询岗位分类
        else if (req.cmd.equals("getWorkJobType")) {
            int workJobTypeId = Integer.parseInt(req.msgBody.getString("workJobTypeId"));
            Dict_WorkJobTypeModel workJobType = workJobTypeService.getWorkJobType(workJobTypeId);
            Object json = JSONObject.toJSON(workJobType);
            msgBody = json.toString();
        }

        //查询岗位列表
        else if (req.cmd.equals("getWorkJobDetailsInfoList")) {
            Integer workJobTypeId = req.msgBody.getInteger("workJobTypeId");
            if (workJobTypeId == null) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "workJobTypeId is null");
            }
            List<Dict_WorkJobModel> workJobList = workJobTypeService.getWorkJobDetailsInfoList(workJobTypeId);
            JSONObject r = new JSONObject();
            r.put("list", workJobList);
            msgBody = r.toJSONString();
        }
        //添加岗位
        else if (req.cmd.equals("addWorkJob")) {
            Dict_WorkJobModel workJobModel = JSON.toJavaObject(req.msgBody, Dict_WorkJobModel.class);
            workJobModel.setCreateBy(handler.getUserContext().getLoginSso().getUserId
                    ().intValue());
            workJobTypeService.addWorkJob(workJobModel);
        }
        //删除岗位
        else if (req.cmd.equals("removeWorkJob")) {
            Integer workJobId = req.msgBody.getInteger("workJobId");
            if (workJobId == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "workJobId is null");
            }
            workJobTypeService.delWorkJob(workJobId);
        }
        //修改岗位
        else if (req.cmd.equals("modifyWorkJob")) {
            Dict_WorkJobModel workJobModel = JSON.toJavaObject(req.msgBody, Dict_WorkJobModel.class);
            workJobTypeService.modifyWorkJob(workJobModel);
        }
        //查询岗位
        else if (req.cmd.equals("getWorkJob")) {
            int WorkJobId = Integer.parseInt(req.msgBody.getString("workJobId"));
            Dict_WorkJobModel w = workJobTypeService.getWorkJob(WorkJobId);
            Object json = JSONObject.toJSON(w);

            msgBody = json.toString();
        }
        //查询工作任务列表
        else if (req.cmd.equals("getTaskDetailsInfoList")) {
            int workJobId = req.msgBody.getInteger("workJobId");
            List<Dict_TaskModel> taskList = workJobTypeService.getTaskList(workJobId);
            JSONObject r = new JSONObject();
            r.put("list", taskList);
            msgBody = r.toJSONString();
        }
        //添加工作任务
        else if (req.cmd.equals("addTask")) {
            Dict_TaskModel task = JSON.toJavaObject(req.msgBody, Dict_TaskModel.class);
            workJobTypeService.addTask(task);
        }
        //删除工作任务
        else if (req.cmd.equals("removeTask")) {
            Integer taskId = req.msgBody.getInteger("taskId");
            if (taskId == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "taskId is null");
            }
            workJobTypeService.delTask(taskId);
        }
        //修改工作任务
        else if (req.cmd.equals("modifyTask")) {
            Dict_TaskModel task = JSON.toJavaObject(req.msgBody, Dict_TaskModel.class);
            workJobTypeService.modifyTask(task);
        }
        //查询工作任务
        else if (req.cmd.equals("getTask")) {
            int TaskId = Integer.parseInt(req.msgBody.getString("taskId"));
            Dict_TaskModel taskModel = workJobTypeService.getTask(TaskId);
            Object json = JSONObject.toJSON(taskModel);
            msgBody = json.toString();
        }

        //根据岗位ID获取模板详细信息
        else if (req.cmd.equals("getDocTemplateDetailsInfo")) {
            Integer workJobId = req.msgBody.getInteger("workJobId");
            JSONArray docTypeJsonArr = req.msgBody.getJSONArray("docTypeArr");
            if (workJobId == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "workJobId is null");
            }
            Integer[] docTypeArr = docTypeJsonArr.toArray(new Integer[docTypeJsonArr.size()]);
            List<Dict_TemplateModel> docTemplateList = templateService.getDocTemplateList(0, workJobId, docTypeArr);
            JSONObject r = new JSONObject();
            r.put("list", docTemplateList);
            msgBody = r.toJSONString();
        }
        //添加模板
        else if (req.cmd.equals("addDocTemplate")) {
            Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
            templateModel.setRepositoryId(0);
            templateService.addTemplate(templateModel);
        }
        //修改模板
        else if (req.cmd.equals("modifyDocTemplate")) {
            Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
            templateService.modifyTemplate(templateModel);
        }
        //删除模板
        else if (req.cmd.equals("removeDocTemplate")) {
            int templateId = Integer.parseInt(req.msgBody.getString("templateId"));
            templateService.delTemplate(templateId);
        }
        //根据岗位查询模板列表
        else if (req.cmd.equals("getDocTemplateList")) {
            int WorkJobTypeId = Integer.parseInt(req.msgBody.getString("workJobtypeId"));
            int docType = Integer.parseInt(req.msgBody.getString("docType"));

            Dict_TemplateModel dict_templateModel = new Dict_TemplateModel();
            dict_templateModel.setWorkJobId(WorkJobTypeId);
            dict_templateModel.setRepositoryId(0);
            dict_templateModel.setDocType(docType);
            List<Dict_TemplateModel> templateList = workJobTypeService.getTemplateList(dict_templateModel);

            msgBody = "{'list':" + YtbUtils.toJSONString(templateList) + "}";
        }
        //获取模板
        else if (req.cmd.equals("getDocTemplate")) {
            int TemplateId = Integer.parseInt(req.msgBody.getString("templateId"));
            Dict_TemplateModel t = workJobTypeService.getTemplate(TemplateId);
            Object json = JSONObject.toJSON(t);
            msgBody = json.toString();
        }
        //获取文档模板详细数据
        else if (req.cmd.equals("getDocTemplateDetails")) {
            int templateId = req.msgBody.getInteger("templateId");
            Dict_TemplateModel templateDetails = workJobTypeService.getDocTemplateDetails(templateId);
            msgBody = JSON.toJSONString(templateDetails);
        }
        //修改文档模板内容
        else if (req.cmd.equals("modifyTemplateDocument")) {
            int documentId = req.msgBody.getInteger("documentId");
            String document = req.msgBody.getString("document");
            workJobTypeService.modifyTemplateNewDoc(documentId, document);
        }

        //查询真值约束列表
        else if (req.cmd.equals("getConstList")) {
            int WorkJobTypeId = Integer.parseInt(req.msgBody.getString("workJobTypeId"));
            List<Dict_WorkJob_Check> list = workJobTypeService.getConstraintList(WorkJobTypeId);

            msgBody = "{\"list\":" + YtbUtils.toJSONString(list) + "}";
        }
        //添加真值约束
        else if (req.cmd.equals("addConst")) {
            Dict_WorkJob_Check cons = JSON.toJavaObject(req.msgBody, Dict_WorkJob_Check.class);
            workJobTypeService.addConstList(cons);
        }
        //删除真值约束
        else if (req.cmd.equals("removeConst")) {
            int ConstraintId = Integer.parseInt(req.msgBody.getString("constraintId"));
            workJobTypeService.delConstList(ConstraintId);
        }
        //修改真值约束
        else if (req.cmd.equals("modifyConst")) {
            Dict_WorkJob_Check cons = JSON.toJavaObject(req.msgBody, Dict_WorkJob_Check.class);
            workJobTypeService.modifyConstList(cons);
        }
        //查询真值约束
        else if (req.cmd.equals("getConst")) {
            int ConstraintId = Integer.parseInt(req.msgBody.getString("constraintId"));
            Dict_WorkJob_Check cons = workJobTypeService.getConstList(ConstraintId);
            Object json = JSONObject.toJSON(cons);
            msgBody = json.toString();
        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }
        return handler.buildMsg(retcode, retmsg, msgBody);

    }

    //岗位类别状态判断
    public void judgeStateById(int WorkJobTypeId){
        WorkJobTypeService workJobTypeService = ManagerSrvContext.getInst().getWorkJobTypeService();
        Dict_WorkJobTypeModel dw=workJobTypeService.getWorkJobType(WorkJobTypeId);
        Integer oldStae= dw.getState();//项目分类状态 0--草稿 1--发布 2--停止发布
        if(oldStae.equals(1)) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "发布状态下不能修改岗位类别信息");
        }
    }


}
