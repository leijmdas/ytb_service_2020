package ytb.project.rest.impl;


import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbUtils;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTemplateAssistModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.service.IFlowFolderView;
import ytb.project.service.template.DocumentToolService;

import ytb.project.service.template.PATemplateService;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: leijianming
 * Date: Created in 2018/8/14 16:21
 */
public final class ProjectDocumentRestProcess {

    static ProjectSrvContext getInst(){
        return ProjectSrvContext.getInst();
    }

    public MsgResponse process(MsgHandler handler) throws Exception {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";

        Integer projectId = handler.req.msgBody.getInteger("projectId");
        Integer documentId =  handler.req.msgBody.getInteger("documentId");
        //获取用户项目上下文
        UserProjectContext context= UserProjectContext.pre(handler);

        //查看文档
        if (handler.req.cmd.equals("selectDocument")) {
            IFlowFolderView flowFolder = getInst().getFlowFolderView();
            Integer templateId =  handler.req.msgBody.getInteger("templateId");
            Integer v = handler.req.msgBody.getInteger("v");

            ProjectTemplateModel templateModel = v != null && v == 1 ? flowFolder.selectDocument_v(templateId) :
                    flowFolder.selectDocument(templateId);
            msgBody = handler.toJSONStringPrettyWriteMapNullValue(templateModel);
        }

        //查看协助的文档
        else if(handler.req.cmd.equals("selectAssitsDocument")){
            IFlowFolderView flowFolder = getInst().getFlowFolderView();
            Integer templateIdAssits =  handler.req.msgBody.getInteger("templateIdAssist");
            Integer templateId =  handler.req.msgBody.getInteger("templateId");

           if(templateIdAssits !=0){
               ProjectTemplateAssistModel projectTemplateAssistModel = flowFolder
                       .selectProjectTemplateAssistModel(templateIdAssits);
               msgBody = handler.toJSONStringPrettyWriteMapNullValue(projectTemplateAssistModel);
           }else {
               ProjectTemplateModel templateModel = flowFolder.selectDocument(templateId);
               msgBody = handler.toJSONStringPrettyWriteMapNullValue(templateModel);
           }


        }

        else if (handler.req.cmd.equals("previewJson")) {
            Integer v = handler.req.msgBody.getInteger("v");

            String ret = DocumentToolService.getDocumentToolService().previewJson(v, documentId);
            msgBody = "{\"list\":[" + ret + "]}";
        }

        else if (handler.req.cmd.equals("getJsonHeader")) {
            msgBody = DocumentToolService.getDocumentToolService().getJsonHeader(documentId);

        } else if (handler.req.cmd.equals("modifyJsonHeader")) {
            if (projectId == null || projectId == 0) {
                new YtbError(YtbError.CODE_DEFINE_ERROR, "修改文档头必须输入项目标识!");
            }
            DocumentToolService.getDocumentToolService().modifyJsonHeader(context,projectId, documentId,
                    handler.req.msgBody.getJSONObject("header"));

        } else if (handler.req.cmd.equals("paModifyTemplate")) {//甲方修改需求文档
            if (projectId == null || projectId == 0) {
                new YtbError(YtbError.CODE_DEFINE_ERROR, "修改文档内容必须输入项目标识!");
                //用户id也必须输入
            }
            String document = handler.req.msgBody.getString("template");
            PATemplateService.modifyTemplate(context, projectId, documentId, document);

        } else if (handler.req.cmd.equals("modifyJson")) {
            if (projectId == null || projectId == 0) {
                new YtbError(YtbError.CODE_DEFINE_ERROR, "修改文档内容必须输入项目标识!");
                //用户id也必须输入
            }
            String document = handler.req.msgBody.getString("document");
            DocumentToolService.getDocumentToolService().modifyJson(context,projectId, documentId, document);

        }
        // 需求说明书
        else if (handler.req.cmd.equals("getDocumentReq")) {
                ProjectTemplateModel m = context.getViewProjectFolderModel().getpAFolder().pbViewReqTemplate(context);
                msgBody = YtbUtils.toJSONStringSkipNull(m);

        } else if (handler.req.cmd.equals("addJson")) {
            String document = handler.req.msgBody.getString("template");
            int id = DocumentToolService.getDocumentToolService().addJson(document);
            JSONObject json = new JSONObject();
            json.put("documentId", id);
            msgBody = JSONObject.toJSONString(json);
        }
        else if (handler.req.cmd.equals("deleteDocument")) {
            msgBody = "{}";
            DocumentToolService.getDocumentToolService().deleteProjectDocument(documentId);
        } else {
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
