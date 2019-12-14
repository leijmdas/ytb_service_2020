package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserWorkPrjModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/7 11:18
 */
public interface UserWorkPrjMapper {
    //获取个人工作业绩列表
    List<Map<String,Object>>  getUserWorkPrjList(int userId);

    //添加个人工作业绩
    void addUserWorkPrj(UserWorkPrjModel userWorkPrjModel);

    //删除个人工作业绩
    void deleteUserWorkPrj(@Param("userId") int userId,@Param("prjId") int prjId);

    //修改个人工作业绩
    void updateUserWorkPrj(UserWorkPrjModel userWorkPrjModel);

}
