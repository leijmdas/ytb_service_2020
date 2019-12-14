package ytb.user.rest.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoExample;

import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.model.LoginConcurrentHashMap;
import ytb.common.basic.safecontext.service.LoginSsoService;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.basic.safecontext.service.impl.LoginSsoServiceImpl;
import ytb.common.basic.safelog.model.Tasklog_UserModel;
import ytb.common.basic.safelog.service.Tasklog_UserServiceImpl;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.user.model.CompanyEmployeesModel;
import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.CompanyCenterServiceImpl;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Package: ytb.user.rest.impl
 * Author: Cchua / leijming
 * Date: Created in 22018年11月8日
 */
public class UserLoginCenter {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody;
    UserCenterService userCenterService = new UserCenterServiceImpl();

    class LoginInfo {
        int userType;
        String companyName;
        String webusername;
        String webpassword;
        String code;
        String ip;
        String login_mode;

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getWebusername() {
            return webusername;
        }

        public void setWebusername(String webusername) {
            this.webusername = webusername;
        }

        public String getWebpassword() {
            return webpassword;
        }

        public void setWebpassword(String webpassword) {
            this.webpassword = webpassword;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }


        public String getLogin_mode() {
            return login_mode;
        }

        public void setLogin_mode(String login_mode) {
            this.login_mode = login_mode;
        }


    }

