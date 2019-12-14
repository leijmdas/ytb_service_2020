/*****************************************************************************************************
 Author		:
 Date		:2018/6/28
 Description	:
 Update		:
 Author			Date				Description
 *****************************************************************************************************/

package ytb.bangbang.model;

import java.util.List;

/// <summary>
/// db.ytb_bangbang.model
/// </summary>
public class Friend_Apply_InfoModel
{
    private  int  Apply_id =  0 ;
    private  int  UserID =  0 ;
    private  int  ToUserID =  0 ;
    private  String  CreateTime =  null;
    private  int  IsAgree ;
    private  int friendsTypeId;//朋友分组Id
    private List<Answer> answers;//回复
    private String userNickName;
    private String friendNickName;


    /*
     * 好友申请记录表
     */
    public  int getApply_id ()
    {
        return this.Apply_id ;
    }
    public void setApply_id ( int  Apply_id)
    {
        this.Apply_id = Apply_id ;
    }

    /*
     * 申请人ID
     */
    public  int getUserID()
    {
        return this.UserID ;
    }
    public void setUserID ( int  UserID)
    {
        this.UserID = UserID ;
    }

    /*
     * 被申请人
     */
    public  int getToUserID ()
    {
        return this.ToUserID ;
    }
    public void setToUserID ( int  ToUserID)
    {
        this.ToUserID = ToUserID ;
    }

    /*
     * 申请时间
     */
    public  String getCreateTime ()
    {
        return this.CreateTime ;
    }

    /**
     * 是否同意 0：查询所有  1：等待审核 2：同意  3：拒绝
     * @return
     */
    public int getIsAgree() {
        return IsAgree;
    }

    public void setIsAgree(int IsAgree) {
        this.IsAgree = IsAgree;
    }

    public int getFriendsTypeId() {
        return friendsTypeId;
    }

    public void setFriendsTypeId(int friendsTypeId) {
        this.friendsTypeId = friendsTypeId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    /// <summary>
    /// 构造函数
    /// </summary>
    public Friend_Apply_InfoModel(){;}
}
