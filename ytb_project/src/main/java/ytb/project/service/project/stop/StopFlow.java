package ytb.project.service.project.stop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.model.*;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.talk.ProjectEvent;
import ytb.project.service.project.change.ChangeFlow;
import ytb.project.service.project.stop.impl.StopService;
import ytb.project.service.project.stop.impl.StopType;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//终止流程接口
public final class StopFlow extends StopService implements IStopService {



    public void confirmStopProjectPB(UserProjectContext context,int status, int userId){

        ProjectTalkModel ptm =context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        int phase = ptm.getPhase();
        //ptm.setPhaseStatus(802);
        ptm.setRemark(String.valueOf(status));
        //  int pPhaseId = IProjectTalkModel.getPhaseAndEvent().addProjectPhase(ptm.getProjectId(), 89, phase,
        //   ptm.getTalkId(),800);//800
        pm.getProjectTalkService().modifyTalkRemark(ptm);
        if (status == 0) {
            ptm.addTalkEvent(context, "乙方同意项目终止请求", 30, userId, pm.getUserId1());
        } else {
            ptm.addTalkEvent(context, "乙方不同意项目终止请求", 31, userId, pm.getUserId1());
        }
    }
    //申请项目终止
    public StopApplyResult applyStopProject(UserProjectContext context, int userId, StopType stopType) throws UnsupportedEncodingException {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProjectModel pm = context.getProjectModel();
        StopApplyResult applyResult = new StopApplyResult(context);

        checkCanStop(context, stopType);
        //创建终止文件夹 判断已经存在不创建！
        createStopFolderAndTemplate(context, stopType,applyResult);
        //计算告知通知书: 先实现6阶段的!第一次算
        computeStopPTable(context, stopType);

        //添加事件
        int eventUserId = (userId == pm.getUserId1()) ? userId : ptm.getUserId2();
        int eventAnother = (userId == pm.getUserId1()) ? ptm.getUserId2() : userId;
        ptm.addTalkEvent(context, "申请项目终止请求", 15, eventUserId, eventAnother);

        //添加一条变更记录
        ProjectChangeModel changeModel = ProjectChangeModel.newStopProjectChangeModel(context);
        changeModel.setPaPb(stopType.getPaPbUser());
        ProjectStopModel projectStopModel = new ProjectStopModel();
        projectStopModel.setPartyBy(stopType.getPaPbUser());
        projectStopModel.setUserId(userId);
        projectStopModel.setNowPhase(stopType.getPhaseNow());

        JSONObject jsonObj = (JSONObject) JSONObject.toJSON(projectStopModel);
        changeModel.setItems(jsonObj.toJSONString());
        new ChangeFlow().addChange(changeModel);

        //status
        getInst().getReleaseView().getProjectDAOService().updateProjectInfoInChangeStage(pm
                .getProjectId(), 0, ptm.getTalkId(), ProjectConstState.CHNAGE_TYPE_STOP);

        return applyResult;

    }

