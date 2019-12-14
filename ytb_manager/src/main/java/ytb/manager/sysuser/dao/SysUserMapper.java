package ytb.manager.sysuser.dao;

import org.apache.ibatis.annotations.Param;
import ytb.manager.sysuser.model.SysUserModel;

import java.util.List;
import java.util.Map;

/**
 * 系统用户Dao类
 * Package: ytb.manager.sysuser.dao
 * Author: ZCS
 * Date: Created in 2018/8/21 13:10
 */
public interface SysUserMapper {

    /*
     获取系统用户列表
     */
    List<SysUserModel> getSysUserList(Map<String,Object> map);

    /*
    添加系统用户
     */
    void addUserInfo(SysUserModel sysUserModel);

    /*
    删除用户
     */
    void deleteUserbyId(int userId);

    /*
    修改用户信息
     */
    void updateSysUserInfo(SysUserModel sysUserModel);

    /*
    通过ID获取用户信息
     */
    SysUserModel getSysUserInfoById(int userId);

    /*
    修改用户密码
     */
    void updatePassword(@Param("newPwd") String newPwd,@Param("userId") int userId);

    /*
    启用、禁用账户
     */
    void updateStatus(Map<String,Object> map);

    //根据角色ID获取所属用户
    List<Integer> getUserIdByRoleId(int roleId);

    //通过用户名获取用户信息
    SysUserModel getUserByUserName(String userName);
    //通过手机号获取用户信息
    SysUserModel getUserByMobile(String mobile);

}


