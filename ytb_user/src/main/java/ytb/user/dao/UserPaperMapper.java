package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.UserPaperModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/4 17:44
 */
public interface UserPaperMapper {
    //获取个人刊物论文信息
    List<Map<String,Object>> getUserPaperById (int userId);

    //添加个人刊物论文信息
    void adduserPaper(UserPaperModel userPaperModel);

    //删除个人刊物论文信息
    void deleteUserPaper(@Param("paperId") int paperId,@Param("userId") int userId);

    //修改个人刊物论文信息、
    void updateUserPaper(UserPaperModel userPaperModel);
}
