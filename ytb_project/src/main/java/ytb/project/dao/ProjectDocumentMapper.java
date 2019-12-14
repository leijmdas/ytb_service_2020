package ytb.project.dao;


import ytb.project.model.ProjectDocumentModel;

public interface ProjectDocumentMapper {

    //添加项目资源文件
    int addProjectDocument(ProjectDocumentModel projectDocument);

    //删除项目资源文件
    void deleteProjectDocument(int documentId);

    //添加项目资源文件
    void modifyProjectDocument(ProjectDocumentModel projectDocument);

    //通过ID获取资源文件
    ProjectDocumentModel getProjectDocument(int documentId);
    //添加项目资源文件
    void modifyProjectDocumentName(ProjectDocumentModel projectDocument);


}
