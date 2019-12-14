package ytb.user.service;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.*;


import java.util.List;
import java.util.Map;

/**
 * 人才搜索
 */
public interface SearchPersonService {

    //专业能力标签
    List<Map<String,Object>> getPageTag(String parentId);
    //接单范围
    List<SearchProjectModel> getPageScope(String parentId);
    //获取用户教育基本信息
    Map<String,Object> getEducationByUserId(@Param("userId")String userId);
    //更据条件查询分页信息
    List<SearchPagePersonModel> getPagePerson(Map<String,Object> map);

    //查询总数
    int getPagePersonCount(Map<String,Object> map);

    //获取地址信息
    List<Map<String,Object>> getDictAreaList(int parentId);

    //查询公司
    List<Map<String,Object>> getSearchCompany(Map<String,Object> map);

    //查询公司
    int getSearchCompanyCount(Map<String,Object> map);
}
