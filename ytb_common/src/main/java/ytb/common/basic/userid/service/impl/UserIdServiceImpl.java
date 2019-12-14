package ytb.common.basic.userid.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.basic.userid.dao.UserIdMapper;
import ytb.common.basic.userid.model.User_IdModel;
import ytb.common.basic.userid.service.UserIdService;
import ytb.common.context.service.impl.YtbContext;

import java.util.UUID;

/**
 * 注：还未加入Spring
 * Package: ytb.log.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public  class UserIdServiceImpl implements UserIdService {


    @Override
    public void addUserId(User_IdModel userIdModel) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_tasklog(true);
        try {
            UserIdMapper userIdDao = sq.getMapper(UserIdMapper.class);
            userIdDao.addUserId(userIdModel);
            sq.commit();
      }finally {
            sq.close();
        }
    }

    @Override
    public User_IdModel getRecordByUuId(String uuId) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_tasklog(true);
        User_IdModel userIdModel;
        try{
            UserIdMapper userIdDao = sq.getMapper(UserIdMapper.class);
             userIdModel = userIdDao.getRecordByUuId(uuId);
        }finally {
            sq.close();
        }

        return userIdModel;
    }


    public static void main(String args[]){
        UserIdService s = new UserIdServiceImpl();
        System.out.print(UUID.randomUUID().toString().replace("-",""));
        User_IdModel userIdModel =new User_IdModel();
        userIdModel.setUuId(UUID.randomUUID().toString().replace("-",""));

        s.addUserId(userIdModel);

    }

}
