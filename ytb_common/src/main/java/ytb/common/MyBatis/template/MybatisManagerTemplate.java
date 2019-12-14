package ytb.common.MyBatis.template;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ytb.common.basic.safecontext.dao.LoginSsoMapper;
import ytb.common.basic.safecontext.model.LoginSso;

import java.util.List;

@Service
public class MybatisManagerTemplate {
    //注入以后就可以直接使用sqlsession
 //   @Qualifier("sqlSessionTemplate")

    //@Resource
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlsession;
    private String className = LoginSsoMapper.class.getName();

    @Autowired
    public MybatisManagerTemplate(@Qualifier("sqlSessionTemplate") SqlSessionTemplate sqlsession) {
        this.sqlsession = sqlsession;
    }

    //用sqlsession去操作数据库
    public LoginSso selectOne(int id) {
        return sqlsession.selectOne(className+".selectByPrimaryKey", id);
    }


    public   List<LoginSso> selectList(int id) {
        return sqlsession.selectList(className+".selectList", id);
    }

}