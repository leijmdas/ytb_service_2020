package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyEnvModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/10 15:08
 */
public interface CompanyEnvMapper {
    //获取公司环境照片
    List<CompanyEnvModel> getCompanyEnvListById(int companyId);

    //添加公司单位环境照片
    void addCompanyEnv(CompanyEnvModel companyEnvModel);

    //删除公司单位环境照片
    void deleteCompanyEnv(@Param("companyId") int companyId,@Param("envId") int envId);

    //修改公司环境照片
    void updateCompanyEnv(CompanyEnvModel companyEnvModel);


}
