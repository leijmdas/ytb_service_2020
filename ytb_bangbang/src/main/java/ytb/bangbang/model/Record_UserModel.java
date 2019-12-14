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
public class Record_UserModel
{
	private  int  Record_id =  0 ;
	private  String  Content =  "" ;
	private  String CreateTime = null;
	private  int  FromUser =  0 ;
	private  int  ToUser =  0 ;
    private String fromUserName;
    private String toUserName;

	/* 
	 * 聊天记录表
	 */
	public  int getRecord_id ()
	{ 
		return this.Record_id ;
	} 
	public void setRecord_id ( int  Record_id)
	{ 
		this.Record_id = Record_id;
	} 

	/* 
	 * 聊天内容
	 */
	public  String getContent () 
	{ 
		return this.Content ;
	} 
	public void setContent ( String  Content) 
	{ 
		this.Content = Content ;
	} 

	/* 
	 * 发送时间
	 */
	public  String getCreateTime ()
	{ 
		return this.CreateTime ;
	}

	/* 
	 * 发送者id
	 */
	public  int getFromUser () 
	{ 
		return this.FromUser ;
	} 
	public void setFromUser ( int  FromUser) 
	{ 
		this.FromUser = FromUser ;
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

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/// <summary>
	/// 构造函数
	/// </summary>
	public Record_UserModel(){;}
}

