package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.FriendApplyInfoDao;
import ytb.bangbang.dao.RecordUserDao;
import ytb.bangbang.dao.UserFriendsDao;
import ytb.bangbang.model.Friend_Apply_InfoModel;
import ytb.bangbang.model.FriendsInfoModel;
import ytb.bangbang.model.Message;
import ytb.bangbang.service.MessageService;
import ytb.bangbang.service.UserFriendService;
import ytb.bangbang.util.DictionaryData;
import ytb.bangbang.util.MyBatisUtil;
import ytb.user.model.UserInfoModel;
import ytb.user.model.UserLoginModel;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class UserFriendServiceImpl implements UserFriendService {
    /**
     * 申请添加好友
     */
    @Override
    public int applyAddFriend( int userId,int friendId,int friendsTypeId) {
        //创建session

        try(SqlSession session=MyBatisUtil.getSession()){
            //查询好友是否存在数据库中
            UserCenterService userLoginService= new UserCenterServiceImpl();
            UserLoginModel friendLoginModel=userLoginService.getUserLoginInfoById(friendId);
            if (null == friendLoginModel) {
                return 9;//用户不存在
            }
            UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
            //自己已将对方加为了好友
            int num=userFriendsDao.IsFriend(userId,friendId);
            if (num>0) {
                //已是好友
                return 11;//记录已存在
            }
            FriendApplyInfoDao friendApplyInfoDao=session.getMapper(FriendApplyInfoDao.class);
//            Friend_Apply_InfoModel apply_infoModel=friendApplyInfoDao.GetFriendApplyInfo(userId, friendId);
//            num =friendApplyInfoDao.IsExistence(userId, friendId);
//            if(num>0){
//                if(apply_infoModel.getIsAgree()>2){
//                    friendApplyInfoDao.setIsAgree(userId,friendId,1);
//                    return 0;
//                }
//                //已经申请了
//                return 11;//记录已存在
//            }
            Friend_Apply_InfoModel friendApplyInfoModel = new Friend_Apply_InfoModel();
            friendApplyInfoModel.setUserID(userId);
            friendApplyInfoModel.setToUserID(friendId);
            friendApplyInfoModel.setFriendsTypeId(friendsTypeId);
            friendApplyInfoDao.AddRecord(friendApplyInfoModel);//要加锁

            MessageService messageService = new MessageServiceImpl();
            Message message = new Message();
            message.setType(1);
            message.setReceiveUserId(friendId);
            message.setRead(1);
            message.setInviteId(friendApplyInfoModel.getApply_id());
            messageService.addMessage(message);//通知表插入一条数据

            Message messageu = new Message();
            messageu.setType(1);
            messageu.setReceiveUserId(userId);
            messageu.setRead(2);
            messageu.setInviteId(friendApplyInfoModel.getApply_id());
            messageService.addMessage(messageu);

            return 0;
        }catch (Exception e){

            return 1;
        }
    }

    /**
     * 是否同意添加好友
     */
    @Override
    public int isAddFriend(int userId,int friendId,int isAgree,int friendsTypeId,int inviteId) {
        //创建session
        SqlSession session=null;
        try{
            session= MyBatisUtil.getSession();
            FriendApplyInfoDao friendApplyInfoDao=session.getMapper(FriendApplyInfoDao.class);
            UserFriendsDao userFriendsDao = session.getMapper(UserFriendsDao.class);
            //开始执行相关逻辑操作，(其实可以考虑提取到逻辑层中)
            if (isAgree == DictionaryData.ISAGREEOK) {
                int num= userFriendsDao.IsFriend(userId,friendId);
                if(num>0){
                    //已成为好友
                    return 11;//记录已存在
                }
                //设置当前记录为已同意操作
                friendApplyInfoDao.setIsAgree(inviteId, DictionaryData.ISAGREEOK);//A的申请信息
                UserCenterService userLoginService = new UserCenterServiceImpl();
                UserInfoModel friendLoginModel =  userLoginService.getUserInfoById(friendId);
                UserInfoModel  userLoginModel = userLoginService.getUserInfoById(userId);
                String userNickName = userLoginModel.getNickName();
                String friendNickName = friendLoginModel.getNickName();
                //调用添加好友数据层；
                userFriendsDao.AddRecord(userId, friendId, friendNickName,friendsTypeId);

                Friend_Apply_InfoModel model=friendApplyInfoDao.getFriendApplyModel(inviteId);

                userFriendsDao.AddRecord(friendId, userId, userNickName,model.getFriendsTypeId());

            }
            //拒绝
            if(isAgree == DictionaryData.ISAGREENOTE){
                friendApplyInfoDao.setIsAgree(inviteId, DictionaryData.ISAGREENOTE);
//                friendApplyInfoDao.DeleteApplyInfo(userId,friendId);
//                friendApplyInfoDao.DeleteApplyInfo(friendId,userId);
            }
                return 0;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            session.close();
            return 1;
        }finally{
            if(session!=null)
            session.close();
        }

    }

    /**
     * 删除好友
     */
    @Override
    public int deleteUserFriend(int userId,int friendId) {
        //创建session
        SqlSession session=null;
        try{
            session= MyBatisUtil.getSession();
            UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
            //删除好友记录信息
            userFriendsDao.DeleteRecord(userId, friendId);
            userFriendsDao.DeleteRecord(friendId,userId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            session.close();
            return 1;
        }finally {
            if(session!=null)
            session.close();
        }
    }

    /**
     * 获取申请记录
     * @param toUserId
     * @return
     */
    @Override
    public  List GetApplyUserFriend(int toUserId) {
        SqlSession session=null;
        List<ApplyUserFriend> result = new ArrayList<>();
        try {
            //创建session
             session = MyBatisUtil.getSession();
            FriendApplyInfoDao friendApplyInfoDao = session.getMapper(FriendApplyInfoDao.class);
            //获取好友申请记录信息
            List<Friend_Apply_InfoModel> friendApplyInfoList = friendApplyInfoDao.GetRecieveList(toUserId);
            //通过好友申请ID获取所有好友记录信息
            UserCenterService userLoginService = new UserCenterServiceImpl();
            // 返回出对应的好友信息
            for (Friend_Apply_InfoModel friendApplyInfo : friendApplyInfoList) {
                UserInfoModel userInfoModel = userLoginService.getUserInfoById(friendApplyInfo.getUserID());
                ApplyUserFriend applyUserFriend = new ApplyUserFriend();
                applyUserFriend.id = userInfoModel.getUserId();
                applyUserFriend.avatar = userInfoModel.getUserHead();
                applyUserFriend.nickName = userInfoModel.getNickName();
                applyUserFriend.sign = userInfoModel.getUserSign();
                applyUserFriend.IsOnline = userInfoModel.getIsOnline();
                applyUserFriend.isAgree = friendApplyInfo.getIsAgree();
                result.add(applyUserFriend);
            }
            //输出结果信息到客户端
            session.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    /**
     * 添加聊天记录
     * @param userId
     * @param content
     * @param friendId
     * @return
     */
    @Override
    public int AddRecordUser(int userId,String content ,int friendId){
        SqlSession session=null;
        try{
            session= MyBatisUtil.getSession();
            RecordUserDao recordUserDao=session.getMapper(RecordUserDao.class);
            int row = recordUserDao.AddRecordUser(userId,content,friendId);
            if(row<=0) {
                    return 1;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    /*
     * 功能：获取全部好友
     * 参数：
     * 	userId :用户id
     */
    @Override
   public List<FriendsInfoModel> GetUserFriend(int userId,String remarks){
       SqlSession session=null;
       List<FriendsInfoModel> friendsModelList=new ArrayList<>();
       try{
           session= MyBatisUtil.getSession();
           UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
           friendsModelList = userFriendsDao.GetUserFriend(userId,remarks);
           return friendsModelList;
       }catch (Exception e){
           e.printStackTrace();
           return friendsModelList;
       }finally {
           if(session!=null)
               session.close();
       }
    }
    /**
     * 好友申请7天过期
     * @param userId
     */
    @Override
   public void FriendsApplyIgnore(int userId){
        SqlSession session=null;
        try{
            session= MyBatisUtil.getSession();
            FriendApplyInfoDao friendApplyInfoDao=session.getMapper(FriendApplyInfoDao.class);
            friendApplyInfoDao.FriendApplyIgnore(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
   }

    @Override
    public void editFriendTyp(int userId, int friendId, int friendsTypeId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
            userFriendsDao.editFriendTyp(userId,friendId,friendsTypeId);
        }
    }

    @Override
    public int existFriends(int userId, int friendsTypeId) {
       try (SqlSession session=MyBatisUtil.getSession()){
           UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
           return userFriendsDao.existFriends(userId,friendsTypeId);
       }
    }

    @Override
    public int removeFriend(int userId, int friendsTypeId, int moveGroupId) {
        try (SqlSession session=MyBatisUtil.getSession()){
            UserFriendsDao userFriendsDao=session.getMapper(UserFriendsDao.class);
            return userFriendsDao.removeFriend(userId,friendsTypeId,moveGroupId);
        }
    }

    //整合查询出来的好友申请信息返回
   private class ApplyUserFriend {
        public int id = 0;
        public String avatar="";
        public String nickName = "";
        public String sign = "";
        public boolean IsOnline = false;
        public int isAgree;
    }
}
