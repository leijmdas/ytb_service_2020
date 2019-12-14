package ytb.project.service.impl.flow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import ytb.project.common.ProjectCommonUtils;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.*;
import ytb.project.dao.IProjectTemplateAssistModelService;
import ytb.project.daoservice.ChangeDaoServiceImpl;
import ytb.project.daoservice.ProjectTemplateAssistModelServiceImpl;
import ytb.project.model.*;
import ytb.project.model.projectFolderView.TaskFolder;
import ytb.project.model.projectFolderView.projectTemplate.CopyTemplateModelPpTask;
import ytb.project.model.projectFolderView.projectTemplateUser.ProjectTemplateUserControl;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.IFlowFolder;
import ytb.project.service.IProjectFileDAOService;
import ytb.project.service.impl.pay.payassist.ProjectPayAssist;
import ytb.project.service.template.TemplateAssist;
import ytb.project.service.template.DocumentToolService;
import ytb.project.service.impl.talk.WorkGroup;
import ytb.project.view.model.ProjectTaskViewModel.ViewProjectTemplateUserModel;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

import java.io.UnsupportedEncodingException;
import java.util.*;

//文档工作流流程

public class FlowFolder extends ProjectFileServiceImpl implements IFlowFolder {

    //组员PM提交准备
    public String pmSubmitPrepare(UserProjectContext context, int userId) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        JSONArray jsonArray = new JSONArray();
        List<ProjectFolderModel> memberFolder = getProjectPhaseFolders(ptm.getProjectId(),   userId, ptm.getPhase() );
        if (memberFolder != null) {
        //    jsonArray = getFolderDoc(memberFolder, jsonArray,userId);
        }
        Map map = new HashMap();
        UserInfoModel infoModel = getInst().getUserCenterService().getUserInfoById(ptm.getUserId2());//获取用户信息
        map.put("nickName", infoModel.getNickName());
        if (ptm.getCompanyId2() > 0) {
            CompanyInfoModel companyInfoModel = getInst().getCompanyCenterService().getCompanyInfoById(ptm.getCompanyId2());
            map.put("companyName", companyInfoModel.getCompanyName());
        }
        return "{\"list\":" + jsonArray.toJSONString() + ",\"mapList\":" + JSON.toJSONString(map) + "}";
    }

    //组员PM提交文档给负责人审核
    public void pmSubmit(UserProjectContext context, int userId) {

        pmSubmit_check(context);
        ProjectTalkModel ptm = context.getProjectTalkModel();
        List<ProjectFolderModel> memberFolderList =  ptm.getMemberFolderModels(userId);
        for (ProjectFolderModel folderModel : memberFolderList) {
            List<ProjectTemplateModel> models =  getTemplateListByFolder(folderModel.getFolderId());
            for (ProjectTemplateModel model : models) {
                IProjectFileDAOService.getProjectTemplateUser().modifyStatusPbPm(ptm.getUserId2(), userId, model.getTemplateId());
            }
            //修改文件夹状态
            submitFolderAndTemplate(context,folderModel, ptm.getUserId2());
        }

        ptm.addTalkEvent(context, "组员提交文档", 5, userId, ptm.getUserId2());
    }

    void pmSubmit_check(UserProjectContext context ) {
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行阶段,你无法提交!");
        }
        if ( context.isPb()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你是项目负责人,不需要提交给自己审核!");
        }
        if (!context.isPm()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是项目组成员,你无法提交!");
        }
    }

    void pbAudit_check(UserProjectContext context) {
        if (!context.isPhaseIn()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行阶段,你（乙方负责人）无法审核!");
        }

        if (!context.isPb()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是项目负责人,无法审核!");
        }
    }

    //负责人PB审核(多个组员)
    public void pbAudit(UserProjectContext context, String userId, int talkId, int status,String
            type) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();

        pbAudit_check(context);

        String[] pmUserId = userId.split(",");//多个组员
        for(int i = 0 ;i<pmUserId.length; i++){
            List<ProjectFolderModel> folderList =  ptm.getMemberFolderModels(Integer.parseInt
                    (pmUserId[i]));
            for (ProjectFolderModel folderModel : folderList) {
                folderModel.setFolderStatus(status);
                folderModel.setAuditor(ptm.getUserId2());

                modifyFolderModel(folderModel);//修改文件夹状态

                List<ProjectTemplateModel> templateModelList = getTemplateListByFolder(folderModel.getFolderId());
                for (ProjectTemplateModel doc : templateModelList) {
                    if (status == ProjectConst.FOLDER_STATUS_PASS_PM) {
                        IProjectFileDAOService.getProjectTemplateUser().modifyTalkAuditStatus(ptm
                                        .getUserId2(), Integer.parseInt(pmUserId[i]),
                                doc.getTemplateId(), 1);
                    } else {
                        IProjectFileDAOService.getProjectTemplateUser().modifyTalkAuditStatus(ptm.getUserId2(), Integer.parseInt(pmUserId[i]),
                                doc.getTemplateId(), 2);
                        IProjectFileDAOService.getProjectTemplateUser().versionUpgrade(doc.getTemplateId(), 3);//升n档
                    }
                }
            }
            ptm.addTalkEvent(context, "负责人审核组员文档", status == 3 ? 15 : 16, ptm.getUserId2(), Integer.parseInt(pmUserId[i]));
        }

        if(!"".equals(type)){//代表变更结项那边审核
            //修改project_change
            new ChangeDaoServiceImpl().updateProjectChange(pm.getNewProjectId(),null,15);

        }

    }

    // 负责人阶段提交给甲方PB2PA
    public ProjectEventModel pbPhaseSubmit(UserProjectContext context, int talkId) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (ptm.getPhase() < pm.getPhaseStart() || ptm.getPhase() > pm.getPhaseEnd()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行阶段,你（乙方负责人）无法提交!");
        }
        if (!context.isPb()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是项目负责人,无法提交!");
        }

        ProjectFolderModel phaseFolder = ptm.fetchPhaseFolder() ;
        submitFolderAndTemplate(context,phaseFolder, pm.getUserId1());
        new ProjectTemplateUserControl().pbSubmitModifyTemplateUserStatus(phaseFolder, pm.getUserId1(), ptm.getUserId2());


        return ptm.addTalkEvent(context, "负责人提交阶段文档", ProjectConst.FOLDER_STATUS_SUBMIT_PB, ptm.getUserId2(),
                pm.getUserId1() );

    }
    // 负责人结项文档给甲方
    public ProjectEventModel pbSubmitKnitDoc(UserProjectContext context, int talkId) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        if (!context.isPb()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是项目负责人,无法提交!");
        }
        ProjectFolderModel phaseFolder = ptm.fetchPhaseFolder() ;
        submitFolderAndTemplate(context,phaseFolder, pm.getUserId1());
        new ProjectTemplateUserControl().pbSubmitModifyTemplateUserStatus(phaseFolder, pm.getUserId1(), ptm.getUserId2());

        new ChangeDaoServiceImpl().updateProjectChange(pm.getNewProjectId(),null,16);

        return ptm.addTalkEvent(context, "负责人提交结项文档", ProjectConst.FOLDER_STATUS_SUBMIT_PB, ptm.getUserId2(),
                pm.getUserId1() );

    }

    //PA甲方审核阶段文档
    public ProjectEventModel paPhaseAudit(UserProjectContext context, int talkId, int status,String passWord) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();

        if (! context.isPhaseIn() ) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行阶段,甲方无法进行审核!");
        }
        if (!context.isPa()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是项目甲方,无法审核!");
        }

        ProjectPhaseModel projectPhase = ProjectTalkModel.getPhaseAndEvent().getProjectPhase(talkId, ptm.getPhase());

        // 阶段总文件夹
        ProjectFolderModel folderPhase = ptm.fetchPhaseFolder() ;

        auditFolderTemplate(folderPhase, pm.getUserId1(), status);
        paAuditModifyFolderTemplatesStatus(folderPhase, pm.getUserId1(), ptm.getUserId2(), status);

        ProjectEventModel eventModel = new ProjectEventModel();
        eventModel.setRemark(status == ProjectConst.FOLDER_STATUS_PASS_PM ? "甲方审核通过" : "甲方审核不通过");
        eventModel.setEventType(status == ProjectConst.FOLDER_STATUS_PASS_PM ? 15 : 16);//发布为0
        eventModel.setPhaseId(projectPhase.getPhaseId());
        eventModel.setPhaseStatus(0);
        eventModel.setEventSponsor(pm.getUserId1());
        eventModel.setEventAnother(ptm.getUserId2());
        eventModel = ptm.addTalkEvent(context, eventModel);

        if (status == ProjectConst.FOLDER_STATUS_PASS_PM) {
            ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS_PAY);
            pm.getProjectTalkService().modifyTalkPhaseAndStatus(ptm.getPhase(),
                    ProjectConst.TalkPhase_STATUS_PAY, null,ptm.getTalkId());
            if (dict_ptm.isPurchaseProcessing()) {
                if (context.needInvoice()) {
                    getInst().getFlowPay().payPhasePp(context, passWord);

                } else {
                    getInst().getFlowPay().payPhasePp(context, passWord);
                }
            }
        }

        return eventModel;
    }

    //PA甲方审结项递交文档
    public ProjectEventModel paAuditKnitDoc(UserProjectContext context, int talkId, int status) throws UnsupportedEncodingException {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = context.getDict_ProjectTypeModel();

        if (! context.isPhaseIn() ) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "项目不在进行阶段,甲方无法进行审核!");
        }
        if (!context.isPa()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "你不是项目甲方,无法审核!");
        }

        ProjectPhaseModel projectPhase = ProjectTalkModel.getPhaseAndEvent().getProjectPhase(talkId, ptm.getPhase());

        // 阶段总文件夹
        ProjectFolderModel folderPhase = ptm.fetchPhaseFolder() ;

        auditFolderTemplate(folderPhase, pm.getUserId1(), status);
        paAuditModifyFolderTemplatesStatus(folderPhase, pm.getUserId1(), ptm.getUserId2(), status);

        ProjectEventModel eventModel = new ProjectEventModel();
        eventModel.setRemark(status == ProjectConst.FOLDER_STATUS_PASS_PM ? "甲方审核通过" : "甲方审核不通过");
        eventModel.setEventType(status == ProjectConst.FOLDER_STATUS_PASS_PM ? 15 : 16);//发布为0
        eventModel.setPhaseId(projectPhase.getPhaseId());
        eventModel.setPhaseStatus(0);
        eventModel.setEventTime(new Date());
        eventModel.setEventSponsor(pm.getUserId1());
        eventModel.setEventAnother(ptm.getUserId2());
        eventModel = ptm.addTalkEvent(context, eventModel);

        //project_change phase_status = 40
        new ChangeDaoServiceImpl().updateProjectChange(pm.getNewProjectId(),ProjectConstState.PRO_CHANGESTATUS_PAYMENT,null);
        return eventModel;
    }


    //获取文件夹
    public String getFolders(UserProjectContext context, int userId, int talkId, int phase) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        JSONArray jsonArray = new JSONArray();
        String msgBody = "";
        ProjectFolderModel projectFolder = getInst().getProjectFileService()
                .getPhaseFolderByParent(0, ProjectConst.TalkPhase, ptm.getProjectId());
        JSONArray map = new JSONArray();
        if (context.isPa()) {
            msgBody = getPAFolder(projectFolder, jsonArray, map, phase, pm);

        } else if (context.isPb()) {
            msgBody = getPBFolder(projectFolder, jsonArray, map, phase);
            //pm
        } else {
            msgBody = getPMFolder(projectFolder, jsonArray, map, phase, userId);
        }
        return msgBody;
    }

    private String getPAFolder(ProjectFolderModel projectFolder, JSONArray jsonArray,
                               JSONArray map, int phase, ProjectModel projectModel) {

        String msgBody = "";
        if (phase == 0) {
            List<ProjectTemplateModel> templateModelList =  getTemplateListByFolder(projectFolder.getFolderId());
            //获取该文件夹下的文档
            List<ProjectTemplateModel> paProjectTemplateModels =
                    getInst().getProjectFileService().getTemplateListByFolder(projectModel.getFolderId());//获取该文件夹下的文档
            for (ProjectTemplateModel templateModel : paProjectTemplateModels) {
                if (templateModel.isTemplateReq()) {
                    jsonArray.add(templateModel);
                }
                map.add(0);
            }
            int i = 1;
            for (ProjectTemplateModel templateModel : templateModelList) {
                jsonArray.add(templateModel);
                map.add(i++);
            }

            msgBody = "{\"list\":" + jsonArray.toJSONString() + ",\"map\":" + map.toJSONString() + "}";
        } else {
            ProjectFolderModel folder = getPhaseFolderByParent( projectFolder.getFolderId(),
                    600 + phase,  projectModel.getProjectId());
            msgBody = getFolderAndDoc(folder, jsonArray, 0, map);
        }
        return msgBody;
    }

    private String getPBFolder(ProjectFolderModel projectFolder, JSONArray jsonArray, JSONArray map, int phase) {

        String msgBody = "";
        if (phase == 0) {
            List<ProjectTemplateModel> templateModelList =
                    getTemplateListByFolder(projectFolder.getFolderId());//获取该文件夹下的文档
            int i = 0;
            for (ProjectTemplateModel doc : templateModelList) {
                jsonArray.add(doc);
                map.add(i);
                i = i + 1;
            }
            msgBody = "{\"list\":" + jsonArray.toJSONString() + ",\"map\":" + map.toJSONString() + "}";
        } else {
            ProjectFolderModel projectFolder1 = getPhaseFolderByParent(projectFolder.getFolderId(), 600 + phase, projectFolder.getProjectId());
            msgBody = getFolderAndDoc(projectFolder1, jsonArray, 0, map);
        }
        return msgBody;
    }

    private String getPMFolder(ProjectFolderModel projectFolder, JSONArray jsonArray, JSONArray map, int phase, int userId) {

        List<ProjectFolderModel> memberFolder = getProjectPhaseFolders(projectFolder.getProjectId(), userId,
                600 + phase );
        int i = 0;
        for (ProjectFolderModel pf : memberFolder) {
            int n = jsonArray.size();
            jsonArray.add(pf);
            i = i + 1;
            map.add(n);
            getFolderAndDoc(pf, jsonArray, i, map);
        }

        return "{\"list\":" + jsonArray.toJSONString() + ",\"map\":" + map.toJSONString() + "}";

    }

    private String getFolderAndDoc(ProjectFolderModel projectFolder, JSONArray jsonArray, int i, JSONArray map) {
        int n = jsonArray.size();
        List<ProjectTemplateModel> templateModelList = getTemplateListByFolder(projectFolder.getFolderId());
        //获取该文件夹下的文档
        List<ProjectFolderModel> list = getFolderModels(projectFolder.getFolderId());//查询子文件夹
        for (ProjectTemplateModel doc : templateModelList) {
            jsonArray.add(doc);
            i = i + 1;
            map.add(n);
        }
        for (ProjectFolderModel pf : list) {
            jsonArray.add(pf);
            i = i + 1;
            map.add(n);
            getFolderAndDoc(pf, jsonArray, i, map);
        }
        return "{\"list\":" + jsonArray.toJSONString() + ",\"map\":" + map.toJSONString() + "}";
    }


    public  List<ViewProjectTemplateUserModel> getFolderTreeTemplates(ProjectFolderModel projectFolder,
                                                                      List<ViewProjectTemplateUserModel> userModelList,
                                                                      int userId,
                                                                      int author ) {
        //获取该文件夹下的文档
        List<ViewProjectTemplateUserModel> userModels = IProjectFileDAOService.getProjectTemplateUser()
                .selectViewProjectTemplateUserModel(projectFolder.getFolderId(), userId,author);
        userModelList.addAll(userModels);

        //查询子文件夹
        List<ProjectFolderModel> list = getInst().getProjectFileService().getFolderModels(projectFolder.getFolderId());
        for (ProjectFolderModel folderModel : list) {
            //System.err.println(folderModel.getFolderId());
            userModelList = getFolderTreeTemplates(folderModel, userModelList, userId,author);

        }
        return userModelList;
    }


    //协助文档添加
    public List<UserAssistModel> showUserAssistFee(String userIds,UserProjectContext context) {
        //根据ID获取信用等级，算出金额，当时主内好友时，金额为0
        List<Integer> userIdList = new ArrayList<>();
        String arr[] = userIds.split(",");
        Arrays.asList(arr).stream().forEach(x -> userIdList.add(Integer.parseInt(x)));
      /*  for(int i = 0; i < arr.length; i++){
            userIdList.add(Integer.parseInt(arr[i]));
        }*/
        List<UserInfoModel> list = getInst().getUserCenterService().getUsetInfoByIds(userIdList);
        int money = 0;
        List<UserAssistModel> reust = new ArrayList<>();
        for (UserInfoModel us: list) {
            UserAssistModel userAssistModel = new UserAssistModel();
             if(judgeUserInWorkGroup(context,us.getUserId())){
                 money = 0;
                 userAssistModel.setFlag(1);//组员
             } else{
                 money = ProjectCommonUtils.getAssistMoney(us.getCreditGrade());
                 userAssistModel.setFlag(2);//非组员
             }
            userAssistModel.setMoney(money);
             userAssistModel.setUserName(us.getNickName());
            userAssistModel.setUserId(us.getUserId());
            reust.add(userAssistModel);
        }
        return reust;
    }

    //判断用户是否是工作组成员
    private boolean judgeUserInWorkGroup(UserProjectContext context,int userId){
        WorkGroup workGroup = new WorkGroup();
        List<WorkGroupMemberModel> list =  workGroup.getWorkGroupMember(context
                .getProjectTalkModel().getGroupId(),userId);
        return list.size()>0;
    }




    public static class AssistResult extends Ytb_Model {
        String retMsg;


        int templateIdPre ;

        List<Integer> templedIdLst=new ArrayList<>();

        public int getTemplateIdPre() {
            return templateIdPre;
        }

        public void setTemplateIdPre(int templateIdPre) {
            this.templateIdPre = templateIdPre;
        }

        public List<Integer> getTempledIdLst() {
            return templedIdLst;
        }
        public void setTempledIdLst(List<Integer> templedIdLst) {
            this.templedIdLst = templedIdLst;
        }
        public String getRetMsg() {
            return retMsg;
        }
        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }


    }

    //协助文档添加
    public AssistResult addAssistTemplate(UserProjectContext context, int templateId, String toUserIds,
                                          int userId, String remark) throws UnsupportedEncodingException {

        AssistResult result = new AssistResult();
        result.setTemplateIdPre(templateId);

        ProjectTalkModel ptm = context.getProjectTalkModel();


        String[] s = toUserIds.split(",");
        if (s.length > ProjectPayAssist.MaxAssistNumber) {
            result.setRetMsg("一份文档最多找"+ProjectPayAssist.MaxAssistNumber+"人协助");
            return result;
        }

        if (!toUserIds.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            list.add(templateId);
            //改文档协助次数加1
            modifyTemplateAssistNumber(templateId, 1);

            for (String sToUserId : s) {
                Integer toUserId = Integer.parseInt(sToUserId);
                new TemplateAssist().createAssistFolder(context, toUserId, userId, list);

                Integer templateIdAssist =
                        new ProjectTemplateAssistModelServiceImpl().selectOneByIni(Integer.parseInt(sToUserId),
                                templateId).getTemplateId();
                result.getTempledIdLst().add(templateIdAssist);
                new ProjectTemplateUserControl().addProjectTemplateUserAssist(context, userId,
                        toUserId, templateId, templateIdAssist);
            }

            //添加协助的事件
            ptm.addAssistEvent(context, "发布协助", 0,  userId,  0 );
            result.setRetMsg( "邀请协助成功!" );
        }
        return result;
    }

    //共享文档添加
    public void addShareTemplate(UserProjectContext context, int toUserId, int userId, int templateId) throws UnsupportedEncodingException {

        List<Integer> list = new ArrayList<>();
        list.add(templateId);
        new TemplateAssist().createShareFolder(context, toUserId, userId, list);

        //修改共享次数 改文档协助次数加1
        modifyTemplateAssistNumber(templateId, TemplateAssist.DocumentAssist_TYPE_SHARE);

    }

    public void cancelShareTemplate(int templateId, int cancelType) {

        List<Integer> list = new ArrayList<>();
        list.add(templateId);
        new TemplateAssist().unShareFolder(cancelType, list);
        //分享数减一
        reduceTemplateShareNumber(templateId);
    }



    //递交文档协助
    public void sendAssistTemplate(int docListId, int auditor) {

        ProjectTemplateModel projectDocList = getProjectTemplateById(docListId);
        getInst().getProjectFileService().submitTemplate(projectDocList, 501, auditor);
    }

    //确认文档协助,协助打回,协著完成
    public void confirmAssistTemplate(int docListId, int status) {

        ProjectTemplateModel projectDocList = getProjectTemplateById(docListId);
        //getInst().getProjectFileService().submitDoc(projectDocList,status,projectDocList.getAuditor());

    }

    //selectDocument
    public ProjectTemplateModel selectDocument(int templateId) throws UnsupportedEncodingException {
        ProjectFileServiceImpl pf = new ProjectFileServiceImpl();
        ProjectTemplateModel t = pf.getProjectTemplateById(templateId);

        ProjectDocumentModel pd = pf.getProjectDocument(t.getDocumentId());
        t.setDocumentModel(pd);
        return t;
    }

    //查看协助文档
    public ProjectTemplateAssistModel selectProjectTemplateAssistModel(int templateIdAssits) throws UnsupportedEncodingException {

        IProjectTemplateAssistModelService ptms = new ProjectTemplateAssistModelServiceImpl();
        ProjectTemplateAssistModel projectTemplateAssistModel = ptms.selectOne(templateIdAssits);

        ProjectFileServiceImpl pf = new ProjectFileServiceImpl();
        ProjectDocumentModel pd = pf.getProjectDocument(projectTemplateAssistModel.getDocumentId());
        projectTemplateAssistModel.setDocumentModel(pd);
        return projectTemplateAssistModel;
    }

    //历史版本
    public ProjectTemplateModel selectDocument_v(int docListId) throws UnsupportedEncodingException {

        ProjectTemplateModel templateModel = DocumentToolService.getDocumentToolService().getProjectDocById_v(docListId);
        ProjectDocumentModel documentModel =
                DocumentToolService.getDocumentToolService().getProjectDocument_v(templateModel.getDocumentId());
        templateModel.setDocumentModel(documentModel);

        return templateModel;
    }



    //生成采购加工清单
    public CustomTaskResult copyTemplatePp(UserProjectContext context,int projectTypeId,int doctype) throws
            UnsupportedEncodingException {
        CustomTaskResult customTaskResult = new CustomTaskResult();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();

        //找到加工单（根据projectTypeId）---------第二步
        TaskFolder taskFolder = context.getViewProjectFolderModel().getpBFolder().getTaskFolder();
        int folderId = taskFolder.createFolder(context, ptm.getPhase()); //生成自定义任务文件夹

        Dict_TemplateModel dictTemplateModel = context.findDictTemplateModel(projectTypeId,
                doctype); // 找到加工的物料清单原始文档

        ProjectTemplateModel newModel = new CopyTemplateModelPpTask().copyTemplate(
                context, dictTemplateModel, folderId, context.getUserId(), "自定义");
        //把第一步找到的数据弄到2上 ----------第三步
        //新增custom_task记录
        int id = getInst().getProjectFlowService().addCustomTask(
                context, CustomTaskModel.TASK_TYPE_Processing, folderId, newModel.getTemplateId());

        String names = (doctype == 101) ? "采购清单" : "加工清单";

        customTaskResult.setTemplateId(newModel.getTemplateId());
        customTaskResult.setCusTomTaskId(id);
        customTaskResult.setProjectName(pm.getProjectName()+names);
        return customTaskResult;


        /*IProjectTalkModel projectTalkModel = context.getProjectTalkModel();
        Dict_ProjectTypeModel dict_ptm = getInst().getProjectTypeService().getProjectType(projectTypeId);

        Dict_TemplateModel dict_tm;
        String taskName = "";
        int mode = 0;
        int memberDutyId = 0;
        if (dict_ptm.isPurchase()) {
            taskName = "采购任务";
            mode = 21;
            dict_tm = getInst().getTemplateRepositoryService().getRequirements(projectTypeId, ProjectConst.Template_TYPE_req_purchase);//采购模板文档
        } else if (dict_ptm.isProcessing()) {
            taskName = "加工任务";
            mode = 22;
            dict_tm = getInst().getTemplateRepositoryService().getRequirements(projectTypeId, ProjectConst.Template_TYPE_req_process);//加工模板文档
        }
        List<ProjectFolderModel> memberFolder = getFolderByUserAndPhase(projectTalkModel.getProjectId(), userId, projectTalkModel.getPhase(), 0);
        WorkGroupMemberModel workGroupMember = IProjectTalkModel.getWorkGroup().getWorkGroupMember(projectTalkModel.getProjectId(), userId, projectTalkModel.getWorkplanId());//获取工作组成员
        List<ProjectMemberDutyModel> projectMemberDutyList = IProjectTalkModel.getWorkGroup().getProjectMemberDuty(workGroupMember.getMemberId());
        for (ProjectMemberDutyModel pMD : projectMemberDutyList) {
            List<ProjectMemberTask> projectMemberTaskList = IProjectTalkModel.getWorkGroup().getProjectMemberTasks(pMD.getMemberDutyId());
            for (ProjectMemberTask pMT : projectMemberTaskList) {
                if ("有采购任务".equals(pMT.getTaskName()) && mode == 21) {
                    memberDutyId = pMT.getMemberDutyId();
                } else if ("有加工任务".equals(pMT.getTaskName()) && mode == 22) {
                    memberDutyId = pMT.getMemberDutyId();
                }
            }
        }

        ProjectTemplateModel templateModel = new CopyTemplateModelPpTask().copyTemplate(context,
                memberFolder.get(0).getFolderId(), userId, taskName);
                map.put("templateId", templateModel.getTemplateId());
       // int taskId = IProjectTalkModel.getWorkGroup().addProjectMemberTask(0, taskName, "",
               // mode, templateModel.getTemplateId(), memberDutyId);
           //map.put("docListId", docListId);*/
    }


    //删除采购清单
    public void delPurchaseTemplate(int taskId) {
        ProjectMemberTask pmTask = ProjectTalkModel.getWorkGroup().getProjectMemberTaskById(taskId);
        delProjectTemplateById(pmTask.getTemplateId());
        ProjectTalkModel.getWorkGroup().delProjectMemberTask(taskId);
    }

    //拷贝物料清单
    public void copyTemplatePpMList(UserProjectContext context, int projectTypeId, int talkId, int userId) throws UnsupportedEncodingException {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        //getInst().getTemplateRepositoryService().getRequirements(projectTypeId, 500);//采购模板文档
        List<ProjectFolderModel> memberFolder = getProjectPhaseFolders(ptm.getProjectId(), userId, ptm.getPhase() );
        new CopyTemplateModelPpTask().copyTemplate (context, memberFolder.get(0).getFolderId(), userId, "");
    }

