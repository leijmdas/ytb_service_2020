package ytb.project.model.projectFolderView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectConst;
import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectDocumentModel;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.model.iface.IFolderModel;
import ytb.project.model.projectFolderView.projectTemplate.PAFolderCopyTemplateModel;
import ytb.manager.templateexcel.model.tag.impl.TagEnum;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.List;



public class PAFolder extends ProjectFolderBase implements IFolderModel {

    //项目根文件夹
    public ProjectFolderModel getProjectFolder(UserProjectContext context) {
        return  context.getProjectModel().selectProjectFolder();
    }

    //甲方发布生成项目文件夹与文档
    //发布时创建文件夹，拷贝文档，确定文档关系
    //0--正常发布，>0加工采购任务发布
    public List<ProjectTemplateModel> copyFolder(int copyPpReqTemplateId, UserProjectContext context, ProjectContext pc) throws UnsupportedEncodingException {

        //创建甲方文件家
        int folderId = createFolder(context, ProjectConst.TalkDraft);
        ProjectModel pm = context.getProjectModel();
        pm.setFolderId(folderId);
        //修改项目文件夹标识
        pm.modifyFolder();

        //甲方加工作组计划和需求书 拷贝模板
        //copyTemplates(copyPpReqTemplateId,context, pc);
        List<ProjectTemplateModel> paModels = new PAFolderCopyTemplateModel().copyTemplates(context, pc,
                copyPpReqTemplateId);
        //List<ProjectTemplateModel> paModels = selectPublishTemplates(context,folderId);
        //确定文档关系
        checkExistReqWorkplan(pc,paModels);
        //  确定甲方需求与计划文档关系
        context.getDocumentFamily().setUpRefAll(pc, paModels);
        return paModels;

    }

    //查询文件夹下的需求说明书与工作计划书模板
    public List<ProjectTemplateModel> selectPublishTemplates(UserProjectContext context)  {
        ProjectModel pm = context.getProjectModel();
        return pm.selectPublishTemplates();

    }

    ProjectTemplateModel selectReqTemplate(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        return pm.selectReqTemplate();
    }

    //find 乙方洽谈前可看的需求文档内容
    public ProjectTemplateModel pbViewReqTemplate(UserProjectContext context) throws Exception {
        ProjectTemplateModel templateModel = selectReqTemplate(context);
        if (templateModel.getDocumentId() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "documentId:" + templateModel.getDocumentId());
        }

        ProjectDocumentModel documentModel = context.getProjectFileService().getProjectDocument(templateModel.getDocumentId());
        String doc = new String(documentModel.getDocument(), "UTF-8");
        JSONObject docjson = JSONObject.parseObject(doc);
        JSONArray body = docjson.getJSONArray("body");
        JSONArray bodynew = JSONArray.parseArray("[]");
        for (int i = 0; i < body.size(); i++) {
            JSONObject r = body.getJSONObject(i);
            if (TagEnum.TAG_TEXT.equals(r.getString("tagType"))) {
                JSONArray dirs = r.getJSONArray("tagTitle").getJSONObject(0).getJSONArray("items");
                if (dirs.toJSONString().contains("A.")) {
                    bodynew.add(body.getJSONObject(i));
                } else if (dirs.toJSONString().contains("B.")) {
                    bodynew.add(body.getJSONObject(i));
                } else if (dirs.toJSONString().contains("C.")) {
                    bodynew.add(body.getJSONObject(i));
                } else if (dirs.toJSONString().contains("D.")) {
                    bodynew.add(body.getJSONObject(i));
                }
            }
        }
        docjson.put("body", bodynew);
        // List<JSONObject> lst = new TagTableProjectService().listTagTitleRed(projectId, m.getDocumentId());

        documentModel.setDocument(docjson.toJSONString().getBytes("UTF-8"));
        templateModel.setDocumentModel(documentModel);
        return templateModel ;
    }

    @Override
    public int createFolder(UserProjectContext context, int phase) {
        //设置文件夹
        ProjectModel pm = context.getProjectModel();

        ProjectFolderModel folder = new ProjectFolderModel(context);
        folder.setParentId(0);
        folder.setFolderName(pm.getProjectName());

        //项目根文件家
        folder.setFolderType(ProjectConst.FOLDER_TYPE_PROJECT);
        folder.setOwnerId(pm.getProjectId());
        folder.setProjectId(pm.getProjectId());
        folder.setTalkId(0);
        folder.setPhase(phase);
        folder.setFolderStatus(ProjectConst.FOLDER_STATUS_WRITE_PM);

        folder.setUserId(pm.getUserId1());
        folder.setAuditor(0);
        folder.setDepth(0);
        context.getProjectFileService().addFolderModel(folder);

        return folder.getFolderId();

    }

    //甲方加文档工作组计划和需求书 拷贝模板
    //    public ProjectContext copyTemplates(int copyPpReqTemplateId, UserProjectContext context, ProjectContext pc) throws UnsupportedEncodingException {
    //        return new PAFolderCopyTemplateModel().copyTemplates(copyPpReqTemplateId, context, pc); }


}
