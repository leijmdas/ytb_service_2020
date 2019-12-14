package ytb.project.context;

import ytb.project.model.ProjectFolderModel;
import ytb.project.service.impl.pay.payfee.ProjectRateTaxModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.common.context.service.IUserContext;

public interface IUserProjectContext extends IUserContext {

    UserProjectContext buildModelInvite(int talkId);

    boolean needInvoice();

    ProjectRateTaxModel selectProjectRateTaxModel();


    //获取本项目的模板文档
    Dict_TemplateModel findDictTemplateModel(int docType);

    ProjectFolderModel getTalkFolderModel();

    ProjectFolderModel getPhaseFolder();

    ProjectFolderModel getPhaseFolder(int phase);


    //获取项目类型指定的模板类型文档
    Dict_TemplateModel findDictTemplateModel(int projectTypeId, int docType);


    boolean isPhaseIn();

    boolean isPhaseIn(int phase);

    boolean isLastStage(int phase);

    boolean isFinishStage();

    String buildProjectUserType();

    boolean isPa();

    boolean isPb();

    boolean isPm();

    //甲方是公司否？
    boolean paIsCompanyUser();

    //乙方是公司否？
    boolean pbIsCompanyUser();

    boolean isPurchase();

    boolean isProcessing();

    boolean isPurchaseProcessing();

    //获取项目类别的阶段数
    //int buildPhaseNo(int projectType);

    boolean existsDict_ProjectTypeModel(int projectTypeId);

    UserProjectContext buildModel(int talkId);


    UserProjectContext buildModelProject(int projectId);

    UserProjectContext buildDict_ProjectTypeModel();


}
