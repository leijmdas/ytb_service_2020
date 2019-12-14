package ytb.project.daoservice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.ProjectChangeMapper;
import ytb.project.model.ProjectChangeModel;
import ytb.project.model.TabPhaseModel;

import java.util.List;

/**
 * Package: ytb.project.daoservice
 * Author: ZCS
 * Date: Created in 2019/4/11 16:43
 */
public class ChangeDaoServiceImpl extends DAOService implements ProjectChangeMapper {

    @Override
    public int addChange(ProjectChangeModel changeModel) {

        try (SqlSession session = getSession()) {
            ProjectChangeMapper pMapper = session.getMapper(ProjectChangeMapper.class);
            return pMapper.addChange(changeModel);
        }
    }

    public ProjectChangeModel getChangeByTalkAndPhase(int talkId, int phase) {
        try (SqlSession session = getSession()) {
            ProjectChangeMapper changeMapper = session.getMapper(ProjectChangeMapper.class);
            return changeMapper.getChangeByTalkAndPhase(talkId, phase);
        }
    }


    public ProjectChangeModel getChangeStop(int talkId, int phase) {
        try (SqlSession session = getSession()) {
            ProjectChangeMapper changeMapper = session.getMapper(ProjectChangeMapper.class);
            return changeMapper.getChangeStop(talkId, phase);
        }
    }

    @Override
    public ProjectChangeModel getChangeById(int talkId, int newProjectId, int phase) {
        try (SqlSession session = getSession()) {
            ProjectChangeMapper changeMapper = session.getMapper(ProjectChangeMapper.class);
            return changeMapper.getChangeById(talkId,newProjectId,phase);
        }
    }

    @Override
    public List<ProjectChangeModel> getChangeModels(@Param("talkId") int talkId) {
        try (SqlSession session = getSession()) {
            ProjectChangeMapper pMapper = session.getMapper(ProjectChangeMapper.class);
            return pMapper.getChangeModels(talkId);
        }
    }

    @Override
    public List<TabPhaseModel> getChangeModelList(int ProjectId) {
        try (SqlSession session = getSession()) {
            ProjectChangeMapper changeMapper = session.getMapper(ProjectChangeMapper.class);
            return changeMapper.getChangeModelList(ProjectId);
        }
    }

    @Override
    public void updateProjectChange(Integer newProjectId, Integer phaseStatus,Integer eventType) {
        try (SqlSession session = getSession()) {
            ProjectChangeMapper pMapper = session.getMapper(ProjectChangeMapper.class);
             pMapper.updateProjectChange(newProjectId,phaseStatus,eventType);
        }
    }

}
