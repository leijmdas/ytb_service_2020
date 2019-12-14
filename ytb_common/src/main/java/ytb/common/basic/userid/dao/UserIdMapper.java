package ytb.common.basic.userid.dao;


import ytb.common.basic.userid.model.User_IdModel;

/**
 * Package: ytb.log.dao
 * Author: ZCS
 * Date: Created in 2018/8/23 16:30
 */
public interface UserIdMapper {

    void addUserId(User_IdModel userIdModel);

    User_IdModel getRecordByUuId(String uuId);

}


