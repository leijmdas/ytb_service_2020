package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;

import java.util.List;

public interface ProjectTemplateMapper {

    int addTemplate(ProjectTemplateModel templateModel);

    List<ProjectTemplateModel> getTemplateListByDocType(@Param("folderId") int folderId, @Param("docType")int docType);
    List<ProjectTemplateModel> getTemplateListByFolder (int folderId);
    //修改文档
    void modifyProjectTemplate(ProjectTemplateModel projectDocList);

    ProjectTemplateModel getProjectTemplateById(int templateId);

    void modifyShareStatus(ProjectTemplateModel projectDocList);

    List<ViewProjectTemplateUserModel> selectProjectTemplateUser(@Param("folderId") int folderId,@Param("userId") int userId);

    List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(@Param("folderId") int folderId,
                                                                          @Param("userId") int userId,
                                                                          @Param("author") int author);

    void delProjectTemplateById(int docListId);

    void modifyDocVer(ProjectTemplateModel projectDocList);

    //修改协助次数（加一）
    void modifyTemplateAssistNumber(@Param("templateId") Integer templateId,@Param("assistType")Integer type);

    //修改共享次数（减一）
    void reduceTemplateShareNumber(int templateId);
}
