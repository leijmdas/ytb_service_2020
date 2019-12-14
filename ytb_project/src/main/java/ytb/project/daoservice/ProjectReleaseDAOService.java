package ytb.project.daoservice;

import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.*;
import ytb.project.model.*;
import ytb.project.context.ProjectSrvContext;
import ytb.project.model.projectview.ProjectAssistViewModel;
import java.util.List;
import java.util.Map;

public class ProjectReleaseDAOService extends DAOService {
    protected ProjectDAOService projectDAOService = new ProjectDAOService();

    protected ProjectInviteDAOService inviteDAOService = new ProjectInviteDAOService();

    public ProjectDAOService getProjectDAOService() {
        return projectDAOService;
    }

    public ProjectInviteDAOService getProjectInviteDAOService() {
        return inviteDAOService;
    }

    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    public List<ProjectAssistViewModel> getProjectAssistList(int userId) {
        try (SqlSession session = getSession()) {
            ProjectFolderAssistMapper pMapper = session.getMapper(ProjectFolderAssistMapper.class);
            return pMapper.getProjectAssistList(userId);
        }
    }

    //获取通讯录好友
    public List getUserFriends(int userId, String remark){
        return  getInst().getUserFriendService().GetUserFriend(userId,remark);
    }


    public List<Map<String, Object>> getUserCollectionList(Map<String, Object> map) {
        try (SqlSession session = getSession()) {

            UserCollectionProMapper PTMapper = session.getMapper(UserCollectionProMapper.class);
            return  PTMapper.getUserCollectionList(map);
        }
    }

    public int getUserCollectionCount(Map<String, Object> map) {
        try (SqlSession session = getSession()) {

            UserCollectionProMapper PTMapper = session.getMapper(UserCollectionProMapper.class);
            return  PTMapper.getUserCollectionCount(map);
        }
    }

    public void addUserCollection(UserCollectionProModel collectionProModel) {
        try (SqlSession session = getSession()) {

            UserCollectionProMapper PTMapper = session.getMapper(UserCollectionProMapper.class);
            PTMapper.addUserCollection(collectionProModel);
            session.commit();
        }
    }

    public void deleteUserCollection(Map<String, Object> map) {
        try (SqlSession session = getSession()) {
            UserCollectionProMapper PTMapper = session.getMapper(UserCollectionProMapper.class);
            PTMapper.deleteUserCollection(map);
            session.commit();
        }
    }

    public void addProjectReport(ProjectReportModel reportModel) {
        try (SqlSession session = getSession()) {
            ProjectReportMapper PTMapper = session.getMapper(ProjectReportMapper.class);
            PTMapper.addProReport(reportModel);
            session.commit();
        }
    }

}
