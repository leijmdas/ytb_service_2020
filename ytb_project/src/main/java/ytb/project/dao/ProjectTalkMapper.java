package ytb.project.dao;



import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectTalkModel;

import java.util.List;

public interface ProjectTalkMapper {
    //通过项目查询洽谈列表
    List getProjectTalk(int projectId);

    //通过项目ID、Userid查找记录
    int getTalkByProjectAndUser(@Param("userId") Integer userId,
                                @Param("companyId") Integer companyId,
                                @Param("projectId") Integer projectId);
    //通过洽谈Id获取洽谈
    ProjectTalkModel getProjectTalkById(int talkId);

    //通过项目ID 找到洽谈
    ProjectTalkModel getProjectTalkByProjectId(int projectId);

    //添加洽谈
    int addProjectTalk(ProjectTalkModel projectTalkModel);

    //修改洽谈状态
    void modifyTalkPhasePayDate(ProjectTalkModel projectTalkModel);
    //修改洽谈阶段
    void modifyTalkPhase(@Param("phase") int phase,@Param("talkId") int talkId);

    void modifyTalkConfirm(ProjectTalkModel talkModel);

    void modifyTalkConfirm(
            @Param("talkId") int talkId,
            @Param("folderId") int v,
            @Param("groupId") int groupId,
            @Param("phase") int phase,
            @Param("documentId") int documentId);

    void modifyTalkPhaseAndStatus(@Param("phase") Integer phase,@Param("phaseStatus") Integer
            phaseStatus,@Param("changeStatus") Integer
            changeStatus,@Param("talkId") Integer talkId);

    //修改洽谈子状态
    void modifyPhaseStatusEventType(@Param("phaseStatus") int phaseStatus,@Param("eventType") int  eventType,@Param(
            "talkId") int talkId);
    void modifyPhaseStatusEventType(ProjectTalkModel ptm);

    //修改变更类型
    void modifyChangeType(@Param("changeType") int changeType,@Param("talkId") int talkId);

    //修改工作组id
    void modifyGroupId(@Param("groupId") int groupId,@Param("talkId") int talkId);

    //修改洽谈表，标志洽谈通过
    void confirmTalk( ProjectTalkModel  ptm );

    //添加工作组计划
    void modifyWorkplanId(@Param("workplanId") int workplanId,@Param("talkId") int talkId);

    void modifyTalkRemark(ProjectTalkModel projectTalkModel);

    void updateNewProjectTalk(@Param("talkId") int talkId,@Param("groupId") int groupId,@Param
            ("workplanId") int workplanId);
}
