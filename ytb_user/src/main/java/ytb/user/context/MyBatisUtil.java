package ytb.user.context;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;

public abstract  class MyBatisUtil {

    public static SqlSession  getSession(){
        return getSession(true);
    }
    

    public static SqlSession getSession(boolean isAutoCommit){
        return YtbContext.getSqlSessionBuilder().getSession_user(isAutoCommit);
    }

}
