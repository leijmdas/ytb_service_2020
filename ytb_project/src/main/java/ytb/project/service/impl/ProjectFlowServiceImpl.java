package ytb.project.service.impl;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.*;
import ytb.project.daoservice.CostModelServiceImpl;
import ytb.project.model.*;
import ytb.project.common.ProjectConst;

import ytb.project.model.tagtable.CostModel;
import ytb.project.model.tagtable.ProjectMemberDutyModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.project.stop.StopApplyResult;
import ytb.project.service.project.stop.StopFlow;
import ytb.project.service.project.stop.impl.StopType;
import ytb.project.view.daoservice.TaskDAOService;
import ytb.project.view.model.ProjectTaskViewModel.ViewCustomTaskModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;
import ytb.project.view.service.impl.ViewTaskService;
import ytb.manager.template.model.Dict_ProjectTypeModel;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public final class ProjectFlowServiceImpl extends TaskDAOService implements ProjectFlowService {


    //甲方提出项目终止
    public String projectTermPA(UserProjectContext context,int reasonTermination,int status,int projectId){
        ProjectModel pm =  context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        StopFlow stopProject = new StopFlow();
        //CostModel costModel =getProjectFeeByTalkId(ptm.getTalkId());
        //stopProject.projectTermPA(reasonTermination,status,pm,costModel);
        return stopProject.toString();
    }

    //乙方提出项目终止
    public String projectTermPB(UserProjectContext context,int status,int projectId){
        ProjectModel pm =  context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        List<CostModel> lst =new CostModelServiceImpl().getProjectFeeByTalk(ptm.getProjectId(),ptm.getUserId2(),
                ptm.getTalkId());
        StopFlow stopProject = new StopFlow();
        //stopProject.projectTermPB(context );
        return stopProject.toString();
    }


    //申请项目终止
    public StopApplyResult applyStopProject(UserProjectContext context, int userId, StopType stopType) throws            UnsupportedEncodingException {

        return new StopFlow().applyStopProject(context,userId,stopType);

    }

    //确认项目终止
    public String confirmStopProject(UserProjectContext context, int userId, int status) {
        return new StopFlow().confirmStopProject(context, userId, status);

    }

    //项目终止页面
    public String stopProject(UserProjectContext context, int userId) {

        return new StopFlow().stopProject(context, userId);

    }

    //乙方同意/拒绝终止
    public void confirmStopProjectPB(UserProjectContext context,int status, int userId){
          new StopFlow().confirmStopProjectPB(context, status,userId);


    }

    //获取项目过程记录
    public List<Map<String, Object>> getProjectEventList(UserProjectContext context, int talkId, int stage) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        Map map = new HashMap();
        map.put("userId", ptm.getUserId2());
        map.put("projectId", ptm.getProjectId());
        map.put("talkId", talkId);
        List<Map<String, Object>> list = ProjectTalkModel.getPhaseAndEvent().getProjectEvent(map);
        if (stage == 0) {
            List<Map<String, Object>> listBeforeTalk = ProjectTalkModel.getPhaseAndEvent().getBeforeTalkEvent(map);     //json = getInst().getFlowFolder().addTemplate(json, listBeforeTalk);
            list.addAll(listBeforeTalk);
        } else {
            map.put("phase",  stage);
        }
        return list;

    }

    public void modifyShipmentNumber(int taskId,String number){

        ProjectMemberTask projectMemberTask = new ProjectMemberTask();
        projectMemberTask.setMemberDutyId(taskId);
        projectMemberTask.setTaskRemark(number);
        ProjectTalkModel.getWorkGroup().modifyPhaseTask(projectMemberTask);
    }
    //点击采购加工子分类
    public CustomTaskResult chooseTask(UserProjectContext context) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        CustomTaskResult customTaskResult = new CustomTaskResult();

        List<ViewCustomTaskModel> list = getInst().getProjectFlowService().getCustomTaskList(context.getUserId(),pm.getProjectId());

        if(list.size()>0){
            return customTaskResult;
        }

        //找到物料总清单，并获取里面的内容---------第一步
        ProjectTemplateModel projectTemplateModel = ptm.findPbTemplate(ProjectConst.Template_TYPE_MaterialList);

        customTaskResult.setTemplateId(projectTemplateModel.getTemplateId());
        customTaskResult.setProjectName(pm.getProjectName()+"电子元器件-采购清单");
       return customTaskResult;

    }




    //去发布
    public String goReleasePro(UserProjectContext context, int projectTypeId, int talkId) {

        ProjectModel pm = context.getProjectModel();
        pm.setProjectTypeId(projectTypeId);
        Dict_ProjectTypeModel ptm = context.getDict_ProjectTypeModel();

        String projectName = pm.getProjectName() + "-" + ptm.getTitle();
        if (ptm.isProcessing()) {
            projectName = pm.getProjectName() + "-" + ptm.getTitle() + "加工清单";
        } else if (ptm.isPurchase()) {
            projectName = pm.getProjectName() + "-" + ptm.getTitle() + "采购清单";
        }
        Map map = new HashMap();
        map.put("title", ptm.getTitle());
        map.put("projectName", projectName);

        return "{\"mapList\":" + JSON.toJSONString(map) + " }";

    }


    //获取添加采购任务
    public Map getTask(UserProjectContext context,int userId,int talkId){
        Map map = new HashMap();

        ProjectTalkModel ptm = context.getProjectTalkModel();

        WorkGroupMemberModel workGroupMember = ProjectTalkModel.getWorkGroup().getWorkGroupMember( ptm.getProjectId()
                ,userId,ptm.getWorkplanId());//获取工作组成员
        List<ProjectMemberDutyModel>  projectMemberDutyList = ProjectTalkModel.getWorkGroup().getProjectMemberDuty(workGroupMember.getMemberId());
        for (ProjectMemberDutyModel pMD : projectMemberDutyList) {
            List<ProjectMemberTask> projectMemberTaskList = ProjectTalkModel.getWorkGroup().getProjectMemberTasks(pMD.getMemberDutyId());
            for (ProjectMemberTask pMT : projectMemberTaskList) {
                if("有采购任务".equals(pMT.getTaskName())){
                    if(map.get("采购")==null){
                        map.put("采购",90);
                    }
                }
                if("有加工任务".equals(pMT.getTaskName())){
                    if(map.get("加工")==null){
                        map.put("加工",94);
                    }
                }
            }
        }
        return map;
    }


    //点击查看任务
    public List<Map<String, Object>> clickViewTask(UserProjectContext context,int talkId){

        ProjectTalkModel ptm = context.getProjectTalkModel();
        List<Map<String, Object>> pmLst = ProjectTalkModel.getWorkGroup().getWorkGroupMemberInfo
                (ptm.getGroupId());
        for(Map map:pmLst){
            map.put("phase",ptm.getPhase());
            map.put("phaseStatus",ptm.getPhaseStatus());
        }
        return pmLst;
    }


    //添加会议任务
    public void addMeetingTask(UserProjectContext context,int talkId,int userId,String participant,String issue ,String startTime, String stopTime,int docListId){

        ProjectTalkModel projectTalkModel = context.getProjectTalkModel();
        int phase = projectTalkModel.getPhase()>ProjectConst.Phase_START?projectTalkModel.getPhase():ProjectConst.TalkPhase;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MeetingNoticeModel meetingNotice = new MeetingNoticeModel();
            meetingNotice.setPhase(phase);
            meetingNotice.setProjectId(projectTalkModel.getProjectId());
            meetingNotice.setSponsor(userId);
            meetingNotice.setRemark("");
            meetingNotice.setStartTime(df.parse(startTime));
            meetingNotice.setStopTime(df.parse(stopTime));
            meetingNotice.setIssue(issue);
            meetingNotice.setParticipant(participant);
            meetingNotice.setDocumentId(docListId);
            addMeetingNotice(meetingNotice);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //添加自定义任务
    public void addCustomTask(UserProjectContext context,int talkId ,String receiver,String remark,int docListId,int userId){
        ProjectTalkModel projectTalkModel =context.getProjectTalkModel();
        int phase = projectTalkModel.getPhase()>ProjectConst.Phase_START?projectTalkModel.getPhase():ProjectConst.TalkPhase;
        CustomTaskModel customTask = new CustomTaskModel();
        customTask.setCreateTime(new Date());
        customTask.setPhase(phase);
        customTask.setProjectId(projectTalkModel.getProjectId());
        customTask.setReceiver(receiver);
        customTask.setRemark(remark);
        customTask.setTemplateId(docListId);
        customTask.setCustomType(1);
        customTask.setUserId(userId);
        addCustomTask(customTask);
    }

    //添加物流单号
    public void addShipmentNumber(int talkId,String remark,String number,String goodsName,String documentName,int userId,int toUserId){
        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);

        try {
            ShipmentNumberMapper shipmentNumberMapper = session.getMapper(ShipmentNumberMapper.class);
            ProjectTalkModel projectTalkModel = ProjectFlowService.getTalkService().getProjectTalkById(talkId);
            int phase = projectTalkModel.getPhase()>ProjectConst.Phase_START?projectTalkModel.getPhase():ProjectConst.TalkPhase;
            ShipmentNumberModel shipmentNumber = new ShipmentNumberModel();
            shipmentNumber.setProjectId(projectTalkModel.getProjectId());
            shipmentNumber.setPhase(phase);
            shipmentNumber.setRemark(remark);
            shipmentNumber.setNumber(number);
            shipmentNumber.setGoodsName(goodsName);
            shipmentNumber.setDocumentName(documentName);
            shipmentNumber.setUserId(userId);
            shipmentNumber.setToUserId(toUserId);
            shipmentNumberMapper.addShipmnetNumber(shipmentNumber);
            session.commit();
        }finally {
            session.close();
        }
    }

    public ViewTaskModel selectTask(UserProjectContext context,int talkId,int userId){

        ProjectTalkModel projectTalkModel = context.getProjectTalkModel();//getInst().getReleaseView().getProjectTalkById(talkId);
        ProjectModel projectModel = context.getProjectModel();// getInst().getReleaseView().getProjectById(projectTalkModel.getProjectId());


        int phase = projectTalkModel.getPhase()>=ProjectConst.Phase_START?projectTalkModel.getPhase():ProjectConst.TalkPhase;
        return new ViewTaskService().selectViewTaskModel(projectModel.getProjectId(),phase,userId);
    }


}

