package ytb.project.daoservice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.ProjectTalkMapper;
import ytb.project.model.ProjectTalkModel;

import java.util.List;

public class ProjectTalkDAOService extends DAOService implements ProjectTalkMapper {


    public List<ProjectTalkModel> getProjectTalk(int projectId) {
        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            return talkMapper.getProjectTalk(projectId);
        }

    }

    //通过talkId查询洽谈
    public ProjectTalkModel getProjectTalkById(int talkId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            return talkMapper.getProjectTalkById(talkId);

        }
    }

    @Override
    public ProjectTalkModel getProjectTalkByProjectId(int projectId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            return talkMapper.getProjectTalkByProjectId(projectId);

        }
    }

    //添加洽谈
    public int addProjectTalk(ProjectTalkModel projectTalkModel) {
        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.addProjectTalk(projectTalkModel);
            return projectTalkModel.getTalkId();
        }
    }

    //乙方或甲方同意协作终止
    public void modifyTalkRemark(ProjectTalkModel projectTalkModel) {
        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyTalkRemark(projectTalkModel);
        }
    }

    @Override
    public void updateNewProjectTalk(int talkId, int groupId,int newWorkplanId) {
        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.updateNewProjectTalk(talkId,groupId,newWorkplanId);
        }
    }

    //洽谈表中工作组id
    public void modifyWorkplanId(int workPlanId, int talkId) {
        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyWorkplanId(workPlanId, talkId);

        }
    }


    //修改洽谈状态
    public void modifyTalkPhase(int phase, int talkId) {

        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyTalkPhase(phase, talkId);

        }
    }

    //修改项目支付时talk表状态
    public void modifyTalkPhasePayDate(ProjectTalkModel projectTalkModel) {
        try (SqlSession session = YtbContext.getSqlSessionBuilder().getSession_project(true)) {
            ProjectTalkMapper PTMapper = session.getMapper(ProjectTalkMapper.class);
            PTMapper.modifyTalkPhasePayDate(projectTalkModel);
        }
    }


    //修改洽谈数据
    //groupid  phase documentid
    public void modifyTalkConfirm(ProjectTalkModel talkModel) {
        modifyTalkConfirm(talkModel.getTalkId(), talkModel.getFolderId(), talkModel.getGroupId(),
                talkModel.getPhase(), talkModel.getWorkplanId());

    }

    public void modifyTalkConfirm(@Param("talkId") int talkId,
                                  @Param("folderId") int folderId,
                                  @Param("groupId") int groupId,
                                  @Param("phase") int phase,
                                  @Param("documentId") int documentId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyTalkConfirm(talkId, folderId, groupId, phase, documentId);
        }
    }

    //洽谈表中工作组id
    public void modifyGroupId(int groupId, int talkId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyGroupId(groupId, talkId);
            session.commit();
        }
    }


    //添加洽谈子状态
    public void modifyPhaseStatusEventType(int phaseStatus, int eventType, int talkId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyPhaseStatusEventType(phaseStatus, eventType, talkId);
            session.commit();
        }
    }
    public void modifyPhaseStatusEventType(ProjectTalkModel ptm)
    {
         modifyPhaseStatusEventType(ptm.getPhaseStatus(),ptm.getEventType(),ptm.getTalkId());

    }

    //项目终止,修改洽谈状态
    public void modifyTalkPhaseAndStatus(Integer phase, Integer phaseStatus, Integer changeStatus,
                                         Integer talkId) {
        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyTalkPhaseAndStatus(phase, phaseStatus,changeStatus, talkId);

        }
    }

    //修改变更类型
    public void modifyChangeType(int changeType, int talkId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.modifyChangeType(changeType, talkId);

        }
    }


    //修改项目洽谈id
    public void confirmTalk(ProjectTalkModel ptm) {

        try (SqlSession session = getSession()) {
            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            talkMapper.confirmTalk(ptm);
        }
    }


    public int getTalkByProjectAndUser(Integer userId, Integer companyId, Integer projectId) {
        try (SqlSession session = getSession()) {

            ProjectTalkMapper talkMapper = session.getMapper(ProjectTalkMapper.class);
            return talkMapper.getTalkByProjectAndUser(userId, companyId, projectId);

        }
    }
}

