package ytb.project.dao;

import ytb.project.model.ProjectDocumentModel;
import ytb.project.model.ProjectDocumentVModel;

public interface ProjectDocumentVMapper {

    //添加项目资源文件
    void addProjectDocumentV(ProjectDocumentModel projectDocument);

    //删除项目资源文件
    void deleteProjectDocument(int documentId);

    //添加项目资源文件
    void modifyProjectDocument(ProjectDocumentVModel projectDocumentV);

    //通过ID获取资源文件
    ProjectDocumentVModel getProjectDocumentV(int documentId);

    //添加项目资源文件
    void modifyProjectDocumentName(ProjectDocumentVModel projectDocumentV);
}
