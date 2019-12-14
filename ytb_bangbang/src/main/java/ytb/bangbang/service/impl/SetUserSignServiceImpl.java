package ytb.bangbang.service.impl;

import ytb.bangbang.service.SetUserSignService;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;

/**
 * 设置用户签名
 */
public class SetUserSignServiceImpl implements SetUserSignService {
    @Override
    public synchronized int setUserSign(int userId ,String userSign) {
        try {
            UserCenterService loginService = new UserCenterServiceImpl();
            loginService.setUserSign(userSign, userId);
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
