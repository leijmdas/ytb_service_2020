package ytb.common.basic.safelog.dao;

import ytb.common.basic.safelog.model.Tasklog_UserModel;
import ytb.common.RestMessage.MsgRequest;

public interface ITasklog_UserService {

    int insert(Tasklog_UserModel model);

    int insertUserLog(MsgRequest req);

}
