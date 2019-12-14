package ytb.manager.tagtable.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.ytblog.YtbLog;
import ytb.manager.tagtable.daoservice.WorkGroupMemberModelServiceImpl;
import ytb.manager.tagtable.model.business.WorkGroupMemberModel;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * author: leijianming
 * date: 2010-11-2
 * 通过工作计划书导出工作任务与计划一系列表
 * */
public class TagTableWorkGroup extends TagTableServiceImpl {

    String TABLE_NAME_dict_document = "ytb_manager.dict_document";
    String TABLE_NAME_project_document = "ytb_project.project_document";
    String TABLE_NAME_project_workjob = "ytb_project.project_workjob";

    String TABLE_NAME_work_group = "ytb_project.work_group";
    String TABLE_NAME_work_group_member = "ytb_project.work_group_member";
    String TABLE_NAME_project_member_duty = "ytb_project.project_member_duty";
    String TABLE_NAME_project_member_task = "ytb_project.project_member_task";

    int project_id;
    int talk_id;
    int document_id;
    int group_id;
    Boolean isPa = false;

    //导出一表
    public int exportTableWorkplanView(TemplateDocumentInfo documentInfo, int project_id, int document_id) {

        this.project_id = project_id;
        this.document_id = document_id;

        StringBuilder sql = new StringBuilder(128);
        sql.append("call ytb_project.spCreateworkplan_view(");
        sql.append(project_id).append(",");
        sql.append(document_id).append(")");

        return YtbSql.update(sql);

    }


    //   通过查询work_group_task表，生成work_group等表
    public int exportTableWorkGroupTaskAll(TemplateDocumentInfo info, int project_id, int document_id)
            throws IOException, Exception {

        this.project_id = project_id;
        this.document_id = document_id;
        this.talk_id = info.getdBDocHeader().getTalkId();
        this.group_id = info.getdBDocHeader().getGroupId();
        this.isPa = info.isPa();
        SqlSession session = getSession(false);
        try {
            deleteTableWorkgroupTaskAll(session, project_id, document_id, group_id);
            group_id =  exportTableWorkGroupTaskAll(session,info,project_id,document_id);
            session.commit();
        } catch (Exception se) {
            session.rollback();
            se.printStackTrace();
            throw se;
        } finally {
            session.close();
        }
        return group_id;
    }

    public int exportTableWorkGroupTaskAll(SqlSession session, TemplateDocumentInfo info, int project_id,
                                           int document_id) throws IOException, Exception {
        List<Map<String, Object>> workGroupTasks = selectTableWorkGroupTask(session, project_id, document_id);
        if (workGroupTasks.size() == 0) {
            session.rollback();
            throw new YtbError(YtbError.CODE_WORKPLAN_DOC_NO_USER, " document_id=" + document_id);
        }
        if (group_id > 0) {
            exportTableWorkGroupTask_update(session, group_id);
        } else {
            group_id = exportTableWorkGroupTask(session);
        }
        exportTableMember(info,session, workGroupTasks);
        Map<Integer, Object> task = exportTableMemberDuty(session);
        exportTableMemberTask(session, task);
        return group_id;
    }


    int deleteTableWorkgroupTaskAll(SqlSession session, int project_id, int document_id, int group_id) throws IOException {
        if (group_id > 0) {
            deleteTableMember(session, group_id, false);
        } else {
            List<Map<String, Object>> r = selectTable(session, project_id, document_id, "work_group");
            if (r.size() == 0) {
                return 0;
            }
            String sgroup_id = r.get(0).get("group_id").toString();
            group_id = Integer.valueOf(sgroup_id);
            deleteTableMember(session, group_id, true);

            StringBuilder sql = new StringBuilder();
            sql.append(" delete from ").append(TABLE_NAME_work_group);
            sql.append(" where group_id=").append(group_id);
            updateSql(session, sql);
        }
        return group_id;
    }

