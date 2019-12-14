package ytb.bangbang.rest;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ytb.bangbang.model.BBDocument;
import ytb.bangbang.rest.impl.GroupManager;
import ytb.bangbang.rest.impl.RecordList;
import ytb.bangbang.rest.impl.UserFriend;
import ytb.bangbang.rest.impl.UserManager;
import ytb.bangbang.service.BBDocumentService;
import ytb.bangbang.service.UploadService;
import ytb.bangbang.service.impl.BBDocumentServiceImpl;
import ytb.bangbang.service.impl.UploadServiceImpl;
import ytb.common.context.rest.IRestProcess;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hj
 * @Description bangbang后台的Rest类
 * @Date 2018/9/4
 **/
@RestController
@RequestMapping("/bangbangRest")
@Scope("prototype")
public class BangbangRest extends RestHandler implements IRestProcess {

    @RequestMapping(value = "Ajax", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String bangbangRest(@RequestBody String data,HttpServletRequest request, HttpServletResponse response) {
      //  return new MsgHandler(new DefaultUserContext()).parseRequest(this, data, request, response);
        return new MsgHandler().parseRequest(this, data, request, response);

//        try {
//            parseRequest(data);
//            int userId = SafeContext.getLog_sso(req.token).getUserid();
//            req.msgBody.put("userId", userId);
//            resp = process();
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp = buildMsg(new YtbError(YtbError.CODE_UNKNOWN_ERROR).getRetcode(), e.getMessage(), "{}");
//        } catch (YtbError e) {
//            e.printStackTrace();
//            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
//        }
//        return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
    }

    //处理请求
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        MsgRequest req = handler.req;

        int userId = handler.getUserContext().getLoginSso().getUserId().intValue();
        req.msgBody.put("userId", userId);

        if (req.cmdtype.equals("FriendsManger")) {
            //好友管理
            return new UserFriend().process(req, handler);
        } else if (req.cmdtype.equals("RecordManger")) {
            //聊天记录管理
            return new RecordList().process(req, handler);
        } else if (req.cmdtype.equals("UserManger")) {
            //用户管理
            return new UserManager().process(req, handler);

        } else if (req.cmdtype.equals("GroupManger")) {
            //群组管理
            return new GroupManager().process(req, handler);
        }
        return buildMsg(0, "ok", "{\"a\":1}");
    }

    @ResponseBody
    @RequestMapping(value = "/UserFile/upload", produces = {"Application/json;charset=UTF-8"})
    public MsgResponse uploadFile(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "file", defaultValue = "") MultipartFile file,
                             @RequestParam(value = "msgBody", defaultValue = "{}") String msgBody) {
        int retcode = 0;
        String retmsg = "成功";
        String  retMsgBody = "{}";
        MsgHandler msgHandler=new MsgHandler();
        msgHandler.parseRequest(msgBody);
        MsgRequest req = msgHandler.req;
        Integer userId=msgHandler.getUserContext().getLoginSso().getUserId().intValue();
        String fileType=req.cmd;
        if(fileType.equals("uploadImg")){
            fileType="2";
        }else if(fileType.equals("uploadFile")){
            fileType="1";
        }
        //获取帮帮文件路径
//        YtbContext.getYtb_context().getConfig_value("FILE_PATH_BB");
        UploadService uploadService = new UploadServiceImpl();
        BBDocumentService bbDocumentService=new BBDocumentServiceImpl();
        if ("".equals(fileType)) {
            retcode = 1;
            retmsg = "失败,cmd参数不存在!";
            return msgHandler.buildMsg(retcode, retmsg, retMsgBody);
        }
        String rootPath = "D:/YTB_BB_FILE/"+userId+"/";
        Date date=new Date();
        rootPath=rootPath+new SimpleDateFormat("yyyy-MM-dd").format(date)+"/";
        rootPath=rootPath+fileType+"/";
        Map<String,String> map=new HashMap<String,String>();
        try {
            map = uploadService.upload(request,file,rootPath);
            BBDocument document =new BBDocument();
            document.setName(map.get("oldFileName"));
            document.setDocType(Integer.parseInt(fileType));
            document.setDocPath(map.get("filePath"));
            document.setDocSize(map.get("fileSize"));
            bbDocumentService.addRecord(document);
            JSONObject retJson=new JSONObject();
            retJson.put("documentId",document.getDocumentId());
            retJson.put("fileSize",document.getDocSize());
            retJson.put("fileName",document.getName());
            retJson.put("filetype",map.get("fileType"));
            retMsgBody=retJson.toJSONString();
        } catch (RuntimeException e) {
            retcode = 1;
            retmsg = "失败";
            return msgHandler.buildMsg(retcode, retmsg, retMsgBody);
        }

        return msgHandler.buildMsg(retcode, retmsg, retMsgBody);
    }

    @RequestMapping(value = "/bangbang/previewImage")
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
                UploadService uploadService = new UploadServiceImpl();
                uploadService.previewImage(documentId,response);
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


    @RequestMapping(value = "/bangbang/download")
    public void loadownFile(HttpServletResponse response, HttpServletRequest request) throws IOException{
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
                UploadService uploadService = new UploadServiceImpl();
                BBDocumentService service=new BBDocumentServiceImpl();
                BBDocument document=service.getDocumentInfo(documentId);
                String filePath=document.getDocPath();
                uploadService.fileDownloadByPath(filePath, response, request,document.getName());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        } catch (YtbError ye) {
            resp = buildMsg(ye.retcode, ye.msg, "{}");
        }

    }

}
