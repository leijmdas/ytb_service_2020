package ytb.common.context.service;

import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safelog.service.Tasklog_UserServiceImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.service.impl.YtbContext;

public interface IUserContext {
    Boolean getTestFlag();

    void setTestFlag(Boolean testFlag);

    LoginSso getLoginSso();

    static Tasklog_UserServiceImpl u = new Tasklog_UserServiceImpl();

    void setLoginSso(LoginSso sso);

    default boolean isUserManager() {
        return getLoginSso().isUserManager();
    }
    default boolean isTest() {
        return getLoginSso().isTest();
    }

    default void checkUserRightValid(MsgRequest req) {
        YtbContext.getSafeContext().checkUserRightValid(this, req);
    }

    default int insertUserLog(MsgRequest req) {
        return 0;//u.insertUserLog(this, req);
    }

}
