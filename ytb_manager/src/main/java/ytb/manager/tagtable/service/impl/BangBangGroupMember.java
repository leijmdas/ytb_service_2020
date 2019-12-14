package ytb.manager.tagtable.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BangBangGroupMember implements IBangBangGroupMember {

    //userId群主
    public void exportMember(int userId, int groupId, List<Integer> userIds ) throws NoSuchMethodException, IOException,
            InvocationTargetException {
//        JSONArray groupUserIds=new JSONArray();
//        for(Integer iuserId :userIds){
//            groupUserIds.add(iuserId);
//        }
        //不是加工采购项目需要在工作计划书中导出组员
        if (userId == 0) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "工作计划书导出有问题，没有导出负责人！");
        }
        Integer bbGroupId = getBbGroupId(groupId);
        deleteMember(userId, bbGroupId, userIds);
        addMember(userId, bbGroupId, userIds);
    }


    Integer getBbGroupId(int group_id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select bb_group_id from ytb_project.work_group ");
        sql.append(" where group_id=").append(group_id);
        return YtbSql.selectOne(sql, int.class);
    }


    void deleteMember(int userId, int bbGroupId, List<Integer> groupUserIds) throws IOException,
            InvocationTargetException, NoSuchMethodException {
        for (Integer id : groupUserIds ) {
            Object ret = YtbUtils.invokeMethod_try("ytb.bangbang.service.impl.GroupServiceImpl", "deleteGroupUser",
                    new Class[]{int.class, int.class, int.class}, userId, id, bbGroupId);
        }
    }

    Object addMember(int userId, int bbGroupId, List<Integer> groupUserIds) throws InvocationTargetException,
            NoSuchMethodException {
        Object ret = YtbUtils.invokeMethod_try("ytb.bangbang.service.impl.GroupServiceImpl", "addGroupUser",
                new Class[]{int.class, int.class, List.class}, userId, bbGroupId, groupUserIds);
        if (ret != null) {
            int i = Integer.parseInt(ret.toString());
            if (i != 0) {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, ret.toString());
            }
        }
        return ret;
    }
}
