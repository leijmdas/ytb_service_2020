package ytb.bangbang.service;

import java.util.Map;

/**
 * 获取用户详情资料信息
 * data : 数据
 * 基本资料
 * 专业标签
 * 最高学历
 * errorMsg : 错误信息
 */
public interface GetUserInfoService {
    Map getUserInfo(int userID);
}
