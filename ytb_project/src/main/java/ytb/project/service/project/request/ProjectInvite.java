package ytb.project.service.project.request;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.project.common.ProjectConstState;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.TalkInviteStatusMapper;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.TalkInviteStatusModel;
import ytb.common.context.model.YtbError;


public class ProjectInvite extends ProjectApply {
    transient private final static String TalkInviteStatus_TableName = "ytb_project.talk_invite_status";

    //添加洽谈邀请状态
    public int addTalkInviteStatus(TalkInviteStatusModel talkInviteStatus){
        try (SqlSession session = getSession()) {
            TalkInviteStatusMapper TISMapper = session.getMapper(TalkInviteStatusMapper.class);
            TISMapper.addTalkInviteStatus(talkInviteStatus);
            return YtbSql.selectAutoID(session);
        }
    }

    //洽谈邀请状态
    public TalkInviteStatusModel getTalkInviteStatus(int projectId,int userId,int documentId){
        try (SqlSession session = getSession()) {

            TalkInviteStatusMapper TISMapper = session.getMapper(TalkInviteStatusMapper.class);
            return  TISMapper.getTalkInviteStatus(projectId,userId,documentId);

        }
    }

    //修改洽谈邀请状态
    public void modifyStatus(TalkInviteStatusModel talkInviteStatus){
        try (SqlSession session = getSession()) {

            TalkInviteStatusMapper TISMapper = session.getMapper(TalkInviteStatusMapper.class);
            TISMapper.modifyStatus(talkInviteStatus);
            session.commit();
        }
    }


    public void addTalkInviteStatus(UserProjectContext context, String userIdMembers, int documentId) {

        if (userIdMembers == null || userIdMembers.isEmpty()){
            return;
        }

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        for (String sUserId : userIdMembers.split(",")) {
            Integer userId = Integer.valueOf(sUserId);
            if(userId.equals(pm.getUserId1())){
                throw new YtbError(YtbError.CODE_DEFINE_ERROR,"甲方不能做为组员！");
            }
            if (!checkTalkInviteStatus( userId , pm.getProjectId(), documentId)) {
                TalkInviteStatusModel talkInviteStatus = new TalkInviteStatusModel();
                talkInviteStatus.setProjectId(pm.getProjectId());
                talkInviteStatus.setDocumentId(documentId);
                talkInviteStatus.setUserId( userId );
                talkInviteStatus.setStatus(ProjectConstState.INVITE_STATUS_YES);//设置为邀请状态
                if(ptm.isPb(userId)){
                    talkInviteStatus.setStatus(ProjectConstState.INVITE_STATUS_PASS);
                }
                int inviteMemberId = addTalkInviteStatus(talkInviteStatus);
            }
        }

    }

    boolean checkTalkInviteStatus(int userId, int projectId,int documentId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select 1 from ").append(TalkInviteStatus_TableName);
        sql.append(" where project_id=").append(projectId);
        sql.append(" and document_id=").append(documentId);
        sql.append(" and user_id =").append(userId);
        try (SqlSession session = getSession()) {
            return YtbSql.selectList(session, sql, TalkInviteStatusModel.class).size() > 0;
        }
    }



}
