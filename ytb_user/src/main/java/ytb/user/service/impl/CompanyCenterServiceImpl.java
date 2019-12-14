package ytb.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.YtbUtils;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.userid.model.BBNoModel;
import ytb.common.basic.userid.model.User_IdModel;
import ytb.common.basic.userid.service.BBNoService;
import ytb.common.basic.userid.service.impl.BBNoServiceImpl;
import ytb.common.basic.userid.service.impl.UserIdServiceImpl;
import ytb.common.context.service.impl.YtbContext;
import ytb.user.dao.*;
import ytb.user.model.*;
import ytb.user.service.CompanyCenterService;
import ytb.user.service.UserCenterService;

import java.util.*;

/**
 * Package: ytb.user.service.impl
 * Author: ZCS
 * Date: Created in 2018/9/6 11:11
 */
public class CompanyCenterServiceImpl implements CompanyCenterService {

    @Override
    public List<CompanyHonorModel> getCompanyHonorListById(int companyId, int honorType) {

        try(  SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_user(true)        ) {
            CompanyHonorMapper companyHonorDao = ss.getMapper(CompanyHonorMapper.class);
            return companyHonorDao.getCompanyHonorListById(companyId, honorType);
        }
    }

    @Override
    public void saveOrUpdateCompanyHonor(JSONArray json, String token) {
        SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyHonorMapper companyHonorDao = ss.getMapper(CompanyHonorMapper.class);
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);

