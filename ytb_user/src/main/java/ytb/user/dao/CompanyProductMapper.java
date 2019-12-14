package ytb.user.dao;


import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyProductModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/6 20:08
 */
public interface CompanyProductMapper {
    //获取已上市产品
    List<CompanyProductModel> getCompanyProductListById(int companyId);

    //添加已上市产品
    void addCompanyProduct(CompanyProductModel companyProjectModel);

    //删除以上市产品
    void deleteCompanyProduct(@Param("projectId") int projectId,@Param("companyId") int companyId);

    //修改已上市产品
    void updateCompanyProduct(CompanyProductModel companyProjectModel);
}
