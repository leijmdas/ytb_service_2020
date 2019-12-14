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
public class Group_InfoModel
{
	private  int  Group_id =  0 ;
	private  String  GroupName =  "" ;
	private  Date  CreateTime =  null ;
	private  String  GroupIcon =  "" ;
	private  int  GroupType =  0 ;


	/* 
	 * 群组表
	 */
	public  int getGroup_id  ()
	{ 
		return this.Group_id  ;
	} 
	public void setGroup_id ( int  Group_id)
	{ 
		this.Group_id  = Group_id ;
	} 

	/* 
	 * 群组名称
	 */
	public  String getGroupName () 
	{ 
		return this.GroupName ;
	} 
	public void setGroupName ( String  GroupName) 
	{ 
		this.GroupName = GroupName ;
	} 

	/* 
	 * 创建时间
	 */
	public  Date getCreateTime () 
	{ 
		return this.CreateTime ;
	} 
	public void setCreateTime ( Date  CreateTime) 
	{ 
		this.CreateTime = CreateTime ;
	} 

	/* 
	 * 群组图标
	 */
	public  String getGroupIcon () 
	{ 
		return this.GroupIcon ;
	} 
	public void setGroupIcon ( String  GroupIcon) 
	{ 
		this.GroupIcon = GroupIcon ;
	} 

	/* 
	 * 群组类型(1:工作组;2:兴趣组)
	 */
	public  int getGroupType () 
	{ 
		return this.GroupType ;
	} 
	public void setGroupType ( int  GroupType) 
	{ 
		this.GroupType = GroupType ;
	} 


	/// <summary>
	/// 构造函数
	/// </summary>
	public Group_InfoModel(){;}
}

