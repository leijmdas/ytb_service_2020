package ytb.project.service;

import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.service.impl.flow.FlowFolder;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IFlowFolder extends IProjectFileService {
    //组员准备提交
    String pmSubmitPrepare(UserProjectContext context, int userId);

    //组员提交给负责人
    void pmSubmit(UserProjectContext context, int userId);

    //乙方负责人审核
    void pbAudit(UserProjectContext context,String userId, int talkId, int status,String type);

    //乙方负责人阶段提交
    ProjectEventModel pbPhaseSubmit(UserProjectContext context,int talkId);

    //乙方负责人提交结项文档
    ProjectEventModel pbSubmitKnitDoc(UserProjectContext context,int talkId);


    //甲方阶段审核  PartyAVerify(int talkId,int status);
    ProjectEventModel paPhaseAudit(UserProjectContext context, int talkId, int status,String passWord) throws UnsupportedEncodingException;

    //甲方结项文档
    ProjectEventModel paAuditKnitDoc(UserProjectContext context, int talkId, int status) throws UnsupportedEncodingException;

    String getFolders(UserProjectContext context, int userId, int talkId, int phase);

    //copy 物料清单
    void copyTemplatePpMList(UserProjectContext context,int projectTypeId, int talkId, int userId) throws UnsupportedEncodingException;

    CustomTaskResult copyTemplatePp(UserProjectContext context,int projectTypeId, int docType) throws
            UnsupportedEncodingException;

    //删除文档
    void delPurchaseTemplate(int taskId);
     //共享文档
    void addShareTemplate(UserProjectContext context, int toUserId, int userId, int templateId) throws UnsupportedEncodingException;

    //取消共享的文档
    void cancelShareTemplate(int templateId,int cancelType);

    //添加协助文档
    FlowFolder.AssistResult addAssistTemplate(UserProjectContext context, int templateId, String toUserId, int userId,
                                              String remark) throws UnsupportedEncodingException;

    //添加协助文档
    List<UserAssistModel> showUserAssistFee(String userids, UserProjectContext context);
    //递交协助文档
    void sendAssistTemplate(int v, int auditor);
    //确认协助文档
    //void confirmAssistTemplate(int v, int status);

    ProjectTemplateModel selectDocument(int templateId) throws UnsupportedEncodingException;

    ProjectTemplateModel selectDocument_v(int docListId) throws UnsupportedEncodingException;

    //查看协助文档
    ProjectTemplateAssistModel selectProjectTemplateAssistModel(int templateIdAssits) throws UnsupportedEncodingException;

    //协助接收方修改状态
    void receiverModifyTemplateUserStatuslAssist(int id);

    void sponsorModifyTemplateUserStatusAssist(UserProjectContext context, int templateIdAssist, int id,
                                               int toUserId);

    //协助发起方审核文档
    void sponsorSubmitTemplateUserStatusAssist(    UserProjectContext context,   int templateIdAssist,
            int id,   int auditStatus  );


      List<ViewProjectTemplateUserModel> getFolderTreeTemplates(ProjectFolderModel projectFolder,
                                                                List<ViewProjectTemplateUserModel> userModelList,
                                                                int userId,
                                                                int author );

    }
