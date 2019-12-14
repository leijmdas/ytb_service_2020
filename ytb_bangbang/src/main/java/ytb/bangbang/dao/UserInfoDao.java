package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.UserInfo;

public interface UserInfoDao {

    UserInfo getUserInfoById(@Param("inviteId") int inviteId);

    UserInfo getUserInfoByUserId(@Param("userId") int userId);

}
