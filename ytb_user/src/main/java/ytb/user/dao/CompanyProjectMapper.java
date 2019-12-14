package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyProjectModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/10 17:10
 */
public interface CompanyProjectMapper {
    //获取公司平台外项目
    List<CompanyProjectModel> getCompanyProjectListById(int companyId);

    //添加公司平台外项目
    void addCompanyProject(CompanyProjectModel companyProjectModel);

    //删除公司平台外项目
    void deleteCompanyProject(@Param("companyId") int companyId,@Param("projectId") int projectId);

    //修改公司平台项目
    void updateCompanyProject(CompanyProjectModel companyProjectModel);
}
