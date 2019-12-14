package ytb.manager.sysuser.service;

import ytb.manager.sysuser.model.Sys_RoleModel;

import java.util.List;
import java.util.Map;

/**
 * 角色Service
 * Package: ytb.manager.sysuser.service
 * Author: ZCS
 * Date: Created in 2018/8/21 13:13
 */
public interface SysRoleService {
    //获取角色列表
    List<Sys_RoleModel> getSysRoleList(String roleName);

    //添加角色信息
    void addRole(Sys_RoleModel sysRoleModel);

    //修改角色信息
    void updateRole(Sys_RoleModel sysRoleModel);

    //删除角色信息
    void deleteRole(int roleId);

    //查询用户所用的角色列表
    List<Sys_RoleModel> getUserRoleList(int userId);

    //新增用户与角色关系
    void  addSysUserRole(String roleId,int userId,int createBy);

    //清除用户与角色关系
    void deleteSysUserRole(int userRoleId);

}
