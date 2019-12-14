package ytb.account.wallet.pojo;

import ytb.user.model.CompanyInfoModel;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.account.wallet.model.AccountUserInner;

import java.util.List;

public class AccountInfoList {

    private UserLoginModel userLoginModel;

    private AccountUserInner userInners;

    private CompanyInfoModel companyInfoModel;

    private UserInfoModel userInfoModel;

    public UserLoginModel getUserLoginModel() {
        return userLoginModel;
    }

    public void setUserLoginModel(UserLoginModel userLoginModel) {
        this.userLoginModel = userLoginModel;
    }

    public AccountUserInner getUserInners() {
        return userInners;
    }

    public void setUserInners(AccountUserInner userInners) {
        this.userInners = userInners;
    }

    public CompanyInfoModel getCompanyInfoModel() {
        return companyInfoModel;
    }

    public void setCompanyInfoModel(CompanyInfoModel companyInfoModel) {
        this.companyInfoModel = companyInfoModel;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }
}
