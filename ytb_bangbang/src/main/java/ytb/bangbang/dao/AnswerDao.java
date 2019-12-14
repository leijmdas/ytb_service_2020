package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.Answer;

public interface AnswerDao {
    /**
     * 添加回复消息
     * @param answer
     * @return
     */
    int addAnswer(Answer answer);

    int setIsRead(@Param("inviteId") int inviteId,@Param("toUserId") int toUserId);
}
