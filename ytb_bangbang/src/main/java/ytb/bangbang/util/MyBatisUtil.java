package ytb.bangbang.util;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;

/**
 * @Author hj
 * @Description //
 * @Date 2018/8/31
 **/
public class MyBatisUtil {

    public static SqlSession  getSession(){
        return getSession(true);
    }


    public static SqlSession getSession(boolean isAutoCommit){
        return YtbContext.getSqlSessionBuilder().getSession_bangbang(isAutoCommit);
    }

}
