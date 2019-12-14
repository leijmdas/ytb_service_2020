package ytb.manager.sysuser.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.sysuser.dao
 * Author: ZCS
 * Date: Created in 2018/8/22 12:11
 * Company: 公司
 */
public interface SysRoleRightMapper {

    //新增角色与权限关系
    void addSysRoleRight(Map<String,Object> map);

    //清除角色与权限关系
    void deltetSysRoleRight(@Param("roleId") int roleId,@Param("type") int type);

    /*
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);


}
