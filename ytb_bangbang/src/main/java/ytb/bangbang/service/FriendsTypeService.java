package ytb.bangbang.service;

import ytb.bangbang.model.FriendsType;

public interface FriendsTypeService {

    void addFriendsType(FriendsType friendsType);

    void editFriendsType(int friendsTypeId,String groupName);

    void delFriendsType(int friendsTypeId);

    int getFriendsTypeCountsById(int userId);

    FriendsType getUserIdByType(int friendsTypeId);

    int existFriendsType(int friendsTypeId,int userId);
}
