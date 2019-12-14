package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserAwardModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/5 15:43
 */
public interface UserAwardMapper {
    //获取用户奖励列表
    List<UserAwardModel> getUserAwardListById(int userId);

    //新增用户奖励
    void addUserAward(UserAwardModel userAwardModel);

    //删除用户奖励
    void deleteUserAward(@Param("userId") int userId,@Param("awardId") int awardId);

    //修改用户奖励
    void updateUserAward(UserAwardModel userAwardModel);

}
