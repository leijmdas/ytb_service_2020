package ytb.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import ytb.user.model.*;

import java.util.List;
import java.util.Map;

/**
 * 用户中心Service
 * Package: ytb.user.service
 * Author: ZCS
 * Date: Created in 2018/9/6 9:52
 */
public interface UserCenterService {

    List<UserLoginModel> getUserLoginModelByUserType(int userType);

    //获取用户基本资料
    Map<String, Object> getUserBaseInfo(int userId);

    Map<String, Object> getFCityByAreaId(int areaId);

    //邦邦设置个性签名
    void setUserSign(String userSgin, int userId);

    //获取用户的学生论文
    List<StudentPaperModel> getStudentPaperListById(int userId);

    //添加学生论文
    void addStudentPaper(JSONArray json, String token);

    //删除学生论文
    void deleteStudentPaper(int paperId, int userId);

    //修改学生论文信息
    void updateStudentPaper(StudentPaperModel studentPaperModel);

    //获取用户的收件地址
    List<UserAddressModel> getUserAddressListById(Map<String, Object> map);

    //新增用户收件地址
    Integer addUserAddress(UserAddressModel userAddressModel);

    //删除用户收件地址
    void deleteUserAddress(Integer addressId, Integer userId, Integer companyId);

    //修改用户收件地址
    void updateUserAddress(UserAddressModel userAddressModel);

    //设置默认收件地址
    void setDefaultAddr(int addressId, int companyId, boolean isDefault);


    //查询总数
    Integer queryUserAddressTotal(Map<String, Object> map);


    //获取用户奖励列表
    List<UserAwardModel> getUserAwardListById(int userId);

    //新增用户奖励
    void saveOrUpdateUserAward(JSONArray json, String token);

    //删除用户奖励
    void deleteUserAward(int userId, int awardId);

    //修改用户奖励
    void updateUserAward(UserAwardModel userAwardModel);

    //获取个人教育经历
    List<Map<String,Object>> getUserEducationById(int userId);

    //前台个人资料教育经历显示
    List<UserEducationModel> getUserEduById(int userId);

    //新增个人教育经历
    void addUserEducation(JSONObject json, String token);

    //删除个人教育经历
    void deleteUserEducation(int educationId, int userId);

    //修改个人教育经历
    void updateUserEducation(UserEducationModel educationModel);

    //获取个人刊物论文信息
    List<Map<String,Object>> getUserPaperById(int userId);

    //添加个人刊物论文信息
    void saveOrUpdateUserPaper(JSONArray json, String token);

    //删除个人刊物论文信息
    void deleteUserPaper(int paperId, int userId);

    //修改个人刊物论文信息、
    void updateUserPaper(UserPaperModel userPaperModel);

    //获取个人专利列表分类别
    List<UserPatentModel> getUserPatentByIdAndType(Integer userId, Integer patentType);

    //获取个人专利列表分类别
    Map<String, Object> getUserPatentById(Integer userId);


    //新增个人专利
    void saveOrUpdateUserPatent(JSONArray json, String token);

    //删除个人专利
    void deleteUserPatent(int userId, int patentId);

    //修改个人专利
    void updateUserPatent(UserPatentModel userPatentModel);

    //获取用户的专业标签
    JSONArray getUserTagById(int userId, int tagType);

    List<UserTagModel> getUserTagById(int userId);

    //添加用户标签
    void addUserTag(UserTagModel userTagModel);

    //删除用户标签
    void deleteUserTag(int userId, int userTagId);

    //修改用户标签
    void updateUserTag(UserTagModel userTagModel);

    //获取个人简历表
    List<UserWorkHistroyModel> getUserWorkHistroyListById(int userId);

    //添加个人简历
    void saveOrUpdateUserWorkHistroy(JSONArray json, String token);

    //删除个人简历
    void deleteUserWorkHistroy(int userId, int workId);

    //修改个人简历
    void updateUserWorkHistroy(UserWorkHistroyModel userWorkHistroyModel);


    //获取个人工作业绩列表
    List<Map<String,Object>> getUserWorkPrjList(int userId);

    //添加个人工作业绩
    void saveOrUpdateUserWorkPrj(JSONArray json, String token);

    //删除个人工作业绩
    void deleteUserWorkPrj(int userId, int prjId);

    //修改个人工作业绩
    void updateUserWorkPrj(UserWorkPrjModel userWorkPrjModel);


    //获取用户基本信息
    UserInfoModel getUserInfoById(int userId);

    //添加用户信息
    int addUserInfo(UserInfoModel userInfoModel);

