package ytb.project.model.projectFolderView.projectTemplate;

import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PAFolderCopyTemplateModel extends CopyTemplateModel {

    //甲方加文档工作组计划和需求书 拷贝模板
    public List<ProjectTemplateModel> copyTemplates(UserProjectContext context, ProjectContext pc, int copyReqTemplateId) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();

        pc.setUserId((long)pm.getUserId1());
        pc.setProjectTypeId(pm.getProjectTypeId());
        pc.setProjectId(pm.getProjectId());
        pc.setTalkId(0);
        pc.setPhase(ProjectConst.RequestIn);
        pc.setGroupId(0);

        List<ProjectTemplateModel> paModels=new ArrayList<>();
        //需求说明书
        if (copyReqTemplateId == 0) {
            Dict_TemplateModel dictModel = findDictTemplateModel(context, ProjectConst.Template_TYPE_req);
            ProjectTemplateModel paModel = copyTemplate(context, pc, dictModel);
            paModels.add(paModel);
        } else {
            //加工采购的需求说明书
            ProjectTemplateModel projectModel = findProjectTemplateModel(context, copyReqTemplateId);
            ProjectTemplateModel paModel = copyTemplate(context, pc, projectModel);
            paModels.add(paModel);
        }
        //工作计划书与报价单的添加
        Dict_TemplateModel dictModel = findDictTemplateModel(context, ProjectConst.Template_TYPE_workplan);
        ProjectTemplateModel paModel =copyTemplate(context, pc, dictModel);
        paModels.add(paModel);

        return paModels;
    }

    public ProjectTemplateModel copyTemplate(UserProjectContext context, ProjectContext pc, ProjectTemplateModel templateModel) throws UnsupportedEncodingException {

        //通过模板获取源文件
        CopyTemplateInfo copyInfo = buildCopyTemplateInfo(context, templateModel.getTitle());
        ProjectTemplateModel projectTemplateModel = copyTemplate(copyInfo, templateModel);
        pc.setDocumentId(projectTemplateModel.getDocumentId());
        pc.setProjectTemplateId(projectTemplateModel.getTemplateId());
        pc.modifyHeader(context);
        return projectTemplateModel;
    }

    //甲方拷贝模板生成工作组计划和需求书
    public ProjectTemplateModel copyTemplate(UserProjectContext context, ProjectContext pc, Dict_TemplateModel templateModel) throws UnsupportedEncodingException {

        CopyTemplateInfo copyInfo = buildCopyTemplateInfo(context, templateModel.getTitle());
        ProjectTemplateModel projectTemplateModel = copyTemplate(copyInfo, templateModel);

        pc.setDocumentId(projectTemplateModel.getDocumentId());
        pc.setProjectTemplateId(projectTemplateModel.getTemplateId());
        pc.modifyHeader(context);
        return projectTemplateModel;
    }


    CopyTemplateInfo buildCopyTemplateInfo(UserProjectContext context,String templateTitle){
        ProjectModel pm = context.getProjectModel();

        CopyTemplateInfo copyInfo = new CopyTemplateInfo();
        copyInfo.setFolderId(pm.getFolderId());
        copyInfo.setDocSeq(""+ ProjectConstState.TEMPLATE_STAGE_PUBLISH);

        copyInfo.getTitleInfo().setProjectName(pm.getProjectName());
        copyInfo.getTitleInfo().setStatus((byte)1);
        copyInfo.getTitleInfo().setTaskName("");
        copyInfo.getTitleInfo().setTitle(templateTitle);
        copyInfo.setDocVer("0.1");

        copyInfo.setAuthor(pm.getUserId1());
        copyInfo.setAuditor(0);
        return copyInfo;
    }


    protected ProjectTemplateModel findProjectTemplateModel(UserProjectContext context, int copyPpReqTemplateId) {
        ProjectTemplateModel model = context.getProjectFileService().getProjectTemplateById(copyPpReqTemplateId);
        if (model == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "被拷贝模板");
        }
        return model;
    }

    protected Dict_TemplateModel findDictTemplateModel(UserProjectContext context, int docType) {

        ProjectModel pm = context.getProjectModel();
        Dict_ProjectTypeModel ptm = context.getDict_ProjectTypeModel();
        //通过项目分类查询需求模板文档
        int reqDocType = ProjectConst.Template_TYPE_req;
        int workplanDocType = ProjectConst.Template_TYPE_workplan;
        if (ptm.isPurchase()) {
            reqDocType = ProjectConst.Template_TYPE_req_purchase;
            workplanDocType = ProjectConst.Template_TYPE_workplan_purchasePrice;
        } else if (ptm.isProcessing()) {
            reqDocType = ProjectConst.Template_TYPE_req_process;
            workplanDocType = ProjectConst.Template_TYPE_workplan_processPrice;
        }
        Dict_TemplateModel templateModel = null;
        //需求书
        if (docType == Dict_TemplateModel.Template_TYPE_req) {
            templateModel = context.getProjectCacheManager().findDictTemplateModel(pm.getProjectTypeId(), reqDocType);
        } else {
            //通过项目分类查询工作计划书
            templateModel = context.getProjectCacheManager().findDictTemplateModel(pm.getProjectTypeId(), workplanDocType);
        }
        if (templateModel == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "需求或者工作计划书模板");
        }
        return templateModel;
    }
}
