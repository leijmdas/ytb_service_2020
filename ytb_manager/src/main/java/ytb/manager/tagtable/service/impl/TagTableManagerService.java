package ytb.manager.tagtable.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.MyBatis.ISqlSessionBuilder;
import ytb.common.context.service.impl.YtbContext;

public class TagTableManagerService extends TagTableServiceImpl {
    String db_name= ISqlSessionBuilder.DB_NAME_MANAGER;

    private TagTableManagerService() {
    }

    @Override
    public SqlSession getSession() {
        return YtbContext.getSqlSessionBuilder().getSession_manager(true);
    }
}
