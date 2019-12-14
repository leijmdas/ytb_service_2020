package ytb.common.basic.userid.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.basic.userid.dao.BBNoMapper;
import ytb.common.basic.userid.model.BBNoModel;
import ytb.common.basic.userid.service.BBNoService;
import ytb.common.context.service.impl.YtbContext;

/**
 * Package: ytb.common.basic.userid.service.impl
 * Author: ZCS
 * Date: Created in 2018/10/16 19:02
 */
public class BBNoServiceImpl implements BBNoService {
    @Override
    public void addBBNOInfo(BBNoModel bbNoModel) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_tasklog(true);
        try{
          BBNoMapper bbNoDao = sq.getMapper(BBNoMapper.class);
            bbNoDao.addBBNOInfo(bbNoModel);
            sq.commit();
        }finally {
            sq.close();
        }
    }

    @Override
    public BBNoModel getBBNoByUUId(String UUId) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_tasklog(true);
        try {

            BBNoMapper bbNoDao = sq.getMapper(BBNoMapper.class);

            return bbNoDao.getBBNoByUUId(UUId);
        }finally {
            sq.close();
        }

    }
}
