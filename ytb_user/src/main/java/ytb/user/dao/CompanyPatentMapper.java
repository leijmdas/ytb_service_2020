package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyPatentModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/10 16:20
 */
public interface CompanyPatentMapper {
    //获取公司专利列表
    List<CompanyPatentModel> getCompanyPatentListById(@Param("companyId") int companyId, @Param("patentType") int patentType);

    //添加公司专利
    void addCompanyPatent(CompanyPatentModel companyPatentModel);

    //删除公司专利
    void deleteCompanyPatent(@Param("companyId") int companyId,@Param("patentId") int patentId);

    //修改公司专利
    void updateCompanyPatent(CompanyPatentModel companyPatentModel);

    //获取用户所有专利
    List<CompanyPatentModel> getCompanyAllPantent(int companyId);

}
