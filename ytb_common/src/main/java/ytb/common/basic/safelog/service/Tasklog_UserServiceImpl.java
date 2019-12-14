package ytb.common.basic.safelog.service;

import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.basic.safelog.model.Tasklog_UserModel;
import ytb.common.basic.safelog.dao.ITasklog_UserService;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.utils.YtbSql;
import ytb.common.context.service.IUserContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public final class Tasklog_UserServiceImpl implements ITasklog_UserService {

    @Override
    public int insert(Tasklog_UserModel model) {
        if(model.getOprtTime()==null){
            model.setOprtTime(Timestamp.valueOf( LocalDateTime.now()));
        }
        if(model.getOprtDate()==null){
            model.setOprtDate(new Date());
        }
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ytb_tasklog.tasklog_user");
        sql.append("(seq_no,user_id, user_ip, oprt_type, oprt_name, ret_code, oprt_date, oprt_time) ");
        sql.append(" VALUES(#{seqNo},#{userId}, #{userIp}, #{oprtType}, #{oprtName}, #{retCode}, #{oprtDate}, #{oprtTime})") ;
        return YtbSql.insert(sql, model);
    }


    public int selectActiveUser(Tasklog_UserModel model) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ytb_tasklog.tasklog_user ");
        sql.append("(user_id, user_ip, oprt_type, oprt_name, ret_code, oprt_date, oprt_time) ");
        sql.append(" VALUES (#{userId}, #{userIp}, #{oprtType}, #{oprtName}, #{retCode}, #{oprtDate}, #{oprtTime})") ;
        return YtbSql.insert(sql, model);
    }


    public int insertUserLog(MsgRequest req) {
        if (req.token != null) {
            LoginSso sso = SafeContext.getLog_sso_catch(req.token);
            if (sso != null) {
                Tasklog_UserModel m = new Tasklog_UserModel();
                m.setOprtName(req.cmdtype + "::" + req.cmd);
                m.setUserId(sso.getUserId());
                m.setUserIp(sso.getLoginIp());
                return  insert(m);
            }
        }
        return -1;
    }
    public int insertUserLog(IUserContext userContext,MsgRequest req) {
        LoginSso sso = SafeContext.getLog_sso_catch(req.token);
        if (userContext.getLoginSso() != null) {
            Tasklog_UserModel m = new Tasklog_UserModel();
            m.setOprtName(req.cmdtype + "::" + req.cmd);
            m.setUserId(sso.getUserId()==null?0:sso.getUserId());
            m.setUserIp(sso.getLoginIp()==null?"":sso.getLoginIp());
            m.setSeqNo(req.getSeqno());
            return insert(m);

        }
        return -1;
    }

}
