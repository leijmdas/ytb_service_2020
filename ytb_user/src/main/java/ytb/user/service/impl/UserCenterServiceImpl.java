package ytb.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.session.SqlSession;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.user.dao.*;
import ytb.user.model.*;
import ytb.user.service.UserCenterService;

import ytb.common.context.service.impl.YtbContext;

import java.util.*;

/**
 * Package: ytb.user.service.impl
 * Author: ZCS
 * Date: Created in 2018/9/6 11:04
 */
public class UserCenterServiceImpl implements UserCenterService {
    private LoginSsoJson getLoginSsoJson(String token) {

        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);
        return JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);

    }

    SqlSession getSession() {
        return YtbContext.getSqlSessionBuilder().getSession_user(true);
    }

    @Override
    public UserLoginModel getUserLoginInfoById(int userId) {

        try (SqlSession sqlSession = getSession()) {
            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.getUserLoginInfoById(userId);
        }
    }


    @Override
    public UserLoginModel getUserLoginInfoByLoginMobile(String loginMobile) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.getUserLoginInfoByLoginMobile(loginMobile);
        }
    }

    @Override
    public UserLoginModel getUserLoginInfoByLoginMobileAndUserType(String loginMobile, int userType) {

        try (SqlSession sqlSession = getSession()) {
            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.getUserLoginInfoByLoginMobileAndUserType(loginMobile, userType);

        }

    }


    @Override
    public List<UserLoginModel> getUserLoginModelByUserType(int userType) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.getUserLoginModelByUserType(userType);

        }

    }


    @Override
    public List<UserLoginModel> getUserLoginInfoByLoginMobileAndUserTypeList(String loginMobile, int userType) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.getUserLoginInfoByLoginMobileAndUserTypeList(loginMobile, userType);
        }

    }


    @Override
    public List<UserLoginModel> getUserLoginInfoByLoginMobileAndUserTypeAndPasswordList(String loginMobile, int userType, String password) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.getUserLoginInfoByLoginMobileAndUserTypeAndPasswordList(loginMobile, userType, password);

        }
    }

    @Override
    public int modifyPassword(Integer userId, int userType, String password, String oldPassword) {

        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.modifyPassword(userId, userType, password, oldPassword);

        }
    }

    @Override
    public Boolean modifyPhone(Integer userId, String newLoginMobile, String password, String oldLoginMobile) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            int sta = userLoginDao.modifyPhone(userId, newLoginMobile, password, oldLoginMobile);
            return sta > 0;


        }


    }


    @Override
    public int updateByPrimaryKeySelective(UserLoginModel userLoginModels) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.updateByPrimaryKeySelective(userLoginModels);

        }
    }

    @Override
    public int insertSelective(UserLoginModel userLoginModel) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            userLoginDao.insertSelective(userLoginModel);

            return userLoginModel.getUserId();
        }


    }

    @Override
    public int newUser(UserLoginModel userLoginModel, Integer areaId) {


        try (SqlSession sqlSession = getSession()) {

            int sta = 0;
            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);

            sta = userLoginDao.insertSelective(userLoginModel);
            if (sta > 0) {
                UserInfoModel userInfoModel = new UserInfoModel();
                userInfoModel.setAreaId(areaId);
                userInfoModel.setUserId(userLoginModel.getUserId());
                userInfoModel.setNickName(userLoginModel.getNickName());

                /*UserCenterServiceImpl userCenterService = new UserCenterServiceImpl();*/
                int userinfosta = addUserInfo(userInfoModel);

            }
        }
        return userLoginModel.getUserId();

    }

    @Override
    public int newComUser(UserLoginModel userLoginModel, CompanyInfoModel companyInfoModel, Integer areaId) {
        int sta = 0;
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);

            CompanyEmployeesMapper companyEmployeesMapper = sqlSession.getMapper(CompanyEmployeesMapper.class);
            sta = userLoginDao.insertSelective(userLoginModel);

            Date date = new Date();

            if (sta > 0) {
                CompanyInfoMapper companyInfoDao = sqlSession.getMapper(CompanyInfoMapper.class);
                int comSta = companyInfoDao.insertSelective(companyInfoModel);

                if (comSta > 0) {

                    CompanyEmployeesModel companyEmployeesModel = new CompanyEmployeesModel();

                    companyEmployeesModel.setCreateTime(date);
                    companyEmployeesModel.setIsAdmin(true);
                    companyEmployeesModel.setIsEnable(0);
                    companyEmployeesModel.setCompanyId(companyInfoModel.getCompanyUserId());
                    companyEmployeesModel.setCompanyUserId(userLoginModel.getUserId());
                    companyEmployeesMapper.addCompanyEmployees(companyEmployeesModel);


                    UserInfoModel userInfoModel = new UserInfoModel();

                    userInfoModel.setAreaId(areaId);
                    userInfoModel.setUserId(userLoginModel.getUserId());
                    userInfoModel.setNickName(userLoginModel.getNickName());
                    UserCenterServiceImpl userCenterService = new UserCenterServiceImpl();
                    int userinfosta = userCenterService.addUserInfo(userInfoModel);


                }

            }


        }
        return userLoginModel.getUserId();


    }


    @Override
    public Map<String, Object> getUserBaseInfo(int userId) {
        try (SqlSession sqlSession = getSession()) {
            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);

            return  userLoginDao.getUserBaseInfo(userId);


        }
    }

    @Override
    public Map<String, Object> getFCityByAreaId(int areaId) {
        try (SqlSession sqlSession = getSession()) {
            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);

            return userLoginDao.getFCityByAreaId(areaId);


        }
    }


    @Override
    public void setUserSign(String userSgin, int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            userLoginDao.setUserSign(userSgin, userId);

        }
    }

    @Override
    public List<StudentPaperModel> getStudentPaperListById(int userId) {

        try (SqlSession sqlSession = getSession()) {
            StudentPaperMapper studentPaperDao = sqlSession.getMapper(StudentPaperMapper.class);
            return  studentPaperDao.getStudentPaperListById(userId);

        }
    }

    @Override
    public void addStudentPaper(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            StudentPaperMapper studentPaperDao = sqlSession.getMapper(StudentPaperMapper.class);
            StudentPaperModel studentPaperModel = new StudentPaperModel();
            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);
                Integer paperId = jo.getInteger("paperId");
                String picId1 = jo.getString("picId1");
                String picId2 = jo.getString("picId2");
                String paperName = jo.getString("paperName");
                String paperNo = jo.getString("paperNo");
                Date passDate = jo.getDate("passDate");

                if (jo.getInteger("paperId") == null) {//新增
                    studentPaperModel.setPaperName(paperName);
                    studentPaperModel.setPaperNo(paperNo);
                    studentPaperModel.setPicture1(picId1);
                    studentPaperModel.setPicture2(picId2);
                    studentPaperModel.setPassDate(passDate);
                    studentPaperModel.setUserId(getLoginSsoJson(token).getUserId());
                    studentPaperDao.addStudentPaper(studentPaperModel);
                    sqlSession.commit();

                } else {//修改
                    studentPaperModel.setPaperName(paperName);
                    studentPaperModel.setPaperNo(paperNo);
                    studentPaperModel.setPicture1(picId1);
                    studentPaperModel.setPicture2(picId2);
                    studentPaperModel.setPassDate(passDate);
                    studentPaperModel.setUserId(getLoginSsoJson(token).getUserId());
                    studentPaperModel.setPaperId(paperId);
                    updateStudentPaper(studentPaperModel);
                }
            }
        }
    }

    @Override
    public void deleteStudentPaper(int paperId, int userId) {
        try (SqlSession sqlSession = getSession()) {
            StudentPaperMapper studentPaperDao = sqlSession.getMapper(StudentPaperMapper.class);
            studentPaperDao.deleteStudentPaper(paperId, userId);

        }
    }

    @Override
    public void updateStudentPaper(StudentPaperModel studentPaperModel) {
        try (SqlSession sqlSession = getSession()) {

            StudentPaperMapper studentPaperDao = sqlSession.getMapper(StudentPaperMapper.class);
            studentPaperDao.updateStudentPaper(studentPaperModel);

        }
    }


    @Override
    public List<UserAddressModel> getUserAddressListById(Map<String, Object> map/*Integer userId,Integer companyId*/) {
        try (SqlSession sqlSession = getSession()) {

            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            return userAddressDao.getUserAddressListById(map);

        }
    }

    @Override
    public Integer addUserAddress(UserAddressModel userAddressModel) {
        try (SqlSession sqlSession = getSession()) {

            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            userAddressDao.addUserAddress(userAddressModel);

            return userAddressModel.getAddressId();
        }
    }


    @Override
    public void deleteUserAddress(Integer addressId, Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            userAddressDao.deleteUserAddress(addressId, userId, companyId);

        }
    }

    @Override
    public void updateUserAddress(UserAddressModel userAddressModel) {
        try (SqlSession sqlSession = getSession()) {


            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            userAddressDao.updateUserAddress(userAddressModel);

        }
    }

    @Override
    public void setDefaultAddr(int addressId, int companyId, boolean isDefault) {
        try (SqlSession sqlSession = getSession()) {


            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            int i = userAddressDao.setDefaultAddr(addressId, isDefault);

            if (i > 0) {//设置默认地址成功，修改其他为非默认
                setNoDefaultAddr(addressId, companyId);
            }
        }
    }

    public void setNoDefaultAddr(int addressId, int companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            userAddressDao.setNoDefaultAddr(addressId, companyId);

        }
    }

    @Override
    public Integer queryUserAddressTotal(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {

            UserAddressMapper userAddressDao = sqlSession.getMapper(UserAddressMapper.class);
            return userAddressDao.queryTotal(map);
        }


    }


    @Override
    public List<UserAwardModel> getUserAwardListById(int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserAwardMapper userAwardDao = sqlSession.getMapper(UserAwardMapper.class);
            return userAwardDao.getUserAwardListById(userId);

        }
    }

    @Override
    public void deleteUserAward(int userId, int awardId) {
        try (SqlSession sqlSession = getSession()) {

            UserAwardMapper userAwardDao = sqlSession.getMapper(UserAwardMapper.class);
            userAwardDao.deleteUserAward(userId, awardId);

        }
    }

    @Override
    public void saveOrUpdateUserAward(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserAwardMapper userAwardDao = sqlSession.getMapper(UserAwardMapper.class);
            UserAwardModel userAwardModel = new UserAwardModel();

            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);
                Date awardDate = jo.getDate("awardDate");
                String awardName = jo.getString("awardName");
                String organName = jo.getString("organName");
                Integer awardId = jo.getInteger("awardId");
                String docId = jo.getString("docId");
                if (awardId == null || "".equals(awardId)) {
                    userAwardModel.setAwardDate(awardDate);
                    userAwardModel.setAwardName(awardName);
                    userAwardModel.setOrganName(organName);
                    userAwardModel.setUserId(getLoginSsoJson(token).getUserId());
                    userAwardModel.setPicUrl(docId);
                    userAwardDao.addUserAward(userAwardModel);

                } else {
                    userAwardModel.setAwardDate(awardDate);
                    userAwardModel.setAwardName(awardName);
                    userAwardModel.setOrganName(organName);
                    userAwardModel.setUserId(getLoginSsoJson(token).getUserId());
                    userAwardModel.setPicUrl(docId);
                    userAwardModel.setAwardId(awardId);
                    updateUserAward(userAwardModel);
                }
            }

        }
    }

    @Override
    public void updateUserAward(UserAwardModel userAwardModel) {
        try (SqlSession sqlSession = getSession()) {

            UserAwardMapper userAwardDao = sqlSession.getMapper(UserAwardMapper.class);
            userAwardDao.updateUserAward(userAwardModel);
        }
    }


    @Override
    public List<Map<String, Object>> getUserEducationById(int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserEducationMapper educationDao = sqlSession.getMapper(UserEducationMapper.class);
            return educationDao.getUserEducationById(userId);
        }
    }

    @Override
    public List<UserEducationModel> getUserEduById(int userId) {
        try (SqlSession sqlSession = getSession()) {

            List<UserEducationModel> returnlist = new ArrayList<>();
            UserEducationMapper educationDao = sqlSession.getMapper(UserEducationMapper.class);
            List<UserEducationModel> list = educationDao.getUserEduById(userId);
            for (UserEducationModel userEducationModel : list) {
                if (userEducationModel.getSchoolName() != null && !"".equals(userEducationModel.getSchoolName())) {
                    returnlist.add(userEducationModel);
                }

            }

            return returnlist;
        }
    }

    @Override
    public void addUserEducation(JSONObject json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserEducationMapper educationDao = sqlSession.getMapper(UserEducationMapper.class);

            JSONArray shcoolName = (JSONArray) json.get("schoolName");
            JSONArray eduEndDate = (JSONArray) json.get("eduEndDate");
            JSONArray eduStartDate = (JSONArray) json.get("eduStartDate");
            JSONArray majorName = (JSONArray) json.get("majorName");
            JSONArray tutorName = (JSONArray) json.get("tutorName");
            JSONArray studentCadres = (JSONArray) json.get("studentCadres");
            JSONArray educationLavel = (JSONArray) json.get("educationLavel");
            JSONArray educationName = (JSONArray) json.get("educationName");
            JSONArray headMaster = (JSONArray) json.get("headMaster");
            JSONArray educationId = (JSONArray) json.get("educationId");

            UserEducationModel userEducationModel = new UserEducationModel();

            for (int i = 0; i < educationName.size(); i++) {

                if (educationId.getInteger(i) == null) {//为空则修改
                    //注改UserID应从cookie中取
                    userEducationModel.setUserId(getLoginSsoJson(token).getUserId());
                    userEducationModel.setSchoolName(shcoolName.getString(i));
                    userEducationModel.setEducationName(educationName.getString(i));
                    userEducationModel.setLevel(educationLavel.getInteger(i));
                    userEducationModel.setTutor(tutorName.getString(i));
                    userEducationModel.setStudentCadres(studentCadres.getString(i));
                    userEducationModel.setMajorName(majorName.getString(i));
                    userEducationModel.setEndDate(eduEndDate.getDate(i));
                    userEducationModel.setStartDate(eduStartDate.getDate(i));
                    userEducationModel.setHeadMaster(headMaster.getString(i));
                    if (shcoolName.getString(i) == null || shcoolName.getString(i).equals("")) {
                        userEducationModel.setIsValid(false);
                    } else {
                        userEducationModel.setIsValid(true);
                    }

                    educationDao.addUserEducation(userEducationModel);

                } else {

                    userEducationModel.setUserId(getLoginSsoJson(token).getUserId());
                    userEducationModel.setSchoolName(shcoolName.getString(i));
                    userEducationModel.setEducationName(educationName.getString(i));
                    userEducationModel.setLevel(educationLavel.getInteger(i));
                    userEducationModel.setTutor(tutorName.getString(i));
                    userEducationModel.setStudentCadres(studentCadres.getString(i));
                    userEducationModel.setMajorName(majorName.getString(i));
                    userEducationModel.setEndDate(eduEndDate.getDate(i));
                    userEducationModel.setStartDate(eduStartDate.getDate(i));
                    userEducationModel.setHeadMaster(headMaster.getString(i));
                    userEducationModel.setEducationId(educationId.getInteger(i));
                    if (shcoolName.getString(i) == null || shcoolName.getString(i).equals("")) {
                        userEducationModel.setIsValid(false);
                    } else {
                        userEducationModel.setIsValid(true);
                    }

                    this.updateUserEducation(userEducationModel);
                }

            }

        }

    }

    @Override
    public void deleteUserEducation(int educationId, int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserEducationMapper educationDao = sqlSession.getMapper(UserEducationMapper.class);
            educationDao.deleteUserEducation(educationId, userId);

        }
    }

    @Override
    public void updateUserEducation(UserEducationModel educationModel) {
        try (SqlSession sqlSession = getSession()) {

            UserEducationMapper educationDao = sqlSession.getMapper(UserEducationMapper.class);
            educationDao.updateUserEducation(educationModel);

        }
    }

    @Override
    public List<Map<String, Object>> getUserPaperById(int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserPaperMapper paperDao = sqlSession.getMapper(UserPaperMapper.class);
            return paperDao.getUserPaperById(userId);

        }
    }

    @Override
    public void saveOrUpdateUserPaper(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserPaperMapper paperDao = sqlSession.getMapper(UserPaperMapper.class);
            UserPaperModel userPaperModel = new UserPaperModel();

            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);
                Date publicationDate = jo.getDate("publicationDate");
                String publicationName = jo.getString("publicationName");
                String publicationNum = jo.getString("publicationNum");
                String publicationFirstName = jo.getString("publicationFirstName");
                String publicationThisName = jo.getString("publicationThisName");
                Integer publicationId = jo.getInteger("publicationId");
                String docId = jo.getString("docId");
                if (publicationId == null || "".equals(publicationId)) {
                    userPaperModel.setFirstName(publicationFirstName);
                    userPaperModel.setPublicationDate(publicationDate);
                    userPaperModel.setThisName(publicationThisName);
                    userPaperModel.setName(publicationName);
                    userPaperModel.setPublicationNum(publicationNum);
                    userPaperModel.setUserId(getLoginSsoJson(token).getUserId());
                    userPaperModel.setPicture(docId);

                    paperDao.adduserPaper(userPaperModel);

                } else {
                    userPaperModel.setFirstName(publicationFirstName);
                    userPaperModel.setPublicationDate(publicationDate);
                    userPaperModel.setThisName(publicationThisName);
                    userPaperModel.setName(publicationName);
                    userPaperModel.setPublicationNum(publicationNum);
                    userPaperModel.setUserId(getLoginSsoJson(token).getUserId());
                    userPaperModel.setPicture(docId);
                    userPaperModel.setPaperId(publicationId);

                    updateUserPaper(userPaperModel);
                }
            }

        }
    }

    @Override
    public void deleteUserPaper(int paperId, int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserPaperMapper paperDao = sqlSession.getMapper(UserPaperMapper.class);
            paperDao.deleteUserPaper(paperId, userId);

        }
    }

    @Override
    public void updateUserPaper(UserPaperModel userPaperModel) {
        try (SqlSession sqlSession = getSession()) {

            UserPaperMapper paperDao = sqlSession.getMapper(UserPaperMapper.class);
            paperDao.updateUserPaper(userPaperModel);
        }
    }


    @Override
    public List<UserPatentModel> getUserPatentByIdAndType(Integer userId, Integer patentType) {
        try (SqlSession sqlSession = getSession()) {

            UserPatentMapper userPatentDao = sqlSession.getMapper(UserPatentMapper.class);
            return userPatentDao.getUserPatentByIdAndType(userId, patentType);
        }
    }

    @Override
    public Map<String, Object> getUserPatentById(Integer userId) {
        try (SqlSession sqlSession = getSession()) {

            UserPatentMapper userPatentDao = sqlSession.getMapper(UserPatentMapper.class);

            List<UserPatentModel> list = userPatentDao.getUserPatentById(userId);

            List<UserPatentModel> list1 = new ArrayList<>();
            List<UserPatentModel> list2 = new ArrayList<>();
            List<UserPatentModel> list3 = new ArrayList<>();
            List<UserPatentModel> list4 = new ArrayList<>();

            for (UserPatentModel patentModel : list) {
                if (patentModel.getPatentType() == 1) {
                    list1.add(patentModel);
                } else if (patentModel.getPatentType() == 2) {
                    list2.add(patentModel);
                } else if (patentModel.getPatentType() == 3) {
                    list3.add(patentModel);
                } else if (patentModel.getPatentType() == 4) {
                    list4.add(patentModel);
                }
            }

            Map<String, Object> map = new HashMap<>();

            map.put("sqfmList", list1);
            map.put("sqsyList", list2);
            map.put("sqwgList", list3);
            map.put("sqrjList", list4);
            return map;

        }

    }

    @Override
    public void saveOrUpdateUserPatent(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserPatentMapper userPatentDao = sqlSession.getMapper(UserPatentMapper.class);
            UserPatentModel userPatentModel = new UserPatentModel();
            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);

                String patentName = jo.getString("patentName");
                String patentNum = jo.getString("patentNum");
                String firstName = jo.getString("firstName");
                String thisName = jo.getString("thisName");
                Integer patentType = jo.getInteger("patentType");
                Integer patentId = jo.getInteger("patentId");
                String docId = jo.getString("docId");

                if (patentId == null || "".equals(patentId)) {
                    userPatentModel.setFirstName(firstName);
                    userPatentModel.setMyNameOrder(thisName);
                    userPatentModel.setPatentName(patentName);
                    userPatentModel.setPatentType(patentType);
                    userPatentModel.setPatentNumber(patentNum);
                    userPatentModel.setPictureUrl(docId);
                    userPatentModel.setUserId(getLoginSsoJson(token).getUserId());
                    userPatentDao.addUserPatent(userPatentModel);

                } else {
                    userPatentModel.setFirstName(firstName);
                    userPatentModel.setMyNameOrder(thisName);
                    userPatentModel.setPatentName(patentName);
                    userPatentModel.setPatentType(patentType);
                    userPatentModel.setPatentNumber(patentNum);
                    userPatentModel.setPictureUrl(docId);
                    userPatentModel.setUserId(getLoginSsoJson(token).getUserId());
                    userPatentModel.setPatentId(patentId);
                    updateUserPatent(userPatentModel);
                }
            }

        }
    }

    @Override
    public void deleteUserPatent(int userId, int patentId) {
        try (SqlSession sqlSession = getSession()) {

            UserPatentMapper userPatentDao = sqlSession.getMapper(UserPatentMapper.class);
            userPatentDao.deleteUserPatent(userId, patentId);
        }
    }

    @Override
    public void updateUserPatent(UserPatentModel userPatentModel) {
        try (SqlSession sqlSession = getSession()) {

            UserPatentMapper userPatentDao = sqlSession.getMapper(UserPatentMapper.class);
            userPatentDao.updateUserPatent(userPatentModel);

        }
    }

    @Override
    public JSONArray getUserTagById(int userId, int tagType) {
        try (SqlSession sqlSession = getSession()) {

            UserTagMapper userTagDao = sqlSession.getMapper(UserTagMapper.class);
            List<Map<String,Object>> list = userTagDao.getUserTagById(userId, tagType);
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
        }

    }


    //用户邦邦用户那边的标签
    @Override
    public List<UserTagModel> getUserTagById(int userId) {
        try (SqlSession sqlSession = getSession()) {
            UserTagMapper userTagDao = sqlSession.getMapper(UserTagMapper.class);
            return userTagDao.getUserTagToBB(userId);

        }
    }

    @Override
    public void addUserTag(UserTagModel userTagModel) {
        try (SqlSession sqlSession = getSession()) {

            UserTagMapper userTagDao = sqlSession.getMapper(UserTagMapper.class);
            userTagDao.addUserTag(userTagModel);
        }
    }

    @Override
    public void deleteUserTag(int userId, int userTagId) {
        try (SqlSession sqlSession = getSession()) {

            UserTagMapper userTagDao = sqlSession.getMapper(UserTagMapper.class);
            userTagDao.deleteUserTag(userId, userTagId);
        }

    }

    @Override
    public void updateUserTag(UserTagModel userTagModel) {
        try (SqlSession sqlSession = getSession()) {

            UserTagMapper userTagDao = sqlSession.getMapper(UserTagMapper.class);
            userTagDao.updateUserTag(userTagModel);
        }

    }

    @Override
    public List<UserWorkHistroyModel> getUserWorkHistroyListById(int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkHistroyMapper userWorkHistroyDao = sqlSession.getMapper(UserWorkHistroyMapper.class);
            return    userWorkHistroyDao.getUserWorkHistroyListById(userId);

        }
    }

    @Override
    public void saveOrUpdateUserWorkHistroy(JSONArray json, String token) {

        try (SqlSession sqlSession = getSession()) {

            UserWorkHistroyMapper userWorkHistroyDao = sqlSession.getMapper(UserWorkHistroyMapper.class);
            UserWorkHistroyModel userWorkHistroyModel = new UserWorkHistroyModel();

            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);

                Date datialStartDate = jo.getDate("datialStartDate");
                Date datialEndDate = jo.getDate("datialEndDate");
                String datialCompanyName = jo.getString("datialCompanyName");
                String department = jo.getString("department");
                String position = jo.getString("position");
                Integer detailId = jo.getInteger("detailId");
                String superiorName = jo.getString("superiorName");
                if (detailId == null || "".equals(detailId)) {
                    userWorkHistroyModel.setCompanyName(datialCompanyName);
                    userWorkHistroyModel.setStartDate(datialStartDate);
                    userWorkHistroyModel.setEndDate(datialEndDate);
                    userWorkHistroyModel.setDpt(department);
                    userWorkHistroyModel.setSuperName(superiorName);
                    userWorkHistroyModel.setDuty(position);
                    userWorkHistroyModel.setUserId(getLoginSsoJson(token).getUserId());//从cookie中获取
                    userWorkHistroyDao.addUserWorkHistroy(userWorkHistroyModel);

                } else {
                    userWorkHistroyModel.setCompanyName(datialCompanyName);
                    userWorkHistroyModel.setStartDate(datialStartDate);
                    userWorkHistroyModel.setEndDate(datialEndDate);
                    userWorkHistroyModel.setDpt(department);
                    userWorkHistroyModel.setSuperName(superiorName);
                    userWorkHistroyModel.setDuty(position);
                    userWorkHistroyModel.setWorkId(detailId);
                    userWorkHistroyModel.setUserId(getLoginSsoJson(token).getUserId());//从cookie中获取
                    updateUserWorkHistroy(userWorkHistroyModel);
                }
            }


        }
    }

    @Override
    public void deleteUserWorkHistroy(int userId, int workId) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkHistroyMapper userWorkHistroyDao = sqlSession.getMapper(UserWorkHistroyMapper.class);
            userWorkHistroyDao.deleteUserWorkHistroy(userId, workId);
        }
    }

    @Override
    public void updateUserWorkHistroy(UserWorkHistroyModel userWorkHistroyModel) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkHistroyMapper userWorkHistroyDao = sqlSession.getMapper(UserWorkHistroyMapper.class);
            userWorkHistroyDao.updateUserWorkHistroy(userWorkHistroyModel);
        }
    }

    @Override
    public List<Map<String, Object>> getUserWorkPrjList(int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkPrjMapper userWorkPrjDao = sqlSession.getMapper(UserWorkPrjMapper.class);
            return    userWorkPrjDao.getUserWorkPrjList(userId);
        }
    }

    @Override
    public void saveOrUpdateUserWorkPrj(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkPrjMapper userWorkPrjDao = sqlSession.getMapper(UserWorkPrjMapper.class);
            UserWorkPrjModel userWorkPrjModel = new UserWorkPrjModel();

            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);


                Date workInfoStartDate = jo.getDate("workInfoStartDate");
                Date workInfoEndDate = jo.getDate("workInfoEndDate");
                String workInfoCompanyName = jo.getString("workInfoCompanyName");
                String entryName = jo.getString("entryName");
                String projectLeader = jo.getString("projectLeader");
                String describe = jo.getString("describe");
                String docId = jo.getString("docId");
                Integer workInfoId = jo.getInteger("workInfoId");
                if (workInfoId == null || "".equals(workInfoId)) {
                    userWorkPrjModel.setStartDate(workInfoStartDate);
                    userWorkPrjModel.setEndDate(workInfoEndDate);
                    userWorkPrjModel.setCompanyName(workInfoCompanyName);
                    userWorkPrjModel.setPrjName(entryName);
                    userWorkPrjModel.setPrjLeader(projectLeader);
                    userWorkPrjModel.setDescribe(describe);
                    userWorkPrjModel.setUserId(getLoginSsoJson(token).getUserId());
                    userWorkPrjModel.setImage(docId);
                    userWorkPrjDao.addUserWorkPrj(userWorkPrjModel);

                } else {
                    userWorkPrjModel.setStartDate(workInfoStartDate);
                    userWorkPrjModel.setEndDate(workInfoEndDate);
                    userWorkPrjModel.setCompanyName(workInfoCompanyName);
                    userWorkPrjModel.setPrjName(entryName);
                    userWorkPrjModel.setPrjLeader(projectLeader);
                    userWorkPrjModel.setDescribe(describe);
                    userWorkPrjModel.setPrjId(workInfoId);
                    userWorkPrjModel.setImage(docId);
                    userWorkPrjModel.setUserId(getLoginSsoJson(token).getUserId());

                    updateUserWorkPrj(userWorkPrjModel);
                }

            }

        }
    }

    @Override
    public void deleteUserWorkPrj(int userId, int prjId) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkPrjMapper userWorkPrjDao = sqlSession.getMapper(UserWorkPrjMapper.class);
            userWorkPrjDao.deleteUserWorkPrj(userId, prjId);
        }
    }

    @Override
    public void updateUserWorkPrj(UserWorkPrjModel userWorkPrjModel) {
        try (SqlSession sqlSession = getSession()) {

            UserWorkPrjMapper userWorkPrjDao = sqlSession.getMapper(UserWorkPrjMapper.class);
            userWorkPrjDao.updateUserWorkPrj(userWorkPrjModel);
        }
    }

    @Override
    public UserInfoModel getUserInfoById(int userId) {
        try (SqlSession sqlSession = getSession()) {

             UserInfoMapper userInfoDao = sqlSession.getMapper(UserInfoMapper.class);
            return  userInfoDao.getUserInfoById(userId);
        }
    }

    @Override
    public int addUserInfo(UserInfoModel userInfoModel) {
        try (SqlSession sqlSession = getSession()) {

            UserInfoMapper userInfoDao = sqlSession.getMapper(UserInfoMapper.class);
            userInfoDao.addUserInfo(userInfoModel);
            return userInfoModel.getUserId();
        }
    }


    @Override
    public void updateUserInfo(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {

            String nickName = (String) map.get("nickName");
            String sex = (String) map.get("sex");
            String realName = (String) map.get("realName");
            Date userAge = (Date) map.get("userAge");
            String emaile = (String) map.get("email");
            String userAddress = (String) map.get("userAddress");
            String detailedAddress = (String) map.get("detailedAddress");
            String userHead = (String) map.get("userHead");
            Integer userId = (Integer) map.get("userId");

            Integer cityId = (Integer) map.get("cityId");
            Integer areaId = (Integer) map.get("areaId");
            Integer provinceId = (Integer) map.get("provinceId");

            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setSex(Integer.parseInt(sex));
            userInfoModel.setRealName(realName);
            userInfoModel.setNickName(nickName);
            userInfoModel.setBirthday(userAge);
            userInfoModel.setEmail(emaile);
            userInfoModel.setUserAddress(userAddress);
            userInfoModel.setUserId(userId);
            userInfoModel.setDetailedAddress(detailedAddress);
            userInfoModel.setProvinceId(provinceId);
            userInfoModel.setCityId(cityId);
            userInfoModel.setAreaId(areaId);
            if (userId == null) {

                addUserInfo(userInfoModel);

            } else {

                UserLoginModel userLoginModels = new UserLoginModel();
                userLoginModels.setUserId(userId);
                userLoginModels.setUserHead(userHead);
                updateByPrimaryKeySelective(userLoginModels);
                UserInfoMapper userInfoDao = sqlSession.getMapper(UserInfoMapper.class);
                userInfoDao.updatUserBaseInfo(userInfoModel);

            }


        }
    }


    @Override
    public String getUserImgInfo(int imgId) {

        try (SqlSession sqlSession = getSession()) {
            UserImgMapper userImgDao = sqlSession.getMapper(UserImgMapper.class);
            UserImgModel userImgModel = userImgDao.getUserImgInfo(imgId);

            byte[] myByte = userImgModel.getDocument();

            return Base64.encodeBase64String(myByte);

        }

    }

    @Override
    public void updateUserSelfEval(int userId, String text) {
        try (SqlSession sqlSession = getSession()) {

            UserInfoMapper userInfoDao = sqlSession.getMapper(UserInfoMapper.class);
            userInfoDao.updateUserSelfEval(userId, text);
        }
    }

    @Override
    public List<VTagSpecialtyModel> getVTagSpecialList() {
        return null;
    }

    @Override
    public List<VTagSpecialtyModel> getVTagSpecialChildList(int parentId) {
        return null;
    }

    @Override
    public void updateUserStatus(int userid, int status) {
        try (SqlSession sqlSession = getSession()) {
            UserInfoMapper userInfoDao = sqlSession.getMapper(UserInfoMapper.class);
            userInfoDao.updateUserStatus(userid, status);
        }
    }

    @Override
    public List<BBUserModel> getUserToBBNoPage(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {
            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);

            List<BBUserModel> list = userLoginDao.getUserLoginList(map);


            List<BBUserModel> returnList = new ArrayList<>();
            String[] str = null;

            for (BBUserModel bbUserModel : list) {
                BBUserModel bb = new BBUserModel();
                if (null != bbUserModel.getTagName() && !"".equals(bbUserModel.getTagName())) {
                    str = bbUserModel.getTagName().split(",");
                }

                bb.setTagNameArr(str);

                bb.setUserAddress(bbUserModel.getUserAddress());
                bb.setNickName(bbUserModel.getNickName());
                bb.setCompanyUserId(bbUserModel.getCompanyUserId());
                bb.setSex(bbUserModel.getSex());
                bb.setLoginMobile(bbUserModel.getLoginMobile());
                bb.setUserAge(bbUserModel.getUserAge());
                bb.setUserHead(bbUserModel.getUserHead());
                if (bbUserModel.getCreditGread() != null) {
                    bb.setCreditGread(bbUserModel.getCreditGread());
                } else {
                    bb.setCreditGread("B");
                }
                bb.setUserId(bbUserModel.getUserId());
                bb.setRegisteredTime(bbUserModel.getRegisteredTime());
                bb.setUserSigin(bbUserModel.getUserSigin());
                bb.setUserId(bbUserModel.getUserId());
                returnList.add(bb);
            }

            return returnList;
        }
    }

    @Override//获取用户接单范围
    public JSONArray getProjectScopeList(Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserProjectScodeMapper userProjectScodeDao = sqlSession.getMapper(UserProjectScodeMapper.class);
            List<Map<String, Object>> list = userProjectScodeDao.getProjectScopeList(userId, companyId);

            JSONArray json = parseListToArray(list);

            return json;
        }
    }

    @Override//新增用户接单范围
    public void addProjectScope(JSONArray json, String token/*Map<String,Object> map*/) {
        try (SqlSession sqlSession = getSession()) {

            UserProjectScopeModel userProjectScopeModel = new UserProjectScopeModel();
            UserProjectScodeMapper userProjectScodeDao = sqlSession.getMapper(UserProjectScodeMapper.class);
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                Integer scodeId = jo.getInteger("scodeId");
                Integer typeId = jo.getInteger("typeId");

                if (scodeId == null || "".equals(scodeId)) {

                    userProjectScopeModel.setTypeId(typeId);
                    userProjectScopeModel.setUserId(getLoginSsoJson(token).getUserId());
                    userProjectScopeModel.setCompanyId(getLoginSsoJson(token).getCompanyId());

                    userProjectScodeDao.addProjectScope(userProjectScopeModel);

                } else {

                    userProjectScopeModel.setTypeId(typeId);
                    userProjectScopeModel.setUserId(getLoginSsoJson(token).getUserId());
                    userProjectScopeModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    userProjectScopeModel.setScodeId(scodeId);

                    updateProjectScode(userProjectScopeModel);
                }
            }
        }
    }

    @Override
    public void updateProjectScode(UserProjectScopeModel userProjectScopeModel) {
        try (SqlSession sqlSession = getSession()) {

            UserProjectScodeMapper userProjectScodeDao = sqlSession.getMapper(UserProjectScodeMapper.class);
            userProjectScodeDao.updateProjectScode(userProjectScopeModel);
        }
    }

    @Override
    public void updateProjectScodeShowOrHide(Integer scodeId, Boolean isShow) {
        try (SqlSession sqlSession = getSession()) {


            UserProjectScodeMapper userProjectScodeDao = sqlSession.getMapper(UserProjectScodeMapper.class);
            userProjectScodeDao.updateProjectScodeShowOrHide(scodeId, isShow);
        }
    }

    @Override
    public void deleteProjectScode(Integer scodeId) {
        try (SqlSession sqlSession = getSession()) {

            UserProjectScodeMapper userProjectScodeDao = sqlSession.getMapper(UserProjectScodeMapper.class);
            userProjectScodeDao.deleteProjectScode(scodeId);
        }
    }


    @Override
    public List<PurchModel> getUserSellList(Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserSellMapper userSellDao = sqlSession.getMapper(UserSellMapper.class);
            List<Map<String, Object>> list = userSellDao.getUserSellList(userId, companyId);
            JSONArray jsonArray = parseListToArray(list);

            List<Map<String, Object>> idList = getUserSellType(userId, companyId);

            List<PurchModel> returnList = new ArrayList<>();

            for (Map idMap : idList) {

                PurchModel sellEntity = new PurchModel();
                List<Map<String, Object>> oList = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {

                    Integer typeId = (Integer) idMap.get("typeId");
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Map<String, Object> map = new HashMap<>();

                    if (typeId == jsonObject.getInteger("typeId")) {//判断内外层ID是否相等
                        map.put("createTime", jsonObject.getString("createTime"));
                        map.put("sellId", jsonObject.getString("sellId"));
                        map.put("companyId", jsonObject.getString("companyId"));
                        map.put("productName", jsonObject.getString("productName"));
                        map.put("productRule", jsonObject.getString("productRule"));
                        map.put("productFactory", jsonObject.getString("productFactory"));
                        map.put("typeId", jsonObject.getString("typeId"));
                        map.put("productPrice", jsonObject.getString("productPrice"));
                        map.put("docUrl", jsonObject.getString("docUrl"));
                        map.put("productPicUrl", jsonObject.getString("productPicUrl"));
                        map.put("docName", jsonObject.getString("docName"));
                        oList.add(map);
                        sellEntity.setHead(idMap.get("title").toString());
                        sellEntity.setDataList(oList);
                    }
                }
                returnList.add(sellEntity);
            }

            return returnList;
        }
    }

    @Override
    public List<Map<String, Object>> getUserSellType(Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserSellMapper userSellDao = sqlSession.getMapper(UserSellMapper.class);
            List<Map<String, Object>> list = userSellDao.getUserSellType(userId, companyId);

            return list;
        }
    }

    @Override
    public void saveOrUpdateUserSell(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserSellMapper userSellDao = sqlSession.getMapper(UserSellMapper.class);
            UserSellModel userSellModel = new UserSellModel();

            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = json.getJSONObject(i);
                Integer sellId = jo.getInteger("sellId");
                Integer typeId = jo.getInteger("typeId");
                String productName = jo.getString("productName");
                String productRule = jo.getString("productRule");
                String productFactory = jo.getString("productFactory");
                Double productPrice = jo.getDouble("productPrice");
                String imgId = jo.getString("imgId");
                String docId = jo.getString("docId");


                if (sellId == null || "".equals(sellId)) {

                    userSellModel.setTypeId(typeId);
                    userSellModel.setUserId(getLoginSsoJson(token).getUserId());
                    userSellModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    userSellModel.setProductName(productName);
                    userSellModel.setProductFactory(productFactory);
                    userSellModel.setDocUrl(docId);
                    userSellModel.setProductPicUrl(imgId);
                    userSellModel.setProductRule(productRule);
                    userSellModel.setProductPrice(productPrice);


                    userSellDao.addUserSell(userSellModel);

                } else {

                    userSellModel.setTypeId(typeId);
                    userSellModel.setUserId(getLoginSsoJson(token).getUserId());
                    userSellModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    userSellModel.setProductName(productName);
                    userSellModel.setProductFactory(productFactory);
                    userSellModel.setDocUrl(docId);
                    userSellModel.setProductPicUrl(imgId);
                    userSellModel.setProductRule(productRule);
                    userSellModel.setProductPrice(productPrice);
                    userSellModel.setSellId(sellId);

                    updateUserSell(userSellModel);
                }
            }
        }
    }

    @Override
    public void updateUserSell(UserSellModel userSellModel) {
        try (SqlSession sqlSession = getSession()) {
            UserSellMapper userSellDao = sqlSession.getMapper(UserSellMapper.class);
            userSellDao.updateUserSell(userSellModel);
        }
    }

    @Override
    public void deleteUserSell(int sellId) {
        try (SqlSession sqlSession = getSession()) {

            UserSellMapper userSellDao = sqlSession.getMapper(UserSellMapper.class);
            userSellDao.deleteUserSell(sellId);

        }
    }

    @Override
    public List<PurchModel> getUserPurchaseList(Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserPurchaseMapper userPurchaseDao = sqlSession.getMapper(UserPurchaseMapper.class);
            List<Map<String, Object>> list = userPurchaseDao.getUserPurchaseList(userId, companyId);
            JSONArray json = parseListToArray(list);


            List<Map<String, Object>> idList = getUserPurchaseType(userId, companyId);

            List<PurchModel> returnList = new ArrayList<>();

            for (Map idMap : idList) {

                PurchModel purchEntity = new PurchModel();
                List<Map<String, Object>> oList = new ArrayList<>();
                for (int i = 0; i < json.size(); i++) {

                    Integer typeId = (Integer) idMap.get("typeId");
                    JSONObject jsonObject = json.getJSONObject(i);
                    Map<String, Object> map = new HashMap<>();

                    if (typeId == jsonObject.getInteger("typeId")) {//判断内外层ID是否相等
                        map.put("createTime", jsonObject.getString("createTime"));
                        map.put("purchaseFactory", jsonObject.getString("purchaseFactory"));
                        map.put("purchaseId", jsonObject.getString("purchaseId"));
                        map.put("purchaseRule", jsonObject.getString("purchaseRule"));
                        map.put("isPrivate", jsonObject.getString("isPrivate"));
                        map.put("purchaseName", jsonObject.getString("purchaseName"));
                        map.put("typeId", jsonObject.getString("typeId"));
                        oList.add(map);
                        purchEntity.setHead(idMap.get("title").toString());
                        purchEntity.setDataList(oList);
                    }
                }
                returnList.add(purchEntity);
            }
            return returnList;
        }
    }

    @Override
    public List<Map<String, Object>> getUserPurchaseType(Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserPurchaseMapper userPurchaseDao = sqlSession.getMapper(UserPurchaseMapper.class);
            List<Map<String, Object>> list = userPurchaseDao.getUserPurchaseType(userId, companyId);
            return list;
        }
    }

    @Override
    public void saveOrUpdateUserPurchase(JSONArray json, String token) {
        try (SqlSession sqlSession = getSession()) {

            UserPurchaseMapper userPurchaseDao = sqlSession.getMapper(UserPurchaseMapper.class);
            UserPurchaseModel userPurchaseModel = new UserPurchaseModel();

            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = json.getJSONObject(i);

                String purchaseName = jo.getString("purchaseName");
                String purchaseRule = jo.getString("purchaseRule");
                String purchaseFactory = jo.getString("purchaseFactory");
                Integer purchaseTypeId = jo.getInteger("purchaseTypeId");
                Integer purchaseId = jo.getInteger("purchaseId");

                if (purchaseId == null || "".equals(purchaseId)) {

                    userPurchaseModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    userPurchaseModel.setUserId(getLoginSsoJson(token).getUserId());
                    userPurchaseModel.setPurchaseFactory(purchaseFactory);
                    userPurchaseModel.setPurchaseName(purchaseName);
                    userPurchaseModel.setPurchaseRule(purchaseRule);
                    userPurchaseModel.setTypeId(purchaseTypeId);

                    userPurchaseDao.addUserPurchase(userPurchaseModel);


                } else {

                    userPurchaseModel.setCompanyId(getLoginSsoJson(token).getCompanyId());
                    userPurchaseModel.setUserId(getLoginSsoJson(token).getUserId());
                    userPurchaseModel.setPurchaseFactory(purchaseFactory);
                    userPurchaseModel.setPurchaseName(purchaseName);
                    userPurchaseModel.setPurchaseRule(purchaseRule);
                    userPurchaseModel.setTypeId(purchaseTypeId);
                    userPurchaseModel.setPurchaseId(purchaseId);

                    userPurchaseDao.updateUserPurchase(userPurchaseModel);

                }

            }

        }
    }

    @Override
    public void deleteUserPurchase(Integer purchaseId) {
        try (SqlSession sqlSession = getSession()) {

            UserPurchaseMapper userPurchaseDao = sqlSession.getMapper(UserPurchaseMapper.class);
            userPurchaseDao.deleteUserPurchase(purchaseId);
        }
    }

    @Override
    public List<Map<String, Object>> selectGoodPeople() {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userLoginDao = sqlSession.getMapper(UserLoginMapper.class);
            return userLoginDao.selectGoodPeople();

        }
    }

    @Override
    public List<VTagSpecialtyModel> getTagList(Integer tagId, Integer tagType) {
        try (SqlSession sqlSession = getSession()) {

            VTagSpecialtyMapper tagDao = sqlSession.getMapper(VTagSpecialtyMapper.class);
            return tagDao.getVTagSpecialList(tagId, tagType);
        }
    }

    @Override
    public int insertUserBlack(UserBlackModel userBlackModel) {
        try (SqlSession sqlSession = getSession()) {


            UserBlackMapper tagDao = sqlSession.getMapper(UserBlackMapper.class);
            tagDao.insertUserBlack(userBlackModel);
            return userBlackModel.getUserId();
        }
    }

    @Override
    public List<Map<String, Object>> getUserBlackList(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {

            UserBlackMapper tagDao = sqlSession.getMapper(UserBlackMapper.class);

            return tagDao.getUserBlackList(map);
        }
    }

    @Override
    public void updateUserBlackList(int userId) {
        try (SqlSession sqlSession = getSession()) {

            UserBlackMapper tagDao = sqlSession.getMapper(UserBlackMapper.class);
            tagDao.updateUserBlackList(userId);
        }
    }

    @Override
    public int getUserBlackTotal(int userType) {
        try (SqlSession sqlSession = getSession()) {

            UserBlackMapper tagDao = sqlSession.getMapper(UserBlackMapper.class);
            return tagDao.getUserBlackTotal(userType);

        }
    }

    @Override
    public List<Map<String, Object>> getAttendList(Integer userId, Integer companyId) {
        try (SqlSession sqlSession = getSession()) {

            UserAttendMapper userAttendDao = sqlSession.getMapper(UserAttendMapper.class);
            return userAttendDao.getAttendList(userId, companyId);

        }
    }

    @Override
    public Integer addAttend(UserAttendModel userAttendModel) {
        try (SqlSession sqlSession = getSession()) {

            UserAttendMapper userAttendDao = sqlSession.getMapper(UserAttendMapper.class);
            userAttendDao.addAttend(userAttendModel);
            return userAttendModel.getAttendId();
        }
    }

    @Override
    public Integer getAttendRepeat(Integer userId, Integer comapnyId, Integer toAttendId) {
        try (SqlSession sqlSession = getSession()) {

            UserAttendMapper userAttendDao = sqlSession.getMapper(UserAttendMapper.class);
            return userAttendDao.getAttendRepeat(userId, comapnyId, toAttendId);

        }
    }

    @Override
    public void deleteUserAttend(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {
            UserAttendMapper userAttendDao = sqlSession.getMapper(UserAttendMapper.class);
            userAttendDao.deleteUserAttend(map);
        }
    }

    @Override
    public void updateUserPhone(Integer userId, String newPhone) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userAttendDao = sqlSession.getMapper(UserLoginMapper.class);
            userAttendDao.updateUserPhone(userId, newPhone);
        }
    }

    @Override
    public void updateUserPassword(Integer userId, String newPwd) {
        try (SqlSession sqlSession = getSession()) {

            UserLoginMapper userAttendDao = sqlSession.getMapper(UserLoginMapper.class);
            userAttendDao.updateUserPassword(userId, newPwd);
        }
    }

    @Override
    public void updateSellViewCountById(Integer sellID) {
        try (SqlSession sqlSession = getSession()) {

            UserSellMapper userAttendDao = sqlSession.getMapper(UserSellMapper.class);
            userAttendDao.updateViewCountById(sellID);
        }
    }

    @Override
    public void updatePurchaseViewNumberById(Integer purchaseId) {
        try (SqlSession sqlSession = getSession()) {

            UserPurchaseMapper userAttendDao = sqlSession.getMapper(UserPurchaseMapper.class);
            userAttendDao.updatePurchaseViewNumberById(purchaseId);
        }
    }

    @Override
    public List<Map<String, Object>> getUserListToBB(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {

            UserInfoMapper userAttendDao = sqlSession.getMapper(UserInfoMapper.class);
            Integer sex = (Integer) map.get("sex");
            if(sex == 0){
                map.put("sexStart",0);
                map.put("sexEnd",2);
            }else{
                map.put("sexStart",sex);
                map.put("sexEnd",sex);
            }
            return userAttendDao.getUserListToBB(map);
        }
    }

    @Override
    public int getUserListToBBCount(Map<String, Object> map) {
        try (SqlSession sqlSession = getSession()) {
            UserInfoMapper userAttendDao = sqlSession.getMapper(UserInfoMapper.class);
            return userAttendDao.getUserListToBBCount(map);
        }
    }

    @Override
    public List<UserInfoModel> getUsetInfoByIds(List<Integer> ids) {
        try (SqlSession sqlSession = getSession()) {
            UserInfoMapper userAttendDao = sqlSession.getMapper(UserInfoMapper.class);
            return userAttendDao.getUsetInfoByIds(ids);
        }
    }


    private JSONArray parseListToArray(List<Map<String, Object>> list) {
        JSONArray jsonArr = new JSONArray();
        for (Map map : list) {
            if (map.get("parentName") == null || "".equals(map.get("parentName"))) {
                map.put("head", map.get("title"));
            } else {
                map.put("head", map.get("parentName") + "-" + map.get("title"));
            }
            jsonArr.add(map);
        }
        return jsonArr;
    }


}
