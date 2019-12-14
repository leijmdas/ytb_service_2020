package ytb.project.service.project.request;

import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.ProjectMapper;
import ytb.project.daoservice.DAOService;
import java.util.Map;

public class ProjectApply extends DAOService {

    //查询终止项目总数
    public int getEndProjectCount(Map<String, Object> map) {
        try (SqlSession session = getSession()) {
            ProjectMapper PTMapper = session.getMapper(ProjectMapper.class);
            return PTMapper.getEndProjectCount(map);
        }
    }
}
