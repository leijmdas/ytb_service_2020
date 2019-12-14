package ytb.manager.template.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.template.rest.impl.*;
import ytb.manager.template.service.IDocumentService;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;


/*
 * author:leijm
 * add template document rest
 * 2010 10 20
 *
 *rest  base class*/
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestTemplateManager extends RestHandler {
    //consumes
    //项目模板管理接口
    @PostMapping(value = "/template", produces = "application/json")
    public String rest(@RequestBody String data) {

        //IDocumentService documentService = ManagerSrvContext.getInst().getDocumentService();
        try {
            parseRequest(data);
            resp = process(null);
        } catch (YtbError e) {
            e.printStackTrace();
            resp.failure(e.getRetcode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            if (e.getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
                ye = new YtbError(YtbError.CODE_DUPLICATE_RECORD);
            }
            resp.failure(ye.getRetcode(), e.getMessage());
        }
        return resp.toJSONString();
    }


    //项目模板管理接口
    @PostMapping(  "/upload" )
    @ResponseBody
    public String uploadTemplate(
            HttpServletRequest request,
            HttpServletResponse response,
            String msgBody,
            @RequestPart("file") MultipartFile file)
            throws IllegalStateException, IOException {
        try {

            parseRequest(msgBody);
            resp = process(file);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }

        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
    }



    protected MsgResponse process(MultipartFile file) throws Exception {

        if (!userContext.isUserManager() && !userContext.isTest()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "不是管理中心用户，不能使用模板管理的接口!");
        }

        if (req.cmdtype.equals("projType")) {
            return new ProjectType().process(req, this);
        } else if (req.cmdtype.equals("workJobType")) {
            return new WorkJobType().process(req, this);
        } else if (req.cmdtype.equals("repository")) {
            return new Repository().process(req, this);
        } else if (req.cmdtype.equals("templateDocument")) {
            return new DocumentRestProcess().process(this, file);
        } else if (req.cmdtype.equals("projConfig")) {

            return new ProjConfigRestProcess().process(this, file);

        }
        throw new YtbError(YtbError.CODE_INVALID_REST);

    }


    //项目模板文档接口:预览图片

    @PostMapping("/template/previewImage")
    public void previewImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            String msgBody = request.getParameter("msgBody");
            if (msgBody == null) {
                throw new YtbError(YtbError.getErrorId(YtbError.CODE_PARAMETER_IS_WRONG), "msgBody 没有传!");
            }
            byte[] data = Base64.getDecoder().decode(msgBody.getBytes("UTF-8"));
            parseRequest(new String(data, "UTF-8"));
            resp = buildMsg(0, "成功", "{}");
        } catch (YtbError ye) {
            ye.printStackTrace();
            resp = buildMsg(ye.retcode, ye.msg, "{}");
            String ret = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
            response.getWriter().write(ret);
            return;
        }
        try {
            Integer documentId = req.msgBody.getInteger("documentId");
            if (documentId == null) {
                YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
                resp = buildMsg(ye.getRetcode(), "please input documentId!", "{}");
            } else {
                IDocumentService documentService = ManagerSrvContext.getInst().getDocumentService();
                documentService.previewImage(documentId, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        } catch (YtbError ye) {
            resp = buildMsg(ye.retcode, ye.msg, "{}");
        }

        String ret = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
        response.getWriter().write(ret);
    }

    /*
     *
     * cmdType=templateDcument
     * cmd:uploadXls
     *   uploadPic
     *   uploadRar
     *   @RequestPart("msgBody")
     * */
    @PostMapping(value = "/template/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         String msgBody, @RequestPart("file") MultipartFile file)
            throws IllegalStateException, IOException {
        try {
            parseRequest(msgBody);
            resp = process(file);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }

        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);

    }


    /*
     *
     * cmdType=templateDcument
     * cmd:download
     */
    @PostMapping("/template/download")
    public void download(String msgBody, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            parseRequest(new String(Base64.getDecoder().decode(msgBody), "UTF-8"));
            Integer documentId = req.msgBody.getInteger("documentId");
            if (documentId == null) {
                YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
                resp = buildMsg(ye.getRetcode(), "please input documentId!", "{}");
            } else {
                IDocumentService documentService = ManagerSrvContext.getInst().getDocumentService();
                documentService.download(documentId, response);
                return;
            }
            return;
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        String msgResp = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
        response.getWriter().write(msgResp);

    }


}
