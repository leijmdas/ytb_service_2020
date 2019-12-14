package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.UserFriendsDao;
import ytb.bangbang.service.SetUserRemarksService;
import ytb.bangbang.util.MyBatisUtil;


/**
 * 设置用户备注信息
 */
public class SetUserRemarksServiceImpl  implements SetUserRemarksService {
    @Override
    public int  setUserRemarks(int userId,int friendId,String remarks) {

        SqlSession session=null;
        try{
            session= MyBatisUtil.getSession();
            if (friendId == 0 && remarks == null && !remarks.isEmpty()) {
                //参数有误
                return 6;
            }
            UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
            userFriendsDao.setUserRemarks(userId,friendId,remarks);
            session.close();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return 1;
        }finally{
            if (session!=null)
            session.close();
        }
    }
}
