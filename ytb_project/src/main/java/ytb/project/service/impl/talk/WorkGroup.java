package ytb.project.service.impl.talk;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.tagtable.ProjectMemberDutyMapper;
import ytb.project.dao.tagtable.ProjectMemberTaskMapper;
import ytb.project.dao.tagtable.WorkGroupMapper;
import ytb.project.dao.tagtable.WorkGroupMemberMapper;
import ytb.project.daoservice.DAOService;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.ProjectMemberDutyModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.project.model.tagtable.WorkGroupModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WorkGroup extends DAOService implements IWorkGroup  {

    //设置工作组成员
    public void addWorkGroupMember(int userId,
                                   int groupId,
                                   int projectId,
                                   int isAdmin,
                                   int talkId) {
        WorkGroupMemberModel workGroupMember = new WorkGroupMemberModel();//添加工作组成员
        workGroupMember.setGroupId(groupId);
        workGroupMember.setProjectId(projectId);
        //临时
        workGroupMember.setDocumentId(userId);

        workGroupMember.setUserId(userId);
        workGroupMember.setIsAdmin(isAdmin);
        workGroupMember.setCreateTime(new Date());
        workGroupMember.setTalkId(talkId);
        addWorkGroupMember(workGroupMember);
    }


    //添加工作组成员
    public void addWorkGroupMember(WorkGroupMemberModel workGroupMember) {

        try(SqlSession session = getSession()) {
            WorkGroupMemberMapper memberMapper = session.getMapper(WorkGroupMemberMapper.class);
            memberMapper.addWorkGroupMember(workGroupMember);

        }

    }

    //查询工作组
    public WorkGroupMemberModel getWorkGroupMember(int projectId, int userId, int documentId) {
        try(SqlSession session = getSession ()) {
            WorkGroupMemberMapper wGMMapper = session.getMapper(WorkGroupMemberMapper.class);
            return wGMMapper.getWorkGroupMemberById(projectId, userId, documentId);
        }
    }

    @Override
    //获取工作组成员
    public List<WorkGroupMemberModel> getWorkGroupMember(Integer groupId,Integer userId) {
        try(SqlSession session = getSession ()) {
            WorkGroupMemberMapper memberMapper = session.getMapper(WorkGroupMemberMapper.class);
            return memberMapper.getWorkGroupMember(groupId,userId);
        }

    }

    @Override
    public List<Map<String, Object>> getWorkGroupMemberInfo(int groupId) {
        try(SqlSession session = getSession ()) {

            WorkGroupMemberMapper wGMMapper = session.getMapper(WorkGroupMemberMapper.class);
            return wGMMapper.getWorkGroupMemberInfo(groupId);
        }
    }

    @Override    //获取成员工作岗位
    public List<ProjectMemberDutyModel> getProjectMemberDuty(int memberId) {
        try(SqlSession session = getSession ()) {

            ProjectMemberDutyMapper pMDMapper = session.getMapper(ProjectMemberDutyMapper.class);
            return pMDMapper.getProjectMemberDuty(memberId);
        }
    }

    //获取项目所有岗位
    public List<Map<String, Object>> getAllProjectMemberDuty(int projectId) {
        try(SqlSession session = getSession ()) {

            ProjectMemberDutyMapper pMDMapper = session.getMapper(ProjectMemberDutyMapper.class);
            return pMDMapper.getAllProjectMemberDuty(projectId);

        }

    }

    public List<ProjectMemberDutyModel> getTalkMemberDuty(int talkId) {
        try (SqlSession session = getSession()) {

            ProjectMemberDutyMapper dutyMapper = session.getMapper(ProjectMemberDutyMapper.class);
            return dutyMapper.getTalkMemberDuty(talkId);

        }

    }

    @Override    //通过岗位获取任务
    public List<ProjectMemberTask> getProjectMemberTasks(int memberDutyId) {
        try (SqlSession session = getSession ()) {
            ProjectMemberTaskMapper memberTaskMapper = session.getMapper(ProjectMemberTaskMapper.class);
            return memberTaskMapper.getProjectMemberTasks(memberDutyId);
        }
    }


    //添加任务
    public void addProjectMemberTask(ProjectMemberTask projectMemberTask) {
        try (SqlSession session = getSession ()) {

            ProjectMemberTaskMapper pMTMapper = session.getMapper(ProjectMemberTaskMapper.class);
            pMTMapper.addProjectMemberTask(projectMemberTask);
            session.commit();
        }

        }

    //删除岗位任务
    public void delProjectMemberTask(int mDutyTaskId) {

        try(SqlSession session = getSession ()) {

            ProjectMemberTaskMapper pMTMapper = session.getMapper(ProjectMemberTaskMapper.class);
            pMTMapper.delProjectMemberTask(mDutyTaskId);
            session.commit();
        }

    }


    public ProjectMemberTask getPhaseTask(String taskName, int projectId) {
        try(SqlSession session = getSession ()) {

            ProjectMemberTaskMapper projectMemberTaskMapper = session.getMapper(ProjectMemberTaskMapper.class);
            return projectMemberTaskMapper.getPhaseTask(taskName, projectId);
        }
    }

    public void modifyPhaseTask(ProjectMemberTask projectMemberTask) {

        try(SqlSession session = getSession ()) {

            ProjectMemberTaskMapper projectMemberTaskMapper = session.getMapper(ProjectMemberTaskMapper.class);
            projectMemberTaskMapper.modifyPhaseTask(projectMemberTask);
        }
    }


    //根据任务ID获取任务
    public ProjectMemberTask getProjectMemberTaskById(int mDutyTaskId) {
        try (SqlSession session = getSession ()) {

            ProjectMemberTaskMapper pMTMapper = session.getMapper(ProjectMemberTaskMapper.class);
            return pMTMapper.getProjectMemberTaskById(mDutyTaskId);

        }

    }


    //添加任务
    public int addProjectMemberTask(int projectId,
                                    String taskName,
                                    String taskRemark,
                                    int mode,
                                    int templateId,
                                    int memberDutyId) {

        ProjectMemberTask pmTask = new ProjectMemberTask();
        pmTask.setMemberDutyId(memberDutyId);
        pmTask.setProjectId(projectId);
        pmTask.setCreateTime(new Date());
        pmTask.setTaskName(taskName);
        pmTask.setTaskStatus(0);
        pmTask.setTaskRemark(taskRemark);
        pmTask.setCreateMode(mode);
        pmTask.setTemplateId(templateId);
        addProjectMemberTask(pmTask);
        return pmTask.getmDutyTaskId();
    }


    //建立工作组
    public int addWorkGroup(WorkGroupModel workGroup){
        try (SqlSession session = getSession ()) {
            WorkGroupMapper wGMapper = session.getMapper(WorkGroupMapper.class);
            wGMapper.addWorkGroup(workGroup);
            return workGroup.getGroupId();
        }
    }

    public void modifyWorkGroup(WorkGroupModel workGroup){
        try (SqlSession session = getSession ()) {
            WorkGroupMapper wGMapper = session.getMapper(WorkGroupMapper.class);
            wGMapper.modifyWorkGroup(workGroup);

        }
    }

    public WorkGroupModel getWorkGroupById(int groupId) {
        try (SqlSession session = getSession ()) {

            WorkGroupMapper wGMapper = session.getMapper(WorkGroupMapper.class);
            return wGMapper.getWorkGroupById(groupId);

        }
    }


    public WorkGroupModel buildWorkGroup(UserProjectContext context) {
        WorkGroupModel workGroup = new WorkGroupModel();
        workGroup.setProjectId(context.getProjectModel().getProjectId());
        //临时
        workGroup.setDocumentId(context.getProjectTalkModel().getUserId2());
        workGroup.setTalkId(context.getProjectTalkModel().getTalkId());
        workGroup.setCreateTime(new Date());
        workGroup.setGroupName(context.getProjectModel().getProjectName() + "-工作组");
        workGroup.setActive(true);
        workGroup.setType(1);
        return workGroup;
    }


    public int addGroupAndUser(UserProjectContext context, int bbGoupId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        WorkGroupModel workGroup = buildWorkGroup(context);
        workGroup.setBbGroupId(bbGoupId);

        int groupId = addWorkGroup(workGroup);
        ProjectTalkModel.getWorkGroup().addWorkGroupMember(pm.getUserId1(), groupId, pm.getProjectId(), 0,
                ptm.getTalkId());
        ProjectTalkModel.getWorkGroup().addWorkGroupMember(ptm.getUserId2(), groupId, pm.getProjectId(), 1, ptm.getTalkId());
        ptm.setGroupId(groupId);
        return groupId;
    }

    public int bbAddGroupAndUser(int userId1, String groupName, int userId) {
        List<Integer> jsonArray = new ArrayList<>();
        jsonArray.add(userId1);
        int bbGroupId = getInst().getGroupService().addGroup(groupName, 1, userId);
        getInst().getGroupService().addGroupUser(userId, bbGroupId, jsonArray);
        // bangbang建立工作组
        return bbGroupId;
    }

    //return groupId
    public int createNewWorkGroup(UserProjectContext context)
    {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        WorkGroupModel workGroup = buildWorkGroup(context);
        int bbGoupId = bbAddGroupAndUser(pm.getUserId1(), workGroup.getGroupName(), ptm.getUserId2());
        return addGroupAndUser(context, bbGoupId);

    }

    public void modifyGroupMemberActive(Integer isActiveType, Integer memberId){

        try(SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true)){
            WorkGroupMemberMapper wGMMapper = session.getMapper(WorkGroupMemberMapper.class);
            wGMMapper.modifyActive(isActiveType,memberId);
        }
    }


    @Override
    public WorkGroupModel getWorkGroupByTalkId(int talkId) {
        try(SqlSession session= YtbContext.getSqlSessionBuilder().getSession_project(true)){
            WorkGroupMapper wGMMapper = session.getMapper(WorkGroupMapper.class);
           return wGMMapper.getWorkGroupByTalkId(talkId);
        }
    }

    //修改Group & member document数据
    public void modifyGroupAndMemberDocumentID(ProjectTalkModel m) {
        StringBuilder sql=new StringBuilder(128);
        sql.append(" update ytb_project.work_group ");
        sql.append(" set document_id=").append(m.getWorkplanId());
        sql.append( " where group_id = ").append(m.getGroupId());
        YtbSql.update(sql);
        sql.delete(0,sql.length());
        sql.append(" update ytb_project.work_group_member ");
        sql.append(" set document_id=").append(m.getWorkplanId());
        sql.append( " where group_id = ").append(m.getGroupId());
        YtbSql.update(sql);
    }


}
