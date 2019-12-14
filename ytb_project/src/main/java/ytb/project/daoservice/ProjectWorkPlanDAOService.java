package ytb.project.daoservice;

import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.tagtable.ProjectPlanMapper;
import ytb.project.model.tagtable.ProjectPlanModel;

public class ProjectWorkPlanDAOService extends DAOService implements ProjectPlanMapper {


    public ProjectPlanModel getProjectPlan(int projectId, int documentId) {

        try (SqlSession session = getSession()) {
            ProjectPlanMapper pPMapper = session.getMapper(ProjectPlanMapper.class);
            return pPMapper.getProjectPlan(projectId, documentId);
        }

    }

}