    //项目终止页面
    public String stopProject( UserProjectContext context ,int userId ){

        ProjectTalkModel ptm=context.getProjectTalkModel();
        ProjectModel pm=context.getProjectModel();

        String msgBody = "";
        JSONObject jsonObject = new JSONObject();
        ProjectTalkModel projectTalkModel = ProjectFlowService.getTalkService().getProjectTalkById(ptm.getTalkId());
        ProjectPhaseModel projectPhase = ProjectTalkModel.getPhaseAndEvent().getProjectPhase(ptm.getTalkId(),
                projectTalkModel.getPhase());
        if(projectTalkModel.getUserId2()!=userId) {
        /*if(projectTalkModel.getProposer()!=userId) {*/
            JSONArray jsonArray = new JSONArray();
            List<ProjectTemplateModel> templateModelList =
                    getInst().getProjectFileService().getTemplateListByFolder(projectPhase.getFolderId());
            ProjectEvent projectEvent = new ProjectEvent();
            List<Map<String, Object>> projectPEvent = null;//       projectEvent.selectProjectEventByUserId
            // (projectModel.getProjectId(), //projectTalkModel.getPhase(), projectTalkModel.getUserId2(), 800);
            JSONArray jsonArray1 = new JSONArray();
            JSONArray jsonArray2 = new JSONArray();
            if (projectPEvent != null && projectPEvent.size() > 0) {
                jsonArray1.add(projectPEvent.get(0));
                jsonObject.fluentPut("submitEvent", jsonArray1);
                jsonObject.fluentPut("docList", jsonArray);
                if (projectPEvent.size() > 1) {
                    jsonArray2.add(projectPEvent.get(1));
                    jsonObject.fluentPut("checkEvent", jsonArray2);
                }
                jsonArray.add(jsonObject);
            }
            Map map = new HashMap();
            JSONArray json = new JSONArray();
            map.put("status",1);
            map.put("state",0);
            if(projectPEvent!=null){
                map.put("status",1);
                json.add(map);
                msgBody ="{\"list\":"+JSON.toJSONString(templateModelList)+",\"mapList\":"+json.toString()+",\"eventList\":"+jsonArray.toString()+"}";//不显示
            }else{
                map.put("status",0);
                json.add(map);
                msgBody ="{\"list\":"+ JSON.toJSONString(templateModelList)+",\"mapList\":"+json.toString()+",\"eventList\":"+jsonArray.toString()+"}";//显示
            }
        }else{
            List<ProjectTemplateModel> templateModelList = getInst().getProjectFileService().getTemplateListByFolder(projectPhase.getFolderId());
            int left =0;
            int right =1;
            int state =0;
            int status =0;
            JSONArray jsonArray = new JSONArray();
            if(projectTalkModel.getPhaseStatus()==801 ){
                left =0;
            }else{
                left =1;
            }
            if(projectTalkModel.getPhaseStatus()==802){
                right =0;
            }
            if(projectTalkModel.getPhaseStatus()==803){
                state =1;
                if("1".equals(projectTalkModel.getRemark())){
                    status =2;//显示同意提示
                }else if("2".equals(projectTalkModel.getRemark())){
                    status =3;//显示不同意提示
                }
                List<Map<String, Object>> projectPEvent3 =
                        ProjectTalkModel.getPhaseAndEvent().selectProjectEventByP(pm.getProjectId(),
                                projectTalkModel.getPhase()-10);
                List<Map<String, Object>> projectPEvent4 = ProjectTalkModel.getPhaseAndEvent().selectProjectEventByP(pm.getProjectId(),projectTalkModel.getPhase());
                jsonArray.add(projectPEvent3.get(0));
                for(Map map:projectPEvent4){
                    jsonArray.add(map);
                }
            }
            if(projectTalkModel.getPhaseStatus()==802){
                List<Map<String, Object>> projectPEvent = ProjectTalkModel.getPhaseAndEvent().selectProjectEventByP(pm.getProjectId(),projectTalkModel.getPhase());
                jsonArray.add(projectPEvent.get(0));
                status =1;//显示第一个提示
            }
            JSONArray json = new JSONArray();
            Map map =new HashMap();
            map.put("left",left);
            map.put("right",right);
            map.put("state",state);
            json.add(map);
            return   "{\"list\":"+JSON.toJSONString(templateModelList)+",\"mapList\":"+json.toString()+",\"eventList\":"+jsonArray.toString()+",\"status\":"+status+"}";

        }
        return msgBody;
    }


