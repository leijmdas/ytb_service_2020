package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.TalkInviteStatusModel;

public interface TalkInviteStatusMapper {

    //添加邀请状态
    int addTalkInviteStatus(TalkInviteStatusModel talkInviteStatus);

    TalkInviteStatusModel getTalkInviteStatus(@Param("projectId") int projectId,@Param("userId") int userId,@Param("documentId") int documentId);

    void modifyStatus(TalkInviteStatusModel talkInviteStatus);

}