                Date honorDate = jo.getDate("honorDate");
                String organizationName = jo.getString("organizationName");
                String honorName = jo.getString("honorName");
                Integer honorId = jo.getInteger("honorId");
                Integer honorType = jo.getInteger("honorType");
                String docId = jo.getString("docId");
                CompanyHonorModel companyHonorModel = new CompanyHonorModel();
                if (honorId == null || "".equals(honorId)) {//新增

                    companyHonorModel.setHonorDate(honorDate);
                    companyHonorModel.setHonorName(honorName);
                    companyHonorModel.setHonorType(honorType);
                    companyHonorModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                    companyHonorModel.setImage(docId);//保存图片
                    companyHonorModel.setOrganizeName(organizationName);
                    companyHonorDao.addCompanyHonor(companyHonorModel);
                    ss.commit();
                } else {
                    companyHonorModel.setHonorDate(honorDate);
                    companyHonorModel.setHonorName(honorName);
                    companyHonorModel.setHonorType(honorType);
                    companyHonorModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                    companyHonorModel.setImage(docId);//保存图片
                    companyHonorModel.setOrganizeName(organizationName);
                    companyHonorModel.setHonorId(honorId);

                    updateCompanyHonor(companyHonorModel);
                }
            }
        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyHonor(int honorId, int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyHonorMapper companyHonorDao = ss.getMapper(CompanyHonorMapper.class);
            companyHonorDao.deleteCompanyHonor(honorId, companyId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyHonor(CompanyHonorModel companyHonorModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyHonorMapper companyHonorDao = ss.getMapper(CompanyHonorMapper.class);
            companyHonorDao.updateCompanyHonor(companyHonorModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public List<CompanyProductModel> getCompanyProductListById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyProductModel> list;
        try {
            CompanyProductMapper companyProductDao = ss.getMapper(CompanyProductMapper.class);
            list = companyProductDao.getCompanyProductListById(companyId);
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public void saveOrUpdateCompanyProduct(JSONArray json, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyProductMapper companyProjectDao = ss.getMapper(CompanyProductMapper.class);
            CompanyProductModel companyProductModel = new CompanyProductModel();
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                String marketName = jo.getString("marketName");
                String marketModel = jo.getString("marketModel");
                Integer salesNum = jo.getInteger("salesNum");
                Integer marketId = jo.getInteger("productId");
                String docId = jo.getString("docId");

                if (marketId == null || "".equals(marketId)) {//新增
                    companyProductModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());//
                    companyProductModel.setProductName(marketName);
                    companyProductModel.setProductRule(marketModel);
                    companyProductModel.setProductSellNumber(salesNum);
                    companyProductModel.setProductImg(docId);//保存图片
                    companyProjectDao.addCompanyProduct(companyProductModel);
                    ss.commit();
                } else {
                    companyProductModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());//
                    companyProductModel.setProductName(marketName);
                    companyProductModel.setProductRule(marketModel);
                    companyProductModel.setProductSellNumber(salesNum);
                    companyProductModel.setProductImg(docId);//保存图片
                    companyProductModel.setProjectId(marketId);
                    updateCompanyProduct(companyProductModel);
                }
            }
        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyProduct(int projectId, int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyProductMapper companyProjectDao = ss.getMapper(CompanyProductMapper.class);
            companyProjectDao.deleteCompanyProduct(projectId, companyId);
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyProduct(CompanyProductModel companyProjectModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyProductMapper companyProjectDao = ss.getMapper(CompanyProductMapper.class);
            companyProjectDao.updateCompanyProduct(companyProjectModel);
        } finally {
            ss.close();
        }
    }

    @Override
    public CompanyInfoModel getCompanyInfoById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        CompanyInfoModel companyInfoModel;
        try {
            CompanyInfoMapper companyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            companyInfoModel = companyInfoDao.getCompanyInfoById(companyId);
        } finally {
            ss.close();
        }
        return companyInfoModel;
    }

    @Override
    public void addCompanyInfo(CompanyInfoModel companyInfoModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyInfoMapper companyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            companyInfoDao.addCompanyInfo(companyInfoModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public CompanyInfoModel getCompanyInfoByCompanyId(Integer companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        CompanyInfoModel companyInfoModel;
        try {
            CompanyInfoMapper companyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            companyInfoModel = companyInfoDao.getCompanyInfoByCompanyId(companyId);
        } finally {
            ss.close();
        }
        return companyInfoModel;
    }

    public int insertSelective(CompanyInfoModel companyInfoModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        int sta = 0;
        try {
            CompanyInfoMapper companyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            sta = companyInfoDao.insertSelective(companyInfoModel);
            ss.commit();
        } finally {
            ss.close();
        }
        return sta;
    }


    @Override
    public void updateCompanyInfo(CompanyInfoModel companyInfoModel) {
        SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyInfoMapper companyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            companyInfoDao.updateCompanyInfo(companyInfoModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }


    public CompanyInfoModel getCompanyInfoByName(String companyName) {

        SqlSession ss = YtbContext.getSqlSessionBuilder().getSession_user(true);
        CompanyInfoModel list;
        try {
            CompanyInfoMapper companyInfoMapper = ss.getMapper(CompanyInfoMapper.class);
            list = companyInfoMapper.getCompanyInfoByName(companyName);
            ss.commit();
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public int addCompanyEmployees(String nickName, String mobile, String idCard, String password, String ip, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            UserCenterService userCenterService = new UserCenterServiceImpl();
            //先注册新增员工的账号
            UserIdServiceImpl userIdService = new UserIdServiceImpl();
            BBNoService bbNoService = new BBNoServiceImpl();

            User_IdModel userIdModel = new User_IdModel();
            userIdModel.setUuId(YtbUtils.getUUID(true));
            userIdService.addUserId(userIdModel);

            //产生邦邦号

            BBNoModel bbNoModel = new BBNoModel();
            bbNoModel.setUuId(YtbUtils.getUUID(true));
            bbNoService.addBBNOInfo(bbNoModel);
            if (userIdModel.getId() != 0) {
                UserLoginModel newUserInfo = new UserLoginModel();
                newUserInfo.setRegisteredTime(new Date());
                newUserInfo.setRegisteredIp(ip);
                newUserInfo.setPassword(password);
                newUserInfo.setUserId(userIdModel.getId());
                newUserInfo.setBangBangNo(bbNoModel.getId() + "");
                newUserInfo.setLoginMobile(mobile);
                newUserInfo.setUserType(1);/*1个人 2 公司*/
                newUserInfo.setUserStatus(0);
              //  newUserInfo.setNickName(nickName);
                newUserInfo.setCompanyUserId(userIdModel.getId());
                int newusersta = userCenterService.insertSelective(newUserInfo);//注册用户完成

                if (newusersta > 0) {
                    UserInfoModel userInfoModel = new UserInfoModel();
                    userInfoModel.setIdcard(idCard);
                    userInfoModel.setUserId(newusersta);
                    userInfoModel.setCompanyUserId(newusersta);
                    userInfoModel.setNickName(nickName);
                    int returnId = userCenterService.addUserInfo(userInfoModel);

                    if (returnId > 0) {
                        CompanyEmployeesMapper companyEmployeesDao = ss.getMapper(CompanyEmployeesMapper.class);
                        CompanyEmployeesModel companyEmployeesMoel = new CompanyEmployeesModel();

                        companyEmployeesMoel.setCompanyUserId(newusersta);
                        companyEmployeesMoel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                        companyEmployeesMoel.setIsAdmin(false);

                        companyEmployeesDao.addCompanyEmployees(companyEmployeesMoel);
                        ss.commit();

                        return companyEmployeesMoel.getEmployeeId();
                    } else {
                        ss.rollback();
                    }
                } else {
                    ss.rollback();
                }
            } else {
                ss.rollback();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return 0;
    }

    @Override
    public List<CompanyEmployeesModel> getCompanyEmployeesListById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyEmployeesModel> list;
        try {
            CompanyEmployeesMapper companyEmployeesDao = ss.getMapper(CompanyEmployeesMapper.class);
            ss.commit();
            list = companyEmployeesDao.getCompanyEmployeesListById(companyId);
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getCompanyEmpList(int companyId, Integer empType, String nickName) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<Map<String, Object>> list;
        try {
            CompanyEmployeesMapper companyEmployeesDao = ss.getMapper(CompanyEmployeesMapper.class);
            list = companyEmployeesDao.getCompanyEmpList(companyId, empType, nickName);
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public void deleteCompanyEmp(int empId, int userId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyEmployeesMapper companyEmployeesDao = ss.getMapper(CompanyEmployeesMapper.class);
            companyEmployeesDao.deleteCompanyEmp(empId, userId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public List<CompanyEnvModel> getCompanyEnvListById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyEnvModel> list;
        try {
            CompanyEnvMapper companyEnvDao = ss.getMapper(CompanyEnvMapper.class);
            list = companyEnvDao.getCompanyEnvListById(companyId);

        } finally {
            ss.close();
        }

        return list;
    }

    @Override
    public void addCompanyEnv(String lifeImgId, String workImgId, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyEnvMapper companyEnvDao = ss.getMapper(CompanyEnvMapper.class);

            CompanyEnvModel companyEnvModel = new CompanyEnvModel();
            companyEnvModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
            companyEnvModel.setWorkImage(workImgId);
            companyEnvModel.setLifeImage(lifeImgId);

            companyEnvDao.addCompanyEnv(companyEnvModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyEnv(int companyId, int envId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyEnvMapper companyEnvDao = ss.getMapper(CompanyEnvMapper.class);
            companyEnvDao.deleteCompanyEnv(companyId, envId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyEnv(CompanyEnvModel companyEnvModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyEnvMapper companyEnvDao = ss.getMapper(CompanyEnvMapper.class);
            companyEnvDao.updateCompanyEnv(companyEnvModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public List<CompanyDeviceModel> getCompanyDeviceListById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyDeviceModel> list;
        try {
            CompanyDeviceMapper companyDeviceDao = ss.getMapper(CompanyDeviceMapper.class);
            list = companyDeviceDao.getCompanyDeviceListById(companyId);
        } finally {
            ss.close();
        }

        return list;
    }

    @Override
    public void saveOrUpdateCompanyDevice(JSONArray json, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyDeviceMapper companyDeviceDao = ss.getMapper(CompanyDeviceMapper.class);
            CompanyDeviceModel companyDeviceModel = new CompanyDeviceModel();
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                String deviceName = jo.getString("deviceName");
                String deviceBrand = jo.getString("deviceBrand");
                String deviceModel = jo.getString("deviceModel");
                Integer deviceNumber = jo.getInteger("deviceNumber");
                Integer deviceId = jo.getInteger("deviceId");
                String docId = jo.getString("docId");

                if (deviceId == null || "".equals(deviceId)) {//新增

                    companyDeviceModel.setBrand(deviceBrand);
                    companyDeviceModel.setCompanyId(getLoginSsoJson(token).getCompanyId());//从当前登录的用户中取companInfo表主键---表示公司用户登录
                    companyDeviceModel.setModel(deviceModel);
                    companyDeviceModel.setNumber(deviceNumber);
                    companyDeviceModel.setImage(docId);//保存图片路径
                    companyDeviceModel.setName(deviceName);
                    companyDeviceDao.addCompanyDevice(companyDeviceModel);
                    ss.commit();
                } else {
                    companyDeviceModel.setBrand(deviceBrand);
                    companyDeviceModel.setCompanyId(getLoginSsoJson(token).getCompanyId());//从当前登录的用户中取companInfo表主键---表示公司用户登录
                    companyDeviceModel.setModel(deviceModel);
                    companyDeviceModel.setNumber(deviceNumber);
                    companyDeviceModel.setImage(docId);//保存图片路径
                    companyDeviceModel.setName(deviceName);
                    companyDeviceModel.setDeviceId(deviceId);
                    updateCompanyDevice(companyDeviceModel);
                }
            }

        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyDevice(int companyId, int deviceId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyDeviceMapper companyDeviceDao = ss.getMapper(CompanyDeviceMapper.class);
            companyDeviceDao.deleteCompanyDevice(companyId, deviceId);
            ss.commit();

        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyDevice(CompanyDeviceModel companyDeviceModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyDeviceMapper companyDeviceDao = ss.getMapper(CompanyDeviceMapper.class);
            companyDeviceDao.updateCompanyDevice(companyDeviceModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public List<CompanyIncomeModel> getCompanyIncomeListById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyIncomeModel> list;
        try {
            CompanyIncomeMapper companyIncomeDao = ss.getMapper(CompanyIncomeMapper.class);
            list = companyIncomeDao.getCompanyIncomeListById(companyId);
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public void addCompanyIncome(JSONArray json, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {

            CompanyIncomeMapper companyIncomeDao = ss.getMapper(CompanyIncomeMapper.class);
            CompanyIncomeModel companyIncomeModel = new CompanyIncomeModel();
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                Integer incomeYear = jo.getInteger("incomeYear");
                Float incomeMoney = jo.getFloat("incomeMoney");
                Integer incomeId = jo.getInteger("incomeId");


                if (incomeId == null || "".equals(incomeId)) {
                    companyIncomeModel.setIncomeMoney(incomeMoney);
                    companyIncomeModel.setIncomeYear(incomeYear);
                    companyIncomeModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    companyIncomeDao.addCompanyIncome(companyIncomeModel);
                    ss.commit();
                } else {
                    companyIncomeModel.setIncomeMoney(incomeMoney);
                    companyIncomeModel.setIncomeYear(incomeYear);
                    companyIncomeModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    companyIncomeModel.setIncomeId(incomeId);
                    updateCompanyIncome(companyIncomeModel);
                }
            }
        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyIncome(int companyId, int incomeId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyIncomeMapper companyIncomeDao = ss.getMapper(CompanyIncomeMapper.class);
            companyIncomeDao.deleteCompanyIncome(companyId, incomeId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyIncome(CompanyIncomeModel companyIncomeModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyIncomeMapper companyIncomeDao = ss.getMapper(CompanyIncomeMapper.class);
            companyIncomeDao.updateCompanyIncome(companyIncomeModel);
        } finally {
            ss.close();
        }
    }

    @Override
    public List<CompanyPatentModel> getCompanyPatentListById(int companyId, int patentType) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyPatentModel> list;
        try {
            CompanyPatentMapper companyPatentDao = ss.getMapper(CompanyPatentMapper.class);
            list = companyPatentDao.getCompanyPatentListById(companyId, patentType);
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public void saveOrUpdateCompanyPatent(JSONArray json, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyPatentMapper companyPatentDao = ss.getMapper(CompanyPatentMapper.class);

            CompanyPatentModel companyPatentModel = new CompanyPatentModel();
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                String patentName = jo.getString("patentName");
                String patentNo = jo.getString("patentNo");
                Integer patentId = jo.getInteger("patentId");
                Integer partentType = jo.getInteger("patentType");
                String docId = jo.getString("docId");
                if (patentId == null || "".equals(patentId)) {//新增

                    companyPatentModel.setImage(docId);
                    companyPatentModel.setPatentType(partentType);
                    companyPatentModel.setPatentName(patentName);
                    companyPatentModel.setPatentNo(patentNo);
                    companyPatentModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                    companyPatentDao.addCompanyPatent(companyPatentModel);
                    ss.commit();
                } else {
                    companyPatentModel.setImage(docId);
                    companyPatentModel.setPatentType(partentType);
                    companyPatentModel.setPatentName(patentName);
                    companyPatentModel.setPatentNo(patentNo);
                    companyPatentModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                    companyPatentModel.setPatentId(patentId);
                    updateCompanyPatent(companyPatentModel);
                }
            }

        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyPatent(int companyId, int patentId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyPatentMapper companyPatentDao = ss.getMapper(CompanyPatentMapper.class);
            companyPatentDao.deleteCompanyPatent(companyId, patentId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyPatent(CompanyPatentModel companyPatentModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyPatentMapper companyPatentDao = ss.getMapper(CompanyPatentMapper.class);
            companyPatentDao.updateCompanyPatent(companyPatentModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public List<CompanyProjectModel> getCompanyProjectListById(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<CompanyProjectModel> list;
        try {
            CompanyProjectMapper companyProjectDao = ss.getMapper(CompanyProjectMapper.class);
            list = companyProjectDao.getCompanyProjectListById(companyId);
        } finally {
            ss.close();
        }
        return list;
    }

    @Override
    public void saveOrUpdateCompanyProject(JSONArray json, String token) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyProjectMapper companyProjectDao = ss.getMapper(CompanyProjectMapper.class);

            CompanyProjectModel companyProjectModel = new CompanyProjectModel();
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                Date startTime = jo.getDate("startTime");
                Date endTime = jo.getDate("endTime");
                String projectName = jo.getString("projectName");
                String companyName = jo.getString("companyName");
                Integer projectId = jo.getInteger("projectId");
                String docId = jo.getString("docId");

                if (projectId == null || "".equals(projectId)) {//新增

                    companyProjectModel.setProjectName(projectName);
                    companyProjectModel.setEndTime(endTime);
                    companyProjectModel.setStartTime(startTime);
                    companyProjectModel.setImage(docId);
                    companyProjectModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                    companyProjectModel.setCompanyName(companyName);
                    companyProjectDao.addCompanyProject(companyProjectModel);
                    ss.commit();
                } else {
                    companyProjectModel.setProjectName(projectName);
                    companyProjectModel.setEndTime(endTime);
                    companyProjectModel.setStartTime(startTime);
                    companyProjectModel.setImage(docId);
                    companyProjectModel.setCompanyId(getLoginSsoJson(token).getCompanyUserId());
                    companyProjectModel.setCompanyName(companyName);
                    companyProjectModel.setProjectId(projectId);
                    updateCompanyProject(companyProjectModel);
                }
            }

        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyProject(int companyId, int projectId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyProjectMapper companyProjectDao = ss.getMapper(CompanyProjectMapper.class);
            companyProjectDao.deleteCompanyProject(companyId, projectId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyProject(CompanyProjectModel companyProjectModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyProjectMapper companyProjectDao = ss.getMapper(CompanyProjectMapper.class);
            companyProjectDao.updateCompanyProject(companyProjectModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyIdea(int companyId, String companyIdea) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyInfoMapper CompanyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            CompanyInfoDao.updateCompanyIdea(companyId, companyIdea);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void setCompanyEmpEdu(Map<String, Object> map) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyInfoMapper CompanyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            CompanyInfoDao.setCompanyEmpEdu(map);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public Map<String, Object> getCompanyEmpEdu(Integer companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyInfoMapper companyInfoDao = ss.getMapper(CompanyInfoMapper.class);
            Map<String, Object> map = companyInfoDao.getCompanyEmpEdu(companyId);
            ss.commit();
            return map;
        } finally {
            ss.close();
        }
    }

    @Override
    public JSONArray getCompanyTag(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyTagMapper companyTagDao = ss.getMapper(CompanyTagMapper.class);
            List<Map<String, Object>> list = companyTagDao.getCompanyTagListById(companyId);
            JSONArray json = new JSONArray();
            for (Map map : list) {
                if (map.get("parentName") == null || "".equals(map.get("parentName"))) {
                    map.put("titleName", map.get("tagName"));
                } else {
                    map.put("titleName", map.get("parentName") + "-" + map.get("tagName"));
                }
                json.add(map);
            }
            return json;
        } finally {
            ss.close();
        }
    }

    @Override
    public void addCompanyTag(CompanyTagModel companyTagModel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyTagMapper companyTagDao = ss.getMapper(CompanyTagMapper.class);
            companyTagDao.addCompanyTag(companyTagModel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void deleteCompanyTagById(int tagId, int companyId) {

        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyTagMapper companyTagDao = ss.getMapper(CompanyTagMapper.class);
            companyTagDao.deleteCompanyTag(companyId, tagId);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public void updateCompanyEmp(CompanyEmployeesModel companyEmployeesMoel) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyEmployeesMapper companyEmpDao = ss.getMapper(CompanyEmployeesMapper.class);
            companyEmpDao.updateCompanyEmp(companyEmployeesMoel);
            ss.commit();
        } finally {
            ss.close();
        }
    }

    @Override
    public Map<String, Object> getCompanyPatent(int companyId) {


        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyPatentMapper companyPatentDao = ss.getMapper(CompanyPatentMapper.class);
            List<CompanyPatentModel> list = companyPatentDao.getCompanyAllPantent(companyId);


            List<CompanyPatentModel> patent1 = new ArrayList<>();
            List<CompanyPatentModel> patent2 = new ArrayList<>();
            List<CompanyPatentModel> patent3 = new ArrayList<>();
            List<CompanyPatentModel> patent4 = new ArrayList<>();

            for (CompanyPatentModel patentModel : list) {
                if (patentModel.getPatentType() == 1) {
                    patent1.add(patentModel);
                } else if (patentModel.getPatentType() == 2) {
                    patent2.add(patentModel);
                } else if (patentModel.getPatentType() == 3) {
                    patent3.add(patentModel);
                } else if (patentModel.getPatentType() == 4) {
                    patent4.add(patentModel);
                }
            }

            Map<String, Object> map = new HashMap<>();

            map.put("sqfmList", patent1);
            map.put("sqsyList", patent2);
            map.put("sqwgList", patent3);
            map.put("sqrjList", patent4);


            return map;

        } finally {
            ss.close();
        }

    }

    @Override
    public Map<String, Object> getAllCompanyHonorList(int companyId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try {
            CompanyHonorMapper companyHonorDao = ss.getMapper(CompanyHonorMapper.class);
            List<CompanyHonorModel> list = companyHonorDao.getAllCompanyHonorList(companyId);

            Map<String, Object> map = new HashMap<>();
            List<CompanyHonorModel> honor1 = new ArrayList<>();
            List<CompanyHonorModel> honor2 = new ArrayList<>();
            List<CompanyHonorModel> honor3 = new ArrayList<>();

            for (CompanyHonorModel honorModel : list) {
                if (honorModel.getHonorType() == 1) {
                    honor1.add(honorModel);
                } else if (honorModel.getHonorType() == 2) {
                    honor2.add(honorModel);
                } else if (honorModel.getHonorType() == 3) {
                    honor3.add(honorModel);
                }
            }
            map.put("awadList", honor1);
            map.put("unitAuthList", honor2);
            map.put("proAuthList", honor3);
            return map;
        } finally {
            ss.close();
        }
    }

    private LoginSsoJson getLoginSsoJson(String token) {
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

        return loginSsoJson;
    }


}
