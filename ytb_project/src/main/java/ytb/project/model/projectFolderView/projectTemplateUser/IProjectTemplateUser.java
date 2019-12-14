package ytb.project.model.projectFolderView.projectTemplateUser;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTemplateUserModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;

import java.util.List;

public interface IProjectTemplateUser {
    //修改版本号
    void versionUpgrade(int templateId, int status);

    List<ViewProjectTemplateUserModel> selectViewProjectTemplateUserModel(int folderId, int userId, int author);

    //文档控制
    void add(UserProjectContext context,
             int userId,
             int templateId,
             int status,
             int activeStatus,
             int displayStatus,
             int authorId);

    void add(UserProjectContext context,
             int userId,
             int templateId,
             int status,
             int activeStatus,
             int displayStatus,
             int authorId,
             int templateIdAssits);

    void add(ProjectTemplateUserModel m);
    //查询文档及控制状态
    ProjectTemplateUserModel selectOne(int userId, int templateId);
    List<ViewProjectTemplateUserModel> select(int folderId, int userId);

    //修改用户文档状态
    void modify(int pa, int pb, int templateId, int status);

    void modifyStatusPbPm(int pb, int pm, int templateId);

    void modify(ProjectTemplateUserModel templateUserModel);

    //甲方查询阶段文件是否审核
    int paCheckModifyStatus(int folderId, int color, int userId,int author);
    //修改洽谈审核状态
    void modifyTalkAuditStatus(int pa, int pb, int templateId, int status);

    //修改终止文档状态
    void modifyByTalkEndStatus(UserProjectContext context, int templateId);


    //获取协助文档
    List<ProjectTemplateUserModel> getTemplateUserModelsAssist( int templateId, int userId);

    //协助接收者修改文
    void receiverModifyTemplateUserStatus(int id, int status, int activeStatus, int displayStatus);

    //协助接收者递交协助文档
    void receiverSubmitTemplateUserStatusAssist(ProjectTemplateUserModel ptum);

    //根据user_id、template_id_assist查找
    ProjectTemplateUserModel getProTemplateUserByUserIdAndTemplateIdAssist(int userId, int templateIdAssist);

    //小变更文档升级
    void smallChangeUpgradeTemplate(UserProjectContext context);

    //批量更新
    void modifyProjectTemplateUserBatch(List<ProjectTemplateUserModel> templateUserList);
}
