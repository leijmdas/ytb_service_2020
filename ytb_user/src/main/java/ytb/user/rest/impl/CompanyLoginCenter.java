package ytb.user.rest.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import ytb.common.basic.safecontext.model.LoginConcurrentHashMap;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoExample;

import ytb.common.basic.safecontext.service.LoginSsoService;
import ytb.common.basic.safecontext.service.impl.LoginSsoServiceImpl;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.user.model.CompanyEmployeesModel;
import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.CompanyCenterServiceImpl;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Package: ytb.user.rest.impl
 * Author: Cchua
 * Date: Created in 22018年9月12日
 */
public class CompanyLoginCenter {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody;

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {


        //根据ID获取信息
        if (req.cmd.equals("login")) {
            try {

                String webusername = req.msgBody.getString("username");

                String companyName = req.msgBody.getString("companyName");


                String mode = req.msgBody.getString("mode");
                String sys = "company";


                if (mode.equals("passwordandcode") && webusername != null) {
                    String code = req.msgBody.getString("code");
                    String webpassword = req.msgBody.getString("password");
                    TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
                    Tasklog_Sms_CodeModel templateLogSmsCodeByPhone =
                            taskLogSmsCodeService.getTemplateLogSmsCodeByPhone(webusername);
//验证
                    if (templateLogSmsCodeByPhone != null) {

                        if (templateLogSmsCodeByPhone.getSmsCode().equals(code)) {
                            if (webusername != null || webusername.equals("")) {
                                /*密码核对 ！~ 后期注意改这句*/
                                String md5HexPassword = DigestUtils.md5Hex(webpassword);

                                UserCenterService userCenterService = new UserCenterServiceImpl();
                                UserLoginModel userLoginModel =
                                        userCenterService.getUserLoginInfoByLoginMobileAndUserType(webusername, 2);

                                CompanyCenterServiceImpl companyCenterService = new CompanyCenterServiceImpl();

                                /*密码核对 ！~ 后期注意改这句*/
                                if (userLoginModel != null) {
                                    if (
                                            userLoginModel.getPassword() != null
                                                    && md5HexPassword != null
                                                    && userLoginModel.getCompanyUserId() != null) {

                                        CompanyInfoModel companyInfoModel = companyCenterService.
                                                getCompanyInfoByCompanyId(userLoginModel.getCompanyUserId());


                                        if (companyInfoModel != null) {
                                            if (md5HexPassword.equals(DigestUtils.md5Hex(userLoginModel.getPassword()))
                                                    && companyInfoModel.getCompanyName().equals(companyName)) {

                                                String token = UUID.randomUUID().toString();
                                                token = token.replace("-", "");
                                                userLoginModel.setLoginToken(token);
                                                userLoginModel.setLoginIp(getIP(request));
                                                int tokenState =
                                                        userCenterService.updateByPrimaryKeySelective(userLoginModel);
                                                if (tokenState > 0) {

                                                    LoginSsoService loginSsoService = new LoginSsoServiceImpl();
                                                    LoginSso loginSso = new LoginSso();
                                                    loginSso.setLoginIp(getIP(request));
                                                    loginSso.setLoginTime(new Date());
                                                    loginSso.setUserid(userLoginModel
                                                            .getUserId().longValue());
                                                    /*  loginSso.setSsoid(userLoginModel.getUserId());*/
                                                    loginSso.setToken(token);

                                                    LoginSsoExample loginSsoExample = new LoginSsoExample();
                                                    LoginSsoExample.Criteria criteria = loginSsoExample.createCriteria();
                                                    criteria.andUseridEqualTo(userLoginModel.getUserId());
                                                    List<LoginSso> loginssinfo =
                                                            loginSsoService.selectByExample(loginSsoExample);

                                                    int addLoginSsoState = 0;

                                                    if (loginssinfo.size() > 0) {

                                                        loginSso.setSsoid(loginssinfo.get(0).getSsoid());
                                                        addLoginSsoState =
                                                                loginSsoService.updateByPrimaryKeySelective(loginSso);

                                                        LoginConcurrentHashMap.remove(loginssinfo.get(0).getToken());
                                                        LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
                                                    } else {


                                                        addLoginSsoState = loginSsoService.addLoginSso(loginSso);

                                                        LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
                                                    }

                                                    if (addLoginSsoState > 0) {
                                                        taskLogSmsCodeService.deleteSmsCode(webusername);
                                                        JSONObject tokenobj = new JSONObject();
                                                        tokenobj.put("userToken", token);
                                                        msgBody = tokenobj.toJSONString();

                                                    } else {
                                                        retcode = 1;
                                                        retmsg = "数据库用户状态记录失败";

                                                    }

                                                } else {
                                                    retcode = 1;
                                                    retmsg = "数据库token记录失败";

                                                }
                                            } else {
                                                retcode = 2;
                                                retmsg = "账户或密码错误或企业信息错误";
                                            }
                                        } else {
                                            retcode = 2;
                                            retmsg = "不存在的企业名";
                                        }

                                    } else {
                                        retcode = 2;
                                        retmsg = "账户或密码或企业信息错误错误";
                                    }
                                }

                            }
                        } else {
                            retcode = 2;
                            retmsg = "短信验证码错误！";
                        }
                    } else {
                        retcode = 2;
                        retmsg = "请获取验证码！";
                    }

                }
                if (mode.equals("verification") && webusername != null) {
                    String code = req.msgBody.getString("code");

                 /*   TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
                    Tasklog_Sms_CodeModel templateLogSmsCodeByPhone =
                            taskLogSmsCodeService.getTemplateLogSmsCodeByPhone(webusername);
*/
                    TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
                    Tasklog_Sms_CodeModel templateLogSmsCodeByPhone =
                            taskLogSmsCodeService.selectByPrimaryKey(webusername);


//验证
                    if (templateLogSmsCodeByPhone != null && templateLogSmsCodeByPhone.getSmsCode() != null && code != null) {

                        if (templateLogSmsCodeByPhone.getSmsCode().equals(code)) {
                            if (webusername != null || webusername.equals("")) {
                                /*密码核对 ！~ 后期注意改这句*/


                                UserCenterService userCenterService = new UserCenterServiceImpl();
                                UserLoginModel userLoginModel =
                                        userCenterService.getUserLoginInfoByLoginMobileAndUserType(webusername, 2);

                                CompanyCenterServiceImpl companyCenterService = new CompanyCenterServiceImpl();

                                /*密码核对 ！~ 后期注意改这句*/
                                if (userLoginModel != null) {
                                    if (userLoginModel.getCompanyUserId() != null) {
                                        CompanyInfoModel companyInfoModel = companyCenterService.
                                                getCompanyInfoByCompanyId(userLoginModel.getCompanyUserId());
                                        if (companyInfoModel != null) {
                                            if (companyInfoModel.getCompanyName().equals(companyName)) {
                                                String token = UUID.randomUUID().toString();
                                                token = token.replace("-", "");
                                                userLoginModel.setLoginToken(token);
                                                userLoginModel.setLoginIp(getIP(request));
                                                int tokenState
                                                        = userCenterService.updateByPrimaryKeySelective(userLoginModel);
                                                if (tokenState > 0) {
                                                    LoginSsoService loginSsoService = new LoginSsoServiceImpl();
                                                    LoginSso loginSso = new LoginSso();
                                                    loginSso.setLoginIp(getIP(request));
                                                    loginSso.setLoginTime(new Date());
                                                    loginSso.setUserid(userLoginModel
                                                            .getUserId().longValue());
                                                    /*  loginSso.setSsoid(userLoginModel.getUserId());*/
                                                    loginSso.setToken(token);
                                                    JSONObject LoginInfoJson = new JSONObject();
                                                    LoginInfoJson.put("userToken", token);
                                                    LoginInfoJson.put("customerType", "companyUser");
                                                    LoginInfoJson.put("userType", "2");
                                                    LoginInfoJson.put("companyUserId", userLoginModel.getCompanyUserId());
                                                    LoginInfoJson.put("loginMode", "password");

                                                    List<CompanyEmployeesModel> companyEmployeesListById = companyCenterService.getCompanyEmployeesListById(userLoginModel.getCompanyUserId());
                                                    if (companyEmployeesListById.size() > 0) {
                                                        companyEmployeesListById.get(0).getIsAdmin();
                                                        LoginInfoJson.put("isAdmin", companyEmployeesListById.get(0).getIsAdmin());
                                                    }


                                                    loginSso.setJson(LoginInfoJson.toJSONString());
                                                    LoginSsoExample loginSsoExample = new LoginSsoExample();
                                                    LoginSsoExample.Criteria criteria = loginSsoExample.createCriteria();
                                                    criteria.andUseridEqualTo(userLoginModel.getUserId());
                                                    List<LoginSso> loginssinfo
                                                            = loginSsoService.selectByExample(loginSsoExample);
                                                    int addLoginSsoState = 0;

                                                    if (loginssinfo.size() > 0) {
                                                        loginSso.setSsoid(loginssinfo.get(0).getSsoid());
                                                        addLoginSsoState
                                                                = loginSsoService.updateByPrimaryKeySelective(loginSso);
                                                        LoginConcurrentHashMap.remove(loginssinfo.get(0).getToken());
                                                        LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
                                                    } else {
                                                        addLoginSsoState = loginSsoService.addLoginSso(loginSso);

                                                        LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);

                                                    }

                                                    if (addLoginSsoState > 0) {
                                                        taskLogSmsCodeService.deleteByPrimaryKey(webusername);
                                                        JSONObject tokenobj = new JSONObject();
                                                        tokenobj.put("userToken", token);
                                                        tokenobj.put("userId", userLoginModel.getUserId());
                                                        msgBody = tokenobj.toJSONString();

                                                    } else {
                                                        retcode = 1;
                                                        retmsg = "数据库用户状态记录失败";
                                                    }

                                                } else {
                                                    retcode = 1;
                                                    retmsg = "数据库token记录失败";
                                                }
                                            } else {
                                                retcode = 2;
                                                retmsg = "账户或或企业信息错误";
                                            }
                                        } else {
                                            retcode = 2;
                                            retmsg = "不存在的企业名";
                                        }

                                    } else {
                                        retcode = 2;
                                        retmsg = "账户或密码或企业信息错误错误";
                                    }
                                } else {
                                    retcode = 2;
                                    retmsg = "用户名不存在";
                                }

                            }
                        } else {
                            retcode = 2;
                            retmsg = "短信验证码错误！";
                        }
                    } else {
                        retcode = 2;
                        retmsg = "用户或验证码错误或验证码失效！";
                    }
                }
                if (mode.equals("password") && webusername != null) {
                    String webpassword = req.msgBody.getString("password");
                    TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();

//验证


                    if (webusername != null || webusername.equals("")) {
                        /*密码核对 ！~ 后期注意改这句*/
                        String md5HexPassword = DigestUtils.md5Hex(webpassword);

                        UserCenterService userCenterService = new UserCenterServiceImpl();
                        UserLoginModel userLoginModel = userCenterService.getUserLoginInfoByLoginMobileAndUserType(webusername, 2);

                        CompanyCenterServiceImpl companyCenterService = new CompanyCenterServiceImpl();

                        /*密码核对 ！~ 后期注意改这句*/
                        if (userLoginModel != null) {
                            if (
                                    userLoginModel.getPassword() != null
                                            && md5HexPassword != null
                                            && userLoginModel.getCompanyUserId() != null) {

                                CompanyInfoModel companyInfoModel = companyCenterService.
                                        getCompanyInfoByCompanyId(userLoginModel.getCompanyUserId());


                                if (companyInfoModel != null) {
                                    if (md5HexPassword.equals(DigestUtils.md5Hex(userLoginModel.getPassword()))
                                            && companyInfoModel.getCompanyName().equals(companyName)) {

                                        String token = UUID.randomUUID().toString();
                                        token = token.replace("-", "");
                                        userLoginModel.setLoginToken(token);
                                        userLoginModel.setLoginIp(getIP(request));
                                        int tokenState = userCenterService.updateByPrimaryKeySelective(userLoginModel);
                                        if (tokenState > 0) {

                                            LoginSsoService loginSsoService = new LoginSsoServiceImpl();
                                            LoginSso loginSso = new LoginSso();
                                            loginSso.setLoginIp(getIP(request));
                                            loginSso.setLoginTime(new Date());
                                            loginSso.setUserid(userLoginModel
                                                    .getUserId().longValue());
                                            /*  loginSso.setSsoid(userLoginModel.getUserId());*/
                                            loginSso.setToken(token);
                                            JSONObject LoginInfoJson = new JSONObject();
                                            LoginInfoJson.put("userToken", token);
                                            LoginInfoJson.put("customerType", "companyUser");
                                            LoginInfoJson.put("userType", "2");
                                            LoginInfoJson.put("companyUserId", userLoginModel.getCompanyUserId());
                                            LoginInfoJson.put("loginMode", "code");
                                            List<CompanyEmployeesModel> companyEmployeesListById = companyCenterService.getCompanyEmployeesListById(userLoginModel.getCompanyUserId());
                                            if (companyEmployeesListById.size() > 0) {
                                                companyEmployeesListById.get(0).getIsAdmin();
                                                LoginInfoJson.put("isAdmin", companyEmployeesListById.get(0).getIsAdmin());
                                            }

                                            loginSso.setJson(LoginInfoJson.toJSONString());
                                            LoginSsoExample loginSsoExample = new LoginSsoExample();
                                            LoginSsoExample.Criteria criteria = loginSsoExample.createCriteria();
                                            criteria.andUseridEqualTo(userLoginModel.getUserId());
                                            List<LoginSso> loginssinfo = loginSsoService.selectByExample(loginSsoExample);

                                            int addLoginSsoState = 0;

                                            if (loginssinfo.size() > 0) {
                                                loginSso.setSsoid(loginssinfo.get(0).getSsoid());
                                                addLoginSsoState = loginSsoService.updateByPrimaryKeySelective(loginSso);
                                                LoginConcurrentHashMap.remove(loginssinfo.get(0).getToken());
                                                LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);

                                            } else {
                                                addLoginSsoState = loginSsoService.addLoginSso(loginSso);

                                                LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
                                            }

                                            if (addLoginSsoState > 0) {
                                                JSONObject tokenobj = new JSONObject();
                                                tokenobj.put("userToken", token);
                                                msgBody = tokenobj.toJSONString();
                                            } else {
                                                retcode = 1;
                                                retmsg = "数据库用户状态记录失败";

                                            }

                                        } else {
                                            retcode = 1;
                                            retmsg = "数据库token记录失败";

                                        }
                                    } else {
                                        retcode = 2;
                                        retmsg = "公司信息错误或密码错误";
                                    }
                                } else {
                                    retcode = 2;
                                    retmsg = "不存在的公司名";
                                }

                            } else {
                                retcode = 2;
                                retmsg = "账户或密码或企业信息错误错误";
                            }
                        } else {
                            retcode = 2;
                            retmsg = "不存在的公司或账户不存在";
                        }

                    }
                }

            } catch (Exception e) {

                retcode = 1;
                retmsg = "缺少数据或错误";
            }

        } else if (req.cmd.equals("register")) {

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


}
