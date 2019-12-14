package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserAddressModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/5 11:20
 */
public interface UserAddressMapper {

    //获取用户的收件地址
    List<UserAddressModel> getUserAddressListById(Map<String,Object> map);

    //新增用户收件地址
    void addUserAddress(UserAddressModel userAddressModel);

    //删除用户收件地址
    void deleteUserAddress(@Param("addressId") Integer addressId,@Param("userId") Integer userId,@Param("companyId") Integer companyId);

    //修改用户收件地址
    void updateUserAddress(UserAddressModel userAddressModel);

    //查询总数
    Integer queryTotal(Map<String,Object> map);

    //设置默认收件地址
    int  setDefaultAddr(@Param("addressId") int addressId,@Param("isDefault") boolean isDefault);

    //设置非默认的收件地址
    void setNoDefaultAddr(@Param("addressId") int addressId,@Param("companyId") int companyId);
}
