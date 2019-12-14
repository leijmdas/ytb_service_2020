/*****************************************************************************************************
Author		:
Date		:2018/6/19
Description	:
Update		:
Author			Date				Description			
*****************************************************************************************************/

package ytb.bangbang.model;
/// <summary>
/// db.ytb_bangbang.model
/// </summary>
public class Group_UserModel
{
	private  int  Group_user_id =  0 ;
	private  int  UserId =  0 ;
	private  int  GroupId =  0 ;
	private  String  CreateTime =  null ;
	private  int  GroupUserType =  0 ;
    private Group_InfoModel groupInfoModel;

	/* 
	 * 群用户关联表
	 */
	public  int getGroup_user_id ()
	{ 
		return this.Group_user_id ;
	} 
	public void setGroup_user_id ( int  Group_user_id)
	{ 
		this.Group_user_id = Group_user_id ;
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

	/* 
	 * 群组id
	 */
	public  int getGroupId () 
	{ 
		return this.GroupId ;
	} 
	public void setGroupId ( int  GroupId) 
	{ 
		this.GroupId = GroupId ;
	} 

	/* 
	 * 创建时间
	 */
	public  String getCreateTime ()
	{ 
		return this.CreateTime ;
	}

	/* 
	 * 用户在群组中的身份(1:群主;2:管理员;3:群成员)
	 */
	public  int getGroupUserType () 
	{ 
		return this.GroupUserType ;
	} 
	public void setGroupUserType ( int  GroupUserType) 
	{ 
		this.GroupUserType = GroupUserType ;
	}

	public Group_InfoModel getGroupInfoModel() {
		return groupInfoModel;
	}

	public void setGroupInfoModel(Group_InfoModel groupInfoModel) {
		this.groupInfoModel = groupInfoModel;
	}

	/// <summary>
	/// 构造函数
	/// </summary>
	public Group_UserModel(){;}
}

