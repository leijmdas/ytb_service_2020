package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectSrvContext;
import ytb.project.model.ProjectDocumentModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;
import ytb.project.service.impl.flow.ProjectFileServiceImpl;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.model.TemplateModel;

import java.util.Date;


//大小
public class CopyTemplateModel extends ProjectFileServiceImpl {

    protected ProjectTemplateUserControl projectTemplateUserControl=new ProjectTemplateUserControl();
    protected ProjectTemplateModel projectTemplateModel ;

    //源文件设置添加
    public int addProjectDocument(Dict_document dict_document) {
        return addProjectDocument(new ProjectDocumentModel(dict_document));
    }

    public ProjectTemplateModel buildTemplateModel(CopyTemplateInfo copyInfo, TemplateModel model) {
        ProjectTemplateModel projectTemplateModel = new ProjectTemplateModel();
        //需求书是通过项目类别配置的文档模板生成,文档名是项目名称+原需求模板文档名
        projectTemplateModel.setFolderId(copyInfo.getFolderId());//文件夹id
        projectTemplateModel.setDocType(model.getDocType());//文档类型
        projectTemplateModel.setDocSeq(copyInfo.getDocSeq());

        copyInfo.getTitleInfo().setTitle(model.getTitle());
        projectTemplateModel.setTitle(copyInfo.buildTitle().toString());//文档名称
        projectTemplateModel.setDocVer(copyInfo.getDocVer());

        projectTemplateModel.setStatus(ProjectConst.P_TEMPLATE_STAT_WRITE_PM);//作者编写状态
        // projectTemplateModel.setDocumentId(documentId);//文档源文件id

        projectTemplateModel.setAuthor(copyInfo.getAuthor());//作者
        projectTemplateModel.setAuditor(copyInfo.getAuditor());

        projectTemplateModel.setShareNum(0);
        projectTemplateModel.setAssistNum(0);
        projectTemplateModel.setUpdateTime(new Date());

        return projectTemplateModel;
    }

    //从项目本身拷贝模板
    public ProjectTemplateModel copyTemplate(CopyTemplateInfo copyInfo, ProjectTemplateModel ptm) {
        projectTemplateModel = buildTemplateModel(copyInfo, ptm);

        ProjectDocumentModel documentModel = ProjectSrvContext.getInst().getProjectFileService().getProjectDocument(ptm.getDocumentId());
        int documentId = addProjectDocument(documentModel);
        projectTemplateModel.setDocumentId(documentId);
        projectTemplateModel.setDocumentModel(documentModel);

        addTemplate(projectTemplateModel);
        return projectTemplateModel;
    }

    //从系统模板字典拷贝字典
    public ProjectTemplateModel copyTemplate(CopyTemplateInfo copyInfo, Dict_TemplateModel dtm) {

        projectTemplateModel = buildTemplateModel(copyInfo, dtm);

        Dict_document document = getInst().getTemplateRepositoryService().getDocument(dtm.getDocumentId());
        int documentId = addProjectDocument(document);
        projectTemplateModel.setDocumentId(documentId);
        projectTemplateModel.setDocSeq("");
        addTemplate(projectTemplateModel);
        return projectTemplateModel;
    }


//    public ProjectTemplateModel buildTemplateModel(CopyTemplateInfo copyInfo,
//                                                   TemplateModel model ,
//                                                   int documentId   ) {
//        ProjectTemplateModel ptm = new ProjectTemplateModel();
//        ptm.setFolderId(copyInfo.getFolderId());//文件夹id
//        ptm.setDocType(model.getDocType());//文档类型
//        ptm.setDocSeq(copyInfo.getDocSeq());
//
//        //需求书是通过项目类别配置的文档模板生成,文档名是项目名称+原需求模板文档名
//        copyInfo.getTitleInfo().setTitle(model.getTitle());
//        StringBuilder st = copyInfo.getTitleInfo().buildTitle();
//        ptm.setTitle(st.toString());//文档名称
//        ptm.setDocVer(copyInfo.getDocVer());
//
//        ptm.setDocumentId(documentId);//文档源文件id
//        ptm.setStatus(ProjectConst.P_TEMPLATE_STAT_WRITE_PM);//作者编写状态
//
//        ptm.setAuthor(copyInfo.getAuthor());//作者
//        ptm.setAuditor(copyInfo.getAuditor());
//
//        ptm.setShareNum(0);
//        ptm.setAssistNum(0);
//        ptm.setUpdateTime(new Date());
//        return ptm;
//    }


}
