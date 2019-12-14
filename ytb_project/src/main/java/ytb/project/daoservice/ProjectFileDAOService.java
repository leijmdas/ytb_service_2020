package ytb.project.daoservice;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.project.dao.*;
import ytb.project.model.*;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.common.context.model.YtbError;

import java.util.List;

public abstract class ProjectFileDAOService extends  ProjectFolderDAOService implements IProjectFileDAOService {


    public List<ViewProjectTemplateUserModel> selectProjectTemplateUser(int folderId, int userId) {
        try (SqlSession session = getSession()) {

            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            return templateMapper.selectProjectTemplateUser(folderId, userId);

        }

    }
    public List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(int folderId,
                                                                                 int userId,
                                                                                 int author) {
        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            return templateMapper.selectViewProjectTemplateUserModel(folderId, userId,author);

        }
    }
    public int addTemplate(ProjectTemplateModel templateModel) {
        if (templateModel.getTitle().isEmpty()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "模板标题为空！");
        }

        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            templateMapper.addTemplate(templateModel);
            templateModel.setTemplateId(YtbSql.selectAutoID(session));
            return templateModel.getTemplateId();
        }
    }
    public void modifyDocVer(ProjectTemplateModel m){

        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            templateMapper.modifyDocVer(m);

        }
    }
    //修改文档
    public void modifyProjectTemplate(ProjectTemplateModel templateModel) {
        try (SqlSession session = getSession()) {
            ProjectTemplateMapper pDLMapper = session.getMapper(ProjectTemplateMapper.class);
            pDLMapper.modifyProjectTemplate(templateModel);
            session.commit();
        }
    }


    //删除文档通过文档id
    public void delProjectTemplateById(int templateId) {

        try (SqlSession session = getSession()) {

            ProjectTemplateMapper pDLMapper = session.getMapper(ProjectTemplateMapper.class);
            pDLMapper.delProjectTemplateById(templateId);
            session.commit();
        }
    }

    @Override
    public int addProjectDocument(ProjectDocumentModel projectDocument) {
        try (SqlSession session = getSession()) {

            ProjectDocumentMapper documentMapper = session.getMapper(ProjectDocumentMapper.class);
            documentMapper.addProjectDocument(projectDocument);
            projectDocument.setDocumentId(YtbSql.selectAutoID(session));
            return projectDocument.getDocumentId();

        }
    }


    @Override
    public void modifyProjectDocumentName(ProjectDocumentModel projectDocument) {
        try (SqlSession session = getSession()) {

            ProjectDocumentMapper pMapper = session.getMapper(ProjectDocumentMapper.class);
            pMapper.modifyProjectDocumentName(projectDocument);
            session.commit();
        }
    }

    @Override
    public void modifyProjectDocument(ProjectDocumentModel projectDocument) {
        try (SqlSession session = getSession()) {

            ProjectDocumentMapper documentMapper = session.getMapper(ProjectDocumentMapper.class);
            documentMapper.modifyProjectDocument(projectDocument);
            session.commit();
        }
    }

    @Override
    public void deleteProjectDocument(int documentId) {
        try (SqlSession session = getSession()) {

            ProjectDocumentMapper pMapper = session.getMapper(ProjectDocumentMapper.class);
            pMapper.deleteProjectDocument(documentId);
            session.commit();
        }
    }


    //修改分享次数减一
    public void reduceTemplateShareNumber(int templateId) {
        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            templateMapper.reduceTemplateShareNumber(templateId);
        }
    }


    //修改协助或者分享的次数（加1）
    public void modifyTemplateAssistNumber(Integer templateId, Integer type) {
        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            templateMapper.modifyTemplateAssistNumber(templateId, type);
        }
    }


    //修改文档共享
    public void modifyShareStatus(ProjectTemplateModel templateModel) {

        try (SqlSession session = getSession()) {

            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            templateMapper.modifyShareStatus(templateModel);
            session.commit();
        }
    }

    //通过文档id获取文档
    public ProjectTemplateModel getProjectTemplateById(int templateId) {
        try (SqlSession session = getSession()) {
            ProjectTemplateMapper pDLMapper = session.getMapper(ProjectTemplateMapper.class);
            return pDLMapper.getProjectTemplateById(templateId);
        }
    }



    public List<ProjectTemplateModel> getTemplateListByDocType(int folderId, int docType) {

        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            return templateMapper.getTemplateListByDocType(folderId, docType);
        }
    }

    //根据文件夹查询文档
    public List<ProjectTemplateModel> getTemplateListByFolder(int folderId) {
        try (SqlSession session = getSession()) {
            ProjectTemplateMapper templateMapper = session.getMapper(ProjectTemplateMapper.class);
            return templateMapper.getTemplateListByFolder(folderId);
        }
    }

    //根据源文件ID查询源文件
    public ProjectDocumentModel getProjectDocument(int documentId) {
        try (SqlSession session = getSession()) {
            ProjectDocumentMapper documentMapper = session.getMapper(ProjectDocumentMapper.class);
            return documentMapper.getProjectDocument(documentId);
        }
    }



}
