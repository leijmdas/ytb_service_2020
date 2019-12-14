package ytb.user.dao;

import ytb.user.model.UserEducationModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/4 17:47
 */
public interface UserEducationMapper {
    //获取个人教育经历
    List<Map<String,Object>> getUserEducationById(int userId);

    //新增个人教育经历
    void addUserEducation(UserEducationModel educationModel);

    //删除个人教育经历
    void deleteUserEducation(int educationId,int userId);

    //修改个人教育经历
    void updateUserEducation(UserEducationModel educationModel);

    //获取个人教育经历
    List<UserEducationModel>  getUserEduById(int userId);
}
