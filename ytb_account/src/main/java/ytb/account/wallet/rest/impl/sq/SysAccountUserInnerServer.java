package ytb.account.wallet.rest.impl.sq;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;

import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.service.CompanyCenterService;
import ytb.user.service.impl.CompanyCenterServiceImpl;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.AccountUserInnerExample;
import ytb.account.wallet.pojo.AccountInfoList;
import ytb.account.wallet.pojo.LoginSsoToInnerId;
import ytb.account.wallet.service.sq.basics.impl.AccountUserInnerServiceImpl;
import ytb.account.wallet.service.service.getUserInfo.ObtainAccountId;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysAccountUserInnerServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    /**
     * 使用用户ID 查询InnerId
     */
    public AccountUserInner getInnerIdByUser(LoginSsoJson loginSsoJson) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();

        ObtainAccountId obtainAccountId = new ObtainAccountId();
        LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

//        if (loginSsoToInnerId != null) {
//            if (loginSsoToInnerId.getUserId() != null) {
//                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
//            }
//            if (loginSsoToInnerId.getCompanyId() != null) {
//                criteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
//            }
//        /*    if (loginSsoToInnerId.getCompanyId() > 0) {
//                criteria.andUserIdEqualTo(0);
//                //    criteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
//            }*/
//

        if (loginSsoToInnerId != null) {
            criteria.andUserTypeEqualTo(Byte.valueOf(loginSsoToInnerId.getUserType().toString()));                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
          /*  if (loginSsoToInnerId.getUserType() == 1) {
                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
            } else if (loginSsoToInnerId.getUserType() == 2) {
                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());


            } else if (loginSsoToInnerId.getUserType() == 3) {
                criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
                criteria.andCompanyIdEqualTo(loginSsoToInnerId.getUserId());
            }*/


        } else {

            return null;
        }

        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);


        if (accountUserInnerList.size() >= 1) {
            return accountUserInnerList.get(0);
        }

        return null;
    }

