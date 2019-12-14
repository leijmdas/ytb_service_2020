package ytb.bangbang.service;

/**
 * 设置用户备注信息
 */
public interface SetUserRemarksService {
    int  setUserRemarks(int userId,int friendId,String remarks);
}