    //修改用户信息
    void updateUserInfo(Map<String,Object> map);


    UserLoginModel getUserLoginInfoById(int userId);

    UserLoginModel getUserLoginInfoByLoginMobile(String username);

    List<UserLoginModel> getUserLoginInfoByLoginMobileAndUserTypeList(String loginMobile, int userType);


    List<UserLoginModel> getUserLoginInfoByLoginMobileAndUserTypeAndPasswordList(String loginMobile, int userType, String password);


    int modifyPassword(Integer loginMobile, int userType, String password, String oldPassword);


    Boolean modifyPhone(Integer userId, String newLoginMobile, String password, String oldLoginMobile);

    UserLoginModel getUserLoginInfoByLoginMobileAndUserType(String loginMobile, int userType);


    int updateByPrimaryKeySelective(UserLoginModel userLoginModel);

    int insertSelective(UserLoginModel userLoginModel);


    int newUser(UserLoginModel userLoginModel, Integer areaId);

    int newComUser(UserLoginModel userLoginModel, CompanyInfoModel companyInfoModel, Integer areaId);

    //获取用户上传图片
    String getUserImgInfo(int imgId);


    //设置用户的自我评价
    void updateUserSelfEval(int userId, String text);

    //获取专业能力标签
    List<VTagSpecialtyModel> getVTagSpecialList();

    //获取专业能力标签
    List<VTagSpecialtyModel> getVTagSpecialChildList(int parentId);

    //修改用户审核之后的状态
    void updateUserStatus(int userid, int status);

    //邦邦获取平台所有用户未分页
    List<BBUserModel> /*List<Map<String,Object>>*/ getUserToBBNoPage(Map<String, Object> map);


    //用户接单范围
    JSONArray getProjectScopeList(Integer userId, Integer companyId);

    //新增用户接单范围
    void addProjectScope(JSONArray json, String token/*Map<String,Object> map*/);

    //修改接单范围
    void updateProjectScode(UserProjectScopeModel userProjectScopeModel);
    void updateProjectScodeShowOrHide(Integer scodeId,Boolean isShow);
    //删除接单范围
    void deleteProjectScode(Integer scodeId);


    //获取推广销售清单
    List<PurchModel> getUserSellList(Integer userId, Integer companyId);

    //获取类别（用于数据组装）
    List<Map<String, Object>> getUserSellType(Integer userId, Integer companyId);

    //新增推广销售清单
    void saveOrUpdateUserSell(JSONArray json, String token);

    //修改推广销售清单
    void updateUserSell(UserSellModel userSellModel);

    //删除推广销售清单
    void deleteUserSell(int sellId);

    //获取意向采购物品清单
    List<PurchModel> getUserPurchaseList(Integer userId, Integer companyId);

    //获取意向采购清单的范围
    List<Map<String, Object>> getUserPurchaseType(Integer userId, Integer companyId);

    //新增或者修改意向采购清单
    void saveOrUpdateUserPurchase(JSONArray json, String token);


    //删除意向采购清单
    void deleteUserPurchase(Integer purchaseId);

    //查询优质人才
    List<Map<String, Object>> selectGoodPeople();


    //用户标签
    List<VTagSpecialtyModel> getTagList(Integer tagId, Integer tagType);


    //新增黑名单
    int insertUserBlack(UserBlackModel userBlackModel);

    //查看黑名单
    List<Map<String, Object>> getUserBlackList(Map<String, Object> map);

    //修改黑明单
    void updateUserBlackList(@Param("userId") int userId);

    int getUserBlackTotal(int userType);


    //获取关注的列表
    List<Map<String,Object>> getAttendList(@Param("userId") Integer userId,@Param("companyId") Integer companyId);

    //新增关注
    Integer addAttend(UserAttendModel userAttendModel);

    //判断是否重复关注
    Integer getAttendRepeat(Integer userId,Integer comapnyId,Integer toAttendId);

    //取消关注
    void deleteUserAttend(Map<String,Object> map);

    //绑定手机号
    void updateUserPhone(Integer userId,String newPhone);

    //修改密码
    void updateUserPassword(Integer userId,String newPwd);

    //设置推广销售清单浏览数量
    void updateSellViewCountById(Integer sellID);

    //设置意向采购清单浏览数量
    void updatePurchaseViewNumberById(Integer purchaseId);

    //邦邦添加好友列表数据
    List<Map<String,Object>> getUserListToBB(Map<String,Object> map);

    int getUserListToBBCount(Map<String,Object> map);

    //批量获取用户
    List<UserInfoModel> getUsetInfoByIds(List<Integer> ids);
}
