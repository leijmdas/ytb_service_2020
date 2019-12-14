package ytb.project.service;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectFolderModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectEventModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;

import java.util.List;
import java.util.Map;

public interface IFlowFolderView extends IFlowFolderAssist {
    void checkTemplateList(UserProjectContext context, int templateId, int status, int talkId);

    List<ViewProjectEventModel> getEventModels(UserProjectContext context, int stage, int userId);

    //获取协助的事件
    List<ViewProjectEventModel> getAssistEventModels(UserProjectContext context, int phase, int userId);

    List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(int folderId, int userId, int author);

    List<ViewProjectTemplateUserModel> getPmFoldersTemplates(List<ProjectFolderModel> folderModels, int userId,
                                                             int author);

    //获取项目tab栏（任务中心）
    Map<String,Object> getProjectTab(UserProjectContext context);

    List<ViewProjectTemplateUserModel> getKnitTemplateList(UserProjectContext context,int userId,
                                                           int phase);
}
