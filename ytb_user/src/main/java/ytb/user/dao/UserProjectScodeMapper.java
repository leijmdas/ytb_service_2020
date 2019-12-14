package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserProjectScopeModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/11/6 18:14
 */
public interface UserProjectScodeMapper {
    //用户接单范围
    List<Map<String,Object>> getProjectScopeList(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    //新增用户接单范围
    Integer addProjectScope(UserProjectScopeModel userProjectScopeModel);

    //修改接单范围
    void updateProjectScode(UserProjectScopeModel userProjectScopeModel);

    //删除接单范围
    void deleteProjectScode(Integer scodeId);

    void updateProjectScodeShowOrHide(@Param("scodeId") Integer scodeId,@Param("isShow") Boolean isShow);
}