    public String confirmStopProject(UserProjectContext context,int userId,  int status ){
        ProjectModel pm=context.getProjectModel();
        ProjectTalkModel ptm=context.getProjectTalkModel();
        String eventType ="";
        String msgBody ="";
        ProjectTalkModel projectTalkModel = ProjectFlowService.getTalkService().getProjectTalkById(ptm.getTalkId());

        int phase = projectTalkModel.getPhase();
        ProjectPhaseModel projectPhase = ProjectTalkModel.getPhaseAndEvent().getProjectPhase(ptm.getTalkId(),phase);
        List<Map<String, Object>> projectPEvent = ProjectTalkModel.getPhaseAndEvent().selectProjectEventByP(pm.getProjectId(),phase);
        for(Map map :projectPEvent){
            eventType =(String) map.get("eventType");
        }
        if(status==0) {
            //projectTalkModel.setPhase(phase + 10); 700
            //projectTalkModel.setStatus(1);
            /*ProjectFlowService.getProjectTalkService().modifyTalkPhaseAndStatus(projectTalkModel);*/
            ProjectFlowService.getTalkService().modifyTalkPhaseAndStatus(phase,
                    projectTalkModel.getPhaseStatus(),null,ptm.getTalkId());
            // int pPhaseId =IProjectTalkModel.getPhaseAndEvent().addProjectPhase(projectTalkModel.getProjectId(),
            //   projectPhase.getFolderId(), phase + 10, projectTalkModel.getTalkId(),0);
            if (userId == pm.getUserId1()) {
                ptm.addTalkEvent(context,"项目终止",  Integer.parseInt  (eventType), userId, projectTalkModel.getUserId2());
            } else {
                ptm.addTalkEvent(context,"项目终止", Integer.parseInt (eventType), projectTalkModel.getUserId2(), userId );
            }
        }else if(status ==1){
            if(projectTalkModel.getPhase()>620){
                projectTalkModel.setPhase(phase - 20);
                phase =phase - 20;
            }else{
                projectTalkModel.setPhase(phase - 10);
                phase =phase - 10;
            }
            //projectTalkModel.setStatus(0);
            /*ProjectFlowService.getProjectTalkService().modifyTalkPhaseAndStatus(projectTalkModel);*/
            ProjectFlowService.getTalkService().modifyTalkPhaseAndStatus(phase,
                    projectTalkModel.getPhaseStatus(),null,ptm.getTalkId());
            if (userId == pm.getUserId1()) {
                ptm.addTalkEvent(context,"取消项目终止", Integer.parseInt
                        (eventType), userId, projectTalkModel.getUserId2());
            } else {
                ptm.addTalkEvent(context,"取消项目终止", Integer.parseInt
                        (eventType), projectTalkModel.getUserId2(), userId);
            }
        }
        return msgBody;
    }


//    //甲方提出项目终止
//    public String projectTermPA(UserProjectContext context, int reasonTermination, int status, CostModel projectFee) {
//        ProjectModel projectModel = context.getProjectModel();
//        ProjectTalkModel projectTalkModel = ProjectFlowService.getTalkService().getProjectTalkById(/*projectModel.getTalkId()*/0);
//        // int pPhaseId = IProjectTalkModel.getPhaseAndEvent().addProjectPhase(projectTalkModel.getProjectId(),0,800,
//        //   projectTalkModel.getTalkId(),0);
//
//        BigDecimal payFee = new BigDecimal("0");
//        if(status==0){//乙方同意
//            if(reasonTermination==0 ){//甲方取消项目
//                if(projectTalkModel.getPhase()==606){
//
//                }else {
//                    payFee = termSchema(context, reasonTermination ,projectFee,projectModel.getUserId1()                  );
//                }
//            }else if(reasonTermination==1){//乙方交付不合格
//                if(projectTalkModel.getPhase()==606){
//                    minusQ(context,                            projectTalkModel.getGroupId(), 1.0,
//                            projectModel.getUserId1());
//                    payFee = projectFee.getCostPhase5().add(projectFee.getCostPhase4());//乙退回四五阶段的钱
//                    projectTalkModel.addTalkEvent(context,getRemark(0,payFee),13,
//                            projectModel.getUserId1(),projectTalkModel.getUserId2());
//
//                }else if(projectTalkModel.getPhase()==605){//乙退回第四阶段的钱
//                    payFee = projectFee.getCostPhase4();
//                    minusQ(context,projectTalkModel.getGroupId(),0.8,projectModel.getUserId1());
//                    projectTalkModel.addTalkEvent(context,getRemark(0,payFee),13,
//                            projectModel.getUserId1(),projectTalkModel.getUserId2() );
//                }else {
//                    payFee = termSchema( context, reasonTermination, projectFee,projectModel.getUserId1());
//                }
//            }else if(reasonTermination ==2){//乙方延期率>500%
//              //  payFee = termSchema2(context,projectFee,projectModel.getUserId1());
//            }
//        }
//        if(status==1){//乙方不同意
//            if(reasonTermination==0 ){//甲方取消项目
//                if(projectTalkModel.getPhase()==606){
//                }else {
//                    payFee = termSchema(context, reasonTermination,projectFee,projectModel.getUserId1() );
//                }
//            }else if(reasonTermination==1){//乙方交付不合格
//                if(projectTalkModel.getPhase()==606){
//                    // minusQ(context,projectTalkModel.getGroupId(),  1.0,projectModel.getUserId1());
//                    payFee = projectFee.getCostPhase5().add(projectFee.getCostPhase4());//乙退回四五阶段的钱
//                    projectTalkModel.addTalkEvent(context,getRemark(0,payFee),13,
//                            projectModel.getUserId1(),projectTalkModel.getUserId2() );
//
//                }else if(projectTalkModel.getPhase()==605){//乙退回第四阶段的钱
//                    payFee = projectFee.getCostPhase4();
//                    minusQ(context,projectTalkModel.getGroupId(), 0.8,projectModel.getUserId1());
//                    projectTalkModel.addTalkEvent(context,getRemark(0,payFee),13,
//                            projectModel.getUserId1(),projectTalkModel.getUserId2() );
//                }else {
//                    payFee = termSchema(context, reasonTermination ,projectFee,projectModel.getUserId1() );
//                }
//
//            }else if(reasonTermination ==2){//乙方延期率>500%
//               // payFee = termSchema2(context, projectFee,projectModel.getUserId1() );
//            }
//        }
//        return "";
//    }

//    //乙方提出项目终止
//    public String projectTermPB(UserProjectContext context,int status,  CostModel projectFee){
//
//        ProjectModel pm = context.getProjectModel();
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//
//        //    int pPhaseId = IProjectTalkModel.getPhaseAndEvent().addProjectPhase(projectTalkModel.getProjectId(),0
//        //,800,projectTalkModel.getTalkId(),0);
//        BigDecimal payFee = new BigDecimal("0").setScale(2,BigDecimal.ROUND_HALF_UP);
//        if(status==0){//甲方同意
//           // payFee = termSchema2(context,  projectFee,pm.getUserId1() );
//        }else if(status==1){//甲方不同意
//           // payFee = termSchema2(context, projectFee,pm.getUserId1() );
//        }
//        return "";
//    }
//

