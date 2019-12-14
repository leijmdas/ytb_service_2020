package ytb.account.wallet.service.service.getUserInfo;

import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.account.wallet.pojo.LoginSsoToInnerId;

public class ObtainAccountId {

    public LoginSsoToInnerId ssoJsonToInnerId(LoginSsoJson loginSsoJson) {

        LoginSsoToInnerId loginSsoToInnerId = new LoginSsoToInnerId();
        if (loginSsoJson.getUserType() != null) {
           // loginSsoToInnerId.setUserType(loginSsoJson.getUserType());
           /* if (loginSsoJson.getUserType().equals(100)||loginSsoJson.getUserType().equals(1)) {

                loginSsoToInnerId.setUserId(loginSsoJson.getUserId());
                loginSsoToInnerId.setCompanyId(0);

            } else if (loginSsoJson.getUserType().equals(200)||loginSsoJson.getUserType().equals(2)) {


                loginSsoToInnerId.setCompanyId(loginSsoJson.getCompanyId());
                loginSsoToInnerId.setUserId(0);

            } else {
                return null;
            }*/

          //  loginSsoToInnerId.setCompanyId(loginSsoJson.getCompanyId());
            loginSsoToInnerId.setUserId(loginSsoJson.getUserId());
            loginSsoToInnerId.setUserType(loginSsoJson.getUserType());
            loginSsoToInnerId.setCompanyId(loginSsoJson.getCompanyId());

        } else {
            return null;
        }

        //**        if (loginSsoJson.getUserType().equals(100)) {
        //            criteria.andUserIdEqualTo(loginSsoJson.getUserId());
        //            criteria.andCompanyIdEqualTo(0);
        //        } else if (loginSsoJson.getUserType().equals(200)) {
        //            criteria.andCompanyIdEqualTo(loginSsoJson.getCompanyId());
        //            criteria.andUserIdEqualTo(0);
        //        } else {
        //            return null;
        //        }*/

        return loginSsoToInnerId;
    }

}
