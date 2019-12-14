package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserPatentModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/5 14:07
 */
public interface UserPatentMapper {
    //获取个人专利列表
    List<UserPatentModel> getUserPatentByIdAndType(@Param("userId") Integer userId,@Param("patentType") Integer patentType);

    //新增个人专利
    void addUserPatent(UserPatentModel userPatentModel);

    //删除个人专利
    void deleteUserPatent(@Param("userId") Integer userId,@Param("patentId")Integer patentId);

    //修改个人专利
    void updateUserPatent(UserPatentModel userPatentModel);

    List<UserPatentModel> getUserPatentById(int userId);
 }
