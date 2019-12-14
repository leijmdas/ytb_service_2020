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
public class Record_GroupModel
{
	private  int  Record_id =  0 ;
	private  int  GroupId =  0 ;
	private  String  Content =  "" ;
	private  int  FromUser =  0 ;
	private  String  CreateTime =null;
	private  String fromUserName;


	/* 
	 * 群消息内容表
	 */
	public  int getRecord_id ()
	{ 
		return this.Record_id ;
	} 
	public void setRecord_id ( int  Record_id)
	{ 
		this.Record_id = Record_id ;
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
	 * 消息内容
	 */
	public String getContent() {
		return this.Content;
	}

	public void setContent(String Content) {
		this.Content = Content;
	}

	/*
	 * 发送者id
	 */
	public int getFromUser() {
		return this.FromUser;
	}

	public void setFromUser(int FromUser) {
		this.FromUser = FromUser;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/*
	 * 发送时间
	 */
	public  String getCreateTime ()
	{ 
		return this.CreateTime ;
	}

	/// <summary>
	/// 构造函数
	/// </summary>
	public Record_GroupModel(){;}
}

