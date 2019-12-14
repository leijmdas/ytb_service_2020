package ytb.project.daoservice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.ProjectInviteMapper;
import ytb.project.model.ProjectInviteModel;
import ytb.project.model.ProjectInviteViewModel;
import ytb.project.model.projectview.ProjectApplyViewModel;

import java.util.List;
import java.util.Map;

public class ProjectInviteDAOService extends DAOService implements ProjectInviteMapper {


    //获取邀请信息：status：状态为1表示查询发出的邀请.状态为2表示查询收到的邀请
    public PageUtils getInviteList(Map map, Integer status) {
        PageUtils pageUtils = null;

        Query query = new Query(map);
        if (status == 1) {
            List<ProjectInviteViewModel> list = getInviteSendList(query);
            int count = getInviteSendCount((Integer) map.get("userId"));
            pageUtils = new PageUtils(list, count, query.getLimit(), query.getPage());
        } else if (status == 2) {
            List<ProjectInviteViewModel> list = getInviteRecList(query);
            int count = getInviteRecCount((Integer) map.get("userId"));
            pageUtils = new PageUtils(list, count, query.getLimit(), query.getPage());
        }
        return pageUtils;

    }

    //获取申请信息列表：status:1：发出的申请    2 收到的申请
    public PageUtils getApplyList(Map map, Integer status) {
        PageUtils pageUtils = null;

        Query query = new Query(map);
        Integer userId = (Integer) map.get("userId");

        if (status == 1) {
            List<ProjectApplyViewModel> list = getApplySendList(query);//发出的申请
            int count = getApplySendCount(userId);
            pageUtils = new PageUtils(list, count, query.getLimit(), query.getPage());
        } else if (status == 2) {

            List<ProjectApplyViewModel> list = getApplyReceivedList(query);//发出的申请
            int count = getApplyReceivedCount(userId);
            pageUtils = new PageUtils(list, count, query.getLimit(), query.getPage());
        }
        return pageUtils;
    }


    @Override
    public List<ProjectInviteViewModel> getInviteRecList(Map<String, Object> map) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);

            return mapper.getInviteRecList((Query)map);
        }
    }
    @Override
    public int getInviteRecCount(Integer userId) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);
            return mapper.getInviteRecCount( userId );

        }
    }
    @Override
    public List<ProjectInviteViewModel> getInviteSendList(Map<String, Object> map) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);

            return mapper.getInviteSendList((Query)map);
        }
    }
    @Override
    public int getInviteSendCount(Integer userId) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);
            return mapper.getInviteSendCount( userId );

        }
    }
    @Override
    public List<ProjectApplyViewModel> getApplyReceivedList(Map<String, Object> map) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);

            return mapper.getApplyReceivedList((Query)map);//发出的申请
        }
    }

    @Override
    public Integer getApplyReceivedCount(Integer userId) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);
            return mapper.getApplyReceivedCount( userId );

        }
    }

    @Override
    public List<ProjectApplyViewModel> getApplySendList(Map<String, Object> map) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);

            return mapper.getApplySendList((Query)map);//发出的申请
        }

    }

    public Integer getApplySendCount(Integer userId){
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);
            return mapper.getApplySendCount( userId );

        }
    }

    public  int countInviteRecordsRec(int projectId){
        try(SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true))
        {
            ProjectInviteMapper PTMapper = session.getMapper(ProjectInviteMapper.class);
            return  PTMapper.countInviteRecordsRec(projectId);
        }
    }


    //点击申请列表
    public  List<Map<String, Object>> getInviteRecDetailsList(Map map){
        try (SqlSession session = getSession()) {

            ProjectInviteMapper inviteMapper = session.getMapper(ProjectInviteMapper.class);
            return  inviteMapper.getInviteRecDetailsList(map);
        }
    }


    public ProjectInviteModel getProjectInviteByTalkId(Integer talkId) {
        try (SqlSession session = getSession()) {
            ProjectInviteMapper ptMapper = session.getMapper(ProjectInviteMapper.class);
            return ptMapper.getProjectInviteByTalkId(talkId);
        }
    }

    //通过项目ID获取申请记录

    public List<ProjectInviteModel> getProjectInvitesByPB (@Param("projectId") Integer projectId,
                                                    @Param("userId") Integer userId){
        try (SqlSession session = getSession()) {
            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);
            return mapper.getProjectInvitesByPB(projectId,userId);
        }
    }

    @Override
    public List<ProjectInviteModel> getProjectInvitesByProject(Integer projectId) {
        try (SqlSession session = getSession()) {

            ProjectInviteMapper mapper = session.getMapper(ProjectInviteMapper.class);
            return mapper.getProjectInvitesByProject(projectId);
        }
    }

    //添加邀请、申请
    public int addProjectInvite(ProjectInviteModel projectInviteModel){

        try( SqlSession session= getSession()) {
            ProjectInviteMapper inviteMapper = session.getMapper(ProjectInviteMapper.class);
            inviteMapper.addProjectInvite(projectInviteModel);
            return projectInviteModel.getTalkId();
        }
    }


    //修改邀请状态
    public void updateInviteEventType(Integer talkId, Integer eventType) {

        try (SqlSession session = getSession()) {

            ProjectInviteMapper inviteMapper = session.getMapper(ProjectInviteMapper.class);
            inviteMapper.updateInviteEventType(talkId, eventType);
        }
    }


}
