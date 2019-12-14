package ytb.user.service;

import com.alibaba.fastjson.JSONArray;
import ytb.user.model.*;

import java.util.List;
import java.util.Map;

/**
 * 公司资料中心
 * Package: ytb.user.service
 * Author: ZCS
 * Date: Created in 2018/9/6 11:10
 */
public interface CompanyCenterService {
    //获取公司荣誉
    List<CompanyHonorModel> getCompanyHonorListById(int companyId,int honorType);

    //添加公司荣誉
    void saveOrUpdateCompanyHonor(JSONArray json, String token);

    //删除公司荣誉
    void deleteCompanyHonor(int honorId, int companyId);

    //修改公司荣誉
    void updateCompanyHonor(CompanyHonorModel companyHonorModel);


    //获取已上市产品
    List<CompanyProductModel> getCompanyProductListById(int companyId);

    //添加已上市产品
    void saveOrUpdateCompanyProduct(JSONArray json,String token);

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

    CompanyInfoModel getCompanyInfoByName(String companyName);

    CompanyInfoModel getCompanyInfoByCompanyId(Integer companyName);
    //添加公司员工
    int addCompanyEmployees(String nickName,String mobile,String idCard, String password,String ip,String token);

    //获取公司员工
    List<CompanyEmployeesModel> getCompanyEmployeesListById(int companyId);

    List<Map<String,Object>> getCompanyEmpList(int companyId,Integer empType,String nickName);


    void deleteCompanyEmp(int empId, int userId);

    //获取公司环境照片
    List<CompanyEnvModel> getCompanyEnvListById(int companyId);

    //添加公司单位环境照片
    void addCompanyEnv(String lifeImgId,String workImgId,String token);

    //删除公司单位环境照片
    void deleteCompanyEnv(int companyId, int envId);

    //修改公司环境照片
    void updateCompanyEnv(CompanyEnvModel companyEnvModel);

    //获取公司设备列表
    List<CompanyDeviceModel> getCompanyDeviceListById(int companyId);

    //添加公司设备信息
    void saveOrUpdateCompanyDevice(JSONArray json,String token);

    //删除公司设备信息
    void deleteCompanyDevice(int companyId, int deviceId);

    //修改公司设备信息
    void updateCompanyDevice(CompanyDeviceModel companyDeviceModel);

    //获取公司收入列表
    List<CompanyIncomeModel> getCompanyIncomeListById(int companyId);

    //添加公司收入列表
    void addCompanyIncome(JSONArray jsonObject,String token);

    //删除公司收入
    void deleteCompanyIncome(int companyId, int incomeId);

    //修改公司收入信息
    void updateCompanyIncome(CompanyIncomeModel companyIncomeModel);

    //获取公司专利列表
    List<CompanyPatentModel> getCompanyPatentListById(int companyId,int patentType);

    //添加公司专利
    void saveOrUpdateCompanyPatent(JSONArray json,String token);

    //删除公司专利
    void deleteCompanyPatent(int companyId, int patentId);

    //修改公司专利
    void updateCompanyPatent(CompanyPatentModel companyPatentModel);

    //获取公司平台外项目
    List<CompanyProjectModel> getCompanyProjectListById(int companyId);

    //添加公司平台外项目
    void saveOrUpdateCompanyProject(JSONArray json,String token);

    //删除公司平台外项目
    void deleteCompanyProject(int companyId, int projectId);

    //修改公司平台项目
    void updateCompanyProject(CompanyProjectModel companyProjectModel);


    //设置公司理念
    void updateCompanyIdea(int companyId,String companyIdea);

    //设置公司员工学历占比
    void setCompanyEmpEdu(Map<String,Object> map);

    //获取公司学员占比
    Map<String,Object> getCompanyEmpEdu(Integer companyId);

    //获取公司专业标签
    JSONArray getCompanyTag(int companyId);

    //新增公司专业能力标签
   void addCompanyTag(CompanyTagModel companyTagModel);

   void deleteCompanyTagById(int tagId,int companyId);


    //修改公司员工
    void updateCompanyEmp(CompanyEmployeesModel companyEmployeesMoel);

    //获取各种专利信息
    Map<String,Object> getCompanyPatent(int companyId);

    //获取
    Map<String,Object> getAllCompanyHonorList(int companyId);

}
