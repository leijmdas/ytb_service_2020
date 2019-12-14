package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserSellModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/11/6 19:29
 */
public interface UserSellMapper {

    //获取推广销售清单
    List<Map<String,Object>> getUserSellList(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    //获取类别（用于数据组装）
    List<Map<String,Object>> getUserSellType(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    //新增推广销售清单
    Integer addUserSell(UserSellModel userSellModel);

    //修改推广销售清单
    void updateUserSell(UserSellModel userSellModel);

    //删除推广销售清单
    void deleteUserSell(int sellId);

    //设置推广销售清单浏览数量
    void updateViewCountById(Integer sellID);

}
