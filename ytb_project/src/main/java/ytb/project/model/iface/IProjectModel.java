package ytb.project.model.iface;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectEventModel;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.service.impl.pay.payfee.ProjectRateTaxModel;

import java.util.List;

public interface IProjectModel {
    boolean isPa(Integer userId);

    boolean isCompanyUser();

    boolean isLastStage(int phase);

    boolean isFinishStage(int phase);

    boolean isNoChange();

    boolean isChange();

    boolean isChangeLittle();

    boolean isChangeBig();

    boolean isChangeStop();

    void modifyFolder();

    ProjectFolderModel selectProjectFolder();

    List<ProjectFolderModel> queryAllFolders();

    List<ProjectTemplateModel> selectPublishTemplates();

    ProjectTemplateModel selectReqTemplate();

    ProjectRateTaxModel selectProjectRateTaxModel(UserProjectContext context);

    ProjectEventModel addProjectEvent(UserProjectContext context, String remark,
                                      int evenType, int eventUserId, int eventAnother);


}
