package ytb.project.dao.tagtable;


import ytb.project.model.tagtable.ProjectMemberDutyModel;

import java.util.List;
import java.util.Map;

public interface ProjectMemberDutyMapper {

    //获取组员岗位
    List<ProjectMemberDutyModel> getProjectMemberDuty(int memberId);

    List<Map<String, Object>> getAllProjectMemberDuty(int projectId);

    //获取岗位
    List<ProjectMemberDutyModel> getTalkMemberDuty(int talkId);


}
