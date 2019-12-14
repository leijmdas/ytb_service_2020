package ytb.bangbang.service;

import ytb.bangbang.model.Friend_Apply_InfoModel;

public interface FriendApplyInfoService {

    int getApplyId(int userId,int toUserId,int friendsTypeId);

    Friend_Apply_InfoModel getFriendApplyInfoByAppId(int inviteId);

    Friend_Apply_InfoModel getFriendApplyModel(int inviteId);
}
