package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyTagModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/10 17:35
 */
public interface CompanyTagMapper {
    //获取公司标签列表
    List<Map<String,Object>> getCompanyTagListById(int companyId);

    //添加公司标签
    void addCompanyTag(CompanyTagModel companyTagModel);

    //删除公司标签
    void deleteCompanyTag(@Param("companyId") int companyId,@Param("companyTagId") int companyTagId);

    //修改公司标签
    void updateCompanyTag(CompanyTagModel companyTagModel);
}
