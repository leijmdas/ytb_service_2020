package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserInfoModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/7 13:20
 * Company: 公司
 */
public interface UserInfoMapper {
    //获取用户基本信息
    UserInfoModel getUserInfoById(int userId);

    //添加用户信息
    void addUserInfo(UserInfoModel userInfoModel);

    //修改用户信息
    void updateUserInfo(UserInfoModel userInfoModel);

    //设置用户自我评价
    void updateUserSelfEval(@Param("userId") int userId,@Param("selfEval") String selfEval);

    void updateUserStatus(@Param("userId") int userId, @Param("status")int status);

    //设置基本信息
    void updatUserBaseInfo(UserInfoModel userInfoModel);

    //邦邦添加好友接口
    List<Map<String,Object>> getUserListToBB(Map<String,Object> map);

    int getUserListToBBCount(Map<String,Object> map);

    //批量获取用户
    List<UserInfoModel> getUsetInfoByIds(@Param("ids") List<Integer> ids);
}
