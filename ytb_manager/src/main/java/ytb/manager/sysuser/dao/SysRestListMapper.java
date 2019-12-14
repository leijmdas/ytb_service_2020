package ytb.manager.sysuser.dao;

import ytb.manager.sysuser.model.SysRestListSimpleModel;
import ytb.manager.sysuser.model.Sys_RestListModel;

import java.util.List;

/**
 * Package: ytb.manager.sysuser.dao
 * Author: ZCS
 * Date: Created in 2018/8/22 11:07
 */
public interface SysRestListMapper {

    List<Sys_RestListModel> getRestList();

    //用于菜单管理接口列表数据获取
    List<SysRestListSimpleModel> getSimpleRestList();

    Sys_RestListModel getRestListInfoById(int restId);

    void deleteRestListById(int restId);

    void addRestList(Sys_RestListModel sysRestListModel);

    void updateRestList(Sys_RestListModel sysRestListModel);

    //获取角色所拥有的接口
    List<Sys_RestListModel> getRestToRole(int roleId);

    //查询接口的子级
    List<Sys_RestListModel> getRestParentId(int parentId);
}