//    //修改用户文档状态
//    public void modifyProjectTemplateUser(UserProjectContext context, int userId, int templateId, int status) {
//        ProjectModel pm = context.getProjectModel();
//        IProjectTalkModel ptm = context.getProjectTalkModel();
//
//        if (userId != pm.getUserId1() && userId != ptm.getUserId2()) {
//            ProjectFileDAOService.getProjectTemplateUser().modify(ptm.getUserId2(), userId, templateId, status);
//        }
//        ProjectFileDAOService.getProjectTemplateUser().modify(pm.getUserId1(), ptm.getUserId2(), templateId, status);
//    }

    //协助接收方修改状态
    public void receiverModifyTemplateUserStatuslAssist(int id) {
        IProjectFileDAOService.getProjectTemplateUser().receiverModifyTemplateUserStatus(id,
                ProjectConst.TASK_STATUS_NotSubmitted,
                ProjectConst.ACTIVE_STATUS_ToModify,
                0);
    }

    //协助发起方修改状态
    public void sponsorModifyTemplateUserStatusAssist(UserProjectContext context,
                                                      int templateIdAssist,
                                                      int id,
                                                      int toUserId){
        ProjectTalkModel ptm = context.getProjectTalkModel();

        IProjectTemplateAssistModelService assistModelService = new ProjectTemplateAssistModelServiceImpl();
        ProjectTemplateAssistModel projectTemplateAssistModel = assistModelService.selectOne(templateIdAssist);

        int userId = projectTemplateAssistModel.getAuditor();
        ProjectTemplateUserModel templateUserModelSponsor = new ProjectTemplateUserModel();//协助发起方
        templateUserModelSponsor.setPkId(id);
        templateUserModelSponsor.setUserId(toUserId);//
        templateUserModelSponsor.setTemplateIdAssist(templateIdAssist);
        templateUserModelSponsor.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
        templateUserModelSponsor.setDisplayStatusLight();
        templateUserModelSponsor.setStatus(ProjectConst.TASK_STATUS_Submitted);
        IProjectFileDAOService.getProjectTemplateUser().receiverSubmitTemplateUserStatusAssist(templateUserModelSponsor);

        //协助接收方
        IProjectFileDAOService.getProjectTemplateUser().receiverModifyTemplateUserStatus(id,
                ProjectConst.TASK_STATUS_Submitted,
                ProjectConst.ACTIVE_STATUS_ToModify,
                0);

        //添加事件记录
        ptm.addAssistEvent(context, "递交协助文档",
                ProjectConst.TASK_STATUS_Submitted,//已递交
                context.getUserId(),//事件发起人
                toUserId); //事件所属人

    }

    //协助发起方修改状态
    public void sponsorSubmitTemplateUserStatusAssist(int id,int auditStatus) {

        if(auditStatus == ProjectConst.TASK_STATUS_Passing){//通过

            IProjectFileDAOService.getProjectTemplateUser().receiverModifyTemplateUserStatus(id,ProjectConst.TASK_STATUS_Passing,
                    ProjectConst.ACTIVE_STATUS_ToAudit,0);

        }else{//未通过
            IProjectFileDAOService.getProjectTemplateUser().receiverModifyTemplateUserStatus(id,ProjectConst.TASK_STATUS_NotPassing,
                    ProjectConst.ACTIVE_STATUS_ToAudit,0);
        }
    }


    //协助发起方修改状态
    public void sponsorSubmitTemplateUserStatusAssist(
            UserProjectContext context,
            int templateIdAssist,
            int id,
            int auditStatus
            ) {

        ProjectTalkModel ptm = context.getProjectTalkModel();

        IProjectTemplateAssistModelService ptms = new ProjectTemplateAssistModelServiceImpl();
        ProjectTemplateAssistModel projectTemplateAssistModel = ptms.selectOne(templateIdAssist);

        int userId = projectTemplateAssistModel.getAuditor();//协助发起人
        int author = projectTemplateAssistModel.getAuthor();//协助接收人

        ProjectTemplateUserModel userModelB = IProjectFileDAOService.getProjectTemplateUser()
                .getProTemplateUserByUserIdAndTemplateIdAssist
                (author,templateIdAssist);//协助接收人的文档状态

        if(auditStatus == ProjectConst.TASK_STATUS_Passing){//通过

            //协助发起方
            ProjectTemplateUserModel userModelA = new ProjectTemplateUserModel();
            userModelA.setUserId(userId);
            userModelA.setTemplateIdAssist(templateIdAssist);
            userModelA.setActiveStatus(ProjectConst.ACTIVE_STATUS_GoRead);
            userModelA.setDisplayStatusLight();
            userModelA.setStatus(ProjectConst.TASK_STATUS_Passing);
            IProjectFileDAOService.getProjectTemplateUser().receiverSubmitTemplateUserStatusAssist(userModelA);

            //找到协助接收方文档

            //协助接收方
            IProjectFileDAOService.getProjectTemplateUser().receiverModifyTemplateUserStatus
                    (userModelB.getPkId(),
                    ProjectConst.TASK_STATUS_Passing,
                    ProjectConst.ACTIVE_STATUS_ToWrite,
                            0);

            ptm.addAssistEvent(context, "审核协助递交文档通过", ProjectConst.TASK_STATUS_Passing_PA, context.getUserId(),
                    projectTemplateAssistModel.getAuthor());

        } else {//未通过

            ProjectTemplateUserModel userModelA = new ProjectTemplateUserModel();//协助发起方
            userModelA.setUserId(userId);
            userModelA.setTemplateIdAssist(templateIdAssist);
            userModelA.setActiveStatus(ProjectConst.ACTIVE_STATUS_ToAudit);
            userModelA.setDisplayStatusGray();
            userModelA.setStatus(ProjectConst.TASK_STATUS_NotPassing);
            IProjectFileDAOService.getProjectTemplateUser().receiverSubmitTemplateUserStatusAssist(userModelA);

            //协助接收方
            IProjectFileDAOService.getProjectTemplateUser().receiverModifyTemplateUserStatus(userModelB.getPkId(),
                    ProjectConst.TASK_STATUS_NotSubmitted,
                    ProjectConst.ACTIVE_STATUS_ToWrite,
                    0);


            ptm.addAssistEvent(context,"审核协助递交文档不通过", ProjectConst.TASK_STATUS_NotPassing_PA,  context.getUserId(),
                    projectTemplateAssistModel.getAuthor());
        }
    }




}
