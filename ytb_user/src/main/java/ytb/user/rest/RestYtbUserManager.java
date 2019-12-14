package ytb.user.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ytb.common.ytblog.YtbLog;
import ytb.common.basic.config.service.ConfigCacheService;
import ytb.common.context.service.impl.YtbContext;
import ytb.user.context.UserSrvContext;
import ytb.user.rest.impl.*;
import ytb.user.service.IImageService;
import ytb.user.service.impl.ImageServiceImpl;
import ytb.user.context.UserContext;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Package: ytb.user.rest
 * Author: ZCS
 * Date: Created in 2018/9/3 15:39
 */
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestYtbUserManager extends RestHandler {
    static UserSrvContext getInst() {
        return UserSrvContext.getInst();
    }

    IImageService imageService = new ImageServiceImpl();


    @PostMapping(value = "ytbuser", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String sysUserRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        try {
            setUserContext(new UserContext());

            parseRequest(data);
            YtbLog.logDebug("Enter " + req.buildCmdInfo(), this.req);

            resp = process(request);
        } catch (YtbError ye) {
            ye.printStackTrace();
            MsgResponse process = buildMsg(ye.retcode, ye.msg, "{}");
            return JSONObject.toJSONString(process, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }finally {
            YtbLog.logDebug("Exit " + req.buildCmdInfo(), resp);

        }

        return resp.toJSONStringPretty();
    }


    //用户图片预览接口
    //cmdType=userImage :cmd=previewImage
    @PostMapping(value = "/ytbuser/image/previewImage")
    public void previewImage(HttpServletRequest request, HttpServletResponse response) throws IOException, YtbError {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            String msgBody = request.getParameter("msgBody");
            if (msgBody == null) {
                throw new YtbError(YtbError.getErrorId(YtbError.CODE_PARAMETER_IS_WRONG), "msgBody 没有传!");
            }
            byte[] data = Base64.getDecoder().decode(msgBody.getBytes("UTF-8"));
            parseRequest(new String(data));
            //parseRequest(new Gson().toJson( defaultMsgRequest()).toString());
            resp = buildMsg(0, "成功", "{}");
        } catch (YtbError ye) {
            ye.printStackTrace();
            resp = buildMsg(ye.retcode, ye.msg, "{}");
            String ret = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
            response.getWriter().write(ret);
            return;
        }
        String ret = JSONObject.toJSONString(resp, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        try {
            Integer documentId = req.msgBody.getInteger("documentId");
            if (documentId != null && !"".equals(documentId)) {
                imageService.previewImage(documentId, response);
                return;
            } else {
                YtbError ye = new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
                resp = buildMsg(ye.getRetcode(), ye.getMsg(), "{}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        } catch (YtbError ye) {
            resp = buildMsg(ye.retcode, ye.msg, "{}");
        }
        ret = JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
        response.getWriter().write(ret);
    }

    //@RequestPart("msgBody")
    @PostMapping(value = "/ytbuser/image/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         String msgBody, @RequestPart("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Map<String,Object> map = new HashMap<>();
        try {

            parseRequest(msgBody);
            resp = buildMsg(0, "成功", "{}");
            //通过调用者传savemode
            //req.msgBody.put("saveMode",IImageService.SAVEMODE_PATH);
            if (req.msgBody.get("saveMode") == null) {
                req.msgBody.put("saveMode", IImageService.SAVEMODE_DB);
            }
            req.msgBody.put("name", file.getOriginalFilename());
            String path = YtbContext.getYtb_context().getConfig_value(ConfigCacheService.CFG_ITENM_USER_FILE_PATH_PIC);
            req.msgBody.put("docPath", path + '/' + UUID.randomUUID().toString().replace("-", "") + req.msgBody.getString("name"));
            int docid = imageService.upload(this.req, file);
            resp.msgBody.put("documentId", docid);
            map.put("documentId", docid);
            map.put("name", file.getOriginalFilename());
            System.out.println(JSONObject.toJSON(resp));
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }

        return JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);

    }

    @PostMapping(value = "/ytbuser/image/delete")
    @ResponseBody
    public String deleteUserImg(@RequestBody String msgBody, HttpServletRequest request, HttpServletResponse response)
            throws IllegalStateException, IOException {
        try {
            parseRequest(msgBody);
            Integer documentId = req.msgBody.getInteger("documentId");
            if (documentId != null) {
                imageService.deleteUserImg(documentId);
            } else {
                YtbError ye = new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
                resp = buildMsg(ye.getRetcode(), ye.getMsg(), "{}");
            }

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


    @PostMapping("/ytbuser/doc/download")
    public void download(String msgBody, HttpServletRequest request, HttpServletResponse response) throws IOException {

        YtbLog.logDebug("文件下载开始：");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            parseRequest(new String(Base64.getDecoder().decode(msgBody), "UTF-8"));
            Integer documentId = req.msgBody.getInteger("documentId");
            if (documentId == null) {
                YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
                resp = buildMsg(ye.getRetcode(), "please input documentId!", "{}");
            } else {
                imageService.download(documentId, response);
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



    protected MsgResponse process(HttpServletRequest request) {

        if (req.cmdtype.equals("userLogin")) {//用户登录

            req.msgBody.put("userType", 1);
            return new UserLoginCenter().process(req, this, request);

        } else if (req.cmdtype.equals("companyLogin")) {//公司登录

            req.msgBody.put("userType", 2);
            return new UserLoginCenter().process(req, this, request);

        } else if (req.cmdtype.equals("registry")) {//注册

            return new RegistryCenter().process(req, this, request);
        }
        else if (req.cmdtype.equals("modifyInfo")) {//modify

            return new ModifyInfo().process(req, this, request);
        }
        else if (req.cmdtype.equals("userCenter")) {//用户资料

            return getInst().getUserCenter().process(req, this, request);

        } else if (req.cmdtype.equals("companyCenter")) {//公司资料

            return getInst().getCompanyCenter().process(req, this, request);

        } else if (req.cmdtype.equals("searchPerson")) {//人才搜索

            return new SearchPerson().process(req, this, request);

        }else if(req.cmdtype.equals("advicePosted")){//广告贴
            return new AdvicePosted().process(req, this, request);

        }else if(req.cmdtype.equals("searchInfo")){ //搜索页面需要的基本信息
            return new SearchInfo().process(req, this, request);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);
    }


//    public static  void main(String kk[]){
//        HttpServletRequest request;
//       MsgRequest req = new MsgRequest();
//        req.token = "135a95c7ea2340e2bc0fdd68766d1daa";
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date());
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "registry";
//        req.cmd = "user";
//        req.msgBody = JSONObject.parseObject("{\"username\":\"18270054576\",\"password\":'123456',\"code\":'388123',\"model\":'password'}");
//        System.out.println(new Gson().toJson(req));
//        System.out.println(new RestYtbUserManager().sysUserRest(JSONObject.toJSONString(req, SerializerFeature.WriteMapNullValue),null,null));
//
//    }

}
