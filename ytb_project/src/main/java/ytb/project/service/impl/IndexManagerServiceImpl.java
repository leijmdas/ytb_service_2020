package ytb.project.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.ProjectMapper;
import ytb.project.service.IndexManagerService;

import java.util.List;
import java.util.Map;

/**
 * Created by ZYB on 2018/11/16 18:33
 */
public class IndexManagerServiceImpl implements IndexManagerService {
    @Override
    public List<Map<String, Object>> selectNewProjects() {
        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.selectNewProject();
        }
    }

}
