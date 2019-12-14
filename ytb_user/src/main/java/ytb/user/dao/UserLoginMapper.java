package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.BBUserModel;
import ytb.user.model.UserLoginModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/3 16:12
 */
public interface UserLoginMapper {
    //新增用户账号信息
    void addUserLogin(UserLoginModel userLoginModel);

    //根据ID获取信息
    UserLoginModel getUserLoginInfoById(int userId);

    //根据手机号获取信息
    UserLoginModel getUserLoginInfoByMobile(String mobile);

    //邦邦设置个性签名
    void setUserSign(@Param("userSign") String userSgin, @Param("userId") int userId);

    int modifyPassword(@Param("userId") Integer userId, @Param("userType") int userType, @Param("password") String password, @Param("oldPassword") String oldPassword);

    int modifyPhone(@Param("userId") Integer userId, @Param("newLoginMobile") String newLoginMobile, @Param("password") String password, @Param("oldLoginMobile") String oldLoginMobile);

    int updateByPrimaryKeySelective(UserLoginModel userLoginModel);

    int insertSelective(UserLoginModel userLoginModel);

    UserLoginModel getUserLoginInfoByLoginMobile(@Param("loginMobile") String loginMobile);

    UserLoginModel getUserLoginInfoByLoginMobileAndUserType(@Param("loginMobile") String loginMobile, @Param("userType") int userType);

    List<UserLoginModel> getUserLoginInfoByLoginMobileAndUserTypeList(@Param("loginMobile") String loginMobile, @Param("userType") int userType);


    List<UserLoginModel> getUserLoginInfoByLoginMobileAndUserTypeAndPasswordList(@Param("loginMobile") String loginMobile, @Param("userType") int userType, @Param("password") String password);


    List<UserLoginModel> getUserLoginModelByUserType(@Param("userType") int userType);

    List<BBUserModel> getUserLoginList(Map<String, Object> map);


    //获取用户基本资料
    Map<String, Object> getUserBaseInfo(int userId);

    Map<String, Object> getFCityByAreaId(int areaId);

    //查询优质人才
    List<Map<String, Object>> selectGoodPeople();

    //绑定手机号
    void updateUserPhone(@Param("userId") Integer userId, @Param("phone") String phone);

    //修改密码
    void updateUserPassword(@Param("userId") Integer userId, @Param("newPwd") String newPwd);
}
