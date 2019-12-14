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
public class Group_RemindModel
{
	private  int  Remind_id =  0 ;
	private  int  ToUser =  0 ;
	private  int  MessageId =  0 ;
	private  boolean  IsRead =  false ;


	/* 
	 * @用户表
	 */
	public  int getRemind_id ()
	{ 
		return this.Remind_id ;
	} 
	public void setRemind_id ( int  ID)
	{ 
		this.Remind_id = Remind_id ;
	} 

	/* 
	 * 接收者id
	 */
	public  int getToUser () 
	{ 
		return this.ToUser ;
	} 
	public void setToUser ( int  ToUser) 
	{ 
		this.ToUser = ToUser ;
	} 

	/* 
	 * 群消息id
	 */
	public  int getMessageId () 
	{ 
		return this.MessageId ;
	} 
	public void setMessageId ( int  MessageId) 
	{ 
		this.MessageId = MessageId ;
	} 

	/* 
	 * 是否已读(0::未读;1:已读)
	 */
	public  boolean getIsRead () 
	{ 
		return this.IsRead ;
	} 
	public void setIsRead ( boolean  IsRead) 
	{ 
		this.IsRead = IsRead ;
	} 


	/// <summary>
	/// 构造函数
	/// </summary>
	public Group_RemindModel(){;}
}

