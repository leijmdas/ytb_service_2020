/*author:leijm
	rest基类
* */
package ytb.common.context.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import ytb.common.basic.safelog.service.Tasklog_UserServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.DefaultUserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


/*@RestController
@RequestMapping("/rest")
*/
public class RestHandler extends MsgHandler implements IRestProcess
{


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

    //项目管理接口
    //@RequestMapping(value = "restMsgHandler",produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    final String restMsgHandler(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler(new DefaultUserContext()).parseRequest(this, data, request, response);

    }

    /*@ResponseBody
    @RequestMapping(value = "/demohandler" )*/
    final public String restHandler(@RequestBody String data) {
        try {
            parseRequest(data);
            resp = process();
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        return resp.toJSONString();

    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    //定制方法
    protected MsgResponse process() throws Exception {
        return buildMsg(0, "ok", "{\"t\":1}");
    }

    public void parseRequestNoCheck(String reqBody) {
        req = MsgRequest.parse(reqBody);
        resp = new MsgResponse(req);
        new Tasklog_UserServiceImpl().insertUserLog(req);
    }

    public MsgResponse buildMsg(int retcode, String retmsg, String retBody) {
        resp = new MsgResponse(req);
        resp.buildMsg(retcode, retmsg, retBody);
        return resp;
    }
    public MsgResponse success(JSONObject retBody) {
        resp = new MsgResponse(req);
        resp.success(retBody);
        return resp;
    }
    public MsgResponse success(String retBody) {
        resp = new MsgResponse(req);
        resp.success(retBody);
        return resp;
    }

    public static HttpEntity buildEntity(String url, String msgBody, File postFile) {
        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.setContentType(ContentType.MULTIPART_FORM_DATA);
        multipartEntity.addTextBody("msgBody", msgBody);
        FileBody fundFileBin = new FileBody(postFile);
        multipartEntity.addPart("file", fundFileBin);
        return multipartEntity.build();
    }

    public static String toJSONStringPretty(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat);
    }

    public static String toJSONStringPrettyWriteMapNullValue(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
    }


    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