//    public int getAcID(int userId, int companyId) {
//        List<AccountUserInner> inners = companyId == 0 ? innerServer.getInnerIdByUserById(userId, companyId)
//                : innerServer.getInnerIdByUserById(0, companyId);
//        if (inners.size() == 0) {
//            throw new YtbError(YtbError.CODE_NOTEXISTS_ACCOUNT, userId + "");
//        }
//        return inners.get(0).getInnerId();
//    }


    public MsgResponse accountInfo(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();//JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        //String token = req.token;
        //LoginSso loginSso = SafeContext.getLog_sso(token);
        //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);


        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria accountUserInnerExampleCriteria = accountUserInnerExample.createCriteria();

        ObtainAccountId obtainAccountId = new ObtainAccountId();
        LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

        if (loginSsoToInnerId != null) {
            accountUserInnerExampleCriteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
            accountUserInnerExampleCriteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
        } else {
            retcode = 1;
            retmsg = "用户类型获取失败";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        if (loginSsoJson.getUserId() != null) {


            List<AccountUserInner> accountUserInners = accountUserInnerService.selectByExample(accountUserInnerExample);

            if (accountUserInners != null & accountUserInners.size() > 0) {
                accountUserInners.get(0).setPayPassword(null);
                msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserInners) + "}";
            }
            msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserInners) + "}";


        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse accountInfoMethod(MsgRequest req, RestHandler handler, HttpServletRequest
            request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria accountUserInnerExampleCriteria = accountUserInnerExample.createCriteria();

        //String token = req.token;
        //LoginSso loginSso = SafeContext.getLog_sso(token);
        //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        if (loginSsoJson.getUserType().equals(1)) {
            accountUserInnerExampleCriteria.andUserIdEqualTo(loginSsoJson.getUserId());
        } else if (loginSsoJson.getUserType().equals(2)) {
            accountUserInnerExampleCriteria.andCompanyIdEqualTo(loginSsoJson.getUserId());
        } else {
            retcode = 1;
            retmsg = "数据错误";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        if (loginSsoJson.getUserId() != null) {


            List<AccountUserInner> accountUserInners = accountUserInnerService.selectByExample(accountUserInnerExample);

            accountUserInners.get(0).setPayPassword(null);

            msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserInners) + "}";

        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse deleteAccountInfo(MsgRequest req, RestHandler handler, HttpServletRequest
            request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria accountUserInnerExampleCriteria = accountUserInnerExample.createCriteria();


        AccountUserInner accountUserInner = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);


        if (loginSsoJson.getUserId() != null) {

            accountUserInnerExampleCriteria.andUserIdEqualTo(accountUserInner.getUserId());
            accountUserInner.setUserId(loginSsoJson.getUserId());

            int sta = accountUserInnerService.deleteByExample(accountUserInnerExample);

            if (sta > 0) {
                retcode = 0;
                retmsg = "成功";

            } else {
                retcode = 1;
                retmsg = "数据错误";
            }

        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse newUserInnerInfo(MsgRequest req, MsgHandler handler, HttpServletRequest
            request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        //String token = req.token;
        //LoginSso loginSso = SafeContext.getLog_sso(token);
        //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserInner data = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);

        if (data.getPayPassword() != null & loginSsoJson.getUserId() != null) {

            AccountUserInner accountUserInner = new AccountUserInner();

            accountUserInner.setOpenTime(new Date());

            ObtainAccountId obtainAccountId = new ObtainAccountId();
            LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

            if (loginSsoToInnerId != null) {
                accountUserInner.setUserId(loginSsoToInnerId.getUserId());
                accountUserInner.setCompanyId(loginSsoToInnerId.getCompanyId());
                accountUserInner.setUserType(Byte.valueOf(String.valueOf(loginSsoToInnerId.getUserType())));
            } else {
                retcode = 1;
                retmsg = "用户类型获取失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }


            AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
            accountUserInner.setPayPassword(data.getPayPassword());
            int sta = accountUserInnerService.insertSelective(accountUserInner);

            if (sta > 0) {
                retcode = 0;
                retmsg = "成功";
            } else {
                retcode = 1;
                retmsg = "数据错误";
            }


        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse newDefaultUserInnerInfo(MsgRequest req, MsgHandler handler, HttpServletRequest
            request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        //String token = req.token;
        //LoginSso loginSso = SafeContext.getLog_sso(token);/
        //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        AccountUserInner data = JSONObject.parseObject(req.msgBody.toString(), AccountUserInner.class);

        data.setOpenTime(new Date());


        ObtainAccountId obtainAccountId = new ObtainAccountId();
        LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

        if (loginSsoToInnerId != null) {
            data.setUserId(loginSsoJson.getUserId());
            data.setCompanyId(loginSsoJson.getCompanyId());
            data.setUserType(Byte.valueOf(String.valueOf(loginSsoToInnerId.getUserType())));
        } else {
            retcode = 1;
            retmsg = "用户类型获取失败";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();


        data.setPayPassword("0");


        int sta = accountUserInnerService.insertSelective(data);
        if (sta > 0) {
            retcode = 0;
            retmsg = "成功";
        } else {
            retcode = 1;
            retmsg = "数据错误";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public List<AccountUserInner> getInnerIdByUserOld(LoginSsoJson loginSsoJson) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();

        if (loginSsoJson.getUserType() != null) {
            if (loginSsoJson.getUserType().equals(1)) {
                criteria.andUserIdEqualTo(loginSsoJson.getUserId());
                criteria.andCompanyIdEqualTo(0);
            } else if (loginSsoJson.getUserType().equals(2)) {
                criteria.andCompanyIdEqualTo(loginSsoJson.getCompanyId());
                criteria.andUserIdEqualTo(0);
            } else {
                return null;
            }
        } else {
            return null;
        }







        /* criteria.andUserIdEqualTo(userId);*/
        /* criteria.andPayPasswordEqualTo(data.getPayPassword());*/
        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);

        return accountUserInnerList;

    }


    public List<AccountUserInner> getInnerIdByUserById(Integer userId, Integer companyId) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();


        criteria.andCompanyIdEqualTo(companyId);
        criteria.andUserIdEqualTo(userId);

        /* criteria.andUserIdEqualTo(userId);*/
        /* criteria.andPayPasswordEqualTo(data.getPayPassword());*/
        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);

        return accountUserInnerList;


    }


    public List<AccountUserInner> getInnerIdByUserByUserId(Integer userId) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();


        criteria.andUserIdEqualTo(userId);

        /* criteria.andUserIdEqualTo(userId);*/
        /* criteria.andPayPasswordEqualTo(data.getPayPassword());*/
        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);

        return accountUserInnerList;


    }


    public AccountUserInner getInnerIdByUserObj(LoginSsoJson loginSsoJson) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();

/*
        if (loginSsoJson.getUserType().equals(1)) {
            criteria.andUserIdEqualTo(loginSsoJson.getUserId());
            criteria.andCompanyIdEqualTo(0);
        } else if (loginSsoJson.getUserType().equals(2)) {
            criteria.andCompanyIdEqualTo(loginSsoJson.getCompanyId());
            criteria.andUserIdEqualTo(0);
        } else {
            return null;
        }*/


        ObtainAccountId obtainAccountId = new ObtainAccountId();
        LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

        if (loginSsoToInnerId != null) {
            criteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
            criteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
        } else {
            retcode = 1;
            retmsg = "用户类型获取失败";
            return null;
        }






        /* criteria.andUserIdEqualTo(userId);*/
        /* criteria.andPayPasswordEqualTo(data.getPayPassword());*/
        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);

        if (accountUserInnerList.size() > 0) {
            return accountUserInnerList.get(0);
        } else {
            return null;
        }

    }

    public List<AccountUserInner> getInnerIdByUserIdAndPassword(Integer userId, String password) {
        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = accountUserInnerExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPayPasswordEqualTo(password);
        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(accountUserInnerExample);

        return accountUserInnerList;

    }

    /**
     * 建立账户
     */
    public MsgResponse accountInfoAndEstablish(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample accountUserInnerExample = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria accountUserInnerExampleCriteria = accountUserInnerExample.createCriteria();

        ObtainAccountId obtainAccountId = new ObtainAccountId();
        LoginSsoToInnerId loginSsoToInnerId = obtainAccountId.ssoJsonToInnerId(loginSsoJson);

        if (loginSsoToInnerId != null) {
            accountUserInnerExampleCriteria.andUserIdEqualTo(loginSsoToInnerId.getUserId());
            accountUserInnerExampleCriteria.andCompanyIdEqualTo(loginSsoToInnerId.getCompanyId());
        } else {
            retcode = 1;
            retmsg = "用户类型获取失败";
            return null;
        }


        List<AccountUserInner> accountUserInners = accountUserInnerService.selectByExample(accountUserInnerExample);

        if (accountUserInners.size() <= 0) {


            AccountUserInner data = new AccountUserInner();

            data.setOpenTime(new Date());

            if (loginSsoToInnerId != null) {
                data.setUserId(loginSsoToInnerId.getUserId());
                data.setCompanyId(loginSsoToInnerId.getCompanyId());
            } else {
                retcode = 1;
                retmsg = "用户类型获取失败";
                return null;
            }


            AccountUserInnerServiceImpl newUser = new AccountUserInnerServiceImpl();

            data.setPayPassword("0");


            int actId = newUser.insertSelective(data);
            if (actId > 0) {


                AccountUserInner accountUserInner = null;
                accountUserInner = accountUserInnerService.selectByPrimaryKey(actId);
                accountUserInner.setPayPassword(null);
                msgBody = "{\"list\":[" + JSONObject.toJSONString(accountUserInner) + "}]";


            } else {
                retcode = 1;
                retmsg = "数据错误";
            }


        } else {
            accountUserInners.get(0).setPayPassword(null);
            msgBody = "{\"list\":" + JSONObject.toJSONString(accountUserInners) + "}";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    /**
     * 首次设置密码
     */
    public MsgResponse SetUpPassword(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        String password = req.msgBody.getString("password");
        if (password != null) {

            // String token = req.token;

            //LoginSso loginSso = SafeContext.getLog_sso(token);

            // LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);


            AccountUserInner accountUserInnerList = this.getInnerIdByUser(loginSsoJson);

            AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();


            if (accountUserInnerList.getPayPassword() == null || accountUserInnerList.getPayPassword().equals("0")) {
                AccountUserInner newPassword = new AccountUserInner();
                newPassword.setPayPassword(password);
                newPassword.setInnerId(accountUserInnerList.getInnerId());
                int sta = accountUserInnerService.updateByPrimaryKeySelective(newPassword);
                if (sta > 0) {
                    retcode = 0;
                    retmsg = "成功";
                } else {
                    retcode = 1;
                    retmsg = "设置密码失败";
                }


            } else {
                retcode = 1;
                retmsg = "数据错误,该用户已经设置密码";
            }


        } else {
            retcode = 1;
            retmsg = "缺少参数";
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    /**
     * 修改密码
     */

    public MsgResponse modifyPassword(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();

        String password = req.msgBody.getString("password");
        String oldPassword = req.msgBody.getString("oldPassword");

        if (password != null && oldPassword != null) {
            //LoginSso loginSso = SafeContext.getLog_sso(req.token);

            //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);


            AccountUserInner accountUserInnerList = this.getInnerIdByUser(loginSsoJson);

            if (accountUserInnerList.getPayPassword().equals(oldPassword)) {
                AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();

                AccountUserInner newPassword = new AccountUserInner();
                newPassword.setPayPassword(password);
                newPassword.setInnerId(accountUserInnerList.getInnerId());
                int sta = accountUserInnerService.updateByPrimaryKeySelective(newPassword);

                if (sta > 0) {
                    retcode = 0;
                    retmsg = "成功";

                } else {
                    retcode = 1;
                    retmsg = "设置密码失败";
                }

            } else {
                retcode = 1;
                retmsg = "密码错误";
            }
        } else {
            retcode = 1;
            retmsg = "缺少参数";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    /**
     * 获取账户列表
     */
    public PageUtils accountInfoList(Paging paging, Byte type) {


        AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();
        AccountUserInnerExample example = new AccountUserInnerExample();
        AccountUserInnerExample.Criteria criteria = example.createCriteria();
        criteria.andUserTypeEqualTo(type);


        if (paging != null) {

            if (
                    paging.getToOrder().equals("desc") || paging.getToOrder().equals("asc")) {
                example.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
            }

        }


        List<AccountUserInner> accountUserInnerList = accountUserInnerService.selectByExample(example);


        PageUtils pageUtil = new PageUtils();
        if (paging != null) {
            pageUtil = new PageUtils(accountUserInnerList, accountUserInnerList.size(), paging.getPageSize(), paging.getCurrPage());
            Integer firstIndex = (paging.getCurrPage() - 1) * paging.getPageSize();
            //到第几条数据结束
            Integer lastIndex = paging.getPageSize();

            example.setOffset(firstIndex);
            example.setLimit(lastIndex);

            accountUserInnerList = accountUserInnerService.selectByExample(example);
        }


        UserCenterServiceImpl userLoginCenter = new UserCenterServiceImpl();
        CompanyCenterService companyCenterService = new CompanyCenterServiceImpl();


        //   List<AccountUserInner> accountUserInnerList = new ArrayList<>();
        List<AccountInfoList> accountInfoLists = new ArrayList<>();

        for (int i = 0; i < accountUserInnerList.size(); i++) {
            AccountInfoList accountInfoList = new AccountInfoList();


            UserLoginModel userLoginModels = userLoginCenter.getUserLoginInfoById(accountUserInnerList.get(i).getUserId());

            if (userLoginModels != null) {


                if (userLoginModels.getUserType() == 1) {
                    UserInfoModel userInfoModel = userLoginCenter.getUserInfoById(userLoginModels.getUserId());
                    if (userInfoModel != null) {
                        accountInfoList.setUserInfoModel(userInfoModel);
                    }
                    accountInfoList.setUserInners(accountUserInnerList.get(i));

                    accountInfoList.setUserLoginModel(userLoginModels);
                    accountInfoLists.add(accountInfoList);

                } else if (userLoginModels.getUserType() == 2) {
                    CompanyInfoModel companyInfoModel = companyCenterService.getCompanyInfoByCompanyId(userLoginModels.getCompanyUserId());

                    if (companyInfoModel != null) {
                        accountInfoList.setCompanyInfoModel(companyInfoModel);
                    }

                    accountInfoList.setUserInners(accountUserInnerList.get(i));

                    accountInfoList.setUserLoginModel(userLoginModels);
                    accountInfoLists.add(accountInfoList);
                }

            }

        }


        pageUtil.setList(accountInfoLists);

        return pageUtil;


    }


    /**
     * 使用手机验证码修改密码
     */

    public MsgResponse modifyPasswordByPhone(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        LoginSso loginSso = handler.getUserContext().getLoginSso();
        LoginSsoJson loginSsoJson = loginSso.getLoginSsoJson();


        String password = req.msgBody.getString("password");
        String phone = req.msgBody.getString("phone");
        String code = req.msgBody.getString("code");

        //获取Token绑定的phone
        //LoginSso loginSso = SafeContext.getLog_sso(req.token);
        //LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        UserCenterServiceImpl userCenterService = new UserCenterServiceImpl();
        UserLoginModel userLoginModel = userCenterService.getUserLoginInfoById(loginSsoJson.getUserId());

//获取验证
        TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();
        Tasklog_Sms_CodeModel newCode = taskLogSmsCodeService.selectByPrimaryKey(userLoginModel.getLoginMobile());


        if (userLoginModel.getLoginMobile().equals(phone) && newCode.getSmsCode().equals(code)) {
//修改密码
            AccountUserInner accountUserInnerList = this.getInnerIdByUser(loginSsoJson);
            AccountUserInnerServiceImpl accountUserInnerService = new AccountUserInnerServiceImpl();

            AccountUserInner newPassword = new AccountUserInner();
            newPassword.setPayPassword(password);
            newPassword.setInnerId(accountUserInnerList.getInnerId());
            int sta = accountUserInnerService.updateByPrimaryKeySelective(newPassword);

            if (sta > 0) {
                retcode = 0;
                retmsg = "成功";

            } else {
                retcode = 1;
                retmsg = "设置密码失败";
            }
        } else {
            retcode = 1;
            retmsg = "设置密码失败 手机号不匹配或验证码错误";
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

}
