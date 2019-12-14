package ytb.project.dao.tagtable;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.tagtable.WorkGroupMemberModel;

import java.util.List;
import java.util.Map;

public interface WorkGroupMemberMapper {

    List<WorkGroupMemberModel> getWorkGroupMember(@Param("groupId") Integer groupId,@Param
            ("userId") Integer userId);

    void addWorkGroupMember(WorkGroupMemberModel workGroupMember);

    void deleteWorkMember(int memberId);

    WorkGroupMemberModel getWorkGroupMemberById(@Param("projectId") int projectId,@Param("userId") int userId ,@Param("documentId") int documentId);

    List<Map<String, Object>> getWorkGroupMemberInfo(int groupId);

    //修改组员同意邀请状态
    void modifyActive(@Param("isActive") Integer isActive,@Param("memberId") Integer memberId);
}
