package ytb.common.basic.safecontext.service;


import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.cache.Cache;
import ytb.common.basic.safecontext.model.Api_KeyModel;
import ytb.common.basic.safecontext.model.LoginConcurrentHashMap;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoExample;
import ytb.common.ehcache.EhcacheContext;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.service.impl.RestRightCacheService;
import ytb.common.basic.safecontext.service.impl.LoginSsoServiceImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.IUserContext;
import ytb.common.context.service.impl.YtbContext;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.rest.impl
 * Author: Cchua
 * Date: Created in 2018年9月12日
 */
public final class SafeContext {
    private static long l_timeout = 240 * 60;
    private static long expires_in = 240 * 60;

    static boolean checkTimeout(LoginSso ls) {
        return System.currentTimeMillis() - ls.getLoginTime().getTime() >= l_timeout * 1000;
    }

    public static LoginSso getLog_ssoAndApiKey(String token){

        LoginSso loginSso = SafeContext.getLog_sso(token);
        if (loginSso == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        Api_KeyModel keyModel = selectApiKey( loginSso.getUserId().intValue());
        loginSso.getLoginSsoJson().setApi_keyModel(keyModel==null?new Api_KeyModel():keyModel);
        return loginSso;
    }

    public static LoginSso getLog_sso(String token) {
        LoginSso sso = LoginConcurrentHashMap.get(token);
        if (sso == null && token != null) {
            sso = loadFromDB(token);
            if (sso != null) {
                LoginConcurrentHashMap.put(token, sso);
            }
        }
        if (sso != null) {
            sso.parseLoginSsoJson();
        }
        return sso;
    }

    public static LoginSso getLog_sso_catch(String token) {
        LoginSso sso = LoginConcurrentHashMap.get(token);
        if (sso == null && token != null) {
            sso = loadFromDB_ignorError(token);
            if (sso != null) {
                LoginConcurrentHashMap.put(token, sso);
            }
        }
        if (sso != null) {
            sso.parseLoginSsoJson();
        }
        return sso;

    }

    public static LoginSso refreshToken(String token, String refresh_token) {
        LoginSso sso = getLog_sso(token);
        if (sso == null) {
            sso = loadFromDB(token);
        }
        JSONObject json = JSONObject.parseObject(sso.getJson().toString());
        if (token.equals(json.getString("token"))
                && refresh_token.equals(json.getString("refresh_token"))) {
            token = YtbUtils.getUUID(true);
            refresh_token = YtbUtils.getUUID(true);

            json.put("token", token);
            json.put("refresh_token", refresh_token);
            json.put("expires_in", expires_in);
            sso.setJson(json.toJSONString());
            sso.setSsoid(null);
            delete(sso);
            sso.setToken(token);
            sso.setLoginTime(new Date());
            LoginSsoService loginSsoService = new LoginSsoServiceImpl();

            int a = loginSsoService.addLoginSso(sso);    //save2DB(token,sso);
            return getLog_sso(token);
        } else {
            throw new YtbError(YtbError.CODE_INVALID_USER);
        }

    }


    //登出，根据token删除会话信息
    public static void logout(String token) {
        if (token == null) {
            return;
        }
        LoginSso sso = LoginConcurrentHashMap.get(token);
        if (sso == null) {
            sso = loadFromDB(token);
        }
        try {
            delete(sso);
        } catch (YtbError e) {
            //e.printStackTrace();
        }
    }

    public static void checkUserRightValid(IUserContext userContext, MsgRequest req) {
        if (RestRightCacheService.getCheckRestRight().findCheckRight(req.cmdtype, req.cmd)) {
            LoginSso sso = userContext.getLoginSso();
            checkLoginValid(sso,req);
            if(sso.isUserManager()) {
                checkApiKey(req);
            }
            checkRestValid(sso.getUserId(),sso,req);
        }
    }

    //getLog_sso save2DB
    public static void checkUserRightValid(MsgRequest req) {
        if (RestRightCacheService.getCheckRestRight().findCheckRight(req.cmdtype, req.cmd)) {
            LoginSso ls = getLog_sso(req.token);
            checkLoginValid(ls,req);

            checkRestValid(ls.getUserId(),ls,req);
        }
    }

    static  void checkLoginValid(LoginSso sso , MsgRequest r) {
        if (!checkValid(sso, r.token)) {
            throw new YtbError(YtbError.CODE_NORIGHT_REST);
        }
    }

    static boolean checkRestValid(Long userId, LoginSso sso, MsgRequest r) {
        RestRightCacheService rlst = RestRightCacheService.getCheckRestRight();

        int userType = sso.getLoginSsoJson().getUserType();
        if (!sso.isUserManager()) {
            boolean checkUserRole = rlst.checkValid_RoleRest(userType, r.cmdtype, r.cmd);
            if (checkUserRole) {
                return true;
            }
            boolean checkUserBBUser = rlst.checkValid_RoleRest_BBUser(r.cmdtype, r.cmd);
            if (checkUserBBUser) {
                return true;
            }
        }
        boolean bCheckSysUser = rlst.checkValid_Rest(userId, r.cmdtype, r.cmd);
        if (bCheckSysUser) {
            return true;
        }
        throw new YtbError(YtbError.CODE_NORIGHT_REST);
    }

    //检查token存在或者不超时
    public static boolean checkValid(LoginSso sso,String userToken) {
        if (sso == null) {
            return false;
        }
        boolean timeout = checkTimeout(sso);
        if (timeout) {
            delete(sso);
        }
        return !timeout;
    }

    static LoginSso loadFromDB_ignorError(String userToken) {
        LoginSsoService loginSsoService = new LoginSsoServiceImpl();
        LoginSsoExample loginSsoExample = new LoginSsoExample();
        loginSsoExample.createCriteria().andTokenEqualTo(userToken);
        List<LoginSso> lst = loginSsoService.selectByExample(loginSsoExample);
        if (lst.size() == 0) {
            return new LoginSso();
        }
        return lst.get(0);

    }
    static LoginSso loadFromDB(String userToken) {
        LoginSsoService loginSsoService = new LoginSsoServiceImpl();
        LoginSsoExample loginSsoExample = new LoginSsoExample();
        loginSsoExample.createCriteria().andTokenEqualTo(userToken);
        List<LoginSso> lst = loginSsoService.selectByExample(loginSsoExample);
        if (lst.size() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        return lst.get(0);

    }


    public static int delete(LoginSso loginSso) {
        int a = 0;
        try {
            LoginConcurrentHashMap.remove(loginSso.getToken());
            LoginSsoService loginSsoService = new LoginSsoServiceImpl();
            LoginSsoExample loginSsoExample = new LoginSsoExample();
            loginSsoExample.createCriteria().andTokenEqualTo(loginSso.getToken());
            a = loginSsoService.deleteByExample(loginSsoExample);
        } catch (Exception e) {
            a = 0;
        }

        return a;
    }

    /*原userToken + 现信息*/
    public static int update(String userToken, LoginSso loginSso) {
        int sta = 0;
        try {
            LoginConcurrentHashMap.remove(loginSso.getToken());
            LoginSsoService loginSsoService = new LoginSsoServiceImpl();
            LoginSsoExample loginSsoExample = new LoginSsoExample();
            loginSsoExample.createCriteria().andTokenEqualTo(userToken);
            sta = loginSsoService.deleteByExample(loginSsoExample);
            if (sta > 0) {

                LoginConcurrentHashMap.put(loginSso.getToken(), loginSso);
                int b = loginSsoService.insertSelective(loginSso);
            } else {
                sta = 0;
            }
        } catch (Exception e) {
            sta = 0;
        }


        return sta;
    }

    public static int save2DB(String token, LoginSso loginSso) {
        int a = 0;
        try {
            LoginConcurrentHashMap.put(token, loginSso);
            LoginSsoService loginSsoService = new LoginSsoServiceImpl();
            a = loginSsoService.insertSelective(loginSso);
        } catch (Exception e) {
            a = 0;
        }
        return a;
    }

    public static void genPicCode(String ip, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-ytb.check=0, pre-ytb.check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        DefaultKaptcha captchaProducer = YtbContext.getYtb_context().getAppContext().getBean("captchaProducer", DefaultKaptcha.class);
        String capText =  captchaProducer.createText();
        EhcacheContext.getEhcacheContext().getCachePicCode().put(ip,capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        YtbLog.logDebug(capText);
    }

    public static byte[] genPicCode2Byte(String ip, HttpServletResponse response) throws IOException {

        DefaultKaptcha captchaProducer = YtbContext.getYtb_context().getAppContext().getBean("captchaProducer", DefaultKaptcha.class);
        String capText = captchaProducer.createText();
        EhcacheContext.getEhcacheContext().getCachePicCode().put(ip, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ByteArrayOutputStream outs = new ByteArrayOutputStream(1024);
        ImageIO.write(bi, "jpg", outs);
        try {
            outs.flush();
        } finally {
            outs.close();
        }
        /*System.out.println(capText);
        System.out.println(outs.size());*/
        return outs.toByteArray();
    }

    public static void checkPicCode(String ip, String picCode) throws IOException {
        Cache.ValueWrapper cv=EhcacheContext.getEhcacheContext().getCachePicCode().get(ip);
        if (cv==null || !picCode.equalsIgnoreCase(cv.get().toString())) {
            throw new YtbError(YtbError.CODE_PICCODE_INVALID);
        }
    }

    //select * from login_sso where login_time <= '2018-10-26';
    public static int deleteTimeout(LoginSso loginSso) {
        int a = 0;
        try {
            LoginSsoService loginSsoService = new LoginSsoServiceImpl();
            LoginSsoExample loginSsoExample = new LoginSsoExample();
            //loginSsoExample.createCriteria().andLoginTimeLessThan(loginSso.getToken());
            a = loginSsoService.deleteByExample(loginSsoExample);
        } catch (Exception e) {
            a = 0;
        }

        return a;
    }



    static void clearTimeoutDBLogin_sso(){
        StringBuilder sql=new StringBuilder();
        sql.append("delete from ytb_tasklog.login_sso ");
        sql.append( " where login_time<DATE_ADD(now(), interval -2 day)")  ;
        YtbContext.getYtb_context().getSqlSessionBuilder().updateSql(sql);
    }

    public static void clearSessionTimeout(){
        clearTimeoutDBLogin_sso();
        LoginConcurrentHashMap.clearTimeout();
    }

    static void clear(){
        //clearDB();
        LoginConcurrentHashMap.clear();
    }

      static void deleteApiKey(int userId){
        StringBuilder sql=new StringBuilder();
        sql.append(" delete from ytb_tasklog.api_key where user_id=#{userId}");
          YtbSql.delete(sql,userId);
    }

    public static int insertApiKey(int userId) {

        deleteApiKey(userId);

        StringBuilder sql = new StringBuilder();
        sql.append(" insert into ytb_tasklog.api_key(user_id,api_key,security_key) ");
        sql.append(" values (#{userId},#{apiKey},#{securityKey})");
        Api_KeyModel keyModel = new Api_KeyModel();
        keyModel.setUserId(userId);
        keyModel.setApiKey(System.currentTimeMillis() + "");
        keyModel.setSecurityKey(System.currentTimeMillis() + "");
        return YtbSql.insert(sql, keyModel);
    }

    public static Api_KeyModel selectApiKey(int userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ytb_tasklog.api_key ");
        sql.append(" where user_id=#{userId}");
        return YtbSql.selectOne(sql,userId,Api_KeyModel.class);

    }

    public static Api_KeyModel genApiKey(int userId) {
        insertApiKey(userId);
        return selectApiKey(userId);
    }

    public static void checkApiKey(MsgRequest req) {
        LoginSso sso = getLog_sso(req.token);
        Api_KeyModel keyModel = selectApiKey(sso.getUserId().intValue());
        if(!keyModel.getApiKey().equals(req.getApiKey())){
            throw new YtbError(YtbError.CODE_NORIGHT_REST);
        }

    }

    //call tableSys_restlist
    public static int getRole(int user_tpe) {
        return RestRightCacheService.getCheckRestRight().getRole(user_tpe);
    }

    public static List<Map<String, Object>> getList_roleRight_menu(int user_tpe) {
        return RestRightCacheService.getCheckRestRight().getList_roleRight_menu(getRole(user_tpe));
    }

    public static List<Map<String, Object>> getList_roleRight_rest(int user_tpe) {
        return RestRightCacheService.getCheckRestRight().getList_roleRight_rest(getRole(user_tpe));

    }
}
