package ytb.user.rest;

import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.ytblog.YtbLog;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Package: ytb.user.rest.impl
 * Author: Cchua
 * Date: Created in 22018年9月12日
 */
public class ModifyInfo {


    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";
        if (req.cmd.equals("changePassword")) {

            try {
                String token = req.token;
                if (token != null) {
                    LoginSso loginSso = handler.getUserContext().getLoginSso();
                    if (loginSso != null) {
                        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
                        YtbLog.logDebug(loginSsoJson.getUserId());
                        String password = req.msgBody.getString("password");
                        String oldPassword = req.msgBody.getString("oldPassword");

                        String code = req.msgBody.getString("code");

                        UserCenterService userCenterService = new UserCenterServiceImpl();

                        System.out.println(password);

                        int sta = userCenterService.modifyPassword(loginSsoJson.getUserId(), loginSsoJson.getUserType(), password, oldPassword);

                        if (sta > 0) {
                            retcode = 0;
                            retmsg = "成功";
                        } else {
                            retcode = 2;
                            retmsg = "修改密码失败";
                        }


                    } else {
                        retcode = 2;
                        retmsg = "修改密码失败，用户密码错误";
                    }


                }

            } catch (Exception e) {
                retcode = 2;
                retmsg = "修改密码失败";
            }
        }


        if (req.cmd.equals("changePhone")) {
            //String token = req.token;
            LoginSso loginSso = handler.getUserContext().getLoginSso();
            LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);


            String oldPhone = req.msgBody.getString("oldPhone");
            String newPhone = req.msgBody.getString("newPhone");
            String password = req.msgBody.getString("password");
            String oldPhoneCode = req.msgBody.getString("oldPhoneCode");
            String newPhoneCode = req.msgBody.getString("newPhoneCode");


            TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();

            Tasklog_Sms_CodeModel oldCode = taskLogSmsCodeService.selectByPrimaryKey(oldPhone);

            Tasklog_Sms_CodeModel newCode = taskLogSmsCodeService.selectByPrimaryKey(newPhone);

            if (oldCode != null && newCode != null) {

                if (oldCode.getSmsCode().equals(oldPhoneCode) && newCode.getSmsCode().equals(newPhoneCode)) {

                    UserCenterService userCenterService = new UserCenterServiceImpl();

                    Boolean sta = userCenterService.modifyPhone(loginSsoJson.getUserId(), newPhone, password, oldPhoneCode);

                    if (sta == false) {
                        retcode = 2;
                        retmsg = "修改密码失败";
                    }

                } else {
                    retcode = 2;
                    retmsg = "短信验证码错误";
                }


            } else {
                retcode = 2;
                retmsg = "短信验证码错误";
            }


        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }

//    public String getIP(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("X-Real-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//    public static String uuid() {
//        String s = UUID.randomUUID().toString();
//
//        return s.replace("-", "");
//
//    }
}