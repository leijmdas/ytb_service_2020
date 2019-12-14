package ytb.manager.pfUser.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.pfUser.dao
 * Author: ZCS
 * Date: Created in 2018/12/5 13:30
 */
public interface PlatformUserManagerMapper {
    List<Map<String,Object>> getPlatformUserList(Map<String,Object> map);

    int getPlatformUserTotal(@Param("nickName") String nickName);


    List<Map<String,Object>> getPlatformCompanyList(Map<String,Object> map);

    int getPlatformCompanyTotal(@Param("nickName") String nickName);
}
