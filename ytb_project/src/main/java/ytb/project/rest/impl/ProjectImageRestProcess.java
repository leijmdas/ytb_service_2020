package ytb.project.rest.impl;


import org.springframework.web.multipart.MultipartFile;
import ytb.project.service.template.DocumentToolService;
import ytb.manager.template.model.Dict_document;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: leijianming
 * Date: Created in 2018/8/14 16:21
 */
public class ProjectImageRestProcess {


    public MsgResponse process(HttpServletRequest request, HttpServletResponse response, MsgHandler handler, MultipartFile mfile) throws IOException {
        DocumentToolService toolService = DocumentToolService.getDocumentToolService();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        handler.resp.setRetcode(0);
        handler.resp.setRetmsg("成功");
        try {
            //previewImage
            if (handler.req.cmd.equals("previewImage")) {
                int documentId = handler.req.msgBody.getInteger("documentId");
                DocumentToolService.getDocumentToolService().previewImage(documentId, response);
                return handler.resp;
            }
            else if (handler.req.cmd.equals("addImage")) {
                int documentId = DocumentToolService.getDocumentToolService().addImage(handler.req, request);
                handler.resp.msgBody.put("documentId",documentId );

            } else if (handler.req.cmd.equals("modifyImage")) {
                int documentId = handler.req.msgBody.getInteger("documentId");
                int update_num = DocumentToolService.getDocumentToolService().modifyImage(documentId, request);
                handler.resp.msgBody.put("update_num", update_num);
                return handler.resp;
            }

            //upload
            else if (handler.req.cmd.equals("uploadTemplate")) {
                handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_JSON);
                int documentId = toolService.uploadTemplate(handler , mfile);
                handler.resp = handler.buildMsg(0, "成功", "{}");
                handler.resp.msgBody.put("documentId", documentId);
                return handler.resp;
            }
            else if (handler.req.cmd.equals("upload")) {
                handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_RAR);
                int documentId = toolService.uploadBinary(handler, mfile);
                handler.resp = handler.buildMsg(0, "成功", "{}");
                handler.resp.msgBody.put("documentId", documentId);
                return handler.resp;
            }
            else if (handler.req.cmd.equals("uploadBinary")) {
                handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_RAR);
                int documentId = toolService.uploadBinary(handler, mfile);
                handler.resp = handler.buildMsg(0, "成功", "{}");
                handler.resp.msgBody.put("documentId", documentId);
                return handler.resp;

            } else if (handler.req.cmd.equals("uploadPIC")) {
                handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_PIC);
                int documentId = toolService.uploadBinary(handler, mfile);
                handler.resp = handler.buildMsg(0, "成功", "{}");
                handler.resp.msgBody.put("documentId", documentId);
                return handler.resp;

            } else if (handler.req.cmd.equals("uploadXLS")) {
                handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_XLS);
                int documentId = toolService.uploadBinary(handler, mfile);
                handler.resp = handler.buildMsg(0, "成功", "{}");
                handler.resp.msgBody.put("documentId", documentId);
                return handler.resp;

            } else if (handler.req.cmd.equals("uploadRAR")) {
                handler.req.msgBody.put("docType", Dict_document.DOC_TYPE_RAR);
                int documentId = toolService.uploadBinary(handler, mfile);
                handler.resp = handler.buildMsg(0, "成功", "{}");
                handler.resp.msgBody.put("documentId", documentId);
                return handler.resp;
            }
            else {
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            handler.resp = handler.buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        } catch (YtbError ye) {
            ye.printStackTrace();
            handler.resp = handler.buildMsg(ye.retcode, ye.msg, "{}");

        }
        //response.getWriter().write(JSONObject.toJSONString(handler.resp));
        return handler.resp;
    }


}
