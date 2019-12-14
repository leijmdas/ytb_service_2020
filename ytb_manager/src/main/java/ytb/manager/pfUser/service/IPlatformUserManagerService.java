package ytb.manager.pfUser.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.pfUser.service
 * Author: ZCS
 * Date: Created in 2018/12/5 13:25
 * Company: 公司
 */
public interface IPlatformUserManagerService {

    //查询平台个人用户
    List<Map<String,Object>> getPlatformUserList(Map<String,Object> map);

    int getPlatformUserTotal(String nickName);


    //查询平台公司用户
    List<Map<String,Object>> getPlatformCompanyList(Map<String,Object> map);

    int getPlatformCompanyTotal(@Param("nickName") String nickName);
}
