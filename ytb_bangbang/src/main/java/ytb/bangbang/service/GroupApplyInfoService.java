package ytb.bangbang.service;

import ytb.bangbang.model.Group_Apply_InfoModel;

public interface GroupApplyInfoService {
    int AddRecord(Group_Apply_InfoModel group_apply_infoModel);

    int setIsAgree(Group_Apply_InfoModel group_apply_infoModel);

    Group_Apply_InfoModel getUserApplyGroupInfo(int inviteId);

    Integer getApplyTypeById(int id);

    int changeIsAgree(int inviteStatus,int id);
}
