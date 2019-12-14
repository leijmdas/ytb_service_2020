package ytb.manager.template.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.template.dao.TStopActionMapper;
import ytb.manager.template.model.T_Stop_ActionModel;

import java.util.List;

public class T_Stop_ActionServiceImpl implements TStopActionMapper {
    final static String tblName = "ytb_manager.t_stop_action";
    @Override
    public List<T_Stop_ActionModel> getList(int templateId) {
        StringBuilder sql=new StringBuilder(128);
        sql.append(" select * from  " ).append(tblName);
        sql.append(" where template_id=#{templateId} ");
        return YtbSql.selectList(sql,templateId,T_Stop_ActionModel.class);
    }

    @Override
    public T_Stop_ActionModel get(int actionId) {
        StringBuilder sql=new StringBuilder(128);
        sql.append(" select * from  " ).append(tblName);
        sql.append(" where action_id=#{actionId} ");
        return YtbSql.selectOne(sql,actionId,T_Stop_ActionModel.class);
    }

    @Override
    public int add(T_Stop_ActionModel sam) {
        StringBuilder sql=new StringBuilder(256);
        sql.append(" insert into  " ).append(tblName);
        sql.append("(template_id,penalty_rate,c_phase,stop_action,stop_q,stop) " );
        sql.append(" values(#{templateId},#{penaltyRate}, #{cPhase},#{stopAction},#{stopQ},#{stop})");
        return YtbSql.insert(sql,sam);
    }

    @Override
    public void modify(T_Stop_ActionModel sam) {
        StringBuilder sql=new StringBuilder(256);
        sql.append(" update " ).append(tblName);
        sql.append(" set template_id=#{templateId} ");
        sql.append(" , c_phase=#{cPhase},penalty_rate=#{penaltyRate} ");
        sql.append(" , stop_action=#{stopAction} ");
        sql.append(" , stop_q=#{stopQ} ");
        sql.append(" , stop=#{stop} ");
        sql.append(" where action_id=#{actionId} ");
        YtbSql.update(sql,sam);

    }


    @Override
    public void del(int actionId) {
        StringBuilder sql=new StringBuilder(128);
        sql.append(" delete from  " ).append(tblName);
        sql.append(" where action_id=#{actionId} ");
        YtbSql.delete(sql,actionId);
    }



}