    int deleteTableMember(SqlSession session, int group_id, boolean isDelMember) throws IOException {
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" select member_id from ").append(TABLE_NAME_work_group_member);
        sql1.append(" where group_id=").append(group_id);
        List<Map<String, Object>> memberLst = selectTable(session, sql1);
        for (Map<String, Object> m : memberLst) {
            String member_id = m.get("member_id").toString();
            deleteTableMemberDuty(session, member_id);
        }
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(TABLE_NAME_work_group_member);
        sql.append(" where group_id=").append(group_id);
        if (!isDelMember) {
            sql.append(" and is_admin>=2");
        }
        return updateSql(session, sql);
    }


    int deleteTableMemberDuty(SqlSession session, String member_id) throws IOException {
        StringBuilder sql1 = new StringBuilder();
        sql1.append("select member_duty_id from ").append(TABLE_NAME_project_member_duty);
        sql1.append(" where member_id=").append(member_id);
        List<Map<String, Object>> dutyLst = selectTable(session, sql1);
        for (Map<String, Object> m : dutyLst) {
            String member_duty_id = m.get("member_duty_id").toString();
            deleteTableMemberDutyTask(session, member_duty_id);
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from ").append(TABLE_NAME_project_member_duty);
        sql.append(" where member_id=").append(member_id);
        return updateSql(session, sql);
    }


    int deleteTableMemberDutyTask(SqlSession session, String member_duty_id) throws IOException {

        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(TABLE_NAME_project_member_task);
        sql.append(" where member_duty_id=").append(member_duty_id);
        return updateSql(session, sql);
    }

    //通过查询work_group_task表，生成work_group等表
    public int exportTableWorkGroupTask_update(SqlSession session, int group_id) throws IOException {
        StringBuilder sql = new StringBuilder();
        sql.append("update  ").append(TABLE_NAME_work_group);
        sql.append(" set  document_id=").append(document_id);
        sql.append(" where group_id=").append(group_id);
        return updateSql(session, sql);
    }


    //通过查询work_group_task表，生成work_group等表
    public int exportTableWorkGroupTask(SqlSession session) throws IOException {
        //  查询work_group_task表条件 projectId,documentId
        List<Map<String, Object>> rs = selectTable(session, "ytb_project.project", "project_id=" + project_id);
        String group_name = "" + document_id;

        if (rs.size() > 0) {
            group_name = rs.get(0).get("project_name").toString();
        }
        JSONObject r = JSONObject.parseObject("{}");
        r.put("group_name", group_name);
        r.put("project_id", project_id);
        r.put("document_id", document_id);
        r.put("talk_id", 0);
        r.put("create_by", 0);
        r.put("create_time", "now()");
        StringBuilder sql = makeSql_insert(TABLE_NAME_work_group, r);
        return updateSql(session, sql);
    }

    void exportTableMember_updateDocumentIdByTalk(SqlSession session) throws IOException {
        StringBuilder sql = new StringBuilder();
        sql.append(" update  ").append(TABLE_NAME_work_group_member);
        sql.append(" set document_id=").append(document_id);
        sql.append(" where talk_id=").append(talk_id);
        updateSql(session,sql);
    }

//        Map<Integer, ExportMemberModel> users = new HashMap<>();
//        for (ExportMemberModel model : modelList) {
//            if (!users.containsKey(model.getUserId())) {
//                users.put(model.getUserId(), model);
//            }
//        }
//        modelList = new ArrayList<>(users.values());

    public Map<Integer, Integer> exportTableMember(TemplateDocumentInfo documentInfo, SqlSession session, List<Map<String, Object>> taskList0) throws IOException, InvocationTargetException, NoSuchMethodException {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.project_id,");
        sql.append(" a.document_id,a.user_id,");

        sql.append(" max(a.is_admin) as is_admin, ");

        sql.append(" ifnull(status,0) as is_active ");

        sql.append(" from ytb_project.work_group_task a");
        sql.append(" left join  ytb_project.talk_invite_status b using(user_id) ");

        sql.append(" where a.project_id=").append(project_id);
        sql.append(" and a.document_id=").append(document_id);

        sql.append(" and a.user_id > 0");
        sql.append(" and (a.is_admin=1 or ( (ifnull(b.project_id, a.project_id) =  ").append(project_id);
        sql.append("   and ifnull(b.document_id, a.document_id) = ").append(document_id);
        sql.append(" ))) ");

        sql.append(" group by a.project_id,a.document_id,a.user_id");//     YtbUtils.testLog(sql);
        List<WorkGroupMemberModel> modelList = YtbSql.selectList(session, sql, WorkGroupMemberModel.class);

        AtomicInteger pbManagerId = new AtomicInteger();
        List<Integer> groupUserIds = new ArrayList<>();
        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (WorkGroupMemberModel memberModel : modelList) {
            memberModel.setProjectId(project_id);
            memberModel.setTalkId(talk_id);
            memberModel.setGroupId(group_id);
            memberModel.setDocumentId(document_id);
            memberModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
            boolean isAdmin = memberModel.getIsAdmin()==1;

            if (isAdmin) {
                pbManagerId.set(memberModel.getUserId());
            } else {
                memberModel.setIsAdmin((byte) 2);
                memberModel.setIsActive((byte) (memberModel.getIsActive() == 2 ? 1 : 0));
                int memberId = new WorkGroupMemberModelServiceImpl().insert(session, memberModel);
                groupUserIds.add(memberModel.getUserId());
                result.put(memberModel.getUserId(), memberId);
            }
        }

        exportTableMember_updateDocumentIdByTalk(session);
        //不是加工采购项目需要在工作计划书中导出组员 不是乙方 不是有项目>0不需要导出！
        if (documentInfo.isTemplate_TYPE_workplan_NonePp() && project_id > 0 && !isPa) {
            new BangBangGroupMember().exportMember(pbManagerId.get(), group_id, groupUserIds);
        }
        return result;
    }

    Map<Integer, Object> exportTableMember_nouse(
            TemplateDocumentInfo documentInfo, SqlSession session, List<Map<String, Object>> taskList0) throws IOException, InvocationTargetException, NoSuchMethodException {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.project_id,a.document_id,a.user_id,");
        sql.append(" max(a.is_admin) as is_admin, ");
        sql.append(" ifnull(status,0) as is_active ");
        sql.append(" from ytb_project.work_group_task a");
        sql.append(" left join  ytb_project.talk_invite_status b using(user_id) ");
        sql.append(" where a.project_id=").append(project_id);
        sql.append(" and a.document_id=").append(document_id);
        sql.append(" and a.user_id > 0"); //负责人不转
        sql.append(" and (a.is_admin=1 or ");
        sql.append("     ( (ifnull(b.project_id, a.project_id) =  ").append(project_id);

        sql.append("     and ifnull(b.document_id, a.document_id) = ").append(document_id).append("))) ");
        sql.append(" group by a.project_id,a.document_id,a.user_id");

        YtbLog.logDebug(sql);
        List<Map<String, Object>> taskList = selectTable(session, sql);
        Map<Object,Map<String, Object>> users=new HashMap<>();
        for(Map<String, Object> map:taskList) {
            if(!users.containsKey((int)map.get("user_id")))
            users.put((int)map.get("user_id"),map);
        }
        taskList = new ArrayList<>(users.values());
        YtbLog.logDebug(YtbUtils.toJSONStringPretty(taskList));

        //memeber_id->user_id
        Map<Integer, Object> map = new LinkedHashMap<>();
        JSONObject r = JSONObject.parseObject("{}");
        r.put("project_id", project_id);
        r.put("talk_id", talk_id);
        r.put("group_id", group_id);
        r.put("document_id", document_id);
        r.put("create_time", "now()");
        AtomicInteger ownerId = new AtomicInteger();
        List<Integer> groupUserIds = new ArrayList<>();
        taskList.stream().forEach(
                x -> {
                    boolean isAdmin = (boolean) x.get("is_admin");
                    if (isAdmin) {
                        ownerId.set((int) x.get("user_id"));
                    } else {
                        JSONObject rr = (JSONObject) r.clone();

                        rr.put("user_id", x.get("user_id"));
                        groupUserIds.add((int)x.get("user_id"));
                        rr.put("is_admin", 2);
                        rr.put("is_active", x.get("is_active").toString() == "2" ? 1 : 0);
                        StringBuilder sql0 = makeSql_insert(TABLE_NAME_work_group_member, rr);
                        int id = updateSql(session, sql0);
                        map.put(id, x.get("user_id"));
                    }
                });

        // exportTableMember_updateDocumentId(session);
        //不是加工采购项目需要在工作计划书中导出组员 不是乙方 不是有项目>0不需要导出！
        if ( documentInfo.isTemplate_TYPE_workplan_NonePp() && project_id > 0 && !isPa ) {
            new BangBangGroupMember().exportMember(ownerId.get(),group_id,groupUserIds);
        }
        return map;
    }


    public Map<Integer, Object> exportTableMemberDuty(SqlSession session) throws IOException {

        StringBuilder sql = new StringBuilder();
        sql.append(" select b.talk_id,a.member_id,a.group_id,a.project_id,a.document_id,a.user_id,b.work_job_id,b.work_task ");
        sql.append(" from ytb_project.work_group_member a,ytb_project.work_group_task b");
        sql.append(" where a.project_id=b.project_id ");
        sql.append(" and a.document_id=b.document_id").append(" and b.work_job_id>0 ");
        sql.append(" and a.user_id=b.user_id").append(" and group_id=").append(group_id);
        YtbLog.logDebug("********exportTableMemberDuty sql:"+sql);
        List<Map<String, Object>> taskList = selectTable(session, sql);
        Map<Integer, Object> map = new HashMap<>();
        taskList.stream().forEach(
                x -> {
                    JSONObject rr = JSONObject.parseObject("{}");
                    rr.put("member_id", x.get("member_id"));
                    rr.put("work_job_id", x.get("work_job_id"));
                    rr.put("talk_id", x.get("talk_id"));
                    StringBuilder sql1 = makeSql_insert(TABLE_NAME_project_member_duty, rr);
                    int member_duty_id = updateSql(session, sql1);
                    map.put(member_duty_id, x.get("work_task"));
                }
        );
        return map;
    }


    public void exportTableMemberTask(SqlSession session, Map<Integer, Object> task) throws IOException {

        for (Map.Entry<Integer, Object> e : task.entrySet()) {
            JSONObject rr = JSONObject.parseObject("{}");
            rr.put("member_duty_id", e.getKey());
            rr.put("project_id", project_id);
            for (String t : e.getValue().toString().split(",|;|；")) {
                rr.put("task_name", t);
                StringBuilder sql = makeSql_insert(TABLE_NAME_project_member_task, rr);
                int member_duty_id = updateSql(session, sql);
            }
        }
    }

    public <T> List<T> selectList(SqlSession session, StringBuilder sql, Class<T> cls) {
        SqlMapper m = new SqlMapper(session);
        return m.selectList(sql.toString(), cls);
    }

    List<Map<String, Object>> selectTable(SqlSession session, String tableName, String where) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from " + tableName);
        sql.append(" where ").append(where);
        return selectTable(session, sql);
    }

    List<Map<String, Object>> selectTable(SqlSession session, StringBuilder sql) {
        SqlMapper m = new SqlMapper(session);
        return m.selectList(sql.toString());
    }

    public List<Map<String, Object>> selectTableWorkGroupTask(SqlSession session, int projectId, int documentId) {
        return selectTable(session, project_id, document_id, TableName_work_group_task);
    }

    public Integer selectUserId1(int project_id) throws IOException {
        StringBuilder sql = new StringBuilder();
        sql.append(" select user_id1 from ytb_project.project  ");
        sql.append(" where project_id=").append(project_id);
        return YtbSql.selectOne(sql, Integer.class);
    }

    //SafeContext 加信用等级credit_grade
    public Collection<SelectProjectMember.DayPayInfoResult> selectProjectMember(LoginSso sso,   int projectId,
                                                                                int documentId) throws IOException {

        return SelectProjectMember.selectList(projectId, documentId, sso.getLoginSsoJson());

    }


    public List<Map<String, Object>> spInitWorkplanWorkjob(int projectId, int
            documentId, long userIdPb, String nickNamePb) {
        return YtbSql.spDb("ytb_project.spInitWorkplanWorkjob", projectId, documentId, userIdPb, nickNamePb);
    }
}
