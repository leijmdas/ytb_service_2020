package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserAttendModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/12/18 19:10
 */
public interface UserAttendMapper {

    //获取关注的列表
    List<Map<String,Object>> getAttendList(@Param("userId") Integer userId,@Param("companyId") Integer companyId);

    //新增关注
    Integer addAttend(UserAttendModel userAttendModel);

    //取消关注
    void deleteUserAttend(Map<String,Object> map);

    //判断是否重复
    int getAttendRepeat(@Param("userId")Integer userId,@Param("companyId") Integer comapnyId,@Param("toAttendId") Integer toAttendId);
}
