package ytb.project.service.impl.talk;

import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.ProjectEventMapper;
import ytb.project.daoservice.DAOService;
import ytb.project.model.ProjectEventViewModel;
import ytb.project.model.ProjectEventModel;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;
import java.util.Map;

public class ProjectEvent extends DAOService implements ProjectEventMapper{


    @Override
    public int addProjectEvent(ProjectEventModel eventModel){
        try(  SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true)){
            ProjectEventMapper eMapper = session.getMapper(ProjectEventMapper.class);
            eMapper.addProjectEvent(eventModel);
            return eventModel.getEventId();
        }
    }

    @Override
    public List<Map<String, Object>> getProjectEvent(Map map) {
        return null;
    }

    @Override
    public List<ProjectEventViewModel> selectProjectEventViewModel(int projectId, int eventType, int phase, int userId) {

        try(  SqlSession session = getSession()){

            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return projectPEventMapper.selectProjectEventViewModel(projectId,eventType,phase,userId);
        }
    }


    //获取事件
    public List<Map<String, Object>> selectProjectEventByP(int projectId, int phase){
        try(  SqlSession session = getSession()){

            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return projectPEventMapper.selectProjectEventByP(projectId,phase);
        }
    }

    @Override
    public List<ProjectEventViewModel> selectPayEventViewModel(int userId ,int talkId, int phase) {
        try(SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)){
            ProjectEventMapper eventMapper = session.getMapper(ProjectEventMapper.class);
            return  eventMapper.selectPayEventViewModel( userId,talkId,phase);
        }
    }

    @Override
    public List<ProjectEventViewModel> selectProjectEventViewModelByUserId(int projectId, int phase,
                                                                           int userId, int phaseStatus) {
        try(  SqlSession session = getSession()){
            ProjectEventMapper projectEventMapper = session.getMapper(ProjectEventMapper.class);
            return  projectEventMapper.selectProjectEventViewModelByUserId(projectId,phase,userId,phaseStatus);
        }

    }

    public List<ProjectEventViewModel> selectProEventViewModelByPhaseStatus(int projectId,int userId,int phaseStatus){

        try(  SqlSession session = getSession()){

            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return projectPEventMapper.selectProEventViewModelByPhaseStatus(projectId,userId,phaseStatus);
        }
    }


    public List<Map<String, Object>> selectProEventByPhaseStatus(int projectId,int userId,int phaseStatus){

        try(  SqlSession session = getSession()){

            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return projectPEventMapper.selectProEventByPhaseStatus(projectId,userId,phaseStatus);
        }
    }

    @Override
    public List<Map<String, Object>> getBeforeTalkEvent(Map map) {
        return null;
    }


    //获取项目变更阶段的事件记录
    public List<ProjectEventViewModel> getProEventInChange(int projectId, int userId, int phase){
        try(  SqlSession session = getSession()){

            ProjectEventMapper projectPEventMapper = session.getMapper(ProjectEventMapper.class);
            return  projectPEventMapper.getProEventInChange(projectId,userId,phase);
        }
    }


}
