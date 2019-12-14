package ytb.manager.sysuser.service;

import ytb.manager.sysuser.model.SysUserModel;

import java.util.List;
import java.util.Map;

/**
 * 用户Service
 * Package: ytb.manager.sysuser.service
 * Author: ZCS
 * Date: Created in 2018/8/21 13:13
 */
public interface SysUserService {

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
    void updatePassword(String newPassword,int userId,String oldPwd);

    /*
    启用、禁用账户
     */
    void updateStatus(Map<String,Object> map);

    //通过手机号获取用户信息(用户后台系统登录)
    Map<String,Object> checkUserByUserName(String userName,String password,String ip);


    //通过手机
    Map<String,Object> checkUserByMobile(String mobile,String ip);
}
