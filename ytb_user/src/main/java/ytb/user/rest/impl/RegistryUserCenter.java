package ytb.user.rest.impl;

import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.common.basic.userid.model.User_IdModel;
import ytb.common.basic.userid.service.impl.UserIdServiceImpl;
import ytb.user.model.UserLoginModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Package: ytb.user.rest.impl
 * Author: Cchua
 * Date: Created in 22018年9月12日
 */
public class RegistryUserCenter {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody;

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {

        int userType = 0;
        if (req.cmd.equals("user")) {
            userType = 1;
        } else if (req.cmd.equals("company")) {
            userType = 2;
        } else {
            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        String username = req.msgBody.getString("username");
        String password = req.msgBody.getString("password");
        String code = req.msgBody.getString("code");


        UserCenterService userCenterService = new UserCenterServiceImpl();
        UserLoginModel userLoginModel =
                userCenterService.getUserLoginInfoByLoginMobile(username);

        if (userLoginModel == null) {

            TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
            Tasklog_Sms_CodeModel templateLogSmsCodeByPhone = taskLogSmsCodeService.selectByPrimaryKey(username);
//验证
            if (templateLogSmsCodeByPhone != null) {

                if (templateLogSmsCodeByPhone.getSmsCode().equals(code)) {
                    if (username != null || username.equals("")) {


                        UserIdServiceImpl userIdService = new UserIdServiceImpl();

                        User_IdModel userIdModel = new User_IdModel();
                        userIdModel.setUuId(uuid());
                        userIdService.addUserId(userIdModel);
                        System.out.println(userIdModel.getId());
                        if (userIdModel.getId() != 0) {
                            UserLoginModel newUserInfo = new UserLoginModel();
                            newUserInfo.setRegisteredTime(new Date());
                            newUserInfo.setRegisteredIp(getIP(request));
                            newUserInfo.setPassword(password);
                            newUserInfo.setUserId(userIdModel.getId());
                            newUserInfo.setLoginMobile(username);
                            newUserInfo.setUserType(userType);/*个人*/
                            newUserInfo.setUserStatus(0);

                            int newusersta = userCenterService.insertSelective(newUserInfo);

                            if (newusersta > 0) {
                                TaskLogSmsCodeServiceImpl taskImpl = new TaskLogSmsCodeServiceImpl();
                                taskImpl.deleteSmsCode(username);
                                retcode = 0;
                                retmsg = "用户注册成功！";
                            } else {
                                retcode = 2;
                                retmsg = "用户注册失败";
                            }

                        } else {
                            retcode = 2;
                            retmsg = "获取用户id失败";
                        }

                    }
                } else {
                    retcode = 2;
                    retmsg = "短信验证码错误！或账户错误";
                }
            } else {
                retcode = 2;
                retmsg = "请重新获取验证码 不存在的验证码";
            }
        } else {
            retcode = 2;
            retmsg = "用户已存在";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String uuid() {
        String s = UUID.randomUUID().toString();

        return s.replace("-", "");

    }

}
