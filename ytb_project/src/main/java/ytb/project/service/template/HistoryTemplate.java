package ytb.project.service.template;

import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.projectTemplateVMapper;
import ytb.project.dao.ProjectDocumentVMapper;
import ytb.project.daoservice.DAOService;
import ytb.project.model.ProjectDocumentModel;
import ytb.project.model.ProjectTemplateVModel;

import ytb.project.model.ProjectTemplateModel;
import ytb.common.context.service.impl.YtbContext;
import java.util.List;

public class HistoryTemplate extends DAOService {

    //添加历史模板文档
    public int addProjectTemplateV(ProjectTemplateModel projectTemplateModel) {
        try (SqlSession session = getSession()) {
            projectTemplateVMapper pDLMapper = session.getMapper(projectTemplateVMapper.class);
            pDLMapper.addProjectTemplate(projectTemplateModel);
            return YtbContext.getSqlSessionBuilder().selectAutoID(session);
        }
    }

    //添加历史资源文件
    public void addProjectDocumentV(ProjectDocumentModel projectDocument) {
        try (SqlSession session = getSession()) {
            ProjectDocumentVMapper pMapper = session.getMapper(ProjectDocumentVMapper.class);
            pMapper.addProjectDocumentV(projectDocument);
            session.commit();
        }
    }

    //查询文档历史版本
    public  List<ProjectTemplateVModel>  selectTemplateV(int templateId){

        try (SqlSession session = getSession()) {
            projectTemplateVMapper pDLMapper = session.getMapper(projectTemplateVMapper.class);
            return  pDLMapper.selectHistoryDoc(templateId);

        }
    }

}
