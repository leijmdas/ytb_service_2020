package ytb.common.basic.config.service;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.common.basic.config.dao.ConfigMapper;
import ytb.common.basic.config.dao.ErrorCodeMapper;
import ytb.common.basic.config.dao.SubSysMapper;
import ytb.common.basic.config.model.Dict_ConfigModel;
import ytb.common.basic.config.model.Dict_ErrorCode;
import ytb.common.basic.config.model.SubSysDictModel;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;

public class ConfigDAOService {
    SqlSession getSession() {
        return YtbContext.getSqlSessionBuilder().getSession_common(true);

    }

    public List<Dict_ConfigModel> getDictConfig() {

        try (SqlSession session = getSession()) {
            ConfigMapper configMapper = session.getMapper(ConfigMapper.class);
            return configMapper.getConfig();
        }

    }

    public List<Dict_ErrorCode> getDictErrorCode() {

        try (SqlSession session = getSession()) {
            ErrorCodeMapper m = session.getMapper(ErrorCodeMapper.class);
            return m.getErrorMsg();
        }

    }

    //ip port
    public List<SubSysDictModel> getSubSysDict() {
        try (SqlSession session = getSession()) {

            SubSysMapper ssm = session.getMapper(SubSysMapper.class);
            return ssm.getSubsys_address();

        }

    }


    public List<Dict_ConfigModel> selectDictConfig() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from dict_config");
        sql.append(" order by config_type,config_item");
        return YtbSql.selectList(sql, Dict_ConfigModel.class);

    }
}
