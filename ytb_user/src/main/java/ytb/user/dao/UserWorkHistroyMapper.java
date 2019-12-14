package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserWorkHistroyModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/5 16:42
 */
public interface UserWorkHistroyMapper {
    //获取个人简历表
    List<UserWorkHistroyModel> getUserWorkHistroyListById(int userId);

    //添加个人简历
    void addUserWorkHistroy(UserWorkHistroyModel userWorkHistroyModel);

    //删除个人简历
    void deleteUserWorkHistroy(@Param("userId") int userId, @Param("workId") int workId);

    //修改个人简历
    void updateUserWorkHistroy(UserWorkHistroyModel userWorkHistroyModel);

}
