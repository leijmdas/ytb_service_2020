package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyEmployeesModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/7 18:12

 */
public interface CompanyEmployeesMapper {
    //添加公司员工
    void addCompanyEmployees(CompanyEmployeesModel companyEmployeesMoel);



    //获取公司员工
    List<CompanyEmployeesModel> getCompanyEmployeesListById(@Param("companyId") int companyId);

    List<Map<String,Object>> getCompanyEmpList(@Param("companyId") int companyId,@Param("empType")Integer empType,@Param("nickName") String nickName);

    //删除员工
    void deleteCompanyEmp(@Param("empId") int empId,@Param("userId") int userId);

    //修改公司员工
    void updateCompanyEmp(CompanyEmployeesModel companyEmployeesMoel);
}
