package ytb.project.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ytb.project.context.UserProjectContext;
import ytb.project.rest.impl.*;
import ytb.project.service.template.DocumentToolService;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;


/*rest  base class*/
@RestController
@RequestMapping("/rest/project")
@Scope("prototype")
public class RestProjectDocument extends RestHandler {

    //cmdType:projectDocument
    //cmd:previewJson upLoad
    //项目文档接口
    @PostMapping(value = "document",produces = {"Application/json;charset=UTF-8"})
    public String restProjectDocument(@RequestBody String data, HttpServletRequest
            request, HttpServletResponse response) {
        try {
            setUserContext(new UserProjectContext());
            parseRequest(data);
            resp = process(request, response,null);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye=new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(),e.getMessage(), "{}");
        }
        return resp.toJSONString();
    }


    MsgResponse process(HttpServletRequest request, HttpServletResponse response, MultipartFile mfile) throws Exception {

        if (getUserContext().isUserManager() && !getUserContext().isTest()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "是管理中心用户，不能使用项目中心的接口!");
        }

        if (req.cmdtype.equalsIgnoreCase("projectDocument")) {
            return new ProjectDocumentRestProcess().process(this);
        } else if (req.cmdtype.equalsIgnoreCase("projectImage")) {
            return new ProjectImageRestProcess().process(request, response, this, mfile);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);
    }


    @PostMapping(value = "/upload", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         String msgBody, @RequestPart("file") MultipartFile file)  throws IllegalStateException, IOException {
        try {
            parseRequest(msgBody);
            resp = process(request, response,file);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye=new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }

        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);

    }

    //二进制文档（图片）接口
    @PostMapping(value = "/image",produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public void image(HttpServletRequest request, HttpServletResponse response,@RequestPart("file") MultipartFile file) throws IOException {

        try {
            String msgBody = request.getParameter("msgBody");
            if (msgBody == null) {
                throw new YtbError(YtbError.getErrorId(YtbError.CODE_PARAMETER_IS_WRONG), "msgBody 没有传!");
            }
            byte[] data = Base64.getDecoder().decode(msgBody.getBytes());
            parseRequest(new String(data));
            resp = process(request, response,file);
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        response.getWriter().write(JSONObject.toJSONString(resp));
    }

    /*
     *
     * cmdType=templateDcument
     * cmd:download
     */
    @PostMapping(value = "/download",produces = {"Application/json;charset=UTF-8"})
    public void download(@RequestBody String reqBody,HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            parseRequest(reqBody);
            Integer documentId = req.msgBody.getInteger("documentId");
            if (documentId == null) {
                YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
                resp = buildMsg(ye.getRetcode(), "please input documentId!", "{}");
            } else {
                DocumentToolService.getDocumentToolService().download(documentId,  response);
                return;
            }
            return;
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye=new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        response.getWriter().write(resp.toJSONString());

    }
}
