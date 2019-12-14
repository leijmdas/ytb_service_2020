package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectTemplateUserModel;

import java.util.List;

public interface ProjectTemplateUserMapper {


    void addProjectTemplateUserModel(ProjectTemplateUserModel templateUserModel);

    //修改用户所在文档状态
    void modifyProjectTemplateUserModel(ProjectTemplateUserModel templateUserModel);

    //查询用户是否已加入文档状态
    ProjectTemplateUserModel selectProjectTemplateUser(@Param("userId") int userId, @Param("templateId") int documentId);

    //获取用户协助的文档状态控制
    List<ProjectTemplateUserModel> getProjectTemplateUserListAssist(
            @Param("templateId") int templateId,
            @Param("userId") int userId);

    //协助接收者修改文档状态控制
    void updateProjectTemplateUserStatus(@Param("id") int id,
                                         @Param("status") int status,
                                         @Param("activeStatus") int activeStatus,
                                         @Param("displayStatus") int displayStatus);

    void updateProjectTemplateUserStatusAssist(ProjectTemplateUserModel ptum);

    ProjectTemplateUserModel getProTemplateUserByUserIdAndTemplateIdAssist(@Param("userId") int
                                                                                   userId,
                                                                           @Param("templateIdAssist") int templateIdAssist);

    //批量更新
   void modifyProjectTemplateUserBatch(@Param(value = "templateUserList")
                                               List<ProjectTemplateUserModel>
                                               templateUserList);
}
