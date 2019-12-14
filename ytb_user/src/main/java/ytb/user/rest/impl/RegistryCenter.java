package ytb.user.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaExample;
import ytb.manager.charges.service.impl.DictAreaServiceImpl;
import ytb.common.utils.YtbUtils;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.common.basic.userid.model.BBNoModel;
import ytb.common.basic.userid.model.User_IdModel;
import ytb.common.basic.userid.service.BBNoService;
import ytb.common.basic.userid.service.impl.BBNoServiceImpl;
import ytb.common.basic.userid.service.impl.UserIdServiceImpl;
import ytb.user.context.UserSrvContext;
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
public class RegistryCenter {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody;

    int getBbNo() {
        //产生邦邦号
        BBNoService bbNoService = new BBNoServiceImpl();
        BBNoModel bbNoModel = new BBNoModel();
        bbNoModel.setUuId(uuid());
        bbNoService.addBBNOInfo(bbNoModel);
        return bbNoModel.getId();
    }

    Integer getAreaIdByIp( HttpServletRequest request){
        Integer areaId = 6;
        String Ip = getIP(request);
        if (Ip != null) {
            //newUserInfo.setRegisteredIp(Ip);
            areaId = getCityId(Ip);
        }
        return areaId;
    }

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request) {

        try {
            int userType = 0;
            if (req.cmd.equals("user")) {
                userType = 1;

                String username = req.msgBody.getString("username");
                String password = req.msgBody.getString("password");
                String code = req.msgBody.getString("code");
                String model = req.msgBody.getString("model");


                UserCenterService userCenterService = UserSrvContext.getInst().getUserCenterService();
                List<UserLoginModel> userLoginModel =
                        userCenterService.getUserLoginInfoByLoginMobileAndUserTypeList(username, userType);

                if (userLoginModel.size() <= 0) {

                    TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
                    Tasklog_Sms_CodeModel templateLogSmsCodeByPhone = taskLogSmsCodeService.selectByPrimaryKey(username);

                    //验证
                    if (templateLogSmsCodeByPhone != null) {

                        if (templateLogSmsCodeByPhone.getSmsCode().equals(code)) {
                            if (username != null && !username.isEmpty()) {


                                UserIdServiceImpl userIdService = new UserIdServiceImpl();

                                User_IdModel userIdModel = new User_IdModel();
                                userIdModel.setUuId(uuid());
                                userIdService.addUserId(userIdModel);


                                if (userIdModel.getId() != 0) {
                                    UserLoginModel newUserInfo = new UserLoginModel();
                                    newUserInfo.setRegisteredTime(new Date());
                                    newUserInfo.setUserType(userType);/*1个人 2 公司*/
                                    newUserInfo.setNickName(username+System.currentTimeMillis());
                                    newUserInfo.setPassword(password);
                                    newUserInfo.setUserId(userIdModel.getId());
                                    newUserInfo.setBangBangNo(getBbNo()+"");
                                    newUserInfo.setLoginMobile(username);
                                    newUserInfo.setUserStatus(0);
                                    newUserInfo.setCompanyUserId(userIdModel.getId());
                                    Integer areaId=getAreaIdByIp(request);
                                    newUserInfo.setRegisteredIp(areaId.toString());
                                    newUserInfo.setLoginNumber(0);

                                    int newusersta = userCenterService.newUser(newUserInfo,areaId);

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


            } else if (req.cmd.equals("company")) {
                userType = 2;

                String username = req.msgBody.getString("username");
                String password = req.msgBody.getString("password");
                String code = req.msgBody.getString("code");

                String companyName = req.msgBody.getString("companyName");
                String contacts = req.msgBody.getString("contacts");

                String phoneNumber = req.msgBody.getString("phoneNumber");
                String email = req.msgBody.getString("email");
                String address = req.msgBody.getString("address");


                String licenseUrl = req.msgBody.getString("licenseUrl");
                String licenseCode = req.msgBody.getString("licenseCode");

                UserCenterService userCenterService = new UserCenterServiceImpl();
                List<UserLoginModel> userLoginModel =
                        userCenterService.getUserLoginInfoByLoginMobileAndUserTypeList(username, userType);
                if (userLoginModel.size() > 0) {
                    retcode = 2;
                    retmsg = "用户已存在";
                } else {

                    TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();

                    Tasklog_Sms_CodeModel templateLogSmsCodeByPhone = taskLogSmsCodeService.selectByPrimaryKey(username);


                    //验证
                    if (templateLogSmsCodeByPhone != null) {

                        if (templateLogSmsCodeByPhone.getSmsCode().equals(code)) {
                            if (username != null && !username.isEmpty()) {


                                UserIdServiceImpl userIdService = new UserIdServiceImpl();

                                User_IdModel userIdModel = new User_IdModel();
                                userIdModel.setUuId(uuid());

                                userIdService.addUserId(userIdModel);

                                if (userIdModel.getId() != 0 /*&& userIdModelCom.getId() != 0*/) {

                                    CompanyCenterServiceImpl companyCenterService = new CompanyCenterServiceImpl();
                                    CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                                    companyInfoModel.setCompanyId(userIdModel.getId());//产生ID，设置到company_info表


                                    companyInfoModel.setContacts(contacts);
                                    companyInfoModel.setCompanyName(companyName);
                                    companyInfoModel.setPhoneNumber(phoneNumber);
                                    companyInfoModel.setEmail(email);
                                    companyInfoModel.setAddress(address);
                                    companyInfoModel.setCompanyUserId(userIdModel.getId());
                                    companyInfoModel.setLicenseUrl(licenseUrl);
                                    companyInfoModel.setLicenseCode(licenseCode);
                                    Date time = new Date();
                                    companyInfoModel.setCreateTime(time);
                                    companyInfoModel.setUpadateTime(time);


                                    UserLoginModel newUserInfo = new UserLoginModel();
                                    newUserInfo.setUserType(userType);/*1个人 2 公司*/
                                    newUserInfo.setNickName(username+System.currentTimeMillis());
                                    newUserInfo.setRegisteredTime(time);
                                    newUserInfo.setBangBangNo(getBbNo() + "");
                                    newUserInfo.setPassword(password);
                                    newUserInfo.setUserId(userIdModel.getId());
                                    newUserInfo.setLoginMobile(username);
                                    newUserInfo.setCompanyUserId(userIdModel.getId());
                                    newUserInfo.setUserStatus(0);
                                    newUserInfo.setLoginNumber(0);

                                    Integer areaId=getAreaIdByIp(request);
                                    newUserInfo.setRegisteredIp(areaId.toString());
                                    int newusersta = userCenterService.newComUser(newUserInfo, companyInfoModel, areaId);


                                    if (newusersta > 0 /*&& comInfo > 0*/) {
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
                } //else {
                  //  retcode = 2;
                  //  retmsg = "用户已存在";
                //}

            } else {
                return handler.buildMsg(retcode, retmsg, msgBody);
            }


        }
        catch (Exception e) {
            throw e;
//            retcode = 2;
//            retmsg = "创建用户失败";
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

    public Integer getCityId(String ip) {
        Integer areaId;
        if (ip.equals("mysql.kunlong.com")) {
            areaId = 6;
        } else {
            String ips = YtbUtils.getAreaByIP(ip);

            //  ips = YtbContext.getYtb_context().getAreaByIP("111.222.241.139");
            JSONObject json = JSONObject.parseObject(ips);
            JSONObject data = JSONObject.parseObject(json.getString("data"));
            data.getString("city");

            DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();

            DictAreaExample dictAreaExample = new DictAreaExample();
            dictAreaExample.createCriteria().andNameLike("%" + data.getString("city") + "%");

            List<DictArea> areas = dictAreaService.selectByExample(dictAreaExample);
            if (areas.size() > 0) {
                areaId = areas.get(0).getAreaId();

            } else {
                areaId = 6;
            }

        }
        return areaId;
    }


    public static String uuid() {
        String s = UUID.randomUUID().toString();

        return s.replace("-", "");

    }

}
