package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyHonorModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/6 20:00
 */
public interface CompanyHonorMapper {
    //获取公司荣誉
    List<CompanyHonorModel> getCompanyHonorListById(@Param("companyId") int companyId, @Param("honorType") int honorType);

    //添加公司荣誉
    void addCompanyHonor(CompanyHonorModel companyHonorModel);

    //删除公司荣誉
    void deleteCompanyHonor(@Param("honorId") int honorId,@Param("companyId") int companyId);

    //修改公司荣誉
    void updateCompanyHonor(CompanyHonorModel companyHonorModel);

    //获取
    List<CompanyHonorModel> getAllCompanyHonorList(int companyId);
}
