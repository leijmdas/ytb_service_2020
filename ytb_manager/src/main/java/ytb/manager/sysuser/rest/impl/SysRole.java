package ytb.manager.sysuser.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Sets;
import ytb.common.utils.YtbUtils;
import ytb.manager.sysuser.model.Sys_RoleModel;
import ytb.manager.sysuser.service.SysRoleService;
import ytb.manager.sysuser.service.impl.SysRoleServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.*;

/**
 * Package: ytb.manager.sysuser.rest.impl
 * Author: ZCS
 * Date: Created in 2018/8/22 14:11
 */
public class SysRole {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    public MsgResponse process(  MsgHandler handler) {
        MsgRequest req = handler.req;
        SysRoleService sysRoleService = new SysRoleServiceImpl();


        //查询角色列表
        if (req.cmd.equals("getRoleList")) {
            String roleName  = req.msgBody.getString("roleName");
            List<Sys_RoleModel> lst = sysRoleService.getSysRoleList(roleName);
            msgBody="{\"list\":"+ YtbUtils.toJSONStringPretty(lst)+"}";
        }

        //添加角色列表
        else if(req.cmd.equals("addRole")){

            Sys_RoleModel sysRoleModel = req.msgBody.toJavaObject(req.msgBody,Sys_RoleModel.class);
           //后面从cookie中取
            sysRoleModel.setCreateBy(0);
            sysRoleService.addRole(sysRoleModel);

        }

        //获取用户所拥有的角色信息
        else if(req.cmd.equals("getUserRoleList")){

            int userId = req.msgBody.getInteger("userId");
            String roleName = req.msgBody.getString("roleName");

            //用户所拥有的角色
            List<Sys_RoleModel> userRoleList = sysRoleService.getUserRoleList(userId);

            //全部角色
            List<Sys_RoleModel> roleList = sysRoleService.getSysRoleList(roleName);

            JSONArray json = new JSONArray();
            Set<Sys_RoleModel> retSet= Sets.newLinkedHashSet(userRoleList);

            for(Sys_RoleModel roleModel :roleList){

                if(!retSet.contains(roleModel)){

                    retSet.add(roleModel);
                }

            }
            List<Sys_RoleModel> l=new LinkedList(retSet);
            Collections.sort(l);


            for(Sys_RoleModel roleModel :l){
                json.add(roleModel);
            }

            msgBody="{\"list\":"+json.toJSONString()+"}";
        }
        //删除角色
        else if(req.cmd.equals("deleteRoleById")){
            int roleId = req.msgBody.getInteger("roleId");
            sysRoleService.deleteRole(roleId);
        }

        //修改用户角色
        else if(req.cmd.equals("updateRoleInfo")){

            Sys_RoleModel roleModel = req.msgBody.toJavaObject(req.msgBody,Sys_RoleModel.class);
            sysRoleService.updateRole(roleModel);
        }

        //重置用户与角色关系
        else if(req.cmd.equals("saveUserRole")){
            String roleIds = req.msgBody.getString("roleId");
            int userId = req.msgBody.getInteger("userId");
            int createBy = req.msgBody.getInteger("createBy");

            sysRoleService.addSysUserRole(roleIds,userId,createBy);
        }

        else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);


    }


}