    //终止方案
//    private BigDecimal termSchema(UserProjectContext context, int reasonTermination,CostModel projectFee, int userId1 ) {
//        ProjectModel pm = context.getProjectModel();
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//
//        BigDecimal totalFee = new BigDecimal("0") ;//剩余总费用
//        BigDecimal payFee = new BigDecimal("0");//甲方,乙方需要支付费用
//
//        Double ret =0.0;
//        if (ptm.getPhase() == 601) {
//            if(reasonTermination == 0) {
//                totalFee = projectFee.getCostSum().subtract(projectFee.getCostPhase1());//减去第一阶段的费用
//                ret = 0.05;
//                payFee = totalFee.multiply(BigDecimal.valueOf(ret));
//                payFee = payFee.add(projectFee.getCostPhase1());
//                ptm.addTalkEvent( context,getRemark(1,payFee),13, pm.getUserId1(),ptm.getUserId2() );
//            }
//            if (reasonTermination == 1) {
//                payFee = projectFee.getCostPhase1();
//                minusQ(context,ptm.getGroupId(),0.2,userId1);
//                ptm.addTalkEvent(context,getRemark(2,payFee),13,
//                        pm.getUserId1(),ptm.getUserId2() );
//            }
//        } else if (ptm.getPhase() == 602) {
//            if(reasonTermination == 0) {
//                totalFee = projectFee.getCostSum().subtract(projectFee.getCostPhase1().add(projectFee.getCostPhase2()));//减去第一阶段+第二阶段的费用
//                ret = 0.1;
//                payFee = totalFee.multiply(BigDecimal.valueOf(ret));//违约金加上当前阶段的费用
//                payFee = payFee.add(projectFee.getCostPhase2());
//                ptm.addTalkEvent(context,getRemark(1,payFee),13,
//                        pm.getUserId1(),ptm.getUserId2() );
//            }
//            if (reasonTermination == 1) {
//                payFee = projectFee.getCostPhase2();
//                minusQ(context,ptm.getGroupId(),0.3,userId1);
//                ptm.addTalkEvent(context,getRemark(2,payFee),13,
//                        pm.getUserId1(),ptm.getUserId2() );
//            }
//        } else if (ptm.getPhase() == 603) {
//            if(reasonTermination == 0) {
//                totalFee = projectFee.getCostSum().subtract(projectFee.getCostPhase1().add(projectFee.getCostPhase2()).add(projectFee.getCostPhase3()));//减去第一阶段+第二阶段的费用
//                ret = 0.2;
//                payFee = totalFee.multiply(BigDecimal.valueOf(ret));//违约金加上当前阶段的费用
//                payFee = payFee.add(projectFee.getCostPhase3());
//                ptm.addTalkEvent(context,getRemark(1,payFee),13,
//                        pm.getUserId1(),ptm.getUserId2() );
//            }
//            if(reasonTermination == 1){
//                payFee = projectFee.getCostPhase3();
//                // minusQ(context,ptm.getGroupId(),0.4,userId1);
//                ptm.addTalkEvent(context,getRemark(2,payFee),13,
//                        pm.getUserId1(),ptm.getUserId2() );
//            }
//        } else if (ptm.getPhase() == 604) {
//            if(reasonTermination == 0) {
//                totalFee = projectFee.getCostPhase5().add(projectFee.getCostPhase6());
//                ret = 0.5;
//                payFee = totalFee.multiply(BigDecimal.valueOf(ret));//违约金加上当前阶段的费用
//                payFee = payFee.add(projectFee.getCostPhase3());
//                ptm.addTalkEvent(context,getRemark(1,payFee),13,
//                        pm.getUserId1(),ptm.getUserId2() );
//            }
//            if(reasonTermination == 1){
//              //  minusQ(context,ptm.getGroupId(),0.5,userId1);
//            }
//        } else if (ptm.getPhase() == 605) {
//            payFee = projectFee.getCostPhase6();
//            ptm.addTalkEvent(context,getRemark(0,payFee),13,pm
//                    .getUserId1(),ptm.getUserId2() );
//        }
//        return payFee;
//    }

//    //终止方案 2  延期率大于5.0  乙方提出项目终止
//    private BigDecimal termSchema2(UserProjectContext context, CostModel projectFee, int userId1) {
//        ProjectModel pm = context.getProjectModel();
//        ProjectTalkModel ptm = context.getProjectTalkModel();
//
//        BigDecimal payFee = new BigDecimal("0");//甲方,乙方需要支付费用
//        Double ret = 0.0;
//        if (ptm.getPhase() == 601) {
//            minusQ(context, ptm.getGroupId(), 0.2, userId1);
//        } else if (ptm.getPhase() == 602) {
//            payFee = projectFee.getCostPhase1();
//            minusQ(context,ptm.getGroupId(),   0.3, userId1);
//            ptm.addTalkEvent(context,getRemark(0, payFee), 13, pm.getUserId1(), ptm.getUserId2() );
//        } else if (ptm.getPhase() == 603) {
//            payFee = projectFee.getCostPhase1().add(projectFee.getCostPhase2());
//            minusQ(context,ptm.getGroupId(), 0.4, userId1);
//            ptm.addTalkEvent(context,getRemark(0, payFee), 13, pm.getUserId1(), ptm.getUserId2());
//        } else if (ptm.getPhase() == 604) {
//            payFee = projectFee.getCostPhase1().add(projectFee.getCostPhase2()).add(projectFee.getCostPhase3());
//            minusQ(context,ptm.getGroupId(),  0.5, userId1);
//            ptm.addTalkEvent(context,getRemark(0, payFee), 13, pm  .getUserId1(), ptm.getUserId2() );
//        } else if (ptm.getPhase() == 605) {
//            payFee = projectFee.getCostPhase1().add(projectFee.getCostPhase2()).add(projectFee.getCostPhase3()).add(projectFee.getCostPhase4());
//            minusQ(context,ptm.getGroupId(),  0.8, userId1);
//            ptm.addTalkEvent(context,getRemark(0, payFee), 13, pm  .getUserId1(), ptm.getUserId2() );
//        } else if (ptm.getPhase() == 606) {
//            payFee = projectFee.getCostSum().divide(projectFee.getCostPhase6());
//            minusQ(context,ptm.getGroupId(),  1.0, userId1);
//            ptm.addTalkEvent(context,getRemark(0, payFee), 13, pm  .getUserId1(), ptm.getUserId2() );
//        }
//        return payFee;
//    }

    //获取项目事件备注
//    private String getRemark(int status,BigDecimal payFee){
//
//        String remark = "";
//        if(status == 1){
//            remark = "项目终止,甲方支付当前费用+违约金共:"+payFee+"元";
//        }else if(status == 2){
//            remark = "项目终止,甲方支付当前费用:"+payFee+"元";
//        }else if(status == 0){
//            remark ="项目终止,乙方退回费用:"+payFee+"元";
//        }
//        return remark;
//    }

    //扣Q分
//      private void minusQ(UserProjectContext context, int groupId, Double qNum, int userId1) {
//
//        //projectTalkModel.getWorkGroupMember();
//        List<WorkGroupMemberModel> list = ProjectTalkModel.getWorkGroup().getWorkGroupMember
//                (groupId, null);
//        String remark = "项目终止,乙方交付不合格,扣除" + qNum + "q分";
//        for (WorkGroupMemberModel workGroupMember : list) {
//            UserInfoModel userInfoModel = getInst().getUserCenterService().getUserInfoById(workGroupMember.getUserId());
//            context.getProjectTalkModel().addTalkEvent(context, remark, 14, workGroupMember.getUserId(),
//                    0);
//        }
//    }


}
