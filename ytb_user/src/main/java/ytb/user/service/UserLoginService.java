package ytb.user.service;

import ytb.user.model.*;

import java.util.List;

/**
 * 公司资料中心
 * Package: ytb.user.service
 * Author: ZCS
 * Date: Created in 2018/9/6 11:10
 */
public interface UserLoginService {
    //获取公司荣誉
    List<CompanyHonorModel> getCompanyHonorListById(int companyId);

    //添加公司荣誉
    void addCompanyHonor(CompanyHonorModel companyHonorModel);

    //删除公司荣誉
    void deleteCompanyHonor(int honorId, int companyId);

    //修改公司荣誉
    void updateCompanyHonor(CompanyHonorModel companyHonorModel);


    //获取已上市产品
    List<CompanyProductModel> getCompanyProductListById(int companyId);

    //添加已上市产品
    void addCompanyProduct(CompanyProductModel companyProjectModel);

    //删除以上市产品
    void deleteCompanyProduct(int projectId, int companyId);

    //修改已上市产品
    void updateCompanyProduct(CompanyProductModel companyProjectModel);


    //获取公司信息
    CompanyInfoModel getCompanyInfoById(int companyId);

    //添加公司信息
    void addCompanyInfo(CompanyInfoModel companyInfoModel);

    //修改公司信息
    void updateCompanyInfo(CompanyInfoModel companyInfoModel);


    //添加公司员工
    void addCompanyEmployees(CompanyEmployeesModel companyEmployeesMoel);

    //获取公司员工
    List<CompanyEmployeesModel> getCompanyEmployeesListById(int companyId);




    //获取公司环境照片
    List<CompanyEnvModel> getCompanyEnvListById(int companyId);

    //添加公司单位环境照片
    void addCompanyEnv(CompanyEnvModel companyEnvModel);

    //删除公司单位环境照片
    void deleteCompanyEnv(int companyId, int envId);

    //修改公司环境照片
    void updateCompanyEnv(CompanyEnvModel companyEnvModel);

    //获取公司设备列表
    List<CompanyDeviceModel> getCompanyDeviceListById(int companyId);

    //添加公司设备信息
    void addCompanyDevice(CompanyDeviceModel companyDeviceModel);

    //删除公司设备信息
    void deleteCompanyDevice(int companyId, int deviceId);

    //修改公司设备信息
    void updateCompanyDevice(CompanyDeviceModel companyDeviceModel);

    //获取公司收入列表
    List<CompanyIncomeModel> getCompanyIncomeListById(int companyId);

    //添加公司收入列表
    void addCompanyIncome(CompanyIncomeModel companyIncomeModel);

    //删除公司收入
    void deleteCompanyIncome(int companyId, int incomeId);

    //修改公司收入信息
    void updateCompanyIncome(CompanyIncomeModel companyIncomeModel);

    //获取公司专利列表
    List<CompanyPatentModel> getCompanyPatentListById(int companyId);

    //添加公司专利
    void addCompanyPatent(CompanyPatentModel companyPatentModel);

    //删除公司专利
    void deleteCompanyPatent(int companyId, int patentId);

    //修改公司专利
    void updateCompanyPatent(CompanyPatentModel companyPatentModel);

    //获取公司平台外项目
    List<CompanyProjectModel> getCompanyProjectListById(int companyId);

    //添加公司平台外项目
    void addCompanyProject(CompanyProjectModel companyProjectModel);

    //删除公司平台外项目
    void deleteCompanyProject(int companyId, int projectId);

    //修改公司平台项目
    void updateCompanyProject(CompanyProjectModel companyProjectModel);

}
