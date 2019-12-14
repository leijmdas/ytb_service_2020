package ytb.bangbang.service;

import ytb.bangbang.model.Answer;

/**
 * tanc
 * 2019/3/12
 */
public interface AnswerService {
    int addAnswer(Answer answer);

    void setIsRead(int inviteId,int toUserId);
}
