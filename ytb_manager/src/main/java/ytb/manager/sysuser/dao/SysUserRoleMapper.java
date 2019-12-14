package ytb.manager.sysuser.dao;


import java.util.Map;

/**
 * Package: ytb.manager.sysuser.dao
 * Author: ZCS
 * Date: Created in 2018/8/22 12:05
 */
public interface SysUserRoleMapper {

    //新增用户与角色关系
    void  addSysUserRole(Map<String,Object> map);

    //清除用户与角色关系
    void deleteSysUserRole(int userRoleId);
}
