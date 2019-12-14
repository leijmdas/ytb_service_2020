package ytb.user.dao;

import ytb.user.model.UserImgModel;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/25 15:44
 */
public interface UserImgMapper {
    //新增用户图片文档资料
    int addUserImg(UserImgModel userImgModel);
    void deleteUserImg(int imgId);

    //获取用户图片
    UserImgModel getUserImgInfo(int imgId);
}
