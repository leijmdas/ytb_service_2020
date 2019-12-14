package ytb.manager.pfUser.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.pfUser.dao.PlatformUserManagerMapper;
import ytb.manager.pfUser.service.IPlatformUserManagerService;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.pfUser.service.impl
 * Author: ZCS
 * Date: Created in 2018/12/5 13:29
 */
public class PlatformUserManagerServiceImpl implements IPlatformUserManagerService {


    @Override
    public List<Map<String, Object>> getPlatformUserList(Map<String,Object> map) {

        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            PlatformUserManagerMapper puDao = s.getMapper(PlatformUserManagerMapper.class);
            return puDao.getPlatformUserList(map);
        } finally {
            s.close();
        }
    }

    @Override
    public int getPlatformUserTotal(String nickName) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            PlatformUserManagerMapper puDao = s.getMapper(PlatformUserManagerMapper.class);
            return puDao.getPlatformUserTotal(nickName);
        } finally {
            s.close();
        }
    }

    @Override
    public List<Map<String, Object>> getPlatformCompanyList(Map<String, Object> map) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            PlatformUserManagerMapper puDao = s.getMapper(PlatformUserManagerMapper.class);
            return puDao.getPlatformCompanyList(map);
        } finally {
            s.close();
        }
    }

    @Override
    public int getPlatformCompanyTotal(String nickName) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            PlatformUserManagerMapper puDao = s.getMapper(PlatformUserManagerMapper.class);
            return puDao.getPlatformCompanyTotal(nickName);
        } finally {
            s.close();
        }
    }
}
