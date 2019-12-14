package ytb.manager.sysuser.service;

import ytb.manager.sysuser.model.LeftMenuModel;
import ytb.manager.sysuser.model.SysRestListSimpleModel;
import ytb.manager.sysuser.model.Sys_MenuModel;
import ytb.manager.sysuser.model.Sys_RestListModel;

import java.util.List;
import java.util.Map;

/**
 * 权限Service
 * Package: ytb.manager.sysuser.service
 * Author: ZCS
 * Date: Created in 2018/8/21 13:16
 */
public interface SysPowerService {

    //获取菜单列表
    List<Sys_MenuModel> getMenuList();

    //获取接口列表
    List<Sys_RestListModel> getRestList();

    //添加菜单记录
    void addMenu(Sys_MenuModel sysMenuModel);

    //添加接口记录
    void addRest(Sys_RestListModel sysRestListModel);

    //删除菜单信息
    void deleteMenuById(int menuId);

    //删除接口信息
    void deleteRestListById(int restId);

    //修改菜单信息
    void updateMenu(Sys_MenuModel sysMenuModel);

    //修改接口信息
    void updateRestList(Sys_RestListModel sysRestListModel);

    //根据ID获取信息
    Sys_MenuModel getMenuInfoById(int menuId);

    //根据ID获取接口信息
    Sys_RestListModel getRestListInfoById(int restId);

    //查询角色所拥有的菜单
    List<Sys_MenuModel> getMenuToRole(int roleId);

    //获取角色所拥有的接口
    List<Sys_RestListModel> getRestToRole(int roleId);

     //根据父菜单，查询子菜单
    List<Sys_MenuModel> queryMenuParentId(int parentId);

    //查询接口的子级
    List<Sys_RestListModel> queryRestParentId(int parentId);

    //查询左侧的菜单数据
    List<LeftMenuModel> queryNotButtonList(int userId);

    //保存菜单角色或接口角色权限
    void saveRoleMenuOrRest(String menuIds,int roleId,int type);

    //保存接口角色权限
    void saveRoleRest(Map<String,Object> map);

    //清除角色与权限关系
    void deltetSysRoleRight(int roleId,int type);

    //用于菜单管理接口列表数据获取
    List<SysRestListSimpleModel> getSimpleRestList();

}
