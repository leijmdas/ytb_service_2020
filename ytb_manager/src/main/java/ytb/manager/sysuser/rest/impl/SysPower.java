package ytb.manager.sysuser.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ytb.manager.sysuser.model.*;
import ytb.manager.sysuser.service.SysPowerService;
import ytb.manager.sysuser.service.impl.SysPowerServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.*;

/**
 * Package: ytb.manager.sysuser.rest.impl
 * Author: ZCS
 * Date: Created in 2018/8/22 14:09
 */

@Component
@Controller
public class SysPower {

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process( MsgHandler handler) {
        MsgRequest req = handler.req;

        SysPowerService sysPowerService = new SysPowerServiceImpl();

        //添加菜单信息
        if(req.cmd.equals("addMenu")){
            Sys_MenuModel menuModel =  req.msgBody.toJavaObject(req.msgBody,Sys_MenuModel.class);
            sysPowerService.addMenu(menuModel);
        }

        //添加接口信息
        else if(req.cmd.equals("addRestList")){

            Sys_RestListModel restListModel = req.msgBody.toJavaObject(req.msgBody,Sys_RestListModel.class);
            restListModel.setCreateBy(1);
            sysPowerService.addRest(restListModel);

        }

        //删除菜单信息
        else if(req.cmd.equals("deleteMenuById")){
            int menuId = Integer.parseInt(req.msgBody.getString("menuId"));

            //判断是否有子菜单或按钮
            List<Sys_MenuModel> menuList = sysPowerService.queryMenuParentId(menuId);
            if(menuList.size()>0){
                retcode = 1;
                retmsg = "删除失败，请先删除子级";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

            sysPowerService.deleteMenuById(menuId);

        }

        //删除接口信息
        else if(req.cmd.equals("deleteRestListById")){
            int restListId = Integer.parseInt(req.msgBody.getString("restListId"));
            List<Sys_RestListModel> list = sysPowerService.queryRestParentId(restListId);
            if(list.size()>0){
                retcode = 1;
                retmsg = "删除失败，请先删除子级";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            sysPowerService.deleteRestListById(restListId);
        }

        //修改菜单信息
        else if(req.cmd.equals("updateMenu")){
            Sys_MenuModel menuModel=  req.msgBody.toJavaObject(req.msgBody,Sys_MenuModel.class);
            sysPowerService.updateMenu(menuModel);
        }

        //修改接口信息
        else if(req.cmd.equals("updateRestList")){
            Sys_RestListModel restListModel=  req.msgBody.toJavaObject(req.msgBody,Sys_RestListModel.class);
            sysPowerService.updateRestList(restListModel);
        }

        //返回数据结构菜单
        else if(req.cmd.equals("treeMenuList")){

            //所有的菜单列表数据
            List<Sys_MenuModel> menuListModelList = sysPowerService.getMenuList();

            msgBody="{\"list\":"+JSON.toJSON(bulidMenuTree(menuListModelList)).toString()+"}";

        }

        //返回接口的树形数据
        else if(req.cmd.equals("treeRestList")){

            //所有的接口数据
            List<SysRestListSimpleModel> totalRestList = sysPowerService.getSimpleRestList();

            msgBody="{\"list\":"+JSON.toJSON( bulidRestTree(totalRestList)).toString()+"}";

        }


        //查询角色所拥有的菜单
        else if(req.cmd.equals("getMenuToRole")){

            int roleId = req.msgBody.getInteger("roleId");

            //所有的菜单列表数据
            List<Sys_MenuModel>  totalMenuList = sysPowerService.getMenuList();

            //角色所拥有的权限
            List<Sys_MenuModel> roleMenuList = sysPowerService.getMenuToRole(roleId);

            //JSONArray menuJson = new JSONArray();
            Set<Sys_MenuModel> menuSet= Sets.newLinkedHashSet(roleMenuList);
            for(Sys_MenuModel menuModel :totalMenuList){
                if(!menuSet.contains(menuModel)){
                    menuSet.add(menuModel);
                }
            }

            List<Sys_MenuModel> l=new LinkedList(menuSet);
            Collections.sort(l);

            List<Sys_MenuModel> retList = bulidMenuTree(l);
            //Collections.sort(retList);
            msgBody="{\"list\":"+JSON.toJSON(retList).toString()+"}";
        }

        //查询角色所拥有的接口
        else if(req.cmd.equals("getRestToRole")){
            int roleId = req.msgBody.getInteger("roleId");

            //角色所拥有的接口列表数据
            List<Sys_RestListModel> restRoleList = sysPowerService.getRestToRole(roleId);
            //所有的接口数据
            List<Sys_RestListModel> totalRestList = sysPowerService.getRestList();

            JSONArray restJson = new JSONArray();
            Set<Sys_RestListModel> restSet = Sets.newLinkedHashSet(restRoleList);
            for(Sys_RestListModel restListModel : totalRestList){
                if(!restSet.contains(restListModel)){
                    restSet.add(restListModel);
                }
            }

            List<Sys_RestListModel> l=new LinkedList(restSet);
            Collections.sort(l);
            //一级菜单
            List<Sys_RestListModel> retList = findRestSubList(l);
            //Collections.sort(retList);
            msgBody="{\"list\":"+JSON.toJSON(retList).toString()+"}";
        }

        //保存菜单权限关系
        else if(req.cmd.equals("saveRoleMenuOrRest")){
            String menuIds = req.msgBody.getString("menuId");
            int roleId = req.msgBody.getInteger("roleId");
            int type = req.msgBody.getInteger("type");

            sysPowerService.saveRoleMenuOrRest( menuIds, roleId, type);
        }

        //查询左侧菜单树
        else if(req.cmd.equals("getMenuLeftList")){

            //所有的菜单列表数据
            if(null == req.msgBody.getInteger("login_userId") || "".equals(req.msgBody.getInteger("login_userId"))){

                retcode=1;
                retmsg ="请求失败";
                msgBody="{}";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

            List<LeftMenuModel>   menuListModelList = sysPowerService.queryNotButtonList(req.msgBody.getInteger("login_userId"));
            //一级菜单
            List<LeftMenuModel> retList = findLeftMenuTree(menuListModelList);

            msgBody="{\"list\":"+JSON.toJSON(retList).toString()+"}";
        }

        //根据ID获取菜单信息
        else if(req.cmd.equals("getMenuInfoById")){
            int menuId  = req.msgBody.getInteger("menuId");
            Sys_MenuModel menuModel = sysPowerService.getMenuInfoById(menuId);
            msgBody="{\"list\":"+JSON.toJSON(menuModel).toString()+"}";
        }

        //根据ID获取接口信息
        else if(req.cmd.equals("getRestListInfoById")){
            int restId  = req.msgBody.getInteger("restId");
            Sys_RestListModel restListModel = sysPowerService.getRestListInfoById(restId);
            msgBody="{\"list\":"+JSON.toJSON(restListModel).toString()+"}";
        } else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }




    private List<LeftMenuModel>  findLeftMenuTree(List<LeftMenuModel> l){
        List<LeftMenuModel> menuRetList = new ArrayList<>();
        List<LeftMenuModel> trees = new ArrayList<LeftMenuModel>();
        for (LeftMenuModel treeNode : l) {
            if (0 == treeNode.getParentId()) {
                trees.add(treeNode);
            }
            for (LeftMenuModel it : l) {
                if (it.getParentId() == treeNode.getMenuId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<LeftMenuModel>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }


    public static  List<Sys_MenuModel> bulidMenuTree( List<Sys_MenuModel> treeNodes) {
        List<Sys_MenuModel> trees = new ArrayList<Sys_MenuModel>();
        for (Sys_MenuModel treeNode : treeNodes) {
            if (0 == treeNode.getParentId()) {
                trees.add(treeNode);
            }
            for (Sys_MenuModel it : treeNodes) {
                if (it.getParentId() == treeNode.getMenuId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<Sys_MenuModel>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    //获取处理接口数据
    private List<Sys_RestListModel>  findRestSubList(List<Sys_RestListModel> treeNodes){
        List<Sys_RestListModel> retList = new ArrayList<>();
        for (Sys_RestListModel treeNode : treeNodes) {
            if (0 == treeNode.getParentId()) {
                retList.add(treeNode);
            }
            for (Sys_RestListModel it : treeNodes) {
                if (it.getParentId() == treeNode.getRestId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<Sys_RestListModel>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return retList;
    }

    public static List<SysRestListSimpleModel> bulidRestTree(List<SysRestListSimpleModel> treeNodes) {
        List<SysRestListSimpleModel> trees = new ArrayList<SysRestListSimpleModel>();
        for (SysRestListSimpleModel treeNode : treeNodes) {
            if (0 == treeNode.getParentId()) {
                trees.add(treeNode);
            }
            for (SysRestListSimpleModel it : treeNodes) {
                if (it.getParentId() == treeNode.getRestId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<SysRestListSimpleModel>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }



}
