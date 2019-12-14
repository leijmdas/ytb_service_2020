package ytb.manager.sysuser.service.impl;

import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.context.service.IUserContext;
import ytb.common.context.service.impl.DefaultUserContext;

public class SysUserContext extends DefaultUserContext implements IUserContext {
    LoginSso sso;

    @Override
    public LoginSso getLoginSso() {
        return sso;
    }

    @Override
    public void setLoginSso(LoginSso sso) {
        this.sso = sso;
    }
}
