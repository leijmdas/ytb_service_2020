package ytb.project.model.projectFolderView.projectTemplate.ProjectChange;

import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateInfo;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateModel;
import ytb.manager.template.model.Dict_TemplateModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyTemplateModelChange extends CopyTemplateModel {

    public ProjectTemplateModel copyTemplate(UserProjectContext context,UserProjectContext
            oldContext,int folderId,int
            chngType ) throws UnsupportedEncodingException {
        ProjectModel pm = oldContext.getProjectModel();
        ProjectTalkModel ptm = oldContext.getProjectTalkModel();

        Dict_TemplateModel dictTemplateModel =
                oldContext.getInst().getProjectCacheManager().findChangeTemplateModel(chngType);


        //拷贝需求变更书（需求告知书）
        CopyTemplateInfo templateInfo = new CopyTemplateInfo();
        templateInfo.getTitleInfo().setTaskName("");
        templateInfo.getTitleInfo().setStatus((byte)1);
        templateInfo.getTitleInfo().setProjectName(pm.getProjectName());

        templateInfo.setFolderId(folderId);
        templateInfo.setAuthor(ptm.getUserId2());
        templateInfo.setDocVer("");
        templateInfo.setAuditor(pm.getUserId1());

        projectTemplateModel = copyTemplate(templateInfo, dictTemplateModel);

        projectTemplateUserControl.addProjectTemplateUserReqChange(context,oldContext,
                projectTemplateModel.getTemplateId());
        //修改文件头
        ProjectContext pc = new ProjectContext(context);
        pc.setDocumentId(projectTemplateModel.getDocumentId());
        pc.modifyHeader(context);
        return projectTemplateModel;
    }
    // 拷贝乙方文件夹
    public void copyPBProjectTemplate(UserProjectContext context, int newFolderId,ProjectContext newPc) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        List<ProjectTemplateModel> newModels = new ArrayList();
        for (ProjectTemplateModel templateModel : ptm.selectTalkTemplates()) {

            CopyTemplateInfo templateInfo = new CopyTemplateInfo();
            templateInfo.setFolderId(newFolderId);
            templateInfo.getTitleInfo().setTaskName("");
            templateInfo.getTitleInfo().setStatus((byte)2);
            templateInfo.getTitleInfo().setProjectName(pm.getProjectName());

            templateInfo.setAuthor(ptm.getUserId2());
            templateInfo.setDocSeq("");
            templateInfo.setAuditor(pm.getUserId1());
            templateInfo.setDocVer("2.0.0.0");

            ProjectTemplateModel newModel = copyTemplate(templateInfo, templateModel);
            newModels.add(newModel);

            newPc.setDocumentId(newModel.getDocumentId());
            newPc.modifyHeader(context);
        }

        newPc.setUpReqWorkplan(newModels);
        newPc.checkReqWorkplan();

    }





    public Map<Integer, Integer> copyPBTalkTemplate(UserProjectContext context,int folderId )   {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Map<Integer, Integer> template_map = new HashMap<>();

        List<ProjectTemplateModel> models = getInst().getProjectFileService().getTemplateListByFolder(folderId);
        for (ProjectTemplateModel templateModel : models) {


            CopyTemplateInfo templateInfo = new CopyTemplateInfo();
            templateInfo.getTitleInfo().setTaskName("");
            templateInfo.getTitleInfo().setStatus((byte)2);
            templateInfo.getTitleInfo().setProjectName(pm.getProjectName());

            templateInfo.setFolderId(folderId);
            templateInfo.setAuthor(ptm.getUserId2());
            templateInfo.setDocVer("");
            templateInfo.setAuditor(pm.getUserId1());
            //新
            ProjectTemplateModel projectTemplateModel = copyTemplate(templateInfo, templateModel);
            //临时ID，防止doc_map.put两个键值一样
            template_map.put(templateModel.getTemplateId(), projectTemplateModel.getTemplateId());
        }
        return template_map;

    }
}
