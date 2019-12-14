package ytb.project.service.impl.tagtable;

import ytb.bangbang.service.impl.GroupServiceImpl;
import ytb.common.utils.YtbSql;
import ytb.manager.tagtable.service.impl.IBangBangGroupMember;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProjectBBGroupMember implements IBangBangGroupMember {

    //userId群主
    public void exportMember(int userId, int groupId, List<Integer> userIds)
            throws NoSuchMethodException, IOException, InvocationTargetException {

        //不是加工采购项目需要在工作计划书中导出组员
        if (userId == 0) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "工作计划书导出有问题，没有导出负责人！");
        }
        Integer bbGroupId = selectBbGroupId(groupId);
        GroupServiceImpl groupService = new GroupServiceImpl();
        for (Integer id : userIds) {
            groupService.deleteGroupUser(userId, id, bbGroupId);
        }
        groupService.addGroupUser(userId, bbGroupId, userIds);
    }


    Integer selectBbGroupId(int group_id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select bb_group_id from ytb_project.work_group ");
        sql.append(" where group_id=").append(group_id);
        return YtbSql.selectOne(sql, Integer.class);
    }


}