    int insertUserLog(UserLoginModel loginModel, MsgRequest req) {
        Tasklog_UserServiceImpl us = new Tasklog_UserServiceImpl();
        Tasklog_UserModel m = new Tasklog_UserModel();
        m.setUserId(loginModel.getUserId());
        m.setUserIp(loginModel.getLoginIp());
        m.setOprtName(req.cmdtype + "::" + req.cmd);
        return us.insert(m);
    }

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {

        //根据ID获取信息
        if (req.cmd.equals("login")) {
            int userType = req.msgBody.getInteger("userType");
            String companyName = req.msgBody.getString("companyName");
            String webusername = req.msgBody.getString("username");
            String webpassword = req.msgBody.getString("password");
            String code = req.msgBody.getString("code");
            String ip = YtbUtils.getIpAddr(request);
            String login_mode = req.msgBody.getString("mode");

            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUserType(userType);
            loginInfo.setCompanyName(companyName);
            loginInfo.setWebusername(webusername);
            loginInfo.setWebpassword(webpassword);
            loginInfo.setCode(code);
            loginInfo.setIp(ip);
            loginInfo.setLogin_mode(login_mode);
            if (webusername == null || webusername.isEmpty()) {
                throw new YtbError(YtbError.CODE_INVALID_USER);
            }
            LoginSso loginSso = null;
            if (login_mode.equals("password")) {
                loginSso = verifyPassword(req, loginInfo);
            } else if (login_mode.equals("verifyCode")) {
                loginSso = verifyCode(req, loginInfo);
            } else {
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }
            if(loginSso!=null){
                msgBody = buildLoginMsgBody(loginSso);
            }

        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    String buildLoginMsgBody(LoginSso loginSso) {
        JSONObject result = new JSONObject();
        result.put("userToken", loginSso.getToken());
        result.put("refresh_token", loginSso.getLoginSsoJson().getRefresh_token());

        LoginSso ret = SafeContext.getLog_ssoAndApiKey(loginSso.getToken());
        result.put("list", ret);
        msgBody = result.toJSONString();
        retmsg = "成功";
        retcode = 0;
        return msgBody;
    }

    LoginSso verifyPassword(MsgRequest req,LoginInfo loginInfo) {

        if (loginInfo.getWebpassword() == null || loginInfo.getWebpassword().isEmpty()) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }
        String md5HexPassword = DigestUtils.md5Hex(loginInfo.getWebpassword());
        UserLoginModel userLoginModel = userCenterService.getUserLoginInfoByLoginMobileAndUserType(
                loginInfo.getWebusername(), loginInfo.getUserType());
        if (userLoginModel == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        if (md5HexPassword.equals(DigestUtils.md5Hex(userLoginModel.getPassword()))) {
            userLoginModel.setLoginIp(loginInfo.getIp());
            LoginSso loginSso=saveLoginInfo(loginInfo, userLoginModel);
            insertUserLog(userLoginModel, req);
            return loginSso;
        }
        insertUserLog(userLoginModel, req);
        throw new YtbError(YtbError.CODE_INVALID_USER);
    }

    LoginSso verifyCode(MsgRequest req,LoginInfo loginInfo) {
        String code = loginInfo.getCode();
        String webusername = loginInfo.getWebusername();
        int userType = loginInfo.getUserType();

        if (code == null || code.isEmpty()) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }
        TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
        Tasklog_Sms_CodeModel templateLogSmsCodeByPhone = taskLogSmsCodeService.selectByPrimaryKey(webusername);

        if (templateLogSmsCodeByPhone != null && templateLogSmsCodeByPhone.getSmsCode().equals(code)) {
            UserLoginModel userLoginModel = userCenterService.getUserLoginInfoByLoginMobileAndUserType(webusername, userType);
            userLoginModel.setLoginIp(loginInfo.getIp());
            LoginSso loginSso=saveLoginInfo(loginInfo, userLoginModel);
            insertUserLog(userLoginModel, req);
            return loginSso;
        }
        //insertUserLog(userLoginModel, req);
        throw new YtbError(YtbError.CODE_INVALID_USER);

    }

    LoginSso saveLoginInfo(LoginInfo loginInfo, UserLoginModel userLoginModel) {
        LoginSsoJson loginSsoJson = new LoginSsoJson();

        loginSsoJson.setCompanyId(0);
        loginSsoJson.setUserType(userLoginModel.getUserType());
        if (loginInfo.getUserType() == 2) {
            CompanyEmployeesModel e = checkCompanyName(userLoginModel.getUserId(), loginInfo.getCompanyName());
            loginSsoJson.setCompanyId(e.getCompanyId());
            if (e.getIsAdmin()) {
                loginSsoJson.setUserType(3);
            }
        }
        String token = YtbUtils.getUUID(true);
        String refresh_token = YtbUtils.getUUID(true);

        userLoginModel.setLoginToken(token);
        userLoginModel.setLoginTime(new Date());
        userCenterService.updateByPrimaryKeySelective(userLoginModel);

        LoginSso loginSso = new LoginSso();
        loginSso.setLoginSsoJson(loginSsoJson);

        loginSso.setLoginIp(userLoginModel.getLoginIp());
        loginSso.setLoginTime(new Date());
        loginSso.setUserid(userLoginModel.getUserId().longValue());
        loginSso.setToken(token);
        UserInfoModel uim = userCenterService.getUserInfoById(userLoginModel.getUserId());
        loginSsoJson.setCreditGrade(uim.getCreditGrade());
        loginSsoJson.setTestFlag(uim.getTestFlag());

        loginSsoJson.setUserId(userLoginModel.getUserId());
        loginSsoJson.setNick_name(userLoginModel.getNickName());
        loginSsoJson.setBangbang_no(userLoginModel.getBangBangNo());
        loginSsoJson.setCompanyUserId(userLoginModel.getCompanyUserId());
        loginSsoJson.setCustomerType(userLoginModel.getUserType() == 1 ? "user" : "companyUser");
        loginSsoJson.setLoginMode(loginInfo.getLogin_mode());
        loginSsoJson.setLogin_mobile(userLoginModel.getLoginMobile());
        loginSsoJson.setToken(token);
        loginSsoJson.setRefresh_token(refresh_token);
        loginSsoJson.setExpires_in(3600L);

        loginSso.setJson(JSONObject.toJSONString(loginSsoJson));
        //System.err.println(JSONObject.toJSONString(loginSsoJson));

        LoginSsoExample loginSsoExample = new LoginSsoExample();
        LoginSsoExample.Criteria criteria = loginSsoExample.createCriteria();
        criteria.andUseridEqualTo(userLoginModel.getUserId());

        LoginSsoService loginSsoService = new LoginSsoServiceImpl();
        List<LoginSso> loginssinfo = loginSsoService.selectByExample(loginSsoExample);
        int addLoginSsoState = 0;
        if (loginssinfo.size() > 0) {
            loginSso.setSsoid(loginssinfo.get(0).getSsoid());
            addLoginSsoState = loginSsoService.updateByPrimaryKeySelective(loginSso);
            LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
        } else {
            addLoginSsoState = loginSsoService.addLoginSso(loginSso);
            LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
        }
        if (addLoginSsoState <= 0) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }
//        JSONObject result = new JSONObject();
//        result.put("userToken", token);
//        result.put("refresh_token", loginSsoJson.getRefresh_token());
//
//        msgBody = result.toJSONString();
//        retmsg = "成功";
//        retcode = 0;
        return loginSso;
    }

    /*
     * 返回公司员工信息
     *
     * */
    CompanyEmployeesModel checkCompanyName(int userId, String companyName) {

        CompanyCenterServiceImpl companyCenterService = new CompanyCenterServiceImpl();
        CompanyInfoModel companyInfo = companyCenterService.getCompanyInfoByName(companyName);
        System.out.println(JSONObject.toJSONString(companyInfo));
        if (companyInfo == null) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }
        System.out.println(userId);
        System.out.println(companyName);
        System.out.println(companyInfo.getCompanyId());
        List<CompanyEmployeesModel> lst = companyCenterService.getCompanyEmployeesListById(companyInfo.getCompanyId());
        lst = lst.stream().filter(x -> x.getCompanyUserId() == userId)
                .collect(Collectors.toList());
        if (lst.size() > 0) {
            return lst.get(0);
        }
        throw new YtbError(YtbError.CODE_INVALID_COMPANY_EMP);
    }
}


