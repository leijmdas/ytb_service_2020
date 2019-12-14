package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.template.model.Dict_TemplateModel;

import java.io.UnsupportedEncodingException;

//拷贝加工采购岗位文档
public class CopyTemplateModelPpTask extends CopyTemplateModel {
    public ProjectTemplateModel copyTemplate(UserProjectContext context, int folderId,
                                             int userId, String taskName) throws UnsupportedEncodingException {
        return copyTemplate(context, context.getDict_tm(), folderId, userId, taskName);
    }

    //生成采购加工清单
    public ProjectTemplateModel copyTemplate(UserProjectContext context, Dict_TemplateModel dtm, int folderId,
                                             int userId, String taskName) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();

        //通过模板获取源文件
        CopyTemplateInfo templateInfo = new CopyTemplateInfo();
        templateInfo.setFolderId(folderId);

        templateInfo.setDocSeq("2");
        templateInfo.getTitleInfo().setStatus((byte) 1);
        templateInfo.getTitleInfo().setTaskName(taskName);
        templateInfo.getTitleInfo().setProjectName(pm.getProjectName());
        templateInfo.setDocVer("1.0.0.0");

        templateInfo.setAuthor(userId);
        templateInfo.setAuditor(pm.getUserId1());

        projectTemplateModel = copyTemplate(templateInfo, dtm);

        ProjectContext pc = new ProjectContext(context);
        pc.setDocumentId(projectTemplateModel.getDocumentId());
        pc.setUserId((long)userId);
        pc.modifyHeader(context);

        return projectTemplateModel;
    }

}
