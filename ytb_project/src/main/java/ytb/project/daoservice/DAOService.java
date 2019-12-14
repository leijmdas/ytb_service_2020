package ytb.project.daoservice;

import org.apache.ibatis.session.SqlSession;
import ytb.account.context.AccountSrvContext;
import ytb.manager.context.ManagerSrvContext;
import ytb.project.context.ProjectSrvContext;
import ytb.user.context.UserSrvContext;
import ytb.common.context.service.impl.YtbContext;

public class DAOService {
    public static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }

    public static UserSrvContext getInstUser() {
        return UserSrvContext.getInst();
    }

    public static ManagerSrvContext getInstMngr() {
        return ManagerSrvContext.getInst();
    }

    public static AccountSrvContext getInstAccount() {
        return AccountSrvContext.getInst();
    }

    protected SqlSession getSession() {
        return getSession(true);
    }

    protected SqlSession getSession(boolean isAutoCommit) {
        return YtbContext.getSqlSessionBuilder().getSession_project(isAutoCommit);
    }

}
