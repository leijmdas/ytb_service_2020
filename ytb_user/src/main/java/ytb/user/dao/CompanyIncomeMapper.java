package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyIncomeModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/10 16:02
 */
public interface CompanyIncomeMapper {
    //获取公司收入列表
    List<CompanyIncomeModel> getCompanyIncomeListById(int companyId);

    //添加公司收入列表
    void addCompanyIncome(CompanyIncomeModel companyIncomeModel);

    //删除公司收入
    void deleteCompanyIncome(@Param("companyId") int companyId,@Param("incomeId") int incomeId);

    //修改公司收入信息
    void updateCompanyIncome(CompanyIncomeModel companyIncomeModel);
}
