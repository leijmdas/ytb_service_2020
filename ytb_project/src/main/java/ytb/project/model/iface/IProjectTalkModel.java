package ytb.project.model.iface;

import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.impl.pay.payevent.PayEventModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IProjectTalkModel {

    boolean checkTalkTerm();
    boolean checkChangeBig();
    boolean checkStop();
    boolean checkFinish();

    boolean isCompanyUser();

    boolean isPm(int userId);

    boolean isPb(Integer userId);

    void modifyWorkplanId();

    //非加工采购项目，cost表无费用 文档则无效 // 检查有费用数据,人员日薪都不为0
    void checkExistCost( UserProjectContext context ) throws IOException;

    List<WorkGroupMemberModel> selectWorkGroupMember();

    //只返回组员，不返回PA,PB
    List<WorkGroupMemberModel> selectWorkGroupMemberPM();

    List<ProjectFolderModel> selectAllFolders();

    List<ProjectFolderModel> selectUserPhaseFolders(int userId);

    List<ProjectTemplateModel> selectUserPhaseTemplates(int userId);

    List<ProjectTemplateModel> selectUserPhaseTemplates(int userId, int docType);

    ProjectFolderModel fetchTalkFolderModel();

    ProjectFolderModel fetchPhaseFolder();

    ProjectFolderModel fetchPhaseFolder(int phase);

    //查询文件夹下的需求说明书与工作计划书模板
    List<ProjectTemplateModel> selectTalkTemplates();
    ProjectTemplateModel selectReqTemplate();
    ProjectTemplateModel selectWorkplanTemplate();

    ProjectTemplateModel findPbTemplate(int docType);
    //获取文件夹以及子文件夹下面所有文档
    List<ProjectTemplateModel> selectPhaseAllTemplates();

    //项目实际开始时间结束时间
    Date getProjectStartTime();

    Date getProjectEndTime();

    //计划开始结束时间
    Date getPlanStartTime();

    Date getPlanEndTime(UserProjectContext context);


    //获取阶段结束时间
    Date getPhaseEndTime(UserProjectContext context);

    ProjectEventModel addTalkEvent(UserProjectContext context, ProjectEventModel eventModel);

    ProjectEventModel addTalkEvent(UserProjectContext context, String remark,
                                   int evenType, int eventUserId, int eventAnother);

    ProjectEventModel addAssistEvent(UserProjectContext context, String remark,
                                     int evenType, int eventUserId, int eventAnother);

    ProjectEventModel addPayEvent(UserProjectContext context, PayEventModel eventModel);

}
