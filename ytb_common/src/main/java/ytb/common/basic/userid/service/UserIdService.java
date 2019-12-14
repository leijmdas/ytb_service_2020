package ytb.common.basic.userid.service;

import ytb.common.basic.userid.model.User_IdModel;

/**
 * Package: ytb.log.service
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public interface UserIdService {
    void addUserId(User_IdModel userIdModel);

    User_IdModel getRecordByUuId(String uuId);
}
