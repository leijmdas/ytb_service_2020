package ytb.common.basic.safecontext.dao;

import org.apache.ibatis.annotations.Param;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoExample;

import java.util.List;

public interface LoginSsoMapper {
    long countByExample(LoginSsoExample example);

    int deleteByExample(LoginSsoExample example);

    int deleteByPrimaryKey(Integer ssoid);

    int insert(LoginSso record);

    int insertSelective(LoginSso record);

    List<LoginSso> selectByExample(LoginSsoExample example);

    LoginSso selectByPrimaryKey(Integer ssoid);
    List<LoginSso> selectList(Integer ssoid);

    int updateByExampleSelective(@Param("record") LoginSso record, @Param("example") LoginSsoExample example);

    int updateByExample(@Param("record") LoginSso record, @Param("example") LoginSsoExample example);

    int updateByPrimaryKeySelective(LoginSso record);

    int updateByPrimaryKey(LoginSso record);
}