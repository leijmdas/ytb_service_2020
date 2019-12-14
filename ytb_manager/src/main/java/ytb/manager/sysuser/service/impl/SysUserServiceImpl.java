package ytb.manager.sysuser.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.sysuser.dao.SysUserMapper;
import ytb.manager.sysuser.model.SysUserModel;
import ytb.manager.sysuser.service.SysUserService;
import ytb.common.context.model.YtbError;

import java.util.*;

/**
 * Package: ytb.manager.sysuser.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/21 20:01
 */
public class SysUserServiceImpl extends SysUserDAOService implements SysUserService, SysUserMapper {

    @Override
    public void updatePassword(String newPassword, int userId, String oldPwd) {
        try (SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true)) {
            StringBuilder sql = new StringBuilder(128);
            sql.append(" select * from ytb_manager.sys_user");
            sql.append(" where userId=").append(userId);
            sql.append(" where password=").append(oldPwd);
            SysUserModel sysUserModel = YtbSql.selectOne(sql, SysUserModel.class);
            if (sysUserModel != null) {
                SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
                userDao.updatePassword(newPassword, userId);
            }

        }
    }
    //判断后台用户登录
    @Override
    public Map<String, Object> checkUserByUserName(String userName, String password, String ip) {

        SysUserModel sysUserModel = getUserByUserNameModel(userName);
        checkUserInfo(sysUserModel, password, 1);
        return saveLoginInfo(sysUserModel, ip);

    }


    @Override
    public Map<String, Object> checkUserByMobile(String mobile, String ip) {

        SysUserModel sysUserModel = getUserByMobileModel(mobile);
        checkUserInfo(sysUserModel, null, 2);
        return saveLoginInfo(sysUserModel, ip);
    }


    void checkUserInfo(SysUserModel sysUserModel, String password, Integer loginType) {
        if (sysUserModel == null) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }
        if (!sysUserModel.getStatus()) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }
        if (loginType == 1 && !DigestUtils.md5Hex(password).equals(sysUserModel.getPassword())) {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }

    }

    Map<String, Object> saveLoginInfo(SysUserModel sysUserModel, String ip) {

        String token = YtbUtils.getUUID(true);
        String refresh_token = YtbUtils.getUUID(true);
        LoginSso loginSso = new LoginSso();
        loginSso.setUserid(sysUserModel.getUserId());
        loginSso.setToken(token);
        loginSso.setLoginIp(ip);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("userType", LoginSsoJson.USER_TYPE_MANAGER);//sysuser
        body.put("testFlag", sysUserModel.getTestFlag());
        body.put("token", token);
        body.put("refresh_token", refresh_token);
        body.put("userId", sysUserModel.getUserId());

        body.put("expires_in", 300);
        body.put("bangbang_no", sysUserModel.getBangbangNo());
        body.put("nickName", sysUserModel.getUserName());
        body.put("userName", sysUserModel.getUserName());
        body.put("login_mobile", sysUserModel.getMobile());
        loginSso.setJson(JSONObject.toJSONString(body));
        loginSso.setLoginTime(new Date());
        YtbContext.getYtb_context().getSafeContext().save2DB(token, loginSso);

        return body;
    }

}

