package ytb.project.service.impl.talk;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.ProjectEventMapper;
import ytb.project.dao.ProjectPhaseMapper;
import ytb.project.model.*;
import ytb.project.service.impl.pay.payevent.PayEventModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class PhaseAndEvent extends ProjectEvent implements IPhaseAndEvent {


    //获取项目阶段
    public ProjectPhaseModel getProjectPhaseByProjectId(int projectId, int phase) {

        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectPhaseMapper pPMapper = session.getMapper(ProjectPhaseMapper.class);
            return pPMapper.getProjectPhaseByProjectId(projectId,phase);
        }finally {
            session.close();
        }
    }

    //获取项目阶段
    public ProjectPhaseModel getProjectPhase(int talkId,int phase){

        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectPhaseMapper pPMapper = session.getMapper(ProjectPhaseMapper.class);
            return pPMapper.getProjectPhase(talkId,phase);
        }finally {
            session.close();
        }
    }

    public ProjectPhaseModel getProjectPhaseAutoAdd(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectPhaseModel phaseModel = ptm.getTalkId() == 0 ?
                getProjectPhaseByProjectId(pm.getProjectId(), 0) :
                getProjectPhase(ptm.getTalkId(), ptm.getPhase());

        return phaseModel != null ? phaseModel : addProjectPhase(context);
    }


    public ProjectPhaseModel addProjectPhase(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectPhaseModel projectPhase = new ProjectPhaseModel();
        projectPhase.setFolderId(ptm.getTalkId() == 0 ? pm.getFolderId() : ptm.getFolderId());

        projectPhase.setProjectId(pm.getProjectId());
        projectPhase.setTalkId(ptm.getTalkId());
        projectPhase.setPhase(ptm.getPhase());
        projectPhase.setPhaseStatus(0);

        projectPhase.setEnterTime(new Date());
        addProjectPhase(projectPhase);

        return projectPhase ;
    }

    //添加项目阶段
    public int addProjectPhase(ProjectPhaseModel projectPhase){
        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectPhaseMapper pPMapper = session.getMapper(ProjectPhaseMapper.class);
            pPMapper.addProjectPhase(projectPhase);
            session.commit();
            return projectPhase.getPhaseId();
        }finally {
            session.close();
        }
    }
    //添加项目阶段
    public int addTalkEvent(ProjectEventModel eventModel){

        try(SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true)){
            ProjectEventMapper eventMapper = session.getMapper(ProjectEventMapper.class);
            eventMapper.addProjectEvent(eventModel);
            return eventModel.getEventId();
        }
    }

    public ProjectEventModel addPayEvent (UserProjectContext context, PayEventModel payEvent) {
        int phaseId = getProjectPhaseAutoAdd(context).getPhaseId();

        ProjectEventModel eventModel = new ProjectEventModel();
        eventModel.setChangeType(payEvent.getChangeType());
        eventModel.setServiceType(ProjectEventModel.EVENT_SERVICE_PAY);
        eventModel.setPhaseId(phaseId);
        eventModel.setRemark(payEvent.getRemark());
        eventModel.setEventType(payEvent.getEvenType());
        eventModel.setPhaseStatus(context.getProjectTalkModel().getPhase());
        eventModel.setEventAnother(payEvent.getPb());
        eventModel.setEventSponsor(payEvent.getPa());
        eventModel.setFee(payEvent.getPayFee());
        addTalkEvent(eventModel);
        return eventModel;
    }

    public ProjectEventModel addEventAssist(UserProjectContext context, PayEventModel payEvent) {
        int phaseId = getProjectPhaseAutoAdd(context).getPhaseId();

        ProjectEventModel eventModel = new ProjectEventModel();
        eventModel.setChangeType(payEvent.getChangeType());
        eventModel.setServiceType(ProjectEventModel.EVENT_SERVICE_ASSIST);
        eventModel.setPhaseId(phaseId);
        eventModel.setRemark(payEvent.getRemark());
        eventModel.setEventType(payEvent.getEvenType());
        eventModel.setPhaseStatus(context.getProjectTalkModel().getPhase());
        eventModel.setEventAnother(payEvent.getPb());
        eventModel.setEventSponsor(payEvent.getPa());
        eventModel.setFee(payEvent.getPayFee());
        addTalkEvent(eventModel);
        return eventModel;
    }

    public ProjectEventModel addEvent(UserProjectContext context, PayEventModel payEvent) {
        int phaseId = getProjectPhaseAutoAdd(context).getPhaseId();

        ProjectEventModel eventModel = new ProjectEventModel();
        eventModel.setChangeType(payEvent.getChangeType());
        eventModel.setServiceType(ProjectEventModel.EVENT_SERVICE_DEFUALT);
        eventModel.setPhaseId(phaseId);
        eventModel.setRemark(payEvent.getRemark());
        eventModel.setEventType(payEvent.getEvenType());
        eventModel.setPhaseStatus(context.getProjectTalkModel().getPhase());
        eventModel.setEventAnother(payEvent.getPb());
        eventModel.setEventSponsor(payEvent.getPa());
        eventModel.setFee(payEvent.getPayFee());
        addTalkEvent(eventModel);
        return eventModel;
    }


    //添加项目记录
    public ProjectEventModel addTalkEvent(
            String remark,
            int evenType,
            int eventUserId,
            int eventAnother,
            int pPhaseId,
            int phaseStatus,
            BigDecimal fee,
            int serviceType) {

        ProjectEventModel projectEventModel = new ProjectEventModel();
        projectEventModel.setPhaseId(pPhaseId);
        projectEventModel.setEventTime(new Date());
        projectEventModel.setEventType(evenType);
        projectEventModel.setEventSponsor(eventUserId);
        projectEventModel.setRemark(remark);
        projectEventModel.setEventAnother(eventAnother);

        projectEventModel.setPhaseStatus(phaseStatus);
        projectEventModel.setFee(fee);
        projectEventModel.setServiceType(serviceType);
        addProjectEvent(projectEventModel);
        return projectEventModel;

    }


    //获取项目过程记录
    public List<Map<String, Object>> getProjectEvent(Map map){

        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);

        try{
            ProjectEventMapper eventMapper = session.getMapper(ProjectEventMapper.class);
            return eventMapper.getProjectEvent(map);

        }finally {
            session.close();
        }
    }

    public List<ProjectEventViewModel> selectProjectEventViewModel(int projectId, int eventType, int phase, int userId){

        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return projectPEventMapper.selectProjectEventViewModel(projectId,eventType,phase,userId);
        }finally {
            session.close();
        }
    }


    public List<Map<String, Object>> selectProjectEventByP(int projectId,int phase){

        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return   projectPEventMapper.selectProjectEventByP(projectId,phase);
        }finally {
            session.close();
        }
    }



    //获取项目过程记录
    public   List<Map<String, Object>> getBeforeTalkEvent(Map map){

        SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true);
        try{
            ProjectEventMapper pPEMapper = session.getMapper(ProjectEventMapper.class);
            return  pPEMapper.getBeforeTalkEvent(map);

        }finally {
            session.close();
        }
    }


}
