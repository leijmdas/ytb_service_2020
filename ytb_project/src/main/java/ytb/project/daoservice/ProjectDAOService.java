package ytb.project.daoservice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.ProjectMapper;
import ytb.project.model.ProjectModel;
import ytb.project.model.VProjectTalkConfirmModel;
import ytb.project.model.projectview.ProjectTalkViewModel;
import ytb.project.model.projectview.ProjectViewModel;

import java.util.List;
import java.util.Map;

public class ProjectDAOService extends DAOService implements ProjectMapper {

    public List<ProjectTalkViewModel> getProjectTalkList(UserProjectContext context, Integer phaseStart,
                                                         Integer PhaseEnd, Integer changeStatus) {
        return getProjectTalkList(context.getUserId(), context.getCompanyId(), phaseStart, PhaseEnd, changeStatus);
    }

    @Override
    public List<ProjectTalkViewModel> getProjectTalkList(Integer userId, Integer companyId,
                                                         Integer phaseStart, Integer PhaseEnd, Integer changeStatus) {
        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.getProjectTalkList(userId, companyId, phaseStart, PhaseEnd, changeStatus);
        }
    }

    @Override
    public List<ProjectViewModel> getProjectList(Integer userId, Integer companyId, Integer status) {
        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.getProjectList(userId, companyId, status);
        }
    }


    //添加项目
    public int addProject(ProjectModel pm) {
        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.addProject(pm);
            return pm.getProjectId();
        }
    }

    //修改项目支付次数
    public int modifyProjectPayTimes(int projectId) {
        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.modifyProjectPayTimes(projectId);
        }
    }

    public int modifyProjectModelFlag(int projectId, byte modelFlag) {
//        try (SqlSession session = getSession()) {
//            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
//            return projectMapper.modifyProjectModelFlag(projectId, modelFlag);
//        }
        StringBuilder sql=new StringBuilder(128);
        sql.append(" UPDATE ytb_project.project  ");
        sql.append(" SET model_flag=  ").append(modelFlag);
        sql.append(" where project_id=").append(projectId);

        return YtbSql.update(sql);
    }


    //修改项目状态
    public void modifyProjectStatus(int status, int projectId) {

        try (SqlSession session = getSession()) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            pMapper.modifyProjectStatus(status, projectId);
        }
    }


    //查询终止项目
    public List<Map<String, Object>> getEndProject(Map map) {
        try (SqlSession session = getSession()) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getEndProject(map);
        }
    }

    public List<Map<String, Object>> getProjectsByCompany(Map map) {
        try (SqlSession session = getSession()) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getProjectsByCompany(map);
        }
    }
    //查询洽谈通过的项目（项目视图）
    @Override
    public VProjectTalkConfirmModel getVProjectTalkConfirmInfo(int projectId) {
        try (SqlSession session = getSession()) {

            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getVProjectTalkConfirmInfo(projectId);

        }
    }


    //搜索项目
    public int getProjectListCount(Map map) {
        try (SqlSession session = getSession()) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getProjectListCount(map);
        }

    }

    public List<Map<String, Object>> getCompanySendAndTalkPro(Integer companyId) {
        try (SqlSession session = getSession()) {

            ProjectMapper PTMapper = session.getMapper(ProjectMapper.class);
            return PTMapper.getCompanySendAndTalkPro(companyId);
        }
    }

    public List<Map<String, Object>> getUserSendAndTalkPro(Integer userId) {
        try (SqlSession session = getSession()) {
            ProjectMapper PTMapper = session.getMapper(ProjectMapper.class);
            return PTMapper.getUserSendAndTalkPro(userId);

        }
    }

    @Override
    public int getEndProjectCount(Map map) {
        return 0;
    }


    //回填新生成项目的ID到老项目的project_change_id中，并修改老项目的状态(用户项目变更)
    @Override
    public void updateProjectInfoInChangeStage(Integer projectId,
                                               Integer newProjectId, Integer oldTalkId, Integer changeType) {
        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.updateProjectInfoInChangeStage(projectId, newProjectId, oldTalkId, changeType);
        }
    }

    @Override
    public List<Map<String, Object>> getReleaseProjectList(Map map) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.getReleaseProjectList(map);
        }
    }

    @Override
    public List<Map<String, Object>> getProjectByChangeStatus(Map map) {
            try (SqlSession session = getSession()) {

                ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
                return projectMapper.getProjectByChangeStatus(map);
            }

    }



    public List<ProjectViewModel> getProjectList(UserProjectContext context, Integer status) {
        try (SqlSession session = getSession()) {

            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getProjectList(context.getUserId(), context.getCompanyId(), status);

        }

    }

    //通过项目id查询项目
    public ProjectModel getProjectById(int projectId) {
        try (SqlSession session = getSession()) {

            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getProjectById(projectId);
        }

    }


    //查询公司员工有关的项目
    public List<Map<String, Object>> getProjectListByCompUser(Map map) {
        try (SqlSession session = getSession()) {

            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.getProjectListByCompUser(map);
        }
    }

    public List<Map<String, Object>> getUserSendPro(Map<String, Object> map) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.getUserSendPro(map);
        }
    }

    public int getUserSendProCount(int userId) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.getUserSendProCount(userId);
        }

    }

    public void cancelFavorite(int projectId) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.cancelFavorite(projectId);
            session.commit();
        }
    }

    //查询已完成项目详情
    public List<Map<String, Object>> getFinishProjectView(int userId, int projectId) {

        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.getFinishProjectView(userId, projectId);

        }

    }


    //修改浏览数
    public void modifyFavorite(int projectId) {

        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.modifyFavorite(projectId);
            session.commit();
        }
    }


    public void modifyStopTime(int projectId) {
        try (SqlSession session = getSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.modifyStopTime(projectId);
            session.commit();
        }
    }


    //修改浏览数
    public void modifyViewNumber(int projectId) {

        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.modifyViewNumber(projectId);
            session.commit();
        }
    }


    //修改项目
    public void modifyFolder(@Param("projectId") int projectId, @Param("folderId") int folderId) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.modifyFolder(projectId, folderId);
            session.commit();
        }

    }


    //查询已完成项目
    public List<Map<String, Object>> selectFinishProject(Map<String, Object> map) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.selectFinishProject(map);
        }
    }


    public int selectFinishProjectCount(int userId) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.selectFinishProjectCount(userId);

        }
    }

    @Override
    public List<Map<String, Object>> selectProjectRun(Map map) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.selectProjectRun(map);

        }
    }


    //搜索项目
    public List<Map<String, Object>> selectProjectList(Map map) {
        try (SqlSession session = getSession()) {

            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.selectProjectList(map);
        }
    }


    @Override
    public List<Map<String, Object>> selectNewProject() {
        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.selectNewProject();
        }
    }

    @Override
    public List<Map<String, Object>> selectProjectTalk(Map map) {
        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            ProjectMapper pMapper = session.getMapper(ProjectMapper.class);
            return pMapper.selectProjectTalk(map);
        }
    }


}
