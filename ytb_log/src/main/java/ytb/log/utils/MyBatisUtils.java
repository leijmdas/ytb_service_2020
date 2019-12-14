package ytb.log.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

/**
 * YTB_log的mybatis工具类
 * Package: ytb.activiti.tools
 * Author: ZCS
 */
public abstract class MyBatisUtils {
    private final static String resourceName = "dbconfig/log/TaskLogMyBatisConfig.xml";
    private static SqlSessionFactory sqlSessionFactory = null;
    private static ReentrantLock lock = new ReentrantLock();

    public static SqlSession getSession()  {
        try {
            return getSession(true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,e.getMessage());
        }
    }


    public static SqlSession getSession(boolean isAutoCommit) throws IOException {

        return getSqlSessionFactory().openSession(isAutoCommit);
    }

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        if (sqlSessionFactory == null) {

            try (InputStream config = MyBatisUtils.class.getClassLoader()
                    .getResourceAsStream(resourceName)) {
                lock.lock();
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
            } finally {
                lock.unlock();
            }

        }
        return sqlSessionFactory;
    }

}
