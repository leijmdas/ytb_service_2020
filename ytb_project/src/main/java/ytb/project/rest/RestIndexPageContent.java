package ytb.project.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.project.rest.impl.CusServiceCenter;
import ytb.project.rest.impl.IndexPage;
import ytb.manager.template.service.IDocumentService;
import ytb.manager.template.service.impl.DocumentServiceImpl;
import ytb.common.context.rest.IRestProcess;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/rest")
public class RestIndexPageContent implements IRestProcess {

    @RequestMapping(value = "/indexPageContent", produces = "application/json;charset=UTF-8")
    public String restIndexPageContent(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);
    }

    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MsgResponse msgResponse;
        switch (handler.req.cmdtype) {
            case "indexPage": {
                msgResponse = new IndexPage().process(handler);
                break;
            }
            case "cusServiceCenter": {
                msgResponse = new CusServiceCenter().process(handler);
                break;
            }
            default: {
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }
        }
        return msgResponse;
    }

    @RequestMapping(value = "/indexPageContent/previewImage")
    public void previewImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        IDocumentService documentService = new DocumentServiceImpl();
        //IDocumentService documentService = ManagerSrvContext.getInst().getDocumentService();

        RestHandler restHandler = new RestHandler();
        MsgResponse resp;
        try {
            String msgBody = request.getParameter("msgBody");
            if (msgBody == null) {
                throw new YtbError(YtbError.getErrorId(YtbError.CODE_PARAMETER_IS_WRONG), "msgBody is null");
            }
            byte[] data = Base64.getDecoder().decode(msgBody.getBytes("UTF-8"));
            restHandler.parseRequestNoCheck(new String(data, "UTF-8"));
            resp = restHandler.buildMsg(0, "成功", "{}");
        } catch (YtbError ye) {
            ye.printStackTrace();
            resp = restHandler.buildMsg(ye.retcode, ye.msg, "{}");
            String ret = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
            response.getWriter().write(ret);
            return;
        }
        try {
            Integer documentId = restHandler.req.msgBody.getInteger("documentId");
            if (documentId == null) {
                YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
                resp = restHandler.buildMsg(ye.getRetcode(), "please input documentId!", "{}");
            } else {
                documentService.previewImage(documentId, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = restHandler.buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        } catch (YtbError ye) {
            resp = restHandler.buildMsg(ye.retcode, ye.msg, "{}");
        }

        String ret = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
        response.getWriter().write(ret);
    }
}
