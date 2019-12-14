package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserBlackModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.pfUser.dao
 * Author: ZCS
 * Date: Created in 2018/12/6 10:30
 */
public interface UserBlackMapper {
    //新增黑名单
    int insertUserBlack(UserBlackModel userBlackModel);

    //查看黑名单
    List<Map<String,Object>> getUserBlackList(Map<String,Object> map);

    //修改黑明单
    void updateUserBlackList(@Param("userId") int userId);

    //获取总条数
    int getUserBlackTotal(@Param("userType") int userType);

    //获取公司用户黑明单

}
