package ytb.project.service;


import ytb.project.context.ProjectContext;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ProjectDAOService;
import ytb.project.daoservice.ProjectInviteDAOService;
import ytb.project.model.ProjectReportModel;
import ytb.project.model.UserCollectionProModel;
import ytb.project.model.projectview.ProjectResultViewModel;
import ytb.project.service.impl.release.ReleaseInfo;
import ytb.project.service.impl.release.ReleaseResult;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface ProjectReleaseService {
    ProjectDAOService getProjectDAOService();

    ProjectInviteDAOService getProjectInviteDAOService();

    List<Map<String, Object>> getProjectListOfCompany(Map map);

    // 发布项目草稿
    ReleaseResult releaseProject(UserProjectContext context, String friends) throws UnsupportedEncodingException;

    // 加工采购发布
    ReleaseResult releaseProjectPp(UserProjectContext context, ReleaseInfo releaseInfo,
                                   String friends) throws UnsupportedEncodingException;

    //确认项目发布与重新发布，停止发布
    String releaseProjectNext(UserProjectContext context, int publishState, int templateId, Boolean isTaskPublish);

    //查询通讯录好友
    List getUserFriends(int userId, String remark);
    //申请项目
    int applyProject(UserProjectContext context, String reqRemark);

    ProjectContext confirmProjectApply(UserProjectContext context, int eventType) throws UnsupportedEncodingException;

    //审核
    void auditProject(int projectId, int status);

    //邀请组员
    void inviteMember(UserProjectContext context,String userId,int documentId);

    void confirmInvitePm(UserProjectContext context, int projectId, int isOk, int userId) throws UnsupportedEncodingException;

    //查询所有项目列表
    ProjectResultViewModel getProjectLists(UserProjectContext context, Integer status);


    //获取搜藏列表
    List<Map<String,Object>> getUserCollectionList(Map<String,Object> map);

    //获取总条数
    int getUserCollectionCount(Map<String,Object> map);

    //添加收藏
    void addUserCollection(UserCollectionProModel collectionProModel);

    //取消收藏
    void deleteUserCollection(Map<String,Object> map);

    //添加举报
    void addProjectReport(ProjectReportModel reportModel);

}
