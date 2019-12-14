package ytb.common.basic.safecontext.service.impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.common.basic.safecontext.dao.LoginSsoMapper;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoExample;
import ytb.common.basic.safecontext.service.LoginSsoService;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;

@Service
public class LoginSsoServiceImpl implements LoginSsoService {


    @Override
    public int addLoginSso(LoginSso loginSso) {

        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.insertSelective(loginSso);
        } finally {
            session.close();
        }

    }

    @Override
    public List<LoginSso> selectByExample(LoginSsoExample example) {

        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_tasklog(true);
        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.selectByExample(example);
        } finally {
            session.close();
        }

    }

    @Override
    public int deleteByExample(LoginSsoExample example) {

        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.deleteByExample(example);
        } finally {
            session.close();
        }

    }


    @Override
    public int updateByPrimaryKeySelective(LoginSso record) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.updateByPrimaryKeySelective(record);
        } finally {
            session.close();
        }

    }

    @Override
    public int insertSelective(LoginSso record) {
        SqlSession session = YtbContext.getSqlSessionBuilder().getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.insertSelective(record);
        } finally {
            session.close();
        }

    }


}
