package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserTagModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/4 13:11
 */
public interface UserTagMapper {

    //获取用户的专业标签
    List<Map<String,Object>> getUserTagById(@Param("userId") Integer userId, @Param("tagType") Integer tagType);

    //添加用户标签
    void addUserTag(UserTagModel userTagModel);

    //删除用户标签
    void deleteUserTag(@Param("userId") int userId,@Param("userTagId") int userTagId);

    //修改用户标签
    void updateUserTag(UserTagModel userTagModel);

    //获取用户的专业标签
    List<UserTagModel> getUserTagToBB(@Param("userId") Integer userId);

}
