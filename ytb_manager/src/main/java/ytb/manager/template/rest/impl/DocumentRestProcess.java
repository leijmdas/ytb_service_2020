package ytb.manager.template.rest.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.model.T_Stop_ActionModel;
import ytb.manager.template.service.IDocumentService;
import ytb.manager.template.service.impl.T_Stop_ActionServiceImpl;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: leijianming
 * Date: Created in 2018/10/20 16:21
 */
public class DocumentRestProcess {

    public MsgResponse process(RestHandler handler, MultipartFile file) throws Exception {
        IDocumentService documentService = ManagerSrvContext.getInst().getDocumentService();
        TemplateDocumentHeader tagDocumentHeader = TemplateDocumentHeader.parse(handler.req.msgBody.toJSONString());

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        Integer documentId = handler.req.msgBody.getInteger("documentId") ;

        //项目模板文档接口：获取json
        //cmdType=templateDcument cmd=previewJson
        if (handler.req.cmd.equals("selectDocTemplates")) {
            Integer repositoryId = handler.req.msgBody.getInteger("repositoryId");
            Integer workJobId = handler.req.msgBody.getInteger("workJobId");
            JSONArray docTypes = handler.req.msgBody.getJSONArray("docTypes");
            if (repositoryId == null && workJobId == null) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
            }
            if (repositoryId == null) {
                repositoryId = 0;
            }
            if (workJobId == null) {
                workJobId = 0;
            }
            YtbLog.logDebug(workJobId);
            List<Dict_TemplateModel> lst = documentService.selectDocTemplates(repositoryId, workJobId, docTypes);
            handler.resp.msgBody.put("list", lst);
            msgBody = JSONObject.toJSONString(handler.resp.msgBody);
        } else if (handler.req.cmd.equals("getDocTemplate_gen")) {
            Integer docType = handler.req.msgBody.getInteger("docType");

            List<Dict_TemplateModel> lst = documentService.getDocTemplate_gen(docType);
            handler.resp.msgBody.put("list",lst);
            msgBody = JSONObject.toJSONString(handler.resp.msgBody);

        }  else  if (handler.req.cmd.equals("previewJson")) {
            //documentId = Integer.parseInt(handler.req.msgBody.getString("documentId"));
            String ret = documentService.previewJson(Integer.valueOf(documentId));
            msgBody = "{\"list\":[" + ret + "]}";

        } else if (handler.req.cmd.equals("delReDocument")) {
            //documentId = Integer.parseInt(handler.req.msgBody.getString("documentId"));
            documentService.delReDocument(documentId);
            msgBody = "{}";
        } else if (handler.req.cmd.equals("modifyReDocument")) {
            //documentId =  handler.req.msgBody.getInteger("documentId" );
            documentService.modifyReDocument(documentId,handler.req);
            msgBody = "{}";
        }
        //修改文档模板
        else if (handler.req.cmd.equals("modifyReDocumentHeader")) {
            //documentId = Integer.parseInt(handler.req.msgBody.getString("documentId"));
            documentService.modifyReDocumentHeader(documentId, tagDocumentHeader);
            msgBody = "{}";
        } else if (handler.req.cmd.equals("getReDocumentHeader")) {
            //documentId = Integer.parseInt(handler.req.msgBody.getString("documentId"));
            String header = documentService.getReDocumentHeader(documentId);
            msgBody = header;
        }
        else if (handler.req.cmd.equals("uploadTemplate")) {
            documentId = documentService.uploadTemplate(handler, file);
            msgBody = buildmsgBody(documentId,handler);
        }
        else if (handler.req.cmd.equals("upload")) {
            documentId = documentService.upload(handler, file);
            msgBody = buildmsgBody(documentId,handler);
        } else if (handler.req.cmd.equals("uploadPIC")) {
            handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_PIC);
            documentId = documentService.upload(handler, file);
            msgBody = buildmsgBody(documentId,handler);
        } else if (handler.req.cmd.equals("uploadXLS")) {
            handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_XLS);
            documentId = documentService.upload(handler, file);
            msgBody = buildmsgBody(documentId,handler);
        } else if (handler.req.cmd.equals("uploadRAR")) {
            handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_RAR);
            documentId = documentService.upload(handler, file);
            msgBody = buildmsgBody(documentId, handler);
        } else if (handler.req.cmd.equals("getStopAction")) {

            int actionId = handler.req.getMsgBody().getInteger("actionId");
            T_Stop_ActionModel sa = new T_Stop_ActionServiceImpl().get(actionId);
            msgBody = "{'list':[" + YtbUtils.toJSONStringPretty(sa) + "]}";
        } else if (handler.req.cmd.equals("getListStopAction")) {

            int templateId = handler.req.getMsgBody().getInteger("templateId");
            List<T_Stop_ActionModel> lst = new T_Stop_ActionServiceImpl().getList(templateId);
            msgBody = "{'list': " + YtbUtils.toJSONStringPretty(lst) + " }";
        } else if (handler.req.cmd.equals("delStopAction")) {

            int actionId = handler.req.getMsgBody().getInteger("actionId");
            new T_Stop_ActionServiceImpl().del(actionId);
            msgBody = "{}";
        } else if (handler.req.cmd.equals("addStopAction")) {

            T_Stop_ActionModel sa = JSONObject.parseObject(handler.req.getMsgBody().toJSONString(),
                    T_Stop_ActionModel.class);
            int actionId = new T_Stop_ActionServiceImpl().add(sa);
            msgBody = "{'actionId':" + actionId + " }";
        } else if (handler.req.cmd.equals("modifyStopAction")) {

            T_Stop_ActionModel sa = JSONObject.parseObject(
                    handler.req.getMsgBody().toJSONString(),
                    T_Stop_ActionModel.class);
            new T_Stop_ActionServiceImpl().modify(sa);
            msgBody = "{'actionId':" + sa.getActionId() + " }";
        }
        else {
            throw new YtbError(YtbError.CODE_INVALID_REST,"cmd未定义!");
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    String buildmsgBody(int documentId,RestHandler handler){
        JSONObject json=JSONObject.parseObject("{}");
        json.put("documentId",documentId);
        json.put("fileName",handler.resp.getMsgBody().getString("fileName"));
        return JSONObject.toJSONString(json);
    }


}
