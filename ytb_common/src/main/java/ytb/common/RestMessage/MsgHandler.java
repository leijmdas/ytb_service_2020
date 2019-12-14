package ytb.common.RestMessage;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.rest.IRestProcess;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.IUserContext;
import ytb.common.context.service.impl.DefaultUserContext;
import ytb.common.context.service.impl.YtbContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class MsgHandler {
    public final static String UTF_8 = "UTF-8";
    public MsgRequest req;
    public MsgResponse resp;
    protected IUserContext userContext = new DefaultUserContext();


    private IRestProcess restProcess;

    public MsgHandler() {

    }

    public MsgHandler(IUserContext userContext) {
        this.userContext = userContext;
    }


    public IUserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(IUserContext userContext) {
        this.userContext = userContext;
    }

    public MsgRequest getReq() {
        return req;
    }

    public void setReq(MsgRequest req) {
        this.req = req;
    }

    public MsgResponse getResp() {
        return resp;
    }

    public void setResp(MsgResponse resp) {
        this.resp = resp;
    }


    public void parseRequest(String reqBody) {
        req = req.parse(reqBody);
        resp = new MsgResponse(req);
        resp = success_default();
        if (userContext == null) {
            userContext = new DefaultUserContext();
        }
        if (req.token != null) {
            userContext.setLoginSso(YtbContext.getSafeContext().getLog_sso_catch(req.token));
            userContext.setTestFlag(req.getTestFlag());
        }

        userContext.checkUserRightValid(req);
        //userContext.insertUserLog(req);
    }


    public MsgResponse success_default() {
        return success( "{}");
    }

    public MsgResponse success(String retBody) {
        resp = new MsgResponse(req);
        resp.success(retBody);
        return resp;
    }
    public MsgResponse failure(int retcode, String retmsg) {
        resp = new MsgResponse(req);
        resp.failure(retcode, retmsg);
        return resp;
    }
    public MsgResponse buildMsg(int retcode, String retmsg, String retBody) {
        resp = new MsgResponse(req);
        resp.buildMsg(retcode, retmsg, retBody);
        return resp;
    }

    public MsgResponse buildMsg(Exception e) {
        e.printStackTrace();
        YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
        return buildMsg(ye.getRetcode(), e.toString(), "{}");

    }

    public MsgResponse buildMsg(YtbError ye) {
        ye.printStackTrace();
        return buildMsg(ye.getRetcode(), ye.getMsg(), "{}");
    }


    public  String toString() {
        return toJSONStringPretty(this.resp);
    }


    public String parseRequest(IRestProcess restProcess, String reqBody, HttpServletRequest request, HttpServletResponse response) {

        this.restProcess = restProcess;
        response.setCharacterEncoding("utf-8");

        try {
            parseRequest(reqBody);
            YtbLog.logDebug("Enter " + req.buildCmdInfo(), this.req);
            restProcess.process(this, request, response);

        } catch (YtbError e) {
            if (YtbLog.isDebug()) {
                e.printStackTrace();
            }
            failure(e.getRetcode(), e.toString());
        } catch (Exception e) {
            if(YtbLog.isDebug()) {
                e.printStackTrace();
            }
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            failure(ye.getRetcode(), e.toString());
        }finally {
            YtbLog.logDebug("Exit " + req.buildCmdInfo(), this.resp);
        }

        return resp.toJSONString();
    }


    public static String toJSONStringPretty(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat);
    }

    public static String toJSONStringPrettyWriteMapNullValue(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
    }

    public static String base64toStr(String base64Str) throws UnsupportedEncodingException {
        byte[] r = Base64.getDecoder().decode(base64Str.getBytes("UTF-8"));
        return new String(r, "UTF-8");
    }

    public static String base64toStr(byte[] base64) throws UnsupportedEncodingException {
        byte[] r = Base64.getDecoder().decode(base64);
        return new String(r, "UTF-8");
    }


    public static String byte2Base64(byte[] b) throws UnsupportedEncodingException {
        byte[] r = Base64.getEncoder().encode(b);
        return new String(r, "UTF-8");
    }



    public static HttpEntity buildEntity(String url, String msgBody, File postFile) {
        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.setContentType(ContentType.MULTIPART_FORM_DATA);
        multipartEntity.addTextBody("msgBody", msgBody);
        FileBody fundFileBin = new FileBody(postFile);
        multipartEntity.addPart("file", fundFileBin);
        return multipartEntity.build();
    }

}
