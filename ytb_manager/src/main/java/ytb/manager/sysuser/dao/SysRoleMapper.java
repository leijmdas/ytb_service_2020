package ytb.manager.sysuser.dao;

import org.apache.ibatis.annotations.Param;
import ytb.manager.sysuser.model.Sys_RoleModel;

import java.util.List;

/**
 * Package: ytb.manager.sysuser.dao
 * Author: ZCS
 * Date: Created in 2018/8/22 17:24
 */
public interface SysRoleMapper {

    //获取角色列表
    List<Sys_RoleModel> getSysRoleList(@Param("roleName")String roleName);

    //添加角色信息
    void addRole(Sys_RoleModel sysRoleModel);

    //修改角色信息
    void updateRole(Sys_RoleModel sysRoleModel);

    //删除角色信息
    void deleteRole(int roleId);

    //查询用户所用的角色列表
    List<Sys_RoleModel> getUserRoleList(int userId);

}
