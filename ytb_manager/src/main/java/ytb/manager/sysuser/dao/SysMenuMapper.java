package ytb.manager.sysuser.dao;

import ytb.manager.sysuser.model.LeftMenuModel;
import ytb.manager.sysuser.model.Sys_MenuModel;

import java.util.List;

/**
 * Package: ytb.manager.sysuser.dao
 * Author: ZCS
 * Date: Created in 2018/8/22 11:07
 */
public interface SysMenuMapper {

    //获取菜单列表
    List<Sys_MenuModel> getMenuList();

    //根据ID获取信息
    Sys_MenuModel getMenuInfoById(int menuId);

    //添加菜单信息
    void addMenu(Sys_MenuModel menuModel);

    //删除菜单
    void deleteMenuById(int menuId);

    //修改菜单信息
    void updateMenu(Sys_MenuModel menuModel);

    //查询角色所拥有的菜单
    List<Sys_MenuModel> getMenuToRole(int roleId);

    //查询子级
    List<Sys_MenuModel> getMenuParentId(int parentId);

    //查询左侧的菜单数据
    List<LeftMenuModel> queryNotButtonList(int userId);
}
