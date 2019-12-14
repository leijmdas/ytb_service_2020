package ytb.common.MyBatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public final class SqlSessionFactory {
    private org.apache.ibatis.session.SqlSessionFactory factory;
    String name;

    public SqlSessionFactory(String name)
    {
        this.name = name;
    }

    public org.apache.ibatis.session.SqlSessionFactory getSqlSessionFactory() throws IOException {
        if (factory == null) {
            try (InputStream config = SqlSessionFactory.class.getClassLoader().getResourceAsStream(name)){
                factory = new SqlSessionFactoryBuilder().build(config);
            }
        }
        return factory;
    }


    public   SqlSession getSession() throws IOException {
        return getSqlSessionFactory().openSession(true);
    }

    public   SqlSession getSession(boolean isAutoCommit) throws IOException {
        return getSqlSessionFactory().openSession(isAutoCommit);
    }


    public void closeSession(){

    }
}
