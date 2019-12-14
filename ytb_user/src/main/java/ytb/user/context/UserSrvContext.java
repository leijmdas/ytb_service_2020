package ytb.user.context;

import ytb.user.rest.impl.CompanyCenter;
import ytb.user.rest.impl.UserCenter;
import ytb.user.service.CompanyCenterService;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.CompanyCenterServiceImpl;
import ytb.user.service.impl.UserCenterServiceImpl;

public class UserSrvContext {
    static UserSrvContext context = new UserSrvContext();

    public static UserSrvContext getInst() {
        return context;
    }

    UserCenterService userCenterService = new UserCenterServiceImpl();
    CompanyCenterService companyCenterService = new CompanyCenterServiceImpl();

    UserCenter userCenter = new UserCenter();
    CompanyCenter companyCenter = new CompanyCenter();

    public CompanyCenterService getCompanyCenterService() {
        return companyCenterService;
    }

    public void setCompanyCenterService(CompanyCenterService companyCenterService) {
        this.companyCenterService = companyCenterService;
    }


    public UserCenterService getUserCenterService() {
        return userCenterService;
    }

    public void setUserCenterService(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    public UserCenter getUserCenter() {
        return userCenter;
    }

    public void setUserCenter(UserCenter userCenter) {
        this.userCenter = userCenter;
    }

    public CompanyCenter getCompanyCenter() {
        return companyCenter;
    }

    public void setCompanyCenter(CompanyCenter companyCenter) {
        this.companyCenter = companyCenter;
    }

}
