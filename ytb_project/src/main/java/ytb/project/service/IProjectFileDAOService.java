package ytb.project.service;


import org.apache.ibatis.annotations.Param;
import ytb.project.dao.ProjectDocumentMapper;
import ytb.project.dao.ProjectFolderMapper;
import ytb.project.dao.ProjectTemplateMapper;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.projectTemplateUser.IProjectTemplateUser;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUser;

import java.util.List;

public interface IProjectFileDAOService extends ProjectFolderMapper, ProjectTemplateMapper, ProjectDocumentMapper {

     static IProjectTemplateUser templateUser = new ProjectTemplateUser();

     public static IProjectTemplateUser getProjectTemplateUser() {
          return templateUser;
     }


     //通过文档id获取文档
     ProjectTemplateModel getProjectTemplateById(int templateId);

     //根据文件夹查询文档
     List<ProjectTemplateModel> getTemplateListByFolder(int folderId);

     List<ProjectTemplateModel> getTemplateListByDocType(@Param("folderId") int folderId, @Param("docType")int docType);

     //添加模板文档
     int addTemplate(ProjectTemplateModel templateModel);

     //修改文档状态和审核人
     void modifyProjectTemplate(ProjectTemplateModel templateModel);

     //修改协助次数(增加1)
     void modifyTemplateAssistNumber(Integer templateId, Integer type);

     void reduceTemplateShareNumber(int templateId);

     //删除文档通过id
     void delProjectTemplateById(int templateId);

     //获取源文件通过id
     ProjectDocumentModel getProjectDocument(int documentId);

     //添加源文件
     int addProjectDocument(ProjectDocumentModel documentModel);


}
