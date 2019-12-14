package ytb.project.dao;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectInviteModel;
import ytb.project.model.ProjectInviteViewModel;
import ytb.project.model.projectview.ProjectApplyViewModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.project.dao
 * Author: ZCS
 * Date: Created in 2019/2/15 10:14
 */
public interface ProjectInviteMapper {

    //查询收到的邀请(加分页)
    List<ProjectInviteViewModel> getInviteRecList(Map<String,Object> map);

    int getInviteRecCount(Integer userId);

    //查询发出的邀请(加分页)
    List<ProjectInviteViewModel> getInviteSendList(Map<String,Object> map);

    int getInviteSendCount(Integer userId);
    //查询收到的申请
    List<ProjectApplyViewModel> getApplyReceivedList(Map<String,Object> map);


    //新增邀请、申请记录
    int addProjectInvite(ProjectInviteModel projectInviteModel);


    Integer getApplyReceivedCount(Integer userId);
    Integer getApplySendCount(Integer userId);

    //获取收到的申请的详情记录
    List<Map<String,Object>> getInviteRecDetailsList(Map<String,Object> map);

    //查询发出的申请
    List<ProjectApplyViewModel> getApplySendList(Map<String,Object> map);

    int countInviteRecordsRec(int projectId);


    //修改邀请状态
    void updateInviteEventType(@Param("talkId") Integer talkId,@Param("eventType") Integer eventType);

    ProjectInviteModel getProjectInviteByTalkId(@Param("talkId") Integer talkId);
    List<ProjectInviteModel> getProjectInvitesByProject(@Param("projectId") Integer projectId);
    List<ProjectInviteModel> getProjectInvitesByPB (@Param("projectId") Integer projectId,
                                                    @Param("userId") Integer userId);

}