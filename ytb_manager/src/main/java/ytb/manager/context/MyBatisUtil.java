package ytb.manager.context;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;

public abstract  class MyBatisUtil {

    public static SqlSession  getSession(){

        return getSession(true);
    }
    
    /**
     *
     * @param isAutoCommit  Ϊfalse ҪsqlSession.commit();rollback();
     * @return sql
     */
    public static SqlSession getSession(boolean isAutoCommit){
        return YtbContext.getSqlSessionBuilder().getSession_manager(isAutoCommit);
    }


    public void closeSession(){
    }
}
