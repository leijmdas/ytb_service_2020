package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserPurchaseModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/11/6 20:01
 */
public interface UserPurchaseMapper {
    //获取列表数据
    List<Map<String,Object>> getUserPurchaseList(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    //新增列表数据
    Integer addUserPurchase(UserPurchaseModel userPurchaseModel);

    //删除
    void deleteUserPurchase(int purchaseId);

    //修改
    void updateUserPurchase(UserPurchaseModel userPurchaseModel);

    //获取用户的接单范围类别
    List<Map<String,Object>> getUserPurchaseType(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    //设置意向采购清单浏览数量
    void updatePurchaseViewNumberById(Integer purchaseId);
}
