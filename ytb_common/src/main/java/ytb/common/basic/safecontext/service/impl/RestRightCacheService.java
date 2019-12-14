/**
 * CheckRestRight
 * author:leijm
 * 20181009
 */
package ytb.common.basic.safecontext.service.impl;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import ytb.common.ehcache.CacheService.ICacheService;
import ytb.common.ehcache.CacheService.ICacheServiceUser;
import ytb.common.ehcache.SysCacheService;
import ytb.common.ehcache.UserCacheService;
import ytb.common.ehcache.EhcacheContext;
import ytb.common.ytblog.YtbLog;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class RestRightCacheService implements ICacheService, ICacheServiceUser {
    final static RestRightCacheService checkRestRight = new RestRightCacheService();
    public static RestRightCacheService getCheckRestRight() {
        return checkRestRight;
    }

    final static int TYPE_MENU = 1;
    final static int TYPE_REST = 2;

    final static String KEY_getList = "CheckRestRight::getList";
    final static String KEY_getList_right = "CheckRestRight::getList_right";
    final static String KEY_getRoleList = "CheckRestRight::getRoleList";
    final static String KEY_getList_roleRight = "CheckRestRight::getList_roleRight";

    public final static int USER_TYPE_MANAGER = 100;
    public final static int USER_TYPE_BBUser = 1000;//BB用户
    public final static int USER_TYPE_BBOwner = 1001;//BB群主
    public final static int USER_TYPE_BBManager = 1002;//BB管理员
    public final static int USER_TYPE_BBMember = 1003;//BB组员

    final static String ROLE_PersonUser = "PersonUser";
    final static String ROLE_CompanyUser = "CompanyUser";
    final static String ROLE_CompanyAdmin = "CompanyAdmin";
    final static String ROLE_guest = "guest";

    @Override
    public Cache getCache() {
        return EhcacheContext.getEhcacheContext().getCache();
    }

    public void refresh(Long user_id) {
        String key = makeUserKey(KEY_getList_right, user_id);
        UserCacheService.evict(key);
    }

    public void refresh() {
        SysCacheService.evict(KEY_getList);
    }

    String makeUserKey(String key, Long user_id) {
        return key + "::" + user_id;
    }


    List<Map<String, Object>> getList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select restId,trim(cmdType) as cmdType,trim(cmd) as cmd ,checkRight from ytb_manager.sys_restlist  ");
        sql.append(" where cmdType!='' and cmdType is not null and cmd!=''");
        sql.append(" order by cmdType,cmd ");
        return SysCacheService.select2Cache(KEY_getList, sql);
    }


    List<Map<String, Object>> getList_right(Long user_id) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select restId from ytb_manager.sys_restlist ");
        sql.append(" where cmd is not null and cmd!='' and cmdType!='' and cmdType is not null ");
        sql.append(" and restid in (select objid from ytb_manager.sys_role_right where objtype = 2 ");
        sql.append(" and roleid in ( select RoleId from ytb_manager.sys_user_role where userId = ");
        sql.append(user_id).append(" ) ) order by  cmdType,cmd");
        return UserCacheService.select2Cache(makeUserKey(KEY_getList_right, user_id), sql);

    }

    List<Map<String, Object>> getRoleList() {
        StringBuilder sql = new StringBuilder("select * from ytb_manager.sys_role");
        return SysCacheService.select2Cache(KEY_getRoleList, sql);

    }
    //type=1 menu/ type=2 rest
    List<Map<String, Object>> getList_roleRight(int role_id, int type) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select trim(cmdType) as cmdType,trim(cmd) as cmd from ytb_manager.sys_restlist ");
        sql.append(" where cmd is not null and cmd != '' and cmdType!='' and cmdType is not null ");
        sql.append(" and restid in (select objid from ytb_manager.sys_role_right ");
        sql.append(" where objtype = ").append(type).append(" and roleid  = ").append(role_id).append(")");
        return SysCacheService.select2Cache(makeUserKey(KEY_getList_roleRight,
                (long)role_id * type), sql);

    }
    public boolean checkValid_Rest(Long user_id, String cmdType, String cmd) {
        int id = findCheckRight_restId(cmdType, cmd);
        YtbLog.logDebug(" now checkRight --> " + cmdType + "::" + cmd+ " id:"+id);
        for (Map<String, Object> item : getList_right(user_id)) {
            if (item.get("restId").equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkValid_RoleRest_BBUser( String cmdType, String cmd ) {
        int roleId = getRole(USER_TYPE_BBUser);
        YtbLog.logDebug(roleId+"=roleId");
        List<Map<String, Object>> lst= getList_roleRight_rest(roleId);

        return lst.stream().filter(item -> item.get("cmdType").equals(cmdType) &&
                item.get("cmd").equals(cmd)).collect(Collectors.toList()).size() > 0;
    }

    public boolean checkValid_RoleRest(int userType, String cmdType, String cmd) {
        int roleId = getRole(userType);
        YtbLog.logDebug(roleId+"=roleId");
        List<Map<String, Object>> lst= getList_roleRight_rest(roleId);
        return lst.stream().filter(item -> item.get("cmdType").equals(cmdType) &&
                item.get("cmd").equals(cmd)).collect(Collectors.toList()).size() > 0;
    }

    //need checkRight
    public boolean findCheckRight(String cmdType, String cmd) {
        for (Map<String, Object> m : getList()) {
            boolean isCmdType = m.get("cmdType") != null && m.get("cmdType").equals(cmdType);
            boolean isCmd = m.get("cmd") != null && m.get("cmd").equals(cmd);
            if (isCmd && isCmdType) {
                return (boolean) m.get("checkRight");
            }
        }
        return true;
    }
    //need checkRight
    public int findCheckRight_restId(String cmdType, String cmd) {
        for (Map<String, Object> m : getList()) {
            boolean isCmdType = m.get("cmdType") != null && m.get("cmdType").equals(cmdType);
            boolean isCmd = m.get("cmd") != null && m.get("cmd").equals(cmd);
            if (isCmd && isCmdType) {
                return (int) m.get("restId");
            }
        }
        return -1;
    }


    public int getRole(int user_type) {
        for (Map<String, Object> m : getRoleList()) {
            int user_type0 = Integer.valueOf(m.get("user_type").toString());
            if (user_type==user_type0) {
                return (int) m.get("RoleId");
            }
        }
        return 0;
    }


    //type=1 menu :type=2 rest
    public List<Map<String, Object>> getList_roleRight_menu(int role_id) {
        return getList_roleRight(role_id, TYPE_MENU);
    }
    public List<Map<String, Object>> getList_roleRight_rest(int role_id) {
        return getList_roleRight(role_id, TYPE_REST);
    }

}