package ytb.common.context.service.impl;

import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.context.model.Ytb_Model;
import ytb.common.context.service.IUserContext;

public class DefaultUserContext extends Ytb_Model implements IUserContext {
    LoginSso sso;
    Boolean testFlag;


    @Override
    public Boolean getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Boolean testFlag) {
        this.testFlag = testFlag;
    }


    @Override
    public LoginSso getLoginSso() {
        return sso;
    }

    @Override
    public void setLoginSso(LoginSso sso) {
        this.sso = sso;

    }
}
