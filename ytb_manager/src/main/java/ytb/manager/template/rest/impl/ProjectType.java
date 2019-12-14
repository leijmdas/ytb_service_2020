package ytb.manager.template.rest.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;

import ytb.common.utils.YtbUtils;
import ytb.manager.context.ManagerSrvContext;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Template_Repository;
import ytb.manager.template.service.ProjectTypeService;
import ytb.manager.template.service.TemplateRepositoryService;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class ProjectType {


    public MsgResponse process(MsgRequest req, RestHandler handler) throws IOException {

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";

        LoginSso loginSso = handler.getUserContext().getLoginSso();
        ProjectTypeService projectTypeService = ManagerSrvContext.getInst().getProjectTypeService();
        TemplateDocumentService templateDocumentService = ManagerSrvContext.getInst().getTemplateDocumentService();
        TemplateRepositoryService repositoryService = ManagerSrvContext.getInst().getTemplateRepositoryService();

        //查询项目分类列表
        if (req.cmd.equals("getProjTypeList")) {

            List<Dict_ProjectTypeModel> lst = projectTypeService.getProjectTypeList();
            msgBody = "{\"list\":" + YtbUtils.toJSONString(lst) + "}";
        }
        //查询项目分类信息
        else if (req.cmd.equals("getProjType")) {

            Integer projectTypeId = req.msgBody.getInteger("projectTypeId");
            Dict_ProjectTypeModel dp = projectTypeService.getProjectType(projectTypeId);
            Object json = JSONObject.toJSON(dp);
            msgBody = json.toString();

        }
        //修改项目分类信息
        else if (req.cmd.equals("modifyProjType")) {
            Dict_ProjectTypeModel p = JSON.toJavaObject(req.msgBody, Dict_ProjectTypeModel.class);
            judgeStateById(p.getProjectTypeId());
            projectTypeService.modifyProjectType(p);
        }
        //修改项目分类状态
        else if (req.cmd.equals("modifyState")) {
            Integer projectTypeId = req.msgBody.getInteger("projectTypeId");
            Integer state = req.msgBody.getInteger("state");
            if(projectTypeId==null||state==null){
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG,"projectTypeId or state is null");
            }
            judgeStateById(projectTypeId);
            projectTypeService.modifySateById(projectTypeId,state);
        }
        //添加项目分类信息
        else if (req.cmd.equals("addProjType")) {
            Dict_ProjectTypeModel ptm = JSON.toJavaObject(req.msgBody, Dict_ProjectTypeModel.class);
            ptm.setCreateBy(loginSso.getUserId().intValue());
            projectTypeService.addProjectType(ptm);
        }
        // 删除项目分类信息
        else if (req.cmd.equals("removeProjType")) {
            int projectTypeId = req.msgBody.getInteger("projectTypeId");
            judgeStateById(projectTypeId);
            projectTypeService.delProjectType(projectTypeId);
        }
        //获取项目分类详细信息列表
        else if (req.cmd.equals("getProjTypeDetailsInfoList")) {
            List<Dict_ProjectTypeModel> projTypeDetailsInfoList = projectTypeService.getProjTypeDetailsInfoList();
            msgBody = "{\"list\":" + JSON.toJSONString(projTypeDetailsInfoList) + "}";
        }
        //根据项目类别ID获取信息
        else if (req.cmd.equals("getProjTypeDetailsInfo")) {

            int projectTypeId = Integer.parseInt(req.msgBody.getString("projectTypeId"));
            JSONObject json = projectTypeService.getProjTypeDetailsInfo(projectTypeId);
            msgBody = json.toString();
        }
        //根据项目类别获取模板列表
        else if (req.cmd.equals("getDocTemplateList")) {

            Integer docType =  req.msgBody.getInteger("docType");
            Dict_TemplateModel tm = new Dict_TemplateModel();
            tm.setDocType(docType);
            tm.setWorkJobId(0);
            List<Dict_TemplateModel> templateList = projectTypeService.getTemplateList(tm);

            msgBody = "{\"list\":" + YtbUtils.toJSONString(templateList) + "}";
        }
        //根据项目类别获取模板详细信息
        else if (req.cmd.equals("getDocTemplateDetailsInfo")) {

             Dict_TemplateModel dict_templateModel = req.msgBody.toJavaObject(req.msgBody, Dict_TemplateModel.class);
            dict_templateModel.setWorkJobId(0);
            List<Dict_TemplateModel> list = projectTypeService.getDocTemplateListinfo(dict_templateModel);

            msgBody = "{\"list\":" + YtbUtils.toJSONString(list) + "}";
        }
        //删除模板
        else if (req.cmd.equals("removeDocTemplate")) {
            Integer templateId = req.msgBody.getInteger("templateId");
            if (templateId == null) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "templateId is null");
            }
            projectTypeService.delTemplate(templateId);
        }
        //添加模板
        else if (req.cmd.equals("addDocTemplate")) {
            Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
            templateModel.setWorkJobId(0);
            templateModel.setUuid(UUID.randomUUID().toString());
            projectTypeService.addTemplate(templateModel);
        }
        //修改模板
        else if (req.cmd.equals("modifyDocTemplate")) {
            Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
            projectTypeService.modifyTemplate(templateModel);
        }

        //获取模板
        else if (req.cmd.equals("getDocTemplate")) {
            int templateId =  req.msgBody.getInteger("templateId") ;
            Dict_TemplateModel t = projectTypeService.getTemplate(templateId);
            Object json = JSONObject.toJSON(t);
            msgBody = json.toString();
            return handler.success(msgBody);
        }
        //转换模板
        else if (req.cmd.equals("parseDocTemplate")) {
            int templateId = req.msgBody.getInteger("templateId");
            boolean res = templateDocumentService.parseExcel(templateId);
            if (!res) {
                retcode = 3;
                retmsg = "转换模板异常";
            }
        }
        //文档模板页面编辑保存
        else if (req.cmd.equals("modifyTemplateContentsNew")) {
            Dict_TemplateModel templateModel = JSON.toJavaObject(req.msgBody, Dict_TemplateModel.class);
            projectTypeService.modifyTemplateContentsNew(templateModel);
        }
        //获取原始文档
        else if (req.cmd.equals("getMngrReDocument")) {
            int documentId = req.msgBody.getInteger("documentId");
            projectTypeService.getMngrReDocument(documentId);
        }
        //获取项目分类列表数据
        else if (req.cmd.equals("getProjTypeListData")) {
            List<Dict_ProjectTypeModel> typeModels = projectTypeService
                    .getProjTypeDetailsInfoList();
            List<Template_Repository> repositories = repositoryService
                    .getPubTemplateRepositoryList();//返回已发布的模板仓库
            JSONObject r = new JSONObject();
            r.put("projTypeDetailsInfoList", typeModels);
            r.put("repositoryList", repositories);
            return handler.success(r);
            //msgBody = r.toJSONString();
        } else if (req.cmd.equals("selectTree")) {
            List<Dict_ProjectTypeModel> models = projectTypeService.selectTree();
            handler.resp.getMsgBody().put("list", JSONObject.toJSON(models));
            //msgBody = handler.resp.getMsgBody().toJSONString();
            return handler.success(handler.resp.getMsgBody());
        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.success(msgBody);
    }

    /**
     * 判断是否可以操作项目分类信息
      * @param projectTypeId
     */
    public void judgeStateById(int projectTypeId){
        ProjectTypeService projectTypeService = ManagerSrvContext.getInst().getProjectTypeService();
        Dict_ProjectTypeModel dim=projectTypeService.getProjectType(projectTypeId);
        Integer oldStae= dim.getState();//项目分类状态 0--草稿 1--发布 2--停止发布
        if(oldStae.equals(1))
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG,"发布状态下不能修改项目分类信息");
    }

}
