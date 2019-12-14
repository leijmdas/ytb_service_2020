/*****************************************************************************************************
Author		:
Date		:2018/6/19
Description	:
Update		:
Author			Date				Description			
*****************************************************************************************************/

package ytb.bangbang.model;
import java.util.Date;  


/// <summary>
/// db.ytb_bangbang.model
/// </summary>
public class User_FriendsModel
{
	private  int  User_rltn_id =  0 ;
	private  int  FriendId =  0 ;
	private  int  UserId =  0 ;
	private String Remarks = "";


	/* 
	 * 好友表
	 */
	public  int getUser_rltn_id ()
	{ 
		return this.User_rltn_id ;
	} 
	public void setUser_rltn_id ( int  User_rltn_id)
	{ 
		this.User_rltn_id = User_rltn_id ;
	} 

	/* 
	 * 好友id
	 */
	public  int getFriendId () 
	{ 
		return this.FriendId ;
	} 
	public void setFriendId ( int  FriendId) 
	{ 
		this.FriendId = FriendId ;
	} 

	/* 
	 * 用户id
	 */
	public  int getUserId () 
	{ 
		return this.UserId ;
	} 
	public void setUserId ( int  UserId) 
	{ 
		this.UserId = UserId ;
	}

	/**
	 * 备注
	 * @return
	 */
	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	/// <summary>
	/// 构造函数
	/// </summary>
	public User_FriendsModel(){;}
}

